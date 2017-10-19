package org.probosque.ejecutivodto;
public class VolumenFechaEspecieDTOP12 {

    public VolumenFechaEspecieDTOP12() {
    }

    public VolumenFechaEspecieDTOP12(String fecha, String volumen, String especie) {
        this.fecha = fecha;
        this.volumen = volumen;
        this.especie = especie;
    }
public String fecha;
public String volumen;
public String especie;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

}
