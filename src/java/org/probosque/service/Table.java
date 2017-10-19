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
import org.probosque.controller.ControllerTable;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 * Clase que proporciona la información de las columnas y el tipo de cada una de ellas para cada programa, es la base para conformar cada formulario
 * @author admin
 */
@WebServlet(name = "Table", urlPatterns = {"/Table"})

public class Table extends HttpServlet {

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
        OutputJson output = new OutputJson();
        ControllerTable controller = new ControllerTable();
        Gson gson = new Gson();
        boolean identify = false;
        try {
            String action = request.getParameter("action");
            switch (action) {
                case "get":
                    output = controller.getTable(request);
                    break;
                case "getsearchables":
                    output = controller.getSearchables(request);
                    break;
                case "set":
                    output = controller.editTable(request);
                    break;
                case "add":
                    output = controller.insertTable(request);
                    break;
                case "find":
                    output = controller.find(request);
                    identify = true;
                    break;
                case "delete":
                    output = controller.delete(request);
                    break;
                case "getTipoInspeccion":
                     output = controller.getTipoInspeccion(request);
                    break;
                case "getListaIValor":
                     output = controller.getListaIValor(request);
                     break;
                    
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Service:Table", ex);
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
        return "Table";
    }
    // </editor-fold>

}