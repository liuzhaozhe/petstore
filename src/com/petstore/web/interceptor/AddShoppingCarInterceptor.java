package com.petstore.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.petstore.entity.Log;
import com.petstore.entity.Product;
import com.petstore.entity.User;
import com.petstore.service.LogService;
import com.petstore.service.ProductService;

import java.sql.Timestamp;
import java.util.Map;

public class AddShoppingCarInterceptor implements Interceptor {

    private LogService logService = new LogService();
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
            session = ActionContext.getContext().getSession();
            Product product = (Product) session.get("product");
            Log log = new Log();
            log.setOperation("把商品 " + product.getProductName() + " 加入到购物车");
            log.setTime(new Timestamp(System.currentTimeMillis()));
            session = ActionContext.getContext().getSession();
            User user = (User) session.get("user");
            log.setUsername(user.getUsername());
            logService.insert(log);
        }
        return result;
    }
}
