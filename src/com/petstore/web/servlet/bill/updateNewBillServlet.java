package com.petstore.web.servlet.bill;

import com.petstore.entity.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by hezhujun on 2016/4/11.
 */
@WebServlet(name = "updateNewBillServlet", value = "/updateNewBill")
public class updateNewBillServlet extends HttpServlet {

    private static String buyPage = "/WEB-INF/jsp/bill/buy.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Item> buyList = (List<Item>) request.getSession().getAttribute("buyList");
        // 计算总金额
        double totalPrice = 0;
        for (Item item : buyList
                ) {
            totalPrice += item.getTotalPrice();
        }
        request.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher(buyPage).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
