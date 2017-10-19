package org.probosque.ejecutivodto;

import java.util.List;

public class EjecutivoPrograma11 {

    public EjecutivoPrograma11() {
    }

    public EjecutivoPrograma11(List<TipoApoyoDTOP11> tipoApoyo, List<CostoApoyoDTOP11> costoApoyo, AportacionesDTOP11 aportaciones, List<ActivoDTOP11> activo) {
        this.tipoApoyo = tipoApoyo;
        this.costoApoyo = costoApoyo;
        this.aportaciones = aportaciones;
        this.activo = activo;
    }
    List<TipoApoyoDTOP11> tipoApoyo;
    List<CostoApoyoDTOP11> costoApoyo;
    AportacionesDTOP11 aportaciones;
    List<ActivoDTOP11> activo;

    public List<TipoApoyoDTOP11> getTipoApoyo() {
        return tipoApoyo;
    }

    public void setTipoApoyo(List<TipoApoyoDTOP11> tipoApoyo) {
        this.tipoApoyo = tipoApoyo;
    }

    public List<CostoApoyoDTOP11> getCostoApoyo() {
        return costoApoyo;
    }

    public void setCostoApoyo(List<CostoApoyoDTOP11> costoApoyo) {
        this.costoApoyo = costoApoyo;
    }

    public AportacionesDTOP11 getAportaciones() {
        return aportaciones;
    }

    public void setAportaciones(AportacionesDTOP11 aportaciones) {
        this.aportaciones = aportaciones;
    }

    public List<ActivoDTOP11> getActivo() {
        return activo;
    }

    public void setActivo(List<ActivoDTOP11> activo) {
        this.activo = activo;
    }
    
}
