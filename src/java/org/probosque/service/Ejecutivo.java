package org.probosque.service;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.probosque.controller.ControllerEjecutivo;

import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

@WebServlet(name = "Ejecutivo", urlPatterns = {"/Ejecutivo"})
public class Ejecutivo extends HttpServlet {

 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
        Configuration.setHeadersJson(response);
        PrintWriter out = response.getWriter();
        OutputJson output = new OutputJson();
        
        ControllerEjecutivo controller = new ControllerEjecutivo();
        Gson gson = new Gson();
        try {
             String action = request.getParameter("action");
             
             switch(action)
                    {
                 case "getMunicipio": output=controller.getMunicipio(request);
                     break;
                 case "getLocalidad": output=controller.getLocalidad(request);
                     break;
                 case "getPredio": output=controller.getPredio(request);
                     break;
                 case "getRepresentantes": output=controller.getRepresentantes(request);
                     break;
                 case "getReporte": output=controller.getReporte(request);
                     break;
                 case "getDetallePredio": output=controller.getDetallesPredio(request);
                     break;
                  
                    }
            
            
            
        } catch (Exception ex) 
        {
            ResponseJson reponseJson = new ResponseJson();
            reponseJson.setSucessfull(false);
            reponseJson.setMessage(ex.getMessage());
            output.setResponse(reponseJson);
        } finally {
           out.print(gson.toJson(output));
           out.close();
        }
      }

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
        return "Ejecutivo";
    }
    // </editor-fold>


}
