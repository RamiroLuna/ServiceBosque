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
public class DPCiudadanoDTO {
    private int Año;
    private String Solicitante; 
    private String Region;
    private String Municipio;
    private String Vale_planta;
    private String Destino;
    private String Vivero;
    private String Nombre_comun; 
    private String Nombre_cientifico;
    private int Cantidad_planta;
    private String Tipo_salida;

    public DPCiudadanoDTO() {
    }

    public DPCiudadanoDTO(int Año, String Solicitante, String Region, String Municipio, String Vale_planta, String Destino, String Vivero, String Nombre_comun, String Nombre_cientifico, int Cantidad_planta, String Tipo_salida) {
        this.Año = Año;
        this.Solicitante = Solicitante;
        this.Region = Region;
        this.Municipio = Municipio;
        this.Vale_planta = Vale_planta;
        this.Destino = Destino;
        this.Vivero = Vivero;
        this.Nombre_comun = Nombre_comun;
        this.Nombre_cientifico = Nombre_cientifico;
        this.Cantidad_planta = Cantidad_planta;
        this.Tipo_salida = Tipo_salida;
    }

    /**
     * @return the Año
     */
    public int getAño() {
        return Año;
    }

    /**
     * @param Año the Año to set
     */
    public void setAño(int Año) {
        this.Año = Año;
    }

    /**
     * @return the Solicitante
     */
    public String getSolicitante() {
        return Solicitante;
    }

    /**
     * @param Solicitante the Solicitante to set
     */
    public void setSolicitante(String Solicitante) {
        this.Solicitante = Solicitante;
    }

    /**
     * @return the Region
     */
    public String getRegion() {
        return Region;
    }

    /**
     * @param Region the Region to set
     */
    public void setRegion(String Region) {
        this.Region = Region;
    }

    /**
     * @return the Municipio
     */
    public String getMunicipio() {
        return Municipio;
    }

    /**
     * @param Municipio the Municipio to set
     */
    public void setMunicipio(String Municipio) {
        this.Municipio = Municipio;
    }

    /**
     * @return the Vale_planta
     */
    public String getVale_planta() {
        return Vale_planta;
    }

    /**
     * @param Vale_planta the Vale_planta to set
     */
    public void setVale_planta(String Vale_planta) {
        this.Vale_planta = Vale_planta;
    }

    /**
     * @return the Destino
     */
    public String getDestino() {
        return Destino;
    }

    /**
     * @param Destino the Destino to set
     */
    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    /**
     * @return the Vivero
     */
    public String getVivero() {
        return Vivero;
    }

    /**
     * @param Vivero the Vivero to set
     */
    public void setVivero(String Vivero) {
        this.Vivero = Vivero;
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
     * @return the Cantidad_planta
     */
    public int getCantidad_planta() {
        return Cantidad_planta;
    }

    /**
     * @param Cantidad_planta the Cantidad_planta to set
     */
    public void setCantidad_planta(int Cantidad_planta) {
        this.Cantidad_planta = Cantidad_planta;
    }

    /**
     * @return the Tipo_salida
     */
    public String getTipo_salida() {
        return Tipo_salida;
    }

    /**
     * @param Tipo_salida the Tipo_salida to set
     */
    public void setTipo_salida(String Tipo_salida) {
        this.Tipo_salida = Tipo_salida;
    }
    
    
}
