package next.model;

import core.ref.Junit3Test;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jojoldu@gmail.com on 2016-07-28.
 */
public class StudentTest {
    private Student student;

    @Before
    public void setup() throws Exception {
        student = new Student();
    }

    private void setField(String name, Object value) throws  Exception{
        Field field = student.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(student, value);
    }

    @Test
    public void run() throws Exception {
        this.setField("name", "동욱");
        this.setField("age", 30);
        assertThat(student.getName(), is("동욱"));
        assertThat(student.getAge(), is(30));
    }
}
