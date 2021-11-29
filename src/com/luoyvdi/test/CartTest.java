package com.luoyvdi.test;

import com.luoyvdi.domain.Cart;
import com.luoyvdi.domain.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {

    Cart cart = new Cart();

    @Test
    public void addItem() {

        cart.addItem(new CartItem(1, "javaWeb", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "javaWeb", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构", 1, new BigDecimal(100), new BigDecimal(100)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        this.addItem();
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        this.addItem();
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        this.addItem();
        cart.updateCount(1, 1);
        System.out.println(cart);
    }
}