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
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de sumatorias de determinado programa
 * @author admin
 */

public class ControllerSum {

    public OutputJson getSum(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        TableDAO dao = new TableDAO();

        String _user = request.getParameter("user");
        String _folio = request.getParameter("folio");
        
        String _destiny = request.getParameter("destiny");
        String _service = request.getParameter("service");
        String _subtable = request.getParameter("subtable");
        String _field = request.getParameter("field");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));

        output.setData(dao.getSum(user, _subtable, _field, _folio));
        response.setSucessfull(true);
        response.setMessage("Datos obtenidos correctamente");
        output.setResponse(response);
        return output;

    }
}
