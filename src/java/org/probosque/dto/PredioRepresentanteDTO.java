/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dto;

/**
 *
 * @author Erick_G
 */
public class PredioRepresentanteDTO {
    
    String consecutivo;
    String folio;
    String nombre_propietario_representante;
    String nombre_secretario_representante_legal;
    String nombre_tesorero;
    String curp_propietario_o_representante;
    String inicio_periodo; 
    String fin_periodo;
    String observaciones_administracion;

    public PredioRepresentanteDTO() {
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getNombre_propietario_representante() {
        return nombre_propietario_representante;
    }

    public void setNombre_propietario_representante(String nombre_propietario_representante) {
        this.nombre_propietario_representante = nombre_propietario_representante;
    }

    public String getNombre_secretario_representante_legal() {
        return nombre_secretario_representante_legal;
    }

    public void setNombre_secretario_representante_legal(String nombre_secretario_representante_legal) {
        this.nombre_secretario_representante_legal = nombre_secretario_representante_legal;
    }

    public String getNombre_tesorero() {
        return nombre_tesorero;
    }

    public void setNombre_tesorero(String nombre_tesorero) {
        this.nombre_tesorero = nombre_tesorero;
    }

    public String getCurp_propietario_o_representante() {
        return curp_propietario_o_representante;
    }

    public void setCurp_propietario_o_representante(String curp_propietario_o_representante) {
        this.curp_propietario_o_representante = curp_propietario_o_representante;
    }

    public String getInicio_periodo() {
        return inicio_periodo;
    }

    public void setInicio_periodo(String inicio_periodo) {
        this.inicio_periodo = inicio_periodo;
    }

    public String getFin_periodo() {
        return fin_periodo;
    }

    public void setFin_periodo(String fin_periodo) {
        this.fin_periodo = fin_periodo;
    }

    public String getObservaciones_administracion() {
        return observaciones_administracion;
    }

    public void setObservaciones_administracion(String observaciones_administracion) {
        this.observaciones_administracion = observaciones_administracion;
    }
    
}
