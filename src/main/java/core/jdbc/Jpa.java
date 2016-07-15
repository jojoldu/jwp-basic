package core.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jojoldu@gmail.com on 2016-07-15.
 */
public class Jpa {
    private static final Logger logger = LoggerFactory.getLogger(JdbcTemplate.class);

    public static <T>int save(T t){
        Query query = new Query<>(t);
        try{
            return JdbcTemplate.insert(QueryGenerator.getSave(query.getSaveData()));
        }catch (Exception e){
            logger.error("Jpa save error", e);
            return 0;
        }

    }
}
