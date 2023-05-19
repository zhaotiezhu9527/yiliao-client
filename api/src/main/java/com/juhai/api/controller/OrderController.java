package com.juhai.api.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.juhai.api.controller.request.OrderRequest;
import com.juhai.api.controller.request.UserRegisterRequest;
import com.juhai.api.utils.JwtUtils;
import com.juhai.commons.entity.*;
import com.juhai.commons.service.*;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Api(value = "订单相关", tags = "订单相关")
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private ParamterService paramterService;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Transactional
    @ApiOperation(value = "下注")
    @PostMapping("/execute")
    public R register(@Validated OrderRequest request, HttpServletRequest httpServletRequest) throws Exception {
        Date now = new Date();

        // 查询项目信息
        Project project = projectService.getById(request.getProjectId());
        if (project == null) {
            return R.error(MsgUtil.get("system.project.invalid"));
        }
        if (!DateUtil.isIn(now, project.getStartTime(), project.getEndTime()) || project.getStatus().intValue() == 1) {
            return R.error(MsgUtil.get("system.project.finshed"));
        }
        // 操作金额
        BigDecimal amount = new BigDecimal(request.getAmount());
        if (amount.doubleValue() < project.getMinAmount().doubleValue()) {
            return R.error(StrUtil.format(MsgUtil.get("system.order.minamount"), project.getMinAmount()));
        }

        // 查询用户信息
        String userName = JwtUtils.getUserName(httpServletRequest);
        User user = userService.getUserByName(userName);
        if (user.getUserStatus().intValue() == 1) {
            return R.error(MsgUtil.get("system.user.enable"));
        }
        if (user.getBalance().doubleValue() < amount.doubleValue()) {
            return R.error(MsgUtil.get("system.order.balance"));
        }

        // 订单号
        String orderNo = IdUtil.getSnowflakeNextIdStr();
        // 扣钱
        userService.updateUserBalance(userName, amount.negate());

        // 添加订单记录
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUserName(userName);
        order.setProjectId(project.getId());
        order.setProjectName(project.getProjectName());
        order.setRealName(user.getRealName());
        order.setAmount(amount);
        order.setIncomeRate(project.getIncomeRate());
        order.setLimitTime(project.getLimitTime());
        order.setStartTime(project.getStartTime());
        order.setEndTime(project.getEndTime());
        order.setForecastReturnTime(DateUtil.offsetMinute(now, project.getLimitTime()));
        order.setActualReturnTime(order.getForecastReturnTime());
        // 计算利息
        BigDecimal income = NumberUtil.mul(amount, NumberUtil.div(project.getIncomeRate(), 100));
        order.setForecastReturnAmount(income);
        order.setActualReturnAmount(income);
        order.setStatus(0);
        order.setUserAgent(user.getUserAgent());
        orderService.save(order);
        // 添加流水记录
        Account account = new Account();
        account.setUserName(userName);
        account.setOptAmount(amount.negate());
        account.setBeforeAmount(user.getBalance());
        account.setAfterAmount(NumberUtil.sub(user.getBalance(), amount));
        account.setType(2);
        account.setOptType(4);
        account.setOptTime(new Date());
        account.setUserAgent(user.getUserAgent());
        account.setRefNo(orderNo);
        account.setRemark("投资项目:" + project.getProjectName() + ",使用余额" + amount + "元");
        accountService.save(account);
        return R.ok();
    }
}
