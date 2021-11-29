package com.luoyvdi.dao;

import com.luoyvdi.domain.Order;
import com.luoyvdi.domain.OrderItem;

public interface OrderItemDao {

    public int saveOrderItem(OrderItem orderItem);
}
