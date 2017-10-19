/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.ConsultaReforestemosDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author Administrador
 */
public class ControllerConsultaReforestemos {
    
    
    public OutputJson selectGeneral(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConsultaReforestemosDAO dao = new ConsultaReforestemosDAO();

        String _usuario = request.getParameter("usuario");
        String folio = request.getParameter("folio");

        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
       
        output.setData(dao.selectGeneral(user, folio));
        response.setSucessfull(true);
        response.setMessage("Tabla limpia correctamente");
        output.setResponse(response);
        return output;
 
    }
    
            
    public OutputJson personasAseguradas(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConsultaReforestemosDAO dao = new ConsultaReforestemosDAO();

        String _usuario = request.getParameter("usuario");
        String folio = request.getParameter("folio");

        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
       
        output.setData(dao.personasAseguradas(user, folio));
        response.setSucessfull(true);
        response.setMessage("Tabla limpia correctamente");
        output.setResponse(response);
        return output;
 
    }
    
    
    public OutputJson dependenciasParticipantes(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConsultaReforestemosDAO dao = new ConsultaReforestemosDAO();

        String _usuario = request.getParameter("usuario");
        String folio = request.getParameter("folio");

        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
       
        output.setData(dao.dependenciasParticipantes(user, folio));
        response.setSucessfull(true);
        response.setMessage("Tabla limpia correctamente");
        output.setResponse(response);
        return output;
 
    }
            
            
    public OutputJson vehiculosAsegurados(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConsultaReforestemosDAO dao = new ConsultaReforestemosDAO();

        String _usuario = request.getParameter("usuario");
        String folio = request.getParameter("folio");

        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
       
        output.setData(dao.vehiculosAsegurados(user, folio));
        response.setSucessfull(true);
        response.setMessage("Tabla limpia correctamente");
        output.setResponse(response);
        return output;
 
    }
            
            
    public OutputJson bienesAseguradosC(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConsultaReforestemosDAO dao = new ConsultaReforestemosDAO();

        String _usuario = request.getParameter("usuario");
        String folio = request.getParameter("folio");

        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
       
        output.setData(dao.bienesAseguradosC(user, folio));
        response.setSucessfull(true);
        response.setMessage("Tabla limpia correctamente");
        output.setResponse(response);
        return output;
 
    }
    
    
    public OutputJson bienesAseguradosL(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConsultaReforestemosDAO dao = new ConsultaReforestemosDAO();

        String _usuario = request.getParameter("usuario");
        String folio = request.getParameter("folio");
        String consecutivo = request.getParameter("consecutivo");

        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
       
        output.setData(dao.bienesAseguradosL(user, folio, consecutivo));
        response.setSucessfull(true);
        response.setMessage("Tabla limpia correctamente(user, folio");
        output.setResponse(response);
        return output;
 
    }
            
   
    public OutputJson prediosC(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConsultaReforestemosDAO dao = new ConsultaReforestemosDAO();

        String _usuario = request.getParameter("usuario");
        String folio = request.getParameter("folio");

        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
       
        output.setData(dao.prediosC(user, folio));
        response.setSucessfull(true);
        response.setMessage("Tabla limpia correctamente(user, folio");
        output.setResponse(response);
        return output;
 
    }
    
    
    public OutputJson prediosL(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConsultaReforestemosDAO dao = new ConsultaReforestemosDAO();

        String _usuario = request.getParameter("usuario");
        String folio = request.getParameter("folio");
        String consecutivo = request.getParameter("consecutivo");

        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
       
        output.setData(dao.prediosL(user, folio, consecutivo));
        response.setSucessfull(true);
        response.setMessage("Tabla limpia correctamente(user, folio");
        output.setResponse(response);
        return output;
 
    }
    
    
    public OutputJson archivos(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConsultaReforestemosDAO dao = new ConsultaReforestemosDAO();

        String _usuario = request.getParameter("usuario");
        String folio = request.getParameter("folio");

        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
       
        output.setData(dao.archivos(user, folio));
        response.setSucessfull(true);
        response.setMessage("Tabla limpia correctamente(user, folio");
        output.setResponse(response);
        return output;
 
    }
}
