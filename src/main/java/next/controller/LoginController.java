package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.annotations.RequestMapping;
import core.db.DataBase;
import next.model.User;

//@WebServlet(value= {"/users/login", "/users/loginForm"})
@RequestMapping(value = "/users/login")
public class LoginController implements Controller {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User user = DataBase.findUserById(userId);
        if (user == null) {
           req.setAttribute("loginFailed", true);
           return "/user/login.jsp";
        }
        if (user.matchPassword(password)) {
            HttpSession session = req.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
            return "redirect:/";
        } else {
            req.setAttribute("loginFailed", true);
            return "/user/login.jsp";
        }
    }

    //    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        forward("/user/login.jsp", req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String userId = req.getParameter("userId");
//        String password = req.getParameter("password");
//        User user = DataBase.findUserById(userId);
//        if (user == null) {
//           req.setAttribute("loginFailed", true);
//           forward("/user/login.jsp", req, resp);
//        }
//        if (user.matchPassword(password)) {
//            HttpSession session = req.getSession();
//            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
//            resp.sendRedirect("/");
//        } else {
//            req.setAttribute("loginFailed", true);
//            forward("/user/login.jsp", req, resp);
//        }
//    }
//
//    private void forward(String forwardUrl, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher rd = req.getRequestDispatcher(forwardUrl);
//        rd.forward(req, resp);
//    }
}
