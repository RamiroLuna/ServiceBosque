package org.probosque.ejecutivodto;

public class VolumenesTipoProductoDTOP1 {

    public VolumenesTipoProductoDTOP1() {
    }

    public VolumenesTipoProductoDTOP1(String genero, String produccion_escuadria, String celulosa, String lenia_combustible, String carbon, String volumen_ejercido) {
        this.genero = genero;
        this.produccion_escuadria = produccion_escuadria;
        this.celulosa = celulosa;
        this.lenia_combustible = lenia_combustible;
        this.carbon = carbon;
        this.volumen_ejercido = volumen_ejercido;
    }
    String genero;
    String produccion_escuadria;
    String celulosa;
    String lenia_combustible;
    String carbon;
    String volumen_ejercido;

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getProduccion_escuadria() {
        return produccion_escuadria;
    }

    public void setProduccion_escuadria(String produccion_escuadria) {
        this.produccion_escuadria = produccion_escuadria;
    }

    public String getCelulosa() {
        return celulosa;
    }

    public void setCelulosa(String celulosa) {
        this.celulosa = celulosa;
    }

    public String getLenia_combustible() {
        return lenia_combustible;
    }

    public void setLenia_combustible(String lenia_combustible) {
        this.lenia_combustible = lenia_combustible;
    }

    public String getCarbon() {
        return carbon;
    }

    public void setCarbon(String carbon) {
        this.carbon = carbon;
    }

    public String getVolumen_ejercido() {
        return volumen_ejercido;
    }

    public void setVolumen_ejercido(String volumen_ejercido) {
        this.volumen_ejercido = volumen_ejercido;
    }
    
}
