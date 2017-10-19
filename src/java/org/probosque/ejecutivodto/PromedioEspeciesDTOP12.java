package org.probosque.ejecutivodto;
public class PromedioEspeciesDTOP12 {

    public PromedioEspeciesDTOP12() {
    }

    public PromedioEspeciesDTOP12(String especie, String promedio) {
        this.especie = especie;
        this.promedio = promedio;
    }
public String especie;
public String promedio;

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }
}
