/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dao;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.CatalogoDTO;
/**
 *
 * @author TOSHIBA-L55
 */
public class SubcuencasDao {
    public List<CatalogoDTO> getSubcuenca(int idSubCuenca) throws SQLException {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id, descripcion");
        sql.append(" FROM catalogos.subcuenca");
        sql.append(" WHERE id_cuenca = ?");
        sql.append(" ORDER BY descripcion");
        Object[] params = {idSubCuenca};
        List<CatalogoDTO> Subcuenca = (List<CatalogoDTO>) qr.query(sql.toString(), rsh, params);      
        return Subcuenca;
    }
}
