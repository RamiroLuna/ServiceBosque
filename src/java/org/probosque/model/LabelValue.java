package org.probosque.model;

public class LabelValue {
    private String label;
    private String value;
    private String aditional;

    public LabelValue(String label, String value){
        this.label = label;
        this.value = value;
    }
    
    public LabelValue(String label, String value, String aditional){
        this.label = label;
        this.value = value;
        this.aditional = aditional;
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAditional() {
        return aditional;
    }

    public void setAditional(String aditional) {
        this.aditional = aditional;
    }
}