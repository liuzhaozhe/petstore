package com.petstore.web.servlet.bill;

import com.petstore.entity.Item;
import com.petstore.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzheng on 2016/3/21.
 */
@WebServlet(name = "AddBillByProductServlet", value = "/addBillByProduct")
public class AddBillByProductServlet extends HttpServlet {

    private static String buyPage = "/WEB-INF/jsp/bill/buy.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 生成Item对象保存购买商品信息
        Product product = (Product) request.getSession().getAttribute("product");
        Item item = new Item();
        item.setProductId(product.getProductId());
        item.setProductName(product.getProductName());
        item.setAmount(1);
        item.setPrice(product.getPrice());
        item.setTotalPrice(product.getPrice());
        // 页面以List方式接受商品信息
        List<Item> buyList = new ArrayList<Item>();
        buyList.add(item);
        request.getSession().setAttribute("buyList", buyList);
        request.setAttribute("totalPrice", item.getTotalPrice());
        request.getRequestDispatcher(buyPage).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
