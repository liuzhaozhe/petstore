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
import java.sql.Timestamp;

/**
 * Created by liuzheng on 2016/3/22.
 */
@WebServlet(name = "UpdateUserServlet", value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {

    private static String userPage = "/WEB-INF/jsp/user/user.jsp";
    private UserService userService = new UserService();

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

        User user = new User();
        User sessionUser = (User) request.getSession().getAttribute("user");
        user.setPassword(sessionUser.getPassword());

        if(pwd.trim().length() != 0){
            if (!pwd.equals(pwd2)){
                request.setAttribute("msg","两次输入密码不同");
                request.getRequestDispatcher(userPage).forward(request,response);
                return;
            } else {
                pwd = DigestUtils.md5Hex(pwd);
                user.setPassword(pwd);
            }
        }

        Timestamp birth = null;
        if(birthday.trim().length() != 0){
            try {
                birth = Timestamp.valueOf(birthday + " 00:00:00");
            } catch (Exception e){
                e.printStackTrace();
                request.setAttribute("msg","出生日期格式不对");
                request.getRequestDispatcher(userPage).forward(request,response);
                return;
            }
        }

        user.setUsername(username);
        user.setAddress(address);
        user.setEmail(email);
        user.setPhone(phone);
        user.setName(name);
        user.setFavcategory(favcategory);
        user.setBirthday(birth);
        user.setBanneropt(Integer.parseInt(banneropt));

        boolean result = userService.update(user);
        String msg;
        if (result) {
            request.getSession().setAttribute("user", user);
            msg = "修改资料成功";
        } else {
            msg = "修改资料失败";
        }
        request.setAttribute("msg", msg);
        request.getRequestDispatcher(userPage).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
