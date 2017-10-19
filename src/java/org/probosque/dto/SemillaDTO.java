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
public class SemillaDTO {
    private String Nombre_comun;
    private String Nombre_Cientifico;
    private String region;
    private String municipio;  
    private String procedencia;
    private String fecha_alta;
    private String F_ClaLot;
    private float viabilidad;
    private float cantidad; 

    public SemillaDTO() {
    }

    
    public SemillaDTO(String Nombre_comun, String Nombre_Cientifico, String region, String municipio, String procedencia, String fecha_alta, String F_ClaLot, float viabilidad, float cantidad) {
        this.Nombre_comun = Nombre_comun;
        this.Nombre_Cientifico = Nombre_Cientifico;
        this.region = region;
        this.municipio = municipio;
        this.procedencia = procedencia;
        this.fecha_alta = fecha_alta;
        this.F_ClaLot = F_ClaLot;
        this.viabilidad = viabilidad;
        this.cantidad = cantidad;
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
     * @return the Nombre_Cientifico
     */
    public String getNombre_Cientifico() {
        return Nombre_Cientifico;
    }

    /**
     * @param Nombre_Cientifico the Nombre_Cientifico to set
     */
    public void setNombre_Cientifico(String Nombre_Cientifico) {
        this.Nombre_Cientifico = Nombre_Cientifico;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
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
     * @return the F_ClaLot
     */
    public String getF_ClaLot() {
        return F_ClaLot;
    }

    /**
     * @param F_ClaLot the F_ClaLot to set
     */
    public void setF_ClaLot(String F_ClaLot) {
        this.F_ClaLot = F_ClaLot;
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
    
    
}
