package org.probosque.ejecutivodto;

public class DatosGeneralesDTOP10 {

    public DatosGeneralesDTOP10() {
    }

    public DatosGeneralesDTOP10(String tipo_actividad, String meta_comprometida, String unidad_medida, String numero_jornales) {
        this.tipo_actividad = tipo_actividad;
        this.meta_comprometida = meta_comprometida;
        this.unidad_medida = unidad_medida;
        this.numero_jornales = numero_jornales;
    }
public String tipo_actividad;
public String meta_comprometida;
public String unidad_medida;
public String numero_jornales;

    public String getTipo_actividad() {
        return tipo_actividad;
    }

    public void setTipo_actividad(String tipo_actividad) {
        this.tipo_actividad = tipo_actividad;
    }

    public String getMeta_comprometida() {
        return meta_comprometida;
    }

    public void setMeta_comprometida(String meta_comprometida) {
        this.meta_comprometida = meta_comprometida;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public String getNumero_jornales() {
        return numero_jornales;
    }

    public void setNumero_jornales(String numero_jornales) {
        this.numero_jornales = numero_jornales;
    }
}
