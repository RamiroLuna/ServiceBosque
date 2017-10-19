package org.probosque.dto;

public class PointDTO {
    
    private int id;
    private String folio;
    private String wkt;
    private String label;
    private String idLayer;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getWkt() {
        return wkt;
    }

    public void setWkt(String wkt) {
        this.wkt = wkt;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIdLayer() {
        return idLayer;
    }

    public void setIdLayer(String idLayer) {
        this.idLayer = idLayer;
    }
}