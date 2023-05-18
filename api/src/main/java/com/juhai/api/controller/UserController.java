package com.juhai.api.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.juhai.api.controller.request.LoginRequest;
import com.juhai.api.controller.request.UserRegisterRequest;
import com.juhai.api.utils.JwtUtils;
import com.juhai.commons.entity.User;
import com.juhai.commons.entity.UserLog;
import com.juhai.commons.service.ParamterService;
import com.juhai.commons.service.UserLogService;
import com.juhai.commons.service.UserService;
import com.juhai.commons.utils.MsgUtil;
import com.juhai.commons.utils.R;
import com.juhai.commons.utils.RedisKeyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Api(value = "用户相关", tags = "用户")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ParamterService paramterService;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @ApiOperation(value = "用户信息")
    @GetMapping("/info")
    public R list(HttpServletRequest httpServletRequest) {
        String userName = JwtUtils.getUserName(httpServletRequest);
        User user = userService.getUserByName(userName);
        JSONObject temp = new JSONObject();
        temp.put("userId", user.getId());
        temp.put("userName", user.getUserName());
        temp.put("balance", user.getBalance());
        temp.put("realName", user.getRealName());
        temp.put("idCard", user.getIdCard());
        temp.put("inviteCode", user.getInviteCode());
        temp.put("walletAddr", user.getWalletAddr());
        temp.put("bankCardNum", user.getBankCardNum());
        temp.put("bankName", user.getBankName());
        temp.put("bankAddr", user.getBankAddr());
        temp.put("userLevelName", "普通用户");
        temp.put("isRealName", user.getIsRealName());
        return R.ok().put("data", temp);
    }

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public R register(@Validated UserRegisterRequest request, HttpServletRequest httpServletRequest) {
        Map<String, String> paramsMap = paramterService.getAllParamByMap();

        // 校验两次密码一致
        if (!StringUtils.equals(request.getConfirmLoginPwd(), request.getLoginPwd())) {
            return R.error(MsgUtil.get("system.user.register.pwd"));
        }
        
        // 查询用户名是否存在
        long exist = userService.count(new LambdaQueryWrapper<User>().eq(User::getUserName, request.getUserName()));
        if (exist > 0) {
            return R.error(MsgUtil.get("system.user.register.exist"));
        }

        // 查询代理用户
        User agent = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getInviteCode, request.getInviteCode()));
        if (agent == null) {
            return R.error(MsgUtil.get("system.user.register.invitecode"));
        }

        String clientIP = ServletUtil.getClientIP(httpServletRequest);

        User user = new User();
        user.setUserName(request.getUserName());
        user.setBalance(new BigDecimal("0"));
        user.setRealName(null);
        user.setIdCard(null);
        user.setLoginPwd(SecureUtil.md5(request.getLoginPwd()));
        user.setPayPwd(SecureUtil.md5(request.getPayPwd()));
        user.setInviteCode(RandomUtil.randomNumbers(6));
        user.setWalletAddr(null);
        user.setBankCardNum(null);
        user.setBankName(null);
        user.setBankAddr(null);
        user.setUserStatus(0);
        user.setUserLevelId(0);
        user.setUserAgent(agent.getUserName());
        user.setIsRealName(1);
        user.setRegisterTime(new Date());
        user.setRegisterIp(clientIP);
        user.setLastTime(new Date());
        user.setLastIp(clientIP);
        user.setModifyTime(new Date());
        userService.save(user);

        // 登录日志
        UserLog log = new UserLog();
        log.setUserName(user.getUserName());
        log.setIp(clientIP);
        log.setIpDetail(null);
        log.setLoginTime(new Date());
        userLogService.save(log);

        /** 保存token **/
        Map<String, String> map = new HashMap<>();
        map.put("userName", user.getUserName());
        map.put("userIp", clientIP);
        map.put("random", RandomUtil.randomString(6));
        String token = JwtUtils.getToken(map);
        redisTemplate.opsForValue().set(RedisKeyUtil.UserTokenKey(user.getUserName()), token, 15, TimeUnit.MINUTES);
        return R.ok().put("token", token);
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public R login(@Validated LoginRequest request, HttpServletRequest httpServletRequest) {
        String clientIP = ServletUtil.getClientIP(httpServletRequest);

        // 查询用户信息
        User user = userService.getUserByName(request.getUserName());
        if (user == null) {
            return R.error(MsgUtil.get("system.user.login.noexist"));
        }

        // 获取所有参数配置
        Map<String, String> paramsMap = paramterService.getAllParamByMap();

        String incKey = RedisKeyUtil.LoginPwdErrorKey(user.getUserName());
        /** 每日错误次数上限 **/
        String dayCount = redisTemplate.opsForValue().get(incKey);
        int count = NumberUtils.toInt(dayCount, 0);
        Integer pwdErrCount = MapUtil.getInt(paramsMap, "login_pwd_error", 0);
        if (pwdErrCount > 0 && count >= pwdErrCount) {
            return R.error(MsgUtil.get("system.user.login.pwd.limit"));
        }

        // 验证密码正确
        String pwd = SecureUtil.md5(request.getLoginPwd());
        if (!StringUtils.equals(pwd, user.getLoginPwd())) {
            /** 累计密码错误 **/
            redisTemplate.opsForValue().increment(incKey);
            redisTemplate.expire(incKey, 1, TimeUnit.DAYS);
            return R.error("密码错误");
        }
        if (user.getUserStatus().intValue() == 1) {
            return R.error(MsgUtil.get("system.user.enable"));
        }

        Date now = new Date();
        /** 更新最后登录时间 **/
        userService.update(
                new UpdateWrapper<User>().lambda()
                        .eq(User::getId, user.getId())
                        .set(User::getLastIp, clientIP)
                        .set(User::getLastTime, now)
        );

        // 登录日志
        UserLog log = new UserLog();
        log.setUserName(user.getUserName());
        log.setIp(clientIP);
        log.setIpDetail(null);
        log.setLoginTime(new Date());
        userLogService.save(log);

        /** 保存token **/
        Map<String, String> map = new HashMap<>();
        map.put("userName", user.getUserName());
        map.put("userIp", clientIP);
        map.put("random", RandomUtil.randomString(6));
        String token = JwtUtils.getToken(map);
        redisTemplate.opsForValue().set(RedisKeyUtil.UserTokenKey(user.getUserName()), token, 15, TimeUnit.MINUTES);

        /** 删除密码输入错误次数 **/
        redisTemplate.delete(incKey);
        return R.ok().put("token", token);
    }
}
