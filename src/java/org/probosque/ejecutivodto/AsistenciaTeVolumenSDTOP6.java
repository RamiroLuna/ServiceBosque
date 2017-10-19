package org.probosque.ejecutivodto;
public class AsistenciaTeVolumenSDTOP6 {

    public AsistenciaTeVolumenSDTOP6() {
    }

    public AsistenciaTeVolumenSDTOP6(String volumen_saneado, String asistencia_tecnica_ha) {
        this.volumen_saneado = volumen_saneado;
        this.asistencia_tecnica_ha = asistencia_tecnica_ha;
    }

public String volumen_saneado;
public String asistencia_tecnica_ha;

    public String getVolumen_saneado() {
        return volumen_saneado;
    }

    public void setVolumen_saneado(String volumen_saneado) {
        this.volumen_saneado = volumen_saneado;
    }

    public String getAsistencia_tecnica_ha() {
        return asistencia_tecnica_ha;
    }

    public void setAsistencia_tecnica_ha(String asistencia_tecnica_ha) {
        this.asistencia_tecnica_ha = asistencia_tecnica_ha;
    }
}
