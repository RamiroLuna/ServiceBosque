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
public class ListArchivos {
    
    
    String consecutivo, folio, url, fecha, descripcion, campoasociado;

    public ListArchivos() {
    }

    public ListArchivos(String consecutivo, String folio, String url, String fecha, String descripcion, String campoasociado) {
        this.consecutivo = consecutivo;
        this.folio = folio;
        this.url = url;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.campoasociado = campoasociado;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCampoasociado() {
        return campoasociado;
    }

    public void setCampoasociado(String campoasociado) {
        this.campoasociado = campoasociado;
    }
    
    
    
}
