package org.probosque.dto;

/**
 *
 * @author admin
 */
public class RowDTO {

    private String folio;
    private String rfc;
    private String expediente;
    private String expediente_origen;

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getExpediente_origen() {
        return expediente_origen;
    }

    public void setExpediente_origen(String expediente_origen) {
        this.expediente_origen = expediente_origen;
    }
}