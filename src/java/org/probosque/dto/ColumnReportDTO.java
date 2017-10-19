package org.probosque.dto;

import java.util.List;

public class ColumnReportDTO {
public String type;
public String label;
public String datatype;
public String name;
public String listname;

  

public ColumnReportDTO() {
        
    }

    public ColumnReportDTO(String type, String label, String datatype, String name, String listname) {
        this.type = type;
        this.label = label;
        this.datatype = datatype;
        this.name = name;
        this.listname = listname;
    }
public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

      public String getListname() {
        return listname;
    }

    public void setListname(String listname) {
        this.listname = listname;
    }
}
