package core.orm;

/**
 * Created by jojoldu@zuminternet.com on 2016-07-15.
 */
public class Column {
    private String key;
    private String value;

    public Column(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
