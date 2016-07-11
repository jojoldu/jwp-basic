package next;

import next.db.Database;
import next.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jojoldu@gmail.com on 2016-07-07.
 */
@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = Database.getUser(req.getParameter("userId"));
        if(user!=null && user.getPassword().equals(req.getParameter("password"))){
            resp.sendRedirect("/user/profile.html");
        }else{
            resp.sendRedirect("/user/login_failed.html");
        }
    }
}
