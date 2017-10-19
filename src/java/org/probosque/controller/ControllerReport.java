package org.probosque.controller;

import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.TableDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.ReportDTO;
import org.probosque.dto.TableDTO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de información tabular mediante un filtro ya sea por una o diferentes columnas
 * @author admin
 */

public class ControllerReport {

    public OutputJson getReportasdads(HttpServletRequest request) {
        String _folio = request.getParameter("folio");

        Gson gson = new Gson();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        try {

            response.setSucessfull(true);
            response.setMessage("Formulario");
            output.setData(123456789);

        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;
    }

    public OutputJson getReport(HttpServletRequest request) throws Exception {
        String _folio = request.getParameter("folio");
        String _tableName = request.getParameter("name");
        String _activity = request.getParameter("activity");

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        String _user = request.getParameter("user");
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
            //tableName = tableName.replace("X", String.valueOf(user.getProgram()));

        String _json = request.getParameter("json");

        Gson gson = new Gson();

        ReportDTO reportJson = gson.fromJson(_json.trim(), ReportDTO.class);

        String filter = "";
        if (reportJson.getFilter() != null) {
            filter = reportJson.getFilter().getSQL(user);
        }

        String order = "";
        if (reportJson.getOrder() != null) {
            order = reportJson.getOrder().getSQL();
        } else {
            order = " ORDER BY folio";
        }

        TableDTO table = new TableDTO();
        table.setColumns(reportJson.getFields());

        response.setSucessfull(true);
        response.setMessage("Reporte");

        TableDAO tableDao = new TableDAO();

        output.setData(tableDao.getTables(user, _activity, table, filter, order));

        output.setResponse(response);
        return output;

    }

    public String getReportJson(HttpServletRequest request) {
        String _folio = request.getParameter("folio");

        Gson gson = new Gson();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        //UserDAO dao = new UserDAO();
        StringBuilder json = new StringBuilder();
        try {

            String _user = request.getParameter("user");
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));

            String _json = request.getParameter("json");
            String jsonTable = "{\"columns\":" + _json + "}";
            Gson gsonTable = new Gson();
            TableDTO tableJson = gsonTable.fromJson(jsonTable.trim(), TableDTO.class);

            response.setSucessfull(true);
            response.setMessage("Formulario");

        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        } finally {
            return json.toString().replace("[,", "[");
        }

    }
}
