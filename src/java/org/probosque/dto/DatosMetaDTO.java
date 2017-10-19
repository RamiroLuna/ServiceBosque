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
public class DatosMetaDTO {
    private int id_meta;
    private int id_region;
    private String descripcion;
    private String anio;
    private String meta_anual;

    public DatosMetaDTO() {
    }

    public DatosMetaDTO(int id_meta, int id_region, String descripcion, String anio, String meta_anual) {
        this.id_meta = id_meta;
        this.id_region = id_region;
        this.descripcion = descripcion;
        this.anio = anio;
        this.meta_anual = meta_anual;
    }

    /**
     * @return the id_meta
     */
    public int getId_meta() {
        return id_meta;
    }

    /**
     * @param id_meta the id_meta to set
     */
    public void setId_meta(int id_meta) {
        this.id_meta = id_meta;
    }

    /**
     * @return the id_region
     */
    public int getId_region() {
        return id_region;
    }

    /**
     * @param id_region the id_region to set
     */
    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the anio
     */
    public String getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(String anio) {
        this.anio = anio;
    }

    /**
     * @return the meta_anual
     */
    public String getMeta_anual() {
        return meta_anual;
    }

    /**
     * @param meta_anual the meta_anual to set
     */
    public void setMeta_anual(String meta_anual) {
        this.meta_anual = meta_anual;
    }
    
}