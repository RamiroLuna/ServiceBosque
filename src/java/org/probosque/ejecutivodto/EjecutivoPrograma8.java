package org.probosque.ejecutivodto;

public class EjecutivoPrograma8 {

    public EjecutivoPrograma8() {
    }

    public EjecutivoPrograma8(TipoAccionDTOP8 accion, DocumentacionGeneralDTOP8 documentacionGeneral, ObservacionesProductosGeneralDTOP8 observacionesGeneral, ParticipantesGeneralDTOP8 particpantes, PersonasAseguradasGeneralDTOP8 personas) {
        this.accion = accion;
        this.documentacionGeneral = documentacionGeneral;
        this.observacionesGeneral = observacionesGeneral;
        this.particpantes = particpantes;
        this.personas = personas;
    }
    
    TipoAccionDTOP8 accion;
    DocumentacionGeneralDTOP8 documentacionGeneral;
    ObservacionesProductosGeneralDTOP8 observacionesGeneral;
    ParticipantesGeneralDTOP8 particpantes;
    PersonasAseguradasGeneralDTOP8 personas;

    public TipoAccionDTOP8 getAccion() {
        return accion;
    }

    public void setAccion(TipoAccionDTOP8 accion) {
        this.accion = accion;
    }

    public DocumentacionGeneralDTOP8 getDocumentacionGeneral() {
        return documentacionGeneral;
    }

    public void setDocumentacionGeneral(DocumentacionGeneralDTOP8 documentacionGeneral) {
        this.documentacionGeneral = documentacionGeneral;
    }

    public ObservacionesProductosGeneralDTOP8 getObservacionesGeneral() {
        return observacionesGeneral;
    }

    public void setObservacionesGeneral(ObservacionesProductosGeneralDTOP8 observacionesGeneral) {
        this.observacionesGeneral = observacionesGeneral;
    }

    public ParticipantesGeneralDTOP8 getParticpantes() {
        return particpantes;
    }

    public void setParticpantes(ParticipantesGeneralDTOP8 particpantes) {
        this.particpantes = particpantes;
    }

    public PersonasAseguradasGeneralDTOP8 getPersonas() {
        return personas;
    }

    public void setPersonas(PersonasAseguradasGeneralDTOP8 personas) {
        this.personas = personas;
    }
    
}
