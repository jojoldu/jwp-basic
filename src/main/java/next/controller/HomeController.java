package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.annotations.RequestMapping;
import core.db.DataBase;

@RequestMapping(value = "/")
public class HomeController implements Controller {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        req.setAttribute("users", DataBase.findAll());
        return "index.jsp";
    }
}
