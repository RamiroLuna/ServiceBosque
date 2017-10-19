package org.probosque.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.MunicipioDAO;
import org.probosque.model.json.MunicipiosJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de información de municipios
 * @author admin
 */

public class ControllerMunicipio {

    public MunicipiosJson getMunicipios(HttpServletRequest request) throws SQLException, Exception {
        int region = Integer.parseInt(request.getParameter("region"));   
        MunicipioDAO dao = new MunicipioDAO();
        MunicipiosJson municipiosJson = new MunicipiosJson();
        municipiosJson.setList(dao.getMunicipios(region));
        return municipiosJson;
    }
}