package com.luoyvdi.web.servlet;

import com.luoyvdi.domain.Book;
import com.luoyvdi.domain.Page;
import com.luoyvdi.service.BookService;
import com.luoyvdi.service.impl.BookServiceImpl;
import com.luoyvdi.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ClientBookServlet", value = "/ClientBookServlet")
public class ClientBookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取请求参数
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
//        调用service 获取page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
//        设置url
        page.setUrl("/ClientBookServlet?action=page");
//        把page对象保存在request中
        request.setAttribute("page", page);
//        请求转发到pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取请求参数
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);
//        调用service 获取page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);

        StringBuilder sb = new StringBuilder("/ClientBookServlet?action=pageByPrice");
//        最小价格追加
        if (request.getParameter("min") != null) {
            sb.append("&min=").append(request.getParameter("min"));
        }
//        最大价格追加
        if (request.getParameter("max") != null) {
            sb.append("&max=").append(request.getParameter("max"));
        }
//        设置url
        page.setUrl(sb.toString());
//        把page对象保存在request中
        request.setAttribute("page", page);
//        请求转发到pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }
}
