package core.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by jojoldu@gmail.com on 2016-07-18.
 */
public class JsonView implements View{
    private static final Logger logger = LoggerFactory.getLogger(JsonView.class);

    @Override
    public void resolve(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=UTF-8");
        try{
            PrintWriter out = resp.getWriter();
            out.print(mapper.writeValueAsString(model));
        }catch (Exception e){
            logger.error("Json View resolve error", e);
        }
    }
}
