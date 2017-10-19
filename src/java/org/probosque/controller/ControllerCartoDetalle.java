/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.CartoDetalleDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author Telyco
 */
public class ControllerCartoDetalle {
    public OutputJson obtenDatosCartoDetalle(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        CartoDetalleDAO dao = new CartoDetalleDAO();

        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
    
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        output.setData(dao.ObtenerDatosCartoDetalle(user, folio));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson obtenDatosCartoDetalleAdmin(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        CartoDetalleDAO dao = new CartoDetalleDAO();

        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String programa = request.getParameter("programa");
    
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        output.setData(dao.ObtenerDatosCartoDetalleAdmin(user, folio, programa));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
}
