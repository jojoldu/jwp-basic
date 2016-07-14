package next.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import core.jdbc.ConnectionManager;
import next.model.User;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UserDaoTest {

    private UserDao userDao;
    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
        userDao = new UserDao();
    }

    @Test
    public void crud() throws Exception {
        User expected = new User("userId", "password", "name", "javajigi@email.com");
        UserDao userDao = new UserDao();
        userDao.insert(expected);
        User actual = userDao.findByUserId(expected.getUserId());
        assertEquals(expected, actual);

        expected.update(new User("userId", "password2", "name2", "sanjigi@email.com"));
        userDao.update(expected);
        actual = userDao.findByUserId(expected.getUserId());
        assertEquals(expected, actual);
    }
    
    @Test
    public void findAll() throws Exception {
        List<User> users = userDao.findAll();
        assertEquals(1, users.size());
    }

    @Test
    public void findByUserId() throws Exception {
        User user = userDao.findByUserId("admin");
        assertEquals(user.getEmail(), "admin@slipp.net");
    }

    @Test
    public void update() throws Exception{
        User user = userDao.findByUserId("admin");
        user.update(new User("admin", "password2", "name2", "sanjigi@email.com"));

        userDao.update(user);
        User updateUser = userDao.findByUserId("admin");
        assertThat(updateUser.getEmail(), is("sanjigi@email.com"));
    }

    @Test
    public void insert() throws Exception {
        userDao.insert(new User("jojoldu", "password2", "name2", "jojoldu@email.com"));
        User user = userDao.findByUserId("jojoldu");
        assertThat(user.getEmail(), is("jojoldu@email.com"));
    }
}