package org.probosque.dto;

import java.util.List;

public class PanelPrincipalCoordinadorDTO {

    public PanelPrincipalCoordinadorDTO() {
    }
private List<PanelCoordinadorDTO> EnTermino;
private List<PanelCoordinadorDTO> EnProceso;
private List<PanelCoordinadorDTO> Retraso;
private List<PanelCoordinadorDTO> Vencido;

    public List<PanelCoordinadorDTO> getEnTermino() {
        return EnTermino;
    }

    public void setEnTermino(List<PanelCoordinadorDTO> EnTermino) {
        this.EnTermino = EnTermino;
    }

    public List<PanelCoordinadorDTO> getEnProceso() {
        return EnProceso;
    }

    public void setEnProceso(List<PanelCoordinadorDTO> EnProceso) {
        this.EnProceso = EnProceso;
    }

    public List<PanelCoordinadorDTO> getRetraso() {
        return Retraso;
    }

    public void setRetraso(List<PanelCoordinadorDTO> Retraso) {
        this.Retraso = Retraso;
    }

    public List<PanelCoordinadorDTO> getVencido() {
        return Vencido;
    }

    public void setVencido(List<PanelCoordinadorDTO> Vencido) {
        this.Vencido = Vencido;
    }



    
}
