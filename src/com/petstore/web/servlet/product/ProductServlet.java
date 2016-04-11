package com.petstore.web.servlet.product;

import com.petstore.persistence.CategoryDao;
import com.petstore.persistence.ProductDao;
import com.petstore.entity.Category;
import com.petstore.entity.Product;
import com.petstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {

    private static String productPage = "/WEB-INF/jsp/product/product.jsp";
    private ProductService productService = new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");

        Object[] o = productService.getProduct(productId);
        Product product = (Product) o[0];
        Category category = productService.getCategory((String) o[1]);
        request.getSession().setAttribute("product", product);
        request.getSession().setAttribute("category", category);
        request.getRequestDispatcher(productPage).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
