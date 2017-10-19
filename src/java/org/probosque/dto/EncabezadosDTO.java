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
public class EncabezadosDTO {
    private String Vivero;
    private String Año;
    private int Programada;
    private int Produccion;

    public EncabezadosDTO() {
    }

    public EncabezadosDTO(String Vivero, String Año, int Programada, int Produccion) {
        this.Vivero = Vivero;
        this.Año = Año;
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
