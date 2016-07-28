package core.nmvc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;

import core.annotation.Controller;
import core.annotation.RequestMethod;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnotationHandlerMapping {
	private static final Logger logger = LoggerFactory.getLogger(AnnotationHandlerMapping.class);
	private Object[] basePackage;
	
	private Map<HandlerKey, HandlerExecution> handlerExecutions = Maps.newHashMap();

	public AnnotationHandlerMapping(Object... basePackage) {
		this.basePackage = basePackage;
	}
	
	public void initialize() {
		for(Object packgeName : this.basePackage){
			Reflections reflections = new Reflections((String)packgeName);
			Set<Constructor> constructors = reflections.getConstructorsAnnotatedWith(Controller.class);
			for(Constructor constructor : constructors){
				try {
					Object obj = constructor.newInstance();
					Method[] methods = obj.getClass().getDeclaredMethods();
					for(Method method : methods){
						//@RequestMapping 선언된것인지 확인후 handlerExecutions에 등록
					}
				} catch (Exception e) {
					logger.error("AnnotationHandlerMapping.initialize()", e);
				}
			}
		}
	}
	
	public HandlerExecution getHandler(HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		RequestMethod rm = RequestMethod.valueOf(request.getMethod().toUpperCase());
		return handlerExecutions.get(new HandlerKey(requestUri, rm));
	}
}
