package org.probosque.dto;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class ReportPredioDTO {
    
    private String programa;
    private ArrayList<String> unidadmedida;
    private ArrayList<String> cantidad;
    private ArrayList<String> accion;
    private ArrayList<String> montoapoyoaprobado;
    private ArrayList<String> montoapoyopagado;
    private ArrayList<String> resultado;
    private ArrayList<String> observaciones;

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public ArrayList<String> getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(ArrayList<String> unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    public ArrayList<String> getCantidad() {
        return cantidad;
    }

    public void setCantidad(ArrayList<String> cantidad) {
        this.cantidad = cantidad;
    }

    public ArrayList<String> getAccion() {
        return accion;
    }

    public void setAccion(ArrayList<String> accion) {
        this.accion = accion;
    }

    

    public ArrayList<String> getResultado() {
        return resultado;
    }

    public void setResultado(ArrayList<String> resultado) {
        this.resultado = resultado;
    }

    public ArrayList<String> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(ArrayList<String> observaciones) {
        this.observaciones = observaciones;
    }
    
    public int getRecordsSize(){
        if(unidadmedida!=null){
            return unidadmedida.size();
        }
        else{
            return 0;
        }
    }

    public ArrayList<String> getMontoapoyoaprobado() {
        return montoapoyoaprobado;
    }

    public void setMontoapoyoaprobado(ArrayList<String> montoapoyoaprobado) {
        this.montoapoyoaprobado = montoapoyoaprobado;
    }

    public ArrayList<String> getMontoapoyopagado() {
        return montoapoyopagado;
    }

    public void setMontoapoyopagado(ArrayList<String> montoapoyopagado) {
        this.montoapoyopagado = montoapoyopagado;
    }

}