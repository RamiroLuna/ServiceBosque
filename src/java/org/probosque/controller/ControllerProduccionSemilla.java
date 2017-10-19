/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.TableProduccionSemilla;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author Jonathan
 */
public class ControllerProduccionSemilla {
    
    public OutputJson DatosBancoGermoplasma(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        TableProduccionSemilla tpp = new TableProduccionSemilla();
        
        String Like = request.getParameter("like");
        
        output.setData(tpp.ObtenerDatosBGP(Like.replace(" ","")));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson TotalBancoGermoplasma(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        TableProduccionSemilla tpp = new TableProduccionSemilla();
        
        String Like = request.getParameter("like");
        
        output.setData(tpp.ObtenerTotalBGP(Like.replace(" ","")));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
     public OutputJson DatosDestinoGermoplasma(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        TableProduccionSemilla tpp = new TableProduccionSemilla();
        
        String Like = request.getParameter("like");
        String Anio = request.getParameter("anio");
        
        output.setData(tpp.ObtenerDatosDGP(Like.replace(" ",""), Anio.replace(" ","")));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
     
    public OutputJson DatosTotalDestinoGermoplasma(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        TableProduccionSemilla tpp = new TableProduccionSemilla();
        
        String Like = request.getParameter("like");
        String Anio = request.getParameter("anio");
        
        output.setData(tpp.ObtenerTotalDGP(Like.replace(" ",""), Anio.replace(" ","")));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
}
