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
import org.probosque.controller.ControllerProduccionSemilla;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author Jonathan
 */
@WebServlet(name = "ProduccionSemilla", urlPatterns = {"/ProduccionSemilla"})
public class ProduccionSemilla extends HttpServlet {

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
        
        ControllerProduccionSemilla controller = new ControllerProduccionSemilla();
        
        Gson gson = new Gson();
        boolean identify = false;
        try {
            String action = request.getParameter("action");
                switch (action) {
                    case "DtoBGP":
                        output = controller.DatosBancoGermoplasma(request);
                        break;
                    case "total":
                        output = controller.TotalBancoGermoplasma(request);
                        break;
                    case "DtoDGP":
                        output = controller.DatosDestinoGermoplasma(request);
                        break;
                     case "TotalDGP":
                        output = controller.DatosTotalDestinoGermoplasma(request);
                        break;
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
