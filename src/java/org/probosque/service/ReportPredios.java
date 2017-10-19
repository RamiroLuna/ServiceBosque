package org.probosque.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.probosque.controller.ControllerReportPredios;
import org.probosque.model.json.OutputReportPrediosJson;
import org.probosque.model.json.ResponseJson;

/**
 * Clase que retorna la información procesada para ser visualizada a nivel ejecutivo de los diferentes programas
 * @author admin
 */
@WebServlet(name = "ReportPredios", urlPatterns = {"/ReportPredios"})

public class ReportPredios extends HttpServlet {

    /**
     * Procesa las peticiones HTTP, ya sean métodos <code>GET</code> o <code>POST</code>
     * respectivamente.
     *
     * @param request petición servlet
     * @param response respuesta servlet
     * @throws ServletException si se produce un error específico del servlet
      * throws IOException si se produce un error de E / S
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Configuration.setHeadersJson(response);
        
        PrintWriter out = response.getWriter();
        OutputReportPrediosJson output = new OutputReportPrediosJson();
        ResponseJson responseJson = new ResponseJson();
        ControllerReportPredios controller = new ControllerReportPredios();
        Gson gson = new GsonBuilder().serializeNulls().create();
        try {
            responseJson.setMessage("valido");
            responseJson.setSucessfull(true);
            output.setColumns(controller.getColumns());
            output.setReport(controller.getReport(request));
        } catch (Exception ex) {
            responseJson.setMessage(ex.getMessage());
            responseJson.setSucessfull(false);
        } finally {
            output.setResponse(responseJson);
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
        return "ReportPredios";
    }// </editor-fold>

}
