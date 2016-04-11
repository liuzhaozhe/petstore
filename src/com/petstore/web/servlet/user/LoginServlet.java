package com.petstore.web.servlet.user;

import com.petstore.persistence.UserDao;
import com.petstore.entity.User;
import com.petstore.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Created by liuzheng on 2016/3/12.
 */
@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends javax.servlet.http.HttpServlet {

    private static String indexPage = "index.jsp";
    private static String loginPage = "/WEB-INF/jsp/user/login.jsp";
    UserService userService = new UserService();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获取登录网页中的用户名的字符串
        String name = request.getParameter("username");
        //获取用户登录的密码字符串
        String pwd = request.getParameter("password");
        pwd = DigestUtils.md5Hex(pwd);
        //根据用户名查找数据库中的信息并提取
        User user = userService.getUser(name, pwd);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("index.jsp");
        } else {
            String msg = "用户名或密码输入错误,请重新输入";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher(loginPage).forward(request, response);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
