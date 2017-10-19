package org.probosque.model;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class PDFrow {

    private ArrayList<Object> values;
    
    public PDFrow(){
        values = new ArrayList<>();
    }
    
    public void addValue(String value){
        values.add(value);
    }

    public ArrayList<Object> getValues() {
        return values;
    }

    public void setValues(ArrayList<Object> values) {
        this.values = values;
    }
}