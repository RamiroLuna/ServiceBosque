package org.probosque.service;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import org.probosque.controller.ControllerPredios;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;


/**
 * Clase que permite subir un archivo tipo imagen al servidor, este archivo corresponde al dato QR de un registro del formulario
 * @author admin
 */

@WebServlet(name = "UploadQR", urlPatterns = {"/UploadQR"})

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 15, // 15 MB
        maxFileSize = 1024 * 1024 * 15, // 15 MB
        maxRequestSize = 1024 * 1024 * 15, // 15 MB
        location = Configuration.PATH_TMP
)

public class UploadQR extends HttpServlet {

    /**
     * Procesa las peticiones HTTP, ya sean métodos <code>GET</code> o
     * <code>POST</code> respectivamente.
     *
     * @param request petición servlet
     * @param response respuesta servlet
     * @throws ServletException si se produce un error específico del servlet
     * throws IOException si se produce un error de E / S
     * @throws java.io.IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Configuration.setHeadersJson(response);

        PrintWriter out = response.getWriter();
        OutputJson output = new OutputJson();
        ResponseJson responseJson = new ResponseJson();
        ControllerPredios controller = new ControllerPredios();
        Gson gson = new Gson();

        try {

            String user = IOUtils.toString(request.getPart("user").getInputStream(), "UTF-8");
            String type = IOUtils.toString(request.getPart("type").getInputStream(), "UTF-8");
            String folio = IOUtils.toString(request.getPart("folio").getInputStream(), "UTF-8");

            responseJson.setMessage("type: " + type + "Archivo QR subido satisfactoriamente");
            responseJson.setSucessfull(true);
            output.setResponse(responseJson);

            String fileName = "carga.zip";

            // 1 Make dir
            final Part filePart = request.getPart("file");

            OutputStream outFile = null;
            InputStream filecontent = null;

            try {
                outFile = new FileOutputStream(new File(""));
                filecontent = filePart.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    outFile.write(bytes, 0, read);
                }
            } catch (FileNotFoundException fne) {

            } finally {
                if (outFile != null) {
                    outFile.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
            }

            //unzipCarga();
        } catch (Exception ex) {
            responseJson.setMessage(ex.getMessage());
            responseJson.setSucessfull(false);
            output.setResponse(responseJson);
        } finally {
            out.print(gson.toJson(output));
            out.close();
        }
    }

    public void descomprimirZIP(String fileName) {
        try {
            ZipInputStream zis = null;
            zis = new ZipInputStream(new FileInputStream(fileName));

            ZipEntry entrada;
            while (null != (entrada = zis.getNextEntry())) {
                System.out.println(entrada.getName());

                FileOutputStream fos = new FileOutputStream(Configuration.PATH_UPLOAD + "temporal/" + entrada.getName());
                int leido;
                byte[] buffer = new byte[1024];
                while (0 < (leido = zis.read(buffer))) {
                    fos.write(buffer, 0, leido);
                }
                fos.close();
                zis.closeEntry();

            }
            zis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadQR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UploadQR.class.getName()).log(Level.SEVERE, null, ex);
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
        return "UploadQR";
    }// </editor-fold>

}
