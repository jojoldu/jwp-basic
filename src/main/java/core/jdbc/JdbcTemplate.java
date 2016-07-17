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

    public static int saveOrUpdate(String sql){
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){

            return pstmt.executeUpdate();
        }catch(SQLException se){
            logger.error("saveOrUpdate Exception : ", se);
            throw new RuntimeException();
        }
    }

    public static <T> T queryForObject(String sql, PreparedGenerator preparedGenerator, RowMapper<T> rowMapper){
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = preparedGenerator.map(pstmt).executeQuery()){

            return rowMapper.execute(rs);
        }catch(SQLException se){
            logger.error("queryForObject Exception : ", se);
            throw new RuntimeException();
        }
    }

    public static <T> T queryForList(String sql, PreparedGenerator preparedGenerator, RowMapper<T> rowMapper){
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = preparedGenerator.map(pstmt).executeQuery()){

            return rowMapper.execute(rs);
        }catch(SQLException se){
            logger.error("queryForList Exception : ", se);
            throw new RuntimeException();
        }
    }

}
