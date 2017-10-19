package org.probosque.dao;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.probosque.dto.StringDTO;
import org.probosque.dto.UserDTO;

/**
 * Clase que administra en base de datos las tablas que proporcionan la información geográfica
 * @author admin
 */

public class TableDataDAO {
    
    public void renameTable(/*String schemaName, String tableName, String oldName,*/ UserDTO user, String newName) throws Exception {
        String newNameWithCommilla = "\"" + newName + "\"";
        
        renameColumn(user, "capasgeograficas.bosque1_r_c");
        reproyectTable(user, "capasgeograficas", "bosque1_r_c", "the_geom");
        renameColumnFolio(user, "capasgeograficas.bosque1_r_c");
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" ALTER TABLE ").append("capasgeograficas.bosque1_r_c");
        sql.append(" RENAME TO ").append(newNameWithCommilla);
        qr.update(sql.toString());
        
        createIndex(user, newName);
        
        LayersDAO dao = new LayersDAO();
        try{
            dao.checkFolioLayer(user, newName);
        } catch (Exception error) {
            dao.deleteLayer(user, newName);
            throw error;
        }
    }
    
    private void createIndex(UserDTO user, String tableName){
        try {
            DataSource ds = PoolDataSource.getDataSource(user);
            QueryRunner qr = new QueryRunner(ds);
            StringBuilder sql = new StringBuilder();
            sql.append(" CREATE INDEX ").append("\"").append("index_capasgeograficas_").append(tableName).append("\"");
            sql.append(" ON capasgeograficas.").append("\"").append(tableName).append("\"");
            sql.append(" USING gist(the_geom)");
            qr.update(sql.toString());
        } catch (SQLException ex) {
            Logger.getLogger(TableDataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void renameColumn(UserDTO user, String name) {
        try {
            DataSource ds = PoolDataSource.getDataSource(user);
            QueryRunner qr = new QueryRunner(ds);
            StringBuilder sql = new StringBuilder();
            sql.append(" ALTER TABLE ").append(name);
            sql.append(" RENAME COLUMN geom TO the_geom");
            qr.update(sql.toString());
        } catch (Exception error) {

        }
    }
    
    public String reproyectTable(UserDTO user, String schemaName, String tableName, String columnGeom) throws SQLException {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler rsh = new BeanHandler(StringDTO.class);
        Object[] params = {schemaName, tableName, columnGeom, 32614};
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT UpdateGeometrySRID(?,?,?,?)");
        StringDTO result = (StringDTO) qr.query(sql.toString(), rsh, params);      
        return result!=null?result.toString():"";
    }
    
    private void renameColumnFolio(UserDTO user, String name) {
        try {
            DataSource ds = PoolDataSource.getDataSource(user);
            QueryRunner qr = new QueryRunner(ds);
            StringBuilder sql = new StringBuilder();
            sql.append(" ALTER TABLE ").append(name);
            sql.append(" RENAME COLUMN \"Folio\" TO folio");
            qr.update(sql.toString());
        } catch (Exception error) {

        }
    }   
}