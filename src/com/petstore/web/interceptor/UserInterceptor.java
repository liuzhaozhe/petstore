package com.petstore.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.petstore.entity.User;

import java.util.Map;

public class UserInterceptor implements Interceptor {

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
        if (user == null){
            ActionSupport action = (ActionSupport) actionInvocation.getAction();
            action.addActionError("请先登录");
            return action.LOGIN;
        } else {
            return actionInvocation.invoke();
        }
    }
}
