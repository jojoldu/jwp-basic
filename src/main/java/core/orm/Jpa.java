package core.orm;

import core.jdbc.JdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2016-07-15.
 */
public class Jpa {
    private static final Logger logger = LoggerFactory.getLogger(JdbcTemplate.class);

    public static <T>int save(T t){
        Query query = new Query<>(t);
        try{
            return JdbcTemplate.saveOrUpdate(QueryGenerator.getSave(query.getSaveData()));
        }catch (Exception e){
            logger.error("Jpa save error", e);
            return 0;
        }
    }

    public static <T> int update(T t){
        Query query = new Query<>(t);
        try{
            return JdbcTemplate.saveOrUpdate(QueryGenerator.getUpdateForObject(query.getUpdateData()));
        }catch (Exception e){
            logger.error("Jpa update error", e);
            return 0;
        }
    }

    public static <T>T find(Class<T> clazz, Criteria criteria){
        Query query = new Query<>(clazz);
        try{
            return JdbcTemplate.queryForObject();
        }catch (Exception e){
            logger.error("Jpa find error", e);
            return null;
        }
    }

    public static <T> List<T> findAll(Class<T> clazz, Criteria criteria){
        Query query = new Query<>(clazz);
        try{
            return null;
        }catch (Exception e){
            logger.error("Jpa find error", e);
            return null;
        }
    }
}
