package core.orm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jojoldu@gmail.com on 2016-07-16.
 */
public class Criteria {
    //private Map<String, String> conditions = new LinkedHashMap<>();
    private List<String> conditions;

    public Criteria() {
        this.conditions = new ArrayList<>();
        this.conditions.add("where");
    }

    public Criteria add(String query){
        String salt="";
        if(this.conditions.size() > 1){
            salt = "and ";
        }
        conditions.add(salt+query);
        return this;
    }

    public Criteria or(String query){
        String salt="";
        if(this.conditions.size() > 1){
            salt = "or ";
        }
        conditions.add(salt+query);
        return this;
    }

    public String getConditions() {
        return this.conditions
                .stream()
                .collect(Collectors.joining(" "));
    }
}
