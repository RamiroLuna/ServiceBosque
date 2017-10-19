package org.probosque.ejecutivodto;

public class CostoApoyoDTOP11 {

    public CostoApoyoDTOP11() {
    }

    public CostoApoyoDTOP11(String tipo_apoyo, String costo_total) {
        this.tipo_apoyo = tipo_apoyo;
        this.costo_total = costo_total;
    }
public String tipo_apoyo;
public String costo_total;

    public String getTipo_apoyo() {
        return tipo_apoyo;
    }

    public void setTipo_apoyo(String tipo_apoyo) {
        this.tipo_apoyo = tipo_apoyo;
    }

    public String getCosto_total() {
        return costo_total;
    }

    public void setCosto_total(String costo_total) {
        this.costo_total = costo_total;
    }
}
