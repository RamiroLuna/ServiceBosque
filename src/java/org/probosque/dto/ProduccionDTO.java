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
public class ProduccionDTO {
    private String Vivero;
    private String Nombre_comun;
    private String Nombre_cientifico;
    private String Lote_Semilla;
    private String Tipo_produccion;
    private int Programada;
    private String Recurso;
    private String Envase;
    private String Altura;
    private int Producción;
    private int Disponibilidad;
    private float Entregada;
    private float F_Exis;

    public ProduccionDTO() {
    }

    public ProduccionDTO(String Vivero, String Nombre_comun, String Nombre_cientifico, String Lote_Semilla, String Tipo_produccion, int Programada, String Recurso, String Envase, String Altura, int Producción, int Disponibilidad, float Entregada, float F_Exis) {
        this.Vivero = Vivero;
        this.Nombre_comun = Nombre_comun;
        this.Nombre_cientifico = Nombre_cientifico;
        this.Lote_Semilla = Lote_Semilla;
        this.Tipo_produccion = Tipo_produccion;
        this.Programada = Programada;
        this.Recurso = Recurso;
        this.Envase = Envase;
        this.Altura = Altura;
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
     * @return the Lote_Semilla
     */
    public String getLote_Semilla() {
        return Lote_Semilla;
    }

    /**
     * @param Lote_Semilla the Lote_Semilla to set
     */
    public void setLote_Semilla(String Lote_Semilla) {
        this.Lote_Semilla = Lote_Semilla;
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
     * @return the Altura
     */
    public String getAltura() {
        return Altura;
    }

    /**
     * @param Altura the Altura to set
     */
    public void setAltura(String Altura) {
        this.Altura = Altura;
    }

    /**
     * @return the Producción
     */
    public int getProducción() {
        return Producción;
    }

    /**
     * @param Producción the Producción to set
     */
    public void setProducción(int Producción) {
        this.Producción = Producción;
    }

    /**
     * @return the Disponibilidad
     */
    public int getDisponibilidad() {
        return Disponibilidad;
    }

    /**
     * @param Disponibilidad the Disponibilidad to set
     */
    public void setDisponibilidad(int Disponibilidad) {
        this.Disponibilidad = Disponibilidad;
    }

    /**
     * @return the Entregada
     */
    public float getEntregada() {
        return Entregada;
    }

    /**
     * @param Entregada the Entregada to set
     */
    public void setEntregada(float Entregada) {
        this.Entregada = Entregada;
    }

    /**
     * @return the F_Exis
     */
    public float getF_Exis() {
        return F_Exis;
    }

    /**
     * @param F_Exis the F_Exis to set
     */
    public void setF_Exis(float F_Exis) {
        this.F_Exis = F_Exis;
    }

    
}
