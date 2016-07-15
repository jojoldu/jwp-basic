package core.jdbc;


import java.util.Map;

/**
 * Created by jojoldu@zuminternet.com on 2016-07-15.
 */
public class QueryGenerator {

    private static final String TEMPLATE_SAVE="INSERT INTO {table} ({columns}) VALUES ({parameters})";

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
}
