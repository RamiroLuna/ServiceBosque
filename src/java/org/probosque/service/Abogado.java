package org.probosque.service;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.probosque.controller.ControllerAbogado;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

@WebServlet(name = "Abogado", urlPatterns = {"/Abogado"})
public class Abogado extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             Configuration.setHeadersJson(response);

        PrintWriter out = response.getWriter();
        OutputJson output = new OutputJson();
        ControllerAbogado controller= new ControllerAbogado();

        Gson gson = new Gson();
        try {
            String action = request.getParameter("action");
            switch (action) {
               case "getPanel":
                               output=controller.getPanel(request);    
                                 break;
                case "getConcluidas":
                               output=controller.getPanelConcluidas(request);    
                                break;
                case "addObservacion":
                               output=controller.addObservacion(request);    
                                 break;
                case "changeEstatus":
                                 output=controller.changeEstatus(request);
                                 break;
                case "getObservaciones":
                                 output=controller.getObservaciones(request);
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
