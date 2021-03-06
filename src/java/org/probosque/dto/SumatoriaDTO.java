package org.probosque.dto;

/**
 *
 * @author admin
 */
public class SumatoriaDTO {

    private int id;
    private int idprograma;
    private String programa;
    private String unidadmedida;
    private String cantidad;
    private String accion;
    private String montoapoyoaprobado;
    private String montoapoyopagado;
    private String resultado;
    private String observaciones;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdprograma() {
        return idprograma;
    }

    public void setIdprograma(int idprograma) {
        this.idprograma = idprograma;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(String unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getMontoapoyoaprobado() {
        return montoapoyoaprobado;
    }

    public void setMontoapoyoaprobado(String montoapoyoaprobado) {
        this.montoapoyoaprobado = montoapoyoaprobado;
    }

    public String getMontoapoyopagado() {
        return montoapoyopagado;
    }

    public void setMontoapoyopagado(String montoapoyopagado) {
        this.montoapoyopagado = montoapoyopagado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}