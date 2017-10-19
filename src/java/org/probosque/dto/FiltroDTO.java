package org.probosque.dto;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

import java.util.List;

public class FiltroDTO {
    
 
public String condicion;
public String tipoFiltro;
public String campo;
public String list; 
public String type;
public String datatype;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
public String id;
 


@SerializedName("condiciones")
public ArrayList<FiltroDTO> filtro;


public FiltroDTO(ArrayList<FiltroDTO> filtro)
         {
           this.filtro=filtro;             
         }
    
    public String getTipoFiltro() {
        return tipoFiltro;
    }

    public void setTipoFiltro(String tipoFiltro) {
        this.tipoFiltro = tipoFiltro;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getName() {
        return campo;
    }

    public void setName(String campo) {
        this.campo = campo;
    }

    public ArrayList<FiltroDTO> getSelect() {
        return filtro;
    }
    
    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
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
    
}
