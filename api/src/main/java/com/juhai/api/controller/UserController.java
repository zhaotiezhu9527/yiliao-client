package com.juhai.api.controller;
import java.math.BigDecimal;
import java.util.Date;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.juhai.api.controller.request.UserRegisterRequest;
import com.juhai.api.utils.JwtUtils;
import com.juhai.commons.entity.User;
import com.juhai.commons.service.ParamterService;
import com.juhai.commons.service.UserService;
import com.juhai.commons.utils.MsgUtil;
import com.juhai.commons.utils.R;
import com.juhai.commons.utils.RedisKeyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    private StringRedisTemplate redisTemplate;

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public R registerPhone(@Validated UserRegisterRequest request, HttpServletRequest httpServletRequest) {
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

        /** 保存token **/
        Map<String, String> map = new HashMap<>();
        map.put("userName", user.getUserName());
        map.put("userIp", clientIP);
        map.put("random", RandomUtil.randomString(6));
        String token = JwtUtils.getToken(map);
        redisTemplate.opsForValue().set(RedisKeyUtil.UserTokenKey(user.getUserName()), token, 15, TimeUnit.MINUTES);
        return R.ok().put("token", token);
    }
}
