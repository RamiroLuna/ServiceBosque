package org.probosque.ejecutivodto;

public class ParticipantesDetalleDTOP8 {

    public ParticipantesDetalleDTOP8() {
    }

    public ParticipantesDetalleDTOP8(String dependencia, String no_participantes) {
        this.dependencia = dependencia;
        this.no_participantes = no_participantes;
    }
public String  dependencia;
public String no_participantes;

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getNo_participantes() {
        return no_participantes;
    }

    public void setNo_participantes(String no_participantes) {
        this.no_participantes = no_participantes;
    }
}
