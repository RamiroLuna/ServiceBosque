package org.probosque.ejecutivodto;

public class VolumenesGeneroP1DTO {

      public VolumenesGeneroP1DTO() {
    } 
    public VolumenesGeneroP1DTO(String areacorta, String genero, String volumen_existencias_reales_rodal, String volumen_posibilidad_total, String volumen_residual_rodal) {
        this.areacorta = areacorta;
        this.genero = genero;
        this.volumen_existencias_reales_rodal = volumen_existencias_reales_rodal;
        this.volumen_posibilidad_total = volumen_posibilidad_total;
        this.volumen_residual_rodal = volumen_residual_rodal;
    }
  
public String areacorta;
public String genero;
public String volumen_existencias_reales_rodal;
public String volumen_posibilidad_total;
public String volumen_residual_rodal;
    public String getAreacorta() {
        return areacorta;
    }

    public void setAreacorta(String areacorta) {
        this.areacorta = areacorta;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getVolumen_existencias_reales_rodal() {
        return volumen_existencias_reales_rodal;
    }

    public void setVolumen_existencias_reales_rodal(String volumen_existencias_reales_rodal) {
        this.volumen_existencias_reales_rodal = volumen_existencias_reales_rodal;
    }

    public String getVolumen_posibilidad_total() {
        return volumen_posibilidad_total;
    }

    public void setVolumen_posibilidad_total(String volumen_posibilidad_total) {
        this.volumen_posibilidad_total = volumen_posibilidad_total;
    }

    public String getVolumen_residual_rodal() {
        return volumen_residual_rodal;
    }

    public void setVolumen_residual_rodal(String volumen_residual_rodal) {
        this.volumen_residual_rodal = volumen_residual_rodal;
    }



    
}
