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
 * Created by hezhujun on 2016/3/21.
 */
@WebServlet(name = "DeleteItemServlet", value = "/deleteItem")
public class DeleteItemServlet extends HttpServlet {

    private static String buyPage = "/WEB-INF/jsp/bill/buy.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        List<Item> buyList = (List<Item>) request.getSession().getAttribute("buyList");
        int index = -1;
        for (int i = 0; i < buyList.size(); i++) {
            Item itemTemp = buyList.get(i);
            if (itemTemp.getProductId().equals(productId)) {
                index = i;
                break;
            }
        }
        buyList.remove(index);
        request.getSession().setAttribute("buyList", buyList);
        response.sendRedirect("updateNewBill");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
