package com.luoyvdi.test;

import com.luoyvdi.dao.OrderDao;
import com.luoyvdi.dao.impl.OrderDaoImpl;
import com.luoyvdi.domain.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("12345",new Date(),new BigDecimal(100),0,1));
    }
}