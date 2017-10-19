/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dao.CartoAdminDAO;
import org.probosque.dao.PoolDataSource;
import org.probosque.dao.UserDAO;
import org.probosque.dto.CartoTemasDTO;
import org.probosque.dto.ResultString;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author Administrador
 */
public class ControllerCartoAdmin {
    
    
    public OutputJson insertMapa(HttpServletRequest request) throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        CartoAdminDAO dao = new CartoAdminDAO();
  
        String _usuario = request.getParameter("usuario");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.insertMapa(user));
        response.setSucessfull(true);
        response.setMessage("Mapa creado");
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson getFiguraType(HttpServletRequest request) throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        CartoAdminDAO dao = new CartoAdminDAO();
  
        String _usuario = request.getParameter("usuario");
        String _tema1 = request.getParameter("tema");
        String _tema = _tema1.replaceAll("[^\\dA-Za-z ]", "");
        String _programa = request.getParameter("programa");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.getFiguraType(user, _tema, _programa));
        response.setSucessfull(true);
        response.setMessage("Tipo de figura obtenido");
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson recuperacionPopUp(HttpServletRequest request) throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        CartoAdminDAO dao = new CartoAdminDAO();
  
        String _usuario = request.getParameter("usuario");
        String _figura = request.getParameter("figura");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.recuperacionPopUp(user, _figura));
        response.setSucessfull(true);
        response.setMessage("Recuperación exitosa");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson insertTemaAdmin(HttpServletRequest request) throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        CartoAdminDAO dao = new CartoAdminDAO();
  
        String _usuario = request.getParameter("usuario");
        String _tema1 = request.getParameter("tema");
        String _tema = _tema1.replaceAll("[^\\dA-Za-z ]", "");
        String _programa = request.getParameter("programa");
        String _figuraType = request.getParameter("figuraType");
        String _colorTema = request.getParameter("colorTema");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.insertTemaAdmin(user, _tema, _programa, _figuraType, _colorTema));
        response.setSucessfull(true);
        response.setMessage("Tema insertado");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson getListTemas(HttpServletRequest request)throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        CartoAdminDAO dao = new CartoAdminDAO();
        
        String _usuario = request.getParameter("usuario");
        String _idMapa = request.getParameter("idMapa");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.getListTemas(user, _idMapa));
        response.setSucessfull(true);
        response.setMessage("Lista de temas obtenidos correctamente");
        output.setResponse(response);
        return output;
    }
    
    
    
    public OutputJson getListIndicadores(HttpServletRequest request)throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        CartoAdminDAO dao = new CartoAdminDAO();
        
        String _usuario = request.getParameter("usuario");
        String _idMapa = request.getParameter("idMapa");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.getListIndicadores(user, _idMapa));
        response.setSucessfull(true);
        response.setMessage("Lista de indicdores obtenida correctamente");
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson getListMapas(HttpServletRequest request)throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        CartoAdminDAO dao = new CartoAdminDAO();
        
        String _usuario = request.getParameter("usuario");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.getListMapas(user));
        response.setSucessfull(true);
        response.setMessage("Lista de mapas obtenida correctamente");
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson eliminarTemas(HttpServletRequest request)throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        CartoAdminDAO dao = new CartoAdminDAO();
        
        String _usuario = request.getParameter("usuario");
        String _tema = request.getParameter("tema");
        String _programa = request.getParameter("programa");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.eliminarTemas(user, _tema, _programa));
        response.setSucessfull(true);
        response.setMessage("Tema eliminado");
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson guardarMapa(HttpServletRequest request)throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        CartoAdminDAO dao = new CartoAdminDAO();
        
        String _usuario = request.getParameter("usuario");
        String _nombreMapa = request.getParameter("nombreMapa");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.guardarMapa(user, _nombreMapa));
        response.setSucessfull(true);
        response.setMessage("Mapa guardado correctamente");
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson eliminarMapas(HttpServletRequest request)throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        CartoAdminDAO dao = new CartoAdminDAO();
        
        String _usuario = request.getParameter("usuario");
        String _idMapa = request.getParameter("idMapa");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.eliminarMapas(user, _idMapa));
        response.setSucessfull(true);
        response.setMessage("Mapa eliminado");
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson insertIndicadores(HttpServletRequest request) throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        CartoAdminDAO dao = new CartoAdminDAO();
  
        String _usuario = request.getParameter("usuario");
        String _nombreIndicador = request.getParameter("nombreImagen");
        String _px = request.getParameter("px");
        String _py = request.getParameter("py");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.insertIndicadores(user, _nombreIndicador, _px, _py));
        response.setSucessfull(true);
        response.setMessage("Tema insertado");
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson eliminarIndicador(HttpServletRequest request)throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        CartoAdminDAO dao = new CartoAdminDAO();
        
        String _usuario = request.getParameter("usuario");
        String _idIndicador = request.getParameter("idIndicador");
        String _idMapa = request.getParameter("idMapa");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.eliminarIndicador(user, _idIndicador, _idMapa));
        response.setSucessfull(true);
        response.setMessage("Tema eliminado");
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson pintarMapaTemas(HttpServletRequest request)throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        CartoAdminDAO dao = new CartoAdminDAO();
        
        String _usuario = request.getParameter("usuario");
        String _idMapa = request.getParameter("idMapa");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.pintarMapaTemas(user, _idMapa));
        response.setSucessfull(true);
        response.setMessage("Temas del mapa obtenidos correctamente");
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson pintarMapaIndicador(HttpServletRequest request)throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        CartoAdminDAO dao = new CartoAdminDAO();
        
        String _usuario = request.getParameter("usuario");
        String _idMapa = request.getParameter("idMapa");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.pintarMapaIndicador(user, _idMapa));
        response.setSucessfull(true);
        response.setMessage("Indicador del mapa obtenidos correctamente");
        output.setResponse(response);
        return output;
    }
}