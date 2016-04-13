package com.petstore.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.petstore.util.ValidationCode;
import com.petstore.util.ValidationCodeImage;

import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 * Created by hezhujun on 2016/4/11.
 */
public class ValidationCodeImageAction extends ActionSupport {

    private Map<String, Object> session = ActionContext.getContext().getSession();

    private ByteArrayInputStream imageStream = null;

    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }

    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }

    @Override
    public String execute() throws Exception {
        // 获取验证码
        String validationCode = ValidationCode.getValidationCode();
        imageStream = ValidationCodeImage.getImageAsInputStream(validationCode);
        // 放入session中
        session.put("validationCode", validationCode);
        return SUCCESS;
    }
}
