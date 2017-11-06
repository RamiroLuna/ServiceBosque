package org.probosque.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.probosque.dao.SubTableDAO;
import org.probosque.dao.TableDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.ColumnDTO;
import org.probosque.dto.Operation;
import org.probosque.dto.SubTableDTO;
import org.probosque.dto.TableDTO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de información tabular de multiregistros contenidos en los formularios de los programas
 * @author admin
 */

public class ControllerSubTable {

    public OutputJson getTable(HttpServletRequest request) throws Exception {
        String _folio = request.getParameter("folio");
        String _tableName = request.getParameter("name");
        String _user = request.getParameter("user");
        String _activity = request.getParameter("activity");

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        try {
            SubTableDAO dao = new SubTableDAO();
            TableDTO table = new TableDTO();
            table.setColumns((ArrayList<ColumnDTO>) dao.getColumns(user, _tableName + "_info", _folio));
            response.setSucessfull(true);
            response.setMessage("Formulario");
            output.setData(table.getColumns());
        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);

        try {
            TableDAO tableDao = new TableDAO();
            List<Operation> operations;
            operations = tableDao.getOperations(user, _tableName + "_operations");
            output.setOperations(operations);
        } catch (Exception ex) {
           
            //Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        return output;
    }

    public OutputJson getTables(HttpServletRequest request) {
        String _folio = request.getParameter("folio");
        String _tableName = request.getParameter("name");
        String _user = request.getParameter("user");

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            SubTableDAO dao = new SubTableDAO();
            ArrayList<ColumnDTO> form = (ArrayList<ColumnDTO>) dao.getColumns(user, _tableName + "_info", _folio);
            List<TableDTO> tables = dao.getTables(form, _tableName, _folio, user);
            output.setData(tables);
            response.setSucessfull(true);
            response.setMessage("Formulario");
        } catch (Exception ex) {
            Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        
        output.setResponse(response);
        return output;

    }
    
    public OutputJson insertTable(HttpServletRequest request) throws SQLException, IOException {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        try {
            String _folio = request.getParameter("folio");
            String _tableName = request.getParameter("name");
            String _activity = request.getParameter("activity");

            String _user = request.getParameter("user");
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));

            String _json = request.getParameter("json");

            String json = "{\"columns\":" + _json + "}";

            //Gson gson = new Gson();    
            //TableDTO tableJson = gson.fromJson(_json.trim(), TableDTO.class);   
            Gson gson = new Gson();
            /*
             JsonReader reader = new JsonReader(new StringReader(json));
             reader.setLenient(true);
             */
            TableDTO tableJson = gson.fromJson(json.trim(), TableDTO.class);

            TableDAO tableDao = new TableDAO();
            if (!tableDao.existsFolio(user, _activity, _folio)) {
                tableDao.insertFolio(user, _activity, _folio);
            }

            SubTableDAO dao = new SubTableDAO();
            dao.insertTable(user, tableJson, _tableName);
            
            if(_tableName.equalsIgnoreCase("formularios.participantes")){
                 
                dao.updateTotalPerson(user, tableJson , _folio );
            }

            response.setSucessfull(true);
            response.setMessage("Datos guardados correctamente");
            output.setResponse(response);

        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
            output.setResponse(response);
        }
        return output;
    }

    public OutputJson find(HttpServletRequest request) throws SQLException, IOException {

        String _key = request.getParameter("key");
        Gson gson = new Gson();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        //UserDAO dao = new UserDAO();
        try {
            TableDAO dao = new TableDAO();
            String _user = request.getParameter("user");
            String _activity = request.getParameter("activity");
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));

            TableDTO table = new TableDTO();
            table.setColumns((ArrayList<ColumnDTO>) dao.getColumnsSearchable(user, _activity));

            output.setData(dao.find(user, _activity, table, _key));

            response.setSucessfull(true);
            response.setMessage("Formulario");
            output.setResponse(response);
        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;
    }

    public OutputJson editTable(HttpServletRequest request) throws SQLException, IOException {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        try {
            String _json = request.getParameter("json");

            String json = "{\"columns\":" + _json + "}";

            //Gson gson = new Gson();    
            //TableDTO tableJson = gson.fromJson(_json.trim(), TableDTO.class);   
            Gson gson = new Gson();
            /*
             JsonReader reader = new JsonReader(new StringReader(json));
             reader.setLenient(true);
             */
            String _tableName = request.getParameter("name");
            String _folio = request.getParameter("folio");
            String _consecutivo = request.getParameter("consecutivo");
            String _user = request.getParameter("user");
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));

            TableDTO tableJson = gson.fromJson(json.trim(), TableDTO.class);

            SubTableDAO dao = new SubTableDAO();
            dao.editTable(user, tableJson, _tableName, _folio, _consecutivo);
            response.setSucessfull(true);
            response.setMessage("Datos guardados correctamente");

         
        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;
    }

    public OutputJson delete(HttpServletRequest request) {
        String _folio = request.getParameter("folio");

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        //UserDAO dao = new UserDAO();
        try {
            SubTableDAO dao = new SubTableDAO();

            String _tableName = request.getParameter("name");
            String _consecutivo = request.getParameter("consecutivo");

            String _user = request.getParameter("user");
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));

            
            if(_tableName.equalsIgnoreCase("formularios.participantes")){
                 dao.updateTotalPerson(user , _consecutivo ,_folio );
            }
            
            dao.deleteTable(user, _tableName, _folio, _consecutivo);
            

            
            response.setSucessfull(true);
            response.setMessage("El registro se ha eliminado");

        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;

    }
     //Victor Porcayo Altamirano
    public String validar(HttpServletRequest request) throws SQLException, IOException {
        String _tableName = request.getParameter("name");
        String _user = request.getParameter("user");
        String _json = request.getParameter("json");
        Gson gson = new Gson();
        String mensaje="";
        ResponseJson response = new ResponseJson();
        String dato="";
        String existe = "";
        try {
            JSONArray jsa = new JSONArray(_json);              
            for (int i = 0; i < jsa.length(); i++) {
                Gson parse = new GsonBuilder().create();
                SubTableDTO datos=parse.fromJson(jsa.get(i).toString(), SubTableDTO.class);
                if(datos.getField().equals("nombre")||datos.getField().equals("identificador_bien"))
                    dato=datos.getValue();
            }
            SubTableDAO dao = new SubTableDAO();
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            existe = dao.validarExistencia(user, _tableName, dato);
            if(!"".equals(existe)){
                //response.setSucessfull(true);
                if(_tableName.equals("formularios.infractores")){
                    mensaje="Esta Persona ya Fue Registrada Anteriormente "+existe;
                }else{
                    mensaje="Este bien o producto ya había sido asegurado "+existe;
                }
                
            }else{

                mensaje=("noencontrado");            
            }
            
           
        } catch (Exception ex) {
            
            mensaje="error";
        }
        return mensaje;
    }
     //Victor Porcayo Altamirano
    public OutputJson getVolumenArbol(HttpServletRequest request) {
        String _user = request.getParameter("user");
        String _folio = request.getParameter("folio");
        String _sitio = request.getParameter("sitio");
        String _arbol = request.getParameter("arbol");

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        String result = "";
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            SubTableDAO dao = new SubTableDAO();
            result = dao.getVolumen(user, _folio, _sitio, _arbol);
            output.setData(result);
        } catch (Exception ex) {
            Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        response.setSucessfull(true);
        //response.setMessage("Formulario");
        output.setResponse(response);
        return output;

    }
    
    public OutputJson getAnioAreaCorta(HttpServletRequest request) {
        String _user = request.getParameter("user");
        String _folio = request.getParameter("folio");
        String _areaCorta = request.getParameter("areaCorta");

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        String result = "";
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            SubTableDAO dao = new SubTableDAO();
            result = dao.getAnioAreaCorta(user, _folio, _areaCorta);
            output.setData(result);
        } catch (Exception ex) {
            Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        response.setSucessfull(true);
        //response.setMessage("Formulario");
        output.setResponse(response);
        return output;

    }
    
    public OutputJson getMunicipios(HttpServletRequest request) {
        String _user = request.getParameter("user");
        String id_estado = request.getParameter("id_estado");

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            SubTableDAO dao = new SubTableDAO();
            output.setData(dao.getListMunicipios(user, Integer.parseInt(id_estado)));
            response.setSucessfull(true);
        } catch (Exception ex) {
            Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        
        
        output.setResponse(response);
        return output;
    }
       
 public OutputJson getSubcategoria(HttpServletRequest request) {
        String _user = request.getParameter("user");
        String idCategoria= request.getParameter("idCategoria");

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            SubTableDAO dao = new SubTableDAO();
            output.setData(dao.getSubCategoria(user, Integer.parseInt(idCategoria)));
            response.setSucessfull(true);
            
        } catch (Exception ex) {
            
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        
        output.setResponse(response);
        return output;
    }    
 
 public OutputJson editNoConglomerado(HttpServletRequest request){
     String _user = request.getParameter("user");
     String numeroConglomerado = request.getParameter("numeroConglomerado");
     String folio = request.getParameter("folio");
     
     OutputJson output = new OutputJson();
     ResponseJson response = new ResponseJson();
     
     try{
         UserDAO userDao = new UserDAO();
         UserDTO user = userDao.getUser(Integer.parseInt(_user));
         SubTableDAO dao = new SubTableDAO();
         output.setData(dao.editNoConglomeradoSitios(user, numeroConglomerado, folio));
         output.setData(dao.editNoConglomerado(user, numeroConglomerado, folio));
         response.setSucessfull(true);
     }
     
     catch(Exception ex){
         response.setSucessfull(false);
         response.setMessage(ex.getMessage());
     }
     output.setResponse(response);
     return output;
 }
}
