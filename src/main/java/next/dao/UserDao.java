package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import core.jdbc.PreparedGenerator;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao {
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	public void insert(User user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());

			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (con != null) {
				con.close();
			}
		}
	}

	public int update(User user) throws SQLException {
		return JdbcTemplate.update("UPDATE USERS SET password=?, name=?, email=? where userId=?",
				(pstmt)->{
					pstmt.setString(1, user.getPassword());
					pstmt.setString(2, user.getName());
					pstmt.setString(3, user.getEmail());
					pstmt.setString(4, user.getUserId());
					return pstmt;
				});
	}

	public List<User> findAll() throws SQLException {
		return (List<User>) JdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS",
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
				});
	}


	public User findByUserId(String userId) throws SQLException {
		return (User)JdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?",
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
	}

}
