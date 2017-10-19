/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.controller;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.LogDAO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;
/**
 *
 * @author Solid
 */
public class ControllerLog {
    public OutputJson fecha_log(HttpServletRequest request) throws SQLException, IOException, Exception{
      OutputJson output = new OutputJson();
      ResponseJson response = new ResponseJson();
      try{
       String fecha1 = request.getParameter("fecha1");
       String fecha2 = request.getParameter("fecha2");
       String programa = request.getParameter("programa");
       LogDAO dao= new LogDAO();
       output.setData(dao.getLogfecha(fecha1,fecha2,programa));
       if(dao.getLogfecha(fecha1,fecha2,programa)==null)
            {
                response.setMessage("No se encontraron resultados");
            }
            
             
       response.setSucessfull(true);
        }catch(Exception ex){
       response.setSucessfull(false);
       response.setMessage(ex.getMessage());
      }
        output.setResponse(response);
       
        return output;
    }
    public OutputJson nombre_log(HttpServletRequest request) throws SQLException,IOException, Exception{
      OutputJson output = new OutputJson();
      ResponseJson response = new ResponseJson();
      try{
       String nombre = request.getParameter("nombre");
       String fecha1 = request.getParameter("fecha1");
       String fecha2 = request.getParameter("fecha2");

       LogDAO dao= new LogDAO();
        output.setData(dao.getLognombre(nombre,fecha1,fecha2));
        if(dao.getLognombre(nombre,fecha1,fecha2)==null)
            {
                response.setMessage("No se encontraron resultados");
            }
        response.setSucessfull(true);
      }catch(Exception ex){
       response.setSucessfull(false);
       response.setMessage(ex.getMessage());
      }
        output.setResponse(response);
        return output;
    }
    
    public OutputJson todoLog(HttpServletRequest request) throws SQLException,IOException, Exception{
      OutputJson output = new OutputJson();
      ResponseJson response = new ResponseJson();
      try{
      
       LogDAO dao= new LogDAO();
      output.setData(dao.getLog());

        response.setSucessfull(true);
      }catch(Exception ex){
       response.setSucessfull(false);
       response.setMessage(ex.getMessage());
      }
        output.setResponse(response);
        return output;
    }
}
