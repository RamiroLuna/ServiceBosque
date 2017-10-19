package org.probosque.ejecutivodto;

import java.util.List;

public class EjecutivoPrograma6 {

    public EjecutivoPrograma6() {
    }

    public EjecutivoPrograma6(RegistroPredioDTOP6 registroPredio, List<FechaVisitaDiagnosticoDTOP6> fechaPlaga, List<ObservacionesDTOP6> observaciones, List<FechaPlagaNotVolDTOP6> fechaplaganotvol, AsistenciaTeVolumenSDTOP6 asistenciaVolumen,SuperficieTotalBoscosaDTOP6 supTotalBoscosa) {
        this.registroPredio = registroPredio;
        this.fechaPlaga = fechaPlaga;
        this.observaciones = observaciones;
        this.fechaplaganotvol = fechaplaganotvol;
        this.asistenciaVolumen = asistenciaVolumen;
        this.supTotalBoscosa=supTotalBoscosa;
    }
RegistroPredioDTOP6 registroPredio;
List<FechaVisitaDiagnosticoDTOP6> fechaPlaga;
List<ObservacionesDTOP6> observaciones;
List<FechaPlagaNotVolDTOP6> fechaplaganotvol;
AsistenciaTeVolumenSDTOP6 asistenciaVolumen;
SuperficieTotalBoscosaDTOP6 supTotalBoscosa;

    public SuperficieTotalBoscosaDTOP6 getSupTotalBoscosa() {
        return supTotalBoscosa;
    }

    public void setSupTotalBoscosa(SuperficieTotalBoscosaDTOP6 supTotalBoscosa) {
        this.supTotalBoscosa = supTotalBoscosa;
    }
    public RegistroPredioDTOP6 getRegistroPredio() {
        return registroPredio;
    }

    public void setRegistroPredio(RegistroPredioDTOP6 registroPredio) {
        this.registroPredio = registroPredio;
    }

    public List<FechaVisitaDiagnosticoDTOP6> getFechaPlaga() {
        return fechaPlaga;
    }

    public void setFechaPlaga(List<FechaVisitaDiagnosticoDTOP6> fechaPlaga) {
        this.fechaPlaga = fechaPlaga;
    }

    public List<ObservacionesDTOP6> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<ObservacionesDTOP6> observaciones) {
        this.observaciones = observaciones;
    }

    public List<FechaPlagaNotVolDTOP6> getFechaplaganotvol() {
        return fechaplaganotvol;
    }

    public void setFechaplaganotvol(List<FechaPlagaNotVolDTOP6> fechaplaganotvol) {
        this.fechaplaganotvol = fechaplaganotvol;
    }

    public AsistenciaTeVolumenSDTOP6 getAsistenciaVolumen() {
        return asistenciaVolumen;
    }

    public void setAsistenciaVolumen(AsistenciaTeVolumenSDTOP6 asistenciaVolumen) {
        this.asistenciaVolumen = asistenciaVolumen;
    }



    
}
