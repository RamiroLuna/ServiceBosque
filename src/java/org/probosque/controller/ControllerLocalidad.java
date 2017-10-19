package org.probosque.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.LocalidadDAO;
import org.probosque.model.json.LocalidadesJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de información de localidades
 * @author admin
 */

public class ControllerLocalidad {

    public LocalidadesJson getLocalidades(HttpServletRequest request) throws SQLException, Exception {
        int municipio = Integer.parseInt(request.getParameter("modulopredio_municipio")!=null?request.getParameter("modulopredio_municipio"):request.getParameter("municipio"));   
        LocalidadDAO dao = new LocalidadDAO();
        LocalidadesJson localidadesJson = new LocalidadesJson();
        localidadesJson.setList(dao.getLocalidades(municipio));
        return localidadesJson;
    }
}