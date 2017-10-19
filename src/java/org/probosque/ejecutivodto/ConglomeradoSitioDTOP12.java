package org.probosque.ejecutivodto;

public class ConglomeradoSitioDTOP12 {

    public ConglomeradoSitioDTOP12(String conglomerado, String sitio,String num_arbol, String especie, String volumen, String ima) {
        this.conglomerado = conglomerado;
        this.sitio = sitio;
        this.num_arbol=num_arbol;
        this.especie = especie;
        this.volumen = volumen;
        this.ima = ima;
    }

    public ConglomeradoSitioDTOP12() {
    }
public String conglomerado;
public String sitio;
public String num_arbol;
public String especie;
public String volumen;
public String ima;

   public String getNum_arbol() {
        return num_arbol;
    }

    public void setNum_arbol(String num_arbol) {
        this.num_arbol = num_arbol;
    }
    public String getConglomerado() {
        return conglomerado;
    }

    public void setConglomerado(String conglomerado) {
        this.conglomerado = conglomerado;
    }

    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getIma() {
        return ima;
    }

    public void setIma(String ima) {
        this.ima = ima;
    }
    
}
