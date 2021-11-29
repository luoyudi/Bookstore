package com.luoyvdi.dao.impl;

import com.luoyvdi.dao.OrderItemDao;
import com.luoyvdi.domain.OrderItem;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`totalPrice`,`orderId`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
