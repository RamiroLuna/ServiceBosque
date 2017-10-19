/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dto;

/**
 *
 * @author Victor Porcayo Altamirano
 */
public class SubTableDTO {
    public String field;
    public String value;
    public String datatype;
    public String label;
    
    public SubTableDTO(String field, String value, String datatype, String label){
        this.field = field;
        this.value = value;
        this.datatype = datatype;
        this.label = value;
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getDatatype() {
        return datatype;
    }
    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
}
