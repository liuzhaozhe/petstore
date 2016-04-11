package com.petstore.web.servlet.shoppingcar;

import com.petstore.persistence.ItemDao;
import com.petstore.entity.Item;
import com.petstore.entity.User;
import com.petstore.service.ShoppingCarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by liuzheng on 2016/3/20.
 */
@WebServlet(name = "UpdateShoppingCarServlet", value = "/updateShoppingCar")
public class UpdateShoppingCarServlet extends HttpServlet {

    private ShoppingCarService shoppingCarService = new ShoppingCarService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String username = ((User) request.getSession().getAttribute("user")).getUsername();
        String productId = request.getParameter("productId");
        int amount = Integer.parseInt(request.getParameter("amount"));

        Item item = shoppingCarService.getCarItem(productId, username);
        item.setAmount(amount);
        item.setTotalPrice(amount * item.getPrice());
        shoppingCarService.update(item, username);
        response.sendRedirect("shoppingCar");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
