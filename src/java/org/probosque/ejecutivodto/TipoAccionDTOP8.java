package org.probosque.ejecutivodto;

public class TipoAccionDTOP8 {

    public TipoAccionDTOP8() {
    }

    public TipoAccionDTOP8(String dictamen_pericial, String inspeccion_predios, String inspeccion_industria, String filtro_transporte, String operativos_coordinados) {
        this.dictamen_pericial = dictamen_pericial;
        this.inspeccion_predios = inspeccion_predios;
        this.inspeccion_industria = inspeccion_industria;
        this.filtro_transporte = filtro_transporte;
        this.operativos_coordinados = operativos_coordinados;
    }

public String dictamen_pericial;
public String inspeccion_predios;
public String inspeccion_industria;
public String filtro_transporte;
public String operativos_coordinados;

    public String getDictamen_pericial() {
        return dictamen_pericial;
    }

    public void setDictamen_pericial(String dictamen_pericial) {
        this.dictamen_pericial = dictamen_pericial;
    }

    public String getInspeccion_predios() {
        return inspeccion_predios;
    }

    public void setInspeccion_predios(String inspeccion_predios) {
        this.inspeccion_predios = inspeccion_predios;
    }

    public String getInspeccion_industria() {
        return inspeccion_industria;
    }

    public void setInspeccion_industria(String inspeccion_industria) {
        this.inspeccion_industria = inspeccion_industria;
    }

    public String getFiltro_transporte() {
        return filtro_transporte;
    }

    public void setFiltro_transporte(String filtro_transporte) {
        this.filtro_transporte = filtro_transporte;
    }

    public String getOperativos_coordinados() {
        return operativos_coordinados;
    }

    public void setOperativos_coordinados(String operativos_coordinados) {
        this.operativos_coordinados = operativos_coordinados;
    }

    
}
