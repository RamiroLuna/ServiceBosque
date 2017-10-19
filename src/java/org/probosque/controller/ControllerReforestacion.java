package org.probosque.controller;

import com.google.gson.Gson;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.ReforestacionDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.PadronDTO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

public class ControllerReforestacion {

public OutputJson getDatos(int tabla) throws SQLException  
  {
  OutputJson output = new OutputJson();
  ResponseJson response = new ResponseJson();
  ReforestacionDAO dao = new ReforestacionDAO();    
      
  try{
   if (tabla ==1)
     {
     output.setData(dao.getDatosTabla1());
     output.setCadena(String.valueOf(dao.getMaxRows("public.inspecciones")));
     }
   else
       if(tabla==2)
          {
         output.setData( dao.getDatosTabla2());
          }
       else
           {
           output.setData(dao.getDatosTabla3());
           
           }
      
   response.setSucessfull(true);
   
  }
catch(Exception ex )
   {
    response.setSucessfull(false);
    response.setMessage(ex.getMessage());
   
   }
      
  output.setResponse(response);
  return output;
  }

// hace update sobr un registro
public OutputJson updateRegistroPadron(HttpServletRequest request)
  {
   
    OutputJson output = new OutputJson();
    ResponseJson response = new ResponseJson();
    ReforestacionDAO dao = new ReforestacionDAO(); 
    String registroUpdate= request.getParameter("datos");
    int user = Integer.parseInt(request.getParameter("user"));
    
    Gson gson = new Gson();
      PadronDTO datos = gson.fromJson(registroUpdate.trim(), PadronDTO.class);
       try{
         UserDAO userDa = new UserDAO();
         UserDTO dto = userDa.getUser(user);       
         boolean exito=dao.UpdateRegistroPadron(dto, datos.getPadron().get(0));
         if (exito==true)
           response.setSucessfull(true);
         else
           response.setMessage("No cambio nungun dato por lo tanto no se registro movimiento ");
           response.setSucessfull(true);  
        }
      catch(Exception ex )
         {
          response.setSucessfull(false);
          response.setMessage(ex.getMessage());

         }

        output.setResponse(response);
        return output;
  
  
  }
//Contiene una liste detallada 
public OutputJson getBusqueda(HttpServletRequest request)
  {
   
    OutputJson output = new OutputJson();
    ResponseJson response = new ResponseJson();
    ReforestacionDAO dao = new ReforestacionDAO(); 
    String Columna= request.getParameter("columna");
    String Criterio= request.getParameter("criterio");
    int user = Integer.parseInt(request.getParameter("user"));
    
    Gson gson = new Gson();
      
       try{
         UserDAO userDa = new UserDAO();
         UserDTO dto = userDa.getUser(user);
         output.setData(dao.getBusquedaREforestacion(dto, Columna, Criterio));
         response.setSucessfull(true);  
        }
      catch(Exception ex )
         {
          response.setSucessfull(false);
          response.setMessage(ex.getMessage());

         }

        output.setResponse(response);
        return output;
  
  
  }

//Borra un Registro
public OutputJson DeleteReg(HttpServletRequest request)
  {
   
    OutputJson output = new OutputJson();
    ResponseJson response = new ResponseJson();
    ReforestacionDAO dao = new ReforestacionDAO(); 
    String id_padron = request.getParameter("idPadron");
    
    int user = Integer.parseInt(request.getParameter("user"));
    
    Gson gson = new Gson();
      
       try{
         UserDAO userDa = new UserDAO();
         UserDTO dto = userDa.getUser(user);
         output.setData(dao.DeleteRegistro(dto,id_padron ));
         response.setSucessfull(true);  
        }
      catch(Exception ex )
         {
          response.setSucessfull(false);
          response.setMessage(ex.getMessage());

         }

        output.setResponse(response);
        return output;
  
  
  }
  

//Obtiene solo un registro
 public OutputJson getRegistroPadron(HttpServletRequest request)
   {
    OutputJson output = new OutputJson();
    ResponseJson response = new ResponseJson();
    ReforestacionDAO dao = new ReforestacionDAO(); 
    String id_padron = request.getParameter("idPadron");
    
    int user = Integer.parseInt(request.getParameter("user"));
    
    Gson gson = new Gson();
      
       try{
         UserDAO userDa = new UserDAO();
         UserDTO dto = userDa.getUser(user);
         output.setData(dao.getDetalleRegPadron(dto,id_padron ));
         response.setSucessfull(true);  
        }
      catch(Exception ex )
         {
          response.setSucessfull(false);
          response.setMessage(ex.getMessage());

         }

        output.setResponse(response);
        return output;
   
   
   
   
   }
 
