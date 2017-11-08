package org.probosque.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.CatalogoDTO;
import org.probosque.dto.CatalogoStringDTO;
import org.probosque.dto.ColumnDTO;
import org.probosque.dto.DatosMetaDTO;
import org.probosque.dto.MetaDTO;
import org.probosque.dto.Operation;
import org.probosque.dto.Request;
import org.probosque.dto.ResultBoolean;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.ResultString;
import org.probosque.dto.RowDTO;
import org.probosque.dto.Summary;
import org.probosque.dto.TableDTO;
import org.probosque.dto.UserDTO;
import org.probosque.model.PDFcontent;
import org.probosque.model.PDFrow;
import org.probosque.model.Tools;

/**
 * Clase que administra en base de datos la información referente a cada uno de los formularios, contiene la descripción de cada una de las columnas que conforman los formularios
 * @author admin
 */

public class TableDAO {

    public List<ColumnDTO> getColumns(UserDTO user, String activity, boolean ignoreNull, int id_municipio, int id_region) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT type, label, name, datatype, listname, max_length, min_length, CAST('' AS TEXT) AS value, editable, privacy");
        sql.append(" FROM ").append(SQL.getTableInfo(user, activity));
        if (user.getRole_id() == 3) {
            sql.append(" WHERE privacy != true");
        }
        sql.append(" ORDER BY position");
        ResultSetHandler rsh = new BeanListHandler(ColumnDTO.class);
        List<ColumnDTO> columns = (List<ColumnDTO>) qr.query(sql.toString(), rsh);
        
        for (ColumnDTO column : columns) {
            if (column.getName().equals("modulopredio_estado")) {
                column.setValue("15");
            }
            if (column.getDatatype().equals("list")) {
                if (column.getName().contains("modulopredio_")) {
                    column.setOnlyselect(true);
                } else {
                    column.setOnlyselect(false);
                }
                
                CatalogoDAO catalogoDao = new CatalogoDAO();
                
                if(!ignoreNull){
                    column.setList(catalogoDao.getList(user, column.getListname(),0));
                }
                else{
                    /*
                     * @Decription 
                     * Issue para filtrar los municipios en base a la region
                     */
                    if (column.getName().equals("modulopredio_municipio")) {
                       column.setList(catalogoDao.getList(user, column.getListname().replaceAll("null;", ""), id_region));
                    }else 
                    /*
                     * Fin issue
                     */
                    if (id_municipio != 0) {
                       column.setList(catalogoDao.getList(user, column.getListname().replaceAll("null;", ""), id_municipio));
                    }else{
                        column.setList(catalogoDao.getList(user, column.getListname().replaceAll("null;", ""),0));
                    }
                }
                
            }

        }
        return columns;
    }

    public List<ColumnDTO> getColumnsForReport(UserDTO user, String activity) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT type, label, name, datatype, listname, max_length, min_length, CAST('' AS TEXT) AS value, editable, privacy");
        sql.append(" FROM ").append(SQL.getTableInfo(user, activity));
        if (user.getRole_id() == 3) {
            sql.append(" WHERE privacy != true");
            sql.append(" AND name != 'modulopredio_estado'");
        }
        sql.append(" WHERE name != 'modulopredio_estado'");
        sql.append(" ORDER BY position");
        ResultSetHandler rsh = new BeanListHandler(ColumnDTO.class);
        List<ColumnDTO> columns = (List<ColumnDTO>) qr.query(sql.toString(), rsh);
        for (ColumnDTO column : columns) {
            if (column.getName().equals("modulopredio_estado")) {
                column.setValue("15");
            }

            if (column.getDatatype().equals("list")) {
                if (column.getName().contains("modulopredio_")) {
                    column.setOnlyselect(true);
                } else {
                    column.setOnlyselect(false);
                }
                
                CatalogoDAO catalogoDao = new CatalogoDAO();
                
                column.setList(catalogoDao.getList(user, column.getListname().replaceAll("null;", ""),0));
                
            }

        }
        return columns;
    }
    
    public List<ColumnDTO> getColumnsPredios() throws Exception {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT type, label, name, datatype, listname, max_length, min_length, CAST('' AS TEXT) AS value, editable, privacy");
        sql.append(" FROM ").append(SQL.getTablePrediosInfo());
        sql.append(" ORDER BY position");
        ResultSetHandler rsh = new BeanListHandler(ColumnDTO.class);
        List<ColumnDTO> columns = (List<ColumnDTO>) qr.query(sql.toString(), rsh);
        for (ColumnDTO column : columns) {
            if (column.getName().equals("modulopredio_estado")) {
                column.setValue("15");
            }
            if (column.getDatatype().equals("list")) {
                if (column.getName().contains("modulopredio_")) {
                    column.setOnlyselect(true);
                } else {
                    column.setOnlyselect(false);
                }
                
                if (column.getName().equals("folio")) {
                    column.setOnlyselect(true);
                }
                
                CatalogoDAO catalogoDao = new CatalogoDAO();
                column.setList(catalogoDao.getList(column.getListname()));
                
                
            }

        }
        return columns;
    }

    public List<ColumnDTO> getColumnsPrediosAnios() throws Exception {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT type, label, name, datatype, listname, max_length, min_length, CAST('' AS TEXT) AS value, editable, privacy");
        sql.append(" FROM ").append(SQL.getTablePrediosAniosInfo());
        sql.append(" ORDER BY position");
        ResultSetHandler rsh = new BeanListHandler(ColumnDTO.class);
        List<ColumnDTO> columns = (List<ColumnDTO>) qr.query(sql.toString(), rsh);
        for (ColumnDTO column : columns) {
            if (column.getName().equals("modulopredio_estado")) {
                column.setValue("15");
            }

            if (column.getDatatype().equals("list")) {
                if (column.getName().contains("modulopredio_")) {
                    column.setOnlyselect(true);
                } else {
                    column.setOnlyselect(false);
                }
                
                CatalogoDAO catalogoDao = new CatalogoDAO();
                column.setList(catalogoDao.getList(column.getListname()));
                
              
            }

          
        }
        return columns;
    }

    public List<ColumnDTO> getColumnsByDataTable(UserDTO user, String activity) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT type, label, name, datatype, listname, max_length, min_length, CAST('' AS TEXT) AS value, editable, privacy");
        sql.append(" FROM ").append(SQL.getTableInfo(user, activity));
        if (user.getRole_id() == 3) {
            sql.append(" WHERE privacy != true");
            sql.append(" AND name != 'modulopredio_estado'");
        }
        sql.append(" WHERE name != 'modulopredio_estado'");
        sql.append(" ORDER BY position");
        ResultSetHandler rsh = new BeanListHandler(ColumnDTO.class);
        List<ColumnDTO> columns = (List<ColumnDTO>) qr.query(sql.toString(), rsh);
        return columns;
    }

    public List<ColumnDTO> getColumnsByIdentify(UserDTO user, String activity, String folio) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT type, label, name, datatype, listname, max_length, min_length, CAST('' AS TEXT) AS value, editable, privacy");
        sql.append(" FROM ").append(SQL.getTableInfo(user, activity));
        if (user.getRole_id() == 3) {
            sql.append(" WHERE privacy != true");
        }
        sql.append(" ORDER BY position");
        ResultSetHandler rsh = new BeanListHandler(ColumnDTO.class);
        List<ColumnDTO> columns = (List<ColumnDTO>) qr.query(sql.toString(), rsh);
        
        
        
        for (ColumnDTO column : columns) {
            if (column.getName().equals("folio")) {
                column.setValue(folio/*getFolio(tableName, program)*/);
            }
            
            
            if (column.getDatatype().equals("list")) {
                if (column.getName().contains("modulopredio_")) {
                    column.setOnlyselect(true);
                } else {
                    column.setOnlyselect(false);
                }
                
                CatalogoDAO catalogoDao = new CatalogoDAO();
                column.setList(catalogoDao.getList(user, column.getListname(),0));
                
            }

        }
        return columns;
    }

    public List<ColumnDTO> getColumnsShape(UserDTO user, String layer, String folio) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder dataType = new StringBuilder();
        dataType.append(" CASE WHEN udt_name = 'int4' THEN 'numeric'");
        dataType.append(" WHEN udt_name = 'numeric' THEN 'real'");
        dataType.append(" WHEN udt_name = 'varchar' THEN 'string'");
        dataType.append(" ELSE 'string' END AS datatype");

        StringBuilder sql = new StringBuilder();
        //sql.append(" SELECT CAST('' AS text) AS type, column_name AS label, column_name AS name, udt_name AS datatype, CAST('' AS text) AS listname, 0 AS max_length, 0 AS min_length, CAST('' AS TEXT) AS value, true AS editable, true AS privacy");
        sql.append(" SELECT CAST('' AS text) AS type, column_name AS label, column_name AS name,").append(dataType.toString()).append(", CAST('' AS text) AS listname, 0 AS max_length, 0 AS min_length, CAST('' AS TEXT) AS value, true AS editable, true AS privacy");
        sql.append(" FROM information_schema.columns");
        sql.append(" WHERE table_schema = ? AND table_name = ? AND udt_name != ?");
        sql.append(" ORDER BY ordinal_position");
        ResultSetHandler rsh = new BeanListHandler(ColumnDTO.class);
        Object[] params = {"capasgeograficas", layer, "geometry"};
        List<ColumnDTO> columns = (List<ColumnDTO>) qr.query(sql.toString(), rsh, params);
        for (ColumnDTO column : columns) {
            if (column.getName().equals("folio")) {
                column.setValue(folio/*getFolio(tableName, program)*/);
            }
            if (column.getListname() != null && !column.getListname().isEmpty()) {
                CatalogoDAO catalogoDao = new CatalogoDAO();
                column.setList(catalogoDao.getList(user, column.getListname(),0));
            }
            if (column.getListname() != null && !column.getListname().isEmpty()) {
                CatalogoDAO catalogoDao = new CatalogoDAO();
                column.setList(catalogoDao.getList(user, column.getListname(),0));
            }
        }
        return columns;
    }

    public int getRecords(UserDTO user, String tableName, String folio) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT count(CAST(consecutivo AS integer)) AS result");
        sql.append(" FROM ").append(tableName);
        sql.append(" WHERE folio = ?");
        Object[] params = {folio};
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh, params);
        if (result != null) {
            return result.getResult();
        } else {
            return 0;
        }
    }

    public String getFolio(UserDTO user, String activity, String anio, String idRegion) throws Exception {
        //tableName = tableName.replace("_info", "");
        String tableName = SQL.getTableMain(user, activity);
        String prefix = "";
        anio = anio.substring(2);

        switch (user.getProgram()) {
            case 1:
                prefix = "AM";
                break;
            case 2:
                prefix = "AT";
                break;
            case 3:
                prefix = "PR";
                break;
            case 4:
                prefix = "PC";
                break;
            case 5:
                prefix = "PC";
                break;
            case 6:
                prefix = "SF";
                break;
            case 7:                
                //codigo mike para la correcta creacion de prefijos de folio
                //para el programa 71 y 72 
                //se hizo el cambio de tomar en cuenta la activdad y no los nombres de usr
                //15/06/2016
                if(user.getActivity()==3){
                   prefix = "IF";
                 }else if(user.getActivity()==4){
                            prefix = "AP";
                        }
                break;
            case 8:
                prefix = "IV";
                break;
            case 9:
                prefix = "PP";
                break;
            case 10:
                prefix = "AH";
                break;
            case 11:
                prefix = "PP";
                break;
            case 12:
                prefix = "SM";
                break;
            case 13:
                prefix = "VE";
                break;
            case 14:
                prefix = "DF";
                break;
        }

        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
     /*Codigo Mike Martínez 15/06/2016
        su funcion es permitir la carga correcta de datos en las tablas
        principal_actividades y principal_incendios para los programas 71 y 72 
              
        */
        if(user.getProgram()!=7)
     {
        sql.append(" SELECT MAX(consecutivo) AS result FROM");
        sql.append(" (");
        sql.append(" SELECT substring(folio from 3 for 2) AS anio, substring(folio from 5 for 2) AS region, substring(folio from 7 for 3) AS consecutivo");
        sql.append(" FROM formularios.principal");
        sql.append(" ) AS sub");
        sql.append(" WHERE anio = ? AND region = ?");
     }
     else
     {
      if(user.getActivity()==3)
      {
        sql.append(" SELECT MAX(consecutivo) AS result FROM");
        sql.append(" (");
        sql.append(" SELECT substring(folio from 3 for 2) AS anio, substring(folio from 5 for 2) AS region, substring(folio from 7 for 3) AS consecutivo");
        sql.append(" FROM formularios.principal_incendios");
        sql.append(" ) AS sub");
        sql.append(" WHERE anio = ? AND region = ?");
      } 
     else
          if(user.getActivity()==4)
          {
              sql.append(" SELECT MAX(consecutivo) AS result FROM");
              sql.append(" (");
              sql.append(" SELECT substring(folio from 3 for 2) AS anio, substring(folio from 5 for 2) AS region, substring(folio from 7 for 3) AS consecutivo");
              sql.append(" FROM formularios.principal_actividades");
              sql.append(" ) AS sub");
              sql.append(" WHERE anio = ? AND region = ?");
          }
     
     }
    //fin codigo Mike 15/06/2016    
     Object[] params = {anio, idRegion};

        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh, params);
        if (result != null) {
            result.setResult(result.getResult() + 1);
            if (result.getResult() < 10) {
                //return prefix + "150100" + result.getResult();
                return prefix + anio + idRegion + "00" + result.getResult();
            }
            if (result.getResult() < 100) {
                //return prefix + "15010"  + result.getResult();
                return prefix + anio + idRegion + "0" + result.getResult();
            } else {
                //return prefix + "1501" + result.getResult();
                return prefix + anio + idRegion + result.getResult();
            }
        } else {
            //return prefix + "AT15010001";
            return prefix + anio + idRegion + "001";
        }
    }
