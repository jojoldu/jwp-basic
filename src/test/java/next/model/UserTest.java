package next.model;

import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserTest {
	public static User newUser(String userId) {
		return new User(userId, "password", "name", "test@sample.com");
	}

	@Test
	public void run() throws Exception {
		Class<User> clazz = User.class;
		Constructor[] constructors = clazz.getDeclaredConstructors();
		Object obj = constructors[0].newInstance("admin", "password", "name", "test@sample.com");
		if(obj instanceof User){
			User user = (User) obj;
			assertThat(user.getName(), is("name"));
		}
	}
}
