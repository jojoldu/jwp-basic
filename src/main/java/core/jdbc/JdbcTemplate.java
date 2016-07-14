package core.jdbc;

import next.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jojoldu@gmail.com on 2016-07-14.
 */
public class JdbcTemplate {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    public static Object queryForObject(String sql, PreparedGenerator preparedGenerator, RowMapper rowMapper) throws SQLException{
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = preparedGenerator.map(pstmt).executeQuery()){

            return rowMapper.execute(rs);
        }catch(SQLException se){
            logger.error("execute Exception : ", se);
            throw new SQLException();
        }
    }

}
