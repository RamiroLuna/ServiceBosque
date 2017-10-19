/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dto;

/**
 *
 * @author Administrador
 */
public class CartoTemasDTO {
    
    public String tema;
    public String the_geom;

    public void setThe_geom(String the_geom) {
        this.the_geom = the_geom;
    }

    public String getThe_geom() {
        return the_geom;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getTema() {
        return tema;
    }

    public CartoTemasDTO() {
    }
    
}
