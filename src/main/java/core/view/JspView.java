package core.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by jojoldu@gmail.com on 2016-07-18.
 */
public class JspView implements View{
    private static final Logger logger = LoggerFactory.getLogger(JspView.class);

    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

    private String name;

    public JspView(String name) {
        this.name = name;
    }

    @Override
    public void resolve(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) {
        try{
            if (name.startsWith(DEFAULT_REDIRECT_PREFIX)) {
                resp.sendRedirect(name.substring(DEFAULT_REDIRECT_PREFIX.length()));
                return;
            }

            RequestDispatcher rd = req.getRequestDispatcher(name);
            rd.forward(req, resp);
        }catch (Exception e){
            logger.error("Jsp view resolve error", e);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
