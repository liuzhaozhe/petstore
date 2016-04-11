package com.petstore.web.servlet.product;

import com.petstore.entity.Category;
import com.petstore.entity.Product;
import com.petstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by hezhujun on 2016/4/10.
 */
@WebServlet(name = "SearchServlet",value = "/search")
public class SearchServlet extends HttpServlet {

    private static String productListPage = "/WEB-INF/jsp/product/productList.jsp";
    private static String productPage = "/WEB-INF/jsp/product/product.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String search = request.getParameter("search");

        ProductService productService = new ProductService();
        List<String[]> productList = productService.getProductInfoByName(search);
        if (productList == null || productList.size() != 1) {
            request.getSession().setAttribute("productList", productList);
            request.getRequestDispatcher(productListPage).forward(request,response);
        } else {
            // 只有一个时直接到商品详细页
            Object[] o = productService.getProduct(productList.get(0)[0]);
            Product product = (Product) o[0];
            Category category = productService.getCategory((String) o[1]);
            request.getSession().setAttribute("product", product);
            request.getSession().setAttribute("category", category);
            request.getRequestDispatcher(productPage).forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
