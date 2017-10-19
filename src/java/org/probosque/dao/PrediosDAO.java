package org.probosque.dao;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.PointDTO;
import org.probosque.dto.UserDTO;

/**
 * Clase que administra en base de datos la información de los predios, es información geográfica
 * @author admin
 */

public class PrediosDAO {

    public List<PointDTO> getPoints(UserDTO user, String layerName, double xmin, double ymin, double xmax, double ymax) throws SQLException {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler rsh = new BeanListHandler(PointDTO.class);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT gid AS id, ST_ASTEXT(ST_TRANSFORM(the_geom,4326)) AS wkt, folio");
        sql.append(" FROM ").append(SQL.SCHEMA_CAPAS).append(".\"").append(layerName).append("\"");
        sql.append(" WHERE ST_INTERSECTS(the_geom,ST_Transform(ST_MakeEnvelope(?,?,?,?,4326),32614))");
        Object[] params1 = {
            xmin, ymin, xmax, ymax
        };
        List<PointDTO> points = (List<PointDTO>) qr.query(sql.toString(), rsh, params1);      
        return points;
    }
    
    public List<PointDTO> find(UserDTO user, String schemaName, String idLayer, String predio) throws SQLException {
        
        String tableName = schemaName+ ".\"" + idLayer + "\"";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        ResultSetHandler rsh = new BeanListHandler(PointDTO.class);
        sql.append(" SELECT gid AS id, ST_ASTEXT(ST_PointOnSurface(ST_TRANSFORM(the_geom,4326))) AS wkt, folio, CAST('").append(idLayer).append("' AS TEXT) AS idlayer");
        sql.append(" FROM ").append(tableName).append(", (SELECT regexp_split_to_table(?, ',') AS match) AS ids");
        sql.append(" WHERE folio = ids.match");
        Object[] params = {
            predio
        };
        List<PointDTO> points = (List<PointDTO>) qr.query(sql.toString(), rsh, params);      
        return points;
    }
}