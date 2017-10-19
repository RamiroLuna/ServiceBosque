package org.probosque.controller;

import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.CSVDataDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de información CSV
 * @author admin
 */

public class ControllerCSV {

    public OutputJson getReport(HttpServletRequest request) {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        try {
            String _user = request.getParameter("user");        
            String _json = request.getParameter("json");

            CSVDataDAO dao = new CSVDataDAO();
            response.setSucessfull(true);
            response.setMessage("OK");

            UserDAO userDao = new UserDAO();
            UserDTO userDto = userDao.getUser(Integer.parseInt(_user));
            output.setData(dao.insert(userDto, _json));
        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;
    }
}