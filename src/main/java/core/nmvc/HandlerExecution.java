package core.nmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import core.mvc.ModelAndView;

import java.lang.reflect.Method;

public class HandlerExecution {

	private Class clazz;
	private Method method;

	public HandlerExecution(Class clazz, Method method) {
		this.clazz = clazz;
		this.method = method;
	}

	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Object object = this.clazz.newInstance();
		return (ModelAndView) method.invoke(object, request, response);
	}
}
