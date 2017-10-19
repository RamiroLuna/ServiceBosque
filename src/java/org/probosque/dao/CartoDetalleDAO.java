/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dao;

import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.ResultString;
import org.probosque.dto.TableCartoDetalleDTO;
import org.probosque.dto.UserDTO;


/**
 *
 * @author Telyco
 */
public class CartoDetalleDAO {
    
    public List<TableCartoDetalleDTO> ObtenerDatosCartoDetalle(UserDTO user, String folio) throws Exception {
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        int u=user.getId();
        int a=user.getActivity();
        String cadena="";
        
        if (u==11 || u==16){ ////P6
            
           sql.append("select general.consulta_multiselect('"+ folio +"') AS result");
          
        ResultSetHandler rsh = new BeanHandler(ResultString.class);  
        ResultString result = (ResultString) qr.query(sql.toString(), rsh); 
        cadena =  result.getResult();
       
        ResultSetHandler rsh1 = new BeanListHandler(TableCartoDetalleDTO.class);
        List<TableCartoDetalleDTO> req = (List<TableCartoDetalleDTO>) qr.query(cadena, rsh1) ;
        List<TableCartoDetalleDTO> requests = (List<TableCartoDetalleDTO>) qr.query(cadena, rsh1) ;
        
        return requests; 
            
        }
        if (a==3){
                sql.append("select general.consulta_ambos71('"+ folio +"') AS result");

                ResultSetHandler rsh = new BeanHandler(ResultString.class);  
                ResultString result = (ResultString) qr.query(sql.toString(), rsh); 
                cadena =  result.getResult();

                ResultSetHandler rsh1 = new BeanListHandler(TableCartoDetalleDTO.class);
                List<TableCartoDetalleDTO> requests = (List<TableCartoDetalleDTO>) qr.query(cadena, rsh1) ;

                return requests;
            
            } else if (a==4) {
                
                sql.append("select general.consulta_ambos72('"+ folio +"') AS result");

                ResultSetHandler rsh = new BeanHandler(ResultString.class);  
                ResultString result = (ResultString) qr.query(sql.toString(), rsh); 
                cadena =  result.getResult();

                ResultSetHandler rsh1 = new BeanListHandler(TableCartoDetalleDTO.class);
                List<TableCartoDetalleDTO> requests = (List<TableCartoDetalleDTO>) qr.query(cadena, rsh1) ;

                return requests;
            }

        
        sql.append("select general.consulta_ambos('"+ folio +"') AS result");
          
        ResultSetHandler rsh = new BeanHandler(ResultString.class);  
        ResultString result = (ResultString) qr.query(sql.toString(), rsh); 
        cadena =  result.getResult();
       
        ResultSetHandler rsh1 = new BeanListHandler(TableCartoDetalleDTO.class);
        List<TableCartoDetalleDTO> req = (List<TableCartoDetalleDTO>) qr.query(cadena, rsh1) ;
        List<TableCartoDetalleDTO> requests = (List<TableCartoDetalleDTO>) qr.query(cadena, rsh1) ;
        
        return requests;
    }
    
    
    public List<TableCartoDetalleDTO> ObtenerDatosCartoDetalleAdmin(UserDTO user, String folio, String programa) throws Exception {
        
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
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        int u=user.getId();
        int a=user.getActivity();
        String cadena="";
        
        if (u==11 || u==16){ ////P6
            
           sql.append("select general.consulta_multiselect('"+ folio +"') AS result");
          
        ResultSetHandler rsh = new BeanHandler(ResultString.class);  
        ResultString result = (ResultString) qr.query(sql.toString(), rsh); 
        cadena =  result.getResult();
       
        ResultSetHandler rsh1 = new BeanListHandler(TableCartoDetalleDTO.class);
        List<TableCartoDetalleDTO> req = (List<TableCartoDetalleDTO>) qr.query(cadena, rsh1) ;
        List<TableCartoDetalleDTO> requests = (List<TableCartoDetalleDTO>) qr.query(cadena, rsh1) ;
        
        return requests; 
            
        }
        if (a==3){
                sql.append("select general.consulta_ambos71('"+ folio +"') AS result");

                ResultSetHandler rsh = new BeanHandler(ResultString.class);  
                ResultString result = (ResultString) qr.query(sql.toString(), rsh); 
                cadena =  result.getResult();

                ResultSetHandler rsh1 = new BeanListHandler(TableCartoDetalleDTO.class);
                List<TableCartoDetalleDTO> requests = (List<TableCartoDetalleDTO>) qr.query(cadena, rsh1) ;

                return requests;
            
            } else if (a==4) {
                
                sql.append("select general.consulta_ambos72('"+ folio +"') AS result");

                ResultSetHandler rsh = new BeanHandler(ResultString.class);  
                ResultString result = (ResultString) qr.query(sql.toString(), rsh); 
                cadena =  result.getResult();

                ResultSetHandler rsh1 = new BeanListHandler(TableCartoDetalleDTO.class);
                List<TableCartoDetalleDTO> requests = (List<TableCartoDetalleDTO>) qr.query(cadena, rsh1) ;

                return requests;
            }

        
        sql.append("select general.consulta_ambos('"+ folio +"') AS result");
          
        ResultSetHandler rsh = new BeanHandler(ResultString.class);  
        ResultString result = (ResultString) qr.query(sql.toString(), rsh); 
        cadena =  result.getResult();
       
        ResultSetHandler rsh1 = new BeanListHandler(TableCartoDetalleDTO.class);
        List<TableCartoDetalleDTO> req = (List<TableCartoDetalleDTO>) qr.query(cadena, rsh1) ;
        List<TableCartoDetalleDTO> requests = (List<TableCartoDetalleDTO>) qr.query(cadena, rsh1) ;
        
        return requests;
    }  
}
