package core.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jojoldu@gmail.com on 2016-07-14.
 */
public interface RowMapper {
    Object execute(ResultSet rs) throws SQLException;
}
