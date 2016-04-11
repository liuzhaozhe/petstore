package com.petstore.web.servlet.user;

import com.petstore.persistence.UserDao;
import com.petstore.entity.User;
import com.petstore.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;

@WebServlet(name = "SignServlet", value = "/sign")
public class SignServlet extends HttpServlet {

    private static String indexPage = "index.jsp";
    private static String signPage = "/WEB-INF/jsp/user/sign.jsp";
    UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        String pwd2 = request.getParameter("password2");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String favcategory = request.getParameter("favcategory");
        String banneropt = request.getParameter("banneropt");

        if (!username.matches("[0-9A-Za-z_]*")){
            request.setAttribute("msg","用户名格式不正确");
            request.getRequestDispatcher(signPage).forward(request,response);
            return;
        }

        if(pwd.trim().length() == 0){
            request.setAttribute("msg","密码不能为空");
            request.getRequestDispatcher(signPage).forward(request,response);
            return;
        }

        if (!pwd.equals(pwd2)){
            request.setAttribute("msg","两次输入密码不同");
            request.getRequestDispatcher(signPage).forward(request,response);
            return;
        }
        Timestamp birth = null;
        if(birthday.trim().length() != 0){
            try {
                birth = Timestamp.valueOf(birthday + " 00:00:00");
            } catch (Exception e){
                e.printStackTrace();
                request.setAttribute("msg","出生日期格式不对");
                request.getRequestDispatcher(signPage).forward(request,response);
                return;
            }
        }

        User user = new User();
        user.setUsername(username);
        pwd = DigestUtils.md5Hex(pwd);
        user.setPassword(pwd);
        user.setAddress(address);
        user.setEmail(email);
        user.setPhone(phone);
        user.setName(name);
        user.setFavcategory(favcategory);
        user.setBirthday(birth);
        user.setBanneropt(Integer.parseInt(banneropt));
        if (userService.add(user)) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(indexPage);
        } else {
            String msg = "注册失败";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher(signPage).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
