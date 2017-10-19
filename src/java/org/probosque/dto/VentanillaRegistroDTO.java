/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dto;

/**
 *
 * @author TOSHIBA-L55
 */
public class VentanillaRegistroDTO {
    public VentanillaRegistroDTO(String modulopredio_estado,String region, String modulopredio_municipio
    , String anio, String modulopredio_cup, String folio, String regimen, String propietario, String prestador
    , String dictaminador_tecnico, String dictaminador_ambiental, String tipo_programa, String nivel_programa
    , String superficie, String volumen, String fecha_pago, String monto_pago, String fecha_recepcion, String fecha_registro
    , String requerimiento_mia, String solicitud_autorizacion, String proceso) {
        super();
        this.modulopredio_estado=modulopredio_estado;
        this.region = region;
        this.modulopredio_municipio=modulopredio_municipio;
        this.anio = anio;
        this.modulopredio_cup = modulopredio_cup;
        this.folio = folio;
        this.regimen = regimen;
        this.propietario = propietario;
        this.prestador = prestador;
        this.dictaminador_tecnico = dictaminador_tecnico;
        this.dictaminador_ambiental = dictaminador_ambiental;
        this.tipo_programa = tipo_programa;
        this.nivel_programa = nivel_programa;
        this.superficie = superficie;
        this.volumen = volumen;
        this.fecha_pago = fecha_pago;
        this.monto_pago = monto_pago;
        this.fecha_recepcion = fecha_recepcion;
        this.fecha_registro = fecha_registro;
        this.requerimiento_mia = requerimiento_mia;
        this.solicitud_autorizacion = solicitud_autorizacion;
        this.proceso = proceso;
    }
    private String modulopredio_estado;
    private String region;
    private String modulopredio_municipio;
    private String anio;
    private String modulopredio_cup;
    private String folio;
    private String regimen;
    private String propietario;
    private String prestador;
    private String dictaminador_tecnico;
    private String dictaminador_ambiental;
    private String tipo_programa;
    private String nivel_programa;
    private String superficie;
    private String volumen;
    private String fecha_pago;
    private String monto_pago;
    private String fecha_recepcion;
    private String fecha_registro;
    private String requerimiento_mia;
    private String solicitud_autorizacion;
    private String proceso;
    
    public void setModulopredioEstado(String modulopredio_estado) {
        this.modulopredio_estado = modulopredio_estado;
    }
    public String getModulopredioEstado() {
        return modulopredio_estado;
    }
    
    public void setRegion(String region) {
        this.region = region;
    }
    public String getRegion() {
        return region;
    }
    
    public void setModulopredioMunicipio(String modulopredio_municipio) {
        this.modulopredio_municipio = modulopredio_municipio;
    }
    public String getModulopredioMunicipio() {
        return modulopredio_municipio;
    }
    
    public void setAnio(String anio) {
        this.anio = anio;
    }
    public String getAnio() {
        return anio;
    }
    
    public void setModulopredioCup(String modulopredio_cup) {
        this.modulopredio_cup = modulopredio_cup;
    }
    public String getModulopredioCup() {
        return modulopredio_cup;
    }
    
    public void setFolio(String folio) {
        this.folio = folio;
    }
    public String getFolio() {
        return folio;
    }
    
    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }
    public String getRegimen() {
        return regimen;
    }
    
    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
    public String getPropietario() {
        return propietario;
    }
    
    public void setPrestador(String prestador) {
        this.prestador = prestador;
    }
    public String getPrestador() {
        return prestador;
    }
    
    public void setDictaminadorTecnico(String dictaminador_tecnico) {
        this.dictaminador_tecnico = dictaminador_tecnico;
    }
    public String getDictaminadorTecnico() {
        return dictaminador_tecnico;
    }
    
    public void setDictaminador_Ambiental(String dictaminador_ambiental) {
        this.dictaminador_ambiental = dictaminador_ambiental;
    }
    public String getDictaminador_Ambiental() {
        return dictaminador_ambiental;
    }
    
    public void setTipoPrograma(String tipo_programa) {
        this.tipo_programa = tipo_programa;
    }
    public String getTipoPrograma() {
        return tipo_programa;
    }
    
    public void setNivel_Programa(String nivel_programa) {
        this.nivel_programa = nivel_programa;
    }
    public String getNivel_Programa() {
        return nivel_programa;
    }
    
    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }
    public String getSuperficie() {
        return superficie;
    }
    
    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }
    public String getVolumen() {
        return volumen;
    }
    
    public void setFechaPago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }
    public String getFechaPago() {
        return fecha_pago;
    }
    
    public void setMontoPago(String monto_pago) {
        this.monto_pago = monto_pago;
    }
    public String getMontoPago() {
        return monto_pago;
    }
    
    public void setFechaRecepcion(String fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }
    public String getFechaRecepcion() {
        return fecha_recepcion;
    }
    
    public void setFechaRegistro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    public String getFechaRegistro() {
        return fecha_registro;
    }
    
    public void setRequerimientoMia(String requerimiento_mia) {
        this.requerimiento_mia = requerimiento_mia;
    }
    public String getRequerimientoMia() {
        return requerimiento_mia;
    }
    
    public void setSolicitudAutorizacion(String solicitud_autorizacion) {
        this.solicitud_autorizacion = solicitud_autorizacion;
    }
    public String getSolicitudAutorizacion() {
        return solicitud_autorizacion;
    }
    
    public void setProceso(String proceso) {
        this.proceso = proceso;
    }
    public String getProceso() {
        return proceso;
    }
}

