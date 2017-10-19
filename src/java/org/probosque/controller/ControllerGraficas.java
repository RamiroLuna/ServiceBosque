/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.controller;


import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.probosque.dao.PoolDataSource;
import org.probosque.dao.UserDAO;
import org.probosque.dto.ResultString;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 *
 * @author Administrador
 */
public class ControllerGraficas {
    
            public OutputJson getControllerGraficas(HttpServletRequest request) throws SQLException, IOException, Exception {
 
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();    
    
        
        //String _region = request.getParameter("region");
        String _usuario = request.getParameter("usuario");
        String _tema = request.getParameter("tema");
        String _inc = request.getParameter("inc");
        
        int roluser= Integer.parseInt(_usuario);
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(roluser);      
    
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        
        String CadenaGrafica="select general.graficar_temas('"+_tema+"', NULL,'"+_inc+"') AS result"; //tipo result


        ResultSetHandler rsh = new BeanHandler(ResultString.class);       
        ResultString result = (ResultString) qr.query(CadenaGrafica, rsh);  //ejecuta el query 
       
        
        String cadenagr= result.getResult();//guarda el resultado de la ejecucion
    
        String CadenaGrafica2= cadenagr.replaceAll("[{]", "[");
        String CadenaGrafica3= CadenaGrafica2.replaceAll("}", "]");

        output.setData(CadenaGrafica3);
        response.setSucessfull(true);
        
        output.setResponse(response);
        return output;
        
    }
    
}
