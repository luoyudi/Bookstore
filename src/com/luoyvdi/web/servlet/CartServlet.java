package com.luoyvdi.web.servlet;

import com.luoyvdi.domain.Book;
import com.luoyvdi.domain.Cart;
import com.luoyvdi.domain.CartItem;
import com.luoyvdi.service.BookService;
import com.luoyvdi.service.impl.BookServiceImpl;
import com.luoyvdi.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取请求商品编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
//        调用bookService的query查询图书信息
        Book book = bookService.queryBookById(id);
//        把book转化为cartItem
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
//        调用cart.addItem方法添加图书
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
//        最后一个名称
        request.getSession().setAttribute("lastName", cartItem.getName());
//        重定向回商品列表
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取商品编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart != null) {
//            删除商品
            cart.deleteItem(id);
//            重定向回购物车
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
//            重定向回购物车
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    /**
     * 修改商品数量
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取请求参数
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if(cart!=null){
            cart.updateCount(id, count);
//            重定向回购物车
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}
