package next.controller;

import core.annotations.RequestMapping;
import core.db.DataBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/users")
@RequestMapping(value = "/users")
public class ListUserController implements Controller {
	private static final long serialVersionUID = 1L;

//	@Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		if (!UserSessionUtils.isLogined(req.getSession())) {
//			resp.sendRedirect("/users/loginForm");
//			return;
//		}
//
//        req.setAttribute("users", DataBase.findAll());
//
//        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
//        rd.forward(req, resp);
//    }

	@Override
	public String run(HttpServletRequest req, HttpServletResponse res) {
		if (!UserSessionUtils.isLogined(req.getSession())) {
			return "/users/loginForm";
		}

		req.setAttribute("users", DataBase.findAll());
		return "/user/list.jsp";
	}
}
