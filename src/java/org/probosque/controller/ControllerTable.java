package org.probosque.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.ReforestacionDAO;
import org.probosque.dao.SQL;
import org.probosque.dao.TableDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.CatalogoDTO;
import org.probosque.dto.ColumnDTO;
import org.probosque.dto.ListDTO;
import org.probosque.dto.Operation;
import org.probosque.dto.Request;
import org.probosque.dto.Summary;
import org.probosque.dto.TableDTO;
import org.probosque.dto.UserDTO;
import org.probosque.model.LabelValue;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de la información necesaria para contruir cada formulario
 * @author admin
 */

public class ControllerTable {
    
    public OutputJson getTable(HttpServletRequest request) {
        String _folio = request.getParameter("folio");
        String _user = request.getParameter("user");        
        String _report = request.getParameter("report");

        String _activity = request.getParameter("activity");
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
    
            TableDAO dao = new TableDAO();
            TableDTO table = new TableDTO();
            
            int id_municipio = 0;
            if (user.getProgram() != 13  && _folio!=null) {
                id_municipio = dao.GetIdMunicipioByFolio(user, _folio);
            }
            
            if(_report == null) {
                table.setColumns((ArrayList<ColumnDTO>) dao.getColumns(user, _activity,  _folio!=null, id_municipio));
            } else {
                table.setColumns((ArrayList<ColumnDTO>) dao.getColumnsForReport(user, _activity));
            }
                
            //table = dao.setValues(table);
            
            response.setSucessfull(true);
            response.setMessage("Formulario");
            //output.setData(dao.getColumns());
            //output.setData(table.getColumns());
            
            List<ColumnDTO> columns = dao.getValues(user, _activity, table, _folio).getColumns();
            
            if(_folio != null) {
               for (int i = 0; i < columns.size(); i++) {
                  if (columns.get(i).getDatatype().equals("list") && !columns.get(i).getListname().contains("catalogos.cup")) {
                        try {
                            /*
                            * If para evitar list con contenido 1,2,3,4,5
                            */
                            String valor = String.valueOf(columns.get(i).getValue()).trim();
                            
                            if(valor.split(",").length == 0){
                                Integer.parseInt(valor);
                            }
                              
                            
                            
                        } catch (Exception error) {
                            ListDTO list = new ListDTO();
                            ArrayList<CatalogoDTO> arreglo = new ArrayList<>();
                            list.setList(arreglo);
                            columns.get(i).setList(list);
                        }
                    }
                }
            }
            
            
            String folio = "";
            if (_folio == null) {
                for (ColumnDTO column : columns) {
                    if (column.getName().equals("folio")) {
                        folio = String.valueOf(column.getValue());
                    }
                }
            }
            else{
                folio = _folio;
            }
            
            for(ColumnDTO column : columns){
                if(column.getDatatype().equals("records")){
                    column.setRecords(String.valueOf(dao.getRecords(user, String.valueOf(column.getName()),folio)));
                }
            }
             
            
            
            output.setData(table.getColumns());
            
            try {                
                List<Operation> operations = dao.getOperations_activity(user, _activity);
                output.setOperations(operations);
            } catch (Exception error) {

            }
            
            try {                
                List<Request> requests = dao.getRequest(user, _activity);
                output.setRequest(requests);
            } catch (Exception error) {

            }
            
            try {                
                List<Summary> summarys = dao.getSummary(user, _activity);
                output.setSummarys(summarys);
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
            ReforestacionDAO daoReforestacion = new ReforestacionDAO();

            if (dao.existsFolio(user, _activity, tableJson.getFolio())) {
                dao.editTable(user, _activity, tableJson);
            } else {
                dao.insertTable(user, _activity, tableJson);
                
                /*INCIIO CÓDIGO NUEVO PARA REFORESTEMOS MÉXICO*/
                int coordenada=0;
                String lat="", lon="", folio="";
                int i=0;
                
                if(user.getProgram() == 8) {
                    while(coordenada<=2 && i<tableJson.getColumns().size()) {
                        if (tableJson.getColumns().get(i).getName().equals("folio")){
                            folio=String.valueOf(tableJson.getColumns().get(i).getValue());
                            coordenada++;
                        }
                        if (tableJson.getColumns().get(i).getName().equals("coordenadas_utm_x")) {
                            lon=String.valueOf(tableJson.getColumns().get(i).getValue());
                            coordenada++;
                        }
                        if (tableJson.getColumns().get(i).getName().equals("coordenadas_utm_y")) {
                            lat=String.valueOf(tableJson.getColumns().get(i).getValue());
                            coordenada++;
                        }
                        i++;
                    }
                 daoReforestacion.insertPunto(user, lon, lat, folio);
                }
                
                /*FIN CÓDIGO NUEVO PARA REFORESTEMOS MÉXICO*/
            }

            response.setSucessfull(true);
            response.setMessage("<Datos guardados correctamente> <ITJSON>");

        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Controller:ControllerTable", ex);
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
        String _datatype = request.getParameter("datatype");
        
        //Gson gson = new Gson();
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
            
            String where = "";
            switch(_datatype){
                case "string":case "alphanumeric": case "time": case "date":
                    where = " WHERE UPPER("+_field+") LIKE " + "'%" + _key.toUpperCase() + "%'";
                    break;
                case "real":
                case "numeric":
                    where = " WHERE "+_field+ " = " + _key;
                    break;
                case "list":
                    where = " WHERE UPPER("+_field+") LIKE " + "'%" + _key.toUpperCase() + "%'";
                    break;
            }
            
            if(user.getProgram()==6 || user.getProgram()==1 || user.getProgram()==7 || user.getProgram()==10 || user.getProgram()==12){
                //Jonathan Aldama output.setData(dao.getTables(user, _activity,  table, where, " ORDER BY folio"));
                output.setData(dao.getTables(user, _activity,  table, where, " ORDER BY region, modulopredio_municipio ASC"));
            }else{
                output.setData(dao.getTables(user, _activity,  table, where, " ORDER BY region, modulopredio_municipio ASC"));
            }            
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
        }catch(SQLException ex)
          {
          response.setSucessfull(false);
          response.setMessage("<Se detecto alguna inconsistencia de datos \n Por favor verifique su información>" + ex);
          } 
        catch (Exception ex) {
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
            String _activity = request.getParameter("activity");
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
            tableJson = gson.fromJson(json.trim(), TableDTO.class);

            TableDAO dao = new TableDAO();
            dao.editTable(user, _activity, tableJson);

            response.setSucessfull(true);
            response.setMessage("Datos guardados correctamente");

        } catch (Exception ex) {
           if (tableJson != null && tableJson.getMensajeError().hasErrors()) {
                response.setSucessfull(false);
                //response.setMessage(tableJson.getMensajeError().getMensaje());
                response.setMessage("Detalle del mensaje"+ex);
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

        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        //UserDAO dao = new UserDAO();
        try {
            String _user = request.getParameter("user");        
            String _activity = request.getParameter("activity");
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
     
    public OutputJson getTipoInspeccion(HttpServletRequest request) {
        
        String _user = request.getParameter("user");
        String region = request.getParameter("region");
        String municipio = request.getParameter("municipio");
        String localidad = request.getParameter("localidad");
        String tipoInspeccion = request.getParameter("tipoInspeccion");

        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        //UserDAO dao = new UserDAO();
        try {
            
            
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            TableDAO dao = new TableDAO();
            output.setData(dao.getCatalogoInspeccion(user, region, municipio, localidad,tipoInspeccion));
            
            response.setSucessfull(true);
            
            
        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;

    }

    public OutputJson getListaIValor(HttpServletRequest request) {
        String _user = request.getParameter("user");
        String region = request.getParameter("region");
        String folio = request.getParameter("folio");
        String municipio = request.getParameter("municipio");
        String localidad = request.getParameter("localidad");
        String tipoInspeccion = request.getParameter("tipoInspeccion");
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            TableDAO dao = new TableDAO();
            output.setData(dao.getCatalogoInspeccion(user, region, municipio, localidad,tipoInspeccion));
            output.setCadena(dao.getIndustria(user, folio));
            response.setSucessfull(true);
         } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;
   }
}
