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
import org.probosque.controller.ControllerATP;
import org.probosque.controller.ControllerAuditoriasPreventivas;
import org.probosque.controller.ControllerEjecutivo;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author Erick_G
 */
@WebServlet(name = "AuditoriasPreventivas", urlPatterns = {"/AuditoriasPreventivas"})
public class AuditoriasPreventivas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Configuration.setHeadersJson(response);

        PrintWriter out = response.getWriter();
        OutputJson output = new OutputJson();
        ControllerATP controllerATP = new ControllerATP();
        ControllerAuditoriasPreventivas controller= new ControllerAuditoriasPreventivas();
        
        Gson gson = new Gson();
        try {
            String action = request.getParameter("action");
            switch(action){
                case "getPredio":
                     ControllerEjecutivo controllerEjecutivo = new ControllerEjecutivo();
                     output=controllerEjecutivo.getPredio(request);
                     break;
                
                case "getRepresentantes":
                     controllerEjecutivo = new ControllerEjecutivo();
                     output=controllerEjecutivo.getRepresentantes(request);
                     break;
                 case "getMunicipioPredios":
                     controllerEjecutivo = new ControllerEjecutivo();
                     output=controllerEjecutivo.getMunicipioPredios(request);
                     break;
                case "getClave":
                     controllerEjecutivo = new ControllerEjecutivo();
                     output=controllerEjecutivo.getClave(request);
                     break;
                case "getDetallePredio":
                     controllerEjecutivo = new ControllerEjecutivo();
                     output=controllerEjecutivo.getDetallesPredio(request);
                     break;
                
                case "getAllPredio":
                      controller = new ControllerAuditoriasPreventivas();
                     output=controller.getAllPredio(request);
                     break;
                     
                case "getPredioPoligonos":
                     controller = new ControllerAuditoriasPreventivas();
                     output=controller.getPredioPoligonos(request);
                     break;
                     
                case "getPredioImagen": 
                     controller = new ControllerAuditoriasPreventivas();
                     output=controller.getPredioImagen(request);
                     break;
                     
                case "getPredioRepresentantes":
                     controller = new ControllerAuditoriasPreventivas();
                     output=controller.getPredioRepresentante(request);
                     break;
                
                case "setAuditoriaTecnica":
                     controllerATP = new ControllerATP();
                     output=controllerATP.insertAuditoria(request);
                     break;           
                case "updateAuditoriaTecnica":
                      controllerATP = new ControllerATP();
                     output=controllerATP.updateAuditoria(request);
                     break;      
                case "deleteAuditoriaTecnica":
                      controllerATP = new ControllerATP();
                     output=controllerATP.deleteAuditoria(request);
                     break;                
                case "historialAuditoriaTecnica":
                      controllerATP = new ControllerATP();
                     output=controllerATP.historialAuditoria(request);
                     break;                    
            }
            
        } catch (Exception ex) {
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
