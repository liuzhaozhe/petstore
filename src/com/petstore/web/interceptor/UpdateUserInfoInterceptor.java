package com.petstore.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.petstore.entity.Log;
import com.petstore.entity.User;
import com.petstore.service.LogService;

import java.sql.Timestamp;
import java.util.Map;

public class UpdateUserInfoInterceptor implements Interceptor {

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
            Log log = new Log();
            session = ActionContext.getContext().getSession();
            User user = (User) session.get("user");
            log.setUsername(user.getUsername());
            log.setOperation("修改用户信息");
            log.setTime(new Timestamp(System.currentTimeMillis()));
            logService.insert(log);
        }
        return result;
    }
}
