package org.probosque.dto;

public class OrderDTO {
    private String field;
    private Object value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    public String getSQL(){
        StringBuilder sql = new StringBuilder();
        sql.append(" ORDER BY ").append(field);
        if(value.equals("1")){
            sql.append(" ASC");
        }
        else{
            sql.append(" DESC");
        }
        return sql.toString();
    }
}
