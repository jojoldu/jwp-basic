package next.controller;

import core.annotations.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jojoldu@gmail.com on 2016-07-11.
 */
@RequestMapping(value = "/users/form")
public class CreateUserFormController implements Controller {

    @Override
    public String run(HttpServletRequest req, HttpServletResponse res) {
        return "/user/form.jsp";
    }
}
