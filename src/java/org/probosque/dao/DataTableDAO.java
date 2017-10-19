package org.probosque.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.probosque.dto.ColumnDTO;
import org.probosque.dto.NumberDTO;
import org.probosque.dto.TableDTO;
import org.probosque.dto.UserDTO;

/**
 * Clase que administra en base de datos la información de las totales en base a alguna columna y tabla especifica
 * @author admin
 */
public class DataTableDAO {
    
    public int getTotalInt(UserDTO user, String activity, TableDTO table, String filter, String order, int offset, int limit) throws SQLException {
        
        ArrayList<TableDTO> tables = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        String resultString = "";
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        
        int total = 0;
    
        
        
        
        
        
        
        StringBuilder sql = new StringBuilder();
         sql.append(" SELECT * FROM ("); 
        //sql.append(" SELECT ").append(table.getSQLColumnsJoin());
        sql.append(" SELECT ").append(table.getSQLSelectJoinWithRecords());//getSQLColumnsJoin());
        sql.append(" FROM ").append(SQL.getTableMain(user, activity));
        sql.append(table.getSQLJoins(user, activity /*tableName.replaceAll("programa", "").replaceAll(".principal", ""))*/));
        
        sql.append(" ) AS matches ");
        sql.append(filter);
        //sql.append(order);
        sql.append(" ORDER BY folio");

        try {
            con = PoolDataSource.getDataSource(user).getConnection();
            stmt = con.createStatement();

            rs = stmt.executeQuery(sql.toString());

            result.append("[");

            while (rs.next()) {
                total++;

            }

        } catch (SQLException e) {
            Logger.getLogger(SubTableDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
            con.close();
            return total;
        }
    }
    
    
    /*
    public int getTotal(UserDTO user, String activity, String order, String where, int offset, int limit) throws SQLException {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT count(*) AS number"); 
        sql.append(" FROM ").append(SQL.getTableMain(user, activity));
        sql.append(where);
        ResultSetHandler rsh = new BeanHandler(NumberDTO.class);
        NumberDTO number = (NumberDTO) qr.query(sql.toString(), rsh);
        return number!=null?number.getNumber():0;
    }*/
}