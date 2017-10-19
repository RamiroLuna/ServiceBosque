/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.TableProduccionPlanta;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author Jonathan
 */
public class ControllerProduccionPlanta {
    
    public OutputJson DatosBusqedaPP(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        TableProduccionPlanta tpp = new TableProduccionPlanta();
        
        String Where = request.getParameter("where");
        String Like = request.getParameter("like");
        String Vivero = request.getParameter("vivero");
        
        output.setData(tpp.ObtenerDatosProduccion(Where, Like.replace(" ",""), Vivero.replace(" ","")));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson ProcentajePP(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        TableProduccionPlanta tpp = new TableProduccionPlanta();
        
        String Where = request.getParameter("where");
        String Like = request.getParameter("like");
        String Vivero = request.getParameter("vivero");
        
        output.setData(tpp.ObtenerPorcentaje(Where, Like.replace(" ",""), Vivero.replace(" ","")));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson TotalesPP(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        TableProduccionPlanta tpp = new TableProduccionPlanta();
        
        String Where = request.getParameter("where");
        String Like = request.getParameter("like");
        String Vivero = request.getParameter("vivero");
        
        output.setData(tpp.ObtenerTotales(Where, Like.replace(" ",""), Vivero.replace(" ","")));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson GranTotalPP(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        TableProduccionPlanta tpp = new TableProduccionPlanta();
        
        String Where = request.getParameter("where");
        String Like = request.getParameter("like");
        String Vivero = request.getParameter("vivero");
        
        output.setData(tpp.GranTotal(Where, Like.replace(" ",""), Vivero.replace(" ","")));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson DatosSelectPAA(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        TableProduccionPlanta tpp = new TableProduccionPlanta();
        
        output.setData(tpp.ObtenDatosSelectPAA());
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson DatosSelectViveros(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        TableProduccionPlanta tpp = new TableProduccionPlanta();
        
        output.setData(tpp.SelectViveros());
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson EncabezadoUnoPAA(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        String Where = request.getParameter("where");
        String Where2 = request.getParameter("anio");
        
        TableProduccionPlanta tpp = new TableProduccionPlanta();
        
        output.setData(tpp.ObtenEncabezados(Where.replace(" ",""), Where2.replace(" ","")));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson EncabezadoDosPAA(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        String Where = request.getParameter("where");
        String Where2 = request.getParameter("anio");
        
        TableProduccionPlanta tpp = new TableProduccionPlanta();
        
        output.setData(tpp.ObtenEncabezado2(Where.replace(" ",""), Where2.replace(" ","")));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson DatosSeccion(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        String Where = request.getParameter("where");
        String Where2 = request.getParameter("anio");
        
        TableProduccionPlanta tpp = new TableProduccionPlanta();
        
        output.setData(tpp.DatosSeccion(Where.replace(" ",""), Where2.replace(" ","")));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson DPCiudadano(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        String Like = request.getParameter("like");
        
        TableProduccionPlanta tpp = new TableProduccionPlanta();
        
        output.setData(tpp.DPCiudadano(Like.replace(" ","")));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson DPAnio(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        String Where = request.getParameter("where");
        
        TableProduccionPlanta tpp = new TableProduccionPlanta();
        
        output.setData(tpp.DPAnio(Where.replace(" ","")));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson DPRegion(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        String Where = request.getParameter("where");
        
        TableProduccionPlanta tpp = new TableProduccionPlanta();
        
        output.setData(tpp.DPAnioRegion(Where.replace(" ","")));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson DPRegionDetalle(HttpServletRequest request) throws SQLException, IOException, Exception {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        String Where = request.getParameter("where");
        
        TableProduccionPlanta tpp = new TableProduccionPlanta();
        
        output.setData(tpp.DPAnioRegionDetalle(Where.replace(" ","")));
        response.setSucessfull(true);
        //response.setMessage("Datos guardados correctamente");
        output.setResponse(response);
        return output;
    }
    
}