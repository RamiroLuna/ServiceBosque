package org.probosque.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.AbogadoDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

public class ControllerAbogado {
public OutputJson getPanel(HttpServletRequest request) {
        String _user=request.getParameter("user");
        ResponseJson response = new ResponseJson();
        OutputJson output = new OutputJson();
        try
           {
            UserDAO daoUser= new UserDAO();   
            AbogadoDAO dao= new AbogadoDAO();
            UserDTO user = daoUser.getUser(Integer.parseInt(_user));
            response.setSucessfull(true);
            output.setData(dao.getPanel(user));
           }
       catch(SQLException sql )
      {
        response.setSucessfull(false);
        response.setMessage("Error en la base de Datos ");
        output.setData(" Descripcion de error: "+sql.getMessage());
      
      }
    catch(Exception ex )
      {
      response.setSucessfull(false);
      response.setMessage(ex.getMessage());
      }
      output.setResponse(response);
      return output;
            
    }
    public OutputJson getPanelConcluidas(HttpServletRequest request) {
        String _user=request.getParameter("user");
        ResponseJson response = new ResponseJson();
        OutputJson output = new OutputJson();
        try
           {
            UserDAO daoUser= new UserDAO();   
            AbogadoDAO dao= new AbogadoDAO();
            UserDTO user = daoUser.getUser(Integer.parseInt(_user));
            response.setSucessfull(true);
            output.setData(dao.getConcluidas(user));
           }
       catch(SQLException sql )
      {
        response.setSucessfull(false);
        response.setMessage("Error en la base de Datos ");
        output.setData(" Descripcion de error: "+sql.getMessage());
      
      }
    catch(Exception ex )
      {
      response.setSucessfull(false);
      response.setMessage(ex.getMessage());
      }
      output.setResponse(response);
      return output;
            
    }
    
    public OutputJson addObservacion(HttpServletRequest request) {
        String _user=request.getParameter("user");
        String observacion = request.getParameter("observacion");
        String fecha = request.getParameter("fechaObservacion");
        String No_expediente= request.getParameter("expediente");
        Integer estatus= Integer.parseInt(request.getParameter("estatus"));
        
        ResponseJson response = new ResponseJson();
        OutputJson output = new OutputJson();
        try
           {
            UserDAO daoUser= new UserDAO();   
            AbogadoDAO dao= new AbogadoDAO();
            UserDTO user = daoUser.getUser(Integer.parseInt(_user));
            response.setSucessfull(true);
            output.setData(dao.addObservacion(user, observacion, fecha, No_expediente,estatus));
           }
       catch(SQLException sql )
      {
        response.setSucessfull(false);
        response.setMessage("Error en la base de Datos ");
        output.setData(" Descripcion de error: "+sql.getMessage());
      
      }
    catch(Exception ex )
      {
      response.setSucessfull(false);
      response.setMessage(ex.getMessage());
      }
      output.setResponse(response);
      return output;

    }

     public OutputJson changeEstatus(HttpServletRequest request) throws Exception {
    
        int user= Integer.parseInt(request.getParameter("user"));

        String No_expediente= request.getParameter("no_expediente");
        String Observaciones=request.getParameter("observaciones");
        String fecha=request.getParameter("fecha");
        String estatus=request.getParameter("estatus");          
        String fecha_conclusion= request.getParameter("fecha_conclusion");
        String multa= request.getParameter("multa");
        String decomiso= request.getParameter("decomiso");
        Boolean E_37;
        Boolean E_38;
        Boolean E_41;
   
        if(fecha_conclusion==null || fecha_conclusion.isEmpty())
            fecha_conclusion=" ";
        if(multa==null || multa.isEmpty())
            multa="0";
        else
           {
             multa=multa.replaceAll(",","");
           }
        if(decomiso==null || decomiso.isEmpty())
            decomiso=" ";
        
        ResponseJson response = new ResponseJson();
        OutputJson output= new OutputJson();
        
        UserDAO daoUser = new UserDAO();   
        UserDTO dto = daoUser.getUser(user);
         AbogadoDAO dao= new AbogadoDAO();
       try{
           StringBuilder aux_estatus= new StringBuilder();
           aux_estatus.append( estatus);
              /*E_37=dao.verificaImpugnacion(dto, No_expediente, "37");
              E_38=dao.verificaImpugnacion(dto, No_expediente, "38");
              E_41=dao.verificaImpugnacion(dto, No_expediente, "41");
              
              if(E_37==true)
                 {
                 aux_estatus.append(",37"); 
                 }
              if(E_38==true)
                 {
                 aux_estatus.append(",38");
                 }
              if(E_41==true)
                 {
                 aux_estatus.append(",41");
                 }*/
              
           Boolean changeEstatus=dao.InsertStatus(dto,aux_estatus.toString(), No_expediente,fecha_conclusion, Float.parseFloat(multa), decomiso);      
            if(estatus.contains("20")|| estatus.contains("21") || estatus.contains("22")
               ||estatus.contains("23")|| estatus.contains("24") || estatus.contains("25")
               ||estatus.contains("26")|| estatus.contains("27") || estatus.contains("28")
               ||estatus.contains("29")     
                )
            {  
               if(dto.getActivity()==2)
               {
                response.setMessage("El numero de Expediente cambio de estatus y fue asigando al coordinador ");  
                dao.changeUser(dto, No_expediente,"COORDINADOR");
               }
               
            }
            
            else{
            response.setMessage("El numero de Expediente cambio de estatus");
            }
            if(dto.getActivity()==3)
                  {
                  response.setMessage("El numero de Expediente cambio de estatus y fue asigando al abogado ");  
                  dao.changeUser(dto, No_expediente,"ABOGADO");
                  }               
            Boolean addObs=dao.addObservacion(dto, Observaciones, fecha, No_expediente, estatus);
           response.setSucessfull(true);
           
           }
        catch(SQLException sql )
            {
              response.setSucessfull(false);
              response.setMessage("Error en la base de Datos ");
              output.setData(" Descripcion de error: "+sql.getMessage());

            }
          catch(Exception ex )
            {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
            }
            output.setResponse(response);
            return output;
        
    }
public OutputJson getObservaciones(HttpServletRequest request )
  {
  int user= Integer.parseInt(request.getParameter("user"));
  String No_expediente= request.getParameter("no_expediente");
  ResponseJson response = new ResponseJson();
  OutputJson output= new OutputJson();
  UserDAO daoUser = new UserDAO();   
        
         AbogadoDAO dao= new AbogadoDAO();
       try{
           UserDTO dto = daoUser.getUser(user);
           output.setData(dao.getObservaciones(dto,No_expediente));
           response.setSucessfull(true);
           response.setMessage(" ");
           }
        catch(SQLException sql )
            {
              response.setSucessfull(false);
              response.setMessage("Error en la base de Datos ");
              output.setData(" Descripcion de error: "+sql.getMessage());

            }
          catch(Exception ex )
            {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
            }
            output.setResponse(response);
            return output;
  }
        

    
}
