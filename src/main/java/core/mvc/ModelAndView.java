package core.mvc;

import core.view.JspView;
import core.view.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jojoldu@gmail.com on 2016-07-18.
 */
public class ModelAndView {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private Map<String, Object> model;
    private View view;

    public ModelAndView(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        this.model = new LinkedHashMap<>();
    }

    public ModelAndView(HttpServletRequest req, HttpServletResponse resp, View view) {
        this.req = req;
        this.resp = resp;
        this.model = new LinkedHashMap<>();
        this.view = view;
    }

    public ModelAndView(HttpServletRequest req, HttpServletResponse resp, String viewName) {
        this.req = req;
        this.resp = resp;
        this.model = new LinkedHashMap<>();
        this.view = new JspView(viewName);
    }

    public void addModelData(String key, Object data){
        this.model.put(key, data);
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

    public void resolve(){
        this.view.resolve(req, resp, model);
    }

}
