/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Jonathan
 */
public class ConexionMySQL {
    private String username = "root";
    private String password = "SRV2BOSQU3PRO300316";
    private String hostname = "187.188.96.133";
    private String port = "3306";
    //private String database = "probosque";
    private String classname = "com.mysql.jdbc.Driver";
    private String url ="jdbc:mysql://" +hostname+":"+port+"/";
    //Objeto Conexion
    private Connection con;
    
    //Constructor
    public ConexionMySQL(String DB){
        try{
            Class.forName(classname);
            //Conexion
            con = DriverManager.getConnection(url+DB, username, password);
             System.out.println(url+DB);
        }catch(ClassNotFoundException e){
            //Atrapamos posibles erroes
            //Imprimir por consola el mensaje del error generado
            System.err.println(e.getMessage());
        }catch(SQLException esql){
            System.err.println(esql.getMessage());
        }        
    }
    
    //Metodo de tipo Conexion que nos regresara la conexion.
    public Connection getConnection(){
        return con;
    }
}
