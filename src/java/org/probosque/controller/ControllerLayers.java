package org.probosque.controller;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.LayersDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.LayerDTO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.DataLayersJson;
import org.probosque.model.json.ResponseJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de información de capas geográficas
 * @author admin
 */
public class ControllerLayers {

    public OutputJson getLayers(HttpServletRequest request) throws SQLException, Exception {
       
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        LayersDAO dao = new LayersDAO();
        
        String _user = request.getParameter("user");        
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            List<LayerDTO> layers = dao.getLayers(user);
            
            for(LayerDTO layer : layers){
                layer.setWkt(dao.getWkt(user, layer.getLabel()));
            }
            
            DataLayersJson data = new DataLayersJson();
            data.setLayers(layers);
            data.setThemes(dao.getThemes(user));
            
            response.setSucessfull(true);
            response.setMessage("valido");
            output.setData(data);
        
        output.setResponse(response);
        return output;
    }
    
    public OutputJson deleteLayer(HttpServletRequest request) throws SQLException, Exception {
       
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        LayersDAO dao = new LayersDAO();

        String layerName = request.getParameter("layername");

        String _user = request.getParameter("user");
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));

        dao.deleteLayer(user, layerName);
        response.setSucessfull(true);
        response.setMessage("Se ha eliminado la capa " + layerName);
        output.setResponse(response);
        return output;
    }
}