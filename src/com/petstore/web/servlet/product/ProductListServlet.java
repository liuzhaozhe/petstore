package com.petstore.web.servlet.product;

import com.petstore.persistence.ProductDao;
import com.petstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by liuzheng on 2016/3/12.
 */
@WebServlet(name = "ProductListServlet",value = "/productList")
public class ProductListServlet extends HttpServlet {

    private static String productListPage = "/WEB-INF/jsp/product/productList.jsp";
    private ProductService productService = new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category2Id = request.getParameter("category2");
        List<String[]> productList = productService.getProductInfo(category2Id);
        request.getSession().setAttribute("productList", productList);
        request.getRequestDispatcher(productListPage).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
