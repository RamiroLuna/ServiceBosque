package org.probosque.model.json;

import org.probosque.dto.RegionDTO;

public class PredioDTO {

    public PredioDTO() {
    }
private Boolean successfull;    
private String mensaje; 
private RegionDTO predioViejo;
private RegionDTO predioNuevo;

 public Boolean getSuccessfull() {
        return successfull;
    }

    public void setSuccessfull(Boolean successfull) {
        this.successfull = successfull;
    }
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public RegionDTO getPredioViejo() {
        return predioViejo;
    }

    public void setPredioViejo(RegionDTO predioViejo) {
        this.predioViejo = predioViejo;
    }

    public RegionDTO getPredioNuevo() {
        return predioNuevo;
    }

    public void setPredioNuevo(RegionDTO predioNuevo) {
        this.predioNuevo = predioNuevo;
    }


}
