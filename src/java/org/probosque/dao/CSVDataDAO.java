package org.probosque.dao;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.probosque.dto.CSVDataDTO;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.UserDTO;

/**
 * Clase que administra en base de datos la información de las solicitudes para las descargas de información en formato CSV
 * @author admin
 */

public class CSVDataDAO {

    /**
     * Método que proporciona el siguiente ID de la tabla general.csvdata
     * @param user
     * @return siguiente ID
     * @throws SQLException 
     */
    private int getNextId(UserDTO user) throws SQLException {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT nextval('general.sequence_csvdata') AS result;");
        ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh);      
        if(result!=null){
            return result.getResult();
        }
        else{
            return 0;
        }
    }
    
    /**
     * Método que proporciona el objeto de la tabla general.csvdata
     * @param user usuario
     * @param id id
     * @return objeto CSV
     * @throws SQLException 
     */
    public CSVDataDTO getCsvData(UserDTO user, int id) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id, json");
        sql.append(" FROM general.csvdata");
        sql.append(" WHERE id = ?");
        Object[] params = {
            id
        };
        ResultSetHandler rsh = new BeanHandler(CSVDataDTO.class);
        CSVDataDTO csv = (CSVDataDTO) qr.query(sql.toString(), rsh, params);
        return csv;
    }
    
    /**
     * Método que agrega a la tabla general.csvdata un nuevo registro
     * @param userDto
     * @param json registro en formato json
     * @return
     * @throws SQLException 
     */
    public int insert(UserDTO userDto, String json) throws Exception {
        int id = getNextId(userDto);
        DataSource ds = PoolDataSource.getDataSource(userDto);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO general.csvdata");
        sql.append("(id, json)");
        sql.append(" VALUES (?,?)");
        Object[] params = {id, json};
        qr.update(sql.toString(), params);
        return id;
    }
}
