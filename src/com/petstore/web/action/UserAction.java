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
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class UserAction extends ActionSupport {

    private UserService userService = new UserService();
    private LogService logService = new LogService();
    Map<String, Object> session = ActionContext.getContext().getSession();

    /**
     * 注册
     *
     * @return
     */
    public String sign() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        user.setName(name);
        user.setAddress(address);
        user.setEmail(email);
        user.setBirthday(birthday);
        user.setFavcategory(favcategory);
        user.setBanneropt(banneropt);
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
        password = DigestUtils.md5Hex(password);
        //根据用户名查找数据库中的信息并提取
        User loginUser = userService.getUser(username, password);
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
        User user = new User();
        user.setUsername(username);

        User sessionUser = (User) session.get("user");
        user.setPassword(sessionUser.getPassword());

        // 更改密码
        if (password.trim().length() != 0) {
            user.setPassword(DigestUtils.md5Hex(password));
        }

        user.setName(name);
        user.setAddress(address);
        user.setEmail(email);
        user.setPhone(phone);
        user.setBirthday(birthday);
        user.setFavcategory(favcategory);
        user.setBanneropt(banneropt);


        boolean result = userService.update(user);
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
        boolean isHas = userService.checkUsername(username);
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


    private String username;
    private String password;
    private String password2;
    private String address;
    private String email;
    private String phone;
    private String name;
    private Timestamp birthday;
    private String favcategory;
    private int banneropt;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getFavcategory() {
        return favcategory;
    }

    public void setFavcategory(String favcategory) {
        this.favcategory = favcategory;
    }

    public int getBanneropt() {
        return banneropt;
    }

    public void setBanneropt(int banneropt) {
        this.banneropt = banneropt;
    }
}
