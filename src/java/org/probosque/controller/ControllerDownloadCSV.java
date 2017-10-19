package org.probosque.controller;

import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.CSVDataDAO;
import org.probosque.dao.TableDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.CSVDataDTO;
import org.probosque.dto.ReportDTO;
import org.probosque.dto.TableDTO;
import org.probosque.dto.UserDTO;
import org.probosque.model.PDFcontent;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de información tabular perteneciente a los programas en formato CSV y PDF
 * @author admin
 */
public class ControllerDownloadCSV {

    
    public String getCSV(HttpServletRequest request) throws Exception {

        String result = "";
        int id = Integer.parseInt(request.getParameter("id"));
        String user = request.getParameter("user");
        
        String _activity = request.getParameter("activity");
        
        UserDAO userDao = new UserDAO();
        UserDTO userDto = userDao.getUser(Integer.parseInt(user));
        
        CSVDataDAO dao = new CSVDataDAO();
        CSVDataDTO data = dao.getCsvData(userDto, id);
        String _json = data.getJson();

        //UserDAO userDao = new UserDAO();
        //UserDTO user = userDao.getUser(Integer.parseInt(_user));
       Gson gson = new Gson();
        ReportDTO reportJson = gson.fromJson(_json.trim(), ReportDTO.class);

        String filter = "";
        if (reportJson.getFilter() != null) {
            filter = reportJson.getFilter().getSQL(userDto);
        }

        String order = "";
        if (reportJson.getOrder() != null) {
            order = reportJson.getOrder().getSQL();
        }

        TableDTO table = new TableDTO();
        table.setColumns(reportJson.getFields());
        TableDAO tableDao = new TableDAO();
        result = tableDao.getCSV(userDto, _activity, table, filter, order);
        
        return result;
    }
    
    public PDFcontent getPDF(HttpServletRequest request) throws Exception {
        
        String result = "";
        int id = Integer.parseInt(request.getParameter("id"));
        String user = request.getParameter("user");
        String _activity = request.getParameter("activity");
        
        UserDAO userDao = new UserDAO();
        UserDTO userDto = userDao.getUser(Integer.parseInt(user));
        
        CSVDataDAO dao = new CSVDataDAO();
        CSVDataDTO data = dao.getCsvData(userDto, id);
        String _json = data.getJson();

        //UserDAO userDao = new UserDAO();
        //UserDTO user = userDao.getUser(Integer.parseInt(_user));
        Gson gson = new Gson();
        ReportDTO reportJson = gson.fromJson(_json.trim(), ReportDTO.class);

        String filter = "";
        if (reportJson.getFilter() != null) {
            filter = reportJson.getFilter().getSQL(userDto);
        }

        String order = "";
        if (reportJson.getOrder() != null) {
            order = reportJson.getOrder().getSQL();
        }
        else{
            order = " ORDER BY folio";
        }

        TableDTO table = new TableDTO();
        table.setColumns(reportJson.getFields());
        TableDAO tableDao = new TableDAO();
        PDFcontent pdfContent = tableDao.getPDF(userDto, _activity, table, filter, order);
        
        return pdfContent;
    }
}