 // Registrar padron 
    public OutputJson insertPadron(HttpServletRequest request) {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ReforestacionDAO dao = new ReforestacionDAO();
  
        String _usuario = request.getParameter("usuario"); 
        String idPadronIndustrial = request.getParameter("idPadronIndustrial");
        String nombre = request.getParameter("nombre");
        String razonSocial = request.getParameter("razonSocial");
        String rfc = request.getParameter("rfc");
        String rnf = request.getParameter("rnf");
        String calle = request.getParameter("calle");
        String noExterior = request.getParameter("noExterior");
        String noInterior = request.getParameter("noInterior");
        String colonia = request.getParameter("colonia");
        String localidad = request.getParameter("localidad");
        String municipio = request.getParameter("municipio");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String latitud = request.getParameter("latitud");
        String longitud = request.getParameter("longitud");
        String tipo = request.getParameter("tipo");
        String actividad = request.getParameter("actividad");
        String fechaAlta = request.getParameter("fechaAlta");
        String observaciones = request.getParameter("observaciones");
        String estatus = request.getParameter("estatus");
        String claveReg = request.getParameter("claveReg");
        String claveMun = request.getParameter("claveMun");
        String claveLoc = request.getParameter("claveLoc");       
        


        try{
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_usuario));
        output.setData(dao.insertPadron(user,claveLoc,claveReg,claveMun,idPadronIndustrial,nombre,razonSocial,rfc,rnf,calle,noExterior,noInterior,colonia,localidad,municipio,telefono,correo,latitud,longitud,tipo,actividad,fechaAlta,observaciones,estatus));
        response.setSucessfull(true);
        response.setMessage("Se registro padrón correctamente");
       
        } catch(Exception ex )
         {
          response.setSucessfull(false);
          response.setMessage(ex.getMessage());

         }
        output.setResponse(response);
        return output;
 
    }
    
        public OutputJson getMunicipio(HttpServletRequest request) {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ReforestacionDAO dao = new ReforestacionDAO();
  
        String _usuario = request.getParameter("usuario"); 
        String idRegion = request.getParameter("id");

        UserDAO userDao = new UserDAO();
        UserDTO user;
       try {
        user = userDao.getUser(Integer.parseInt(_usuario));
        output.setData(dao.getMunicipio(user,idRegion));
        response.setSucessfull(true);
        response.setMessage("Obtención correcta!!!");
        } catch (Exception ex) {
         response.setSucessfull(false);
          response.setMessage(ex.getMessage());
        }   
       
        output.setResponse(response);
        return output;
 
    }
            
            
    public OutputJson getLocalidad(HttpServletRequest request)  {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ReforestacionDAO dao = new ReforestacionDAO();
  
        String _usuario = request.getParameter("usuario"); 
        String idMunicipio = request.getParameter("id");

        UserDAO userDao = new UserDAO();
        UserDTO user;
    try {
        user = userDao.getUser(Integer.parseInt(_usuario));
        output.setData(dao.getLocalidad(user,idMunicipio));
        response.setSucessfull(true);
        response.setMessage("Obtención correcta!!!");
    } catch (Exception ex) {
       response.setSucessfull(false);
       response.setMessage(ex.getMessage());
    }
       
        output.setResponse(response);
        return output;
 
    }
    
    public OutputJson updateStatus(HttpServletRequest request) {

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ReforestacionDAO dao = new ReforestacionDAO();
  
        String _usuario = request.getParameter("usuario"); 
        String idPadron = request.getParameter("idPadron");

        UserDAO userDao = new UserDAO();
        UserDTO user;
        try {
            user = userDao.getUser(Integer.parseInt(_usuario));
            output.setData(dao.updateStatus(user,idPadron));
            response.setSucessfull(true);
            response.setMessage("Se actualizo!!!");
        } catch (Exception ex) {
             response.setSucessfull(false);
             response.setMessage(ex.getMessage());
        }
       
        output.setResponse(response);
        return output;

    }
public OutputJson getClave(HttpServletRequest request) throws Exception
    {
     ResponseJson response = new ResponseJson();
     OutputJson output= new OutputJson();
     
     String tipoIndustria=request.getParameter("actividad");
     String idRegion=request.getParameter("idRegion");
     String idMunicipio=request.getParameter("idMunicipio");
     
     try {
         
         ReforestacionDAO dao = new ReforestacionDAO();
         
         output.setData(dao.getFolio( tipoIndustria, idRegion, idMunicipio ));
         response.setSucessfull(true);
         }
    catch(Exception ex )
         {
         response.setMessage(ex.getMessage());
         response.setSucessfull(false);
         }
    
    output.setResponse(response);
    return output;
    }
 


    
}
