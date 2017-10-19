package org.probosque.dao;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.CatalogoDTO;


/**
 * Clase que administra en base de datos la información de los municipios para todos los programas
 * @author admin
 */
public class MunicipioDAO {
    
    public List<CatalogoDTO> getMunicipios(int idRegion) throws SQLException {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id, descripcion");
        sql.append(" FROM catalogos.municipio");
        sql.append(" WHERE id_region = ?");
        sql.append(" ORDER BY descripcion");
        Object[] params = {idRegion};
        List<CatalogoDTO> municipios = (List<CatalogoDTO>) qr.query(sql.toString(), rsh, params);      
        return municipios;
    }    
}