package org.probosque.dto;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ReporteDTO {

    public String name;
    public String type;
    public String datatype;
    public String label;

   
    
    public List<String> Info = new ArrayList();

  public ReporteDTO(String name,String Label, String type,String datatype,List<String> repo  )
     {
         this.name=name;
         this.datatype=datatype;
         this.type=type;
         this.Info=repo;
         this.label=Label;
     }

 



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public List<String> getInfo() {
        return Info;
    }

    public void setInfo(List<String> Info) {
        this.Info = Info;
    }
 public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }           
}
