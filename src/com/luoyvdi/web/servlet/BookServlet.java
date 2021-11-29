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
import java.util.List;

@WebServlet(value = "/BookServlet")
public class BookServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 0);
        pageNo += 1;
//        获取请求参数封装book对象
        Book book = WebUtils.copyParamToBean(new Book(), request.getParameterMap());
//        调用bookService
        bookService.addBook(book);
//        跳转到图书列表页面
        response.sendRedirect("/BookServlet?action=page&pageNo=" + pageNo);
    }

    /**
     * 处理分页
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取请求参数
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
//        调用service 获取page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
//        设置url
        page.setUrl("/BookServlet?action=page");
//        把page对象保存在request中
        request.setAttribute("page", page);
//        请求转发到pages/manager/book_manager.jsp
        request.getRequestDispatcher("pages/manager/book_manager.jsp").forward(request, response);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取请求id
        String id = request.getParameter("id");
        int i = WebUtils.parseInt(id, 0);
//        调用bookService删除
        bookService.deleteBookById(i);
//        重定向图书管理页面
        response.sendRedirect("/BookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取请求参数,封装成对象
        Book book = WebUtils.copyParamToBean(new Book(), request.getParameterMap());
//        修改
        bookService.updateBook(book);
//        tai
        response.sendRedirect("/BookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取请求的图书编号
        String id = request.getParameter("id");
        int i = WebUtils.parseInt(id, 0);
        Book book = bookService.queryBookById(i);
//        把book存入request域中
        request.setAttribute("book", book);
//        重定向到更改页面
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        查询所有图书
        List<Book> books = bookService.queryBooks();
//        把数据保存到request域中
        request.setAttribute("books", books);
//        转发
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

}
