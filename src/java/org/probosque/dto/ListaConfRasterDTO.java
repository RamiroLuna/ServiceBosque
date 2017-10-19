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
public class ListaConfRasterDTO {
    
    public ListaConfRasterDTO(){
    }
    
    String identificador_raster;
    String nombre_raster;
    String cuadratura_raster;
    String tamano_raster;
    

    public ListaConfRasterDTO(String identificador_raster, String nombre_raster, String cuadratura_raster, String tamano_raster) {
        this.identificador_raster = identificador_raster;
        this.nombre_raster = nombre_raster;
        this.cuadratura_raster = cuadratura_raster;
        this.tamano_raster = tamano_raster;
    }

    public String getIdentificador_raster() {
        return identificador_raster;
    }

    public void setIdentificador_raster(String identificador_raster) {
        this.identificador_raster = identificador_raster;
    }

    public String getNombre_raster() {
        return nombre_raster;
    }

    public void setNombre_raster(String nombre_raster) {
        this.nombre_raster = nombre_raster;
    }

    public String getCuadratura_raster() {
        return cuadratura_raster;
    }

    public void setCuadratura_raster(String cuadratura_raster) {
        this.cuadratura_raster = cuadratura_raster;
    }

    public String getTamano_raster() {
        return tamano_raster;
    }

    public void setTamano_raster(String tamano_raster) {
        this.tamano_raster = tamano_raster;
    }
}
