package org.probosque.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.CoordinadorDAO;
import org.probosque.dao.OficialiaDAO;
import org.probosque.dao.SemaforoReforestacionDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

public class ControllerConsultasOficialia {

public OutputJson getPanel(HttpServletRequest request) throws Exception
   {
   
       
    OutputJson output = new OutputJson();
    String _user = request.getParameter("idUser");
    ResponseJson response = new ResponseJson();
    UserDAO userDao = new UserDAO();
    UserDTO user = userDao.getUser(Integer.parseInt(_user));  
    OficialiaDAO dao = new OficialiaDAO();
    try
       {
       output.setData(dao.getInspecionesIndustria(user));
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

public OutputJson getAbogados(HttpServletRequest request) throws Exception
   {
   
       
    OutputJson output = new OutputJson();
    String _user = request.getParameter("idUser");
    ResponseJson response = new ResponseJson();
    UserDAO userDao = new UserDAO();
    UserDTO user = userDao.getUser(Integer.parseInt(_user));  
    OficialiaDAO dao = new OficialiaDAO();
    try
       {
       output.setData(dao.getUserAbogado());
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

   public OutputJson setExpediente(HttpServletRequest request) throws Exception
   {
   
       
    OutputJson output = new OutputJson();
    String _user = request.getParameter("idUser");
    String expediente=request.getParameter("expediente");
    String folio=request.getParameter("folio");
    
    ResponseJson response = new ResponseJson();
    UserDAO userDao = new UserDAO();
    UserDTO user = userDao.getUser(Integer.parseInt(_user));  
    OficialiaDAO dao = new OficialiaDAO();
    try
       {   //NO EXISTE 
           if(dao.validarExpedienteExist(user, expediente) == 0)
               {
               dao.insertExpediente(user, expediente, folio);
               response.setSucessfull(true);
               response.setMessage(" Se asigno expediente ");
               }
           else 
               {
               //Existe 
                response.setSucessfull(false);
                response.setMessage(" El número de expediente ya fue asignado. Ingrese uno nuevo");
               }
       
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

public OutputJson setAbogado(HttpServletRequest request) throws Exception
   {
   
       
    OutputJson output = new OutputJson();
    String _user = request.getParameter("idUser");
    String expediente=request.getParameter("expediente");
    String abogado=request.getParameter("idAbogado");
    
    ResponseJson response = new ResponseJson();
    UserDAO userDao = new UserDAO();
    UserDTO user = userDao.getUser(Integer.parseInt(_user));  
    OficialiaDAO dao = new OficialiaDAO();
    try
       {    
              response.setSucessfull(true);
               response.setMessage("Se asigno abogado al expediente "+expediente);
               dao.InsertAbogado(user, Integer.parseInt(abogado), expediente);
                   
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

public OutputJson setEstatus(HttpServletRequest request) throws Exception
   {
   
       
    OutputJson output = new OutputJson();
    String _user = request.getParameter("idUser");
    String estatus=request.getParameter("estatus");
    String expediente=request.getParameter("noExpediente");
    
    ResponseJson response = new ResponseJson();
    UserDAO userDao = new UserDAO();
    UserDTO user = userDao.getUser(Integer.parseInt(_user));  
    OficialiaDAO dao = new OficialiaDAO();
    SemaforoReforestacionDAO  DAORef = new SemaforoReforestacionDAO();
    try
       {    
              response.setSucessfull(true);
              response.setMessage("Cambió el estatus del expediente: "+expediente);
              //dao.InsertStatus(user, estatus, expediente);
              if(estatus.contains("1"))
                  {
                  DAORef.updateSemaforoReforestacion(user, expediente, "AMARILLO", DAORef.getDetalleEstatus(user, estatus),estatus);
                      //DAORef.updateSemaforoReforestacion(user, expediente, "AMARILLO", DAORef.getDetalleEstatus(user, estatus));
                  }
              else
                  {
                  if(estatus.contains("2") || estatus.contains("3") || estatus.contains("4")
                     || estatus.contains("5") || estatus.contains("6") || estatus.contains("7")
                         || estatus.contains("8") || estatus.contains("9") 
                          )
                       {
                       DAORef.updateSemaforoReforestacion(user, expediente, "NARANJA", DAORef.getDetalleEstatus(user, estatus),estatus);    
                       //DAORef.updateSemaforoReforestacion(user, expediente, "NARANJA", DAORef.getDetalleEstatus(user, estatus));
                       }
                  } 
              
             if(user.getProgram()==15 && user.getActivity()==1)
               {
                   dao.setEstatusAtendidoP8(user,expediente);
                   dao.setFechaAtencionAbogado(user,expediente);
               }
          /*  if(user.getActivity()==3)
               {
                   DAORef.deleteNoExpediente(user, expediente);
                   CoordinadorDAO coordinador = new CoordinadorDAO();
                   coordinador.changeUser(user, expediente,"ABOGADO");
               }*/

           
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
