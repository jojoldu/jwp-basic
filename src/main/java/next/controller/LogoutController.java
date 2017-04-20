package next.controller;

import core.annotations.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(value = "/users/logout")
public class LogoutController implements Controller {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession();
        session.removeAttribute(UserSessionUtils.USER_SESSION_KEY);
        return "redirect:/";
    }

}
