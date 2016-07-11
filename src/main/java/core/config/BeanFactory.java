package core.config;

import core.annotations.RequestMapping;
import next.controller.Controller;
import next.controller.CreateUserController;
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

    public static Map<String, Controller> db = new HashMap<>();

    public static Controller get(String key){
        return db.get(key);
    }

    public static void put(String key, Controller clazz){
        db.put(key, clazz);
    }

    public static void init(){
        Reflections reflections = new Reflections("next");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(RequestMapping.class);

        for (Class<?> clazz : annotated) {
            try{
                if(clazz.isInstance(Controller.class)){
                    put(clazz.getAnnotation(RequestMapping.class).value(), clazz.newInstance());
                }
            }catch (Exception e){
                log.error("bean factory init error : ", e);
            }

//            RequestMapping request = controller.getAnnotation(RequestMapping.class);
//            String url = request.value();
//            try {
//                put(url, controller.newInstance());
//            } catch (Exception e){
//                log.error("bean factory init error : ", e);
//            }
        }

    }
}
