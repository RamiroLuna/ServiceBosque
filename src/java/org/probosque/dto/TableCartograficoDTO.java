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
public class TableCartograficoDTO {
    private String Folio;
    private String Municipio;
    private String Localidad;
    private String Año;
    private String Región;
    private String Predio;
    private String Nombre_Representante_Actual;
    private String Áreas_de_Corta;
    private String Rodal;
    private String Existencias;
    private String Posibilidad;
    private String Residual;
    private String ICA_Pino_y_Oyamel;
    
    public TableCartograficoDTO() {
    }

    public TableCartograficoDTO(String Folio,String Municipio, String Localidad, String Año, String Región, String Predio) {
        this.Folio=Folio;
        this.Municipio = Municipio;
        this.Localidad = Localidad;
        this.Año = Año;
        this.Región = Región;
       this.Predio = Predio;
        
        
    }

    public TableCartograficoDTO(String Nombre_Representante_Actual, String Áreas_de_Corta, String Rodal, String Existencias, String Posibilidad, String Residual, String ICA_Pino_y_Oyamel) {
        this.Nombre_Representante_Actual = Nombre_Representante_Actual;
        this.Áreas_de_Corta = Áreas_de_Corta;
        this.Rodal = Rodal;
        this.Existencias = Existencias;
        this.Posibilidad = Posibilidad;
        this.Residual = Residual;
        this.ICA_Pino_y_Oyamel = ICA_Pino_y_Oyamel;
    }
    
    
    
           /**************************************************
     * @return the Folio
     */
    public String getFolio() {
        return Folio;
    }

    /**
     * @param Folio the Folio 
     */
    public void setFolio (String Folio ) {
        this.Folio = Folio ;
    } 


    /**********************************************************
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

    /**********************************************************
     * @return the Localidad
     */
    public String getLocalidad() {
        return Localidad;
    }

    /**
     * @param Localidad the Localidad to set
     */
    public void setLocalidad(String Localidad) {
        this.Localidad = Localidad;
    }
    /**********************************************************
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
/**********************************************************
     * @return the Región
     */
      public String getRegión() {
        return Región;
    }

    /**
     * @param Región the Región to set
     */
    public void setRegión(String Región) {
        this.Región = Región;
    }
    
    /**
     * @return the Predio
     */
    public String getPredio() {
        return Predio;
    }

    /**
     * @param Predio the Situación_especial_del_predio to set
     */
    public void setPredio(String Predio) {
        this.Predio= Predio;
    }
    
    /*P1*********************************************************/

    public String getNombre_Representante_Actual() {
        return Nombre_Representante_Actual;
    }

    public String getÁreas_de_Corta() {
        return Áreas_de_Corta;
    }

    public String getRodal() {
        return Rodal;
    }

    public String getExistencias() {
        return Existencias;
    }

    public String getPosibilidad() {
        return Posibilidad;
    }

    public String getResidual() {
        return Residual;
    }

    public String getICA_Pino_y_Oyamel() {
        return ICA_Pino_y_Oyamel;
    }

    public void setNombre_Representante_Actual(String Nombre_Representante_Actual) {
        this.Nombre_Representante_Actual = Nombre_Representante_Actual;
    }

    public void setÁreas_de_Corta(String Áreas_de_Corta) {
        this.Áreas_de_Corta = Áreas_de_Corta;
    }

    public void setRodal(String Rodal) {
        this.Rodal = Rodal;
    }

    public void setExistencias(String Existencias) {
        this.Existencias = Existencias;
    }

    public void setPosibilidad(String Posibilidad) {
        this.Posibilidad = Posibilidad;
    }

    public void setResidual(String Residual) {
        this.Residual = Residual;
    }

    public void setICA_Pino_y_Oyamel(String ICA_Pino_y_Oyamel) {
        this.ICA_Pino_y_Oyamel = ICA_Pino_y_Oyamel;
    }
       
    
}
