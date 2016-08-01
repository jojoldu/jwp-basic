package core.nmvc;

import com.google.common.collect.Maps;
import core.annotation.RequestMapping;
import core.annotation.RequestMethod;
import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping {
	private static final Logger logger = LoggerFactory.getLogger(AnnotationHandlerMapping.class);
	private Object[] basePackage;
	
	private Map<HandlerKey, HandlerExecution> handlerExecutions = Maps.newHashMap();
	private ControllerScanner scanner = new ControllerScanner();

	public AnnotationHandlerMapping(Object... basePackage) {
		this.basePackage = basePackage;
	}
	
	public void initialize() {
		for(Object packgeName : this.basePackage){
			scanner.scan((String) packgeName);
		}

		for(Class clazz : scanner.getFactory().keySet()){
			Set<Method> methods = ReflectionUtils.getAllMethods(clazz, ReflectionUtils.withAnnotation(RequestMapping.class));
			for(Method method : methods){
				handlerExecutions.put(createHandlerKey(method.getDeclaredAnnotation(RequestMapping.class)), new HandlerExecution());
			}
		}
	}
	private HandlerKey createHandlerKey(RequestMapping requestMapping){
		return new HandlerKey(requestMapping.value(), requestMapping.method());
	}

	public HandlerExecution getHandler(HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		RequestMethod rm = RequestMethod.valueOf(request.getMethod().toUpperCase());
		return handlerExecutions.get(new HandlerKey(requestUri, rm));
	}

	public Map<HandlerKey, HandlerExecution> getHandlerExecutions() {
		return handlerExecutions;
	}
}
