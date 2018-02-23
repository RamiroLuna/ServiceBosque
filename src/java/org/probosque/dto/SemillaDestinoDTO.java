/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dto;

/**
 *
 * @author Jonathan
 */
public class SemillaDestinoDTO {
    private int año;
    private String Nombre_cientifico;
    private String Nombre_comun;
    private String procedencia;
    private String destino;
    private String fecha_alta;
    private String lote_semilla;
    private float viabilidad; 
    private float cantidad; 
    private float costo;

    public SemillaDestinoDTO() {
    }

    public SemillaDestinoDTO(int año, String Nombre_cientifico, String Nombre_comun, String procedencia, String destino, String fecha_alta, String lote_semilla, float viabilidad, float cantidad, float costo) {
        this.año = año;
        this.Nombre_cientifico = Nombre_cientifico;
        this.Nombre_comun = Nombre_comun;
        this.procedencia = procedencia;
        this.destino = destino;
        this.fecha_alta = fecha_alta;
        this.lote_semilla = lote_semilla;
        this.viabilidad = viabilidad;
        this.cantidad = cantidad;
        this.costo = costo;
    }

    /**
     * @return the año
     */
    public int getAño() {
        return año;
    }

    /**
     * @param año the año to set
     */
    public void setAño(int año) {
        this.año = año;
    }

    /**
     * @return the Nombre_cientifico
     */
    public String getNombre_cientifico() {
        return Nombre_cientifico;
    }

    /**
     * @param Nombre_cientifico the Nombre_cientifico to set
     */
    public void setNombre_cientifico(String Nombre_cientifico) {
        this.Nombre_cientifico = Nombre_cientifico;
    }

    /**
     * @return the Nombre_comun
     */
    public String getNombre_comun() {
        return Nombre_comun;
    }

    /**
     * @param Nombre_comun the Nombre_comun to set
     */
    public void setNombre_comun(String Nombre_comun) {
        this.Nombre_comun = Nombre_comun;
    }

    /**
     * @return the procedencia
     */
    public String getProcedencia() {
        return procedencia;
    }

    /**
     * @param procedencia the procedencia to set
     */
    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    

    /**
     * @return the fecha_alta
     */
    public String getFecha_alta() {
        return fecha_alta;
    }

    /**
     * @param fecha_alta the fecha_alta to set
     */
    public void setFecha_alta(String fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    /**
     * @return the lote_semilla
     */
    public String getLote_semilla() {
        return lote_semilla;
    }

    /**
     * @param lote_semilla the lote_semilla to set
     */
    public void setLote_semilla(String lote_semilla) {
        this.lote_semilla = lote_semilla;
    }

    /**
     * @return the viabilidad
     */
    public float getViabilidad() {
        return viabilidad;
    }

    /**
     * @param viabilidad the viabilidad to set
     */
    public void setViabilidad(float viabilidad) {
        this.viabilidad = viabilidad;
    }

    /**
     * @return the cantidad
     */
    public float getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the costo
     */
    public float getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(float costo) {
        this.costo = costo;
    }
    
    
}
