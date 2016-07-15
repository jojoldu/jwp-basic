package core.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by jojoldu@gmail.com on 2016-07-15.
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Table {
    String name() default "";
}
