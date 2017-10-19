/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Telyco
 */
public class InfoMultiregistroDTO {
    private String Label;
    private String Nombre;

    public InfoMultiregistroDTO() {
        
    }
    private List<String> Info = new ArrayList();
    
    
    
    public InfoMultiregistroDTO(String Label, String Nombre, List<String> repo) {
        this.Label = Label;
        this.Nombre = Nombre;
        this.Info=repo;
    }


    public String getLabel() {
        return Label;
    }

    public String getNombre() {
        return Nombre;
    }

    public List<String> getInfo() {
        return Info;
    }

    public void setLabel(String Label) {
        this.Label = Label;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setInfo(ArrayList<String> Info) {
        this.Info = Info;
    }  
    
}
