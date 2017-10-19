package org.probosque.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.probosque.controller.ControllerDownloadCSV;

/**
 * Clase que permite retornar un archivo con formato CSV, el cual corresponde a la información tabular de un determinado programa
 * @author admin
 */
@WebServlet(name = "DownloadCSV", urlPatterns = {"/DownloadCSV"})
public class DownloadCSV extends HttpServlet {

    /**
     * Procesa las peticiones HTTP, ya sean métodos <code>GET</code> o <code>POST</code>
     * respectivamente.
     *
     * @param request petición servlet
     * @param response respuesta servlet
     * @throws ServletException si se produce un error específico del servlet
      * throws IOException si se produce un error de E / S
     * @throws java.io.IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Allow", "HEAD, GET, TRACE, OPTIONS");
        
        response.setCharacterEncoding("charset=ISO-8859-1");
        response.setContentType("application/csv; charset=ISO-8859-1");
        
        //response.setContentType("application/csv; charset=ISO-8859-1");
        try {
            String fileName = request.getParameter("filename");
            if(fileName==null||fileName.isEmpty()){
                fileName = "Reporte";
            }
            
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".csv\"");

            ControllerDownloadCSV controller = new ControllerDownloadCSV();
            
            String outputResult = controller.getCSV(request);        
            
            OutputStream outputStream = response.getOutputStream();
            
            //outputStream.write(outputResult.getBytes("ISO-8859-1"));
            
            outputStream.write(outputResult.getBytes());
            outputStream.flush();
            outputStream.close();
            
        } catch (Exception ex) {
            Logger.getLogger(DownloadCSV.class.getName()).log(Level.SEVERE, null, ex);
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
        Configuration.setHeadersCSV(response);
    }

    /**
     * Proporciona una información breve del servlet. 
     *
     * @return String que contiene descripción del servlet
     */
    @Override
    public String getServletInfo() {
        return "DownloadCSV";
    }
    // </editor-fold>

}