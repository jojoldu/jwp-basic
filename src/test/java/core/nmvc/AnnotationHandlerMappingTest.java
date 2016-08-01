package core.nmvc;

import static org.junit.Assert.*;

import core.annotation.RequestMethod;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import core.mvc.ModelAndView;

import java.util.Arrays;
import java.util.List;

public class AnnotationHandlerMappingTest {
    private AnnotationHandlerMapping handlerMapping;
    private MockHttpServletResponse response;
    
    @Before
    public void setup() {
        handlerMapping = new AnnotationHandlerMapping("core.nmvc");
        handlerMapping.initialize();
        
        response = new MockHttpServletResponse();
    }

    @Test
    public void init() throws Exception {
        HandlerKey[] array = {
                new HandlerKey("/users", RequestMethod.GET),
                new HandlerKey("/users", RequestMethod.POST),
                new HandlerKey("/users/show", RequestMethod.GET)};

        List<HandlerKey> handlerKeys = Arrays.asList(array);

        for(HandlerKey handlerKey : handlerMapping.getHandlerExecutions().keySet()){
            assertTrue(handlerKeys.contains(handlerKey));
        }

    }

    @Test
    public void list() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/users");
        HandlerExecution execution = handlerMapping.getHandler(request);
        ModelAndView mav = execution.handle(request, response);
        mav.getView().render(mav.getModel(), request, response);
        assertEquals("/users/list.jsp", response.getForwardedUrl());
    }

    @Test
    public void show() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/users/show");
        HandlerExecution execution = handlerMapping.getHandler(request);
        ModelAndView mav = execution.handle(request, response);
        mav.getView().render(mav.getModel(), request, response);
        assertEquals("/users/show.jsp", response.getForwardedUrl());
    }
    
    @Test
    public void create() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/users");
        HandlerExecution execution = handlerMapping.getHandler(request);
        ModelAndView mav = execution.handle(request, response);
        mav.getView().render(mav.getModel(), request, response);
        assertEquals("/users", response.getRedirectedUrl());
    }
}