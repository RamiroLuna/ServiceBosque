package org.probosque.dto;

public class ContenedorEstatusAbogado {

    public ContenedorEstatusAbogado() {
    }

public String Folio;
public String No_expediente;
public String Estatus;
public String Fecha_recepcion_abogado;
public String fecha_conclusion;
public String multa;
public String decomiso;

    public String getFecha_conclusion() {
        return fecha_conclusion;
    }

    public void setFecha_conclusion(String fecha_conclusion) {
        this.fecha_conclusion = fecha_conclusion;
    }

    public String getMulta() {
        return multa;
    }

    public void setMulta(String multa) {
        this.multa = multa;
    }

    public String getDecomiso() {
        return decomiso;
    }

    public void setDecomiso(String decomiso) {
        this.decomiso = decomiso;
    }

    public String getFolio() {
        return Folio;
    }

    public void setFolio(String Folio) {
        this.Folio = Folio;
    }

    public String getNo_expediente() {
        return No_expediente;
    }

    public void setNo_expediente(String No_expediente) {
        this.No_expediente = No_expediente;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

    public String getFecha_recepcion_abogado() {
        return Fecha_recepcion_abogado;
    }

    public void setFecha_recepcion_abogado(String Fecha_recepcion_abogado) {
        this.Fecha_recepcion_abogado = Fecha_recepcion_abogado;
    }



    
}
