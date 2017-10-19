package org.probosque.ejecutivodto;

public class FechaPlagaNotVolDTOP6 {

    public FechaPlagaNotVolDTOP6() {
    }

    public FechaPlagaNotVolDTOP6(String fecha_visita, String plaga_enfermedad, String hospedero, String num_notificacion, String superficie_notificada, String volumen_notificado, String vigencia) {
        this.fecha_visita = fecha_visita;
        this.plaga_enfermedad = plaga_enfermedad;
        this.hospedero = hospedero;
        this.num_notificacion = num_notificacion;
        this.superficie_notificada = superficie_notificada;
        this.volumen_notificado = volumen_notificado;
        this.vigencia = vigencia;
    }

public String  fecha_visita;
public String plaga_enfermedad;
public String hospedero;
public String num_notificacion;
public String superficie_notificada;
public String  volumen_notificado;
public String vigencia;

    public String getFecha_visita() {
        return fecha_visita;
    }

    public void setFecha_visita(String fecha_visita) {
        this.fecha_visita = fecha_visita;
    }

    public String getPlaga_enfermedad() {
        return plaga_enfermedad;
    }

    public void setPlaga_enfermedad(String plaga_enfermedad) {
        this.plaga_enfermedad = plaga_enfermedad;
    }

    public String getHospedero() {
        return hospedero;
    }

    public void setHospedero(String hospedero) {
        this.hospedero = hospedero;
    }

    public String getNum_notificacion() {
        return num_notificacion;
    }

    public void setNum_notificacion(String num_notificacion) {
        this.num_notificacion = num_notificacion;
    }

    public String getSuperficie_notificada() {
        return superficie_notificada;
    }

    public void setSuperficie_notificada(String superficie_notificada) {
        this.superficie_notificada = superficie_notificada;
    }

    public String getVolumen_notificado() {
        return volumen_notificado;
    }

    public void setVolumen_notificado(String volumen_notificado) {
        this.volumen_notificado = volumen_notificado;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }


    
}
