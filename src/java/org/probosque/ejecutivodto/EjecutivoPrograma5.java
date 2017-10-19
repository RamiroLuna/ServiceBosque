package org.probosque.ejecutivodto;

public class EjecutivoPrograma5 {

    public EjecutivoPrograma5() {
    }

    public EjecutivoPrograma5(SuperficieFinalRefDTOP5 superficieFinalredf, MontoTotalDTOP5 montoP5, TotalMinistracionDTOP5 totalMinP5, ModalidadPlantacionDTOP5 plantacion, TotalAclareoDTOP5 totalAclareo, VentaArbolNavidadDTOP5 venta) {
        this.superficieFinalredf = superficieFinalredf;
        this.montoP5 = montoP5;
        this.totalMinP5 = totalMinP5;
        this.plantacion = plantacion;
        this.totalAclareo = totalAclareo;
        this.venta = venta;
    }
SuperficieFinalRefDTOP5 superficieFinalredf;    
MontoTotalDTOP5 montoP5;
TotalMinistracionDTOP5 totalMinP5;
ModalidadPlantacionDTOP5 plantacion;
TotalAclareoDTOP5 totalAclareo;
VentaArbolNavidadDTOP5 venta;

    public SuperficieFinalRefDTOP5 getSuperficieFinalredf() {
        return superficieFinalredf;
    }

    public void setSuperficieFinalredf(SuperficieFinalRefDTOP5 superficieFinalredf) {
        this.superficieFinalredf = superficieFinalredf;
    }

    public MontoTotalDTOP5 getMontoP5() {
        return montoP5;
    }

    public void setMontoP5(MontoTotalDTOP5 montoP5) {
        this.montoP5 = montoP5;
    }

    public TotalMinistracionDTOP5 getTotalMinP5() {
        return totalMinP5;
    }

    public void setTotalMinP5(TotalMinistracionDTOP5 totalMinP5) {
        this.totalMinP5 = totalMinP5;
    }

    public ModalidadPlantacionDTOP5 getPlantacion() {
        return plantacion;
    }

    public void setPlantacion(ModalidadPlantacionDTOP5 plantacion) {
        this.plantacion = plantacion;
    }

    public TotalAclareoDTOP5 getTotalAclareo() {
        return totalAclareo;
    }

    public void setTotalAclareo(TotalAclareoDTOP5 totalAclareo) {
        this.totalAclareo = totalAclareo;
    }

    public VentaArbolNavidadDTOP5 getVenta() {
        return venta;
    }

    public void setVenta(VentaArbolNavidadDTOP5 venta) {
        this.venta = venta;
    }
}
