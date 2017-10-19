/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import javax.sql.DataSource;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.CatalogoDTO;
import org.probosque.dto.InfoMultiregistroDTO;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.ResultString;
import org.probosque.dto.UserDTO;

/**
 *
 * @author Telyco
 */
public class MultiregistroDAO {
    public ArrayList<InfoMultiregistroDTO> ObtenerDatosMiltiregistro(UserDTO user, String folio, String multi, String consulta) throws Exception {
    
    DataSource ds = PoolDataSource.getDataSource(user);
    Connection con =  ds.getConnection();
    Statement sta= con.createStatement();
    
    QueryRunner qr = new QueryRunner(ds);
    StringBuilder sql = new StringBuilder();
    ArrayList<ArrayList<String>> infoMultireg= new ArrayList<>();
    int u=user.getId();
    int a=user.getActivity();
   
    
    if (consulta.equals("label")){
        
        if (a==3){
            sql.append("select name as Nombre, label as Label from formularios.principal_incendios_info where type='form' and name<>'formularios.imagenincendios'");
        } 
        else if (a==4) {
            sql.append("select name as Nombre, label as Label from formularios.principal_actividades_info where type='form' and name<>'formularios.imagen'");       
        }
        else{
    
        sql.append("select name as Nombre, label as Label from formularios.principal_info where type='form' and name<>'formularios.imagen'");
        }
        ResultSetHandler rsh1 = new BeanListHandler(InfoMultiregistroDTO.class);
            List<InfoMultiregistroDTO> requests = (List<InfoMultiregistroDTO>) qr.query(sql.toString(), rsh1) ;

        return (ArrayList<InfoMultiregistroDTO>) requests;
    }
    
////        sql.append("select general.consulta_multi('"+ folios +"','"+ multi +"_info ') AS result");
          
        
      
        //sql.append("select general.var('"+ folio +"','"+ multi +"_info ') AS result");
        sql.append("select general.multiregistro('"+ folio +"','"+ multi +"_info ') AS result");
        ResultSetHandler rsh = new BeanHandler(ResultString.class);  
        ResultString result = (ResultString) qr.query(sql.toString(), rsh); 
        String cadena =  result.getResult();
        //ResultSet  rs=sta.executeQuery(cadena+" WHERE folio='"+folio+"'"); 
        ResultSet  rs=sta.executeQuery(cadena); 
        ArrayList<String> infoMulti= new ArrayList<>();
        
        sql = new StringBuilder();
        sql.append("Select label as id, name as descripcion from formularios.").append(multi).append("_info");
        sql.append(" where name!='folio'  and type!='multiselect' and type!='attach' and type!='form' ");
        ResultSetHandler rsh2 = new BeanListHandler(CatalogoDTO.class);
        List<CatalogoDTO> columns = (List<CatalogoDTO>) qr.query(sql.toString(), rsh2) ;
        
        int i=0;
        while(rs.next())
         {
             
             infoMultireg.add(new ArrayList<String>());
          for(CatalogoDTO c: columns)
              {
               infoMultireg.get(i).add(rs.getString(c.getDescripcion()));
              }
           i++;
         }

        int x=0; 
         i=1;
         ArrayList<InfoMultiregistroDTO> imdto = new ArrayList();
         for(CatalogoDTO columnas: columns)
         {
            ArrayList<String> inf= new ArrayList();
            for(int m=0;m<infoMultireg.size(); m++)
               {
                inf.add(infoMultireg.get(m).get(x));
               }
          imdto.add(new InfoMultiregistroDTO( columnas.getId(),columnas.getDescripcion(),inf) );
          i++;x++;
         }
        
        con.close();
        rs.close();
        
        
       
////        ResultSetHandler rsh = new BeanHandler(ResultString.class);  
////        ResultString result = (ResultString) qr.query(sql.toString(), rsh); 
////        String cadena =  result.getResult();
////        
////        ResultSetHandler rsh1 = new BeanListHandler(TableMultiregistroDTO.class);
////        List<TableMultiregistroDTO> requests = (List<TableMultiregistroDTO>) qr.query(cadena, rsh1) ;
        
        
        return imdto;
    }
    
    
    public ArrayList<InfoMultiregistroDTO> ObtenerDatosMiltiregistroAdmin(UserDTO user, String folio, String programa, String multi, String consulta) throws Exception {
        
        DataSource ds1 = PoolDataSource.getDataSource(user);
        QueryRunner qr1 = new QueryRunner(ds1);
        
        String selectUsuario = "SELECT id AS result FROM \"user\" where program='"+programa+"'";

        if(programa.equals("71") || programa.equals("72")){
            selectUsuario = "SELECT id AS result FROM \"user\" where program='7'";
        }

        ResultSetHandler rshUsr = new BeanHandler(ResultInteger.class);
        ResultInteger resultUsr = (ResultInteger) qr1.query(selectUsuario, rshUsr);
        
        UserDAO userDao = new UserDAO();
        UserDTO user1 = userDao.getUser(resultUsr.getResult());
    
        DataSource ds = PoolDataSource.getDataSource(user1);
        Connection con =  ds.getConnection();
        Statement sta= con.createStatement();

        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        ArrayList<ArrayList<String>> infoMultireg= new ArrayList<>();
        int u=user.getId();
        int a=user.getActivity();


        if (consulta.equals("label")){

            if (a==3){
                sql.append("select name as Nombre, label as Label from formularios.principal_incendios_info where type='form' and name<>'formularios.imagenincendios'");
            } 
            else if (a==4) {
                sql.append("select name as Nombre, label as Label from formularios.principal_actividades_info where type='form' and name<>'formularios.imagen'");       
            }
            else{

            sql.append("select name as Nombre, label as Label from formularios.principal_info where type='form' and name<>'formularios.imagen'");
            }
            ResultSetHandler rsh1 = new BeanListHandler(InfoMultiregistroDTO.class);
                List<InfoMultiregistroDTO> requests = (List<InfoMultiregistroDTO>) qr.query(sql.toString(), rsh1) ;

            return (ArrayList<InfoMultiregistroDTO>) requests;
        }

    ////        sql.append("select general.consulta_multi('"+ folios +"','"+ multi +"_info ') AS result");



            //sql.append("select general.var('"+ folio +"','"+ multi +"_info ') AS result");
            sql.append("select general.multiregistro('"+ folio +"','"+ multi +"_info ') AS result");
            ResultSetHandler rsh = new BeanHandler(ResultString.class);  
            ResultString result = (ResultString) qr.query(sql.toString(), rsh); 
            String cadena =  result.getResult();
            //ResultSet  rs=sta.executeQuery(cadena+" WHERE folio='"+folio+"'"); 
            ResultSet  rs=sta.executeQuery(cadena); 
            ArrayList<String> infoMulti= new ArrayList<>();

            sql = new StringBuilder();
            sql.append("Select label as id, name as descripcion from formularios.").append(multi).append("_info");
            sql.append(" where name!='folio'  and type!='multiselect' and type!='attach' and type!='form' ");
            ResultSetHandler rsh2 = new BeanListHandler(CatalogoDTO.class);
            List<CatalogoDTO> columns = (List<CatalogoDTO>) qr.query(sql.toString(), rsh2) ;

            int i=0;
            while(rs.next())
             {

                 infoMultireg.add(new ArrayList<String>());
              for(CatalogoDTO c: columns)
                  {
                   infoMultireg.get(i).add(rs.getString(c.getDescripcion()));
                  }
               i++;
             }

            int x=0; 
             i=1;
             ArrayList<InfoMultiregistroDTO> imdto = new ArrayList();
             for(CatalogoDTO columnas: columns)
             {
                ArrayList<String> inf= new ArrayList();
                for(int m=0;m<infoMultireg.size(); m++)
                   {
                    inf.add(infoMultireg.get(m).get(x));
                   }
              imdto.add(new InfoMultiregistroDTO( columnas.getId(),columnas.getDescripcion(),inf) );
              i++;x++;
             }

            con.close();
            rs.close();  

            return imdto;
    }
}
