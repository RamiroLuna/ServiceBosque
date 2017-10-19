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
import org.probosque.controller.ControllerCoordinador;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;
@WebServlet(name = "Coordinador", urlPatterns = {"/Coordinador"})
public class Coordinador extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
    Configuration.setHeadersJson(response);
    PrintWriter out = response.getWriter();
    OutputJson output = new OutputJson();
    Gson gson = new Gson();
        try {
            String action = request.getParameter("action");
            switch (action) {
                case "getObservaciones":
                           ControllerAbogado controller= new ControllerAbogado();
                            output=controller.getObservaciones(request);
                      break;
                case "getPanel":
                           ControllerCoordinador controllerP= new ControllerCoordinador();
                           output= controllerP.getPanel(request);
                     break;
                case "getConcluidas":
                          ControllerCoordinador controllerC= new ControllerCoordinador();
                           output= controllerC.getConcluidas(request);
                           break;
                case "estatusReforestacion":
                          ControllerCoordinador controllerE= new ControllerCoordinador();
                          output= controllerE.AsignarEstatusReforestacion(request);
                          break;
                case "terminarProceso":           
                    ControllerCoordinador controllerT= new ControllerCoordinador();
                          output= controllerT.terminarExpediente(request);
                          break;          
                case "getSeguimiento":           
                    ControllerCoordinador controllerS= new ControllerCoordinador();
                          output= controllerS.getSeguimiento(request);
                          break;                    
                case "impugnar":
                    ControllerCoordinador controllerI= new ControllerCoordinador();
                    output=controllerI.impugnar(request);
                    break;
                case "getImpugnaciones":
                    ControllerCoordinador controllerGI= new ControllerCoordinador();
                    output=controllerGI.getImpugnaciones(request);
                    break;
                case "reposicion":
                    ControllerCoordinador controllerR= new ControllerCoordinador();
                    output=controllerR.Reposicion(request);
                    break;
                case "cambiaExpediente":
                    ControllerCoordinador controllerCU= new ControllerCoordinador();
                    output=controllerCU.changeExpediente(request);
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
