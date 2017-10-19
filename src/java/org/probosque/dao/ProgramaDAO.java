package org.probosque.dao;

import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.probosque.dto.ProgramaDTO;

/**
 * Clase que administra en base de datos la información que describe cada uno de los programas; sin embargo no maneja la información contenida en los formularios * @author admin
 */

public class ProgramaDAO {
    public ProgramaDTO getProgram(int programa) throws Exception {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();    
        sql.append(" SELECT id, descripcion, unidad_medida, accion");
        sql.append(" FROM ").append(SQL.TABLE_PROGRAMAS);
        sql.append(" WHERE id = ?");
        Object[] params = {
            programa
        };
        ResultSetHandler rsh = new BeanHandler(ProgramaDTO.class);
        ProgramaDTO program = (ProgramaDTO) qr.query(sql.toString(), rsh, params);
        return program;
    } 
}