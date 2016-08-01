package core.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.annotation.HandlerMapping;
import core.nmvc.AnnotationHandlerMapping;
import core.nmvc.HandlerExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "dispatcher", urlPatterns = {"", "/"}, loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	private LegacyRequestMapping rm;
	private List<HandlerMapping> handlerMappings;

	@Override
	public void init() throws ServletException {
		rm = new LegacyRequestMapping();
		rm.initMapping();

		handlerMappings = new ArrayList<>();
		handlerMappings.add(new LegacyRequestMapping());
		handlerMappings.add(new AnnotationHandlerMapping());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUri = req.getRequestURI();
		logger.debug("Method : {}, Request URI : {}", req.getMethod(), requestUri);

		ModelAndView mav = null;
		try {
			for(HandlerMapping handlerMapping : handlerMappings){
				mav = getModelAndView(handlerMapping, req, resp);
			}

			if(mav != null){
				View view = mav.getView();
				view.render(mav.getModel(), req, resp);
			}

		} catch (Throwable e) {
			logger.error("Exception : {}", e);
			throw new ServletException(e.getMessage());
		}
	}

	private ModelAndView getModelAndView(HandlerMapping handlerMapping, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Object handler = handlerMapping.getHandler(request);
		ModelAndView mav;
		if(handler instanceof Controller){
			mav = ((Controller) handler).execute(request, response);
		}else if(handler instanceof HandlerExecution){
			mav = ((HandlerExecution) handler).handle(request, response);
		}else{
			throw new Exception();
		}

		return mav;
	}
}
