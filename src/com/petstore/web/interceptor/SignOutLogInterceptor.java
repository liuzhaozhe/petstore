package com.petstore.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.petstore.entity.Log;
import com.petstore.entity.User;
import com.petstore.service.LogService;

import java.sql.Timestamp;
import java.util.Map;

public class SignOutLogInterceptor implements Interceptor {
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
        session = ActionContext.getContext().getSession();
        User user = (User) session.get("user");
        if (user != null) {
            Log log = new Log();
            log.setUsername(user.getUsername());
            log.setOperation("登出");
            log.setTime(new Timestamp(System.currentTimeMillis()));
            logService.insert(log);
        }
        return actionInvocation.invoke();
    }
}
