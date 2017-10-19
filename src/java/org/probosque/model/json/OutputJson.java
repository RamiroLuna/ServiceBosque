package org.probosque.model.json;

import java.util.List;
import org.probosque.dto.Operation;
import org.probosque.dto.Request;
import org.probosque.dto.Summary;
import org.probosque.model.LabelValue;

public class OutputJson {
    
    private Object response;
    private Object data;
    private Object data1;
    private List<LabelValue> tabular;
    private List<Operation> operations;
    private List<Request> request;
    private List<Summary> summarys;
   private String cadena;    

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

    public List<LabelValue> getTabular() {
        return tabular;
    }

    public void setTabular(List<LabelValue> tabular) {
        this.tabular = tabular;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }
    
    public Object getData1() {
        return data1;
    }
    
    public void setData1(Object data, Object data1) {
        this.data = data;
        this.data1 = data1;
    }

    public List<Summary> getSummarys() {
        return summarys;
    }

    public void setSummarys(List<Summary> summarys) {
        this.summarys = summarys;
    }
    
    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }
}