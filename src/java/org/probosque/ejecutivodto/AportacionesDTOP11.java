package org.probosque.ejecutivodto;

public class AportacionesDTOP11 {

    public AportacionesDTOP11() {
    }

    public AportacionesDTOP11(String aportacion_gubernamental, String aportacion_productor, String empleos_generados) {
        this.aportacion_gubernamental = aportacion_gubernamental;
        this.aportacion_productor = aportacion_productor;
        this.empleos_generados=empleos_generados;
    }

public String aportacion_gubernamental;
public String aportacion_productor;
public String empleos_generados;

    public String getEmpleos_generados() {
        return empleos_generados;
    }

    public void setEmpleos_generados(String empleos_generados) {
        this.empleos_generados = empleos_generados;
    }

    public String getAportacion_gubernamental() {
        return aportacion_gubernamental;
    }

    public void setAportacion_gubernamental(String aportacion_gubernamental) {
        this.aportacion_gubernamental = aportacion_gubernamental;
    }

    public String getAportacion_productor() {
        return aportacion_productor;
    }

    public void setAportacion_productor(String aportacion_productor) {
        this.aportacion_productor = aportacion_productor;
    }
    
}
