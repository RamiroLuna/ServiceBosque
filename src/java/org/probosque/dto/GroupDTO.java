package org.probosque.dto;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class GroupDTO {
public String label;
public String name;
public String tipo;
public String operador;
@SerializedName("agrupador")
public ArrayList<GroupDTO> group;

public List<TotalGroupDTO> paramsTotal;


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public ArrayList<GroupDTO> getGroup() {
        return group;
    }

    public void setGroup(ArrayList<GroupDTO> group) {
        this.group = group;
    }
 
    public List<TotalGroupDTO> getparamsTotal() {
        return paramsTotal;
    }

    public void setparamsTotal(List<TotalGroupDTO> paramsTotal) {
        this.paramsTotal = paramsTotal;
    }

    
}




