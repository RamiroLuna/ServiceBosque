package org.probosque.dto;

public class PanelCoordinadorDTO {

    public PanelCoordinadorDTO() {
    }
    
private String Folio;
private String No_expediente;
private String estatus;
private String Fecha_recepcion_abogado;
private String contenedor;
private String usuario;
private String fecha_conclusion;
private String multa;
private String decomiso;
private String estatus_reforestacion;
private String estatus_oficialia;
private String observaciones_publicas;
    public String getEstatus_oficialia() {
        return estatus_oficialia;
    }

    public void setEstatus_oficialia(String estatus_oficialia) {
        this.estatus_oficialia = estatus_oficialia;
    }

    public String getEstatus_reforestacion() {
        return estatus_reforestacion;
    }

    public void setEstatus_reforestacion(String estatus_reforestacion) {
        this.estatus_reforestacion = estatus_reforestacion;
    }

    public String getObservaciones_publicas() {
        return observaciones_publicas;
    }

    public void setObservaciones_publicas(String observaciones_publicas) {
        this.observaciones_publicas = observaciones_publicas;
    }

    public String getMulta() {
        return multa;
    }

    public void setMulta(String multa) {
        this.multa = multa;
    }

    public String getDecomiso() {
        return decomiso;
    }

    public void setDecomiso(String decomiso) {
        this.decomiso = decomiso;
    }

    public String getFolio() {
        return Folio;
    }

    public void setFolio(String Folio) {
        this.Folio = Folio;
    }

    public String getNo_expediente() {
        return No_expediente;
    }

    public void setNo_expediente(String No_expediente) {
        this.No_expediente = No_expediente;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getFecha_recepcion_abogado() {
        return Fecha_recepcion_abogado;
    }

    public void setFecha_recepcion_abogado(String Fecha_recepcion_abogado) {
        this.Fecha_recepcion_abogado = Fecha_recepcion_abogado;
    }

    public String getContenedor() {
        return contenedor;
    }

    public void setContenedor(String contenedor) {
        this.contenedor = contenedor;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
  public String getFecha_conclusion() {
        return fecha_conclusion;
    }

    public void setFecha_conclusion(String fecha_conclusion) {
        this.fecha_conclusion = fecha_conclusion;
    }
    
}
