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
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "CartoAdmin", urlPatterns = {"/CartoAdmin"})
public class CartoAdmin extends HttpServlet {

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
        ControllerCartoAdmin controller = new ControllerCartoAdmin();
        Gson gson = new Gson();
        try {
            String action = request.getParameter("action");
            switch(action){
                case "insertMapa":
                        output = controller.insertMapa(request);
                       break;
                       
                case "getFiguraType":
                        output = controller.getFiguraType(request);
                       break;
                       
                case "recuperacionPopUp":
                        output = controller.recuperacionPopUp(request);
                       break;
                       
                case "insertTemaAdmin":
                        output = controller.insertTemaAdmin(request);
                       break;
                
                case "getListTemas":
                        output = controller.getListTemas(request);
                       break;
                               
                case "getListIndicadores":
                        output = controller.getListIndicadores(request);
                       break;
                       
                case "getListMapas":
                        output = controller.getListMapas(request);
                       break;
                       
                case "eliminarTemas":
                        output = controller.eliminarTemas(request);
                       break;
                
                case "guardarMapa":
                        output = controller.guardarMapa(request);
                       break;
                       
                case "eliminarMapas":
                        output = controller.eliminarMapas(request);
                       break;
                               
                case "insertIndicadores":
                        output = controller.insertIndicadores(request);
                       break;
                               
                case "eliminarIndicador":
                        output = controller.eliminarIndicador(request);
                       break;
                               
                case "pintarMapaTemas":
                        output = controller.pintarMapaTemas(request);
                       break;
                               
                case "pintarMapaIndicador":
                        output = controller.pintarMapaIndicador(request);
                       break;
                    
            }
            //output = controller.insertFigura(request);
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

 // <editor-fold defaultstate="collapsed" desc="Métodos HttpServlet.">
    /**
     * Maneja los métodos HTTP <code>GET</code>.
     *
     * @param request petición servlet
     * @param response respuesta servlet
     * @throws ServletException si se produce un error específico del servlet
      * throws IOException si se produce un error de E / S
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Maneja los métodos HTTP <code>POST</code>.
     *
     * @param request petición servlet
     * @param response respuesta servlet
     * @throws ServletException si se produce un error específico del servlet
      * throws IOException si se produce un error de E / S
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        Configuration.setHeadersJson(response);
    }

    /**
     * Proporciona una información breve del servlet. 
     *
     * @return String que contiene descripción del servlet
     */
    @Override
    public String getServletInfo() {
        return "Conect Mapa";
    }
    // </editor-fold>

}
