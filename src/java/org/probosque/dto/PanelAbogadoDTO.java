package org.probosque.dto;

import java.util.List;

public class PanelAbogadoDTO {

    public PanelAbogadoDTO(List<ContenedorEstatusAbogado> contenedorEnProceso, List<ContenedorEstatusAbogado> contenedorMedidaSeguridad) {
        this.contenedorEnProceso = contenedorEnProceso;
        this.contenedorMedidaSeguridad = contenedorMedidaSeguridad;
    }
    
    
    public List<ContenedorEstatusAbogado> contenedorEnProceso;
    public List<ContenedorEstatusAbogado> contenedorMedidaSeguridad;

    public PanelAbogadoDTO() {
       
    }

    public List<ContenedorEstatusAbogado> getContenedorEnProceso() {
        return contenedorEnProceso;
    }

    public void setContenedorEnProceso(List<ContenedorEstatusAbogado> contenedorEnProceso) {
        this.contenedorEnProceso = contenedorEnProceso;
    }

    public List<ContenedorEstatusAbogado> getContenedorMedidaSeguridad() {
        return contenedorMedidaSeguridad;
    }

    public void setContenedorMedidaSeguridad(List<ContenedorEstatusAbogado> contenedorMedidaSeguridad) {
        this.contenedorMedidaSeguridad = contenedorMedidaSeguridad;
    }
    
    
    
    
}
