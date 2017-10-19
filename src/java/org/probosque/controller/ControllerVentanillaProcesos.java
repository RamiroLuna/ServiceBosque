/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.UserDAO;
import org.probosque.dao.VentanillaDAO;
import org.probosque.dto.CatalogoDTO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author Jonathan
 */
public class ControllerVentanillaProcesos {
    
    public OutputJson InsertaDictaminacion(HttpServletRequest request) {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String fecha_cambio = request.getParameter("fecha_cambio_estatus");
        String fecha_envio_t = request.getParameter("dte");
        String fecha_envio_j = request.getParameter("dje");
        String fecha_envio_a = request.getParameter("dae");
        String fecha_recep_t = request.getParameter("djr");
        String fecha_recep_j = request.getParameter("dtr");
        String fecha_recep_a = request.getParameter("dar");
        String observaciones = request.getParameter("observaciones");
        String respuesta = request.getParameter("respuesta");
        String estatus_anterior = request.getParameter("estatus_anterior");
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.InsertDictaminacion(user, folio, fecha_cambio, fecha_envio_t, fecha_envio_j, fecha_envio_a, fecha_recep_t, fecha_recep_j, fecha_recep_a, observaciones, respuesta, Integer.parseInt(estatus_anterior)));
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson UpdateDictaminacion(HttpServletRequest request) {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String fecha_envio_t = request.getParameter("dje");
        String fecha_envio_j = request.getParameter("dte");
        String fecha_envio_a = request.getParameter("dae");
        String fecha_recep_t = request.getParameter("dtr");
        String fecha_recep_j = request.getParameter("djr");
        String fecha_recep_a = request.getParameter("dar");
        String observaciones = request.getParameter("observaciones");
        String consecutivo = request.getParameter("consecutivo");
        String respuesta = request.getParameter("respuesta");
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.UpdateDictaminacion(user, folio, fecha_envio_t, fecha_envio_j, fecha_envio_a, fecha_recep_t, fecha_recep_j, fecha_recep_a, observaciones, Integer.parseInt(consecutivo), respuesta));
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson InsertaRechazo(HttpServletRequest request) {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String fecha_cambio = request.getParameter("fecha_cambio_estatus");
        String observaciones = request.getParameter("observaciones");
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.InsertRechazo(user, folio, fecha_cambio, observaciones));
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
        return output;
    }
    
     public OutputJson UpdateRechazo(HttpServletRequest request) {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String observaciones = request.getParameter("observaciones");
        String consecutivo = request.getParameter("consecutivo");
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.UpdateRechazo(user, folio, observaciones, Integer.parseInt(consecutivo)));
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson AddFecha(HttpServletRequest request) {
        String _user = request.getParameter("user");
        String fecha = request.getParameter("fecha");
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.InsertFechaExclusa(user, fecha));
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson getFecha(HttpServletRequest request) {
        String _user = request.getParameter("user");
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            List<CatalogoDTO> fechas = dao.getListFechas(user);
            output.setData(fechas);
            response.setSucessfull(true);
        } catch (Exception ex) {
            Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson deleteFecha(HttpServletRequest request) {
        String _user = request.getParameter("user");
        String indice = request.getParameter("indice");
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.deleteFechaExclusa(user, Integer.parseInt(indice)));
            response.setSucessfull(true);
        } catch (Exception ex) {
            Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        
        //response.setMessage("Formulario");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson GetSolicitudes(HttpServletRequest request) {
        String _user = request.getParameter("user");
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.getSolicitudes(user));
            response.setSucessfull(true);
        } catch (Exception ex) {
            Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
        return output;
    }
    
    public OutputJson UltimaFecha(HttpServletRequest request) {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.UltimaFecha(user, folio));
            response.setSucessfull(true);
        } catch (Exception ex) {
            Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
        return output;
    }
    
    
    public OutputJson GetVuelta(HttpServletRequest request){
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.SelectVuelta(user, folio));
            response.setSucessfull(true);
        } catch (Exception ex) {
            Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;
    }
    
    public OutputJson UpdateVuelta(HttpServletRequest request){
       String _user = request.getParameter("user");
        String nuevoEstatus = request.getParameter("estatus");
        String folio = request.getParameter("folio");
        String vuelta = request.getParameter("vuelta");
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.UpdateVuelta(user, nuevoEstatus, folio, Integer.parseInt(vuelta)));
            response.setSucessfull(true);
        } catch (Exception ex) {
            Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;
    }
    
    public OutputJson UpdateEstatus(HttpServletRequest request){
       String _user = request.getParameter("user");
        String nuevoEstatus = request.getParameter("estatus");
        String folio = request.getParameter("folio");
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.UpdateEstatus(user, nuevoEstatus, folio));
            response.setSucessfull(true);
        } catch (Exception ex) {
            Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;
    }
    
    /// Codigo Ventanilla Mike 
public OutputJson InsertaInfoAdicional(HttpServletRequest request) {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String fecha_cambio = request.getParameter("fecha_cambio_estatus");
        String observaciones = request.getParameter("observaciones");
       
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.InsertInformacionAdicional(user, folio, fecha_cambio, observaciones));
            dao.UpdateEstatus(user, "3", folio);
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
        return output;
    }

