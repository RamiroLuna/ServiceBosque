package org.probosque.service;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.probosque.controller.ControllerReporteador;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

@WebServlet(name = "Reporteador", urlPatterns = {"/Reporteador"})
public class Reporteador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
Configuration.setHeadersJson(response);

        PrintWriter out = response.getWriter();
        OutputJson output = new OutputJson();
        
        ControllerReporteador controller = new ControllerReporteador();
        Gson gson = new Gson();
        
        try {
            String action = request.getParameter("action");
            switch (action) {
                case "getCampos":
                           output = controller.getCampos(request);
                     break;
                
                case "getReporte":
                           output = controller.procesoMaestro(request);
                     break;
                     
                case "saveReport":
                           output = controller.saveReport(request);
                     break;
                case "getTitulosReporte":
                            output =  controller.ListaReportes(request);
                            //output = controller.GetRecuperaReporte(request);
                     break;
                case "getReporteByID":
                        output = controller.GetRecuperaReporte(request);
                        break;
                case "getBorrarReporte":
                       output = controller.GetBorrarReporte(request);
                        break;
                case "getTitulosReportes":
                       //output = controller.GetRecuperaReporte(request);
                    output =  controller.ListaReportes(request);
                    break;
            }
         } catch (Exception ex) {
            ResponseJson reponseJson = new ResponseJson();
            reponseJson.setSucessfull(false);
            reponseJson.setMessage(ex.getMessage());
            output.setResponse(reponseJson);
        }finally {
            out.print(gson.toJson(output));
            out.close();
        } 
        
    }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        Configuration.setHeadersJson(response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
