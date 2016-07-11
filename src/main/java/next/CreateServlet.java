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
@WebServlet("/user/create")
public class CreateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database.addUser(new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"), req.getParameter("email")));
        resp.sendRedirect("/index.html");
    }
}
