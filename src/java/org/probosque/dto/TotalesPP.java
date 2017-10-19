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
public class TotalesPP {
    private String Vivero;
    private String Nombre_comun;
    private String Programada;
    private String Producción;
    private String Disponibilidad;
    private String Entregada;
    private String F_Exis;

    public TotalesPP() {
    }

    public TotalesPP(String Vivero, String Nombre_comun, String Programada, String Producción, String Disponibilidad, String Entregada, String F_Exis) {
        this.Vivero = Vivero;
        this.Nombre_comun = Nombre_comun;
        this.Programada = Programada;
        this.Producción = Producción;
        this.Disponibilidad = Disponibilidad;
        this.Entregada = Entregada;
        this.F_Exis = F_Exis;
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
     * @return the Programada
     */
    public String getProgramada() {
        return Programada;
    }

    /**
     * @param Programada the Programada to set
     */
    public void setProgramada(String Programada) {
        this.Programada = Programada;
    }

    /**
     * @return the Producción
     */
    public String getProducción() {
        return Producción;
    }

    /**
     * @param Producción the Producción to set
     */
    public void setProducción(String Producción) {
        this.Producción = Producción;
    }

    /**
     * @return the Disponibilidad
     */
    public String getDisponibilidad() {
        return Disponibilidad;
    }

    /**
     * @param Disponibilidad the Disponibilidad to set
     */
    public void setDisponibilidad(String Disponibilidad) {
        this.Disponibilidad = Disponibilidad;
    }

    /**
     * @return the Entregada
     */
    public String getEntregada() {
        return Entregada;
    }

    /**
     * @param Entregada the Entregada to set
     */
    public void setEntregada(String Entregada) {
        this.Entregada = Entregada;
    }

    /**
     * @return the F_Exis
     */
    public String getF_Exis() {
        return F_Exis;
    }

    /**
     * @param F_Exis the F_Exis to set
     */
    public void setF_Exis(String F_Exis) {
        this.F_Exis = F_Exis;
    }

    
    
    
}
