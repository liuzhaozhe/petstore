package com.petstore.web.servlet.product;

import com.petstore.persistence.ProductDao;
import com.petstore.service.ProductService;

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
@WebServlet(name = "ProductStockServlet", value = "/getStock")
public class ProductStockServlet extends HttpServlet {

    private static ProductService productService = new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        int stock = productService.getStock(productId);
        PrintWriter out = response.getWriter();
        out.print(stock);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
