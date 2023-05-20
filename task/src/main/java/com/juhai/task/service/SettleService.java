package com.juhai.task.service;

import com.juhai.commons.entity.Order;

public interface SettleService {

    void settle(Order order) throws Exception;
}
