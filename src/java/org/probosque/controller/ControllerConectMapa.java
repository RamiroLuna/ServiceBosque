package org.probosque.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dao.PoolDataSource;
import org.probosque.dao.ReporteadorDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dao.ConectMapaDAO;
import org.probosque.dto.CartografiaTemasDTO;
import org.probosque.dto.CartografiaTemasDosDTO;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.ResultString;
import org.probosque.dto.TemasDTO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author Administrador
 */
public class ControllerConectMapa {
    
    
    public OutputJson cargarTemas(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();

        String _usuario = request.getParameter("usuario");

        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));

        output.setData(dao.cargarTemas(user));
        response.setSucessfull(true);
        response.setMessage("Temas obtenidos correctamente");
        output.setResponse(response);
        return output;

    }
    
    
    
    public OutputJson mostrarTemasPoligonos(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();
        
        
        String _usuario = request.getParameter("usuario");
        String _tema1 = request.getParameter("tema");
        String _tema = _tema1.replaceAll("[^\\dA-Za-z ]", "");
        
       
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
                                                                                            
       
        output.setData(dao.mostrarTemasPoligonos(user, _tema));
        response.setSucessfull(true);
        response.setMessage("Figuras obtenidas correctamente");
        output.setResponse(response);
        return output;
        
    }
    
    
    
    public OutputJson conseguirTipoFigura(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();
        
        String _usuario = request.getParameter("usuario");
        String _tema1 = request.getParameter("tema");
        String _tema = _tema1.replaceAll("[^\\dA-Za-z ]", "");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.conseguirTipoFIgura(user, _tema));
        response.setSucessfull(true);
        response.setMessage("Tipo de la figura obtenido correctamente");
        output.setResponse(response);
        return output;

    }
    
    
    
    public OutputJson conseguirFolio(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();
        
        String _usuario = request.getParameter("usuario");
        String _tema1 = request.getParameter("tema");
        String _tema = _tema1.replaceAll("[^\\dA-Za-z ]", "");
        String _figura = request.getParameter("figura");
        String _tipoFigura = request.getParameter("typeFigura");
        
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        
        output.setData(dao.getfolioPredio(user, _tema, _figura, _tipoFigura));
        response.setSucessfull(true);
        response.setMessage("Folio y nombre de predio obtenido correctamente");
        output.setResponse(response);
        return output;
        
    }
    
    
    
    public OutputJson mostrarDescripcionesPoligonos(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();

        String _usuario = request.getParameter("usuario");
        String _tema1 = request.getParameter("tema");
        String _tema = _tema1.replaceAll("[^\\dA-Za-z ]", "");
        String _figura = request.getParameter("figura");
        String _tipoFigura = request.getParameter("typeFigura");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.mostrarDescripcionesPoligonos(user, _tema, _figura, _tipoFigura));
        response.setSucessfull(true);
        response.setMessage("Descripción obtenida correctamente");
        output.setResponse(response);
        return output;     
        
    }
    
    
    
    public OutputJson mostrarRegiones(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();
        
        String _usuario = request.getParameter("usuario");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.mostrarRegiones(user));
        response.setSucessfull(true);
        response.setMessage("Región obtenida correctamente");
        output.setResponse(response);
        return output;        
        
    }
    
    
    
    public OutputJson mostrarLocalidades(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();
 
        String _usuario = request.getParameter("usuario");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.mostrarLocalidades(user));
        response.setSucessfull(true);
        response.setMessage("Localidad obtenida correctamente");
        output.setResponse(response);
        return output;
        
    }
    
    
    public OutputJson mostrarLocalRurales(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();
           
        String _usuario = request.getParameter("usuario");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.mostrarLocalRurales(user));
        response.setSucessfull(true);
        response.setMessage("Localidad obtenida correctamente");
        output.setResponse(response);
        return output;
        
    }
    
    
    
    public OutputJson areaFigura(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();

        String _usuario = request.getParameter("usuario");
        String _tema1 = request.getParameter("tema");
        String _tema = _tema1.replaceAll("[^\\dA-Za-z ]", "");
        String _figura = request.getParameter("figura");
        String _tipoFigura = request.getParameter("typeFigura");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.areaFigura(user, _tema, _figura, _tipoFigura));
        response.setSucessfull(true);
        response.setMessage("Área obtenida correctamente");
        output.setResponse(response);
        return output;
 
    }
    
    
    public OutputJson uploadRaster(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();

        String _usuario = request.getParameter("usuario");
        String _nombreRaster = request.getParameter("nombreRaster");
        String _cuadraturaRaster = request.getParameter("cuadraturaRaster");
        String _tamanoRaster = request.getParameter("tamanoRaster");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.uploadRaster(user, _nombreRaster, _cuadraturaRaster, _tamanoRaster));
        response.setSucessfull(true);
        response.setMessage("Área obtenida correctamente");
        output.setResponse(response);
        return output;
 
    }
    
    
    public OutputJson uploadKML(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();

        String _usuario = request.getParameter("usuario");
        String _descripcionKML = request.getParameter("descripcionKML");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.uploadKML(user, _descripcionKML));
        response.setSucessfull(true);
        response.setMessage("Área obtenida correctamente");
        output.setResponse(response);
        return output;
 
    }
            
            
    public OutputJson uploadServicio(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();

        String _usuario = request.getParameter("usuario");
        String _nombreServicio = request.getParameter("nombreServicio");
        String _tokenServicio = request.getParameter("tokenServicio");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.uploadServicio(user, _nombreServicio, _tokenServicio));
        response.setSucessfull(true);
        response.setMessage("Área obtenida correctamente");
        output.setResponse(response);
        return output;
 
    }
    

    public OutputJson insertTema(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();

        String _usuario = request.getParameter("usuario"); 
        String _tema = request.getParameter("tema");


        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
       
        output.setData(dao.insertTema(user, _tema));
        response.setSucessfull(true);
        response.setMessage("Tema creado correctamente");
        output.setResponse(response);
        return output;
 
    }
  
    
   /* public OutputJson insertFiguraManual(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();
  
        String _tema = request.getParameter("tema");
        String _usuario = request.getParameter("usuario");
        String _cadenaFigura = request.getParameter("cadenaFigura");
        String _descripcion = request.getParameter("descripcion");
        String _folio = request.getParameter("folio");
        String _colorRelleno = request.getParameter("colorRelleno");
        String _colorContorno = request.getParameter("colorContorno");
        String _tipoCoordenada = request.getParameter("tipoCoordenada");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.insertFiguraManual(user, _tema, _cadenaFigura, _descripcion, _folio, _colorRelleno, _colorContorno, _tipoCoordenada));
        response.setSucessfull(true);
        response.setMessage("Figura agregada");
        output.setResponse(response);
        return output;
 
    }
    
    
    
    public OutputJson insertFigura(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();
    
        String _tema = request.getParameter("tema");
        String _usuario = request.getParameter("usuario");
        String _cadenaFigura = request.getParameter("cadenaFigura");
        String _tipoFigura = request.getParameter("tipoFigura");
        String _descripcion = request.getParameter("descripcion");
        String _folio = request.getParameter("folio");
        String _colorRelleno = request.getParameter("colorRelleno");
        String _colorContorno = request.getParameter("colorContorno");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        
        output.setData(dao.insertFigura(user, _tema, _cadenaFigura, _descripcion, _folio, _colorRelleno, _colorContorno, _tipoFigura));
        response.setSucessfull(true);
        response.setMessage("Figura agregada");
        output.setResponse(response);
        return output;
        
    }*/
    
    
    public OutputJson insertMapa(HttpServletRequest request) throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();
  
        String _usuario = request.getParameter("usuario");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.insertMapa(user));
        response.setSucessfull(true);
        response.setMessage("Mapa creado");
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson insertTemaAdmin(HttpServletRequest request) throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();
  
        String _usuario = request.getParameter("usuario");
        String _tema1 = request.getParameter("tema");
        String _tema = _tema1.replaceAll("[^\\dA-Za-z ]", "");
        String _colorTema = request.getParameter("colorTema");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.insertTemaAdmin(user, _tema, _colorTema));
        response.setSucessfull(true);
        response.setMessage("Tema insertado");
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson insertIndicadores(HttpServletRequest request) throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();
  
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
    
    
    
    public OutputJson getListMapas(HttpServletRequest request)throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();
        
        String _usuario = request.getParameter("usuario");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.getListMapas(user));
        response.setSucessfull(true);
        response.setMessage("Lista de mapas obtenida correctamente");
        output.setResponse(response);
        return output;
    }
    
        
    public OutputJson getConfRaster(HttpServletRequest request)throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();
        
        String _usuario = request.getParameter("usuario");
        String _verNombreRaster = request.getParameter("verNombreRaster");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.getConfRaster(user, _verNombreRaster));
        response.setSucessfull(true);
        response.setMessage("Configuración raster obtenida correctamente");
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson eliminarTema(HttpServletRequest request)throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();
        
        String _usuario = request.getParameter("usuario");
        String _tema1 = request.getParameter("tema");
        String _tema = _tema1.replaceAll("[^\\dA-Za-z ]", "");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.eliminarTema(user, _tema));
        response.setSucessfull(true);
        response.setMessage("Tema eliminado");
        output.setResponse(response);
        return output;
    }
            
            
    public OutputJson contadorTemas(HttpServletRequest request)throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();
        
        String _usuario = request.getParameter("usuario");
        
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.contadorTemas(user));
        response.setSucessfull(true);
        response.setMessage("Contador obtenido correctamente");
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson eliminarTablaTema(HttpServletRequest request)throws SQLException, IOException, Exception {
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ConectMapaDAO dao = new ConectMapaDAO();
        
        String _usuario = request.getParameter("usuario");
        String _tema1 = request.getParameter("tema");
        String _tema = _tema1.replaceAll("[^\\dA-Za-z ]", "");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        
        output.setData(dao.eliminarTablaTema(user, _tema));
        response.setSucessfull(true);
        response.setMessage("Tema eliminado");
        output.setResponse(response);
        return output;
    }
    
}
