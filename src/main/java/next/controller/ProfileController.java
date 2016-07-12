package next.controller;

import core.annotations.RequestMapping;
import core.db.DataBase;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/users/profile")
@RequestMapping(value = "/users/profile")
public class ProfileController implements Controller {
	private static final long serialVersionUID = 1L;

//	@Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String userId = req.getParameter("userId");
//        User user = DataBase.findUserById(userId);
//        if (user == null) {
//            throw new NullPointerException("사용자를 찾을 수 없습니다.");
//        }
//        req.setAttribute("user", user);
//        RequestDispatcher rd = req.getRequestDispatcher("/user/profile.jsp");
//        rd.forward(req, resp);
//    }

    @Override
    public String run(HttpServletRequest req, HttpServletResponse res) {
        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);
        if (user == null) {
            throw new NullPointerException("사용자를 찾을 수 없습니다.");
        }
        req.setAttribute("user", user);
        return "/user/profile.jsp";
    }
}
