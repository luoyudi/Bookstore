package com.luoyvdi.dao.impl;

import com.luoyvdi.dao.OrderDao;
import com.luoyvdi.domain.Order;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`orderId`,`createTime`,`price`,`status`,`userId`) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
