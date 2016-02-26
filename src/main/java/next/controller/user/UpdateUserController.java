package next.controller.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.controller.UserSessionUtils;
import next.dao.UserDao;
import next.model.User;

@WebServlet(value = { "/users/update", "/users/updateForm" })
public class UpdateUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        UserDao userDao = new UserDao();
    	try {
			user = userDao.findByUserId(req.getParameter("userId"));
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
        if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
        	throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }
        req.setAttribute("user", user);
        RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	User user = null;
        UserDao userDao = new UserDao();
    	try {
			user = userDao.findByUserId(req.getParameter("userId"));
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
        if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
        	throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }
        
        User updateUser = new User(
                req.getParameter("userId"), 
                req.getParameter("password"), 
                req.getParameter("name"),
                req.getParameter("email"));
        log.debug("Update User : {}", updateUser);
        user.update(updateUser);
        resp.sendRedirect("/");
    }
}