/*
    Codigo Mike Martínez 16=06/2016
    lanza  una consulta para traer un numero consecutivo 
    funciona solo para el programa de incendios 
    */
public String getNumIncendio(UserDTO user, String activity, String anio, String idRegion) throws SQLException
{
        //String tableName = SQL.getTableMain(user, activity);
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        String nIncendio = null;
        if(user.getProgram()==7 && !anio.equals("combatientes"))
        {
           
         sql.append(" SELECT COUNT(numincendio) AS result");
         sql.append(" FROM formularios.principal_incendios");
         sql.append(" WHERE anio = '");
         sql.append(anio).append("'");
         ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
         ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh);
                  
         if(result!=null)
                {
                 result.setResult(result.getResult()+1);
                }
         
         nIncendio=(String) String.valueOf(result.getResult());
         
        }
        else
        {
         if(user.getProgram()==7)
           {    
            String folio=idRegion;
            sql.append(" SELECT SUM(CAST(num_combatientes AS INTEGER)) AS result");
            sql.append(" FROM formularios.incendios_brigadas");
            sql.append(" WHERE folio = '");
            sql.append(folio).append("'");
            ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
            ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh);
            nIncendio=(String) String.valueOf(result.getResult());
          } 
         if(user.getProgram()==2){
               if (anio.equals("totalhombres")){   
                String folio=idRegion;
               sql.append(" SELECT COUNT(genero) AS result");
               sql.append(" FROM formularios.participantes");
               sql.append(" WHERE folio = '");
               sql.append(folio).append("' AND genero='1'");
               ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
               ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh);
               nIncendio=String.valueOf(result.getResult());
           }
               else if( anio.equals("totalmujeres"))
                  {
                      String folio=idRegion;
                      sql.append(" SELECT COUNT(genero) AS result");
                      sql.append(" FROM formularios.participantes");
                      sql.append(" WHERE folio = '");
                      sql.append(folio).append("' AND genero='2'");
                      ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
                      ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh);
                      nIncendio=String.valueOf(result.getResult());
                  }   
             }

        }
    return nIncendio;
}   



public int getTotalVehiculos(UserDTO user, String folio) throws SQLException
{
        //String tableName = SQL.getTableMain(user, activity);
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        String nIncendio = null;
      
         sql.append(" select case when  sum(cast(cantidad as integer)) is null  then 0 else sum(cast(cantidad as integer)) end as result");
         sql.append("  from formularios.vehiculos ");
         sql.append(" WHERE folio = '");
         sql.append(folio).append("'");
         ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
         ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh);
           return result.getResult();
}    




