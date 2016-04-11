package com.petstore.web.servlet.bill;

import com.petstore.entity.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateItemServlet", value = "/updateItem")
public class UpdateItemServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        int amount = Integer.parseInt(request.getParameter("amount"));
        List<Item> buyList = (List<Item>) request.getSession().getAttribute("buyList");
        for (Item itemTemp : buyList
                ) {
            if (itemTemp.getProductId().equals(productId)){
                itemTemp.setAmount(amount);
                itemTemp.setTotalPrice(amount * itemTemp.getPrice());
                break;
            }
        }
        request.getSession().setAttribute("buyList", buyList);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
