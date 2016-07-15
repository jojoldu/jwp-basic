package core.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2016-07-14.
 */
public class JdbcTemplate {
    private static final Logger logger = LoggerFactory.getLogger(JdbcTemplate.class);

    public static int saveOrUpdate(String sql) throws SQLException{
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){

            return pstmt.executeUpdate();
        }catch(SQLException se){
            logger.error("saveOrUpdate Exception : ", se);
            throw new SQLException();
        }
    }

    public static <T> T queryForObject(String sql, PreparedGenerator preparedGenerator, RowMapper rowMapper, Class<T> elementType) throws SQLException{
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = preparedGenerator.map(pstmt).executeQuery()){

            return elementType.cast(rowMapper.execute(rs));
        }catch(SQLException se){
            logger.error("execute Exception : ", se);
            throw new SQLException();
        }
    }

    public static <T> List<T> queryForList(String sql, PreparedGenerator preparedGenerator, RowMapper rowMapper, Class<List<T>> elementType) throws SQLException{
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = preparedGenerator.map(pstmt).executeQuery()){

            return elementType.cast(rowMapper.execute(rs));
        }catch(SQLException se){
            logger.error("execute Exception : ", se);
            throw new SQLException();
        }
    }

}
