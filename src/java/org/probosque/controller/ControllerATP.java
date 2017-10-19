package org.probosque.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.AuditoriaTecnicaPreventivaDAO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

public class ControllerATP {
    public OutputJson insertAuditoria(HttpServletRequest request)
    {
        String predio= request.getParameter("idPredio");
        String auditoriaTecnica=request.getParameter("auditoria");
        auditoriaTecnica=(auditoriaTecnica.equals("true"))?"1":"2";
        String fechaAuditoria=request.getParameter("fechaAuditoria").replaceAll("-", "/");
        AuditoriaTecnicaPreventivaDAO atp = new AuditoriaTecnicaPreventivaDAO();
        ResponseJson response = new ResponseJson();
        OutputJson output = new OutputJson();
          try
           {
            atp.setAuditoria(predio, auditoriaTecnica, fechaAuditoria);
            response.setSucessfull(true);
            response.setMessage("Registro Exitoso");
           }
       catch(SQLException sql )
      {
        response.setSucessfull(false);
        response.setMessage("Ya exiten datos de inspección con la fecha:  "+fechaAuditoria+"\n Intente con una nueva fecha ");
        if(sql.getMessage().contains("llave duplicada"))
           {
           }
        else
           {
            response.setMessage("Error en la Base de Datos ");
           }
        
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
   public OutputJson updateAuditoria(HttpServletRequest request)
    {
        Integer consecutivo = Integer.parseInt(request.getParameter("consecutivo"));
        String predio= request.getParameter("idPredio");
        String auditoriaTecnica=request.getParameter("auditoria");
        auditoriaTecnica=(auditoriaTecnica.equals("true"))?"1":"2";
        String fechaAuditoria=request.getParameter("fechaAuditoria").replaceAll("-", "/");
        AuditoriaTecnicaPreventivaDAO atp = new AuditoriaTecnicaPreventivaDAO();
        ResponseJson response = new ResponseJson();
        OutputJson output = new OutputJson();
          try
           {
            atp.updateAuditoria(consecutivo,predio, auditoriaTecnica, fechaAuditoria);
            response.setSucessfull(true);
            response.setMessage("Se actualizó el registro");
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

   public OutputJson deleteAuditoria(HttpServletRequest request)
    {
        Integer consecutivo = Integer.parseInt(request.getParameter("consecutivo"));
        String predio= request.getParameter("idPredio");
        AuditoriaTecnicaPreventivaDAO atp = new AuditoriaTecnicaPreventivaDAO();
        ResponseJson response = new ResponseJson();
        OutputJson output = new OutputJson();
          try
           {
            atp.deleteAuditoria(consecutivo,predio);
            response.setSucessfull(true);
            response.setMessage("Información Eliminanda ");
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
 
   public OutputJson historialAuditoria(HttpServletRequest request)
    {
        String predio= request.getParameter("idPredio");
        AuditoriaTecnicaPreventivaDAO atp = new AuditoriaTecnicaPreventivaDAO();
        ResponseJson response = new ResponseJson();
        OutputJson output = new OutputJson();
          try
           {
            output.setData(atp.getAuditorias(predio));
            response.setSucessfull(true);
            response.setMessage("ok");
           }
       catch(SQLException sql )
      {
        response.setSucessfull(false);
        response.setMessage("Error en la base de Datos ");
        output.setData(" Descripcion de error: "+sql.getMessage());
      }
    catch(NullPointerException Nex )
      {
      response.setSucessfull(false);
      response.setMessage("No se encontro registro de ATPs ");
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
