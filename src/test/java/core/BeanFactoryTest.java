package core;

import core.config.BeanFactory;
import org.junit.Test;

/**
 * Created by jojoldu@zuminternet.com on 2016-07-12.
 */
public class BeanFactoryTest {

    @Test
    public void test_init() throws Exception{
        BeanFactory.init();
        BeanFactory.get("/users/create");
    }
}
