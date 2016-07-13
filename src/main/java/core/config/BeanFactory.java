package core.config;

import core.annotations.RequestMapping;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by jojoldu@gmail.com on 2016-07-11.
 */
public class BeanFactory {
    private static final Logger log = LoggerFactory.getLogger(BeanFactory.class);

    public static Map<String, Object> beanTable = new HashMap<>();

    public static Object get(String key){
        return beanTable.get(key);
    }

    public static void put(String key, Object clazz){
        beanTable.put(key, clazz);
    }

    public static void init() throws Exception{
        Reflections reflector = new Reflections("");
        Set<Class<?>> classes = reflector.getTypesAnnotatedWith(RequestMapping.class);
        String key;
        for(Class<?> clazz : classes){
            key = clazz.getAnnotation(RequestMapping.class).value();
            put(key, clazz.newInstance());
        }
    }
}
