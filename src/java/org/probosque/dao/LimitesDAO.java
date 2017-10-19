/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dao;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.RegionDTO;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.ResultString;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.PredioDTO;

/**
 *
 * @author Erick_G
 */
public class LimitesDAO {
    
    
    public PredioDTO checarLimites(UserDTO user, String idPredio) throws Exception {
        
        String respuesta = "";
        PredioDTO predio= new PredioDTO();
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        
        String selectNewLimit = "SELECT the_geom as result FROM capasgeograficas." + "\"" + idPredio + "\"" + " WHERE id_predio = " + "'" + idPredio + "'";
        try{
            
        
        ResultSetHandler rsh = new BeanHandler(ResultString.class);
        ResultString the_geometry = (ResultString)qr.query(selectNewLimit, rsh);
        if(the_geometry == null || the_geometry.getResult().isEmpty())
        {
          respuesta="No existe Clave de Predio en el comprimido ZIP,\n verifique que la columna id_predio coinsida con la clave: "+idPredio;  
          predio.setMensaje(respuesta);
          predio.setSuccessfull(false);
        }
        else
        {
        String count = "SELECT count (*) id, ST_ASTEXT(ST_Force_2D(ST_TRANSFORM(ST_SETSRID(the_geom, 32614), 4326))) AS wkt FROM capasgeograficas.\"LIMITES\" WHERE id_predio = '"+idPredio+"'";
        count+=" group by the_geom limit 1";
        
        rsh = new BeanHandler(RegionDTO.class);
        RegionDTO predioAntiguo = (RegionDTO) qr.query(count, rsh);
        
        if (predioAntiguo==null || predioAntiguo.getId() == 0){
            String insertLimit = "INSERT INTO capasgeograficas.\"LIMITES\" (gid, id_predio, the_geom) SELECT case when max (gid) is null then 0 else max (gid) end + 1, '" + idPredio + "', '" +
                    the_geometry.getResult() + "' FROM capasgeograficas.\"LIMITES\"";
            qr.update(insertLimit);
            respuesta = "Limite insertada correctamente";
        }else {
            String updateLimit = "UPDATE capasgeograficas.\"LIMITES\" SET the_geom = '" + the_geometry.getResult() + "' WHERE id_predio = '" + idPredio + "'";
            qr.update(updateLimit);
            respuesta = "Limite actualizada correctamente";
            predio.setPredioViejo(predioAntiguo);
            
        }
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT count (*) id, ST_ASTEXT(ST_Force_2D(ST_TRANSFORM(ST_SETSRID(the_geom, 32614), 4326))) AS wkt");
        sql.append(" FROM capasgeograficas.\"LIMITES\"");
       sql.append(" WHERE id_predio = '").append(idPredio).append("'");
       sql.append(" group by the_geom " );
        RegionDTO predioNuevo = (RegionDTO) qr.query(sql.toString(), rsh);
        predio.setPredioNuevo(predioNuevo);
        predio.setSuccessfull(true);
        }  
        StringBuilder sql = new StringBuilder();
        sql.append("DROP TABLE capasgeograficas.\"").append(idPredio).append("\"");
        qr.update(sql.toString());
        }
        catch(SQLException eSql)
        {
        
        StringBuilder sql = new StringBuilder();
        sql.append("DROP TABLE capasgeograficas.\"").append(idPredio).append("\"");
        qr.update(sql.toString());
        predio.setMensaje("Error al procesar archivo DBF: "+eSql.getMessage());
        predio.setSuccessfull(Boolean.FALSE);
        }
        catch(Exception ex)
        {
         StringBuilder sql = new StringBuilder();
        sql.append("DROP TABLE capasgeograficas.\"").append(idPredio).append("\"");
        qr.update(sql.toString());
        predio.setMensaje("ErrorError: "+ex.getMessage());
        predio.setSuccessfull(Boolean.FALSE);
        
        }
        
        return predio;
        
    }
    
}
