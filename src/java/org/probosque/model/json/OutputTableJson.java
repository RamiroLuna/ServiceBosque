package org.probosque.model.json;

public class OutputTableJson {
    
    private Object response;
    private Object data;
    private Object pageinfo;
    private Object columns;
    
    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(Object pageinfo) {
        this.pageinfo = pageinfo;
    }

    public Object getColumns() {
        return columns;
    }

    public void setColumns(Object columns) {
        this.columns = columns;
    }
}