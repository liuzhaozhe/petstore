package com.petstore.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.petstore.entity.Log;
import com.petstore.entity.User;
import com.petstore.service.LogService;
import com.petstore.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class UserAction extends ActionSupport implements ModelDriven<User> {

    private User user = new User();
    private UserService userService = new UserService();
    private LogService logService = new LogService();
    Map<String, Object> session = ActionContext.getContext().getSession();

    /**
     * 注册
     *
     * @return
     */
    public String sign() {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        if (userService.add(user)) {
            session.put("user", user);
            return SUCCESS;
        } else {
            addActionError("注册失败");
            return ERROR;
        }
    }

    /**
     * 登录
     *
     * @return
     */
    public String login() {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        //根据用户名查找数据库中的信息并提取
        User loginUser = userService.getUser(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            session.put("user", loginUser);
            return SUCCESS;
        } else {
            addActionError("用户名或密码输入错误,请重新输入");
            return ERROR;
        }
    }

    /**
     * 更改用户信息
     *
     * @return
     */
    public String updateUser() {
        User sessionUser = (User) session.get("user");
        sessionUser.setPassword(sessionUser.getPassword());

        // 更改密码
        if (user.getPassword().trim().length() != 0) {
            sessionUser.setPassword(DigestUtils.md5Hex(user.getPassword()));
        }

        boolean result = userService.update(sessionUser);
        if (result) {
            session.put("user", user);
            addActionMessage("修改资料成功");
            return SUCCESS;
        } else {
            addActionError("修改资料失败");
            return ERROR;
        }
    }

    public String signOut(){
        session.remove("user");
        return SUCCESS;
    }

    /**
     * 检查用户名
     *
     * @return
     * @throws IOException
     */
    public String checkUsername() throws IOException {
        boolean isHas = userService.checkUsername(user.getUsername());
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out = response.getWriter();
        if (isHas) {
            out.print("exist");
        } else {
            out.print("not exist");
        }
        out.flush();
        out.close();
        return "";
    }

    /**
     * 查看日志
     * @return
     */
    public String log(){
        User sessionUser = (User) session.get("user");
        List<Log> logList = logService.find(sessionUser.getUsername());
        session.put("log", logList);
        return SUCCESS;
    }


    @Override
    public User getModel() {
        return user;
    }
}
