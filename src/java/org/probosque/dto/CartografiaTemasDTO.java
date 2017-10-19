package org.probosque.dto;

/**
 *
 * @author Administrador
 */
public class CartografiaTemasDTO {

    public CartografiaTemasDTO(){
        
    }

    String gid;
    String descripcion;
    String folio;
    String programa;

    public CartografiaTemasDTO(String gid, String descripcion, String folio, String programa) {
        this.gid = gid;
        this.descripcion = descripcion;
        this.folio = folio;
        this.programa = programa;
    }
    
    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }
    
}