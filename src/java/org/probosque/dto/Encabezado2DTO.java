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
public class Encabezado2DTO {
    private String Vivero;
    private String Año;
    private String Tipo_produccion;
    private int Programada; 
    private int Produccion;

    public Encabezado2DTO() {
    }

    public Encabezado2DTO(String Vivero, String Año, String Tipo_produccion, int Programada, int Produccion) {
        this.Vivero = Vivero;
        this.Año = Año;
        this.Tipo_produccion = Tipo_produccion;
        this.Programada = Programada;
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
