package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jojoldu@gmail.com on 2016-07-11.
 */
public interface Controller {
    String execute(HttpServletRequest req, HttpServletResponse res);
}
