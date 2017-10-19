/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.MultiregistroDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author Telyco
 */
public class ControllerMultiregistro {
        public OutputJson obtenDatosMultiregistro(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        MultiregistroDAO dao = new MultiregistroDAO();

        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String multi = request.getParameter("multi");
        String consulta = request.getParameter("consul");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
            output.setData(dao.ObtenerDatosMiltiregistro(user, folio, multi, consulta));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
        
        
    public OutputJson obtenDatosMultiregistroAdmin(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        MultiregistroDAO dao = new MultiregistroDAO();

        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String programa = request.getParameter("programa");
        String multi = request.getParameter("multi");
        String consulta = request.getParameter("consul");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
            output.setData(dao.ObtenerDatosMiltiregistroAdmin(user, folio, programa, multi, consulta));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
}
