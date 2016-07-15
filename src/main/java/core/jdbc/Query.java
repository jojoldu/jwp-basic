package core.jdbc;

import core.annotations.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jojoldu@zuminternet.com on 2016-07-15.
 */
public class Query<T> {
    private static final Logger logger = LoggerFactory.getLogger(Query.class);

    private String table;
    private List<Column> columns;

    public Query(T t) {
        this.table = t.getClass().getAnnotation(Table.class).name();
        this.columns = new ArrayList<>();
        Method methods[] = t.getClass().getDeclaredMethods();
        for(Method m : methods){
            core.annotations.Column column = m.getAnnotation(core.annotations.Column.class);
            if(column != null){
                try {
                    columns.add(new Column(column.name(), (String)m.invoke(t)));
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    public String getTable() {
        return table;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public Map<String, String> getSaveData(){
        Map<String, String> data = new LinkedHashMap<>();
        data.put("table", this.table);
        data.put("columns", this.columns.stream().map(column -> column.getKey()).collect(Collectors.joining(",")));
        data.put("parameters", this.columns.stream().map(column -> "\'"+column.getValue()+"\'").collect(Collectors.joining(",")));

        return data;
    }
}
