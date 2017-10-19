package org.probosque.dto;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class PadronDTO {

    public PadronDTO() {
    }



public String idPadron;
public String nombre;
public String razonSocial;
public String rfc;
public String rnf;
public String calle;
public String noExterior;
public String noInterior;
public String colonia;
public String localidad;
public String municipio;
public String telefono;
public String correo;
public String latitud;
public String longitud;
public String tipo;
public String actividad;
public String fechaAlta;
public String observaciones;
public String cve_localidad;
public String cve_reg;
public String cve_mpio;
/********INICIO CÓDIGO EDGA NUEVOS CAMPOS DE CONSULTA 10/07/17*************/
public String fecha_atp;
public String atp;

/********FIN CÓDIGO EDGA NUEVOS CAMPOS DE CONSULTA 10/07/17*************/




@SerializedName("datos")
public ArrayList<PadronDTO> padron;

    public List<PadronDTO> getPadron() {
        return padron;
    }

    public void setPadron(ArrayList<PadronDTO> padron) {
        this.padron = padron;
    }

    public String getIdPadron() {
        return idPadron;
    }

    public void setIdPadron(String idPadron) {
        this.idPadron = idPadron;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRnf() {
        return rnf;
    }

    public void setRnf(String rnf) {
        this.rnf = rnf;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNoExterior() {
        return noExterior;
    }

    public void setNoExterior(String noExterior) {
        this.noExterior = noExterior;
    }

    public String getNoInterior() {
        return noInterior;
    }

    public void setNoInterior(String noInterior) {
        this.noInterior = noInterior;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
     public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getCve_localidad() {
        return cve_localidad;
    }

    public void setCve_localidad(String cve_localidad) {
        this.cve_localidad = cve_localidad;
    }

    public String getCve_reg() {
        return cve_reg;
    }

    public void setCve_reg(String cve_reg) {
        this.cve_reg = cve_reg;
    }

    public String getCve_mpio() {
        return cve_mpio;
    }

    public void setCve_mpio(String cve_mpio) {
        this.cve_mpio = cve_mpio;
    }
    public String getFecha_atp() {
        return fecha_atp;
    }

    public void setFecha_atp(String fecha_atp) {
        this.fecha_atp = fecha_atp;
    }

    public String getAtp() {
        return atp;
    }

    public void setAtp(String atp) {
        this.atp = atp;
    }

}
