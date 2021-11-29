package com.luoyvdi.test;

import com.luoyvdi.domain.Cart;
import com.luoyvdi.domain.CartItem;
import com.luoyvdi.service.OrderService;
import com.luoyvdi.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderServiceTest {

    @Test
    public void createOder() {
        OrderService orderService = new OrderServiceImpl();
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "javaWeb", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "javaWeb", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构", 1, new BigDecimal(100), new BigDecimal(100)));
        System.out.println(cart);

        System.out.println(orderService.createOder(cart, 1));
    }
}