public OutputJson UpdateInfoAdicional(HttpServletRequest request) {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String fecha_cambio = request.getParameter("fecha_cambio_estatus");
        String observaciones = request.getParameter("observaciones");
        int consecutivo=Integer.parseInt(request.getParameter("consecutivo"));
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.UpdateInformacionAdicional(user, folio, fecha_cambio, observaciones,consecutivo));
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
        return output;
    }

public OutputJson getInfoAdicoinalDictaminacion(HttpServletRequest request) {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String fecha_cambio = request.getParameter("fecha_cambio_estatus");
        String observaciones = request.getParameter("observaciones");
        
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.InformacionAdicionalDictaminacion(user, folio, fecha_cambio, observaciones));
            dao.UpdateEstatus(user, "1", folio);
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
        return output;
    }


public OutputJson InsertComiteFomento(HttpServletRequest request )
 {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String fecha_cambio = request.getParameter("fecha_cambio_estatus");
        String observaciones = request.getParameter("observaciones");
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
         try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.InsertComiteFomento(user, folio, fecha_cambio, observaciones));
            dao.UpdateEstatus(user, "4", folio);
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
      return output;
 }

public OutputJson UpdateComiteFomento(HttpServletRequest request )
 {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String fecha = request.getParameter("fecha_cambio_estatus");
        String respuesta = request.getParameter("respuesta");
        String observaciones = request.getParameter("observaciones");
        int consecutivo = Integer.parseInt(request.getParameter("consecutivo"));
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
         try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.UpdateComiteFomento(user, folio, observaciones,respuesta,consecutivo,fecha));
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
      return output;
 }

public OutputJson InsertCodigoIdentificacion(HttpServletRequest request )
 {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String fecha = request.getParameter("fecha_cambio_estatus");
        String observaciones = request.getParameter("observaciones");
        String fecha_oficio = request.getParameter("fecha_oficio");
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
         try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.InsertCodigoIdentificacion(user, folio,fecha, observaciones, fecha_oficio));
            dao.UpdateEstatus(user, "5", folio);
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
      return output;
 }

public OutputJson UpdateCodigoIdentificacion(HttpServletRequest request )
 {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String observaciones = request.getParameter("observaciones");
        String respuesta = request.getParameter("respuesta");
        String fecha_recepcion_codigo = request.getParameter("fecha_recepcion");
        String fecha_recepcion_oficio= request.getParameter("fecha_oficio");
        int consecutivo = Integer.parseInt(request.getParameter("consecutivo"));
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
         try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.UpdateCodigoIdentificacion(user, folio,observaciones,fecha_recepcion_codigo,respuesta,consecutivo,fecha_recepcion_oficio));
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
      return output;
 }

public OutputJson InsertNegacion(HttpServletRequest request )
 {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String fecha = request.getParameter("fecha_cambio_estatus");
        String observaciones = request.getParameter("observaciones");
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
         try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.InsertNegacion(user, folio,fecha, observaciones));
            dao.UpdateEstatus(user, "7", folio);
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
      return output;
 }

public OutputJson UpdateNegacion(HttpServletRequest request )
 {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String observaciones = request.getParameter("observaciones");
        int consecutivo = Integer.parseInt(request.getParameter("consecutivo"));
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
         try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.UpdateNegacion(user, folio,observaciones,consecutivo));
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
      return output;
 }

public OutputJson InsertMIA(HttpServletRequest request )
 {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String fecha = request.getParameter("fecha_cambio_estatus");
        String observaciones = request.getParameter("observaciones");
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
         try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.InsertMIA(user, folio,fecha, observaciones));
            dao.UpdateEstatus(user, "9", folio);
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
      return output;
 }

public OutputJson UpdateMIA(HttpServletRequest request )
 {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String observaciones = request.getParameter("observaciones");
        String fecha_recepcion_codigo=request.getParameter("fecha_recepcion");
        int consecutivo = Integer.parseInt(request.getParameter("consecutivo"));
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
         try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.UpdateMIA(user, folio,observaciones,fecha_recepcion_codigo,consecutivo));
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
      return output;
 }

public OutputJson InsertVencidas(HttpServletRequest request )
 {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String fecha = request.getParameter("fecha_cambio_estatus");
        String observaciones = request.getParameter("observaciones");
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
         try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.InsertVencidas(user, folio,fecha, observaciones));
            dao.UpdateEstatus(user, "8", folio);
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
      return output;
 }

public OutputJson UpdateVencidas(HttpServletRequest request )
 {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        String observaciones = request.getParameter("observaciones");
        String fecha_recepcion_codigo=request.getParameter("fecha_recepcion");
        int consecutivo = Integer.parseInt(request.getParameter("consecutivo"));
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
         try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.UpdateVencidas(user, folio,observaciones,consecutivo));
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
      return output;
 }

public OutputJson InsertAutorizadas(HttpServletRequest request )
 {
        String _user = request.getParameter("user");
        String folio = request.getParameter("folio");
        
        String fecha_recepcion_codigo=request.getParameter("fecha_cambio_estatus");
        
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
         try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            VentanillaDAO dao = new VentanillaDAO();
            output.setData(dao.InsertAutorizadas(user, folio,fecha_recepcion_codigo));
            dao.UpdateEstatus(user, "6", folio);
            response.setSucessfull(true);
        } catch (Exception ex) {
             Logger.getLogger(ControllerSubTable.class.getName()).log(Level.SEVERE, null, ex);
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        //response.setMessage("Formulario");
        output.setResponse(response);
      return output;
 }






//Codigo ventanilla mike 
}
