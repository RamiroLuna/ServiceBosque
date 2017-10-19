package org.probosque.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de folios del programa 3
 * @author admin
 */
public class ControllerFolioPrograma3 {

    public OutputJson getFolioPrograma3(HttpServletRequest request) throws SQLException, IOException, Exception {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        
        String _tipoReforestacion = request.getParameter("id_tipo_reforestacion");
        String _anio = request.getParameter("anio");
        String _region = request.getParameter("region");
        String _expedienteConsecutivo = request.getParameter("expediente_consecutivo");

        _anio = _anio.substring(2);
        _region = "0" + _region;
        
        //RS, RR, RC
        switch(_tipoReforestacion){
            case "4":
                _tipoReforestacion = "CR";
            break;
            case "3":
                _tipoReforestacion = "RC";
                break;
            case "2":
                _tipoReforestacion = "RR";
                break;
                 case "1":
                _tipoReforestacion = "RS";
                break;
        }
        
        String folio = _tipoReforestacion + _anio + _region + _expedienteConsecutivo;
        output.setData(folio);
        response.setSucessfull(true);
        response.setMessage("Folio obtenido correctamente");
        output.setResponse(response);
        return output;

    }
}
