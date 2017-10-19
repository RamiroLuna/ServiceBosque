package org.probosque.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.RegionDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.RegionesJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de información de regiones geográficas
 * @author admin
 */
public class ControllerRegiones {

    public RegionesJson getRegiones(HttpServletRequest request) throws SQLException, Exception {
        String _user = request.getParameter("user");        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        
        RegionDAO dao = new RegionDAO();
        RegionesJson regionesJson = new RegionesJson();
        regionesJson.setFeatures(dao.getRegiones(user));
        return regionesJson;
    }
}