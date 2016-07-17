package next.dao;

import java.util.ArrayList;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.orm.Criteria;
import core.orm.Jpa;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao {
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	public int insert(User user) {
		return Jpa.save(user);
	}

	public int update(User user) {
		return Jpa.update(user);
	}

	public User findByUserId(String userId) {
		return Jpa.find(User.class, new Criteria().add("userId", userId));
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return JdbcTemplate.queryForList("SELECT userId, password, name, email FROM USERS",
				(pstmt) -> pstmt,
				(rs) -> {
					List<User> list = new ArrayList<>();
					while (rs.next()) {
						list.add(new User(
								rs.getString("userId"),
								rs.getString("password"),
								rs.getString("name"),
								rs.getString("email")));
					}
					return list;
				}
		);
	}


/*	public User findByUserId(String userId) {
		return JdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?",
				(pstmt) -> {
					pstmt.setString(1, userId);
					return pstmt;
				},
				(rs) -> {
					User user = null;
					if (rs.next()) {
						user = new User(
								rs.getString("userId"),
								rs.getString("password"),
								rs.getString("name"),
								rs.getString("email"));
					}
					return user;
				}
		);
	}*/

}
