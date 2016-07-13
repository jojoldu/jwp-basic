package core;

import core.config.BeanFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
/**
 * Created by jojoldu@zuminternet.com on 2016-07-12.
 */
public class BeanFactoryTest {

    @Test
    public void test_init() throws Exception{
        BeanFactory.init();
        assertThat(BeanFactory.get("/users/create").getClass().getName(), is("next.controller.CreateUserController"));
    }
}
