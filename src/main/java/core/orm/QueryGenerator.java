package core.orm;


import java.util.Map;

/**
 * Created by jojoldu@zuminternet.com on 2016-07-15.
 */
public class QueryGenerator {

    private static final String TEMPLATE_SAVE="INSERT INTO {table} ({columns}) VALUES ({parameters})";
    private static final String TEMPLATE_UPDATE="UPDATE {table} SET {parameters} where {condition}";
    private static final String TEMPLATE_FIND = "SELECT * FROM {table} {condition}";

    private static String template(String sql, Map<String, String> data){
        String result = sql;
        for(Map.Entry<String, String> entry : data.entrySet()){
            result = result.replace("{"+entry.getKey()+"}", entry.getValue());
        }
        return result;
    }

    public static String getSave(Map<String, String> data){
        return template(TEMPLATE_SAVE, data);
    }

    public static String getUpdateForObject(Map<String, String> data){
        return template(TEMPLATE_UPDATE, data);
    }

    public static String getFind(Map<String, String> data){
        return template(TEMPLATE_FIND, data);
    }
}
