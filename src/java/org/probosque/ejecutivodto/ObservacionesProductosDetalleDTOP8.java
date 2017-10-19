package org.probosque.ejecutivodto;

public class ObservacionesProductosDetalleDTOP8 {

    public ObservacionesProductosDetalleDTOP8() {
    }

    public ObservacionesProductosDetalleDTOP8(String observaciones, String total_productos_asegurados) {
        this.observaciones = observaciones;
        this.total_productos_asegurados = total_productos_asegurados;
    }
public String observaciones;
public String  total_productos_asegurados;

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTotal_productos_asegurados() {
        return total_productos_asegurados;
    }

    public void setTotal_productos_asegurados(String total_productos_asegurados) {
        this.total_productos_asegurados = total_productos_asegurados;
    }

}
