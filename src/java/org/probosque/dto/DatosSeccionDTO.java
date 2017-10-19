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
public class DatosSeccionDTO {
    private String Vivero;
    private String Año; 
    private String Tipo_produccion;
    private String Nombre_comun;
    private String Nombre_cientifico;
    private int Programada;
    private String Recurso;
    private String Envase;
    private int Produccion;

    public DatosSeccionDTO() {
    }

    public DatosSeccionDTO(String Vivero, String Año, String Tipo_produccion, String Nombre_comun, String Nombre_cientifico, int Programada, String Recurso, String Envase, int Produccion) {
        this.Vivero = Vivero;
        this.Año = Año;
        this.Tipo_produccion = Tipo_produccion;
        this.Nombre_comun = Nombre_comun;
        this.Nombre_cientifico = Nombre_cientifico;
        this.Programada = Programada;
        this.Recurso = Recurso;
        this.Envase = Envase;
        this.Produccion = Produccion;
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
     * @return the Año
     */
    public String getAño() {
        return Año;
    }

    /**
     * @param Año the Año to set
     */
    public void setAño(String Año) {
        this.Año = Año;
    }

    /**
     * @return the Tipo_produccion
     */
    public String getTipo_produccion() {
        return Tipo_produccion;
    }

    /**
     * @param Tipo_produccion the Tipo_produccion to set
     */
    public void setTipo_produccion(String Tipo_produccion) {
        this.Tipo_produccion = Tipo_produccion;
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
     * @return the Programada
     */
    public int getProgramada() {
        return Programada;
    }

    /**
     * @param Programada the Programada to set
     */
    public void setProgramada(int Programada) {
        this.Programada = Programada;
    }

    /**
     * @return the Recurso
     */
    public String getRecurso() {
        return Recurso;
    }

    /**
     * @param Recurso the Recurso to set
     */
    public void setRecurso(String Recurso) {
        this.Recurso = Recurso;
    }

    /**
     * @return the Envase
     */
    public String getEnvase() {
        return Envase;
    }

    /**
     * @param Envase the Envase to set
     */
    public void setEnvase(String Envase) {
        this.Envase = Envase;
    }

    /**
     * @return the Produccion
     */
    public int getProduccion() {
        return Produccion;
    }

    /**
     * @param Produccion the Produccion to set
     */
    public void setProduccion(int Produccion) {
        this.Produccion = Produccion;
    }
    
    
}