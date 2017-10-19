package org.probosque.dao;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.RegionDTO;
import org.probosque.dto.UserDTO;

/**
 * Clase que administra en base de datos la información de las regiones gegráficas, proporciona los atributos necesarios para visualizar los datos gegraficamente
 * @author admin
 */

public class RegionDAO {
    
    public List<RegionDTO> getRegiones(UserDTO user) throws SQLException {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler rsh = new BeanListHandler(RegionDTO.class);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT gid AS id, reg_for || ' ' || nom_regfor AS nombre,ST_ASTEXT(ST_Force_2D(ST_TRANSFORM(ST_SETSRID(geom, 32614), 4326))) AS wkt");
        sql.append(" FROM ").append(SQL.TABLE_REGIONES);
        List<RegionDTO> regiones = (List<RegionDTO>) qr.query(sql.toString(), rsh);      
        return regiones;
    }    
}