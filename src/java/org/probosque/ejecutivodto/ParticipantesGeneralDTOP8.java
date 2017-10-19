package org.probosque.ejecutivodto;

import java.util.List;

public class ParticipantesGeneralDTOP8 {

    public ParticipantesGeneralDTOP8() {
    }

    public ParticipantesGeneralDTOP8(List<ParticipantesDetalleDTOP8> particiapntesOperativos, List<ParticipantesDetalleDTOP8> particiapntesTransporte, List<ParticipantesDetalleDTOP8> particiapntesIndustria, List<ParticipantesDetalleDTOP8> particiapntesPredios, List<ParticipantesDetalleDTOP8> particiapntesPericiales) {
        this.particiapntesOperativos = particiapntesOperativos;
        this.particiapntesTransporte = particiapntesTransporte;
        this.particiapntesIndustria = particiapntesIndustria;
        this.particiapntesPredios = particiapntesPredios;
        this.particiapntesPericiales = particiapntesPericiales;
    }

    
    
List<ParticipantesDetalleDTOP8> particiapntesOperativos;
List<ParticipantesDetalleDTOP8> particiapntesTransporte;
List<ParticipantesDetalleDTOP8> particiapntesIndustria;
List<ParticipantesDetalleDTOP8> particiapntesPredios;
List<ParticipantesDetalleDTOP8> particiapntesPericiales;

    public List<ParticipantesDetalleDTOP8> getParticiapntesOperativos() {
        return particiapntesOperativos;
    }

    public void setParticiapntesOperativos(List<ParticipantesDetalleDTOP8> particiapntesOperativos) {
        this.particiapntesOperativos = particiapntesOperativos;
    }

    public List<ParticipantesDetalleDTOP8> getParticiapntesTransporte() {
        return particiapntesTransporte;
    }

    public void setParticiapntesTransporte(List<ParticipantesDetalleDTOP8> particiapntesTransporte) {
        this.particiapntesTransporte = particiapntesTransporte;
    }

    public List<ParticipantesDetalleDTOP8> getParticiapntesIndustria() {
        return particiapntesIndustria;
    }

    public void setParticiapntesIndustria(List<ParticipantesDetalleDTOP8> particiapntesIndustria) {
        this.particiapntesIndustria = particiapntesIndustria;
    }

    public List<ParticipantesDetalleDTOP8> getParticiapntesPredios() {
        return particiapntesPredios;
    }

    public void setParticiapntesPredios(List<ParticipantesDetalleDTOP8> particiapntesPredios) {
        this.particiapntesPredios = particiapntesPredios;
    }

    public List<ParticipantesDetalleDTOP8> getParticiapntesPericiales() {
        return particiapntesPericiales;
    }

    public void setParticiapntesPericiales(List<ParticipantesDetalleDTOP8> particiapntesPericiales) {
        this.particiapntesPericiales = particiapntesPericiales;
    }

    


    
}
