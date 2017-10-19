package org.probosque.controller;

import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.SQL;
import org.probosque.dao.TableDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.ReportDTO;
import org.probosque.dto.TableDTO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de información de gráficas
 * @author admin
 */
public class ControllerGraph {

    
    public OutputJson getReport(HttpServletRequest request) {
        String _folio = request.getParameter("folio");
        String _tableName = request.getParameter("name");
        String _activity = request.getParameter("activity");
        

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        //UserDAO dao = new UserDAO();
        try {
            
            String _user = request.getParameter("user");        
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            
            String tableName = SQL.getTableMain(user, _activity);
            
            String _json = request.getParameter("json");

            Gson gson = new Gson();
            
            ReportDTO reportJson = gson.fromJson(_json.trim(), ReportDTO.class);
            
            String graph = "";
            if(reportJson.getGraph()!=null){
                graph = reportJson.getGraph().getField();
            }
            
            String filter  = "";
            if(reportJson.getFilter()!=null){
                filter = reportJson.getFilter().getSQL(user);
            }
            
            String order = "";
            if(reportJson.getOrder()!=null){
                order = reportJson.getOrder().getSQL();
            }
                
            
            TableDTO table = new TableDTO();
            table.setColumns(reportJson.getFields());
            
            
            
            response.setSucessfull(true);
            response.setMessage("Reporte");
            
            TableDAO tableDao = new TableDAO();
            
            output.setData(tableDao.getTablesGraph(user, _activity, table, graph, filter, order));
            

        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;

    }
    
}