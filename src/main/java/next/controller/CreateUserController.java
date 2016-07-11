package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.annotations.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

//@WebServlet(value= {"/users/create", "/users/form"})
@RequestMapping(value = "/users/create")
public class CreateUserController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		User user = new User(
				req.getParameter("userId"),
				req.getParameter("password"),
				req.getParameter("name"),
				req.getParameter("email"));
		log.debug("User : {}", user);

		DataBase.addUser(user);
		return "redirect:/";
	}
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	    RequestDispatcher rd = req.getRequestDispatcher("/user/form.jsp");
//        rd.forward(req, resp);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User user = new User(
//                req.getParameter("userId"),
//                req.getParameter("password"),
//                req.getParameter("name"),
//                req.getParameter("email"));
//        log.debug("User : {}", user);
//
//        DataBase.addUser(user);
//
//        resp.sendRedirect("/");
//	}
}
