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
import org.probosque.controller.ControllerVentanillaProcesos;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author Jonathan
 */
@WebServlet(name = "VentanillaProcesos", urlPatterns = {"/VentanillaProcesos"})
public class VentanillaProcesos extends HttpServlet {

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
        ControllerVentanillaProcesos controller = new ControllerVentanillaProcesos();
        //String a =""
        Gson gson = new Gson();
        boolean identify = false;
        try {
            String action = request.getParameter("action");
                switch (action) {
                    case "insertDictaminacion":
                        output = controller.InsertaDictaminacion(request);
                        break;
                    case "updateDictaminacion":
                        output = controller.UpdateDictaminacion(request);
                        break;
                    case "insertRechazo":
                        output = controller.InsertaRechazo(request);
                        break;
                    case "updateRechazo":
                        output = controller.UpdateRechazo(request);
                        break;
                    case "addFecha":
                        output = controller.AddFecha(request);
                        break;
                    case "getFechas":
                        output = controller.getFecha(request);
                        break;
                    case "deleteFecha":
                        output = controller.deleteFecha(request);
                        break;
                    case "getSolicitudes":
                        output = controller.GetSolicitudes(request);
                        break;
                    case "vuelta":
                        output = controller.GetVuelta(request);
                        break;
                    case "updateVuelta":
                        output = controller.UpdateVuelta(request);
                        break;
                    case "updateEstatus":
                        output = controller.UpdateEstatus(request);
                        break;
                    case "ultimaFecha":
                        output = controller.UltimaFecha(request);
                        break;
                        //codigo mike 
                    case "InsertInfoAdicional":    
                         output = controller.InsertaInfoAdicional(request);
                        break;       
                    case "UpdateInfoAdicional":    
                         output = controller.UpdateInfoAdicional(request);
                        break;          
                      
                    case "infoAdicoinalDictaminacion":    
                         output = controller.getInfoAdicoinalDictaminacion(request);
                         break;
                         
                    case "InsertcomiteFomento":
                          output= controller.InsertComiteFomento(request);
                          break;
                     case "UpdatecomiteFomento":
                          output= controller.UpdateComiteFomento(request);
                          break;     
                    case "InsertCodigo":
                          output= controller.InsertCodigoIdentificacion(request);
                          break;
                    case "UpdateCodigo":
                          output= controller.UpdateCodigoIdentificacion(request);
                          break;     
                    case "InsertNegacion":
                          output= controller.InsertNegacion(request);
                          break;
                    case "UpdateNegacion":
                          output= controller.UpdateNegacion(request);
                          break;     
                    case "InsertMIA":
                          output= controller.InsertMIA(request);
                          break;
                    case "UpdateMIA":
                          output= controller.UpdateMIA(request);
                          break;     
                         
                    case "InsertVencidas":
                          output= controller.InsertVencidas(request);
                          break;
                    case "UpdateVencidas":
                          output= controller.UpdateVencidas(request);
                          break;  
                    case "InsertAutorizadas":
                          output= controller.InsertAutorizadas(request);
                          break;      
                         
                         
                         //
                         //Codigo Mike 
                }
        } catch (Exception ex) {
            //Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Service:Table", ex);
            ResponseJson reponseJson = new ResponseJson();
            reponseJson.setSucessfull(false);
            reponseJson.setMessage(ex.getMessage());
            output.setResponse(reponseJson);
        } finally {
            if(!identify){
            out.print(gson.toJson(output));
            out.close();
            }
            else{
                String data = String.valueOf(output.getData());
            
            int[] dato = {123, 123};
            output.setData(dato);
            String json = gson.toJson(output);
            json = json.replace("[123,123]", data);
            out.print(json);
            out.close();
            }
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
