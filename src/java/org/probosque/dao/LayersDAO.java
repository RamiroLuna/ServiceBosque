package org.probosque.dao;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.LayerDTO;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.ThemeDTO;
import org.probosque.dto.UserDTO;

/**
 * Clase que administra en base de datos las capas de información geográfica
 * @author admin
 */
public class LayersDAO {
    
    public void deleteLayer(UserDTO user, String layerName) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" DROP TABLE ").append(SQL.SCHEMA_CAPAS).append(".\"").append(layerName).append("\"");
        qr.update(sql.toString());
    }

    public List<LayerDTO> getLayers(UserDTO user) throws SQLException {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler rsh = new BeanListHandler(LayerDTO.class);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT replace(f_table_name, 'capasgeograficas.', '') AS id, replace(f_table_name, 'capasgeograficas.', '') AS label");
        sql.append(" FROM geometry_columns");
        sql.append(" WHERE f_table_schema = ?");
        Object[] params = {
            SQL.SCHEMA_CAPAS
        };
        List<LayerDTO> layers = (List<LayerDTO>) qr.query(sql.toString(), rsh, params);      
        return layers;
    }
    
    public LayerDTO checkFolioLayer(UserDTO user, String tableName) throws SQLException {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler rsh = new BeanHandler(LayerDTO.class);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT folio AS label");
        sql.append(" FROM ").append(SQL.SCHEMA_CAPAS).append(".\"").append(tableName).append("\"");
        sql.append(" LIMIT 1");
        LayerDTO layer = (LayerDTO) qr.query(sql.toString(), rsh);      
        return layer;
    }
    
    public String getWkt(UserDTO user, String layerName) throws SQLException {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler rsh = new BeanHandler(LayerDTO.class);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ST_ASTEXT(ST_TRANSFORM(ST_SetSRID(ST_Extent(the_geom),32614),4326)) as wkt");
        sql.append(" FROM capasgeograficas.\"").append(layerName).append("\"");
        LayerDTO layer = (LayerDTO) qr.query(sql.toString(), rsh);
        if(layer!=null){
            return layer.getWkt();
        }
        else{
            return "";
        }
    }
    
    
    
    public List<ThemeDTO> getThemes(UserDTO user) throws SQLException {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler rsh = new BeanListHandler(ThemeDTO.class);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT replace(f_table_name, 'capasgeograficas.', '') AS id, replace(f_table_name, 'capasgeograficas.', '') AS label");
        sql.append(" FROM geometry_columns");
        sql.append(" WHERE f_table_schema = ?");
        Object[] params = {
            SQL.SCHEMA_CAPAS
        };
        List<ThemeDTO> themes = (List<ThemeDTO>) qr.query(sql.toString(), rsh, params);      
        return themes;
    }
    
    public int getCount(UserDTO user, String tableName, int idRegion) throws SQLException {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT COUNT(the_geom) AS result");
        sql.append(" FROM ").append(SQL.SCHEMA_CAPAS).append(".\"").append(tableName).append("\"").append(",");
        sql.append(" (SELECT geom FROM ").append(SQL.TABLE_REGIONES);
        sql.append(" WHERE gid = ?) AS region");
        sql.append(" WHERE ST_CONTAINS(geom, the_geom)");
        
        Object[] params = {
            idRegion
        };
        ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh, params);
        if (result != null) {
            return result.getResult();
        } else {
            return 0;
        }
    }
}