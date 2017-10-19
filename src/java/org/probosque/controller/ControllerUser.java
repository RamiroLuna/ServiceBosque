package org.probosque.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.UserDAO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.DataLoginJson;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de información referente a los usuarios de todos los programas
 * @author admin
 */

public class ControllerUser {
    
    public OutputJson getUser(HttpServletRequest request) throws Exception {
        String _id = request.getParameter("id");
        OutputJson output = new OutputJson();
        DataLoginJson data = new DataLoginJson();
        ResponseJson response = new ResponseJson();
        UserDAO dao = new UserDAO();
        UserDTO userDto = dao.getUser(Integer.parseInt(_id));
        if (userDto != null) {
            output.setData(userDto);
            response.setSucessfull(true);
            response.setMessage("OK");
        } else {
            response.setSucessfull(false);
            response.setMessage("El usuario no existe");
        }
        output.setResponse(response);
        return output;
    }
    
    public OutputJson getUsers(HttpServletRequest request) throws Exception {
        OutputJson output = new OutputJson();
        DataLoginJson data = new DataLoginJson();
        ResponseJson response = new ResponseJson();
        UserDAO dao = new UserDAO();
        List<UserDTO> users = dao.getUsers();
        if (!users.isEmpty()) {
            output.setData(users);
            response.setSucessfull(true);
            response.setMessage("OK");
        } else {
            response.setSucessfull(false);
            response.setMessage("No existen usuarios");
        }
        output.setResponse(response);
        return output;
    }
    
    public OutputJson setUser(HttpServletRequest request) throws SQLException, IOException {
        
        String _json = request.getParameter("json");
        
        Gson gson = new Gson();
        
        UserDTO userJson = gson.fromJson(_json, UserDTO.class);   
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        UserDAO dao = new UserDAO();
        try {
            if(userJson.isNew()==0){
                if(dao.getUser(userJson.getUsername())==null){
                    dao.insertUser(userJson);
                    response.setSucessfull(true);
                    response.setMessage("Usuario creado satisfactoriamente");
                }
                else{
                    response.setSucessfull(false);
                    response.setMessage("El usuario ya existe");
                }
            }
            else{
                dao.updateUser(userJson);
                response.setSucessfull(true);
                response.setMessage("Los cambios han sido guardados");
            }
        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        finally{
            output.setResponse(response);
        }
        return output;
    }
    
    public OutputJson deleteUser(HttpServletRequest request) throws SQLException, IOException {
        String _id = request.getParameter("id");
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        try {
            UserDAO dao = new UserDAO();
            dao.deleteUser(Integer.parseInt(_id));
            response.setSucessfull(true);
            response.setMessage("Usuario eliminado satisfactoriamente");

        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage("El usuario no se pudo eliminar");
        }
        output.setResponse(response);
        return output;
    }
    
    public OutputJson editUser(HttpServletRequest request) throws SQLException, IOException {
        
        String _json = request.getParameter("json");
        int id = Integer.parseInt(request.getParameter("id"));
        Gson gson = new Gson();
        
        UserDTO userJson = gson.fromJson(_json, UserDTO.class);   
        userJson.setId(id);
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        UserDAO dao = new UserDAO();
        try {
                dao.updateUser(userJson);
                response.setSucessfull(true);
                response.setMessage("Los cambios han sido guardados");
            
        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        finally{
            output.setResponse(response);
        }
        return output;
    }
    
    
    
    
    
    
    public OutputJson findUsers(HttpServletRequest request) throws Exception {
        String _key = request.getParameter("key");
        OutputJson output = new OutputJson();
        DataLoginJson data = new DataLoginJson();
        ResponseJson response = new ResponseJson();
        UserDAO dao = new UserDAO();
        List<UserDTO> users = dao.findUsers(_key);
        if (!users.isEmpty()) {
            output.setData(users);
            response.setSucessfull(true);
            response.setMessage("OK");
        } else {
            response.setSucessfull(false);
            response.setMessage("No existen usuarios");
        }
        output.setResponse(response);
        return output;
    }

}