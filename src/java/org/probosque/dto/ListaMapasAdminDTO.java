package org.probosque.dto;

public class ListaMapasAdminDTO {
    
    public ListaMapasAdminDTO(){
    }

    public String getMapa_id() {
        return mapa_id;
    }

    public void setMapa_id(String mapa_id) {
        this.mapa_id = mapa_id;
    }

    public String getMapa_descripcion() {
        return mapa_descripcion;
    }

    public void setMapa_descripcion(String mapa_descripcion) {
        this.mapa_descripcion = mapa_descripcion;
    }

    public ListaMapasAdminDTO(String mapa_id, String mapa_descripcion) {
        this.mapa_id = mapa_id;
        this.mapa_descripcion = mapa_descripcion;
    }
    
    String mapa_id;
    String mapa_descripcion;
    
}