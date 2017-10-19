package org.probosque.dto;


import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class SelectDTO {

public String label;
public String type;
public String datatype;
public String name;

   
public String list;

@SerializedName("campos")
public ArrayList<SelectDTO> Select;

  /*public SelectDTO(String name, String type, String label, String datatype)
         {
           this.label=label;
           this.type=type;
           this.name=name;
           this.datatype=datatype;
         }
*/

public SelectDTO(ArrayList<SelectDTO> campos)
         {
           this.Select=campos;             
         }
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SelectDTO> getSelect() {
        return Select;
    }
     public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
    
}
