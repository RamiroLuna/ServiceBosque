package org.probosque.dto;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class OrdenamientoDTO {

    
public int id;   
public String ordenamiento;
public String name;
@SerializedName("arr_ordenamiento")
public ArrayList<OrdenamientoDTO> ordenar;
public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrdenamiento() {
        return ordenamiento;
    }

    public void setOrdenamiento(String ordenamiento) {
        this.ordenamiento = ordenamiento;
    }

    public ArrayList<OrdenamientoDTO> getOrdenar() {
        return ordenar;
    }

    public void setOrdenar(ArrayList<OrdenamientoDTO> ordenar) {
        this.ordenar = ordenar;
    }
    
}
        