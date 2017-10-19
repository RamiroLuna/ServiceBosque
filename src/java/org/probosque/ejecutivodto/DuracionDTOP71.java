package org.probosque.ejecutivodto;

public class DuracionDTOP71 {

    public DuracionDTOP71(String duracion, String numincendio, String observacion) {
        this.duracion = duracion;
        this.numincendio = numincendio;
        this.observacion = observacion;
    }

    public DuracionDTOP71() {
    }

   
public String duracion;  
public String numincendio;
public String observacion;

    public String getNumincendio() {
        return numincendio;
    }

    public void setNumincendio(String numincendio) {
        this.numincendio = numincendio;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
}
