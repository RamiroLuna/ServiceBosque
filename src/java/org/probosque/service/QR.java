package org.probosque.service;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import org.probosque.dao.QrDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.UserDTO;

/**
 * Clase que permite retornar un archivo en formato de imagen, dependiendo del formato con el que usuario subio la imagen
 * @author admin
 */
@MultipartConfig
@WebServlet(name = "QR", urlPatterns = {"/QR"})
public class QR extends HttpServlet {

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

        Configuration.setHeadersFile(response);

        try {
            QrDAO qrDao = new QrDAO();
            UserDAO userDao = new UserDAO();

            String action = "";
            try {
                action = IOUtils.toString(request.getPart("action").getInputStream(), "UTF-8");
            } catch (Exception error) {
                action = request.getParameter("action");
            }
            int programa=0;
            switch (action) {
                case "get":
                    String gUser = request.getParameter("user");
                    String gFolio = request.getParameter("folio");
                    String gConsecutivo = request.getParameter("consecutivo");
                    String gField = request.getParameter("field");
                    
                    /*
                    * Se agrega if para pasear el consecutivo 
                    */                  
                    if(Integer.parseInt(gConsecutivo) <= 9){
                        gConsecutivo = "00" + gConsecutivo;
                    }else if(Integer.parseInt(gConsecutivo)> 9 && Integer.parseInt(gConsecutivo) <= 99){
                        gConsecutivo = "0" + gConsecutivo;
                    }
                    
                    
                    UserDTO userDto = userDao.getUser(Integer.parseInt(gUser));
                    programa=userDto.getProgram();
                     //Jon PDF
                    String subField = gField.substring(0,3);
                    String archivo = "pdf";
                    // fin  
                    programa=userDto.getProgram();
                    
                    File nfsPDF = new File("C:\\sifem\\programa"+programa+"\\qr\\" + gFolio + "_" + gField + "_" + gConsecutivo+".pdf");                 
                    //Si el Field es PDF ... 
                    if (nfsPDF.exists()) {
                        
                        FileInputStream fis = new FileInputStream(nfsPDF);
                        BufferedInputStream bis = new BufferedInputStream(fis);
                        ServletOutputStream sos = response.getOutputStream();
                        byte[] buffer = new byte[4096];
                        while (true) {
                          int bytesRead = bis.read(buffer, 0, buffer.length);
                          if (bytesRead < 0) {
                            break;
                          }
                        sos.write(buffer, 0, bytesRead);
                        sos.flush();
                        }
                        sos.flush();
                        bis.close();

                    }else{
                        String ext = "png";
                        response.setContentType("image/" + ext);
                        File file = new File("C:\\sifem\\programa"+programa+"\\qr\\" + gFolio + "_" + gField + "_" + gConsecutivo);
                        if (file.exists()) {
                            BufferedImage bi = ImageIO.read(file);
                            OutputStream out = response.getOutputStream();
                            ImageIO.write(bi, ext, out);
                            out.close();
                        }
                    }
                    //String ext = qrDao.getExt(userDto, gFolio);
/*                    String ext = "png";
                    response.setContentType("image/" + ext);

                    File file = new File("D:\\sifem\\programa"+programa+"\\qr\\" + gFolio + "_" + gField + "_" + gConsecutivo);
                    if (file.exists()) {
                        BufferedImage bi = ImageIO.read(file);
                        OutputStream out = response.getOutputStream();
                        ImageIO.write(bi, ext, out);
                        out.close();
                    }*/
                    break;
                case "set":
                    
                    String _user = IOUtils.toString(request.getPart("user").getInputStream(), "UTF-8");
                    String _folio = IOUtils.toString(request.getPart("folio").getInputStream(), "UTF-8");
                    String _consecutivo = IOUtils.toString(request.getPart("consecutivo").getInputStream(), "UTF-8");
                    String _field = IOUtils.toString(request.getPart("field").getInputStream(), "UTF-8");
                    
                    /*
                    * Se agrega if para pasear el consecutivo 
                    */   
                    int con = Integer.parseInt(_consecutivo);
                    if(con <= 9){
                        _consecutivo = "00" + con;
                    }else if(con > 9 && con <= 99){
                        _consecutivo = "0" + con;
                    }
                    
                    UserDTO user = userDao.getUser(Integer.parseInt(_user));
                    programa=user.getProgram();
                    //Codigo Jon PDF
                    
                     final Part filePart = request.getPart("file");
                     UploadShape  ups= new UploadShape();
                     String nombreArchivo = ups.getFileName(filePart);
                     String _subField = nombreArchivo.substring(nombreArchivo.length()-3,nombreArchivo.length());
                     String _archivo = "pdf";
                    
                    
                  
                    //fin Jon PDF
       
                    programa=user.getProgram();
                    
                    File folder = new File("C:\\sifem\\programa"+programa+"\\qr\\");
                    if (!folder.exists()) {
                        folder.mkdirs();
                    }
                    
                    //Codigo }Jon 
                    
                    String sFichero = "";
                    //Si el _Field es "pdf"
                    if (_archivo.equalsIgnoreCase(_subField)) {
                        sFichero = "C:\\sifem\\programa"+programa+"\\qr\\" + _folio + "_" + _field + "_" + _consecutivo+".pdf";
                        File fichero = new File(sFichero);
                        if (fichero.exists()) {
                            fichero.delete();
                        }
                    } else {
                        sFichero = "C:\\sifem\\programa"+programa+"\\qr\\" + _folio + "_" + _field + "_" + _consecutivo;
                        File fichero = new File(sFichero);
                        if (fichero.exists()) {
                            fichero.delete();
                        }
                    }

                    OutputStream outFile = null;
                    InputStream filecontent = null;
                    try {
                        outFile =new FileOutputStream(new File(sFichero));
                        //outFile = new FileOutputStream(new File("C:\\sifem\\programa"+programa+"\\qr\\" + _folio + "_" + _field + "_" + _consecutivo));
                        filecontent = filePart.getInputStream();
                        int read = 0;
                        final byte[] bytes = new byte[1024];
                        while ((read = filecontent.read(bytes)) != -1) {
                            outFile.write(bytes, 0, read);
                        }
                    } catch (Exception ex ) {
                        System.out.println(ex);
                    } finally {
                        if (outFile != null) {
                            outFile.close();
                        }
                        if (filecontent != null) {
                            filecontent.close();
                        }
                    }
                    break;
            }
        } catch (Exception ex) {
            response.getWriter().print(ex.getMessage());
            //Logger.etLogger(QR.class.getName()).log(Level.SEVERE, null, ex);
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
        Configuration.setHeadersFile(response);
    }

    /**
     * Proporciona una información breve del servlet. 
     *
     * @return String que contiene descripción del servlet
     */
    @Override
    public String getServletInfo() {
        return "QR";
    }// </editor-fold>
}
