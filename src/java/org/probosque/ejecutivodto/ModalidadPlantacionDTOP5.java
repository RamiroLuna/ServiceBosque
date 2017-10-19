package org.probosque.ejecutivodto;


public class ModalidadPlantacionDTOP5 {
public String tipo_reforestacion;    
public String porcentaje_sobrevivencia;

    public ModalidadPlantacionDTOP5() {
    }

    public ModalidadPlantacionDTOP5(String tipo_reforestacion, String porcentaje_sobrevivencia) {
        this.tipo_reforestacion = tipo_reforestacion;
        this.porcentaje_sobrevivencia = porcentaje_sobrevivencia;
    }
    public String getTipo_reforestacion() {
        return tipo_reforestacion;
    }

    public void setTipo_reforestacion(String tipo_reforestacion) {
        this.tipo_reforestacion = tipo_reforestacion;
    }

    public String getPorcentaje_sobrevivencia() {
        return porcentaje_sobrevivencia;
    }

    public void setPorcentaje_sobrevivencia(String porcentaje_sobrevivencia) {
        this.porcentaje_sobrevivencia = porcentaje_sobrevivencia;
    }

}
