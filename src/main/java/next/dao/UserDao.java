package next.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.Jpa;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao {
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	public int insert(User user) throws SQLException {
		return Jpa.save(user);
	}

	public int update(User user) throws SQLException {
		return Jpa.update(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() throws SQLException {
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
				},
				(Class<List<User>>)(Object)List.class
		);
	}


	public User findByUserId(String userId) throws SQLException {
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
				},
				User.class
		);
	}

}
