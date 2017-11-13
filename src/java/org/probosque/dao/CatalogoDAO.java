package org.probosque.dao;

import java.sql.SQLException;
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
import org.probosque.dto.ListDTO;
import org.probosque.dto.UserDTO;

/**
 * Clase que administra en base de datos la información de los catalogos para los diferentes programas
 * @author admin
 */

public class CatalogoDAO {

    public CatalogoDTO getCatalogo(UserDTO user, String tableName, String id) throws SQLException {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler rsh = new BeanHandler(CatalogoDTO.class);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id, descripcion");
        sql.append(" FROM ").append(tableName);
        sql.append(" WHERE id = ?");
        sql.append(" LIMIT 1");
        Object[] params = {Integer.valueOf(id)};
        CatalogoDTO catalogo = (CatalogoDTO) qr.query(sql.toString(), rsh, params);
        return catalogo;
    }

    private List<CatalogoDTO> find(UserDTO user, String tableName, String key) throws SQLException {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id, descripcion");
        sql.append(" FROM ").append(tableName);
        sql.append(" WHERE UPPER(descripcion) LIKE ?");
        sql.append(" ORDER BY descripcion");
        Object[] params = {"'%" + key.toUpperCase() + "%'"};
        List<CatalogoDTO> rows = (List<CatalogoDTO>) qr.query(sql.toString(), rsh, params);
        return rows;
    }

    private List<CatalogoDTO> find(String tableName, String key) throws SQLException {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id, descripcion");
        sql.append(" FROM ").append(tableName);
        sql.append(" WHERE UPPER(descripcion) LIKE '%").append(key.toUpperCase()).append("%'");
        sql.append(" ORDER BY descripcion");
        List<CatalogoDTO> rows = (List<CatalogoDTO>) qr.query(sql.toString(), rsh);
        return rows;
    }

    public String getWhereIN(UserDTO user, String tableName, String field, String key) throws SQLException {
        if (tableName.contains("null")) {
            String[] values = tableName.split(";");
            tableName = values[1];
        }
        List<CatalogoDTO> matches = null;
        if (tableName.contains("catalogos.region") || tableName.contains("catalogos.municipio") || tableName.contains("catalogos.localidad")) {
            matches = find(tableName, key);
        } else {
            matches = find(user, tableName, key);
        }
        if (matches != null && matches.size() > 0) {
            StringBuilder where = new StringBuilder();
            where.append(" WHERE ").append(field).append(" IN (");
            for (CatalogoDTO cat : matches) {
                if (where.toString().equals(" WHERE " + field + " IN (")) {
                    where.append("'").append(cat.getId()).append("'");
                } else {
                    where.append(",").append("'").append(cat.getId()).append("'");
                }
            }
            where.append(")");
            return where.toString();
        }
        return "";
    }

    public ListDTO getList(String listName) {
        ListDTO empty = new ListDTO();
        List<CatalogoDTO> listEmpty = new ArrayList<>();
        empty.setList(listEmpty);
        
        if (listName != null && !listName.isEmpty()) {
            if (listName.indexOf("null")>-1) {
                return empty;
            } else {
                String tableName = listName;
                if (listName.contains(";")) {
                    String[] tables = tableName.split(";");
                    tableName = tables[1];
                }
                try {
                    ListDTO list = new ListDTO();
                    list.setList(this.getListDataModuloPredio(tableName,0));
                    return list;
                } catch (Exception ex) {
                    return empty;
                }
            }
        } else {
            return empty;
        }
    }
    