//fin codigo mike 16/06/2016
    public String getSum(UserDTO user, String subtable, String field, String folio) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        
        String paramFolio = "'" + folio + "'"; 
        
        sql.append(" SELECT CAST(sum(").append(field).append(") AS text) AS result");
        sql.append(" FROM ").append(subtable);
        sql.append(" WHERE folio = ?");
        
        Object[] params = {folio};
        
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh, params);
        
        if (result != null) {
            return String.valueOf(result.getResult());
        } else {
            return "0";
        }
    }

    public String getFolioPredioNext(String estado, String region, String municipio, String localidad) throws Exception {
        estado = Tools.completeString(estado, 2);
        region = Tools.completeString(region, 2);
        municipio = Tools.completeString(municipio, 3);
        localidad = Tools.completeString(localidad, 4);
        
        String tableName = SQL.getTableMain();
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT MAX(consecutivo) AS result FROM");
        sql.append(" (");
        sql.append(" SELECT substring(folio from 1 for 2) AS estado, substring(folio from 3 for 2) AS region, substring(folio from 5 for 3) AS municipio,substring(folio from 12 for 4) AS consecutivo");
        sql.append(" FROM ").append(tableName);
        sql.append(" ) AS sub");
        sql.append(" WHERE estado = ? AND region = ? AND municipio = ?");
        Object[] params = {estado, region, municipio};

        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh, params);
        if (result != null) {
            result.setResult(result.getResult() + 1);
            if (result.getResult() < 10) {
                return estado + region + municipio + localidad + "000" + result.getResult();
            } else {
                if (result.getResult() < 100) {
                    return estado + region + municipio + localidad + "00" + result.getResult();
                } else {
                    //if (result.getResult() < 1000) {
                    return estado + region + municipio + localidad + "0" + result.getResult();
                }
            }
        }
        else {
            return estado + region + municipio + localidad  + "0001";
        }
    }

    public List<ColumnDTO> getColumnsSearchable(UserDTO user, String activity) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT type, label, name, datatype, listname, max_length, min_length, CAST('valor' AS TEXT) AS value, editable, privacy");
        sql.append(" FROM ").append(SQL.getTableInfo(user, activity));
        sql.append(" WHERE searchable = true");
        if (user.getRole_id() == 3) {
            sql.append(" AND privacy != true");
        }
        sql.append(" ORDER BY position");
        ResultSetHandler rsh = new BeanListHandler(ColumnDTO.class);
        List<ColumnDTO> columns = (List<ColumnDTO>) qr.query(sql.toString(), rsh);
        for (ColumnDTO column : columns) {
            if (column.getDatatype().equals("list")) {
                if (column.getName().contains("modulopredio_")) {
                    column.setOnlyselect(true);
                } else {
                    column.setOnlyselect(false);
                }
                
                CatalogoDAO catalogoDao = new CatalogoDAO();
                column.setList(catalogoDao.getList(user, column.getListname(),0));
                
            }
            
        }
        return columns;
    }

    public TableDTO getValues(UserDTO user, String activity, TableDTO table, String folio) {
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ").append(table.getSQLSelect());
        sql.append(" FROM ").append(SQL.getTableMain(user, activity));
        sql.append(" WHERE folio =").append("'").append(folio).append("'");

        try {
            con = PoolDataSource.getDataSource(user).getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql.toString());
            if (rs.next()) {
                for (int i = 0; i < table.getColumns().size(); i++) {
                    switch (table.getColumns().get(i).getDatatype()) {
                        case "string":case "alphanumeric":case "time":
                            String value = rs.getString(table.getColumns().get(i).getName());
                            table.getColumns().get(i).setValue(value != null ? value : "");
                            //table.getColumns().get(i).setValue(rs.getString(table.getColumns().get(i).getName()));
                            break;
                        case "real":
                            table.getColumns().get(i).setValue(rs.getDouble(table.getColumns().get(i).getName()));
                            break;
                        case "numeric":
                            table.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            break;
                        case "date":
                            value = rs.getString(table.getColumns().get(i).getName());
                            table.getColumns().get(i).setValue(value != null ? value : "");
                            break;
                        case "datetime":
                            value = rs.getString(table.getColumns().get(i).getName());
                            table.getColumns().get(i).setValue(value != null ? value : "");
                            break;
                        case "list":
                            String value2 = rs.getString(table.getColumns().get(i).getName());
                            table.getColumns().get(i).setValue(value2 != null ? value2 : "");
                            //table.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            break;
                    }
                }
            }
        } catch (SQLException e) {
            String mensaje = "";

            //Logger.getLogger(TableDAO.class.getName()).log(Level.SEVERE, null, e);
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
            return table;
        }
    }

    public TableDTO getValuesFromShape(UserDTO user, TableDTO table, String tableName, String folio) {
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ").append(table.getSqlSelectColumnsWithComillas());
        sql.append(" FROM ").append(SQL.SCHEMA_CAPAS).append(".\"").append(tableName).append("\"");
        sql.append(" WHERE folio =").append("'").append(folio).append("'");

        try {
            con = PoolDataSource.getDataSource(user).getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql.toString());
            if (rs.next()) {
                for (int i = 0; i < table.getColumns().size(); i++) {
                    switch (table.getColumns().get(i).getDatatype()) {
                        case "string":case "alphanumeric":case "time":
                            String value = rs.getString(table.getColumns().get(i).getName());
                            table.getColumns().get(i).setValue(value != null ? value : "");
                            //table.getColumns().get(i).setValue(rs.getString(table.getColumns().get(i).getName()));
                            break;
                        case "real":
                            table.getColumns().get(i).setValue(rs.getDouble(table.getColumns().get(i).getName()));
                            break;
                        case "numeric":
                            table.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            break;
                        case "date":
                            value = rs.getString(table.getColumns().get(i).getName());
                            table.getColumns().get(i).setValue(value != null ? value : "");
                            break;
                        case "list":
                            String value2 = rs.getString(table.getColumns().get(i).getName());
                            table.getColumns().get(i).setValue(value2 != null ? value2 : "");
                            //table.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            break;
                    }
                }
            }
        } catch (SQLException e) {
            String mensaje = "";

            //Logger.getLogger(TableDAO.class.getName()).log(Level.SEVERE, null, e);
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
            return table;
        }
    }

    public TableDTO getValuesIdentify(UserDTO user, String activity, TableDTO table, String folio) {
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ").append(table.getSQLSelect());
        sql.append(" FROM ").append(SQL.getTableMain(user, activity));
        sql.append(" WHERE folio =").append("'").append(folio).append("'");

        try {
            con = PoolDataSource.getDataSource(user).getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql.toString());
            if (rs.next()) {
                for (int i = 0; i < table.getColumns().size(); i++) {
                    switch (table.getColumns().get(i).getDatatype()) {
                        case "string":case "alphanumeric":case "time":
                            String value = rs.getString(table.getColumns().get(i).getName());
                            table.getColumns().get(i).setValue(value != null ? value : "");
                            //table.getColumns().get(i).setValue(rs.getString(table.getColumns().get(i).getName()));
                            break;
                        case "real":
                            table.getColumns().get(i).setValue(rs.getDouble(table.getColumns().get(i).getName()));
                            break;
                        case "numeric":
                            table.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            break;
                        case "date":
                            value = rs.getString(table.getColumns().get(i).getName());
                            table.getColumns().get(i).setValue(value != null ? value : "");
                            break;
                        case "list":
                            String value2 = rs.getString(table.getColumns().get(i).getName());
                            table.getColumns().get(i).setValue(value2 != null ? value2 : "");
                            //table.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            break;
                    }
                }
            }
        } catch (SQLException e) {
            String mensaje = "";

            //Logger.getLogger(TableDAO.class.getName()).log(Level.SEVERE, null, e);
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
            return table;
        }
    }

   
    public List<RowDTO> find(UserDTO user, String activity, TableDTO table, String folio) throws SQLException {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ").append(table.getSQLSelect());
        sql.append(" FROM ").append(SQL.getTableMain(user, activity));
        sql.append(" WHERE UPPER(folio) LIKE ?");
        Object[] params = {
            "%" + folio.toUpperCase() + "%"
        };
        ResultSetHandler rsh = new BeanListHandler(RowDTO.class);
        List<RowDTO> matches = (List<RowDTO>) qr.query(sql.toString(), rsh, params);
        return matches;
    }

    
    public void insertTable(UserDTO user, String activity, TableDTO table) throws SQLException, Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO ").append(SQL.getTableMain(user, activity));
        sql.append(" (").append(table.getSqlEditColumns()).append(")");//username, password, firstname, lastname, address, phone, email, curp, rfc, role_id, program, privacy)");
        sql.append(" VALUES (").append(table.getSQLParams()).append(")"); //?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        qr.update(sql.toString(), table.getParams());
        
        LogDAO log=new LogDAO();
                String lusr=user.getId()+"-"+user.getFirstname()+" "+user.getLastname()+"-"+user.getProgram();
                log.log(lusr,1,"Se registra Folio "+table.getFolio());
    }

    public void editTable(UserDTO user, String activity, TableDTO table) throws SQLException, Exception {
        ArrayList<String> dataOld= queryList(user, activity, table);
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE ").append(SQL.getTableMain(user, activity));
        sql.append(" SET ").append(table.getSQLEditParams());//username, password, firstname, lastname, address, phone, email, curp, rfc, role_id, program, privacy)");
        sql.append(" WHERE folio=?");
        qr.update(sql.toString(), table.getEditParams());
        //codigo Log Ivan 14 07 2916 
         ArrayList<String> dataUpdate= queryList(user, activity, table);
        ArrayList<String> NameField = ColumnName(user, activity, table);
        String changes = fieldsUpdate(dataOld, dataUpdate,NameField,user,activity,table);
        LogDAO log=new LogDAO();
                String lusr=user.getId()+"-"+user.getFirstname()+" "+user.getLastname()+"-"+user.getProgram();
                log.log(lusr,2,"Modificacion de datos del folio "+table.getFolio()+"\n"+changes);
    }

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 
    
    public ArrayList<String> queryList(UserDTO user, String activity, TableDTO table) throws SQLException
    {
        ArrayList <String> data = new ArrayList<String>();
        Connection connection = null;
        DataSource ds = PoolDataSource.getDataSource(user);
        connection = ds.getConnection();
        Statement sta1 = connection.createStatement();
        Statement sta = connection.createStatement();
 
        String query1 = "SELECT * FROM "+SQL.getTableMain(user, activity)+" where folio='"+table.getFolio()+"'";
        ResultSet rs1 = sta1.executeQuery(query1);
        
        String TableName = SQL.getTableMain(user, activity).substring(12); 
        String query = "SELECT count(*) FROM  information_schema.columns where  table_name='"+TableName+"' and table_schema='formularios'" ;
        ResultSet rs = sta.executeQuery(query);
        
        while(rs.next())
        {
           int columns = rs.getInt(1);
           
            while (rs1.next()) {
                    for (int i = 1; i <= columns; i++) 
                    {
                            data.add(""+rs1.getObject(i));     
                    }
                
            }

       }      
        rs.close();
        sta.close();
        rs1.close();
        sta.close();
        connection.close();
        return data;
    }
    
    public String fieldsUpdate(ArrayList<String> old,ArrayList<String> update,ArrayList<String> nameField,UserDTO user,String activity, TableDTO table)throws SQLException
    {
        String cambios="";
        for(int x=0;x<old.size();x++) {
            
            if(!old.get(x).equals(update.get(x)))
            { 
             //Comprobar si el nameField.get(x) tiene asociado un catalogo
             //Si tiene asociado un catalogo entonces pasar el id.
             //En caso contrario hacer lo de la linea anterior
             String LabelName=nameField.get(x);
             String catalogo=tieneCatalogo(nameField.get(x), user, activity);
             if(catalogo!="")
             {
                //Consultar si existe Catalogo
                if(existeCatalogo(catalogo,user, activity, table))
                {
                    String Dataold = data_Catalog(catalogo, old.get(x), user, activity, table);
                    String Dataupdate = data_Catalog(catalogo, update.get(x), user, activity, table);
                    cambios= cambios +" <b>"+nameField.get(x)+"</b> cambió de: "+Dataold+" a: "+Dataupdate+" ";
                }
                else //Si no existe catalogo
                {
                    cambios=cambios + " Hubo cambió en: <b>" +nameField.get(x)+"</b>";
                }
                     
             }else
             {
                cambios= cambios +" <b>"+nameField.get(x)+"</b> cambió de: "+old.get(x)+" a: "+update.get(x)+" ";     
             }
             
            }
        }
        
        return cambios;
        
    }
    
    public ArrayList<String> ColumnName(UserDTO user, String activity, TableDTO table)throws SQLException
    {
        ArrayList<String> ListName = new ArrayList<>();
        Connection connection = null;
        DataSource ds = PoolDataSource.getDataSource(user);
        connection = ds.getConnection();
        Statement sta = connection.createStatement();
        Statement sta1 = connection.createStatement();
        String TableName = SQL.getTableMain(user, activity).substring(12);
        
        String query = "select column_name from information_schema.columns where table_name='"+TableName+"'";
        ResultSet rs1 = sta.executeQuery(query);
        
        while(rs1.next())
        {
         String query1 = "select label from "+SQL.getTableMain(user, activity)+"_info where name='"+rs1.getString(1)+"'";
         ResultSet rs = sta1.executeQuery(query1);
         
            if(rs.next()) 
            {                
                ListName.add(rs.getString(1));
            }
            else
            {
                ListName.add("Campo sin etiqueta");
            }
            rs.close();
        }
        
       sta.close();
       rs1.close();
       sta1.close();
       connection.close();
       return  ListName;
       
    }
    
    public String tieneCatalogo(String name, UserDTO user, String activity)throws SQLException
    {
     String catalogName ="";
     Connection connection;
     DataSource ds1 = PoolDataSource.getDataSource(user);
     connection = ds1.getConnection();
     Statement sta = connection.createStatement();
     String query ="select listname from "+SQL.getTableMain(user, activity)+"_info where label='"+name+"'";
     ResultSet rs = sta.executeQuery(query);
     
     while(rs.next())
     {
        catalogName= rs.getString(1);
     }
     
     sta.close();
     rs.close();
     connection.close();   
     return rightName(catalogName); 
    }
    
    public String data_Catalog(String catalogo,String id, UserDTO user, String activity, TableDTO table)throws SQLException
    {
     String data ="";
     boolean b = false;
     try{
         //Verifica que sea un numero para buscar por Id
         int x =Integer.parseInt(id);
         b=true;
         }
          catch (Exception e) {
         b=false;
     }
     
     if(b==true)
     {
            Connection connection = null;
            DataSource ds = PoolDataSource.getDataSource(user);
            connection = ds.getConnection();
            Statement sta = connection.createStatement();
            String query ="select descripcion from "+catalogo+" where id="+id;     
            ResultSet rs = sta.executeQuery(query);

            if(rs.next())
            {
               data= rs.getString(1);
            }
            else
            {
                data="Sin asignar";
            }

            sta.close();
            rs.close();
            connection.close();
     }else 
         if(b==false)
         {
             data="Dato no identificado";
         }
            return data;

    }
    
    public String rightName(String name)
    {
       if(name != null)
       {
            if(name.length()>5)
            {
                if("null;".equals(name.substring(0, 5)))
                {
                    return name.substring(5);
                }
                else
                {
                    return name;
                }  
            }
            else
            {
                return name="";
            }

       }
       else
       {
           return name="";
       }
    }
    
    public boolean existeCatalogo(String nameCatalogo,UserDTO user,String activity, TableDTO table)throws SQLException
    {
     boolean existe=false;
     Connection connection = null;
     String nameOk= nameCatalogo.substring(10);
     if(!nameOk.equals("localidad"))
     {
     
     DataSource ds = PoolDataSource.getDataSource(user);
     connection = ds.getConnection();
     Statement statement = connection.createStatement();
     String query="select count(*) FROM information_schema.columns\n" +
                  "where table_schema <>'information_schema' and table_schema <>'pg_catalog'  \n" +
                  "and table_schema='catalogos' and table_name='"+nameOk+"'";
     ResultSet res = statement.executeQuery(query);
     
        while (res.next()) 
        {
            if(res.getInt(1)>0)
            {
                existe=true;
            }
            else
            {
                existe=false;
            }
        }
        
        statement.close();
        res.close();    
        connection.close();
     }
      else if (nameOk.equals("localidad"))
      {
        existe=false;
       }
     return existe;
    }
    
    
      

    //  fin Codigo ivan ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void deleteTable(UserDTO user, String activity, String folio) throws SQLException, Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM ").append(SQL.getTableMain(user, activity));
        sql.append(" WHERE folio = ?");
        Object[] params = {
            folio
        };
        qr.update(sql.toString(), params);
        
        LogDAO log=new LogDAO();
                String lusr=user.getId()+"-"+user.getFirstname()+" "+user.getLastname()+"-"+user.getProgram();
                log.log(lusr,3,"Eliminacion del Folio "+folio);
    }

    public String getTables(UserDTO user, String activity, TableDTO table, String filter, String order) throws SQLException {
        SQLException error = null;
        boolean hasError = false;
        ArrayList<TableDTO> tables = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        String resultString = "";

        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        /*
         StringBuilder sql = new StringBuilder();
         sql.append(" SELECT ").append(table.getSQLColumns());
         sql.append(" FROM ").append(tableName);
         sql.append(filter);
         sql.append(order);
         */

        StringBuilder sql = new StringBuilder();
        //sql.append(" SELECT ").append(table.getSQLColumnsJoin());
        sql.append(" SELECT * FROM ("); 
        sql.append(" SELECT ").append(table.getSQLSelectJoinWithRecords());//getSQLColumnsJoin());
        //sql.append(" SELECT ").append(table.getSQLColumnsJoin());//getSQLColumnsJoin());
        sql.append(" FROM ").append(SQL.getTableMain(user, activity));
        sql.append(table.getSQLJoins(user, activity/*, tableName.replaceAll("programa", "").replaceAll(".principal", ""))*/));
        sql.append(" GROUP BY ").append(table.getSQLSelectJoinWithRecords2());
        sql.append(" ) AS matches ");
        
        sql.append(filter);
        sql.append(order);

        //sql.append(" LIMIT 1");
        try {
            con = PoolDataSource.getDataSource(user).getConnection();
            stmt = con.createStatement();

            rs = stmt.executeQuery(sql.toString());

            result.append("[");

            while (rs.next()) {
                TableDTO newTable = new TableDTO();
                ArrayList<ColumnDTO> newColumns = new ArrayList<>();

                for (ColumnDTO column : table.getColumns()) {
                    newColumns.add(column);
                }

                newTable.setColumns(newColumns);

                for (int i = 0; i < table.getColumns().size(); i++) {
                    switch (newTable.getColumns().get(i).getDatatype()) {
                        case "string":case "alphanumeric":case "time":
                            String value = rs.getString(table.getColumns().get(i).getName());
                            value = value != null ? value : "";
                            value = value.replaceAll("\"", "'");
                            newTable.getColumns().get(i).setValue(value);
                            //newTable.getColumns().get(i).setValue(rs.getString(table.getColumns().get(i).getName()));
                            break;
                        case "real":
                            newTable.getColumns().get(i).setValue(rs.getDouble(table.getColumns().get(i).getName()));
                            break;
                        case "numeric":
                            newTable.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            break;
                        case "date":
                            value = rs.getString(table.getColumns().get(i).getName());
                            value = value != null ? value : "";
                            value = value.replaceAll("\"", "'");
                            newTable.getColumns().get(i).setValue(value);
                            break;
                        case "list":
                            String value2 = rs.getString(table.getColumns().get(i).getName());
                            value2 = value2 != null ? value2 : "";
                            value2 = value2.replaceAll("\"", "'");
                            newTable.getColumns().get(i).setValue(value2);
                            //newTable.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            break;
                        case "records":
                            /*
                             String value3 = "{\"campo\":{\"total\":\"1\",\"folio\":\"AM0001008\",\"label\":\""+table.getColumns().get(i).getLabel()+"\"}}";
                             newTable.getColumns().get(i).setValue(value3);
                             */

                            //String value3 = rs.getString("\"" + table.getColumns().get(i).getName() + "\"");
                            //String value3 = rs.getString("\"" + table.getColumns().get(i).getName() + "\"");
                            String value3 = rs.getString(table.getColumns().get(i).getName().replaceAll("[.]", ""));
                            value3 = value3 != null ? value3 : "";
                            //value3 = value3.replaceAll("\"", "'");
                            newTable.getColumns().get(i).setValue(value3);

                            //String value3 = "{\"campo\":{\"total\":\"1\",\"folio\":\"AM0001008\",\"label\":\"AM0001008\"}}";
                            //newTable.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            //newTable.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            /*                            
                             //String value3 = rs.getString("\"" + table.getColumns().get(i).getName() + "\"");
                             String value3 = "\"total\"";
                             //value3 = value3!=null?value3:"";
                             //value3 = value3.replaceAll("\"", "'");
                             newTable.getColumns().get(i).setValue(value3);
                             //newTable.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                             */
                            /*
                             //String value3 = rs.getString("\"" + table.getColumns().get(i).getName() + "\"");
                             String value3 = rs.getString(table.getColumns().get(i).getName());
                             value3 = value3!=null?value3:"";
                             //value3 = value3.replaceAll("\"", "'");
                             newTable.getColumns().get(i).setValue(value3);
                             //newTable.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                             */
                            break;
                    }
                }

                result.append(newTable.getFieldValueReport()).append(",");
                tables.add(newTable);

            }
            result.append("]");

            resultString = result.toString();
            resultString = resultString.replace(",]", "]");

        } catch (SQLException e) {
            error = e;
            hasError = true;
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
            if (hasError) {
                throw error;
            }
            return resultString;
        }
    }

    public String getTablesFindOtros(UserDTO user, String activity, TableDTO table, String where, String order) throws SQLException {
        SQLException error = null;
        boolean hasError = false;
        ArrayList<TableDTO> tables = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        String resultString = "";

        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        /*
         StringBuilder sql = new StringBuilder();
         sql.append(" SELECT ").append(table.getSQLColumns());
         sql.append(" FROM ").append(tableName);
         sql.append(filter);
         sql.append(order);
         */

        StringBuilder sql = new StringBuilder();
        //sql.append(" SELECT ").append(table.getSQLColumnsJoin());
        
        sql.append(" SELECT ").append(table.getSQLSelectJoinWithRecords());//getSQLColumnsJoin());
        //sql.append(" SELECT ").append(table.getSQLColumnsJoin());//getSQLColumnsJoin());
        sql.append(" FROM ").append(SQL.getTableMain(user, activity));
        sql.append(table.getSQLJoins(user, activity/*, tableName.replaceAll("programa", "").replaceAll(".principal", ""))*/));
        sql.append(where);        
        
        sql.append(order);

        //AGREGAR FILTRO 
        //sql.append(" LIMIT 1");
        try {
            con = PoolDataSource.getDataSource(user).getConnection();
            stmt = con.createStatement();

            rs = stmt.executeQuery(sql.toString());

            result.append("[");

            while (rs.next()) {
                TableDTO newTable = new TableDTO();
                ArrayList<ColumnDTO> newColumns = new ArrayList<>();

                for (ColumnDTO column : table.getColumns()) {
                    newColumns.add(column);
                }

                newTable.setColumns(newColumns);

                for (int i = 0; i < table.getColumns().size(); i++) {
                    switch (newTable.getColumns().get(i).getDatatype()) {
                        case "string":case "alphanumeric":case "time":
                            String value = rs.getString(table.getColumns().get(i).getName());
                            value = value != null ? value : "";
                            value = value.replaceAll("\"", "'");
                            newTable.getColumns().get(i).setValue(value);
                            //newTable.getColumns().get(i).setValue(rs.getString(table.getColumns().get(i).getName()));
                            break;
                        case "real":
                            newTable.getColumns().get(i).setValue(rs.getDouble(table.getColumns().get(i).getName()));
                            break;
                        case "numeric":
                            newTable.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            break;
                        case "date":
                            value = rs.getString(table.getColumns().get(i).getName());
                            value = value != null ? value : "";
                            value = value.replaceAll("\"", "'");
                            newTable.getColumns().get(i).setValue(value);
                            break;
                        case "list":
                            String value2 = rs.getString(table.getColumns().get(i).getName());
                            value2 = value2 != null ? value2 : "";
                            value2 = value2.replaceAll("\"", "'");
                            newTable.getColumns().get(i).setValue(value2);
                            //newTable.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            break;
                        case "records":
                            /*
                             String value3 = "{\"campo\":{\"total\":\"1\",\"folio\":\"AM0001008\",\"label\":\""+table.getColumns().get(i).getLabel()+"\"}}";
                             newTable.getColumns().get(i).setValue(value3);
                             */

                            //String value3 = rs.getString("\"" + table.getColumns().get(i).getName() + "\"");
                            //String value3 = rs.getString("\"" + table.getColumns().get(i).getName() + "\"");
                            String value3 = rs.getString(table.getColumns().get(i).getName().replaceAll("[.]", ""));
                            value3 = value3 != null ? value3 : "";
                            //value3 = value3.replaceAll("\"", "'");
                            newTable.getColumns().get(i).setValue(value3);

                            //String value3 = "{\"campo\":{\"total\":\"1\",\"folio\":\"AM0001008\",\"label\":\"AM0001008\"}}";
                            //newTable.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            //newTable.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            /*                            
                             //String value3 = rs.getString("\"" + table.getColumns().get(i).getName() + "\"");
                             String value3 = "\"total\"";
                             //value3 = value3!=null?value3:"";
                             //value3 = value3.replaceAll("\"", "'");
                             newTable.getColumns().get(i).setValue(value3);
                             //newTable.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                             */
                            /*
                             //String value3 = rs.getString("\"" + table.getColumns().get(i).getName() + "\"");
                             String value3 = rs.getString(table.getColumns().get(i).getName());
                             value3 = value3!=null?value3:"";
                             //value3 = value3.replaceAll("\"", "'");
                             newTable.getColumns().get(i).setValue(value3);
                             //newTable.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                             */
                            break;
                    }
                }

                result.append(newTable.getFieldValueReport()).append(",");
                tables.add(newTable);

            }
            result.append("]");

            resultString = result.toString();
            resultString = resultString.replace(",]", "]");

        } catch (SQLException e) {
            error = e;
            hasError = true;
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
            if (hasError) {
                throw error;
            }
            return resultString;
        }
    }
    
    
    public String getRecordsForTable(UserDTO user, String activity, TableDTO table, String filter, String order, int offset, int limit) {
        ArrayList<TableDTO> tables = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        String resultString = "";
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        
        
    
        
        
        
        
        
        
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
        sql.append(" OFFSET ").append(offset);
        sql.append(" LIMIT ").append(limit);
        try {
            con = PoolDataSource.getDataSource(user).getConnection();
            stmt = con.createStatement();

            rs = stmt.executeQuery(sql.toString());

            result.append("[");

            while (rs.next()) {
                TableDTO newTable = new TableDTO();
                ArrayList<ColumnDTO> newColumns = new ArrayList<>();

                for (ColumnDTO column : table.getColumns()) {
                    newColumns.add(column);
                }

                newTable.setColumns(newColumns);

                for (int i = 0; i < table.getColumns().size(); i++) {
                    switch (newTable.getColumns().get(i).getDatatype()) {
                        case "string":case "alphanumeric":case "time":
                            String value = rs.getString(table.getColumns().get(i).getName());
                            value = value != null ? value : "";
                            value = value.replaceAll("\"", "'");
                            newTable.getColumns().get(i).setValue(value);
                            //newTable.getColumns().get(i).setValue(rs.getString(table.getColumns().get(i).getName()));
                            break;
                        case "real":
                            newTable.getColumns().get(i).setValue(rs.getDouble(table.getColumns().get(i).getName()));
                            break;
                        case "numeric":
                            newTable.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            break;
                        case "date":
                            value = rs.getString(table.getColumns().get(i).getName());
                            value = value != null ? value : "";
                            value = value.replaceAll("\"", "'");
                            newTable.getColumns().get(i).setValue(value);
                            break;
                        case "list":
                            String value2 = rs.getString(table.getColumns().get(i).getName());
                            value2 = value2 != null ? value2 : "";
                            value2 = value2.replaceAll("\"", "'");
                            newTable.getColumns().get(i).setValue(value2);
                            //newTable.getColumns().get(i).setValue(rs.getString(table.getColumns().get(i).getName()));
                            break;
                        case "records":
                            String value3 = rs.getString(table.getColumns().get(i).getName().replaceAll("[.]", ""));
                            value3 = value3 != null ? value3 : "";
                            newTable.getColumns().get(i).setValue(value3);
                            break;
                    }
                }

                result.append(newTable.getFieldValueReport()).append(",");
                //result.append(newTable.getFieldValue()).append(",");
                tables.add(newTable);

            }
            result.append("]");

            resultString = result.toString();
            resultString = resultString.replace(",]", "]");

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
            return resultString;
        }
    }

    public String getCSV(UserDTO user, String activity, TableDTO table, String filter, String order) {
        ArrayList<TableDTO> tables = new ArrayList<>();

        StringBuilder result = new StringBuilder();
        String resultString = "";

        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        /*
         StringBuilder sql = new StringBuilder();
         sql.append(" SELECT ").append(table.getSQLColumns());
         sql.append(" FROM ").append(tableName);
         sql.append(filter);
         sql.append(order);
         */
        
        /*
           StringBuilder sql = new StringBuilder();
         sql.append(" SELECT * FROM ("); 
        //sql.append(" SELECT ").append(table.getSQLColumnsJoin());
        sql.append(" SELECT ").append(table.getSQLSelectJoinWithRecords());//getSQLColumnsJoin());
        sql.append(" FROM ").append(SQL.getTableMain(user, activity));
        sql.append(table.getSQLJoins(user, activity /*tableName.replaceAll("programa", "").replaceAll(".principal", ""))));
        
        sql.append(" ) AS matches ");
        sql.append(filter);
        */
        
        
           StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM (");
        sql.append(" SELECT ").append(table.getSQLSelectJoin());
        sql.append(" FROM ").append(SQL.getTableMain(user, activity));
        sql.append(table.getSQLJoins(user, activity/*, tableName.replaceAll("programa", "").replaceAll(".principal", "")*/));
        sql.append(" ) AS matches ");
        sql.append(filter);
        sql.append(order);

        try {
            con = PoolDataSource.getDataSource(user).getConnection();
            stmt = con.createStatement();

            rs = stmt.executeQuery(sql.toString());
            
            StringBuilder titles = new StringBuilder();
            for (ColumnDTO column : table.getColumns()) {
                titles.append(String.valueOf(column.getLabel())).append(",");
            }
            titles.append("\n");
            byte ptexto[] = titles.toString().getBytes("ISO-8859-1"); 
            
            String titlesString = titles.toString();
            titlesString = new String(ptexto, "UTF-8");
            titlesString = titlesString.replace(",\n", "\n");
            result.append(titlesString);
            while (rs.next()) {
                TableDTO newTable = new TableDTO();
                ArrayList<ColumnDTO> newColumns = new ArrayList<>();

                for (ColumnDTO column : table.getColumns()) {
                    newColumns.add(column);
                }

                newTable.setColumns(newColumns);

                for (int i = 0; i < table.getColumns().size(); i++) {
                    switch (newTable.getColumns().get(i).getDatatype()) {
                        case "string":case "alphanumeric":case "time":
                            String value = rs.getString(table.getColumns().get(i).getName());
                            newTable.getColumns().get(i).setValue(value != null ? value : "");
                            //newTable.getColumns().get(i).setValue(rs.getString(table.getColumns().get(i).getName()));
                            break;
                        case "real":
                            newTable.getColumns().get(i).setValue(rs.getDouble(table.getColumns().get(i).getName()));
                            break;
                        case "numeric":
                            newTable.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            break;
                        case "date":
                            value = rs.getString(table.getColumns().get(i).getName());
                            newTable.getColumns().get(i).setValue(value != null ? value : "");
                            break;
                        case "list":
                            String value2 = rs.getString(table.getColumns().get(i).getName());
                            newTable.getColumns().get(i).setValue(value2 != null ? value2 : "");
                            //newTable.getColumns().get(i).setValue(rs.getInt(table.getColumns().get(i).getName()));
                            break;
                    }
                }

                result.append(newTable.getCSVValue());
                tables.add(newTable);

            }
            resultString = result.toString();
            resultString = resultString.replace(",]", "]");
           // byte ptexto[] = result.toString().getBytes("UTF-8"); 
           // resultString = new String(ptexto, "UTF-8");

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
            return resultString;
        }
    }

    public PDFcontent getPDF(UserDTO user, String activity, TableDTO table, String filter, String order) throws Exception {
        ArrayList<ColumnDTO> pdfColumns = table.getColumns();
        ArrayList<PDFrow> pdfRows = new ArrayList<>();

        StringBuilder result = new StringBuilder();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;

        /*
         StringBuilder sql = new StringBuilder();
         sql.append(" SELECT ").append(table.getSQLColumns());
         sql.append(" FROM ").append(tableName);
         sql.append(filter);
         sql.append(order);
         */
        
        ///
   
        ///
        
        
        
        
        StringBuilder sql = new StringBuilder();
          sql.append(" SELECT * FROM ("); 
        //sql.append(" SELECT ").append(table.getSQLColumnsJoin());
        sql.append(" SELECT ").append(table.getSQLSelectJoinWithRecords());
        sql.append(" FROM ").append(SQL.getTableMain(user, activity));
        sql.append(table.getSQLJoins(user, activity/*, tableName.replaceAll("programa", "").replaceAll(".principal", "")*/));
        sql.append(" ) AS matches ");
        sql.append(filter);
        sql.append(order);
        //sql.append(" LIMIT 5");

        
        try {
            con = PoolDataSource.getDataSource(user).getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql.toString());

            while (rs.next()) {
                PDFrow pdfRow = new PDFrow();

                for (int i = 0; i < table.getColumns().size(); i++) {
                    String value = "";
                    switch (table.getColumns().get(i).getDatatype()) {
                        case "string":case "alphanumeric":case "time":
                            value = rs.getString(table.getColumns().get(i).getName());
                            value = value != null ? value : "";
                            break;
                        case "real":
                            value = String.valueOf(rs.getDouble(table.getColumns().get(i).getName()));
                            break;
                        case "numeric":
                            value = String.valueOf(rs.getInt(table.getColumns().get(i).getName()));
                            break;
                        case "date":
                            value = rs.getString(table.getColumns().get(i).getName());
                            value = value != null ? value : "";
                            break;
                        case "list":
                            value = rs.getString(table.getColumns().get(i).getName());
                            value = value != null ? value : "";
                            break;
                        case "records":
                            value = "";
                            break;
                    }
                    pdfRow.addValue(value);
                }
                pdfRows.add(pdfRow);
            }
        } catch (SQLException e) {
            System.out.println("Error 4 "+e);
            Logger.getLogger(SubTableDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                //catch
                System.out.println("Error 5 "+e);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                //
                System.out.println("Error 6 "+e);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                //catch
                System.out.println("Error 7 "+e);
            }

            SubTableDAO dao = new SubTableDAO();

            TableDTO subTable = new TableDTO();
            subTable.setColumns(pdfColumns);

            /*
             for (int i = 0; i < pdfColumns.size(); i++) {
             String folio = "";
             if (pdfColumns.get(i).getName().equals("folio")) {
             folio = String.valueOf(pdfRows.get(i).getValues().get(i));
             }
             if (pdfColumns.get(i).getDatatype().equals("records")) {
             //dao.getColumns(user, pdfColumns.get(i).getName() + "_info" , folio);
             PDFcontent pdfContent = new PDFcontent((ArrayList<ColumnDTO>) dao.getColumns(user, pdfColumns.get(i).getName() + "_info", folio), null);
             pdfRows.get(iRow).getValues().set(i, pdfContent);
             }
             }*/
            for (int iRow = 0; iRow < pdfRows.size(); iRow++) {
                String folio = "";
                for (int i = 0; i < pdfColumns.size(); i++) {
                    if (pdfColumns.get(i).getName().equals("folio")) {
                        folio = String.valueOf(pdfRows.get(iRow).getValues().get(i));
                    }
                    if (pdfColumns.get(i).getDatatype().equals("records")) {
                        //dao.getColumns(user, pdfColumns.get(i).getName() + "_info" , folio);
                        //PDFcontent pdfContent = dao.getPDF(user, folio, folio)
                        //new PDFcontent((ArrayList<ColumnDTO>) dao.getColumns(user, pdfColumns.get(i).getName() + "_info" , folio), null);            
                        //PDFcontent pdfContent = new PDFcontent((ArrayList<ColumnDTO>) dao.getColumns(user, pdfColumns.get(i).getName() + "_info" , folio), null);
                        //pdfRows.get(iRow).getValues().set(i, pdfContent);
                        pdfRows.get(iRow).getValues().set(i, dao.getPDF(user, pdfColumns.get(i).getName(), folio));
                    }
                }
            }

            PDFcontent pdfContent = new PDFcontent(pdfColumns, pdfRows);
            return pdfContent;
        }
    }


    public String getTablesGraph(UserDTO user, String activity, TableDTO table, String graph, String filter, String order) {
        ArrayList<TableDTO> tables = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        String resultString = "";

        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT folio, ").append(graph);
        sql.append(" FROM ").append(SQL.getTableMain(user, activity));
        sql.append(filter);
        sql.append(order);

        try {
            con = PoolDataSource.getDataSource(user).getConnection();
            stmt = con.createStatement();

            rs = stmt.executeQuery(sql.toString());

            result.append("[");

            while (rs.next()) {
                TableDTO newTable = new TableDTO();
                ArrayList<ColumnDTO> newColumns = new ArrayList<>();

                String folio = rs.getString("folio");

                for (ColumnDTO column : table.getColumns()) {
                    if (column.getName().equals(graph)) {

                        column.setFolio(folio);
                        newColumns.add(column);
                    }
                }

                newTable.setColumns(newColumns);

                for (int i = 0; i < newTable.getColumns().size(); i++) {
                    switch (newTable.getColumns().get(i).getDatatype()) {
                        case "string":case "alphanumeric":case "time":
                            String value = rs.getString(table.getColumns().get(i).getName());
                            newTable.getColumns().get(i).setValue(value != null ? value : "");
                            //newTable.getColumns().get(i).setValue(rs.getString(newTable.getColumns().get(i).getName()));
                            break;
                        case "real":
                            newTable.getColumns().get(i).setValue(rs.getDouble(newTable.getColumns().get(i).getName()));
                            break;
                        case "numeric":
                            newTable.getColumns().get(i).setValue(rs.getInt(newTable.getColumns().get(i).getName()));
                            break;
                        case "date":
                            value = rs.getString(table.getColumns().get(i).getName());
                            newTable.getColumns().get(i).setValue(value != null ? value : "");
                            break;
                        case "list":
                            String value2 = rs.getString(table.getColumns().get(i).getName());
                            newTable.getColumns().get(i).setValue(value2 != null ? value2 : "");
                            //newTable.getColumns().get(i).setValue(rs.getInt(newTable.getColumns().get(i).getName()));
                            break;
                    }
                }

                /*
                 AM0103023
                 AM0103023
                 */
                result.append(newTable.getLabelValue()).append(",");
                tables.add(newTable);
            }
            result.append("]");

            resultString = result.toString();
            resultString = resultString.replace(",]", "]");

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
            return resultString;
        }
    }

    public boolean existsFolio(UserDTO user, String activity, String folio) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT true AS result");
        sql.append(" FROM ").append(SQL.getTableMain(user, activity));
        sql.append(" WHERE folio = ?");
        Object[] params = {folio};
        ResultSetHandler rsh = new BeanHandler(ResultBoolean.class);
        ResultBoolean result = (ResultBoolean) qr.query(sql.toString(), rsh, params);
        if (result != null) {
            return result.isResult();
        } else {
            return false;
        }
    }

    public void insertFolio(UserDTO user, String activity, String folio) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO ").append(SQL.getTableMain(user, activity));
        sql.append(" (folio)");
        sql.append(" VALUES (?)");
        Object[] params = {folio};
        qr.update(sql.toString(), params);
    }

    /*
     public List<Operation> getOperations(UserDTO user, String tableName) throws Exception {
     DataSource ds = PoolDataSource.getDataSource(user);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append(" SELECT operation, destiny, datatype");
     sql.append(" FROM ").append(SQL.getTableRequest(user));
     ResultSetHandler rsh = new BeanListHandler(Operation.class);
     List<Operation> operations = (List<Operation>) qr.query(sql.toString(), rsh);
     return operations;
     }
    
     public List<Request> getRequest(UserDTO user) throws Exception {
     DataSource ds = PoolDataSource.getDataSource(user);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append(" SELECT operation, destiny, service, datatype");
     sql.append(" FROM ").append(SQL.getTableRequest(user));
     ResultSetHandler rsh = new BeanListHandler(Request.class);
     List<Request> requests = (List<Request>) qr.query(sql.toString(), rsh);
     return requests;
     }*/
    public List<Operation> getOperations_activity(UserDTO user, String activity) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT operation, destiny, datatype");
        sql.append(" FROM ").append(SQL.getTableOperations(user, activity));
        ResultSetHandler rsh = new BeanListHandler(Operation.class);
        List<Operation> operations = (List<Operation>) qr.query(sql.toString(), rsh);
        return operations;
    }

    public List<Operation> getOperations(UserDTO user, String tableName) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT operation, destiny, datatype");
        sql.append(" FROM ").append(tableName);
        ResultSetHandler rsh = new BeanListHandler(Operation.class);
        List<Operation> operations = (List<Operation>) qr.query(sql.toString(), rsh);
        return operations;
    }

    public List<Request> getRequest(UserDTO user, String activity) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT operation, destiny, service, datatype, clean");
        sql.append(" FROM ").append(SQL.getTableRequest(user, activity));
        ResultSetHandler rsh = new BeanListHandler(Request.class);
        List<Request> requests = (List<Request>) qr.query(sql.toString(), rsh);
        return requests;
    }
    
    public List<Summary> getSummary(UserDTO user, String activity) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT destiny, service, subtable, field, typedestiny");
        sql.append(" FROM ").append(SQL.getTableSummary(user, activity));
        ResultSetHandler rsh = new BeanListHandler(Summary.class);
        List<Summary> summarys = (List<Summary>) qr.query(sql.toString(), rsh);
        return summarys;
    }

    public List<Request> getRequestGeneral(UserDTO user, String activity) throws Exception {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT operation, destiny, service, datatype, clean");
        sql.append(" FROM ").append(SQL.getTableRequest(user, activity));
        ResultSetHandler rsh = new BeanListHandler(Request.class);
        List<Request> requests = (List<Request>) qr.query(sql.toString(), rsh);
        return requests;
    }
    
    public List<CatalogoStringDTO> getFoliosPredio(String estado, String region, String municipio, String localidad) throws Exception {
        estado = Tools.completeString(estado, 2);
        region = Tools.completeString(region, 2);
        municipio = Tools.completeString(municipio, 3);
        localidad = Tools.completeString(localidad, 4);
        
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT folio AS id, predio AS descripcion");
        sql.append(" FROM ").append(SQL.getTableMain());
        sql.append(" WHERE folio LIKE ?");
        String folio = estado + region + municipio + "%";
        Object[] params = {folio};
        ResultSetHandler rsh = new BeanListHandler(CatalogoStringDTO.class);
        List<CatalogoStringDTO> requests = (List<CatalogoStringDTO>) qr.query(sql.toString(), rsh, params);
        return requests;
    }
    
    /*
    Codigo Jonathan aldama  Para metas de reforestacion Anual Programa 3
    Implementacion fecha 03/08/2016 
    
     */
    
