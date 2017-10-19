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
public class ListBienesAsegurados {
    
    
    String consecutivo, folio, tipo_producto, ilicito_producto, genero_producto, 
       unidad_medida, lugar_deposito, total_productos_asegurados, total_herramientas_menores, 
       identificador_bien;

    public ListBienesAsegurados() {
    }

    public ListBienesAsegurados(String consecutivo, String folio, String tipo_producto, String ilicito_producto, String genero_producto, String unidad_medida, String lugar_deposito, String total_productos_asegurados, String total_herramientas_menores, String identificador_bien) {
        this.consecutivo = consecutivo;
        this.folio = folio;
        this.tipo_producto = tipo_producto;
        this.ilicito_producto = ilicito_producto;
        this.genero_producto = genero_producto;
        this.unidad_medida = unidad_medida;
        this.lugar_deposito = lugar_deposito;
        this.total_productos_asegurados = total_productos_asegurados;
        this.total_herramientas_menores = total_herramientas_menores;
        this.identificador_bien = identificador_bien;
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

    public String getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public String getIlicito_producto() {
        return ilicito_producto;
    }

    public void setIlicito_producto(String ilicito_producto) {
        this.ilicito_producto = ilicito_producto;
    }

    public String getGenero_producto() {
        return genero_producto;
    }

    public void setGenero_producto(String genero_producto) {
        this.genero_producto = genero_producto;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public String getLugar_deposito() {
        return lugar_deposito;
    }

    public void setLugar_deposito(String lugar_deposito) {
        this.lugar_deposito = lugar_deposito;
    }

    public String getTotal_productos_asegurados() {
        return total_productos_asegurados;
    }

    public void setTotal_productos_asegurados(String total_productos_asegurados) {
        this.total_productos_asegurados = total_productos_asegurados;
    }

    public String getTotal_herramientas_menores() {
        return total_herramientas_menores;
    }

    public void setTotal_herramientas_menores(String total_herramientas_menores) {
        this.total_herramientas_menores = total_herramientas_menores;
    }

    public String getIdentificador_bien() {
        return identificador_bien;
    }

    public void setIdentificador_bien(String identificador_bien) {
        this.identificador_bien = identificador_bien;
    }
    
    
    
}
