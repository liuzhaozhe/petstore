package com.petstore.web.servlet.product;


import com.petstore.entity.Product;
import com.petstore.persistence.CategoryDao;
import com.petstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by liuzheng on 2016/3/12.
 */
@WebServlet(name = "CategoryServlet", value = "/category")
public class CategoryServlet extends HttpServlet {

    private static String categoryPage = "/WEB-INF/jsp/product/categoryList.jsp";
    private ProductService productService = new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String category = request.getParameter("category");

        Map<String, String> category2 = productService.getCategory2(category);
        request.getSession().setAttribute("category2", category2);
        request.getSession().setAttribute("categoryName", category);
        request.getRequestDispatcher(categoryPage).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
