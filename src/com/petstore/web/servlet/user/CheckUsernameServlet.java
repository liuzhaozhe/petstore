package com.petstore.web.servlet.user;

import com.petstore.persistence.UserDao;
import com.petstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by hezhujun on 2016/3/20.
 */
@WebServlet(name = "CheckUsernameServlet", value = "/checkUsername")
public class CheckUsernameServlet extends HttpServlet {

    UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        boolean isHas = userService.checkUsername(username);
        PrintWriter out = response.getWriter();
        if (isHas){
            out.print("exist");
        } else {
            out.print("not exist");
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
