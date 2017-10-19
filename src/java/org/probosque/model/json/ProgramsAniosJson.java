package org.probosque.model.json;

import java.util.List;
import org.probosque.dto.CatalogoStringDTO;

public class ProgramsAniosJson {
    private ProgramsJson programs;
    private AniosJson anios;
    
    
    /*
    private List<CatalogoStringDTO> programs;
    private List<CatalogoStringDTO> anios;

    public List<CatalogoStringDTO> getPrograms() {
        return programs;
    }

    public void setPrograms(List<CatalogoStringDTO> programs) {
        this.programs = programs;
    }

    public List<CatalogoStringDTO> getAnios() {
        return anios;
    }

    public void setAnios(List<CatalogoStringDTO> anios) {
        this.anios = anios;
    }*/

    public ProgramsJson getPrograms() {
        return programs;
    }

    public void setPrograms(ProgramsJson programs) {
        this.programs = programs;
    }

    public AniosJson getAnios() {
        return anios;
    }

    public void setAnios(AniosJson anios) {
        this.anios = anios;
    }
    
    

}