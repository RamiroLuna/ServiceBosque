package org.probosque.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.TableDAO;
import org.probosque.model.json.FolioPredioJson;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de folios de predios
 * @author admin
 */
public class ControllerFolioPredio {
    
    public FolioPredioJson getFolioPredio(HttpServletRequest request) throws SQLException, Exception {
        
        String _estado = request.getParameter("modulopredio_estado");
        String _region = request.getParameter("region");
        String _municipio = request.getParameter("modulopredio_municipio");
        String _localidad = request.getParameter("modulopredio_localidad");
        TableDAO tableDao = new TableDAO(); 
        FolioPredioJson folios = new FolioPredioJson();
        folios.setList(tableDao.getFoliosPredio(_estado, _region, _municipio, _localidad));
        return folios;
    }
    
    public OutputJson getFolioNext(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        TableDAO dao = new TableDAO();
        String _estado = request.getParameter("modulopredio_estado");
        String _region = request.getParameter("region");
        String _municipio = request.getParameter("modulopredio_municipio");
        String _localidad = request.getParameter("modulopredio_localidad");
        TableDAO tableDao = new TableDAO();
        output.setData(tableDao.getFolioPredioNext(_estado, _region, _municipio, _localidad));
        response.setSucessfull(true);
        response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
}
