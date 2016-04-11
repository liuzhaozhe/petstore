package com.petstore.web.servlet.shoppingcar;

import com.petstore.entity.Item;
import com.petstore.entity.User;
import com.petstore.service.ShoppingCarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by hezhujun on 2016/4/10.
 */
@WebServlet(name = "ShoppingCarServlet", value = "/shoppingCar")
public class ShoppingCarServlet extends HttpServlet {

    private static String shoppingCarPage = "/WEB-INF/jsp/shoppingCar/shoppingCar.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShoppingCarService shoppingCarService = new ShoppingCarService();
        String username = ((User)request.getSession().getAttribute("user")).getUsername();
        List<Item> items = shoppingCarService.getShoppingCarItems(username);
        request.getSession().setAttribute("itemList", items);
        request.getRequestDispatcher(shoppingCarPage).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