public List<MetaDTO> ObtenerRegionMeta(UserDTO user) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id, descripcion");
        sql.append(" FROM catalogos.region");
        ResultSetHandler rsh = new BeanListHandler(MetaDTO.class);
        List<MetaDTO> requests = (List<MetaDTO>) qr.query(sql.toString(), rsh);
        return requests;
    }
    
    public List<DatosMetaDTO> ObtenerDatosMeta(UserDTO user) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT formularios.meta.id_meta, catalogos.region.descripcion, formularios.meta.anio, formularios.meta.meta_anual");
        sql.append(" FROM formularios.meta inner join catalogos.region on formularios.meta.id_region = catalogos.region.id order by 1");
        ResultSetHandler rsh = new BeanListHandler(DatosMetaDTO.class);
        List<DatosMetaDTO> requests = (List<DatosMetaDTO>) qr.query(sql.toString(), rsh);
        return requests;
    }
    
    public boolean InsertDatosMeta(UserDTO user, int id_region, String anio, String meta_anual) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO formularios.meta (id_region, anio, meta_anual)");
        sql.append(" VALUES (?, ?, ?)");
        Object[] params = {id_region, anio, meta_anual};
        qr.update(sql.toString(), params);
        return true;
    }
    
    public List<DatosMetaDTO> ObtenerDatosnMetabyID(UserDTO user, int id_meta) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT formularios.meta.id_meta, formularios.meta.id_region, catalogos.region.descripcion, formularios.meta.anio, formularios.meta.meta_anual");
        sql.append(" FROM formularios.meta inner join catalogos.region on formularios.meta.id_region = catalogos.region.id");
        sql.append(" WHERE formularios.meta.id_meta = ?");
        Object[] params = {id_meta};
        ResultSetHandler rsh = new BeanListHandler(DatosMetaDTO.class);
        List<DatosMetaDTO> requests = (List<DatosMetaDTO>) qr.query(sql.toString(), rsh, params);
        return requests;
    }
    
    public boolean ActualizaDatosMeta(UserDTO user, int id_region, String anio, String meta_anual, int id_meta) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE formularios.meta");
        sql.append(" SET id_region=?, anio=?, meta_anual=?");
        sql.append(" WHERE id_meta = ?");
        Object[] params = {id_region, anio, meta_anual, id_meta};
        qr.update(sql.toString(), params);
        return true;
    }
    
    public boolean EliminaDatosMeta(UserDTO user, int id_meta) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM formularios.meta");
        sql.append(" WHERE id_meta = ?");
        Object[] params = {id_meta};
        qr.update(sql.toString(), params);
        return true;
    }
    
    public int GetIdMunicipioByFolio(UserDTO user, String folio) throws Exception{
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT modulopredio_municipio as result");
        sql.append(" FROM ").append(SQL.getTableMain(user,String.valueOf(user.getActivity())));; 
        sql.append(" WHERE folio = ?");
        Object[] params = {folio};
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh, params);
        if (result != null) {
            return result.getResult();
        } else {
            return 0;
        }
    }
    
    public int GetIdRegionByFolio(UserDTO user, String folio) throws Exception{
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT region as result");
        sql.append(" FROM ").append(SQL.getTableMain(user,String.valueOf(user.getActivity())));; 
        sql.append(" WHERE folio = ?");
        Object[] params = {folio};
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh, params);
        if (result != null) {
            return result.getResult();
        } else {
            return 0;
        }
    }
    
        public int GetIdLocalidadByFolio(UserDTO user, String folio) throws Exception{
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT modulopredio_localidad as result");
        sql.append(" FROM ").append(SQL.getTableMain(user,String.valueOf(user.getActivity())));; 
        sql.append(" WHERE folio = ?");
        Object[] params = {folio};
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh, params);
        if (result != null) {
            return result.getResult();
        } else {
            return 0;
        }
    }
      public List<CatalogoDTO> getCatalogoInspeccion(UserDTO user, String region, String municipio, String localidad,  String tipoInspeccion) throws Exception 
     {
         ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        if(tipoInspeccion.equals("getIndustrias"))
          {
           DataSource ds = PoolDataSource.getDataSource(user);
           QueryRunner qr = new QueryRunner(ds);
           StringBuilder sql = new StringBuilder();
        
          sql.append(" SELECT case when ID_PADRON_INDUSTRIA is null then NOMBRE  else ID_PADRON_INDUSTRIA end  AS ID,  case when RAZON_SOCIAL  is null then NOMBRE else  RAZON_SOCIAL end   as DESCRIPCION ");
          sql.append(" FROM formularios.padron_industria ");
          sql.append(" WHERE  cve_reg=").append(Integer.parseInt(region)).append(" AND ");
          sql.append(" cve_mpio=").append(Integer.parseInt(municipio));
          //append(" AND ");
          //sql.append(" cve_localidad=").append(localidad);
          List<CatalogoDTO> catalogo = (List<CatalogoDTO>)qr.query(sql.toString(), rsh);
          return catalogo;
          }
        else
           {
            DataSource ds = PoolDataSource.getDataSourceGeneral();
            QueryRunner qr = new QueryRunner(ds);
            StringBuilder sql = new StringBuilder();
            StringBuilder clave = new StringBuilder();
            clave.append("150").append(region);
            if(Integer.parseInt((municipio))<10)
                {
               clave.append("00").append(municipio);
                }
            else
              if(Integer.parseInt((municipio))>=10 && Integer.parseInt((municipio))<100)
                {
                clave.append("0").append(municipio);
                }
            else
                {  
                    clave.append(municipio);
                }
               clave.append("");
            if(Integer.parseInt(localidad)<10)
               {
               clave.append("000").append(localidad);
               }
            else
                if(Integer.parseInt(localidad)>=10 && Integer.parseInt(localidad)<100)
                   {
                   clave.append("00").append(localidad);
                   }
                  else
                    if(Integer.parseInt(localidad)>=100 && Integer.parseInt(localidad)<1000)
                   {
                   clave.append("0").append(localidad);
                   }
                   else
                    if(Integer.parseInt(localidad)>=1000)
                   {
                   clave.append(localidad);
                   }
            
            sql.append(" SELECT  case when FOLIO is null then ' '  else FOLIO END AS ID, CASE WHEN  PREDIO IS NULL THEN ' ' ELSE PREDIO END AS DESCRIPCION ");
            sql.append(" FROM FORMULARIOS.PRINCIPAL ");
            sql.append(" WHERE FOLIO LIKE '%").append(clave.toString()).append("%'");
                    
            
            List<CatalogoDTO> catalogo = (List<CatalogoDTO>)qr.query(sql.toString(), rsh);
       return catalogo;
           }
     }

    public String getIndustria(UserDTO user, String folio) throws SQLException {
         DataSource ds = PoolDataSource.getDataSource(user);
           QueryRunner qr = new QueryRunner(ds);
           StringBuilder sql = new StringBuilder();
        
          sql.append(" SELECT modulopredio_cup as result ");
          sql.append(" FROM formularios.principal ");
          sql.append(" WHERE folio='").append(folio).append("'");
          ResultSetHandler rsh = new BeanHandler(ResultString.class);
          ResultString industria = (ResultString)qr.query(sql.toString(), rsh);
          return industria.getResult();
    
    }
 
    
}
