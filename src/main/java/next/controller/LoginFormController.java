package next.controller;

import core.annotations.RequestMapping;
import core.db.DataBase;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet(value= {"/users/login", "/users/loginForm"})
@RequestMapping(value = "/users/loginForm")
public class LoginFormController implements Controller {
    private static final long serialVersionUID = 1L;

    @Override
    public String run(HttpServletRequest req, HttpServletResponse res) {
        return "/user/login.jsp";
    }

}
