package org.probosque.model.json;

import java.util.ArrayList;
import org.probosque.dto.ReportPredioDTO;

public class ReportPrediosRowJson {
    
    private String anio;
    private ArrayList<ReportPredioDTO> data;

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public ArrayList<ReportPredioDTO> getData() {
        return data;
    }

    public void setData(ArrayList<ReportPredioDTO> data) {
        this.data = data;
    }
    
}