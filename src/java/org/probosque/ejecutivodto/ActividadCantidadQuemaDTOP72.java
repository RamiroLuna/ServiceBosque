package org.probosque.ejecutivodto;

public class ActividadCantidadQuemaDTOP72 {

    public ActividadCantidadQuemaDTOP72() {
    }

    public ActividadCantidadQuemaDTOP72(String actividad_realizada, String cantidad) {
        this.actividad_realizada = actividad_realizada;
        this.cantidad = cantidad;
    }
public String actividad_realizada;
public String cantidad;

    public String getActividad_realizada() {
        return actividad_realizada;
    }

    public void setActividad_realizada(String actividad_realizada) {
        this.actividad_realizada = actividad_realizada;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
}
