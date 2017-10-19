package org.probosque.ejecutivodto;
public class TotalDTOP71 {
 
 public String numincendio;
 public String duracion;
 public String observacion;
 public String total;   
 public String renuevo;
 public String arbolado_adulto;   
 public String arbusto;
 public String pasto;

public String getNumincendio() {
        return numincendio;
    }

    public void setNumincendio(String numincendio) {
        this.numincendio = numincendio;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
 
  public String getRenuevo() {
        return renuevo;
    }

    public void setRenuevo(String renuevo) {
        this.renuevo = renuevo;
    }

    public String getArbolado_adulto() {
        return arbolado_adulto;
    }

    public void setArbolado_adulto(String arbolado_adulto) {
        this.arbolado_adulto = arbolado_adulto;
    }

    public String getArbusto() {
        return arbusto;
    }

    public void setArbusto(String arbusto) {
        this.arbusto = arbusto;
    }

    public String getPasto() {
        return pasto;
    }

    public void setPasto(String pasto) {
        this.pasto = pasto;
    }

    public String getCombatientes() {
        return combatientes;
    }

    public void setCombatientes(String combatientes) {
        this.combatientes = combatientes;
    }
 public String combatientes;       

    public TotalDTOP71(String numincendio,String total, String renuevo, String arbolado_adulto, String arbusto, String pasto, String combatientes, String duracion, String observacion) {
        this.numincendio=numincendio;
        this.total = total;
        this.renuevo = renuevo;
        this.arbolado_adulto = arbolado_adulto;
        this.arbusto = arbusto;
        this.pasto = pasto;
        this.combatientes = combatientes;
        this.duracion=duracion;
        this.observacion=observacion;
    }
    
    
    
    public TotalDTOP71() {
    }

    
    
    public TotalDTOP71(String total) {
        this.total = total;
    }
 

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
