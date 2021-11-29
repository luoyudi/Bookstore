package com.luoyvdi.test;

import com.luoyvdi.dao.OrderItemDao;
import com.luoyvdi.dao.impl.OrderItemDaoImpl;
import com.luoyvdi.domain.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(1,"java从入门到精通",1,new BigDecimal(100),new BigDecimal(100),"12345"));

    }
}