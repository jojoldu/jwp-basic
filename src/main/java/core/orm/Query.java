package core.orm;

import core.annotations.Id;
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
    private Column id;

    public Query(T t) {
        this.table = t.getClass().getAnnotation(Table.class).name();
        this.columns = new ArrayList<>();
        Method methods[] = t.getClass().getDeclaredMethods();
        for(Method m : methods){
            core.annotations.Column column = m.getAnnotation(core.annotations.Column.class);
            if(column != null){
                try {
                    Column c = new Column(column.name(), (String)m.invoke(t));
                    columns.add(c);
                    if(m.getAnnotation(Id.class) != null){
                        this.id = c;
                    }
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

    public Column getId() {
        return id;
    }

    private Map<String, String> getDefaultData() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("table", this.table);
        return data;
    }

    public Map<String, String> getSaveData(){
        Map<String, String> data = getDefaultData();
        data.put("columns", this.columns.stream().map(column -> column.getKey()).collect(Collectors.joining(",")));
        data.put("parameters", this.columns.stream().map(column -> toSqlColumn(column.getValue())).collect(Collectors.joining(",")));

        return data;
    }

    public Map<String, String> getUpdateData(){
        Map<String, String> data = getDefaultData();
        data.put("parameters", this.columns
                .stream()
                .filter(column -> column.getValue() != null && !column.getValue().isEmpty())
                .map(column -> column.getKey()+"="+toSqlColumn(column.getValue()))
                .collect(Collectors.joining(",")));
        data.put("condition", this.id.getKey()+"="+toSqlColumn(this.id.getValue()));

        return data;
    }

    public Map<String, String> getFindData(Criteria criteria){
        Map<String, String> data = getDefaultData();

        return data;
    }

    private String toSqlColumn(String value){
        return "\'"+value+"\'";
    }
}
