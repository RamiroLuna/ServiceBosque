package org.probosque.dto;

public class SeguimientoInspeccionesDTO {

    public SeguimientoInspeccionesDTO() {
    }

    private String no_expediente;
    private String orden_inspeccion;
    private String acta_inspeccion;
    private String inspeccionado;
    private String tipo;
    private String abogado;
    private String fecha_conclusion;
    private String resolucion_admin;
    private String sancion;
    private String cantidad;
    private String terminado;

    public String getTerminado() {
        return terminado;
    }

    public void setTerminado(String terminado) {
        this.terminado = terminado;
    }


    public String getNo_expediente() {
        return no_expediente;
    }

    public void setNo_expediente(String no_expediente) {
        this.no_expediente = no_expediente;
    }

    public String getActa_inspeccion() {
        return acta_inspeccion;
    }

    public void setActa_inspeccion(String acta_inspeccion) {
        this.acta_inspeccion = acta_inspeccion;
    }

    public String getOorden_inspeccion() {
        return orden_inspeccion;
    }

    public void setOrden_inspeccion(String orden_inspeccion) {
        this.orden_inspeccion = orden_inspeccion;
    }

    public String getInspeccionado() {
        return inspeccionado;
    }

    public void setInspeccionado(String inspeccionado) {
        this.inspeccionado = inspeccionado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAbogado() {
        return abogado;
    }

    public void setAbogado(String abogado) {
        this.abogado = abogado;
    }

    public String getResolucion_admin() {
        return resolucion_admin;
    }

    public void setResolucion_admin(String resolucion_admin) {
        this.resolucion_admin = resolucion_admin;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha_conclusion() {
        return fecha_conclusion;
    }

    public void setFecha_conclusion(String fecha_conclusion) {
        this.fecha_conclusion = fecha_conclusion;
    }

    public String getSancion() {
        return sancion;
    }

    public void setSancion(String sancion) {
        this.sancion = sancion;
    }
    
    
}
