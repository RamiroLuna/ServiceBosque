package org.probosque.dao;

import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.probosque.dto.QrDTO;
import org.probosque.dto.UserDTO;

/**
 * Clase que administra en base de datos la información tabular perteneciente a cada una las imagenes QR de algunos programas
 * @author admin
 */

public class QrDAO {

    public void updateImage(UserDTO user, String folio, String image) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE ").append("formularios.planta_valedelegacion");
        sql.append(" SET codigoqr=?");
        sql.append(" WHERE folio = ?");
        Object[] params = {
            image, folio
        };
        qr.update(sql.toString(), params);
    }
    
    public String getExt(UserDTO user, String folio) throws Exception {
        String ext = "";
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();    
        sql.append(" SELECT codigoqr AS image");
        sql.append(" FROM ").append("formularios.planta_valedelegacion");
        sql.append(" WHERE folio = ?");
        Object[] params = {
            folio
        };
        ResultSetHandler rsh = new BeanHandler(QrDTO.class);
        QrDTO image = (QrDTO) qr.query(sql.toString(), rsh, params);
        if(image!=null){
            ext = image.getImage().substring(image.getImage().lastIndexOf(".")+1);
        }   
        return ext;
    }
}
