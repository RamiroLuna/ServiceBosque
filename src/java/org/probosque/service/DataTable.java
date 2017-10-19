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
import org.probosque.controller.ControllerDataTable;
import org.probosque.model.json.OutputTableJson;
import org.probosque.model.json.ResponseJson;

/**
 * Clase que permite retornar la información de forma tabular contenida en los formularios, es capaz de generar filtros por columnas
 * @author admin
 */
@WebServlet(name = "DataTable", urlPatterns = {"/DataTable"})
public class DataTable extends HttpServlet {

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
        OutputTableJson output = new OutputTableJson();
        ResponseJson responseJson = new ResponseJson();
        ControllerDataTable controller = new ControllerDataTable();
        Gson gson = new GsonBuilder().serializeNulls().create();
            boolean identify = false;
        try {
            String action = request.getParameter("action"); 
            switch (action) {
                case "getcolumns":
                    output = controller.getColumns(request);
                    break;
                case "getall":
                    output = controller.getAll(request);
                    identify = true;
                    break;
                default:
                    responseJson.setMessage("error");
                    responseJson.setSucessfull(false);
            }
        } catch (Exception ex) {
            responseJson.setMessage(ex.getMessage());
            responseJson.setSucessfull(false);
        } finally {
            if (!identify) {
                out.print(gson.toJson(output));
                out.close();
            } else {
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
        return "DataTable";
    }// </editor-fold>

}
