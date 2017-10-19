package org.probosque.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.CoordinadorDAO;
import org.probosque.dao.SemaforoReforestacionDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

public class ControllerCoordinador {

    public OutputJson getPanel(HttpServletRequest request) throws Exception {
        int _user = Integer.parseInt(request.getParameter("user"));
        UserDAO userDAO = new UserDAO();
        UserDTO dto = userDAO.getUser(_user);
        CoordinadorDAO dao = new CoordinadorDAO();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        try {
            output.setData(dao.getPanelPrincipalCoordinador(dto));
            output.setCadena(dao.getPendientesOficialia(dto));
            response.setSucessfull(true);
            response.setMessage(" ");
        } catch (SQLException sql) {
            response.setSucessfull(false);
            response.setMessage("Error en la base de Datos ");
            output.setData(" Descripcion de error: " + sql.getMessage());

        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;
    }

    public OutputJson AsignarEstatusReforestacion(HttpServletRequest request) throws Exception {
        int _user = Integer.parseInt(request.getParameter("user"));
        String estatus = request.getParameter("estatus");
        String Observaciones = request.getParameter("Observaciones");
        String noExpediente = request.getParameter("noExpediente");
        UserDAO userDAO = new UserDAO();
        UserDTO dto = userDAO.getUser(_user);
        CoordinadorDAO dao = new CoordinadorDAO();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        try {
            SemaforoReforestacionDAO DAORef = new SemaforoReforestacionDAO();
            if (estatus.contains("21") || estatus.contains("22") || estatus.contains("23") || estatus.contains("24")
                    || estatus.contains("30") || estatus.contains("31") || estatus.contains("32")
                    || estatus.contains("33") || estatus.contains("34") || estatus.contains("35")) {
                
                    Boolean Encontrado=DAORef.getExisteSemaforizacion(dto, noExpediente);
                    if(Encontrado==false)
                       {
                       DAORef.setTable_estatusReforestacion(dto, noExpediente, estatus,"ROJO",DAORef.getDetalleEstatus(dto, estatus));
                       }
                   else
                      {
                      DAORef.setUpdate_Table_estatusReforestacion(dto, noExpediente, estatus,"ROJO",DAORef.getDetalleEstatus(dto, estatus));                      
                              } 
                //DAORef.updateSemaforoReforestacion(dto, noExpediente, "ROJO", DAORef.getDetalleEstatus(dto, estatus),estatus);    
                //DAORef.updateSemaforoReforestacion(dto, noExpediente, "ROJO", DAORef.getDetalleEstatus(dto, estatus));
            } else if (estatus.contains("39") || estatus.contains("40")) {
            
                Boolean Encontrado=DAORef.getExisteSemaforizacion(dto, noExpediente);
                    if(Encontrado==false)
                       {
                       DAORef.setTable_estatusReforestacion(dto, noExpediente, estatus,"VERDE",DAORef.getDetalleEstatus(dto, estatus));
                       }
                   else
                      {
                      DAORef.setUpdate_Table_estatusReforestacion(dto, noExpediente, estatus,"VERDE",DAORef.getDetalleEstatus(dto, estatus));                      
                      } 
                //DAORef.updateSemaforoReforestacion(dto, noExpediente, "VERDE", DAORef.getDetalleEstatus(dto, estatus),estatus);     
                //DAORef.updateSemaforoReforestacion(dto, noExpediente, "VERDE", DAORef.getDetalleEstatus(dto, estatus));
            }
            if (!Observaciones.isEmpty()) {
                DAORef.setObservacionesPublicas(dto, Observaciones, noExpediente);
            }
           response.setSucessfull(true); 
        } catch (SQLException sql) {
            response.setSucessfull(false);
            response.setMessage("Error en la base de Datos ");
            output.setData(" Descripcion de error: " + sql.getMessage());

        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;

    }

    public OutputJson terminarExpediente(HttpServletRequest request) throws Exception {
        int _user = Integer.parseInt(request.getParameter("user"));
        String noExpediente = request.getParameter("noExpediente");
        UserDAO userDAO = new UserDAO();
        UserDTO dto = userDAO.getUser(_user);
        CoordinadorDAO dao = new CoordinadorDAO();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        try {
            SemaforoReforestacionDAO DAORef = new SemaforoReforestacionDAO();
            Boolean Encontrado = DAORef.getExisteSemaforizacion(dto, noExpediente);
            if (Encontrado == true) {
                String estatus=DAORef.getEstatus(dto, noExpediente);
                if (estatus.contains("21") || estatus.contains("22") || estatus.contains("23") || estatus.contains("24")
                        || estatus.contains("30") || estatus.contains("31") || estatus.contains("32")
                        || estatus.contains("33") || estatus.contains("34") || estatus.contains("35")) {

                    DAORef.updateSemaforoReforestacion(dto, noExpediente, "ROJO",
                            DAORef.getDetalleEstatus(dto, estatus), estatus);
                } else if (estatus.contains("39") || estatus.contains("40")) {

                    DAORef.updateSemaforoReforestacion(dto, noExpediente, "VERDE",
                            DAORef.getDetalleEstatus(dto, estatus), estatus);

                }
                DAORef.deleteNoExpediente(dto, noExpediente);
                output.setData(dao.terminarExpediente(dto, noExpediente));
                response.setSucessfull(true);
            } else {
                response.setMessage(" El expediente no puede ser terminado debido a que"
                        + " no tiene asignado un estatus de reforestamos méxico ");
                response.setSucessfull(false);
            }

        } catch (SQLException sql) {
            response.setSucessfull(false);
            response.setMessage("Error en la base de Datos ");
            output.setData(" Descripcion de error: " + sql.getMessage());

        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;
    }

    public OutputJson getSeguimiento(HttpServletRequest request) throws Exception {
        int _user = Integer.parseInt(request.getParameter("user"));

        UserDAO userDAO = new UserDAO();
        UserDTO dto = userDAO.getUser(_user);
        CoordinadorDAO dao = new CoordinadorDAO();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();

        try {
            output.setData(dao.getSeguimiento(dto));
            output.setCadena(dao.getPendientesOficialia(dto));
            response.setSucessfull(true);
            response.setMessage(" ");
        } catch (SQLException sql) {
            response.setSucessfull(false);
            response.setMessage("Error en la base de Datos ");
            output.setData(" Descripcion de error: " + sql.getMessage());

        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;
    }

    public OutputJson getConcluidas(HttpServletRequest request) throws Exception {
        int _user = Integer.parseInt(request.getParameter("user"));
        UserDAO userDAO = new UserDAO();
        UserDTO dto = userDAO.getUser(_user);
        CoordinadorDAO dao = new CoordinadorDAO();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        try {
            output.setData(dao.getConcluidas(dto));
            output.setCadena(dao.getPendientesOficialia(dto));
            response.setSucessfull(true);
            response.setMessage(" ");
        } catch (SQLException sql) {
            response.setSucessfull(false);
            response.setMessage("Error en la base de Datos ");
            output.setData(" Descripcion de error: " + sql.getMessage());

        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;
    }

    public OutputJson impugnar(HttpServletRequest request) throws Exception {

        int _user = Integer.parseInt(request.getParameter("user"));
        String estatus = request.getParameter("estatus");
        String noExpediente = request.getParameter("noExpediente");
        UserDAO userDAO = new UserDAO();
        UserDTO dto = userDAO.getUser(_user);
        CoordinadorDAO dao = new CoordinadorDAO();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        try {
            output.setData(dao.setImpugnar(dto, noExpediente, estatus));
            response.setSucessfull(true);
            response.setMessage(" ");
        } catch (SQLException sql) {
            response.setSucessfull(false);
            response.setMessage("Error en la base de Datos ");
            output.setData(" Descripcion de error: " + sql.getMessage());

        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;
    }

    public OutputJson getImpugnaciones(HttpServletRequest request) throws Exception {
        int _user = Integer.parseInt(request.getParameter("user"));
        UserDAO userDAO = new UserDAO();
        UserDTO dto = userDAO.getUser(_user);
        CoordinadorDAO dao = new CoordinadorDAO();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        try {
            output.setData(dao.getImpugnaciones(dto));
            output.setCadena(dao.getPendientesOficialia(dto));
            response.setSucessfull(true);
            response.setMessage(" ");
        } catch (SQLException sql) {
            response.setSucessfull(false);
            response.setMessage("Error en la base de Datos ");
            output.setData(" Descripcion de error: " + sql.getMessage());

        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;

    }

    public OutputJson Reposicion(HttpServletRequest request) {
        String user=request.getParameter("user");
        String noExpediente=request.getParameter("noExpediente");
        String Observaciones=request.getParameter("observaciones");
        String fecha=request.getParameter("fecha");

        UserDAO userDAO = new UserDAO();
        CoordinadorDAO dao = new CoordinadorDAO();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
       try
          {
           UserDTO dto = userDAO.getUser(Integer.parseInt(user));   
           dao.reponer(dto, noExpediente);
           Boolean addObs=dao.addObservacion(dto, Observaciones, fecha, noExpediente, "37,38,41");
           response.setSucessfull(true);
           
         } catch (SQLException sql) {
            response.setSucessfull(false);
            response.setMessage("Error en la base de Datos ");
            output.setData(" Descripcion de error: " + sql.getMessage());

        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;
    }


    public OutputJson changeExpediente(HttpServletRequest request) {
        String user=request.getParameter("user");
        String noExpedienteNuevo=request.getParameter("noExpedienteNuevo");
        String noExpedienteViejo=request.getParameter("noExpediente");
        
        UserDAO userDAO = new UserDAO();
        CoordinadorDAO dao = new CoordinadorDAO();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
       try
          {
           UserDTO dto = userDAO.getUser(Integer.parseInt(user));   
           dao.changeExpediente(dto, noExpedienteNuevo, noExpedienteViejo);
           response.setSucessfull(true);
           
         } catch (SQLException sql) {
            response.setSucessfull(false);
            response.setMessage("Error en la base de Datos ");
            output.setData(" Descripcion de error: " + sql.getMessage());

        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;
    }
}
