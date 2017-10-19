package org.probosque.dao;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.probosque.dto.UserDTO;


/**
 * Clase que administra las conexiones a la base de datos de acuerdo al programa proporcionado
 * @author admin
 */
public class PoolDataSource {

    private static DataSource[] dataSource = new DataSource[16];
    public static final String[] userAndDatabase = {
        "programageneral",
        "programa1",
        "programa2",
        "programa3",
        "programa4",
        "programa5",
        "programa6",
        "programa7",
        "programa8",
        "programa9",
        "programa10",
        "programa11",
        "programa12",
        "programa13",
        "",
        "programa16"};

    public static final String[] passDatabase = {
        "programageneral_WUVJO73748",
        "programa1_Ak457fgyt",
        "programa2_hjtuGijry",
        "programa3_nmIPOf546",
        "programa4_Ak457fgyt",
        "programa5_23BZPlqwd",
        "programa6_123Mfjuas",
        "programa7_MNyyHSJSU",
        "programa8_ZXCVMNQ12",
        "programa9_QWASD4737",
        "programa10_8978Mqdk2",
        "programa11_QP536jjsa",
        "programa12_Ak15sfnmt",
        "programa13_VE74nilla",
        "",
        "programa16"
        };

    public static DataSource getDataSource(UserDTO user) {
        if (user.getRole_id() == 1) {
            BasicDataSource bds = new BasicDataSource();
            bds.setDriverClassName("org.postgresql.Driver");
            bds.setUrl("jdbc:postgresql://localhost:5432/" + userAndDatabase[user.getProgram()]);
            bds.setUsername(userAndDatabase[0]);
            bds.setPassword(passDatabase[0]);
            bds.setPoolPreparedStatements(true);
            bds.setMinIdle(5);
            bds.setMaxIdle(10);
            bds.setMaxOpenPreparedStatements(5);
            dataSource[user.getProgram()] = bds;
        } else {
            if (dataSource[user.getProgram()] == null) {
                BasicDataSource bds = new BasicDataSource();
                bds.setDriverClassName("org.postgresql.Driver");
                bds.setUrl("jdbc:postgresql://localhost:5432/" + userAndDatabase[user.getProgram()]);
                    bds.setUsername(userAndDatabase[user.getProgram()]);
                bds.setPassword(passDatabase[user.getProgram()]);
                bds.setPoolPreparedStatements(true);
                bds.setMinIdle(5);
                bds.setMaxIdle(10);
                bds.setMaxOpenPreparedStatements(5);
                dataSource[user.getProgram()] = bds;
            }
        }
        return dataSource[user.getProgram()];
    }
    
     /*E Zamora DataSource Log */
    public static DataSource getLogDataSource(){
         if (dataSource[0] == null){
     BasicDataSource bds = new BasicDataSource();
     bds.setDriverClassName("org.postgresql.Driver");
     bds.setUrl("jdbc:postgresql://localhost:5432/programageneral");
     bds.setUsername("postgres");
     bds.setPassword("postgres");
     bds.setPoolPreparedStatements(true);
     bds.setMinIdle(5);
     bds.setMaxIdle(10);
     bds.setMaxOpenPreparedStatements(5);
           dataSource[0] = bds; 
         }
         return dataSource[0];
    }
    /*E Zamora DataSource Log */
    
    protected static  DataSource getDataSource(boolean value) {
        if (dataSource[0] == null) {
            BasicDataSource bds = new BasicDataSource();
            bds.setDriverClassName("org.postgresql.Driver");
            bds.setUrl("jdbc:postgresql://localhost:5432/" + userAndDatabase[0]);
            bds.setUsername(userAndDatabase[0]);
            bds.setPassword(passDatabase[0]);
            bds.setPoolPreparedStatements(true);
            bds.setMinIdle(5);
            bds.setMaxIdle(10);
            bds.setMaxOpenPreparedStatements(5);
            dataSource[0] = bds;
        }
        return dataSource[0];
    }
    protected static synchronized DataSource getDbProgram8(boolean value) {
        if (dataSource[8] == null) {
            BasicDataSource bds = new BasicDataSource();
            bds.setDriverClassName("org.postgresql.Driver");
            bds.setUrl("jdbc:postgresql://localhost:5432/" + userAndDatabase[8]);
            bds.setUsername(userAndDatabase[8]);
            bds.setPassword(passDatabase[8]);
            bds.setPoolPreparedStatements(true);
            bds.setMinIdle(5);
            bds.setMaxIdle(10);
            bds.setMaxOpenPreparedStatements(5);
            dataSource[8] = bds;
        }
        return dataSource[8];
    }
    
    public static DataSource getRefDataSource(){
         if (dataSource[14] == null){
     BasicDataSource bds = new BasicDataSource();
     bds.setDriverClassName("org.postgresql.Driver");
     bds.setUrl("jdbc:postgresql://localhost:5432/programa15");
     bds.setUsername("postgres");
     bds.setPassword("postgres");
     bds.setPoolPreparedStatements(true);
     bds.setMaxOpenPreparedStatements(5);
           dataSource[14] = bds; 
         }
         return dataSource[14];
    }
    
    protected static synchronized DataSource getDataSourceGeneral() {
        if (dataSource[0] == null) {
            BasicDataSource bds = new BasicDataSource();
            bds.setDriverClassName("org.postgresql.Driver");
            bds.setUrl("jdbc:postgresql://localhost:5432/" + userAndDatabase[0]);
            bds.setUsername(userAndDatabase[0]);
            bds.setPassword(passDatabase[0]);
            bds.setPoolPreparedStatements(true);
            bds.setMinIdle(5);
            bds.setMaxIdle(10);
           
            bds.setMaxOpenPreparedStatements(5);
            dataSource[0] = bds;
        }
        return dataSource[0];
    }
}