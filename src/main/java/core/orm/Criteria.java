package core.orm;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jojoldu@gmail.com on 2016-07-16.
 */
public class Criteria {
    private Map<String, String> conditions = new LinkedHashMap<>();

    public Criteria() {
    }

    public Criteria add(String column, String value){
        this.conditions.put(column, value);
        return this;
    }

    public String getConditions() {
        return this.conditions
                .entrySet()
                .stream()
                .map(condition -> condition.getKey()+"='"+condition.getValue()+"'")
                .collect(Collectors.joining(" and "));
    }
}
