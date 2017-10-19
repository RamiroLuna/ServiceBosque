package org.probosque.ejecutivodto;

public class EjecutivoPrograma3 {

    public EjecutivoPrograma3(SuperficieFinalRefDTOP3 supFinRef, MontoTotalDTOP3 montoTP3, TotalMinistracionDTOP3 totalMinistracion, PlantasNuevasDTOP3 plantas, ObservacionesP3DTO observaciones, SuperficieReforestadaProrrimDTOP3 prorrim,  MontoTotalProrrimDTOP3 montoTotalProrrim, TotalMinistracionProrrimDTOP3 totalMinistracionProrrim, SuperficieReforestadaMenosDesistidaDTOP3 SuperficieReforestadaMenosDesistida, ObservacionesProrrimDTOP3 observacionesProrrim) {
        this.supFinRef = supFinRef;
        this.montoTP3 = montoTP3;
        this.totalMinistracion = totalMinistracion;
        this.plantas = plantas;
        this.observaciones = observaciones;
        this.prorrim = prorrim;
        this.montoTotalProrrim = montoTotalProrrim;
        this.totalMinistracionProrrim = totalMinistracionProrrim;
        this.SuperficieReforestadaMenosDesistida = SuperficieReforestadaMenosDesistida;
        this.observacionesProrrim = observacionesProrrim;
    }

    public EjecutivoPrograma3() {
    }

   
public SuperficieFinalRefDTOP3 supFinRef;
public MontoTotalDTOP3 montoTP3; 
public TotalMinistracionDTOP3 totalMinistracion;
public PlantasNuevasDTOP3 plantas;  
public ObservacionesP3DTO observaciones;
public SuperficieReforestadaProrrimDTOP3 prorrim;

public MontoTotalProrrimDTOP3 montoTotalProrrim;
public TotalMinistracionProrrimDTOP3 totalMinistracionProrrim;
public SuperficieReforestadaMenosDesistidaDTOP3 SuperficieReforestadaMenosDesistida;
public ObservacionesProrrimDTOP3 observacionesProrrim;


    public ObservacionesP3DTO getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(ObservacionesP3DTO observaciones) {
        this.observaciones = observaciones;
    }
    public SuperficieFinalRefDTOP3 getSupFinRef() {
        return supFinRef;
    }

    public void setSupFinRef(SuperficieFinalRefDTOP3 supFinRef) {
        this.supFinRef = supFinRef;
    }

    public MontoTotalDTOP3 getMontoTP3() {
        return montoTP3;
    }

    public void setMontoTP3(MontoTotalDTOP3 montoTP3) {
        this.montoTP3 = montoTP3;
    }

    public TotalMinistracionDTOP3 getTotalMinistracion() {
        return totalMinistracion;
    }

    public void setTotalMinistracion(TotalMinistracionDTOP3 totalMinistracion) {
        this.totalMinistracion = totalMinistracion;
    }

    public PlantasNuevasDTOP3 getPlantas() {
        return plantas;
    }

    public void setPlantas(PlantasNuevasDTOP3 plantas) {
        this.plantas = plantas;
    }

    public SuperficieReforestadaProrrimDTOP3 getProrrim() {
        return prorrim;
    }

    public void setProrrim(SuperficieReforestadaProrrimDTOP3 prorrim) {
        this.prorrim = prorrim;
    }

    public MontoTotalProrrimDTOP3 getMontoTotalProrrim() {
        return montoTotalProrrim;
    }

    public void setMontoTotalProrrim(MontoTotalProrrimDTOP3 montoTotalProrrim) {
        this.montoTotalProrrim = montoTotalProrrim;
    }

    public TotalMinistracionProrrimDTOP3 getTotalMinistracionProrrim() {
        return totalMinistracionProrrim;
    }

    public void setTotalMinistracionProrrim(TotalMinistracionProrrimDTOP3 totalMinistracionProrrim) {
        this.totalMinistracionProrrim = totalMinistracionProrrim;
    }

    public SuperficieReforestadaMenosDesistidaDTOP3 getSuperficieReforestadaMenosDesistida() {
        return SuperficieReforestadaMenosDesistida;
    }

    public void setSuperficieReforestadaMenosDesistida(SuperficieReforestadaMenosDesistidaDTOP3 SuperficieReforestadaMenosDesistida) {
        this.SuperficieReforestadaMenosDesistida = SuperficieReforestadaMenosDesistida;
    }

    public ObservacionesProrrimDTOP3 getObservacionesProrrim() {
        return observacionesProrrim;
    }

    public void setObservacionesProrrim(ObservacionesProrrimDTOP3 observacionesProrrim) {
        this.observacionesProrrim = observacionesProrrim;
    }
 
}
