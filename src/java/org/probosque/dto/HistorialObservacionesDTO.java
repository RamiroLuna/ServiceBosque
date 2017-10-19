package org.probosque.dto;
public class HistorialObservacionesDTO {

    public HistorialObservacionesDTO() {
    }
private String fecha;
private String user;
private String observacion;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }



    
}
