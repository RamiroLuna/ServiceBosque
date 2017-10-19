package org.probosque.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.TableDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de folios para cada uno de los formularios
 * @author admin
 */
public class ControllerFolio {

    public OutputJson getFolio(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        TableDAO dao = new TableDAO();

        String _user = request.getParameter("user");
        String anio = request.getParameter("anio");
        String idRegion = request.getParameter("id_region");
        String _activity = request.getParameter("activity");
        idRegion = "0" + idRegion;

        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));

        output.setData(dao.getFolio(user, _activity, anio, idRegion));
        response.setSucessfull(true);
        response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;

    }
}
