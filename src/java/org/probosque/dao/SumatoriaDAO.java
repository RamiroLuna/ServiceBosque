package org.probosque.dao;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.SumatoriaDTO;

/**
 * Clase que retorna de la base de datos la sumatoria de acuerdo a un programa proporcionado
 * @author admin
 */

public class SumatoriaDAO {
    
    public List<SumatoriaDTO> getSumatorias(int program) throws SQLException {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler rsh = new BeanListHandler(SumatoriaDTO.class);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id, idprograma, programa, unidadmedida, cantidad, accion, montoapoyoaprobado,montoapoyopagado, resultado, observaciones");
        sql.append(" FROM ").append(SQL.TABLE_SUMATORIAS);
        sql.append(" WHERE idprograma = ?");
        Object[] params = {program};
        List<SumatoriaDTO> regiones = (List<SumatoriaDTO>) qr.query(sql.toString(), rsh, params);      
        return regiones;
    }    
}