package org.probosque.dto;

import com.google.gson.annotations.SerializedName;

public class Summary {
    
    private String destiny;
    private String service;
    private String subtable;
    private String field;
    @SerializedName("typeDestiny") private String typedestiny;

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getSubtable() {
        return subtable;
    }

    public void setSubtable(String subtable) {
        this.subtable = subtable;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTypedestiny() {
        return typedestiny;
    }

    public void setTypedestiny(String typedestiny) {
        this.typedestiny = typedestiny;
    }
}