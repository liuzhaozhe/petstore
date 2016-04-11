package com.petstore.web.servlet.shoppingcar;

import com.petstore.persistence.ItemDao;
import com.petstore.entity.Item;
import com.petstore.entity.Product;
import com.petstore.entity.User;
import com.petstore.service.ShoppingCarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddShoppingCarServlet", value = "/addShoppingCar")
public class AddShoppingCarServlet extends HttpServlet {

    private static String shoppingCarPage = "/WEB-INF/jsp/shoppingCar/shoppingCar.jsp";
    private ShoppingCarService shoppingCarService = new ShoppingCarService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        Product product = (Product) request.getSession().getAttribute("product");
        String username = ((User) request.getSession().getAttribute("user")).getUsername();
        boolean result = shoppingCarService.checkCarItem(product.getProductId(), username);
        String msg;
        if (!result) {
            Item item = new Item();
            item.setAmount(1);
            item.setPrice(product.getPrice());
            item.setProductId(product.getProductId());
            item.setProductName(product.getProductName());
            item.setTotalPrice(item.getPrice());
            ItemDao.getInstance().addShoppingCar(item, username);
        }
        response.sendRedirect("shoppingCar");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