    public ListDTO getList(UserDTO user, String listName, int id_municipio, int id_localidad){
        ListDTO empty = new ListDTO();
        List<CatalogoDTO> listEmpty = new ArrayList<>();
        empty.setList(listEmpty);
        if (listName != null && !listName.isEmpty()) {
            if (listName.contains("catalogos.cup")) {
                try {
                    ListDTO list = new ListDTO();
                    list.setList(this.getListCup(id_municipio, id_localidad));
                    return list;
                } catch (Exception ex) {
                    Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return empty;
    }
    
    public ListDTO getList(UserDTO user, String listName, int id_municipio) {
        ListDTO empty = new ListDTO();
        List<CatalogoDTO> listEmpty = new ArrayList<>();
        empty.setList(listEmpty);
        if (listName != null && !listName.isEmpty()) {
            
            if (!listName.contains("catalogos.cup")){

                if (listName.contains("null")) {
                    return empty;
                } else {
                    if (/*listName.contains("catalogos.region") || */listName.contains("catalogos.municipio") || listName.contains("catalogos.localidad")) {
                        try {
                            if (listName.contains("catalogos.localidad") && id_municipio != 0) {
                                ListDTO list = new ListDTO();
                                list.setList(this.getListDataModuloPredio(listName, id_municipio));
                                return list;
                            }else  if (listName.contains("catalogos.municipio") && id_municipio != 0) {
                                //Cuando entra a este if el id_municipio se considera como el id_region 
                                //Se toma de esta manera para evitar dañar el proceo 
                                ListDTO list = new ListDTO();
                                list.setList(this.getListDataModuloPredio(listName, id_municipio));
                                return list;
                            }else{
                                ListDTO list = new ListDTO();
                                list.setList(this.getListDataModuloPredio(listName,0));
                                return list;
                            }
                        } catch (Exception ex) {
                            return empty;
                        }
                    } else {
                        String tableName = listName;
                        if (listName.contains(";")) {
                            String[] tables = tableName.split(";");
                            tableName = tables[1];
                        }
                        try {
                            ListDTO list = new ListDTO();
                            list.setList(this.getListData(user, tableName));
                            return list;
                        } catch (Exception ex) {
                            return empty;
                        }
                    }
                }
            }
            return  empty;
        } else {
            return empty;
        }
    }
    
    private List<CatalogoDTO> getListData(UserDTO user, String tableName) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id, descripcion");
        sql.append(" FROM ").append(tableName);
        if(tableName.contains("region")||tableName.contains("mes")){
            sql.append(" ORDER BY id");
        } else {
            sql.append(" ORDER BY descripcion ASC");
        }
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        List<CatalogoDTO> list = (List<CatalogoDTO>) qr.query(sql.toString(), rsh);
        return list;
    }

    private List<CatalogoDTO> getListDataModuloPredio(String tableName, int id_municipio) throws Exception {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id, descripcion");
        sql.append(" FROM ").append(tableName);
        
        
        if (tableName.contains("municipio")) {
            //En caso de entrar aqui se toma id_municipio como el id de la region
            sql.append(" WHERE id_region = ?");
            sql.append(" GROUP BY id, descripcion");
        }
        
        if (tableName.contains("localidad")) {
            sql.append(" WHERE id_municipio = ?");
            sql.append(" GROUP BY id, descripcion");
        }
        
        if(tableName.contains("region")) {
            sql.append(" ORDER BY id");
        } else {
            sql.append(" ORDER BY descripcion");
        }
        
        if (tableName.contains("localidad")) {
            Object[] params = {Integer.valueOf(id_municipio)};
            ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
            List<CatalogoDTO> list = (List<CatalogoDTO>) qr.query(sql.toString(), rsh, params);
            return list;
        }else if(tableName.contains("municipio")){
            Object[] params = {Integer.valueOf(id_municipio)};
            ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
            List<CatalogoDTO> list = (List<CatalogoDTO>) qr.query(sql.toString(), rsh, params);
            return list;
        }else{
            ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
            List<CatalogoDTO> list = (List<CatalogoDTO>) qr.query(sql.toString(), rsh);
            return list;
        }
    }
    
    private List<CatalogoDTO> getListCup(int id_municipio, int id_localidad) throws Exception {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT folio AS id, TRIM(predio) AS descripcion");
        sql.append(" FROM ").append("formularios.principal");
        sql.append(" WHERE modulopredio_municipio = ? AND modulopredio_localidad = ? ");
        sql.append(" ORDER BY descripcion ASC");
        Object[] params = {
                    Integer.valueOf(id_municipio),
                    Integer.valueOf(id_localidad)
        };
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        List<CatalogoDTO> list = (List<CatalogoDTO>) qr.query(sql.toString(), rsh, params);
        return list;
    }
}
