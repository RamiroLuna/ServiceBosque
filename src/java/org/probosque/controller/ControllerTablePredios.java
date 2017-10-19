package org.probosque.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.SQL;
import org.probosque.dao.TableDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.ColumnDTO;
import org.probosque.dto.Operation;
import org.probosque.dto.Request;
import org.probosque.dto.TableDTO;
import org.probosque.dto.UserDTO;
import org.probosque.model.LabelValue;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de información correspondiente a cada uno de los formularios
 * @author admin
 */

public class ControllerTablePredios {
    
    public OutputJson getTable(HttpServletRequest request) {
        String _user = request.getParameter("user");        
        String _activity = request.getParameter("activity");

        Gson gson = new Gson();

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
    
            TableDAO dao = new TableDAO();
            TableDTO table = new TableDTO();
            table.setColumns((ArrayList<ColumnDTO>) dao.getColumnsPredios());
            //table = dao.setValues(table);
            
            response.setSucessfull(true);
            response.setMessage("Formulario");
            output.setData(table.getColumns());
            
            try {                
                List<Operation> operations = dao.getOperations_activity(user, _activity);
                output.setOperations(operations);
            } catch (Exception error) {

            }
            
            try {                
                List<Request> requests = dao.getRequestGeneral(user, _activity);
                output.setRequest(requests);
            } catch (Exception error) {

            }
            
            
        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;

    }
    
    public OutputJson getSearchables(HttpServletRequest request) {
        String _user = request.getParameter("user");        
        String _activity = request.getParameter("activity");
        Gson gson = new Gson();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
    
            TableDAO dao = new TableDAO();
            TableDTO table = new TableDTO();
            //table.setColumns((ArrayList<ColumnDTO>) dao.getColumns(user, tableName, user.getProgram(), user.getRole_id()));
            
            table.setColumns((ArrayList<ColumnDTO>) dao.getColumnsSearchable(user, _activity));
            //table = dao.setValues(table);
            
            response.setSucessfull(true);
            response.setMessage("Formulario");
            //output.setData(dao.getColumns());
            //output.setData(table.getColumns());
            
            
            output.setData(table.getColumns());            
            
        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;

    }
    
    public OutputJson insertTable(HttpServletRequest request) throws SQLException, IOException {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        TableDTO tableJson = null;
        try {
            
            String _json = request.getParameter("json");
            String _activity = request.getParameter("activity");

            String json = "{\"columns\":" + _json + "}";


            String _user = request.getParameter("user");        
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            //tableName = tableName.replace("X", String.valueOf(user.getProgram()));
            
            String tableName = SQL.getTableInfo(user, _activity);
            
        //Gson gson = new Gson();    
            //TableDTO tableJson = gson.fromJson(_json.trim(), TableDTO.class);   
            Gson gson = new Gson();
            /*
             JsonReader reader = new JsonReader(new StringReader(json));
             reader.setLenient(true);
             */
            
            
            tableJson = gson.fromJson(json.trim(), TableDTO.class);

            
            TableDAO dao = new TableDAO();

            if (dao.existsFolio(user,_activity, tableJson.getFolio())) {
                dao.editTable(user, _activity, tableJson);
            } else {
                dao.insertTable(user, _activity, tableJson);
            }

            response.setSucessfull(true);
            response.setMessage("Datos guardados correctamente");

        } catch (Exception ex) {
            if (tableJson != null && tableJson.getMensajeError().hasErrors()) {
                response.setSucessfull(false);
                response.setMessage(tableJson.getMensajeError().getMensaje());
            }
            else{
                response.setSucessfull(false);
                response.setMessage(ex.getMessage());
            }
            
            
        }
        output.setResponse(response);
        return output;
    }

    public OutputJson find(HttpServletRequest request) throws SQLException, IOException {
        
        String _key = request.getParameter("key");
        String _field = request.getParameter("field");
        
        Gson gson = new Gson();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        //UserDAO dao = new UserDAO();
        try {
            String _user = request.getParameter("user");        
            String _activity = request.getParameter("activity");
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            TableDAO dao = new TableDAO();

            TableDTO table = new TableDTO();
            table.setColumns((ArrayList<ColumnDTO>) dao.getColumnsSearchable(user, _activity));
            
            //String where = " WHERE UPPER(folio) LIKE " + "'%" + _key.toUpperCase() + "%'";
            String where = " WHERE UPPER("+_field+") LIKE " + "'%" + _key.toUpperCase() + "%'";
            
            
            output.setData(dao.getTables(user, _activity, table, where, " ORDER BY folio"));
            ArrayList<LabelValue> titles = new ArrayList<>();
            
            for(ColumnDTO column : table.getColumns()){
                titles.add(new LabelValue(column.getLabel(), column.getName()));
            }
            titles.add(new LabelValue("Acci&oacute;n", "button", "folio"));
            
            output.setTabular(titles);
            
            //output.setData(dao.find(table, _key, tableName));

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

        TableDTO tableJson= null;
        try {
            
            String _user = request.getParameter("user");        
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            
            String _json = request.getParameter("json");
            
            String _activity = request.getParameter("activity");

            String json = "{\"columns\":" + _json + "}";

        //Gson gson = new Gson();    
            //TableDTO tableJson = gson.fromJson(_json.trim(), TableDTO.class);   
            Gson gson = new Gson();
            /*
             JsonReader reader = new JsonReader(new StringReader(json));
             reader.setLenient(true);
             */
            tableJson = gson.fromJson(json.trim(), TableDTO.class);

            TableDAO dao = new TableDAO();
            dao.editTable(user, _activity, tableJson);

            response.setSucessfull(true);
            response.setMessage("Datos guardados correctamente");

        } catch (Exception ex) {
           if (tableJson != null && tableJson.getMensajeError().hasErrors()) {
                response.setSucessfull(false);
                response.setMessage(tableJson.getMensajeError().getMensaje());
            }
            else{
                response.setSucessfull(false);
                response.setMessage(ex.getMessage());
            }
        }
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson delete(HttpServletRequest request) {
        String _folio = request.getParameter("folio");
        String _activity = request.getParameter("activity");

        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        //UserDAO dao = new UserDAO();
        try {
            String _user = request.getParameter("user");        
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            TableDAO dao = new TableDAO();
            
            dao.deleteTable(user, _activity, _folio);
            response.setSucessfull(true);
            response.setMessage("El registro se ha eliminado");
            
        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;

    }
}
