package com.agile.asyoumean.web;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.agile.asyoumean.dao.UserDAO;
import com.agile.asyoumean.util.Util;

@ViewScoped
@ManagedBean(name = "loginBean")
public class LoginBean extends Util {

    private static final long serialVersionUID = 1L;
    private int password;
    private String message, uname;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String loginProject() {
        boolean result = UserDAO.login(uname, password);
        if (result) {
            // get Http Session and store username
            HttpSession session = Util.getSession();
            session.setAttribute("username", uname);

            return "home";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Login!", "Please Try Again!"));
            return "login";
        }
    }

    public String logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        return "login";
    }

    public LoginBean() {
        // TODO Auto-generated constructor stub
    }

}
