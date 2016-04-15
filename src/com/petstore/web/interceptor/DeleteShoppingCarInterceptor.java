package com.petstore.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.petstore.entity.Log;
import com.petstore.entity.Product;
import com.petstore.entity.User;
import com.petstore.service.LogService;
import com.petstore.service.ProductService;
import com.petstore.web.action.ShoppingCarAction;

import java.sql.Timestamp;
import java.util.Map;

public class DeleteShoppingCarInterceptor implements Interceptor {

    private LogService logService = new LogService();
    private ProductService productService = new ProductService();
    private Map<String, Object> session;

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        String result = actionInvocation.invoke();
        if(result.equals("success")){
            ShoppingCarAction action = (ShoppingCarAction) actionInvocation.getAction();
            String productId = action.getProductId();
            Object[] o = productService.getProduct(productId);
            Product product = (Product) o[0];
            Log log = new Log();
            log.setOperation("把商品 " + product.getProductName() + " 从购物车中删除");
            log.setTime(new Timestamp(System.currentTimeMillis()));
            session = ActionContext.getContext().getSession();
            User user = (User) session.get("user");
            log.setUsername(user.getUsername());
            logService.insert(log);
        }
        return result;
    }
}
