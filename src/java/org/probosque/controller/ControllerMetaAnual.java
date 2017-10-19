/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.controller;

import java.io.IOException;
import java.sql.SQLException;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;
import org.probosque.dao.TableDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.UserDTO;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author Jonathan
 */
public class ControllerMetaAnual {
     
    public OutputJson obtenMetaAnual(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        TableDAO dao = new TableDAO();

        String _user = request.getParameter("user");
    
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        output.setData(dao.ObtenerRegionMeta(user));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson obtenDatosMetaAnual(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        TableDAO dao = new TableDAO();

        String _user = request.getParameter("user");
    
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        output.setData(dao.ObtenerDatosMeta(user));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson insertaDatosMetaAnual(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        TableDAO dao = new TableDAO();

        String _user = request.getParameter("user");
        String id_region = request.getParameter("id_region");
        String anio = request.getParameter("anio");
        String meta_anual = request.getParameter("meta_anual");
    
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        output.setData(dao.InsertDatosMeta(user, Integer.parseInt(id_region), anio, meta_anual));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson obtenMetaAnualbyId(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        TableDAO dao = new TableDAO();

        String _user = request.getParameter("user");
        String id_meta_anual = request.getParameter("id_meta_anual");
    
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        output.setData(dao.ObtenerDatosnMetabyID(user, Integer.parseInt(id_meta_anual)));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson actualizaDatosMetaAnual(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        TableDAO dao = new TableDAO();

        String _user = request.getParameter("user");
        String id_region = request.getParameter("id_region");
        String anio = request.getParameter("anio");
        String meta_anual = request.getParameter("meta_anual");
        String id_meta_anual = request.getParameter("id_meta_anual");
    
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        output.setData(dao.ActualizaDatosMeta(user, Integer.parseInt(id_region), anio, meta_anual, Integer.parseInt(id_meta_anual)));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson eliminaDatosMetaAnual(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        TableDAO dao = new TableDAO();

        String _user = request.getParameter("user");
        String id_meta_anual = request.getParameter("id_meta_anual");
    
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        output.setData(dao.EliminaDatosMeta(user, Integer.parseInt(id_meta_anual)));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
}
