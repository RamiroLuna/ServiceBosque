package org.probosque.ejecutivodto;

public class FechaVisitaDiagnosticoDTOP6 {

    public FechaVisitaDiagnosticoDTOP6() {
    }

    public FechaVisitaDiagnosticoDTOP6(String fechaVisita, String plaga_enfermedad) {
        this.fechaVisita = fechaVisita;
        this.plaga_enfermedad = plaga_enfermedad;
    }
public String fechaVisita;
public String plaga_enfermedad;

    public String getPlaga_enfermedad() {
        return plaga_enfermedad;
    }

    public void setPlaga_enfermedad(String plaga_enfermedad) {
        this.plaga_enfermedad = plaga_enfermedad;
    }

    public String getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(String fechaVisita) {
        this.fechaVisita = fechaVisita;
    }




    
}
