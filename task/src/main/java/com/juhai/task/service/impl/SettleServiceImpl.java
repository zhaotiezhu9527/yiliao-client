package com.juhai.task.service.impl;
import java.math.BigDecimal;
import java.util.Date;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.juhai.commons.entity.Account;
import com.juhai.commons.entity.Order;
import com.juhai.commons.entity.User;
import com.juhai.commons.service.AccountService;
import com.juhai.commons.service.OrderService;
import com.juhai.commons.service.UserService;
import com.juhai.task.service.SettleService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SettleServiceImpl implements SettleService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Transactional
    @Override
    public void settle(Order order) throws Exception {
        Date now = new Date();
        BigDecimal amount = NumberUtil.add(order.getAmount(), order.getForecastReturnAmount());
        // 修改订单状态
        boolean flag = orderService.update(
                new UpdateWrapper<Order>().lambda()
                        .set(Order::getStatus, 1)
                        .set(Order::getActualReturnTime, now)
                        .eq(Order::getId, order.getId())
                        .eq(Order::getStatus, 0)
        );
        if (!flag) {
            throw new Exception("修改订单失败");
        }

        User user = userService.getUserByName(order.getUserName());
        // 添加流水记录
        Account account = new Account();
        account.setUserName(order.getUserName());
        account.setOptAmount(amount);
        account.setBeforeAmount(user.getBalance());
        account.setAfterAmount(NumberUtil.add(user.getBalance(), amount));
        account.setType(1);
        account.setOptType(5);
        account.setOptTime(now);
        account.setUserAgent(order.getUserAgent());
        account.setRefNo(order.getOrderNo());
        account.setRemark(order.getProjectName() + "第一期收益" + amount + "元");
        accountService.save(account);

        // 给用户加钱
        userService.updateUserBalance(order.getUserName(), amount);
    }
}