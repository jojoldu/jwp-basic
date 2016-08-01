package core.nmvc;

import next.controller.HomeController;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * ControllerScanner Tester.
 *
 * @author <Authors name>
 * @since <pre>8�� 1, 2016</pre>
 * @version 1.0
 */
public class ControllerScannerTest {

    private ControllerScanner scanner;

    @Before
    public void before() throws Exception {
        scanner = new ControllerScanner();
    }

    @After
    public void after() throws Exception {
    }


    /**
     * Method: scan(String packageName)
     */
    @Test
    public void testScan() throws Exception {
        scanner.scan("core.nmvc");
        assertThat(scanner.get(MyController.class).getClass().getName(), is("core.nmvc.MyController"));
    }
}
