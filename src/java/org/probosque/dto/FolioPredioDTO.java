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
public class FolioPredioDTO {
    
    public FolioPredioDTO() {
    }

    public FolioPredioDTO(String folio, String nombrePredio) {
        this.folio = folio;
        this.nombrePredio = nombrePredio;
    }

    String folio;
    String nombrePredio;

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getNombrePredio() {
        return nombrePredio;
    }

    public void setNombrePredio(String nombrePredio) {
        this.nombrePredio = nombrePredio;
    }
    
}
