package core.annotation;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jojoldu@gmail.com on 2016-08-01.
 */
public interface HandlerMapping {
    Object getHandler(HttpServletRequest request);
}
