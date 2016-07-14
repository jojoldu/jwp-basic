package core.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by jojoldu@gmail.com on 2016-07-14.
 */
public interface PreparedGenerator {
    PreparedStatement map(PreparedStatement pstmt) throws SQLException;
}
