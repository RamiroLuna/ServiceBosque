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
import org.probosque.controller.ControllerConectMapa;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "ConectMapa", urlPatterns = {"/ConectMapa"})
public class ConectMapa extends HttpServlet {

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
        ControllerConectMapa controller = new ControllerConectMapa();
        Gson gson = new Gson();
        try {
            String action = request.getParameter("action");
            switch(action){
             /*   case "insertFigura":
                        output = controller.insertFigura(request);
                       break;
                       
                case "insertFiguraManual":
                        output = controller.insertFiguraManual(request);
                       break;*/
                       
                case "cargarTemas":
                       output= controller.cargarTemas(request);
                    break;
                    
                case "mostrarTemasPoligonos":
                       output = controller.mostrarTemasPoligonos(request);
                    break;
                    
                case "mostrarDescripciones":
                       output = controller.mostrarDescripcionesPoligonos(request);
                    break;
                    
                case "mostrarRegiones":
                        output = controller.mostrarRegiones(request);
                    break;
                
                case "insertTema":
                       output = controller.insertTema(request);
                    break;
                    
                case "conseguirFolio":
                       output = controller.conseguirFolio(request);
                    break;
                    
                case "mostrarLocalidades":
                       output = controller.mostrarLocalidades(request);
                    break;
                
                case "mostrarLocalRurales":
                       output = controller.mostrarLocalRurales(request);
                    break;
                    
                case "conseguirTipoFigura":
                       output = controller.conseguirTipoFigura(request);
                    break;
                    
                case "areaFigura":
                       output = controller.areaFigura(request);
                    break;
                    
                case "uploadRaster":
                       output = controller.uploadRaster(request);
                    break;
                    
                case "uploadKML":
                       output = controller.uploadKML(request);
                    break;
                    
                case "uploadServicio":
                       output = controller.uploadServicio(request);
                    break;
                    
                case "insertMapa":
                       output = controller.insertMapa(request);
                    break;
                    
                case "insertTemaAdmin":
                        output = controller.insertTemaAdmin(request);
                       break;
                       
                case "insertIndicadores":
                        output = controller.insertIndicadores(request);
                       break;
                       
                case "getListMapas":
                        output = controller.getListMapas(request);
                       break;
                       
                case "getConfRaster":
                        output = controller.getConfRaster(request);
                       break;
                       
                case "eliminarTema":
                        output = controller.eliminarTema(request);
                       break;
                               
                case "contadorTemas":
                        output = controller.contadorTemas(request);
                       break;
                       
                case "eliminarTablaTema":
                        output = controller.eliminarTablaTema(request);
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
