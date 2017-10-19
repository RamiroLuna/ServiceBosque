package org.probosque.dto;

public class PanelOficialiaDTO {

    public PanelOficialiaDTO() {
    }

public String expediente;
public String folio;
public String abogado;
public String no_documento;
public String clave_unica;

    public String getClave_unica() {
        return clave_unica;
    }

    public void setClave_unica(String clave_unica) {
        this.clave_unica = clave_unica;
    }

    public String getNo_documento() {
        return no_documento;
    }

    public void setNo_documento(String no_documento) {
        this.no_documento = no_documento;
    }


    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getAbogado() {
        return abogado;
    }

    public void setAbogado(String abogado) {
        this.abogado = abogado;
    }

    
}
