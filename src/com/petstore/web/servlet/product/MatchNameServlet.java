package com.petstore.web.servlet.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.petstore.persistence.ProductDao;
import com.petstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by hezhujun on 2016/3/22.
 */
@WebServlet(name = "MatchNameServlet", value = "/matchName")
public class MatchNameServlet extends HttpServlet {

    ProductService productService = new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String search = request.getParameter("search");
        List<String> productInfo = productService.getProductNameList(search);
        JsonArray array = new JsonArray();
        for (String name : productInfo
             ) {
            JsonObject temp = new JsonObject();
            temp.addProperty("value", name);
            array.add(temp);
        }
        PrintWriter out = response.getWriter();
        out.print(array.toString());
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
