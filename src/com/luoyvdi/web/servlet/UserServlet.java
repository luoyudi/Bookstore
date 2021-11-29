package com.luoyvdi.web.servlet;

import com.luoyvdi.domain.User;
import com.luoyvdi.service.UserService;
import com.luoyvdi.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 实现登录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        获取生成的验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");

//            登录
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//            获取输入的验证码
        String code = request.getParameter("code");

        if (Objects.equals(code, "")) {//检查验证码
            //            把错误信息写入到request域中
            request.setAttribute("msg", "验证码为空");
//            错误,跳回页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        } else {
            if (checkcode_server.equalsIgnoreCase(code)) {
                //        验证用户名和密码是否正确
                User loginUser = userService.login(new User(null, username, password, null));
                if (loginUser == null) {
//            把错误信息和回显信息存储到request域中
                    request.setAttribute("msg", "用户名或密码错误");
//            没有查找到,登陆失败
                    request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
                } else {
//            查找到了登录成功
                    request.getSession().setAttribute("user", loginUser);
                    request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
                }
            } else {
                //            把错误信息写入到request域中
                request.setAttribute("msg", "验证码错误");
//            错误,跳回页面
                request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            }
        }


    }

    /**
     * 实现注册
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取生成的验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");


//            注册
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password1 = request.getParameter("repwd");


        String email = request.getParameter("email");
//        获取输入的验证码
        String code = request.getParameter("code");
//        检查验证码
        if (Objects.equals(code, "")) {
//            把错误信息写入到request域中
            request.setAttribute("msg", "验证码为空");
//            错误,跳回页面
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        } else {
            if (Objects.equals(password, password1)) {
                if (checkcode_server.equalsIgnoreCase(code)) {
//            相等
//            检查用户名是否可用
                    if (userService.existsUserName(username)) {
//                不可用
//                把错误信息写入到request域中
                        request.setAttribute("msg", "用户名不可用");
                        request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
                    } else {
//                可用
                        userService.registerUser(new User(null, username, password, email));
                        request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
                    }
                } else {
//            把错误信息写入到request域中
                    request.setAttribute("msg", "验证码错误");
//            错误,跳回页面
                    request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
                }
            } else {
                //            把错误信息写入到request域中
                request.setAttribute("msg", "两次密码不相同");
//            错误,跳回页面
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }

        }
    }

    /**
     * 注销登录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        销毁session
        request.getSession().invalidate();
//        重定向到主页
        response.sendRedirect(request.getContextPath() + "index.jsp");
    }

}
