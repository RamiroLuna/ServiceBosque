/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.controller;

import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.AuditoriasPreventivasDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author Erick_G
 */
public class ControllerAuditoriasPreventivas {
    
    public OutputJson getAllPredio( HttpServletRequest request) throws Exception{
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        AuditoriasPreventivasDAO dao =new AuditoriasPreventivasDAO();
        
        try{
            String folio = request.getParameter("folio");
            output.setData(dao.getAllPredio(folio));    
            response.setSucessfull(true);
        }catch(Exception ex){
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response); 
        return output;
    }
    
    public OutputJson getPredioPoligonos( HttpServletRequest request) throws Exception{
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        AuditoriasPreventivasDAO dao =new AuditoriasPreventivasDAO();
        
        try{
            String folio = request.getParameter("folio");
            output.setData(dao.getPredioPoligonos(folio));    
            response.setSucessfull(true);
        }catch(Exception ex){
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response); 
        return output;
    }
    
    public OutputJson getPredioImagen( HttpServletRequest request) throws Exception{
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        AuditoriasPreventivasDAO dao =new AuditoriasPreventivasDAO();
        
        try{
            String folio = request.getParameter("folio");
            output.setData(dao.getPredioImagen(folio));    
            response.setSucessfull(true);
        }catch(Exception ex){
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response); 
        return output;
    }
    
    public OutputJson getPredioRepresentante( HttpServletRequest request) throws Exception{
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        AuditoriasPreventivasDAO dao =new AuditoriasPreventivasDAO();
        
        try{
            String folio = request.getParameter("folio");
            output.setData(dao.getPredioRepresentante(folio));    
            response.setSucessfull(true);
        }catch(Exception ex){
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response); 
        return output;
    }
}
