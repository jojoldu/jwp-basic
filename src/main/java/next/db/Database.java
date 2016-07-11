package next.db;

import next.domain.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jojoldu@gmail.com on 2016-07-07.
 */
public class Database {
    public static Map<String, User> Users = new HashMap<>();

    public static void addUser(User user){
        Users.put(user.getUserId(), user);
    }

    public static User getUser(String userId){
        return Users.get(userId);
    }
}
