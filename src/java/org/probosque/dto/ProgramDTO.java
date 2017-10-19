package org.probosque.dto;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ProgramDTO {

   

    public ProgramDTO() {
    }

public String id;
public int activity;
public int idProgram;       
@SerializedName("programas")
public ArrayList<ProgramDTO> Program;


 public ProgramDTO(String id, int activity, int idProgram) {
        this.id = id;
        this.activity = activity;
        this.idProgram = idProgram;
    }

    public ArrayList<ProgramDTO> getProgram() {
        return Program;
    }

    public void setProgram(ArrayList<ProgramDTO> Program) {
        this.Program = Program;
    }
        
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }


    
}
