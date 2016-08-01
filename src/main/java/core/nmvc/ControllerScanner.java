package core.nmvc;

import core.annotation.Controller;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by jojoldu@gmail.com on 2016-08-01.
 */
public class ControllerScanner {
    private static final Logger logger = LoggerFactory.getLogger(ControllerScanner.class);
    private Map<Class<?>, Object> factory;

    public ControllerScanner() {
        this.factory = new LinkedHashMap<>();
    }

    public void scan(String packageName){
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Controller.class);
        for(Class clazz : annotated){
            try {
                factory.put(clazz, clazz.newInstance());
            } catch (Exception e) {
                logger.error("ControllerScanner.scan()", e);
            }
        }
    }

    public Object get(Class<?> clazz){
        return this.factory.get(clazz);
    }

    public Map<Class<?>, Object> getFactory() {
        return factory;
    }

    public void setFactory(Map<Class<?>, Object> factory) {
        this.factory = factory;
    }
}
