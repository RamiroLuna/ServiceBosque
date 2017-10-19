package org.probosque.dto;


public class ListaIndicadorAdminDTO {
    
    String indicador_nombre;
    String indicador_id;
    String mapa_id;
    String indicador_x;
    String indicador_y;

    /*public ListaIndicadorAdminDTO(String indicador_nombre, String mapa_id) {
        this.indicador_nombre = indicador_nombre;
        this.mapa_id = mapa_id;
    }*/
    
    public ListaIndicadorAdminDTO(String indicador_nombre, String mapa_id, String indicador_x, String indicador_y, String indicador_id){
        this.indicador_nombre = indicador_nombre;
        this.indicador_id = indicador_id;
        this.mapa_id = mapa_id;
        this.indicador_x = indicador_x;
        this.indicador_y = indicador_y;
    }
    
     public String getIndicador_id() {
        return indicador_id;
    }

    public void setIndicador_id(String indicador_id) {
        this.indicador_id = indicador_id;
    }
    
    
    public String getIndicador_nombre() {
        return indicador_nombre;
    }

    public void setIndicador_nombre(String indicador_nombre) {
        this.indicador_nombre = indicador_nombre;
    }

    public ListaIndicadorAdminDTO() {
    }

    public String getIndicador_x() {
        return indicador_x;
    }

    public void setIndicador_x(String indicador_x) {
        this.indicador_x = indicador_x;
    }

    public String getIndicador_y() {
        return indicador_y;
    }

    public void setIndicador_y(String indicador_y) {
        this.indicador_y = indicador_y;
    }

    public String getMapa_id() {
        return mapa_id;
    }

    public void setMapa_id(String mapa_id) {
        this.mapa_id = mapa_id;
    }
    
}
