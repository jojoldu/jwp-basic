package core;

import core.config.BeanFactory;
import next.controller.Controller;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
/**
 * Created by jojoldu@zuminternet.com on 2016-07-12.
 */
public class BeanFactoryTest {

    @Before
    public void setup() throws Exception{
        BeanFactory.init();
    }

    @Test
    public void test_init() throws Exception{
        assertThat(BeanFactory.get("/users/create").getClass().getName(), is("next.controller.CreateUserController"));
    }

    @Test
    public void test_get() throws Exception {
        Controller controller = (Controller) BeanFactory.get("/users/create");
        String viewName = controller.execute(new MockHttpServletRequest(), new MockHttpServletResponse());
        assertThat(viewName, is("redirect:/"));
    }
}
