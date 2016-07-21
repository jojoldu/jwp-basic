package core.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by jojoldu@gmail.com on 2016-07-18.
 */
public interface View {
    void resolve(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model);
}
