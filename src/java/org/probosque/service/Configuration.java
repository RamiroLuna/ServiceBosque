package org.probosque.service;

import javax.servlet.http.HttpServletResponse;

/**
 * Clase que permite definir variables de directorios de trabajo; 
 * asi como las cabeceras de los servlets.
 * @author admin
 */

public class Configuration {
    public static final String PATH_UPLOAD_TEMPORAL = "d:\\sifem\\upload\\temporal\\"; 
    public static final String PATH_UPLOAD = "d:\\sifem\\upload\\"; 
    public static final String PATH_TMP = "d:\\sifem\\tmp\\";
    public static final String PATH_BOSQUE = "d:\\sifem\\";
    public static final String PATH_QR_PROGRAMA9 = "d:\\sifem\\programa9\\qr\\";
    public static final String IMG_PDF = "http://localhost:8082/ServiceBosque/img/pdfheader.png";
    
    /**
     * Método que permite establecer las cabeceras de un servlet, en particular este método define el cruce de dominios y el tipo de retorno del servlet como un formato JSON. 
     *
     * @param response respuesta del servlet*/
    public static void setHeadersJson(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Allow", "GET, HEAD, POST, TRACE, OPTIONS");
        response.setContentType("application/json;charset=UTF-8");
    }
    
    /**
     * Método que permite establecer las cabeceras de un servlet, en particular este método define el cruce de dominios y el tipo de retorno del servlet como un archivo sin formato especifico. 
     *
     * @param response respuesta del servlet*/
    public static void setHeadersFile(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Allow", "GET, HEAD, POST, TRACE, OPTIONS");
    }
    
    public static void setHeadersCSV(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Allow", "GET, HEAD, POST, TRACE, OPTIONS");
        response.setCharacterEncoding("charset=ISO-8859-1"); 
        response.setContentType("application/csv;charset=ISO-8859-1");
    }
}