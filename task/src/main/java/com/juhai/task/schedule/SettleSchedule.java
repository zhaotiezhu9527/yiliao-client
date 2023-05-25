package com.juhai.task.schedule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.juhai.commons.entity.Order;
import com.juhai.commons.service.OrderService;
import com.juhai.task.service.SettleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 每周奖励
 * @author Administrator
 */
@Slf4j
@Component
public class SettleSchedule {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SettleService settleService;

    @Scheduled(cron = "0/10 * * * * ?")
    public void settle() {
        log.info("结算任务开启");
        Date now = new Date();
        List<Order> list = orderService.list(
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getStatus, 0)
                        .le(Order::getForecastReturnTime, now)
        );
        for (Order order : list) {
            try {
                settleService.settle(order);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.info("结算任务结束");
    }
}
