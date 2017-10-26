/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.LogDTO;

/**
 *
 * @author TOSHIBA-L55
 */
public class LogDAO {
    private String Actividad (int idactividad) throws Exception{
        String a="";
        switch(idactividad){
            case 1:
                a="Registro de Datos";               
            break;
            case 2:
                a="Modificacion de Datos";
            break;
            case 3:
                a="Borrado de datos";
            break;
            case 4:
                a="Inicio de sesion";
            break;            
            default: 
                a="undefined";             
             break;                
        }
        return a;
    }
    
    public int Prolog(String user,int idactividad,String antes)throws Exception{
        String insertval="id_usr,usuario,programa,accion,fecha,antes";
        int confirm=1;

        String [] idusr= user.split("-");
      DataSource ds = PoolDataSource.getLogDataSource();
      QueryRunner qr = new QueryRunner(ds);
      StringBuilder sql = new StringBuilder();
      sql.append(" INSERT INTO ").append("public.log");
      sql.append(" (").append(insertval).append(")");
      sql.append(" VALUES (").append("'").append(idusr[0]).append("','").append(idusr[1]).append("','").append(idusr[2]).append("','");
      sql.append(Actividad(idactividad)).append("',now(), '").append(antes).append("')");
      qr.update(sql.toString());
      return confirm;
   }
    
    public void log(String user,int idactividad,String antes)throws Exception{
        String insertval="id_usr,usuario,programa,accion,fecha,antes";
        int confirm=1;
        String [] idusr= user.split("-");
      DataSource ds = PoolDataSource.getLogDataSource();
      QueryRunner qr = new QueryRunner(ds);
      StringBuilder sql = new StringBuilder();
      sql.append(" INSERT INTO ").append("public.log");
      sql.append(" (").append(insertval).append(")");
      sql.append(" VALUES (").append("'").append(idusr[0]).append("','").append(idusr[1]).append("','").append(idusr[2]).append("','");
      sql.append(Actividad(idactividad)).append("',now(), '").append(antes).append("')");
      qr.update(sql.toString());
      
      
   }
    
    public List<LogDTO> getLogfecha(String fecha1, String fecha2, String Programa) throws SQLException{
      DataSource ds = PoolDataSource.getLogDataSource();
      QueryRunner qr = new QueryRunner(ds);
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT 'id serial', id_usr,usuario,programa, accion, to_char(fecha,'dd/mm/yyyy hh:mi AM') as fecha, antes FROM ").append(" public.log ");
      sql.append(" WHERE to_char(fecha,'dd/mm/yyyy') ");
      
            
      if(!fecha1.equals("")&& !fecha2.equals(""))
         {
         sql.append(" BETWEEN  '").append(fecha1).append("' AND '").append(fecha2).append("'");
         }
       else
         if(!fecha1.equals(""))
        {
        sql.append("   LIKE '%" );
        sql.append(fecha1).append("%'");
        
        }
      if(!Programa.equals("-1"))
      {
        sql.append(" AND  programa='").append(Programa).append("'");
      }
      
      
      
      
      
       ResultSetHandler rsh = new BeanListHandler(LogDTO.class);
       List<LogDTO> columns = (List<LogDTO>) qr.query(sql.toString(), rsh);
       return columns;
    }
    public List<LogDTO> getLognombre(String nombre,String fecha1,String fecha2) throws SQLException{
      DataSource ds = PoolDataSource.getLogDataSource();
      QueryRunner qr = new QueryRunner(ds);
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT 'id serial', id_usr,usuario,programa, accion,to_char(fecha,'dd/mm/yyyy hh:mi AM') AS fecha, antes FROM ").append(" public.log ");
      sql.append(" WHERE usuario ");
      sql.append(" LIKE ").append("'%");
      sql.append(nombre).append("%'  ");
     
      
       if(!fecha1.equals("")&& !fecha2.equals(""))
         {
         sql.append(" AND fecha BETWEEN  '").append(fecha1).append("' AND '").append(fecha2).append("'");
         }
       else
         if(!fecha1.equals(""))
        {
        sql.append(" AND  to_char(fecha,'dd/mm/yyyy') LIKE '%" );
        sql.append(fecha1).append("%'");
        
        }
     
      
      
       ResultSetHandler rsh = new BeanListHandler(LogDTO.class);
       List<LogDTO> columns = (List<LogDTO>) qr.query(sql.toString(), rsh);
       return columns;
    }
    
     public List<LogDTO> getLog( ) throws SQLException{
      DataSource ds = PoolDataSource.getLogDataSource();
      QueryRunner qr = new QueryRunner(ds);
      StringBuilder sql = new StringBuilder();
      Calendar c = Calendar.getInstance();
      
      
       int dia = c.get(Calendar.DATE);
       int mes = c.get(Calendar.MONTH)+1;
       int anio = c.get(Calendar.YEAR);
       String fechaDia;
       if(mes<10)
       fechaDia = dia+"/0"+mes+"/"+anio;
       else
        fechaDia = dia+"/0"+mes+"/"+anio;
      sql.append("SELECT 'id serial', id_usr,usuario,programa, accion,to_char(fecha,'dd/mm/yyyy hh:mi') AS fecha, antes FROM ").append(" public.log ");
      sql.append(" WHERE to_char(fecha,'dd/mm/yyyy')");
      sql.append(" LIKE ").append("'%");
      sql.append(fechaDia).append("'");
       sql.append(" Order by  fecha DESC");
      ResultSetHandler rsh = new BeanListHandler(LogDTO.class);
       List<LogDTO> columns = (List<LogDTO>) qr.query(sql.toString(), rsh);
       return columns;
    }
}
