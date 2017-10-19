package org.probosque.ejecutivodto;

public class DatosPredioDTO {

    public DatosPredioDTO(String tenencia, String id, String descripcion, String representante,
            String region, String municipio,String localidad) {
        this.tenencia = tenencia;
        this.id = id;
        this.descripcion = descripcion;
        this.representante = representante;
        this.region = region;
        this.municipio= municipio;
        this.localidad=localidad;
    }

    public DatosPredioDTO() {
    }

    public String tenencia;
    public String id;
    public String descripcion;
    public String representante;
    public String region;
    public String municipio;
    public String localidad;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    
    
    public String getTenencia() {
        return tenencia;
    }

    public void setTenencia(String tenencia) {
        this.tenencia = tenencia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

}
