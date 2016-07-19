package core.orm;

/**
 * Created by jojoldu@zuminternet.com on 2016-07-18.
 */
public class Conditions {

    private static String result(String column, String value, String sign){
        return column+sign+"'"+value+"'";
    }

    public static String eq(String column, String value){
        return result(column, value, "=");
    }

    public static String neq(String column, String value){
        return result(column, value, "!=");
    }

    public static String gt(String column, String value){
        return result(column, value, ">");
    }

    public static String ge(String column, String value){
        return result(column, value, ">=");
    }

    public static String lt(String column, String value){
        return result(column, value, "<");
    }

    public static String le(String column, String value){
        return result(column, value, "<=");
    }
}
