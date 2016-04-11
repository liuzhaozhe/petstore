package com.petstore.web.servlet.bill;

import com.petstore.entity.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hezhujun on 2016/3/21.
 */
@WebServlet(name = "AddBillByCarServlet", value = "/addBillByCar")
public class AddBillByCarServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        List<Item> buyList = new ArrayList<Item>();
        List<Item> itemList = (List<Item>) request.getSession().getAttribute("itemList");

        if (productId != null) {
            for (Item item : itemList
                    ) {
                if (item.getProductId().equals(productId)) {
                    buyList.add(item);
                    break;
                }
            }
        } else {
            // 购买购物车所有的商品
            buyList.addAll(itemList);
        }
        request.getSession().setAttribute("buyList", buyList);
        response.sendRedirect("updateNewBill");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
