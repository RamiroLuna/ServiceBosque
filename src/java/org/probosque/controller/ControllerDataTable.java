package org.probosque.controller;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.DataTableDAO;
import org.probosque.dao.TableDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.ColumnDTO;
import org.probosque.dto.ReportDTO;
import org.probosque.dto.TableDTO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputTableJson;
import org.probosque.model.json.PageInfoJson;
import org.probosque.model.json.ResponseJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de información tabular perteneciente a los programas
 * @author admin
 */
public class ControllerDataTable {

    public OutputTableJson getAll(HttpServletRequest request) throws SQLException, Exception {
        OutputTableJson output = new OutputTableJson();
        ResponseJson responseJson = new ResponseJson();

        int page = 0;
        int limit = 0;
        int offset = 0;

        String order = "";
        String filter = "";

        try {
            limit = Integer.parseInt(request.getParameter("rows"));
        } catch (Exception error) {

        }

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception error) {
            page = 1;
        }

        try {
            offset = (page - 1) * limit;
        } catch (Exception error) {

        }

        
        String _user = request.getParameter("user");
        String _activity = request.getParameter("activity");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        
        String json = request.getParameter("json");

        Gson gson = new Gson();

        try {
            ReportDTO reportJson = gson.fromJson(json.trim(), ReportDTO.class);
            filter = "";
            if (reportJson.getFilter() != null) {
                filter = reportJson.getFilter().getSQL(user);
            }

            order = "";
            if (reportJson.getOrder() != null) {
                order = reportJson.getOrder().getSQL();
            }
        } catch (Exception error) {
            int x = 15;
            x = 23;
        }

        
        
        
        TableDAO tableDao = new TableDAO();
        

        TableDTO  table = new TableDTO();
        //table.setColumns((ArrayList<ColumnDTO>) tableDao.getColumns(user));
        table.setColumns((ArrayList<ColumnDTO>) tableDao.getColumnsByDataTable(user,_activity));
        
        
        DataTableDAO dao = new DataTableDAO();
        //PrediosDAO dao = new PrediosDAO();
        PageInfoJson pageInfo = new PageInfoJson();

        if (json == null) {
            output.setColumns(tableDao.getColumnsForReport(user, _activity));
        }

        
        
        //int total = dao.getTotal(user, _activity, table, filter, order, offset, limit);
        
        int total = dao.getTotalInt(user, _activity, table, filter, order, offset, limit);


        
        //user, _activity, table, filter, order, offset, limit
        
        
        int pages = 0;
        try{
            if (limit > total) {
                pages = 1;
            }
            else{
                int pageInt = total / limit;
                int pageDec = total % limit;
                if(pageDec>0){
                    pages = pageInt + 1;
                }
            }
            //long pages = Math.round((double) total / (double) limit);
        }
        catch(Exception error){
            
        }
        
        
      
        pageInfo.setTotalrows(total);
        pageInfo.setTotalpages(pages);
        pageInfo.setCurrentpage(page);

        responseJson.setMessage("Table");
        responseJson.setSucessfull(true);

        output.setResponse(responseJson);
        
        
        output.setData(tableDao.getRecordsForTable(user, _activity, table, filter, order, offset, limit));
        //output.setData(dao.getAll(tableName, order, filter, offset, limit));
        output.setPageinfo(pageInfo);

        return output;
    }

    public OutputTableJson getColumns(HttpServletRequest request) throws SQLException, Exception {

        String _user = request.getParameter("user");
        String _activity = request.getParameter("activity");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        
        OutputTableJson output = new OutputTableJson();
        ResponseJson responseJson = new ResponseJson();

        //DataTableDAO dao = new DataTableDAO();
        TableDAO dao = new TableDAO();
        
        responseJson.setMessage("Table");
        responseJson.setSucessfull(true);

        output.setResponse(responseJson);
        output.setData(dao.getColumnsForReport(user, _activity));
        output.setColumns(null);
        //output.setColumns(dao.getColumns(user));

        return output;
    }
}


