package com.luoyvdi.web.servlet;

import com.luoyvdi.dao.BookDao;
import com.luoyvdi.dao.impl.BookDaoImpl;
import com.luoyvdi.domain.Cart;
import com.luoyvdi.domain.User;
import com.luoyvdi.service.OrderService;
import com.luoyvdi.service.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();


    /**
     * 生成订单
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        User loginUser = (User) request.getSession().getAttribute("user");

        if (loginUser == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }

        Integer userId = loginUser.getId();
        String orderId = orderService.createOder(cart, userId);
//          request访问不到重定向后的值
//        request.getSession().setAttribute("orderId", orderId);

//        转发
//        request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request, response);
//        为了防止订单重复提交,使用重定向
        request.getSession().setAttribute("orderId" ,orderId);

        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
