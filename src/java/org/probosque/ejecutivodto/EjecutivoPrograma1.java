
package org.probosque.ejecutivodto;

import java.util.List;
import org.probosque.dto.CatalogoDTO;
import org.probosque.dto.ResultString;

public class EjecutivoPrograma1 {

    public EjecutivoPrograma1() {
    }

    public EjecutivoPrograma1(List<VolumenesGeneroP1DTO> vol, Oficio_fechasP1DTO oficioP1, List<VolumenesTipoProductoDTOP1> volTpP1,SituacionPredioDTOP1 situacionPredio, Oficio_fechasP1DTO liberacionVencimiento, ResultString superficieArbolada, List<AreaRodalSuperficieDTOP1> rodalSuperficie,ResultString oficoAutorizacion) {
        this.vol = vol;
        this.oficioP1 = oficioP1;
        this.volTpP1 = volTpP1;
        this.situacionPredio=situacionPredio;
        this.liberacionVencimiento = liberacionVencimiento;
        this.superficieArbolada = superficieArbolada;
        this.rodalSuperficie = rodalSuperficie;
        this.oficoAutorizacion=oficoAutorizacion;
    }

    List<VolumenesGeneroP1DTO> vol;
    Oficio_fechasP1DTO       oficioP1;
    List<VolumenesTipoProductoDTOP1> volTpP1;
    SituacionPredioDTOP1 situacionPredio;
    Oficio_fechasP1DTO liberacionVencimiento;
    ResultString superficieArbolada;
    List<AreaRodalSuperficieDTOP1> rodalSuperficie;
    ResultString oficoAutorizacion;

    public SituacionPredioDTOP1 getSituacionPredio() {
        return situacionPredio;
    }

    public void setSituacionPredio(SituacionPredioDTOP1 situacionPredio) {
        this.situacionPredio = situacionPredio;
    }
    
    public ResultString getOficoAutorizacion() {
        return oficoAutorizacion;
    }

    public void setOficoAutorizacion(ResultString oficoAutorizacion) {
        this.oficoAutorizacion = oficoAutorizacion;
    }

    public List<VolumenesGeneroP1DTO> getVol() {
        return vol;
    }

    public void setVol(List<VolumenesGeneroP1DTO> vol) {
        this.vol = vol;
    }

    public Oficio_fechasP1DTO getOficioP1() {
        return oficioP1;
    }

    public void setOficioP1(Oficio_fechasP1DTO oficioP1) {
        this.oficioP1 = oficioP1;
    }

    public List<VolumenesTipoProductoDTOP1> getVolTpP1() {
        return volTpP1;
    }

    public void setVolTpP1(List<VolumenesTipoProductoDTOP1> volTpP1) {
        this.volTpP1 = volTpP1;
    }

    public Oficio_fechasP1DTO getLiberacionVencimiento() {
        return liberacionVencimiento;
    }

    public void setLiberacionVencimiento(Oficio_fechasP1DTO liberacionVencimiento) {
        this.liberacionVencimiento = liberacionVencimiento;
    }

    public ResultString getSuperficieArbolada() {
        return superficieArbolada;
    }

    public void setSuperficieArbolada(ResultString superficieArbolada) {
        this.superficieArbolada = superficieArbolada;
    }

    public List<AreaRodalSuperficieDTOP1> getRodalSuperficie() {
        return rodalSuperficie;
    }

    public void setRodalSuperficie(List<AreaRodalSuperficieDTOP1> rodalSuperficie) {
        this.rodalSuperficie = rodalSuperficie;
    }
    

    
}
