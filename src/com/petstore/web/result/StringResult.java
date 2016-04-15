package com.petstore.web.result;

import com.opensymphony.xwork2.ActionInvocation;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class StringResult extends StrutsResultSupport {

    /**
     * 编码方式
     */
    private String charSet = "UTF-8";

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    @Override
    protected void doExecute(String s, ActionInvocation actionInvocation) throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding(charSet);
        PrintWriter out = response.getWriter();
        out.write(s);
        out.flush();
        out.close();
    }
}
