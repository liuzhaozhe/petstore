package com.petstore.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.petstore.entity.Log;
import com.petstore.entity.User;
import com.petstore.service.LogService;

import java.sql.Timestamp;
import java.util.Map;

public class BuyInterceptor implements Interceptor {

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
        if (result.equals("success")) {
            session = ActionContext.getContext().getSession();
            User user = (User) session.get("user");
            Log log = new Log();
            log.setUsername(user.getUsername());
            log.setOperation("购买商品");
            log.setTime(new Timestamp(System.currentTimeMillis()));
            logService.insert(log);
        }
        return result;
    }
}
