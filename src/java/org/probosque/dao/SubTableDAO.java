package org.probosque.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.probosque.dto.CatalogoDTO;
import org.probosque.dto.ColumnDTO;
import org.probosque.dto.FolioDTO;
import org.probosque.dto.ListDTO;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.TableDTO;
import org.probosque.dto.UserDTO;
import org.probosque.model.PDFcontent;
import org.probosque.model.PDFrow;


/**
 * Clase que administra en base de datos la información de los multiregistros contenidos en algunos formularios dependiendo del programa
 * @author admin
 */

public class SubTableDAO {

    private final String TABLE_INFO = "prog2_info_participantes";
    private final String TABLE_PROGRAM = "prog2_participantes";

    public List<ColumnDTO> getColumns(UserDTO user, String tableName, String folio) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT type, label, name, datatype, listname, max_length, min_length, CAST('' AS TEXT) AS value, editable, privacy");
        sql.append(" FROM ").append(tableName);
        if (user.getRole_id() == 3) {
            sql.append(" WHERE privacy != true");
        }
        sql.append(" ORDER BY position");
        ResultSetHandler rsh = new BeanListHandler(ColumnDTO.class);
        List<ColumnDTO> columns = (List<ColumnDTO>) qr.query(sql.toString(), rsh);
        for (ColumnDTO column : columns) {
            if (column.getName().equals("folio")) {
                column.setValue(folio);
            }
            if (column.getName().equals("consecutivo")) {
                column.setValue(getConsecutivo(user, tableName, folio));
            }//&& !column.getListname().equals("catalogos.vacio")
            if (column.getListname() != null && !column.getListname().isEmpty() ) {
                if (("s400_numero_sitio").equalsIgnoreCase(column.toString()) && user.getProgram() == 12) {
                    ListDTO list = new ListDTO();
                    list.setList(this.getListSitios(user, folio));
                    column.setList(list);
                }else if(("numero_arbol").equalsIgnoreCase(column.toString()) && user.getProgram() == 12){
                    ListDTO list = new ListDTO();
                    list.setList(this.getListArboles(user, folio));
                    column.setList(list);
                }else if(("area_corta").equalsIgnoreCase(column.toString()) && user.getProgram() == 1){
                    ListDTO list = new ListDTO();
                    list.setList(this.getListAreasCortaEdit(user));
                    column.setList(list);
                }else{
                    ListDTO list = new ListDTO();
                    list.setList(this.getList(user, column.getListname()));
                    column.setList(list);
                }
            }
        }
        return columns;
    }

    public String getConsecutivo(UserDTO user, String tableName, String folio) throws Exception {

        tableName = tableName.replace("_info", "");

        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT max(CAST(consecutivo AS integer)) AS result");
        sql.append(" FROM ").append(tableName);
        sql.append(" WHERE folio = ?");
        Object[] params = {folio};
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh, params);
        if (result != null) {
            result.setResult(result.getResult() + 1);
            if (result.getResult() < 10) {
                return "00" + result.getResult();
            }
            if (result.getResult() < 100) {
                return "0" + result.getResult();
            } else {
                return "" + result.getResult();
            }
        } else {
            return "001";
        }
    }

    public List<ColumnDTO> getColumnsSearchable(UserDTO user, int roleId) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT type, label, name, datatype, listname, max_length, min_length, CAST('valor' AS TEXT) AS value, editable, privacy");
        sql.append(" FROM ").append(TABLE_INFO);
        sql.append(" WHERE searchable = true");
        if (roleId == 3) {
            sql.append(" AND privacy != true");
        }
        sql.append(" ORDER BY position");
        ResultSetHandler rsh = new BeanListHandler(ColumnDTO.class);
        List<ColumnDTO> columns = (List<ColumnDTO>) qr.query(sql.toString(), rsh);
        for (ColumnDTO column : columns) {
            if (column.getListname() != null && !column.getListname().isEmpty()) {
                ListDTO list = new ListDTO();
                list.setList(this.getList(user, column.getListname()));
                column.setList(list);
            }
        }
        return columns;
    }

    public List<CatalogoDTO> getList(UserDTO user, String table_name) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id, descripcion");
        sql.append(" FROM ").append(table_name);
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        List<CatalogoDTO> list = (List<CatalogoDTO>) qr.query(sql.toString(), rsh);
        return list;
    }
    
    public List<CatalogoDTO> getListSitios(UserDTO user, String folio) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT sitio AS id, sitio AS descripcion");
        sql.append(" FROM formularios.sitios");
        sql.append(" WHERE folio = ?");
        sql.append(" GROUP BY sitio ORDER BY sitio");
        Object[] params = {folio};
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        List<CatalogoDTO> list = (List<CatalogoDTO>) qr.query(sql.toString(), rsh, params);
        return list;
    }
    
    public List<CatalogoDTO> getListArboles(UserDTO user, String folio) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CONCAT(sitio,'-',num_arbol) as id, num_arbol as descripcion");
        sql.append(" FROM formularios.sitios");
        sql.append(" WHERE folio = ?");
        sql.append(" ORDER BY sitio");
        Object[] params = {folio};
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        List<CatalogoDTO> list = (List<CatalogoDTO>) qr.query(sql.toString(), rsh, params);
        return list;
    }
    
    public String getVolumen(UserDTO user, String folio, String sitio, String arbol) throws Exception {
        String result = "";
        DataSource ds = PoolDataSource.getDataSource(user);
        Connection con  = ds.getConnection();
        Statement sta = con.createStatement();
       
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT cast(volumen as text) as result");
        sql.append(" FROM formularios.sitios");
        sql.append(" WHERE folio ='").append(folio).append("'");
        sql.append(" AND sitio ='").append(sitio).append("'");
        sql.append(" AND num_arbol='").append(arbol).append("'");
        //Object[] params = {folio, sitio, arbol};
        ResultSet rs = sta.executeQuery(sql.toString());
        
        while(rs.next()){
            result= rs.getString("result");
        }
        rs.close();
        sta.close();
        con.close();
        return result;
    }
    
    public String getAnioAreaCorta(UserDTO user, String folio, String areaCorta) throws Exception {
        String result = "";
        DataSource ds = PoolDataSource.getDataSource(user);
        Connection con  = ds.getConnection();
        Statement sta = con.createStatement();        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT anio as result");
        sql.append(" FROM formularios.areascorta");
        sql.append(" WHERE folio ='").append(folio).append("'");
        sql.append(" AND area_cortaa ='").append(areaCorta).append("'");
        //Object[] params = {folio, sitio, arbol};
        ResultSet rs = sta.executeQuery(sql.toString());
        
        while(rs.next()){
            result= rs.getString("result");
        }
        rs.close();
        sta.close();
        con.close();
        return result;
    }
    
    public List<CatalogoDTO> getListAreasCorta(UserDTO user, String folio) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT area_cortaa AS id,");
        sql.append(" CASE");
        sql.append(" WHEN area_cortaa = '1' THEN 'I'");
        sql.append(" WHEN area_cortaa = '2' THEN 'II'");
        sql.append(" WHEN area_cortaa = '3' THEN 'III'");
        sql.append(" WHEN area_cortaa = '4' THEN 'IV'");
        sql.append(" WHEN area_cortaa = '5' THEN 'V'");
        sql.append(" WHEN area_cortaa = '6' THEN 'VI'");
        sql.append(" WHEN area_cortaa = '7' THEN 'VII'");
        sql.append(" WHEN area_cortaa = '8' THEN 'VIII'");
        sql.append(" WHEN area_cortaa = '9' THEN 'IX'");
        sql.append(" WHEN area_cortaa = '10' THEN 'X'");
	sql.append(" WHEN area_cortaa = '11' THEN 'CONSERVACIÓN'"); 
	sql.append(" WHEN area_cortaa = '12' THEN 'RESTAURACIÓN'");
	sql.append(" ELSE 'OTROS USOS'");
        sql.append(" END AS descripcion");
        sql.append(" FROM formularios.areascorta");
        sql.append(" where folio = ? ");
        sql.append("order by area_cortaa::int");
        Object[] params = {folio};
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        List<CatalogoDTO> list = (List<CatalogoDTO>) qr.query(sql.toString(), rsh, params);
        return list;
    }
   public List<CatalogoDTO> getListAreasCortaEdit(UserDTO user ) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT catalogos.areacorta.id ,catalogos.areacorta.descripcion AS descripcion");
        sql.append(" FROM  catalogos.areacorta");
                
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        List<CatalogoDTO> list = (List<CatalogoDTO>) qr.query(sql.toString(), rsh);
        return list;
    }
     
    public List<CatalogoDTO> getListMunicipios(UserDTO user, int id_estado) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT mun.id, mun.descripcion");
        sql.append(" FROM catalogos.municipios AS mun");
        sql.append(" INNER JOIN catalogos.estados AS est");
        sql.append(" ON est.id = mun.id_estado");
        sql.append(" WHERE est.id = ?");
        sql.append(" ORDER BY mun.descripcion asc");
        Object[] params = {id_estado};
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        List<CatalogoDTO> list = (List<CatalogoDTO>) qr.query(sql.toString(), rsh, params);
        return list;
    }
    
    public TableDTO setValues(UserDTO user, TableDTO table) {

        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ").append(table.getSQLSelect());
        sql.append(" FROM ").append(TABLE_PROGRAM);

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
            } else {
                return table;
            }

        } catch (SQLException e) {
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
    
    public TableDTO get(UserDTO user, TableDTO table, String folio) {
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ").append(table.getSQLSelect());
        sql.append(" FROM ").append(TABLE_PROGRAM);
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
            con.close();
        } catch (SQLException e) {
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

    public TableDTO setValues(UserDTO user, TableDTO table, String folio) {
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ").append(table.getSQLSelect());
        sql.append(" FROM ").append(TABLE_PROGRAM);
        sql.append(" WHERE folio = ").append(folio);

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
            } else {
                return table;
            }
         
        } catch (SQLException e) {
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

    public void insertTable(UserDTO user, TableDTO table, String tableName) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO ").append(tableName);
        sql.append(" (").append(table.getSqlEditColumns()).append(")");//username, password, firstname, lastname, address, phone, email, curp, rfc, role_id, program, privacy)");
        sql.append(" VALUES (").append(table.getSQLParams()).append(")"); //?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        qr.update(sql.toString(), table.getParams());
        //  Fuciona para el registro de insercion de datos 
        LogDAO log=new LogDAO();
                String lusr=user.getId()+"-"+user.getFirstname()+" "+user.getLastname()+"-"+user.getProgram();
                log.log(lusr,1,"Se registra datos en el Multiregistro "+getMultiregistro(user,tableName)+" con Folio "+table.getFolio());
    }
    
    /*
    * Actualiza el numero de personas cuando se:
    * insertar un multiregitro de participantes 
    */
    public void updateTotalPerson(UserDTO user, TableDTO table, String folio) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        ResultSetHandler rsh = new MapHandler();
        String tipo = "";
        
        /*
        * Recupera el total 
        */
        for (ColumnDTO columna : table.getColumns()){
            if(columna.getName().equals("genero")){
                // El numero 1 es el valor del combo en el formulario
                tipo = columna.getValue().equals("1")? "total_hombres" : "total_mujeres";
                break;
            }
        }
        sql.append( " SELECT CASE WHEN ").append( tipo ).append (" IS NULL THEN 0 ELSE ").append( tipo )
                    .append(" END FROM FORMULARIOS.PRINCIPAL WHERE FOLIO = '" ).append(folio).append("'");
        Map clientMap = (Map) qr.query(sql.toString(), rsh);
        int cantidad = Integer.parseInt(clientMap.get(tipo).toString());
       
           cantidad++;
        
       /*
        * Actualiza dato 
        
        */
        sql = new StringBuilder();
        sql.append(" UPDATE FORMULARIOS.PRINCIPAL SET ").append(tipo).append(" = ").append(cantidad)
                .append(" WHERE FOLIO = '").append( folio ).append("'");
        qr.update(sql.toString());
       
    }
    
    /*
    * Decrementa el total de personas cuando se elimina un multiregistro
    * de participantes
    */
    public void updateTotalPerson(UserDTO user, String consecutivo ,String folio)throws Exception{
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        ResultSetHandler rsh = new MapHandler();
        String tipo = "";
        
        //Identifica que se elimina hombre o mujer
        sql.append( " SELECT genero ").append (" FROM FORMULARIOS.PARTICIPANTES ")
                .append ("WHERE FOLIO = '" ).append(folio).append("' AND ")
                .append( "CONSECUTIVO = ").append( consecutivo );
        Map clientMap = (Map) qr.query(sql.toString(), rsh);
        
        tipo = clientMap.get("genero").toString().equals("1")? "total_hombres" : "total_mujeres";
        
        //Consulta cuantos hay dependiendo el tipo
        sql = new StringBuilder();
        sql.append( " SELECT CASE WHEN ").append( tipo ).append( " IS NULL THEN 0 ELSE ").append( tipo )
                  .append (" END FROM FORMULARIOS.PRINCIPAL WHERE FOLIO = '" ).append(folio).append("'");
        Map client = (Map) qr.query(sql.toString(), rsh);
        int cantidad = Integer.parseInt(client.get(tipo).toString());
       
           cantidad--;
           
        //Actualiza el dato 
        sql = new StringBuilder();
        sql.append(" UPDATE FORMULARIOS.PRINCIPAL SET ").append(tipo).append(" = ").append(cantidad)
                .append(" WHERE FOLIO = '").append( folio ).append("'");
        qr.update(sql.toString());
    
    }
    
    
    public void editTable(UserDTO user, TableDTO table, String tableName, String folio, String consecutivo) throws Exception {
        ArrayList<String> dataOld= queryList(user, String.valueOf(user.getActivity()), table, tableName,consecutivo); 
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE ").append(tableName);
        sql.append(" SET ").append(table.getSQLEditParams());//username, password, firstname, lastname, address, phone, email, curp, rfc, role_id, program, privacy)");
        sql.append(" WHERE folio=? AND consecutivo = '").append(consecutivo).append("'");
        Object[] params = {folio, consecutivo};
        table.getEditParams();
        qr.update(sql.toString(), table.getEditParams());
        //Codigo para Registro de evento de edicion de consultas 
        ArrayList<String> dataUpdate= queryList(user, String.valueOf(user.getActivity()), table,tableName,consecutivo);
        ArrayList<String> NameField = ColumnName(user, String.valueOf(user.getActivity()), table,tableName, consecutivo);
        String changes = fieldsUpdate(dataOld, dataUpdate,NameField,user,String.valueOf(user.getActivity()),table,tableName,consecutivo);
        LogDAO log=new LogDAO();
                String lusr=user.getId()+"-"+user.getFirstname()+" "+user.getLastname()+"-"+user.getProgram();
                log.log(lusr,2,"Modificacion de datos en el Multiregistro "+getMultiregistro(user,tableName)+ " con Folio "+table.getFolio()+"\n"+changes);
  
    }

    public void deleteTable(UserDTO user, String tableName, String folio, String consecutivo) throws SQLException, Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM ").append(tableName);
        sql.append(" WHERE folio = ? AND consecutivo = ?");
        Object[] params = {
            folio, Integer.parseInt(consecutivo)
        };
        qr.update(sql.toString(), params);
        
        
        
          LogDAO log=new LogDAO();
                String lusr=user.getId()+"-"+user.getFirstname()+" "+user.getLastname()+"-"+user.getProgram();
                log.log(lusr,1,"Se eliminan datos en el Multiregistro "+getMultiregistro(user,tableName)+" con Folio "+folio+" el consecutivo es"+consecutivo);
        
    }
 /*Este Codigo funciona para obtener catalogos, campos y nombres de columnas todo para el correcto funcionamiento de la bitacor 
    Autor Ivan Tadeo
    colaborador  Mike MArteinez 
    
    */
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 
public String getMultiregistro(UserDTO user,String tableName) throws SQLException
     {
       String nombreMultiregistro = null;   
       DataSource ds = PoolDataSource.getDataSource(user);
       Connection con =ds.getConnection();
       Statement sta = con.createStatement();
       StringBuilder sql = new StringBuilder();
       sql.append("Select label FROM ").append(SQL.getTableInfo(user, String.valueOf(user.getActivity())));
       sql.append(" WHERE name ='").append(tableName).append("'");
       ResultSet rs = sta.executeQuery(sql.toString());
       while (rs.next()) {
               nombreMultiregistro=rs.getString("label");
                                
            }
       if (nombreMultiregistro==null)
           nombreMultiregistro=" ";
      
       rs.close();
       sta.close();
       con.close();
        
       
       return nombreMultiregistro;
     }      
    public ArrayList<String> queryList(UserDTO user, String activity, TableDTO table,String tableName,String consecutivo) throws SQLException
    {
        ArrayList <String> data = new ArrayList<String>();
        Connection connection = null;
        DataSource ds = PoolDataSource.getDataSource(user);
        connection = ds.getConnection();
        Statement sta1 = connection.createStatement();
        Statement sta = connection.createStatement();
 
        String query1 = "SELECT * FROM "+tableName+" where folio='"+table.getFolio()+"' AND consecutivo='"+consecutivo+"'";
        ResultSet rs1 = sta1.executeQuery(query1);
        
        String TableName = tableName.substring(12); 
        String query = "SELECT count(*) FROM  information_schema.columns where  table_name='"+TableName+"' and table_schema='formularios'";
        ResultSet rs = sta.executeQuery(query);
        String rrr=" ";
        while(rs.next())
        {
           int columns = rs.getInt(1);
           
            while (rs1.next()) {
                    for (int i = 1; i <= columns; i++) 
                    {
                        rrr=String.valueOf(rs1.getObject(i));
                        data.add(""+rs1.getObject(i));     
                    }
                
            }

       }      
        rs.close();
        sta.close();
        rs1.close();
        sta1.close();
        connection.close();
        return data;
    }
    
    public String fieldsUpdate(ArrayList<String> old,ArrayList<String> update,ArrayList<String> nameField,UserDTO user,String activity, TableDTO table,String tableName,String consecutivo)throws SQLException
    {
        String cambios="";
        for(int x=0;x<old.size();x++) {
            
            if(!old.get(x).equals(update.get(x)))
            { 
             //Comprobar si el nameField.get(x) tiene asociado un catalogo
             //Si tiene asociado un catalogo entonces pasar el id.
             //En caso contrario hacer lo de la linea anterior
             String LabelName=nameField.get(x);
             String catalogo=tieneCatalogo(nameField.get(x), user, activity,tableName, consecutivo);
             if(catalogo!="")
             {
                //Consultar si existe Catalogo
                if(existeCatalogo(catalogo,user, activity, table,tableName, consecutivo))
                {
                    String Dataold = data_Catalog(catalogo, old.get(x), user, activity, table,tableName, consecutivo);
                    String Dataupdate = data_Catalog(catalogo, update.get(x), user, activity, table,tableName, consecutivo);
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
    
    public ArrayList<String> ColumnName(UserDTO user, String activity, TableDTO table,String tableName,String consecutivo)throws SQLException
    {
        ArrayList<String> ListName = new ArrayList<>();
        Connection connection = null;
        DataSource ds = PoolDataSource.getDataSource(user);
        connection = ds.getConnection();
        Statement sta = connection.createStatement();
        Statement sta1 = connection.createStatement();
        String TableName = tableName.substring(12);
        
        String query = "select column_name from information_schema.columns where table_name='"+TableName+"'";
        ResultSet rs1 = sta.executeQuery(query);
        
        while(rs1.next())
        {
         String query1 = "select label from "+tableName+"_info where name='"+rs1.getString(1)+"'";
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
    
    public String tieneCatalogo(String name, UserDTO user, String activity,String tableName,String consecutivo)throws SQLException
    {
     String catalogName ="";
     Connection connection;
     DataSource ds1 = PoolDataSource.getDataSource(user);
     connection = ds1.getConnection();
     Statement sta = connection.createStatement();
     String query ="select listname from "+tableName+"_info where label='"+name+"'";
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
    
    public String data_Catalog(String catalogo,String id, UserDTO user, String activity, TableDTO table,String tableName,String consecutivo)throws SQLException
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
    
    public boolean existeCatalogo(String nameCatalogo,UserDTO user,String activity, TableDTO table,String tableName,String consecutivo)throws SQLException
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
    
    
    
    

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  
    
    
    public ArrayList<TableDTO> getTables(ArrayList<ColumnDTO> formulario, String tableName, String folio, UserDTO user) {
        final ArrayList<TableDTO> tables = new ArrayList<>();
        TableDTO table = new TableDTO();
        table.setColumns(formulario);
        
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ").append(table.getSQLSelect());
        sql.append(" FROM ").append(tableName);
        sql.append(" WHERE folio = '").append(folio).append("'");
        //Victor Porcayo Altamirano
        if(tableName.equals("formularios.rodal_especie")){
           // sql.append("ORDER BY dependencia");
           sql.append(" order by cast(area_corta as integer) asc, cast(consecutivo as integer) asc");
        }
        else
            if(tableName.equals("formularios.produccion"))
            {
            sql.append(" ORDER BY cast(area_corta as integer) asc,cast( anio as integer) asc, cast(consecutivo as integer) asc "); 
                 
            }
        
        else{
            sql.append(" ORDER BY cast(consecutivo as integer) asc ");
        }
        //Victor Porcayo Altamirano
        
        try {
            con = PoolDataSource.getDataSource(user).getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql.toString());
            int contador =0;
            while (rs.next()) {

                if(contador == 0){
                SubTableDAO dao = new SubTableDAO();
                formulario =  (ArrayList<ColumnDTO>) dao.getColumns(user, tableName + "_info", folio);
                contador++;
                }
             
                ArrayList<ColumnDTO> copy = new ArrayList<ColumnDTO>(formulario.size());
                
                try {
                    for(ColumnDTO item : formulario){
                           copy.add(item.clone());
                    }
                } catch (Exception e) {
                    System.out.println("Cloning is not supported by ArrayList element");
                    e.printStackTrace();
                }
                
                TableDTO newTable = new TableDTO();
                newTable.setColumns(copy);
                //String numeroDeSitio guarda el numero de sitio de cada multiregistro
                String numeroDeSitio="";
                
                for (int i = 0; i < newTable.getColumns().size(); i++) {
                   if(newTable.getColumns().get(i).getListname().equals("catalogos.vacio"))
                        {
                           newTable.getColumns().get(i).setDatatype("string");
                           
                          
                        }
                    switch (newTable.getColumns().get(i).getDatatype()) {
                        
                        case "string": case "alphanumeric": case "time":
                        case "image":
                           String value = rs.getString(table.getColumns().get(i).getName());
                           if(newTable.getColumns().get(i).getName().equals("numero_arbol")){
                               value = numeroDeSitio+"-"+value;
                           }
                           
                           newTable.getColumns().get(i).setValue(value != null ? value : "");
                           if(newTable.getColumns().get(i).getListname().equals("catalogos.vacio"))
                              {
                               numeroDeSitio =(newTable.getColumns().get(i).getName().equals("s400_numero_sitio"))?value:"";
                               newTable.getColumns().get(i).setDatatype("list");
                              }
                            
                            //newTable.getColumns().get(i).setValue(rs.getString(table.getColumns().get(i).getName()));
                            break;
                        case "real":
                            newTable.getColumns().get(i).setValue(rs.getDouble(table.getColumns().get(i).getName()));
                            break;
                        case "numeric":
                            newTable.getColumns().get(i).setValue(rs.getDouble(table.getColumns().get(i).getName()));
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
                tables.add(newTable);
            }
            
        } catch (SQLException e) {
            Logger.getLogger(SubTableDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        catch(Exception ex )
         {
          ex.getMessage();
         }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
            return tables;
        }
    }

    public PDFcontent getPDF(UserDTO user, String tableName, String folio) throws Exception {
        ArrayList<PDFrow> pdfRows = new ArrayList<>();
        ArrayList<ColumnDTO> pdfColumns = (ArrayList<ColumnDTO>) getColumns(user, tableName + "_info", folio);
        
        TableDTO subtable = new TableDTO();
        subtable.setColumns(pdfColumns);
        
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ").append(subtable.getSQLSelect());
        sql.append(" FROM ").append(tableName);
        sql.append(" WHERE folio = '").append(folio).append("'");
        sql.append(" ORDER BY consecutivo");

        try {
            con = PoolDataSource.getDataSource(user).getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql.toString());

            while (rs.next()) {
                PDFrow pdfRow = new PDFrow();

                for (int i = 0; i < subtable.getColumns().size(); i++) {
                    String value = "";
                    switch (subtable.getColumns().get(i).getDatatype()) {
                        case "string":case "alphanumeric":case "time":
                            value = rs.getString(subtable.getColumns().get(i).getName());
                            value = value != null ? value : "";
                            break;
                        case "real":
                            value = String.valueOf(rs.getDouble(subtable.getColumns().get(i).getName()));
                            break;
                        case "numeric":
                            value = String.valueOf(rs.getInt(subtable.getColumns().get(i).getName()));
                            break;
                        case "date":
                            value = rs.getString(subtable.getColumns().get(i).getName());
                            value = value != null ? value : "";
                            break;
                        case "list":
                            value = rs.getString(subtable.getColumns().get(i).getName());
                            value = value != null ? value : "";
                            break;
                            /*
                        case "records":
                            value = "";
                            break;*/
                    }
                    pdfRow.addValue(value);
                }
                pdfRows.add(pdfRow);
            }
            
        } catch (SQLException e) {
            Logger.getLogger(SubTableDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                //catch
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                //catch
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                //catch
            }
            PDFcontent pdfContent = new PDFcontent(pdfColumns, pdfRows);
            return pdfContent;
        }
    }
    
    public String getFoliosForWhere(UserDTO user, String table_name, String where) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT folio");
        sql.append(" FROM ").append(table_name);
        //WHERE 
        sql.append(where);
        ResultSetHandler rsh = new BeanListHandler(FolioDTO.class);
        List<FolioDTO> folios = (List<FolioDTO>) qr.query(sql.toString(), rsh);
        if(folios!=null){
            StringBuilder foliosIn = new StringBuilder();
            boolean hasRecords = false;
            for(FolioDTO folio : folios){
                if(hasRecords){
                    foliosIn.append(",").append("'").append(folio.getFolio()).append("'");
                }
                else{
                    foliosIn.append("'").append(folio.getFolio()).append("'");
                    hasRecords = true;
                }
            }
            if(folios.size()>0){
                return " WHERE folio IN ("+foliosIn.toString()+")";
            }
            else{
                return " WHERE folio IN('empty')";
            }
        }
        else{
            return " WHERE folio IN('empty')";
        }
    }
    
    //Victor Porcayo Altamirano
    public String validarExistencia(UserDTO user, String table, String dato) {
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        StringBuilder sql = new StringBuilder();
        String filtro="";
        String datos="";
        if(table.equals("formularios.infractores")){
            filtro="nombre";
        }else{
            filtro="identificador_bien";
        }        
        sql.append(" SELECT * ");
        sql.append(" FROM programa8.").append(table);
        sql.append(" WHERE ").append(filtro);
        sql.append(" = ").append("'").append(dato).append("'");
        try {
            con = PoolDataSource.getDataSource(user).getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql.toString());
            if(table.equals("formularios.infractores")){
                while (rs.next()) {
                    datos+=rs.getString("folio")+" "+rs.getString("nombre");
                }
            }else{
                while (rs.next()) {
                    datos+=rs.getString("folio")+" ";
                }                
            }            
        } catch (SQLException e) {
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
            
        }
        return datos;
    }
    //Victor Porcayo Altamirano
    public List<CatalogoDTO> getSubCategoria(UserDTO user, int idCategoria) throws SQLException
   {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM   catalogos.tipoproducto ");
        if(idCategoria==1)
            {
            sql.append("  where cast(id as integer)>=43 and cast(id as integer)<=56 ");
            }
        else
            if(idCategoria==2)
             {
             sql.append("  where cast(id as integer)>=1 and cast(id as integer)<=20 ");
             }
            else 
               if(idCategoria==3)
                {
            sql.append("  where cast(id as integer)>=21 and cast(id as integer)<=42 ");
                }
        sql.append(" order  by  cast(id as integer) asc ");
        
        
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class); 
        
        List<CatalogoDTO> dto = (List<CatalogoDTO>) qr.query(sql.toString(), rsh);
        return dto;
   }  
    
    public Boolean editNoConglomeradoSitios(UserDTO user, String noConglomerado, String folio) throws SQLException, Exception{
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        
        sql.append("update formularios.sitios set conglomerado = ?");
        sql.append("where folio = ?");
        
        Object[] params = {
            noConglomerado, folio
        };
        
        qr.update(sql.toString(), params);
        
        return true;
    }
    
    public Boolean editNoConglomerado(UserDTO user, String noConglomerado, String folio) throws SQLException, Exception{
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        
        sql.append("update formularios.s400 set num_conglomerado = ?");
        sql.append("where folio = ?");
        
        Object[] params = {
            noConglomerado, folio
        };
        
        qr.update(sql.toString(), params);
        
        return true;
    }
   
    
    public Integer updateCountS400(UserDTO user, String folio)throws SQLException, Exception{
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        ResultSetHandler rsh = new MapHandler();

        sql.append( " SELECT COUNT(*) AS TOTAL_MULTIREGISTROS ").append (" FROM FORMULARIOS.S400 ")
           .append ("WHERE FOLIO = '" ).append(folio).append("' ");
        Map clientMap = (Map) qr.query(sql.toString(), rsh);
        
        int cantidad = Integer.parseInt(clientMap.get("TOTAL_MULTIREGISTROS").toString());
       
        return cantidad;
           
    }
}