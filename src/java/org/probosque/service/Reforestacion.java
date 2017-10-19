/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.service;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.probosque.controller.ControllerCartoAdmin;
import org.probosque.controller.ControllerReforestacion;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author MIKE
 */
@WebServlet(name = "Reforestacion", urlPatterns = {"/Reforestacion"})
public class Reforestacion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             Configuration.setHeadersJson(response);
             PrintWriter out = response.getWriter();
             OutputJson output = new OutputJson();
             Gson gson = new Gson();
             
             String action=request.getParameter("action");
                try
                   {
                   ControllerReforestacion controller = new ControllerReforestacion();    
                    switch(action)
                           {
                        case "tabla1":
                                        
                                        output = controller.getDatos(1);
                                   break;
                        case "tabla2":
                                        
                                        output = controller.getDatos(2);
                                   break;
                        case "tabla3":
                                        
                                        output = controller.getDatos(3);
                                   break;
                            case "getBusqueda":
                             output= controller.getBusqueda(request);
                             break;
                        case "getRegistro":
                             output= controller.getRegistroPadron(request);
                              break;
                        case "deleteReg":
                             output= controller.DeleteReg(request);
                              break;
                        case "updateRegistro":
                             output= controller.updateRegistroPadron(request);
                            
                            break;
                        case "updateRegPadron": 
                             output = controller.updateRegistroPadron(request);
                            break;
                        case "insertPadron":
                             output = controller.insertPadron(request);
                            break;
                        case "getMunicipio":
                             output = controller.getMunicipio(request);
                       break;                       
                       case "getLocalidad":
                             output = controller.getLocalidad(request);
                       break;
                       case "updateStatus":
                        output = controller.updateStatus(request);
                       break;  
                      case "getFolio":
                          output=controller.getClave(request);
                          break;           
                            
                            }
                    }
                 catch (Exception ex)
                     {
                      ResponseJson reponseJson = new ResponseJson();
                      reponseJson.setSucessfull(false);
                      reponseJson.setMessage(ex.getMessage());
                      output.setResponse(reponseJson);
                    } finally {
                        out.print(gson.toJson(output));
                        out.close();
                    }
             
             
             
  
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
