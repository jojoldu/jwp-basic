package next.controller.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.controller.UserSessionUtils;
import next.dao.UserDao;
import next.model.User;

@WebServlet(value= {"/users/login", "/users/loginForm"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("/user/login.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        
        User user = null;
        UserDao userDao = new UserDao();
    	try {
			user = userDao.findByUserId(userId);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
        
        if (user == null) {
           req.setAttribute("loginFailed", true);
           forward("/user/login.jsp", req, resp);
        }
        if (user.matchPassword(password)) {
            HttpSession session = req.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
            resp.sendRedirect("/");
        } else {
            req.setAttribute("loginFailed", true);
            forward("/user/login.jsp", req, resp);
        }
    }

    private void forward(String forwardUrl, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(forwardUrl);
        rd.forward(req, resp);
    }
}
