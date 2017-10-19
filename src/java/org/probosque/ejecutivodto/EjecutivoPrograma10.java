package org.probosque.ejecutivodto;

import java.util.List;

public class EjecutivoPrograma10 {

    public EjecutivoPrograma10() {
    }

    public EjecutivoPrograma10(SuperficieAprobadaDTOP10 superficieAprobada, MontoAprobadoDTOP10 montoAprobado, TotalMinistracionDTOP10 totalMinistracion, List<DatosGeneralesDTOP10> datosGenerales,List<ObservacionesDTOP10> observaciones) {
        this.superficieAprobada = superficieAprobada;
        this.montoAprobado = montoAprobado;
        this.totalMinistracion = totalMinistracion;
        this.datosGenerales = datosGenerales;
        this.observaciones=observaciones;     
    }

SuperficieAprobadaDTOP10 superficieAprobada;
MontoAprobadoDTOP10 montoAprobado; 
TotalMinistracionDTOP10 totalMinistracion;
List<DatosGeneralesDTOP10> datosGenerales;
List<ObservacionesDTOP10> observaciones;

    public List<ObservacionesDTOP10> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<ObservacionesDTOP10> observaciones) {
        this.observaciones = observaciones;
    }

    public SuperficieAprobadaDTOP10 getSuperficieAprobada() {
        return superficieAprobada;
    }

    public void setSuperficieAprobada(SuperficieAprobadaDTOP10 superficieAprobada) {
        this.superficieAprobada = superficieAprobada;
    }

    public MontoAprobadoDTOP10 getMontoAprobado() {
        return montoAprobado;
    }

    public void setMontoAprobado(MontoAprobadoDTOP10 montoAprobado) {
        this.montoAprobado = montoAprobado;
    }

    public TotalMinistracionDTOP10 getTotalMinistracion() {
        return totalMinistracion;
    }

    public void setTotalMinistracion(TotalMinistracionDTOP10 totalMinistracion) {
        this.totalMinistracion = totalMinistracion;
    }

    public List<DatosGeneralesDTOP10> getDatosGenerales() {
        return datosGenerales;
    }

    public void setDatosGenerales(List<DatosGeneralesDTOP10> datosGenerales) {
        this.datosGenerales = datosGenerales;
    }
    
}
