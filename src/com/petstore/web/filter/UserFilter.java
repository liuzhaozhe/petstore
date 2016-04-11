package com.petstore.web.filter;

import com.petstore.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by hezhujun on 2016/4/11.
 */
@WebFilter(filterName = "UserFilter", value = {
        "/userInfo",
        "/shoppingCar",
        "/addShoppingCar",
        "/deleteShoppingCar",
        "/updateShoppingCar",
        "/addBillByCar",
        "/addBillByProduct",
        "/addBill",
        "/confirmBuy",
        "/deleteItem",
        "/billDetail",
        "/billList",
        "/updateItem"
})
public class UserFilter implements Filter {

    private static String loginForm = "/WEB-INF/jsp/user/login.jsp";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        User user = (User) req.getAttribute("user");
        if (user == null) {
            req.setAttribute("msg", "请先登录");
            req.getRequestDispatcher(loginForm).forward(req, resp);
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
