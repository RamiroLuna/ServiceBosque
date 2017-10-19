/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dto;

/**
 *
 * @author Administrador
 */
public class ListPredios {
    
    
    String consecutivo, folio, clave_unica_de_predio, cve_sedemex, campo_trabajo, 
       nombre_del_predio, cve_tenencia, descripcion_llegar_predio, latitud_utm, 
       longitud_utm, superficie_total_ha, superficie_cartografica_ha, 
       superficie_arbolada_ha, superficie_otros_usos_ha, microcuenca_subcuenca_especifica, 
       area_natural_protegida, status_predio, observaciones_predio, 
       propietario_o_representante_legal, poligonos_del_predio, clave_estatal, 
       cve_regftal, cve_num, cve_loctext;

    public ListPredios() {
    }

    public ListPredios(String consecutivo, String folio, String clave_unica_de_predio, String cve_sedemex, String campo_trabajo, String nombre_del_predio, String cve_tenencia, String descripcion_llegar_predio, String latitud_utm, String longitud_utm, String superficie_total_ha, String superficie_cartografica_ha, String superficie_arbolada_ha, String superficie_otros_usos_ha, String microcuenca_subcuenca_especifica, String area_natural_protegida, String status_predio, String observaciones_predio, String propietario_o_representante_legal, String poligonos_del_predio, String clave_estatal, String cve_regftal, String cve_num, String cve_loctext) {
        this.consecutivo = consecutivo;
        this.folio = folio;
        this.clave_unica_de_predio = clave_unica_de_predio;
        this.cve_sedemex = cve_sedemex;
        this.campo_trabajo = campo_trabajo;
        this.nombre_del_predio = nombre_del_predio;
        this.cve_tenencia = cve_tenencia;
        this.descripcion_llegar_predio = descripcion_llegar_predio;
        this.latitud_utm = latitud_utm;
        this.longitud_utm = longitud_utm;
        this.superficie_total_ha = superficie_total_ha;
        this.superficie_cartografica_ha = superficie_cartografica_ha;
        this.superficie_arbolada_ha = superficie_arbolada_ha;
        this.superficie_otros_usos_ha = superficie_otros_usos_ha;
        this.microcuenca_subcuenca_especifica = microcuenca_subcuenca_especifica;
        this.area_natural_protegida = area_natural_protegida;
        this.status_predio = status_predio;
        this.observaciones_predio = observaciones_predio;
        this.propietario_o_representante_legal = propietario_o_representante_legal;
        this.poligonos_del_predio = poligonos_del_predio;
        this.clave_estatal = clave_estatal;
        this.cve_regftal = cve_regftal;
        this.cve_num = cve_num;
        this.cve_loctext = cve_loctext;
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

    public String getClave_unica_de_predio() {
        return clave_unica_de_predio;
    }

    public void setClave_unica_de_predio(String clave_unica_de_predio) {
        this.clave_unica_de_predio = clave_unica_de_predio;
    }

    public String getCve_sedemex() {
        return cve_sedemex;
    }

    public void setCve_sedemex(String cve_sedemex) {
        this.cve_sedemex = cve_sedemex;
    }

    public String getCampo_trabajo() {
        return campo_trabajo;
    }

    public void setCampo_trabajo(String campo_trabajo) {
        this.campo_trabajo = campo_trabajo;
    }

    public String getNombre_del_predio() {
        return nombre_del_predio;
    }

    public void setNombre_del_predio(String nombre_del_predio) {
        this.nombre_del_predio = nombre_del_predio;
    }

    public String getCve_tenencia() {
        return cve_tenencia;
    }

    public void setCve_tenencia(String cve_tenencia) {
        this.cve_tenencia = cve_tenencia;
    }

    public String getDescripcion_llegar_predio() {
        return descripcion_llegar_predio;
    }

    public void setDescripcion_llegar_predio(String descripcion_llegar_predio) {
        this.descripcion_llegar_predio = descripcion_llegar_predio;
    }

    public String getLatitud_utm() {
        return latitud_utm;
    }

    public void setLatitud_utm(String latitud_utm) {
        this.latitud_utm = latitud_utm;
    }

    public String getLongitud_utm() {
        return longitud_utm;
    }

    public void setLongitud_utm(String longitud_utm) {
        this.longitud_utm = longitud_utm;
    }

    public String getSuperficie_total_ha() {
        return superficie_total_ha;
    }

    public void setSuperficie_total_ha(String superficie_total_ha) {
        this.superficie_total_ha = superficie_total_ha;
    }

    public String getSuperficie_cartografica_ha() {
        return superficie_cartografica_ha;
    }

    public void setSuperficie_cartografica_ha(String superficie_cartografica_ha) {
        this.superficie_cartografica_ha = superficie_cartografica_ha;
    }

    public String getSuperficie_arbolada_ha() {
        return superficie_arbolada_ha;
    }

    public void setSuperficie_arbolada_ha(String superficie_arbolada_ha) {
        this.superficie_arbolada_ha = superficie_arbolada_ha;
    }

    public String getSuperficie_otros_usos_ha() {
        return superficie_otros_usos_ha;
    }

    public void setSuperficie_otros_usos_ha(String superficie_otros_usos_ha) {
        this.superficie_otros_usos_ha = superficie_otros_usos_ha;
    }

    public String getMicrocuenca_subcuenca_especifica() {
        return microcuenca_subcuenca_especifica;
    }

    public void setMicrocuenca_subcuenca_especifica(String microcuenca_subcuenca_especifica) {
        this.microcuenca_subcuenca_especifica = microcuenca_subcuenca_especifica;
    }

    public String getArea_natural_protegida() {
        return area_natural_protegida;
    }

    public void setArea_natural_protegida(String area_natural_protegida) {
        this.area_natural_protegida = area_natural_protegida;
    }

    public String getStatus_predio() {
        return status_predio;
    }

    public void setStatus_predio(String status_predio) {
        this.status_predio = status_predio;
    }

    public String getObservaciones_predio() {
        return observaciones_predio;
    }

    public void setObservaciones_predio(String observaciones_predio) {
        this.observaciones_predio = observaciones_predio;
    }

    public String getPropietario_o_representante_legal() {
        return propietario_o_representante_legal;
    }

    public void setPropietario_o_representante_legal(String propietario_o_representante_legal) {
        this.propietario_o_representante_legal = propietario_o_representante_legal;
    }

    public String getPoligonos_del_predio() {
        return poligonos_del_predio;
    }

    public void setPoligonos_del_predio(String poligonos_del_predio) {
        this.poligonos_del_predio = poligonos_del_predio;
    }

    public String getClave_estatal() {
        return clave_estatal;
    }

    public void setClave_estatal(String clave_estatal) {
        this.clave_estatal = clave_estatal;
    }

    public String getCve_regftal() {
        return cve_regftal;
    }

    public void setCve_regftal(String cve_regftal) {
        this.cve_regftal = cve_regftal;
    }

    public String getCve_num() {
        return cve_num;
    }

    public void setCve_num(String cve_num) {
        this.cve_num = cve_num;
    }

    public String getCve_loctext() {
        return cve_loctext;
    }

    public void setCve_loctext(String cve_loctext) {
        this.cve_loctext = cve_loctext;
    }
    
    
    
}
