package org.probosque.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dto.CatalogoStringDTO;
import org.probosque.model.json.AniosJson;
import org.probosque.model.json.ProgramsAniosJson;
import org.probosque.model.json.ProgramsJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de información de programas de acuerdo al año
 * @author admin
 */

public class ControllerPrediosPrograms {

    public ProgramsAniosJson getProgramsAnios(HttpServletRequest request) throws SQLException, Exception {
        ProgramsAniosJson programsAniosJson = new ProgramsAniosJson();
        
        List<CatalogoStringDTO> anios = new ArrayList<>();
        anios.add(new CatalogoStringDTO("2001","2001"));
        anios.add(new CatalogoStringDTO("2002","2002"));
        anios.add(new CatalogoStringDTO("2003","2003"));
        anios.add(new CatalogoStringDTO("2004","2004"));
        anios.add(new CatalogoStringDTO("2005","2005"));
        
        List<CatalogoStringDTO> programs = new ArrayList<>();
        programs.add(new CatalogoStringDTO("1","Estadística de Predios con Autorizaciones para el Aprovechamiento de Recursos Forestales Maderables"));
        programs.add(new CatalogoStringDTO("2","Programa de Asistencia Técnica a la Producción Forestal"));
        programs.add(new CatalogoStringDTO("3","Programa de Reforestación y Restauración Integral de Microcuencas"));
        programs.add(new CatalogoStringDTO("4","Programa de Conservación y Acondicionamiento de Suelos Forestales"));
        programs.add(new CatalogoStringDTO("5","Programa de Reconversión Productiva (Plantaciones Forestales Comerciales)"));
        programs.add(new CatalogoStringDTO("6","Programa de Sanidad Forestal"));
        
        ProgramsJson programsJson = new ProgramsJson();
        AniosJson aniosJson = new AniosJson();
        
        programsJson.setList(programs);
        aniosJson.setList(anios);
        
        programsAniosJson.setAnios(aniosJson);
        programsAniosJson.setPrograms(programsJson);
        return programsAniosJson;
    }
}