package org.probosque.dto;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author admin
 */
public class CatalogoStringDTO {
    @SerializedName("value") private String id;
    @SerializedName("label") private String descripcion;

    public CatalogoStringDTO(){
        
    }
    
    public CatalogoStringDTO(String id, String descripcion){
        this.id = id;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
