package org.probosque.ejecutivodto;

import java.util.List;

public class EjecutivoPrograma71 {

    public EjecutivoPrograma71() {
    }

    public EjecutivoPrograma71(List<TotalDTOP71> total,/* List<DuracionDTOP71> duracion,*/ List<ActividadCantidadDTOP72> actividadCantidad, CantidadBrechaDTOP72 brecha, List<ObservacionesBrechaDTOP72> observacionesBrecha, List<ActividadCantidadQuemaDTOP72> actividadCantidadQuema, CantidadQuemaDTOP72 CantidadQuema, List<ObservacionesQuemaDTOP72> observacionesQuema) {
        this.total = total;
        //this.duracion = duracion;
        this.actividadCantidad = actividadCantidad;
        this.brecha = brecha;
        this.observacionesBrecha = observacionesBrecha;
        this.actividadCantidadQuema = actividadCantidadQuema;
        this.CantidadQuema = CantidadQuema;
        this.observacionesQuema = observacionesQuema;
    }
List<TotalDTOP71> total;
List<DuracionDTOP71> duracion;
List<ActividadCantidadDTOP72> actividadCantidad;
CantidadBrechaDTOP72 brecha;
List<ObservacionesBrechaDTOP72> observacionesBrecha;
List<ActividadCantidadQuemaDTOP72> actividadCantidadQuema;
CantidadQuemaDTOP72 CantidadQuema;
List<ObservacionesQuemaDTOP72> observacionesQuema;

    public List<TotalDTOP71> getTotal() {
        return total;
    }

    public void setTotal(List<TotalDTOP71> total) {
        this.total = total;
    }

    public List<DuracionDTOP71> getDuracion() {
        return duracion;
    }

    public void setDuracion(List<DuracionDTOP71> duracion) {
        this.duracion = duracion;
    }

    public List<ActividadCantidadDTOP72> getActividadCantidad() {
        return actividadCantidad;
    }

    public void setActividadCantidad(List<ActividadCantidadDTOP72> actividadCantidad) {
        this.actividadCantidad = actividadCantidad;
    }

    public CantidadBrechaDTOP72 getBrecha() {
        return brecha;
    }

    public void setBrecha(CantidadBrechaDTOP72 brecha) {
        this.brecha = brecha;
    }

    public List<ObservacionesBrechaDTOP72> getObservacionesBrecha() {
        return observacionesBrecha;
    }

    public void setObservacionesBrecha(List<ObservacionesBrechaDTOP72> observacionesBrecha) {
        this.observacionesBrecha = observacionesBrecha;
    }

    public List<ActividadCantidadQuemaDTOP72> getActividadCantidadQuema() {
        return actividadCantidadQuema;
    }

    public void setActividadCantidadQuema(List<ActividadCantidadQuemaDTOP72> actividadCantidadQuema) {
        this.actividadCantidadQuema = actividadCantidadQuema;
    }

    public CantidadQuemaDTOP72 getCantidadQuema() {
        return CantidadQuema;
    }

    public void setCantidadQuema(CantidadQuemaDTOP72 CantidadQuema) {
        this.CantidadQuema = CantidadQuema;
    }

    public List<ObservacionesQuemaDTOP72> getObservacionesQuema() {
        return observacionesQuema;
    }

    public void setObservacionesQuema(List<ObservacionesQuemaDTOP72> observacionesQuema) {
        this.observacionesQuema = observacionesQuema;
    }
}
