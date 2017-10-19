package org.probosque.service;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.probosque.controller.ControllerDownloadCSV;
import org.probosque.model.PDF;

/**
 * Clase que permite retornar un archivo con formato PDF, el cual corresponde a la información tabular de un determinado programa
 * @author admin
 */
@WebServlet(name = "DownloadPDF", urlPatterns = {"/DownloadPDF"})
public class DownloadPDF extends HttpServlet {

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
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Allow", "HEAD, GET, TRACE, OPTIONS");
        try {
            String fileName = request.getParameter("filename");
            if(fileName==null||fileName.isEmpty()){
                fileName = "Reporte";
            }
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".pdf\"");
            ControllerDownloadCSV controller = new ControllerDownloadCSV();        
            OutputStream out = response.getOutputStream();
            PDF pdf = new PDF(controller.getPDF(request), out);
            out.close();
        } catch (Exception e) {
            System.out.println(e.toString());
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
     * @throws java.io.IOException
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
     * @throws java.io.IOException
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
        return "DownloadPDF";
    }
    // </editor-fold>

}