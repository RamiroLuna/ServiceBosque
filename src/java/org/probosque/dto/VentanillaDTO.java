/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dto;

/**
 *
 * @author TOSHIBA-L55
 */
public class VentanillaDTO {
    
    private String folio;
    private int consecutivo;
    private String observacion;
    private String Fecha_Registro;
    private String fecha_envio_juridico;
    private String fecha_recibido_juridico;
    private String fecha_envio_tecnico;
    private String fecha_recibido_tecnico;
    private String fecha_envio_ambiental;
    private String fecha_recibido_ambiental;
    private String respuesta;
    private String predio;
    private int numvuelta;
    private int diasinternos;
    private int max_consecutivo;

    public VentanillaDTO() {
    }

    public VentanillaDTO(String folio, int consecutivo, String observacion, String Fecha_Registro, String fecha_envio_juridico, String fecha_recibido_juridico, String fecha_envio_tecnico, String fecha_recibido_tecnico, String fecha_envio_ambiental, String fecha_recibido_ambiental, String respuesta, String predio, int numvuelta, int diasinternos, int max_consecutivo) {
        this.folio = folio;
        this.consecutivo = consecutivo;
        this.observacion = observacion;
        this.Fecha_Registro = Fecha_Registro;
        this.fecha_envio_juridico = fecha_envio_juridico;
        this.fecha_recibido_juridico = fecha_recibido_juridico;
        this.fecha_envio_tecnico = fecha_envio_tecnico;
        this.fecha_recibido_tecnico = fecha_recibido_tecnico;
        this.fecha_envio_ambiental = fecha_envio_ambiental;
        this.fecha_recibido_ambiental = fecha_recibido_ambiental;
        this.respuesta = respuesta;
        this.predio = predio;
        this.numvuelta = numvuelta;
        this.diasinternos = diasinternos;
        this.max_consecutivo = max_consecutivo;
    }

    /**
     * @return the folio
     */
    public String getFolio() {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * @return the consecutivo
     */
    public int getConsecutivo() {
        return consecutivo;
    }

    /**
     * @param consecutivo the consecutivo to set
     */
    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the Fecha_Registro
     */
    public String getFecha_Registro() {
        return Fecha_Registro;
    }

    /**
     * @param Fecha_Registro the Fecha_Registro to set
     */
    public void setFecha_Registro(String Fecha_Registro) {
        this.Fecha_Registro = Fecha_Registro;
    }

    /**
     * @return the fecha_envio_juridico
     */
    public String getFecha_envio_juridico() {
        return fecha_envio_juridico;
    }

    /**
     * @param fecha_envio_juridico the fecha_envio_juridico to set
     */
    public void setFecha_envio_juridico(String fecha_envio_juridico) {
        this.fecha_envio_juridico = fecha_envio_juridico;
    }

    /**
     * @return the fecha_recibido_juridico
     */
    public String getFecha_recibido_juridico() {
        return fecha_recibido_juridico;
    }

    /**
     * @param fecha_recibido_juridico the fecha_recibido_juridico to set
     */
    public void setFecha_recibido_juridico(String fecha_recibido_juridico) {
        this.fecha_recibido_juridico = fecha_recibido_juridico;
    }

    /**
     * @return the fecha_envio_tecnico
     */
    public String getFecha_envio_tecnico() {
        return fecha_envio_tecnico;
    }

    /**
     * @param fecha_envio_tecnico the fecha_envio_tecnico to set
     */
    public void setFecha_envio_tecnico(String fecha_envio_tecnico) {
        this.fecha_envio_tecnico = fecha_envio_tecnico;
    }

    /**
     * @return the fecha_recibido_tecnico
     */
    public String getFecha_recibido_tecnico() {
        return fecha_recibido_tecnico;
    }

    /**
     * @param fecha_recibido_tecnico the fecha_recibido_tecnico to set
     */
    public void setFecha_recibido_tecnico(String fecha_recibido_tecnico) {
        this.fecha_recibido_tecnico = fecha_recibido_tecnico;
    }

    /**
     * @return the fecha_envio_ambiental
     */
    public String getFecha_envio_ambiental() {
        return fecha_envio_ambiental;
    }

    /**
     * @param fecha_envio_ambiental the fecha_envio_ambiental to set
     */
    public void setFecha_envio_ambiental(String fecha_envio_ambiental) {
        this.fecha_envio_ambiental = fecha_envio_ambiental;
    }

    /**
     * @return the fecha_recibido_ambiental
     */
    public String getFecha_recibido_ambiental() {
        return fecha_recibido_ambiental;
    }

    /**
     * @param fecha_recibido_ambiental the fecha_recibido_ambiental to set
     */
    public void setFecha_recibido_ambiental(String fecha_recibido_ambiental) {
        this.fecha_recibido_ambiental = fecha_recibido_ambiental;
    }

    /**
     * @return the respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    /**
     * @return the predio
     */
    public String getPredio() {
        return predio;
    }

    /**
     * @param predio the predio to set
     */
    public void setPredio(String predio) {
        this.predio = predio;
    }

    /**
     * @return the numvuelta
     */
    public int getNumvuelta() {
        return numvuelta;
    }

    /**
     * @param numvuelta the numvuelta to set
     */
    public void setNumvuelta(int numvuelta) {
        this.numvuelta = numvuelta;
    }

    /**
     * @return the diasinternos
     */
    public int getDiasinternos() {
        return diasinternos;
    }

    /**
     * @param diasinternos the diasinternos to set
     */
    public void setDiasinternos(int diasinternos) {
        this.diasinternos = diasinternos;
    }

    /**
     * @return the max_consecutivo
     */
    public int getMax_consecutivo() {
        return max_consecutivo;
    }

    /**
     * @param max_consecutivo the max_consecutivo to set
     */
    public void setMax_consecutivo(int max_consecutivo) {
        this.max_consecutivo = max_consecutivo;
    }
    
    
}
