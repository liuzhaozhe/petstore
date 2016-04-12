package com.petstore.web.servlet.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.petstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by hezhujun on 2016/4/11.
 */
@WebServlet(name = "GetStockAndPriceServlet", value = "/getProductInfo")
public class GetStockAndPriceServlet extends HttpServlet {

    ProductService productService = new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        Map<String, String> info = productService.getStockAndPrice(productId);
        PrintWriter out = response.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("price", info.get("price"));
        json.addProperty("stock", info.get("stock"));
        out.write(json.toString());
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
