package org.probosque.dto;

import java.io.Serializable;

public class AuditoriaDTO implements Serializable{

    public AuditoriaDTO() {
        super();
    }
    
    private Integer consecutivo;
    private String idPredio;
    private Boolean auditoriaTecnica;
    private String fechaATP;

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getIdPredio() {
        return idPredio;
    }

    public void setIdPredio(String idPredio) {
        this.idPredio = idPredio;
    }

    public Boolean getAuditoriaTecnica() {
        return auditoriaTecnica;
    }

    public void setAuditoriaTecnica(Boolean auditoriaTecnica) {
        this.auditoriaTecnica = auditoriaTecnica;
    }

    public String getFechaATP() {
        return fechaATP;
    }

    public void setFechaATP(String fechaATP) {
        this.fechaATP = fechaATP;
    }
    
}
