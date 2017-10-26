package org.probosque.dao;

import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.CatalogoDTO;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.TableDTO;
import org.probosque.dto.UserDTO;
import org.probosque.dto.UserLoginDTO;

/**
 * Clase que administra en base de datos la información de los usuarios para todos los programas
 * @author admin
 */

public class UserDAO {

    private final String TABLE_USER = "\"user\"";

    public UserLoginDTO getUserLogin(String user, String password) throws Exception {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id, username, firstname, lastname, role_id, program, activity");
        sql.append(" FROM ").append(TABLE_USER);
        sql.append(" WHERE username = ? AND password = ?");
        Object[] params = {
            user, password
        };
        ResultSetHandler rsh = new BeanHandler(UserLoginDTO.class);
        UserLoginDTO userInfo = (UserLoginDTO) qr.query(sql.toString(), rsh, params);
     
        try{
            userInfo.setProgramname(getProgram(userInfo).getDescripcion());
        }
        catch(Exception error){
            
        }
        return userInfo;
    }
    
    public CatalogoDTO getProgram(UserLoginDTO user) throws Exception {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();    
        sql.append(" SELECT id, descripcion");
        sql.append(" FROM ").append(SQL.TABLE_PROGRAMAS);
        sql.append(" WHERE id = ? AND activity = ?");
        Object[] params = {
            user.getProgram(), user.getActivity()
        };
        ResultSetHandler rsh = new BeanHandler(CatalogoDTO.class);
        CatalogoDTO program = (CatalogoDTO) qr.query(sql.toString(), rsh, params);
        return program;
    } 

    public UserDTO getUser(int id) throws Exception {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id, username, password, firstname, lastname, address, phone, email, curp, rfc, role_id, program, activity");
        sql.append(" FROM ").append(TABLE_USER);
        sql.append(" WHERE id = ?");
        Object[] params = {
            id
        };
        ResultSetHandler rsh = new BeanHandler(UserDTO.class);
        UserDTO user = (UserDTO) qr.query(sql.toString(), rsh, params);
        
        return user;
    }

    public List<UserDTO> getUsers() throws Exception {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id, username, firstname, lastname, address, phone, email, curp, rfc, role_id, program, activity");
        sql.append(" FROM ").append(TABLE_USER);
        sql.append(" WHERE id != 1");
        ResultSetHandler rsh = new BeanListHandler(UserDTO.class);
        List<UserDTO> users = (List<UserDTO>) qr.query(sql.toString(), rsh);
        return users;
    }

    public UserDTO getUser(String username) throws Exception {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id, username, firstname, lastname, address, phone, email, curp, rfc, role_id, program, activity");
        sql.append(" FROM ").append(TABLE_USER);
        sql.append(" WHERE username = ?");
        Object[] params = {
            username
        };
        ResultSetHandler rsh = new BeanHandler(UserDTO.class);
        UserDTO user = (UserDTO) qr.query(sql.toString(), rsh, params);
        return user;
    }

  public void insertUser(UserDTO user) throws Exception {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        StringBuilder MaxId = new StringBuilder();
       if(user.getProgram()==14)
           {
           user.setProgram(13);
           user.setRole_id(5);
           }else if (user.getProgram()==1 && user.getRole_id()==2)
                {
                   user.setRole_id(5);
                }
        MaxId.append("Select MAX(id)+1 as result from ").append(TABLE_USER);
        ResultSetHandler rsh= new BeanHandler(ResultInteger.class);
        ResultInteger IdUser = (ResultInteger)qr.query(MaxId.toString(), rsh);
        sql.append(" INSERT INTO ").append(TABLE_USER);
        sql.append(" (id,username, password, firstname, lastname, address, phone, email, curp, rfc, role_id, program,activity)");
        sql.append(" VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        Object[] params = {
            IdUser.getResult(),
            user.getUsername(),
            user.getPassword(),
            user.getFirstname(),
            user.getLastname(),
            user.getAddress(),
            user.getPhone(),
            user.getEmail(),
            user.getCurp(),
            user.getRfc(),
            user.getRole_id(),
            user.getProgram(),
            user.getActivity()
        };
        qr.update(sql.toString(), params);
    }

    public void updateUser(UserDTO user) throws Exception {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        
        if(user.getProgram()==14)
           {
           user.setProgram(13);
           user.setActivity(5);
           }
        sql.append(" UPDATE ").append(TABLE_USER);
        sql.append(" SET username=?,password=?,firstname=?,lastname=?,address=?,phone=?,email=?,curp=?,rfc=?,role_id=?,program=?,activity=?");
        sql.append(" WHERE id = ?");
        Object[] params = {
            user.getUsername(),
            user.getPassword(),
            user.getFirstname(),
            user.getLastname(),
            user.getAddress(),
            user.getPhone(),
            user.getEmail(),
            user.getCurp(),
            user.getRfc(),
            user.getRole_id(),
            user.getProgram(),
            user.getActivity(),
            user.getId(),
        };
        qr.update(sql.toString(), params);
    }

    public void deleteUser(int id) throws Exception {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM ").append(TABLE_USER);
        sql.append(" WHERE id = ?");
        Object[] params = {
            id
        };
        qr.update(sql.toString(), params);
    }

    public List<UserDTO> findUsers(String key) throws Exception {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id, username, firstname, lastname, address, phone, email, curp, rfc, role_id, program, activity");
        sql.append(" FROM ").append(TABLE_USER);
        sql.append(" WHERE UPPER(username || firstname || lastname) LIKE UPPER(?) AND id != 1");
        ResultSetHandler rsh = new BeanListHandler(UserDTO.class);
        Object[] params = {
            "%" + key + "%"
        };
        List<UserDTO> users = (List<UserDTO>) qr.query(sql.toString(), rsh, params);
        return users;
    }

    public void insertUser(TableDTO table) throws Exception {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO ").append(TABLE_USER);
        sql.append(" (").append(table.getSqlEditColumns()).append(")");
        sql.append(" VALUES (").append(table.getSQLParams()).append(")");
        qr.update(sql.toString(), table.getParams());
    }

}
