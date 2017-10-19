package org.probosque.controller;

import com.google.gson.Gson;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.UserDAO;
import org.probosque.dao.VentanillaDAO;
import org.probosque.dao.VentanillaRegistroDAO;
import org.probosque.dto.UserDTO;
import org.probosque.dto.VentanillaDTO;
import org.probosque.dto.VentanillaRegistroDTO;

/**
 * Victor Porcayo Altamirano
 **/
public class ControllerVentanilla {
    public String getAll(HttpServletRequest request) throws Exception {        
        String _user = request.getParameter("user");
        String _folio = request.getParameter("folio");
        int _tipo = Integer.parseInt(request.getParameter("tipo"));
        String _action=request.getParameter("action");
        String regresa="";
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        Gson gson= new Gson();
switch(_action){
            case "get":{
                VentanillaDAO objetoCarga=new VentanillaDAO();
                List<VentanillaDTO> lista = objetoCarga.cargaPeticion(user,_tipo, _folio);
                regresa=gson.toJson(lista);
                break;
            }
            case "set":{
                VentanillaRegistroDAO objetoCarga=new VentanillaRegistroDAO();
                List<VentanillaRegistroDTO> lista = objetoCarga.cargaPeticion(user, _folio);
                regresa=gson.toJson(lista);
            break;
            }
        }                
        return regresa;        
    }
}
