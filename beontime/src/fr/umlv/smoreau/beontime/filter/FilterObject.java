package fr.umlv.smoreau.beontime.filter;

/**
 * @author BeOnTime
 */
public class FilterObject {
    public static final String EQUAL = "=";
    public static final String LESS = "<";
    public static final String GREAT = ">";
    public static final String LESS_EQUAL = "<=";
    public static final String GREAT_EQUAL = ">=";

    private String tableName;
    private String operator;
    
    public FilterObject(String tableName, String operator) {
        this.tableName = tableName;
        this.operator = operator;
    }
    
    public FilterObject(String tableName) {
        this(tableName, EQUAL);
    }
    
    public String getOperator() {
        return operator;
    }
    public String getTableName() {
        return tableName;
    }
}
