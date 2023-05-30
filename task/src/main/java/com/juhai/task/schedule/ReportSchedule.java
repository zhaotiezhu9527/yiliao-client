package com.juhai.task.schedule;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.juhai.commons.entity.*;
import com.juhai.commons.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 每周奖励
 * @author Administrator
 */
@Slf4j
@Component
public class ReportSchedule {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private DayReportService dayReportService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 汇总昨日报表
     */
    @Scheduled(cron = "0 5 0 1/1 * ?")
    public void report() {
        log.info("报表任务开启");
        Date date = new Date();
        DateTime yesterDay = DateUtil.offsetDay(date, -1);
        String  today = DateUtil.format(yesterDay, "yyyyMMdd");
        String key = "day:report:" + today;
        if (redisTemplate.hasKey(key)) {
            log.info(today + "报表任务已完成");
            return;
        }

        Date beginTime = DateUtil.beginOfDay(yesterDay);
        Date endTime = DateUtil.endOfDay(yesterDay);
        // 查询昨日注册
        long registerCount = userService.count(
                new LambdaQueryWrapper<User>()
                        .ge(User::getRegisterTime, beginTime)
                        .le(User::getRegisterTime, endTime)
        );
        // 昨日充值量
        List<Deposit> deposits = depositService.list(new LambdaQueryWrapper<Deposit>().ge(Deposit::getOptTime, beginTime).le(Deposit::getOptTime, endTime).eq(Deposit::getStatus, 1));
        BigDecimal depositAmount = new BigDecimal(0);
        Set<String> depositSet = new HashSet<>();
        for (Deposit temp : deposits) {
            depositAmount = NumberUtil.add(depositAmount, temp.getOptAmount());
            depositSet.add(temp.getUserName());
        }

        // 昨日提现量
        List<Withdraw> withdraws = withdrawService.list(new LambdaQueryWrapper<Withdraw>().ge(Withdraw::getOptTime, beginTime).le(Withdraw::getOptTime, endTime).eq(Withdraw::getStatus, 1));
        BigDecimal withdrawAmount = new BigDecimal(0);
        Set<String> withdrawSet = new HashSet<>();
        for (Withdraw temp : withdraws) {
            withdrawAmount = NumberUtil.add(withdrawAmount, temp.getOptAmount());
            withdrawSet.add(temp.getUserName());
        }

        // 昨日投资量
        List<Order> orders = orderService.list(new LambdaQueryWrapper<Order>().ge(Order::getOrderTime, beginTime).le(Order::getOrderTime, endTime));
        // 总投资
        BigDecimal investmentAmount = new BigDecimal(0);
        // 已返还利息
        BigDecimal returnIncome = new BigDecimal(0);
        Set<String> investmentSet = new HashSet<>();
        for (Order temp : orders) {
            investmentAmount = NumberUtil.add(investmentAmount, temp.getAmount());
            investmentSet.add(temp.getUserName());
            returnIncome = NumberUtil.add(returnIncome, temp.getForecastReturnAmount());
        }
        // 写入报表
        DayReport dayReport = new DayReport();
        dayReport.setToday(today);
        dayReport.setDepositAmount(depositAmount);
        dayReport.setWithdrawAmount(withdrawAmount);
        dayReport.setInvestmentAmount(investmentAmount);
        dayReport.setIncomeAmount(returnIncome);
        dayReport.setRegisterCount((int)registerCount);
        dayReport.setDepositCount(depositSet.size());
        dayReport.setWithdrawCount(withdrawSet.size());
        dayReport.setInvestmentCount(investmentSet.size());
        dayReportService.save(dayReport);

        redisTemplate.opsForValue().set(key, "true", 2, TimeUnit.DAYS);
        log.info("报表任务结束");
    }
}
