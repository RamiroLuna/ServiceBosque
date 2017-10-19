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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
import org.probosque.dao.TableDataDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;
import org.probosque.dao.LayersDAO;
import org.probosque.dao.LimitesDAO;
import org.probosque.model.json.PredioDTO;
/**
 * Clase que permite copiar al servidor una capa de información geográfica enviada en formato SHAPE
 * @author admin
 */

@WebServlet(name = "UploadShape", urlPatterns = {"/UploadShape"})

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 15, // 15 MB
        maxFileSize = 1024 * 1024 * 15, // 15 MB
        maxRequestSize = 1024 * 1024 * 15, // 15 MB
        location = Configuration.PATH_TMP
)

public class UploadShape extends HttpServlet {
    
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
        
        Configuration.setHeadersJson(response);
        PrintWriter out = response.getWriter();
        OutputJson output = new OutputJson();
        ResponseJson responseJson = new ResponseJson();
        Gson gson = new Gson();
          UserDTO user=null;
           String _layername=null;
        
        try {

            String _user = IOUtils.toString(request.getPart("user").getInputStream(), "UTF-8");
            _layername = IOUtils.toString(request.getPart("layername").getInputStream(), "UTF-8");
             
            LayersDAO layersdao= new LayersDAO();
            final Part filePart = request.getPart("file");
            UserDAO userDao = new UserDAO();
             user = userDao.getUser(Integer.parseInt(_user));        
            
            responseJson.setMessage("Archivo Importado Correctamente");    
            responseJson.setSucessfull(true);
            output.setResponse(responseJson);
            //List<ThemeDTO> nombreCapas=layersdao.verificarCapa(user);
            
            //boolean existe=nombreCapas.contains(_layername);
              //  if(existe==false){
                    
          //String duplicada=layersdao.verificarCapa(user, _layername);
          /*  if(_layername.equals(duplicada))
            {
            String message ="Ya existe una capa con ese nombre";
            responseJson.setSucessfull(false);
            output.setResponse(responseJson);
            }*/
            //else
            //{               
            String pathTemporal = Configuration.PATH_UPLOAD+"programa"+String.valueOf(user.getProgram())+ "//";
            System.out.println(" path temporal: "+pathTemporal);
            File dirTemporal = new File(pathTemporal);

            for (File file : dirTemporal.listFiles()) {
                System.out.println("file "+file);
                file.delete();
            }
            
            String fileName = getFileName(filePart);
            System.out.println(filePart.getName());
            //String fileZipCarga = pathTemporal + fileName;
            String fileZipCarga= pathTemporal+fileName;
            
            filePart.getName();
            OutputStream outFile = null;
            InputStream filecontent = null;

            try {
                outFile = new FileOutputStream(new File(fileZipCarga));
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
            descomprimirZIP(pathTemporal, fileZipCarga );
            
            String error = "";
            //if(layername.equals("charge")){
                boolean isChargeValid = false;
                for (File file : dirTemporal.listFiles()) {
                    if(file.getName().toUpperCase().contains(".SHP")){
                        isChargeValid = true;
                        break;
                    }
                }
                if (!isChargeValid) {
                    
                    responseJson.setMessage("El ZIP es incorrecto, debe contener un archivo shape.");   
                    responseJson.setSucessfull(false);
                    output.setResponse(responseJson);
                } else {
                    //Rename
                    for (File file : dirTemporal.listFiles()) {
                        File newFile = new File(file.getAbsolutePath().replace(file.getName().replaceFirst("[.][^.]+$", ""), "filetoup"));
                        file.renameTo(newFile);
                    }
                    fileToUp(user.getProgram(),pathTemporal,_layername);
                    //////
                    //Reemplazar caracteres
                    /////
                    
                    List<String> lines = new ArrayList<>();
                    String line = null;

                    try {
                        
                        String file = Configuration.PATH_UPLOAD_TEMPORAL;
                        /*File f1 = new File(file + "filetoup"+user.getProgram()+".sql");
                        FileReader fr = new FileReader(f1);
                        BufferedReader br = new BufferedReader(fr);
                        while ((line = br.readLine()) != null) {
                            if (line.contains("#QNB")) {
                                line = line.replace("#QNB", "0000");
                            }
                            lines.add(line);
                        }
                        fr.close();
                        br.close();
                                
                        FileWriter fw = new FileWriter(f1);
                        BufferedWriter bout = new BufferedWriter(fw);
                        for (String s : lines) {
                            bout.write(s);
                        }
                        bout.flush();
                        bout.close();*/
                    } catch (Exception ex) {
                        Logger.getLogger(UploadShape.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    psqlFileToUp(user.getProgram(), pathTemporal);
                    //Renombrar tabla
                    TableDataDAO tableDataDao = new TableDataDAO();
                    //tableDataDao.renameTable(schemaName, "bosque1_r_c", schemaName+".bosque1_r_c", "\"" +_layername + "\"");
                    tableDataDao.renameTable(user, _layername);
                //}
                
            if(!error.trim().isEmpty()){
                responseJson.setMessage(error);
                responseJson.setSucessfull(false);
                output.setResponse(responseJson);
            }
        }
                /*}
                else{
                       String message;
                        message = "Ya existe una capa con ese nombre";
                        responseJson.setMessage(message);
                        responseJson.setSucessfull(false);
                        output.setResponse(responseJson);
                        }*/
        if(user.getProgram()==0 && request.getParameter("capa").equals("LIMITES"))
             {
              LimitesDAO dao = new LimitesDAO();
              PredioDTO predio=dao.checarLimites(user, _layername);
              output.setData(predio);
              responseJson.setSucessfull(predio.getSuccessfull());
              responseJson.setMessage(predio.getMensaje());
              output.setResponse(responseJson);
             }
        } catch (Exception ex) {
            //Mensajes
            //already exists 
            String message = ex.getMessage();
             
            message = message.contains("already exists")?"Ya existe una capa con ese nombre":ex.getMessage();
            message = message.contains("column \"folio\" does not exist")?"La capa debe contener una columna llamada folio":ex.getMessage();
            //column "folio" does not exist Position: 8 Query: SELECT folio AS label FROM capasgeograficas."regiones forestales" LIMIT 1 Para
            responseJson.setMessage(message);
            responseJson.setSucessfull(false);
            output.setResponse(responseJson);
        } finally {
           
            out.print(gson.toJson(output));
            out.close();
        }
        
        
    }
    
    public void descomprimirZIP(String pathUnzip, String fileName) {
        try {
            ZipInputStream zis = null;
            zis = new ZipInputStream(new FileInputStream(fileName));

            ZipEntry entrada;
            while (null != (entrada = zis.getNextEntry())) {
                //nombre de archivos
                String nombre=entrada.getName();
                System.out.println(nombre);

                FileOutputStream fos = new FileOutputStream(pathUnzip + nombre);
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
            Logger.getLogger(UploadShape.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UploadShape.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void fileToUp(int program, String ArchivoSHP, String nombreTabla) {
        String cmd4;// = "cmd /C //Program Files//PostgreSQL//9.3//bin//shp2pgsql.exe -s 4326 -c -D -W latin1 -I ";
        //cmd4+=ArchivoSHP+"filetoup capasgeograficas."+nombreTabla +" > "+ArchivoSHP+"filetoup.sql";
        
        cmd4=Configuration.PATH_BOSQUE + "bats//filetoup";
        //cmd4 =Configuration.PATH_UPLOAD+"";
                
        cmd4+=String.valueOf(program) + ".bat";
        
        Runtime run = Runtime.getRuntime();
        Process p = null;
        try {  
            p = run.exec(cmd4);
            //p.getErrorStream();
            p.waitFor();
            //p.waitFor(90, TimeUnit.SECONDS);
        } catch (IOException | InterruptedException e) {
            Logger.getLogger(UploadShape.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            p.destroy();
        }
    }
    
     public void psqlFileToUp(int program, String ArchivoSQL) {
        String cmd4="";/* = " SET PGPASSFILE=localhost:5432:programa7:programa7:programa7_Ak457fgyt";
                cmd4+= "cmd /C \\Program Files\\PostgreSQL\\9.3\\bin\\psql.exe -d";
                cmd4+= "programa"+program+  "-h localhost -U postgres  -p5432 ";
                cmd4+="-f "+ArchivoSQL+"filetoup.sql";*/
        cmd4 =Configuration.PATH_BOSQUE + "bats\\psql_filetoup"+String.valueOf(program) + ".bat";
        
        Runtime run = Runtime.getRuntime();
        Process p = null;
        try {
        p = run.exec(cmd4);
//           p.getErrorStream();
            p.waitFor();
           p.waitFor(30, TimeUnit.SECONDS);
        } catch (IOException | InterruptedException e) {
            Logger.getLogger(UploadShape.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            p.destroy();
        }
    }
    public String getFileName(final Part part) {
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
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
        return "UploadShape";
    }// </editor-fold>

}
