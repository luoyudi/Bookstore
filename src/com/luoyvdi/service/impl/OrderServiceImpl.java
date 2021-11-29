package com.luoyvdi.service.impl;

import com.luoyvdi.dao.BookDao;
import com.luoyvdi.dao.OrderDao;
import com.luoyvdi.dao.OrderItemDao;
import com.luoyvdi.dao.impl.BookDaoImpl;
import com.luoyvdi.dao.impl.OrderDaoImpl;
import com.luoyvdi.dao.impl.OrderItemDaoImpl;
import com.luoyvdi.domain.*;
import com.luoyvdi.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOder(Cart cart, Integer userId) {

        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);

        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
//            获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
//            转化为订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
//            保存到数据库中
            orderItemDao.saveOrderItem(orderItem);

            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());

            book.setStock(book.getStock()-cartItem.getCount());

            bookDao.updateBook(book);
        }
//        清空购物车
        cart.clear();
        return orderId;
    }
}
