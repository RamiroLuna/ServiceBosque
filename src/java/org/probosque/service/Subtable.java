/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.service;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.probosque.controller.ControllerSubTable;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author VICTOR
 */
@WebServlet(name = "Subtable", urlPatterns = {"/Subtable"})
public class Subtable extends HttpServlet {

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
        String mensaje="";
        PrintWriter out = response.getWriter();
        OutputJson output = new OutputJson();
        ControllerSubTable controller = new ControllerSubTable();
        Gson gson = new Gson();
        boolean identify = false;
        try {
            String action = request.getParameter("action");
            switch (action) {
                case "get":
                    output = controller.getTable(request);
                    break;
                case "set":
                    output = controller.editTable(request);
                    break;
                case "add":
                    //Victor Porcayo Altamirano
                    mensaje = controller.validar(request);
                    ResponseJson responsej = new ResponseJson();
                    if(("noencontrado".equals(mensaje))){
                        
                        output = controller.insertTable(request);
                        
                        identify = true;
                    }else if("error".equals(mensaje)){
                        responsej.setSucessfull(false);
                        responsej.setMessage("No se registró");
                    }else{
                        responsej.setSucessfull(false);
                        responsej.setMessage(mensaje);
                    }
                    break;
                    //Victor Porcayo Altamirano
                case "find":
                    output = controller.find(request);
                    identify = true;
                    break;
                case "delete":
                    output = controller.delete(request);
                    break;
                case "volumenArbol":
                    output = controller.getVolumenArbol(request);
                    break;
                case "getAnioAreaCorta":
                    output = controller.getAnioAreaCorta(request);
                    break;
                case "getMunicipios":
                    output = controller.getMunicipios(request);
                    break;
                case "getSubcategoria":
                     output=controller.getSubcategoria(request);
                    break;
                
                    
                default :
                    output = controller.getTables(request);
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Service:SubTable", ex);
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
