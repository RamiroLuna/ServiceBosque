/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.CatalogoDTO;
import org.probosque.dto.DateDTO;
import org.probosque.dto.FechasDictaminacion;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.ResultString;
import org.probosque.dto.UserDTO;
import org.probosque.dto.VentanillaDTO;

/**
 *
 * @author TOSHIBA-L55
 */
public class VentanillaDAO {
    public List<VentanillaDTO> cargaPeticion(UserDTO user, int tipo, String _folio){
        List<VentanillaDTO> exito=new ArrayList<VentanillaDTO>();
        try {
        DataSource ds = PoolDataSource.getDataSource(user);
        Connection con=ds.getConnection();
        ResultSet rs=null;
        PreparedStatement st=null;
        String sql="";
         switch(tipo){
            case 1:
                sql=("SELECT"
                        + " formularios.principal.folio,"
                        + " formularios.principal.modulopredio_estado AS consecutivo,"
                        + " formularios.principal.region AS observacion, "
                        + " formularios.principal.fecha_registro,"
                        + " CAST('' AS text) AS fecha_envio_juridico,"
                        + " CAST('' AS text) AS fecha_recibido_juridico,"
                        + " CAST('' AS text) AS fecha_envio_tecnico,"
                        + " CAST('' AS text) AS fecha_recibido_tecnico,"
                        + " CAST('' AS text) AS fecha_envio_ambiental,"
                        + " CAST('' AS text) AS fecha_recibido_ambiental,"
                        + " formularios.principal.estatus AS respuesta,"
                        + " CAST(0 as integer) as max_consecutivo,"
                        + " formularios.principal.predio,"
                        + " formularios.principal.numvuelta,"
                        + " formularios.principal.diasinternos"
                        + " FROM formularios.principal "
                        + " WHERE formularios.principal.estatus='-1' "
                        + " ORDER BY "
                        + " formularios.principal.folio,formularios.principal.fecha_registro DESC");
            break;
            case 2:
                sql=("SELECT *"
                        + " FROM (select fd.folio,"
                        + " fd.consecutivo,"
                        + " fd.observaciones as observacion,"
                        + " fd.fecha_cambio as Fecha_Registro,"
                        + " fd.fecha_envio_j as fecha_envio_juridico,"
                        + " fd.fecha_recep_j as fecha_recibido_juridico,"
                        + " fd.fecha_envio_t as fecha_envio_tecnico, "
                        + " fd.fecha_recep_t as fecha_recibido_tecnico,"
                        + " fd.fecha_envio_a as fecha_envio_ambiental,"
                        + " fd.fecha_recep_a as fecha_recibido_ambiental,"
                        + " fd.respuesta,"
                        + " fp.predio,"
                        + " fp.diasinternos,"
                        + " fp.numvuelta,"
                        + " MAX(fd.consecutivo) OVER (PARTITION BY fd.folio) as max_consecutivo"
                        + " FROM formularios.dictaminacion fd"
                        + " inner join formularios.principal fp"
                        + " on fd.folio = fp.folio and fp.estatus = '1') t "
                        + " WHERE consecutivo = max_consecutivo");
            break;
            case 3:
                sql=("SELECT * "
                        + " FROM (select fr.folio,"
                        + " fr.consecutivo,"
                        + " fr.obcervaciones as observacion,"
                        + " fr.fecha_cambio as Fecha_Registro,"
                        + " CAST('' as text) as fecha_envio_juridico,"
                        + " CAST('' as text) as fecha_recibido_juridico, "
                        + " CAST('' as text) as fecha_envio_tecnico, "
                        + " CAST('' as text) as fecha_recibido_tecnico,"
                        + " CAST('' as text) as fecha_envio_ambiental,"
                        + " CAST('' as text) as fecha_recibido_ambiental,"
                        + " fr.respuesta, "
                        + " fp.predio,"
                        + " fp.diasinternos,"
                        + " fp.numvuelta,"
                        + " MAX(fr.consecutivo) OVER (PARTITION BY fr.folio) as max_consecutivo"
                        + " FROM formularios.rechazo fr"
                        + " inner join formularios.principal fp"
                        + " on fr.folio = fp.folio and fp.estatus = '2') t"
                        + " WHERE consecutivo = max_consecutivo");
            break;
            case 4:
                sql=("SELECT * "
                        + " FROM (select fi.folio, "
                        + " fi.consecutivo,"
                        + " fi.observaciones as observacion,"
                        + " fi.fecha_cambio as Fecha_Registro,"
                        + " CAST('' as text) as fecha_envio_juridico,"
                        + " CAST('' as text) as fecha_recibido_juridico, "
                        + " CAST('' as text) as fecha_envio_tecnico,"
                        + " CAST('' as text) as fecha_recibido_tecnico,"
                        + " CAST('' as text) as fecha_envio_ambiental, "
                        + " CAST('' as text) as fecha_recibido_ambiental, "
                        + " CAST('' as text) as respuesta,"
                        + " fp.predio,"
                        + " fp.diasinternos,"
                        + " fp.numvuelta,"
                        + " MAX(fi.consecutivo) OVER (PARTITION BY fi.folio) as max_consecutivo"
                        + " FROM formularios.info_adicional fi "
                        + " inner join formularios.principal fp"
                        + " on fi.folio = fp.folio and fp.estatus = '3') t"
                        + " WHERE consecutivo = max_consecutivo");
            break;
            case 5:
                sql=("SELECT *"
                        + " FROM (select fc.folio,"
                        + " fc.consecutivo, "
                        + " fc.obcervaciones as observacion,"
                        + " fc.fecha_cambio as Fecha_Registro,"
                        + " CAST('' as text) as fecha_envio_juridico,"
                        + " CAST('' as text) as fecha_recibido_juridico,"
                        + " CAST('' as text) as fecha_envio_tecnico,"
                        + " CAST('' as text) as fecha_recibido_tecnico,"
                        + " CAST('' as text) as fecha_envio_ambiental,"
                        + " CAST('' as text) as fecha_recibido_ambiental,"
                        + " fc.respuesta,"
                        + " fp.predio,"
                        + " fp.diasinternos,"
                        + " fp.numvuelta,"
                        + " MAX(fc.consecutivo) OVER (PARTITION BY fc.folio) as max_consecutivo"
                        + " FROM formularios.comite_fomento fc"
                        + " inner join formularios.principal fp"
                        + " on fc.folio = fp.folio and fp.estatus = '4') t"
                        + " WHERE consecutivo = max_consecutivo");
            break;
            case 6:
                sql=("SELECT * "
                        + " FROM (select fc.folio,"
                        + " fc.consecutivo,"
                        + " fc.observaciones as observacion,"
                        + " fc.fecha_cambio as Fecha_Registro, "
                        + " fc.fecha_recepcion_codigo as fecha_envio_juridico,"
                        + " fc.fecha_recepcion_oficio as fecha_recibido_juridico,"
                        + " CAST('' as text) as fecha_envio_tecnico,"
                        + " CAST('' as text) as fecha_recibido_tecnico,"
                        + " CAST('' as text) as fecha_envio_ambiental,"
                        + " CAST('' as text) as fecha_recibido_ambiental,"
                        + " fc.respuesta, fp.predio,"
                        + " fp.diasinternos, "
                        + " fp.numvuelta,"
                        + " MAX(fc.consecutivo) OVER (PARTITION BY fc.folio) as max_consecutivo "
                        + " FROM formularios.codigo_identificacion fc"
                        + " inner join formularios.principal fp"
                        + " on fc.folio = fp.folio and fp.estatus = '5') t"
                        + " WHERE consecutivo = max_consecutivo");
            break;
            case 7:
                sql=("SELECT *"
                        + " FROM (select fn.folio,"
                        + " fn.consecutivo,"
                        + " fn.observaciones as observacion,"
                        + " fn.fecha_cambio as Fecha_Registro,"
                        + " CAST('' as text) as fecha_envio_juridico,"
                        + " CAST('' as text) as fecha_recibido_juridico,"
                        + " CAST('' as text) as fecha_envio_tecnico,"
                        + " CAST('' as text) as fecha_recibido_tecnico,"
                        + " CAST('' as text) as fecha_envio_ambiental,"
                        + " CAST('' as text) as fecha_recibido_ambiental,"
                        + " CAST('' as text) as respuesta,"
                        + " fp.predio, "
                        + " fp.diasinternos,"
                        + " fp.numvuelta, "
                        + " MAX(fn.consecutivo) OVER (PARTITION BY fn.folio) as max_consecutivo"
                        + " FROM formularios.negacion fn "
                        + " inner join formularios.principal fp"
                        + " on fn.folio = fp.folio and fp.estatus = '7') t"
                        + " WHERE consecutivo = max_consecutivo");
            break;
            case 8:
                sql=("SELECT *"
                        + " FROM (select fv.folio,"
                        + " fv.consecutivo,"
                        + " fv.observaciones as observacion,"
                        + " fv.fecha_cambio as Fecha_Registro,"
                        + " CAST('' as text) as fecha_envio_juridico,"
                        + " CAST('' as text) as fecha_recibido_juridico,"
                        + " CAST('' as text) as fecha_envio_tecnico,"
                        + " CAST('' as text) as fecha_recibido_tecnico,"
                        + " CAST('' as text) as fecha_envio_ambiental,"
                        + " CAST('' as text) as fecha_recibido_ambiental, "
                        + " CAST('' as text) as respuesta,"
                        + " fp.predio, fp.diasinternos, "
                        + " fp.numvuelta, MAX(fv.consecutivo) OVER (PARTITION BY fv.folio) as max_consecutivo "
                        + " FROM formularios.vencidas fv"
                        + " inner join formularios.principal fp"
                        + " on fv.folio = fp.folio and fp.estatus = '8') t"
                        + " WHERE consecutivo = max_consecutivo");
            break;
            case 9:
                sql=("SELECT *"
                        + " FROM (select distinct(fa.folio),"
                        + " fa.consecutivo,"
                        + " re.descripcion as observacion, "
                        + " fa.fecha_cambio as Fecha_Registro, "
                        + " mu.descripcion as fecha_envio_juridico, "
                        + " fp.superficie as fecha_recibido_juridico, "
                        + " fp.volumen as fecha_envio_tecnico,"
                        + " fp.propietario as fecha_recibido_tecnico, "
                        + " CAST('' as text) as fecha_envio_ambiental,"
                        + " CAST('' as text) as fecha_recibido_ambiental,"
                        + " CAST('' as text) as respuesta,"
                        + " fp.predio, "
                        + " CAST(0 as integer) as diasinternos,"
                        + " CAST(0 as integer) as numvuelta,"
                        + " MAX(fa.consecutivo) OVER (PARTITION BY fa.folio) as max_consecutivo"
                        + " FROM formularios.autorizacion fa"
                        + " inner join formularios.principal fp"
                        + " on fa.folio = fp.folio and fp.estatus = '6'"
                        + " inner join catalogos.region re"
                        + " on CAST(fp.region as integer) = re.id"
                        + " inner join catalogos.municipio mu"
                        + " on CAST(fp.modulopredio_municipio as integer) = mu.id) t"
                        + " WHERE consecutivo = max_consecutivo");
            break;
            case 10:
                sql=("SELECT *"
                        + " FROM (select fm.folio,"
                        + " fm.consecutivo,"
                        + " fm.observaciones as observacion,"
                        + " fm.fecha_cambio as Fecha_Registro, "
                        + " fm.fecha_recepcion_codigo as fecha_envio_juridico,"
                        + " CAST('' as text) as fecha_recibido_juridico,"
                        + " CAST('' as text) as fecha_envio_tecnico,"
                        + " CAST('' as text) as fecha_recibido_tecnico,"
                        + " CAST('' as text) as fecha_envio_ambiental,"
                        + " CAST('' as text) as fecha_recibido_ambiental,"
                        + " fm.respuesta,"
                        + " fp.predio,"
                        + " fp.diasinternos,"
                        + " fp.numvuelta, "
                        + " MAX(fm.consecutivo) OVER (PARTITION BY fm.folio) as max_consecutivo "
                        + " FROM formularios.estatus_mia fm"
                        + " inner join formularios.principal fp"
                        + " on fm.folio = fp.folio and fp.estatus = '9') t"
                        + " WHERE consecutivo = max_consecutivo");
            break;
            case 11:
                sql=("");
            break;
        }                             
            st=con.prepareStatement(sql);
            rs=st.executeQuery();
            while(rs.next()){
                exito.add(new VentanillaDTO(rs.getString("folio"), rs.getInt("consecutivo"), rs.getString("observacion"), rs.getString("Fecha_registro"), rs.getString("fecha_envio_juridico"), rs.getString("fecha_recibido_juridico"), rs.getString("fecha_envio_tecnico"), rs.getString("fecha_recibido_tecnico"), rs.getString("fecha_envio_ambiental"), rs.getString("fecha_recibido_ambiental"), rs.getString("respuesta"), rs.getString("predio"), rs.getInt("numvuelta"), rs.getInt("diasinternos"), rs.getInt("max_consecutivo")));
            }
            con.close();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(""+ex);
        }
        return exito;
    }
    
    public boolean InsertDictaminacion(UserDTO user, String folio, String fecha_cambio, String fecha_envio_t, String fecha_envio_j, String fecha_envio_a, String fecha_recep_t, String fecha_recep_j, String fecha_recep_a, String observaciones, String respuesta, int estatus_anterior) throws Exception {
        try{
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO formularios.dictaminacion (folio, fecha_cambio, fecha_envio_t, fecha_envio_j, fecha_envio_a, fecha_recep_t, fecha_recep_j, fecha_recep_a, observaciones, respuesta, estatus)");
        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        Object[] params = {folio, fecha_cambio, fecha_envio_t, fecha_envio_j, fecha_envio_a, fecha_recep_t, fecha_recep_j, fecha_recep_a, observaciones, respuesta, estatus_anterior};
        qr.update(sql.toString(), params);
        return true;
        }catch (SQLException e){
        return false;
        } 
    }
    
    public boolean UpdateDictaminacion(UserDTO user, String folio, String fecha_envio_t, String fecha_envio_j, String fecha_envio_a, String fecha_recep_t, String fecha_recep_j, String fecha_recep_a, String observaciones, int consecutivo, String respuesta) throws Exception {
        try{ 
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE formularios.dictaminacion");
        sql.append(" SET fecha_envio_t=?, fecha_envio_j=?, fecha_envio_a=?, fecha_recep_t=?, fecha_recep_j=?, fecha_recep_a=?, observaciones=?, respuesta=?");
        sql.append(" WHERE folio = ? and consecutivo = ?;");
        Object[] params = {fecha_envio_t, fecha_envio_j, fecha_envio_a, fecha_recep_t, fecha_recep_j, fecha_recep_a, observaciones, respuesta, folio, consecutivo};
        qr.update(sql.toString(), params);
        return true;
        }catch (SQLException e){
        return false;
        } 
    }
    
    
     public boolean InsertRechazo(UserDTO user, String folio, String fecha_cambio, String observaciones) throws Exception {
        try{
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO formularios.rechazo(folio, fecha_cambio, obcervaciones)");
        sql.append(" VALUES (?, ?, ?);");
        Object[] params = {folio, fecha_cambio, observaciones};
        qr.update(sql.toString(), params);
        
        sql = new StringBuilder();
        sql.append(" SELECT estatus  AS result FROM formularios.principal WHERE  folio='").append(folio).append("'");
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger)qr.query(sql.toString(), rsh);
        
        if(result!=null && result.getResult()==1)
         {
        sql = new StringBuilder();
        sql.append(" SELECT fecha_envio_t, fecha_envio_j,  fecha_envio_a,"
                + " fecha_recep_t as fecha_recepcion_t, fecha_recep_j as fecha_recepcion_j,  fecha_recep_a as fecha_recepcion_a FROM formularios.dictaminacion "
                + " WHERE  folio='").append(folio).append("' and ");
        sql.append(" consecutivo=(Select max(consecutivo) from formularios.dictaminacion where folio='").append(folio).append("')");
        rsh= new BeanHandler(FechasDictaminacion.class);
        FechasDictaminacion fechas= (FechasDictaminacion)qr.query(sql.toString(), rsh);
       if(fechas.getFecha_envio_t()==null || fechas.getFecha_envio_t().isEmpty() || fechas.getFecha_envio_t().equals(" "))
            fechas.setFecha_envio_t("11/11/9999");
        if(fechas.getFecha_envio_j()==null || fechas.getFecha_envio_j().isEmpty() || fechas.getFecha_envio_j().equals(" "))
            fechas.setFecha_envio_j("11/11/9999");
        if(fechas.getFecha_envio_a()==null || fechas.getFecha_envio_a().isEmpty() || fechas.getFecha_envio_a().equals(" "))
            fechas.setFecha_envio_a("11/11/9999");
        
        if(fechas.getFecha_recepcion_t()==null || fechas.getFecha_recepcion_t().isEmpty() || fechas.getFecha_recepcion_t().equals(" "))
            fechas.setFecha_recepcion_t("11/11/1111");
        if(fechas.getFecha_recepcion_j()==null || fechas.getFecha_recepcion_j().isEmpty() || fechas.getFecha_recepcion_j().equals(" "))
            fechas.setFecha_recepcion_j("11/11/1111");
        if(fechas.getFecha_recepcion_a()==null || fechas.getFecha_recepcion_a().isEmpty() || fechas.getFecha_recepcion_a().equals(" "))
            fechas.setFecha_recepcion_a("11/11/1111");
        
        this.getDiasDictaminacion(user,folio, fechas.getFecha_envio_t(), fechas.getFecha_envio_j(),fechas.getFecha_envio_a(), fechas.getFecha_recepcion_t(), fechas.getFecha_recepcion_j(), fechas.getFecha_recepcion_a());
            
         } 
        
        
        
        
        return true;
        }catch (SQLException e){
        return false;
        } 
    }

    public boolean UpdateRechazo(UserDTO user, String folio, String observaciones, int consecutivo){
        try {
            DataSource ds = PoolDataSource.getDataSource(user);
            QueryRunner qr = new QueryRunner(ds);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE formularios.rechazo");
            sql.append(" SET obcervaciones=?");
            sql.append(" WHERE folio=? and consecutivo=?;");
            Object[] params = {observaciones, folio, consecutivo};
            qr.update(sql.toString(), params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }    
    
    public boolean InsertFechaExclusa(UserDTO user, String fecha) throws Exception {
        try{
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO formularios.fechas_excluidas(fechaexcluida)");
        sql.append(" VALUES (?);");
        Object[] params = {fecha};
        qr.update(sql.toString(), params);
        return true;
        }catch (SQLException e){
        return false;
        } 
    }
    
    public List<CatalogoDTO> getListFechas(UserDTO user) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT consecutivo AS id, fechaExcluida AS descripcion");
        sql.append(" FROM formularios.fechas_excluidas");
        sql.append(" ORDER BY consecutivo");
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        List<CatalogoDTO> list = (List<CatalogoDTO>) qr.query(sql.toString(), rsh);
        return list;
    }
    
    public boolean deleteFechaExclusa(UserDTO user, int indice) throws Exception {
        try{
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM formularios.fechas_excluidas");
        sql.append(" WHERE consecutivo = ?;");
        Object[] params = {indice};
        qr.update(sql.toString(), params);
        return true;
        }catch (SQLException e){
        return false;
        } 
    }
    
    public List<VentanillaDTO> getSolicitudes(UserDTO user) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT");
        sql.append(" folio,");
        sql.append(" CAST(0 as INT) as consecutivo,");
        sql.append(" modulopredio_cup as observacion,");
        sql.append(" fecha_registro as Fecha_Registro,");
        sql.append(" CAST('' as TEXT) as fecha_envio_juridico,");
        sql.append(" CAST('' as TEXT) as fecha_recibido_juridico,");
        sql.append(" CAST('' as TEXT) as fecha_envio_tecnico,");
        sql.append(" CAST('' as TEXT) as fecha_recibido_tecnico,");
        sql.append(" CAST('' as TEXT) as fecha_envio_ambiental,");
        sql.append(" CAST('' as TEXT) as fecha_recibido_ambiental,");
        sql.append(" CASE");
        sql.append("    WHEN estatus = '1' THEN 'DICTAMINACÍON'");
        sql.append("	WHEN estatus = '2' THEN 'RECHAZO'");
        sql.append("	WHEN estatus = '3' THEN 'INFORMACIÓN ADICIONAL'");
        sql.append("	WHEN estatus = '4' THEN 'COMITÉ DE FOMENTO'");
        sql.append("	WHEN estatus = '5' THEN 'CÓDIGO DE IDENTIFICACIÓN'");
        sql.append("	WHEN estatus = '6' THEN 'AUTORIZACIÓN'");
        sql.append("	WHEN estatus = '7' THEN 'NEGACIÓN'");
        sql.append("	WHEN estatus = '8' THEN 'VENCIDAS'");
        sql.append("	WHEN estatus = '9' THEN 'ESTATUS DE MIA(Espera resolutivo de MIA)'");
        sql.append("	ELSE 'INICIO DE PROCESO'");
        sql.append(" END as respuesta,");
        sql.append(" estatus as predio,");
        sql.append(" diasinternos");
        sql.append(" FROM formularios.principal");
        sql.append(" ORDER BY fecha_registro asc");
        ResultSetHandler rsh = new BeanListHandler(VentanillaDTO.class);
        List<VentanillaDTO> list = (List<VentanillaDTO>) qr.query(sql.toString(), rsh);
        return list;
    }
    
    public String UltimaFecha(UserDTO user, String folio){
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        try {
            sql.append("SELECT fecha_cambio as result"
                    + " FROM (select distinct(fa.folio),"
                    + " fa.consecutivo,"
                    + " fa.fecha_cambio,"
                    + " MAX(fa.consecutivo) OVER (PARTITION BY fa.folio) as max_consecutivo"
                    + " FROM formularios.autorizacion fa"
                    + " inner join formularios.principal fp"
                    + " on fa.folio = fp.folio and fp.estatus = '6' and fa.folio = '"+folio+"') t"
                    + " WHERE consecutivo = max_consecutivo;");
            ResultSetHandler rsh = new BeanHandler(ResultString.class);
            ResultString result = (ResultString)qr.query(sql.toString(), rsh);

            return result.getResult();
        } catch (Exception e) {
            return "";
        }
    }
    
    public int SelectVuelta(UserDTO user, String folio) throws Exception {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT numvuelta as consecutivo FROM formularios.principal WHERE folio = ?");
        Object[] params = {folio};
        ResultSetHandler rsh = new BeanHandler(VentanillaDTO.class);
        VentanillaDTO result = (VentanillaDTO) qr.query(sql.toString(), rsh, params);
        if (result != null) {
            return result.getConsecutivo();
        } else {
            return 10;
        }
    }
    
    public boolean UpdateVuelta(UserDTO user, String estatus, String folio, int vuelta) throws Exception {
        try{
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE formularios.principal");
        sql.append(" SET estatus=?, numvuelta=?");
        sql.append(" WHERE folio = ?;");
        Object[] params = {estatus, vuelta, folio};
        qr.update(sql.toString(), params);
        return true;
        }catch (SQLException e){
        return false;
        } 
    }
    
    public boolean UpdateEstatus(UserDTO user, String estatus, String folio) throws Exception {
        try{
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE formularios.principal");
        sql.append(" SET estatus=?");
        sql.append(" WHERE folio = ?;");
        Object[] params = {estatus, folio};
        qr.update(sql.toString(), params);
        return true;
        }catch (SQLException e){
        return false;
        } 
    }
    
    
    // Codigo Mike 
  //  VEntanilla 
    //
        public boolean InsertInformacionAdicional(UserDTO user, String folio, String fecha_cambio, String observaciones) throws Exception {
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT estatus  AS result FROM formularios.principal WHERE  folio='").append(folio).append("'");
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger)qr.query(sql.toString(), rsh);
       
       try{
           sql= new StringBuilder();
            sql.append("Insert Into formularios.info_adicional(folio, fecha_cambio, observaciones, status)");             
            sql.append("values(?,?,?,?)");
            Object[] params = {folio, fecha_cambio, observaciones,result.getResult()};
            qr.update(sql.toString(), params);
        
            if(result!=null && result.getResult()==1)
         {
        sql = new StringBuilder();
        sql.append(" SELECT fecha_envio_t, fecha_envio_j,  fecha_envio_a,"
                + " fecha_recep_t as fecha_recepcion_t, fecha_recep_j as fecha_recepcion_j,  fecha_recep_a as fecha_recepcion_a FROM formularios.dictaminacion "
                + " WHERE  folio='").append(folio).append("' and ");
        sql.append(" consecutivo=(Select max(consecutivo) from formularios.dictaminacion where folio='").append(folio).append("')");
        rsh= new BeanHandler(FechasDictaminacion.class);
        FechasDictaminacion fechas= (FechasDictaminacion)qr.query(sql.toString(), rsh);
        if(fechas.getFecha_envio_t()==null || fechas.getFecha_envio_t().isEmpty() || fechas.getFecha_envio_t().equals(" "))
            fechas.setFecha_envio_t("11/11/9999");
        if(fechas.getFecha_envio_j()==null || fechas.getFecha_envio_j().isEmpty() || fechas.getFecha_envio_j().equals(" "))
            fechas.setFecha_envio_j("11/11/9999");
        if(fechas.getFecha_envio_a()==null || fechas.getFecha_envio_a().isEmpty() || fechas.getFecha_envio_a().equals(" "))
            fechas.setFecha_envio_a("11/11/9999");
        
        if(fechas.getFecha_recepcion_t()==null || fechas.getFecha_recepcion_t().isEmpty() || fechas.getFecha_recepcion_t().equals(" "))
            fechas.setFecha_recepcion_t("11/11/1111");
        if(fechas.getFecha_recepcion_j()==null || fechas.getFecha_recepcion_j().isEmpty() || fechas.getFecha_recepcion_j().equals(" "))
            fechas.setFecha_recepcion_j("11/11/1111");
        if(fechas.getFecha_recepcion_a()==null || fechas.getFecha_recepcion_a().isEmpty() || fechas.getFecha_recepcion_a().equals(" "))
            fechas.setFecha_recepcion_a("11/11/1111");
        
        this.getDiasDictaminacion(user,folio, fechas.getFecha_envio_t(), fechas.getFecha_envio_j(),fechas.getFecha_envio_a(), fechas.getFecha_recepcion_t(), fechas.getFecha_recepcion_j(), fechas.getFecha_recepcion_a());
            
         }
        return true;
        }catch (SQLException e){
        return false;
        } 
    }



    
    public boolean UpdateInformacionAdicional(UserDTO user, String folio, String fecha_cambio, String observaciones, int consecutivo) throws Exception {
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
      try{    
        sql= new StringBuilder();
            sql.append("Update formularios.info_adicional set folio=?, fecha_cambio=?, observaciones=?");             
            sql.append("WHERE folio='").append(folio).append("' and consecutivo=").append(consecutivo);
            Object[] params = {folio, fecha_cambio, observaciones};
            qr.update(sql.toString(), params);
           
        return true;
        }catch (SQLException e){
        return false;
        } 
    }

    
    
    public boolean InformacionAdicionalDictaminacion(UserDTO user, String folio, String fecha_cambio, String observaciones) throws Exception {
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
      try{    
          sql.append(" SELECT estatus  AS result FROM formularios.principal WHERE  folio='").append(folio).append("'");
          ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger)qr.query(sql.toString(), rsh);
          sql = new StringBuilder();
         
          sql.append(" INSERT INTO formularios.dictaminacion (folio, fecha_cambio, estatus) ");
          sql.append(" VALUES(?,?,?) ");
          Object[] params = {folio, fecha_cambio, result.getResult()};
          qr.update(sql.toString(), params);
          
          sql = new StringBuilder();
          sql.append(" SELECT COUNT(*) as result FROM formularios.principal ");
          sql.append(" WHERE folio ='").append(folio).append("'");
          rsh=new BeanHandler(ResultInteger.class);
          ResultInteger resultI = (ResultInteger) qr.query(folio, rsh);
          
          sql = new StringBuilder(); 
          sql.append("UPDATE formularios.principal SET numvuelta=").append(resultI.getResult());
          sql.append(" WHERE folio='").append(folio).append("'");
          qr.update(sql.toString());
          
          
          
        return true;
        }catch (SQLException e){
        return false;
        } 
    }

        public boolean InsertComiteFomento(UserDTO user,String folio, String fecha,  String observaciones )
     {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        try{
          sql.append(" SELECT estatus  AS result FROM formularios.principal WHERE  folio='").append(folio).append("'");
          ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger)qr.query(sql.toString(), rsh);
          sql = new StringBuilder();
          
          sql= new StringBuilder();
              sql.append(" INSERT INTO  formularios.comite_fomento (folio,fecha_cambio,obcervaciones,status) ");             
              sql.append(" VALUES(?,?,?,?) ");
              Object[] params = {folio, fecha, observaciones,result.getResult()};
              qr.update(sql.toString(), params);
              
              if(result!=null && result.getResult()==1)
         {
        sql = new StringBuilder();
        sql.append(" SELECT fecha_envio_t, fecha_envio_j,  fecha_envio_a,"
                + " fecha_recep_t as fecha_recepcion_t, fecha_recep_j as fecha_recepcion_j,  fecha_recep_a as fecha_recepcion_a FROM formularios.dictaminacion "
                + " WHERE  folio='").append(folio).append("' and ");
        sql.append(" consecutivo=(Select max(consecutivo) from formularios.dictaminacion where folio='").append(folio).append("')");
        rsh= new BeanHandler(FechasDictaminacion.class);
        FechasDictaminacion fechas= (FechasDictaminacion)qr.query(sql.toString(), rsh);
       if(fechas.getFecha_envio_t()==null || fechas.getFecha_envio_t().isEmpty() || fechas.getFecha_envio_t().equals(" "))
            fechas.setFecha_envio_t("11/11/9999");
        if(fechas.getFecha_envio_j()==null || fechas.getFecha_envio_j().isEmpty() || fechas.getFecha_envio_j().equals(" "))
            fechas.setFecha_envio_j("11/11/9999");
        if(fechas.getFecha_envio_a()==null || fechas.getFecha_envio_a().isEmpty() || fechas.getFecha_envio_a().equals(" "))
            fechas.setFecha_envio_a("11/11/9999");
        
        if(fechas.getFecha_recepcion_t()==null || fechas.getFecha_recepcion_t().isEmpty() || fechas.getFecha_recepcion_t().equals(" "))
            fechas.setFecha_recepcion_t("11/11/1111");
        if(fechas.getFecha_recepcion_j()==null || fechas.getFecha_recepcion_j().isEmpty() || fechas.getFecha_recepcion_j().equals(" "))
            fechas.setFecha_recepcion_j("11/11/1111");
        if(fechas.getFecha_recepcion_a()==null || fechas.getFecha_recepcion_a().isEmpty() || fechas.getFecha_recepcion_a().equals(" "))
            fechas.setFecha_recepcion_a("11/11/1111");
        
        this.getDiasDictaminacion(user,folio, fechas.getFecha_envio_t(), fechas.getFecha_envio_j(),fechas.getFecha_envio_a(), fechas.getFecha_recepcion_t(), fechas.getFecha_recepcion_j(), fechas.getFecha_recepcion_a());
            
         }
              
              
              
          return true;
          }catch (SQLException e){
          return false;}
     
     }
    
    public boolean UpdateComiteFomento(UserDTO user,String folio,  String observaciones,String respuesta, int consecutivo, String fecha )
     {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        try{    
          sql= new StringBuilder();
              sql.append(" UPDATE   formularios.comite_fomento  SET obcervaciones=?, respuesta=? ,fecha_cambio=?");             
              sql.append(" WHERE folio='").append(folio).append("' and consecutivo =").append(consecutivo);
              Object[] params = {observaciones,respuesta,fecha};
              qr.update(sql.toString(), params);
          return true;
          }catch (SQLException e){
          return false;}
     }
   
    public boolean InsertCodigoIdentificacion(UserDTO user, String folio, String fecha,String  observaciones, String fecha_oficio) throws Exception
    {
    
    DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        try{
            sql.append(" SELECT estatus  AS result FROM formularios.principal WHERE  folio='").append(folio).append("'");
            ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
            ResultInteger result = (ResultInteger)qr.query(sql.toString(), rsh);
            sql = new StringBuilder();
          
            sql= new StringBuilder();
            sql.append(" INSERT INTO  formularios.codigo_identificacion(folio,fecha_cambio,observaciones,status, fecha_recepcion_oficio,dias_oficio) ");             
            sql.append(" VALUES(?,?,?,?,?,?) ");
            Object[] params = {folio, fecha, observaciones,result.getResult(), fecha_oficio,0};
            qr.update(sql.toString(), params);
            
         /*   if (result.getResult() == 4) {
                this.DiasComiteFomento_CodigoIdentificacion(user, folio, fecha);
            }*/
            
            return true;
         }catch (SQLException e){
            return false;
         }
    }
    
    public boolean DiasComiteFomento_CodigoIdentificacion(UserDTO user, String folio, String fecha){
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        try {
            Date FechaCI = this.getFormatoDate(user, fecha);
            sql.append("SELECT fecha_cambio as result"); 
            sql.append(" FROM (SELECT fc.folio,");
            sql.append(" fc.consecutivo,");
            sql.append(" fc.fecha_cambio,");
            sql.append(" MAX(fc.consecutivo) OVER (PARTITION BY fc.folio) AS max_consecutivo");
            sql.append(" FROM formularios.comite_fomento fc");
            sql.append(" INNER JOIN formularios.principal fp");
            sql.append(" ON fc.folio = fp.folio AND fp.folio = '"+folio+"') t");
            sql.append(" WHERE consecutivo = max_consecutivo");
            ResultSetHandler rshf = new BeanHandler(ResultString.class);
            ResultString resultf = (ResultString)qr.query(sql.toString(), rshf);
            
            Date FechaCF = this.getFormatoDate(user, resultf.getResult());
            
            sql= new StringBuilder();
            sql.append("SELECT  formularios.contdia('").append(FechaCF).append("','").append(FechaCI).append("') AS result");
            ResultSetHandler rsh=new BeanHandler(ResultInteger.class);
            ResultInteger result = (ResultInteger)(qr.query(sql.toString(), rsh));
            result.getResult();              

            sql= new StringBuilder();
            sql.append("SELECT  diasinternos AS result");
            sql.append(" FROM formularios.principal ");
            sql.append(" WHERE folio='").append(folio).append("'");
            rsh=new BeanHandler(ResultInteger.class);
            ResultInteger result2 = (ResultInteger)(qr.query(sql.toString(), rsh));
            if(result2.getResult()==0 )
               {
            sql= new StringBuilder();
            sql.append("Update formularios.principal SET diasinternos=").append(result.getResult());
            sql.append(" WHERE folio='").append(folio).append("'");

               }
            else
            {             
            sql= new StringBuilder();
            sql.append("Update formularios.principal SET diasinternos=diasinternos+").append(result.getResult());
            sql.append(" WHERE folio='").append(folio).append("'");
            }
           qr.update(sql.toString());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean UpdateCodigoIdentificacion(UserDTO user,String folio, String observaciones, String fecha_recepcion_codigo, String respuesta, int consecutivo, String fecha_recepcion_oficio )
     {
         DataSource ds = PoolDataSource.getDataSource(user);
         QueryRunner qr = new QueryRunner(ds);
         StringBuilder sql = new StringBuilder();
         try{    
             sql= new StringBuilder();
             sql.append(" UPDATE   formularios.codigo_identificacion  SET observaciones=?, respuesta=?,fecha_recepcion_codigo=?, fecha_recepcion_oficio=? ");             
             sql.append(" WHERE folio='").append(folio).append("' and consecutivo =").append(consecutivo);
             Object[] params = {observaciones,respuesta, fecha_recepcion_codigo, fecha_recepcion_oficio};
             qr.update(sql.toString(), params);
             sql= new StringBuilder();
             
             /*
              Seleciono la fecha de cambio para el conteo de dias se toma la consulta del estatus de comite de fomento
             */
             sql.append(" SELECT consecutivo as id, fecha_cambio as descripcion FROM formularios.comite_fomento WHERE folio='").append(folio).append("' and consecutivo= (select max(consecutivo) from formularios.comite_fomento where folio='").append(folio).append("')");
             
             ResultSetHandler rsh = new BeanHandler(CatalogoDTO.class);
             CatalogoDTO fechaCambio= (CatalogoDTO)qr.query(sql.toString(),rsh);
             
             //obtengo los dias de comite a recepcion de oficio 
             int dias = this.getDias(user, fechaCambio.getDescripcion(), fecha_recepcion_oficio);
             dias=dias-1;
                          
             sql= new StringBuilder();
             sql.append(" SELECT  dias_oficio as result FROM formularios.codigo_identificacion WHERE folio='").append(folio).append("'");
             sql.append(" AND consecutivo=").append(consecutivo);
             rsh = new BeanHandler(ResultInteger.class);
             ResultInteger rs= (ResultInteger)(qr.query(sql.toString(),rsh));
             if(rs.getResult()==0)
                {
                sql= new StringBuilder();
                sql.append("Update formularios.principal SET diasinternos=(diasinternos+").append(dias).append(")");
                sql.append(" WHERE folio='").append(folio).append("'");
                }
             else    
                 {
                 sql= new StringBuilder();
                 sql.append("Update formularios.principal SET diasinternos=diasinternos-").append(rs.getResult());
                 sql.append(" WHERE folio='").append(folio).append("'");
                 qr.update(sql.toString());
                 sql= new StringBuilder();
                 sql.append("Update formularios.principal SET diasinternos=diasinternos+").append(dias);
                 sql.append(" WHERE folio='").append(folio).append("'");
                     
                 }
             qr.update(sql.toString());
             
             sql= new StringBuilder();
             sql.append("UPDATE formularios.codigo_identificacion SET dias_oficio=").append(dias);
             sql.append(" WHERE  folio='").append(folio).append("' and consecutivo=").append(consecutivo);
             qr.update(sql.toString());
              
             
             
             
             
             return true;
         }catch (SQLException e){
             return false;
         }
     }
    
    public boolean InsertNegacion(UserDTO user, String folio, String fecha,String  observaciones)
    {
    
    DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        try{
          sql.append(" SELECT estatus  AS result FROM formularios.principal WHERE  folio='").append(folio).append("'");
          ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger)qr.query(sql.toString(), rsh);
          sql = new StringBuilder();
          
          sql= new StringBuilder();
              sql.append(" INSERT INTO  formularios.negacion(folio,fecha_cambio,observaciones,estatus) ");             
              sql.append(" VALUES(?,?,?,?) ");
              Object[] params = {folio, fecha, observaciones,result.getResult()};
              qr.update(sql.toString(), params);
              
                            if(result!=null && result.getResult()==1)
         {
        sql = new StringBuilder();
        sql.append(" SELECT fecha_envio_t, fecha_envio_j,  fecha_envio_a,"
                + " fecha_recep_t as fecha_recepcion_t, fecha_recep_j as fecha_recepcion_j,  fecha_recep_a as fecha_recepcion_a FROM formularios.dictaminacion "
                + " WHERE  folio='").append(folio).append("' and ");
        sql.append(" consecutivo=(Select max(consecutivo) from formularios.dictaminacion where folio='").append(folio).append("')");
        rsh= new BeanHandler(FechasDictaminacion.class);
        FechasDictaminacion fechas= (FechasDictaminacion)qr.query(sql.toString(), rsh);
        if(fechas.getFecha_envio_t()==null || fechas.getFecha_envio_t().isEmpty() || fechas.getFecha_envio_t().equals(" "))
            fechas.setFecha_envio_t("11/11/9999");
        if(fechas.getFecha_envio_j()==null || fechas.getFecha_envio_j().isEmpty() || fechas.getFecha_envio_j().equals(" "))
            fechas.setFecha_envio_j("11/11/9999");
        if(fechas.getFecha_envio_a()==null || fechas.getFecha_envio_a().isEmpty() || fechas.getFecha_envio_a().equals(" "))
            fechas.setFecha_envio_a("11/11/9999");
        
        if(fechas.getFecha_recepcion_t()==null || fechas.getFecha_recepcion_t().isEmpty() || fechas.getFecha_recepcion_t().equals(" "))
            fechas.setFecha_recepcion_t("11/11/1111");
        if(fechas.getFecha_recepcion_j()==null || fechas.getFecha_recepcion_j().isEmpty() || fechas.getFecha_recepcion_j().equals(" "))
            fechas.setFecha_recepcion_j("11/11/1111");
        if(fechas.getFecha_recepcion_a()==null || fechas.getFecha_recepcion_a().isEmpty() || fechas.getFecha_recepcion_a().equals(" "))
            fechas.setFecha_recepcion_a("11/11/1111");
        
        this.getDiasDictaminacion(user,folio, fechas.getFecha_envio_t(), fechas.getFecha_envio_j(),fechas.getFecha_envio_a(), fechas.getFecha_recepcion_t(), fechas.getFecha_recepcion_j(), fechas.getFecha_recepcion_a());
            
         }

              
              
              
          return true;
          }catch (SQLException e){
         return false;}
    }

    public boolean UpdateNegacion(UserDTO user,String folio,   String observaciones, int consecutivo )
     {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        try{    
          sql= new StringBuilder();
              sql.append(" UPDATE   formularios.negacion  SET observaciones=?");             
              sql.append(" WHERE folio='").append(folio).append("' and consecutivo =").append(consecutivo);
              Object[] params = {observaciones};
              qr.update(sql.toString(), params);
          return true;
          }catch (SQLException e){
          return false;}
     }
    
    
    public boolean InsertMIA(UserDTO user, String folio, String fecha,String  observaciones) throws Exception
    {
    
    DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        try{
          sql.append(" SELECT estatus  AS result FROM formularios.principal WHERE  folio='").append(folio).append("'");
         ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger)qr.query(sql.toString(), rsh);
          sql = new StringBuilder();
          
          sql= new StringBuilder();
              sql.append(" INSERT INTO  formularios.estatus_mia(folio,fecha_cambio,observaciones,estatus) ");             
              sql.append(" VALUES(?,?,?,?) ");
              Object[] params = {folio, fecha, observaciones,result.getResult()};
              qr.update(sql.toString(), params);
              
           if (result.getResult() == 4) {
                this.DiasComiteFomento_Mia(user, folio, fecha);
            }
           
          return true;
          }catch (SQLException e){
         return false;}
    }
    
    public boolean DiasComiteFomento_Mia(UserDTO user, String folio, String fecha){
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        try {
            Date FechaMia = this.getFormatoDate(user, fecha);
            sql.append("SELECT fecha_cambio as result"); 
            sql.append(" FROM (SELECT fc.folio,");
            sql.append(" fc.consecutivo,");
            sql.append(" fc.fecha_cambio,");
            sql.append(" MAX(fc.consecutivo) OVER (PARTITION BY fc.folio) AS max_consecutivo");
            sql.append(" FROM formularios.comite_fomento fc");
            sql.append(" INNER JOIN formularios.principal fp");
            sql.append(" ON fc.folio = fp.folio AND fp.folio = '"+folio+"') t");
            sql.append(" WHERE consecutivo = max_consecutivo");
            ResultSetHandler rshf = new BeanHandler(ResultString.class);
            ResultString resultf = (ResultString)qr.query(sql.toString(), rshf);
            
            Date FechaCF = this.getFormatoDate(user, resultf.getResult());
            
            sql= new StringBuilder();
            sql.append("SELECT  formularios.contdia('").append(FechaCF).append("','").append(FechaMia).append("') AS result");
            ResultSetHandler rsh=new BeanHandler(ResultInteger.class);
            ResultInteger result = (ResultInteger)(qr.query(sql.toString(), rsh));
            result.getResult();              

            sql= new StringBuilder();
            sql.append("SELECT  diasinternos AS result");
            sql.append(" FROM formularios.principal ");
            sql.append(" WHERE folio='").append(folio).append("'");
            rsh=new BeanHandler(ResultInteger.class);
            ResultInteger result2 = (ResultInteger)(qr.query(sql.toString(), rsh));
            if(result2.getResult()==0 )
               {
            sql= new StringBuilder();
            sql.append("Update formularios.principal SET diasinternos=").append(result.getResult());
            sql.append(" WHERE folio='").append(folio).append("'");

               }
            else
            {             
            sql= new StringBuilder();
            sql.append("Update formularios.principal SET diasinternos=diasinternos+").append(result.getResult());
            sql.append(" WHERE folio='").append(folio).append("'");
            }
           qr.update(sql.toString());

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean UpdateMIA(UserDTO user,String folio, String observaciones,String fecha_recepcion , int consecutivo )
     {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        try{    
          sql= new StringBuilder();
              sql.append(" UPDATE   formularios.estatus_mia  SET observaciones=?, fecha_recepcion_codigo=?");             
              sql.append(" WHERE folio='").append(folio).append("' and consecutivo =").append(consecutivo);
              Object[] params = {observaciones,fecha_recepcion};
              qr.update(sql.toString(), params);
          return true;
          }catch (SQLException e){
          return false;}
     }
    
    public boolean InsertVencidas(UserDTO user, String folio, String fecha,String  observaciones)
    {
    
    DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        try{
          sql.append(" SELECT estatus  AS result FROM formularios.principal WHERE  folio='").append(folio).append("'");
          ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
            ResultInteger result = (ResultInteger)qr.query(sql.toString(), rsh);
          sql = new StringBuilder();
          
          sql= new StringBuilder();
              sql.append(" INSERT INTO  formularios.vencidas(folio,fecha_cambio,observaciones,estatus) ");             
              sql.append(" VALUES(?,?,?,?) ");
              Object[] params = {folio, fecha, observaciones,result.getResult()};
              qr.update(sql.toString(), params);
          return true;
          }catch (SQLException e){
         return false;}
    }

    public boolean UpdateVencidas(UserDTO user,String folio, String observaciones, int consecutivo )
     {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        try{    
          sql= new StringBuilder();
              sql.append(" UPDATE   formularios.vencidas  SET observaciones=?");             
              sql.append(" WHERE folio='").append(folio).append("' and consecutivo =").append(consecutivo);
              Object[] params = {observaciones};
              qr.update(sql.toString(), params);
          return true;
          }catch (SQLException e){
          return false;}
     }
    
    
    public boolean InsertAutorizadas(UserDTO user, String folio, String fecha) throws Exception
    {
    
    DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        try{
          sql.append(" SELECT estatus  AS result FROM formularios.principal WHERE  folio='").append(folio).append("'");
          ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
            ResultInteger result = (ResultInteger)qr.query(sql.toString(), rsh);
          sql = new StringBuilder();
            
          sql= new StringBuilder();
              sql.append(" INSERT INTO  formularios.autorizacion(folio,fecha_cambio) ");             
              sql.append(" VALUES(?,?) ");
              Object[] params = {folio, fecha};
              qr.update(sql.toString(), params);
          
            if (result.getResult() == 5 || result.getResult() == 9) {
                this.DiasCIaA_DiasMaA(user, folio, fecha, result.getResult());
            }          
                  
          return true;
          }catch (SQLException e){
         return false;}
    }
    
    public boolean DiasCIaA_DiasMaA(UserDTO user, String folio, String fecha, int direccion){
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        try {
            Date FechaMia = this.getFormatoDate(user, fecha);
            if (direccion == 5) {
                sql.append("SELECT fecha_recepcion_codigo as result"
                    + " FROM (select fc.folio,"
                    + " fc.consecutivo,"
                    + " fc.fecha_recepcion_codigo,"
                    + " MAX(fc.consecutivo) OVER (PARTITION BY fc.folio) as max_consecutivo"
                    + " FROM formularios.codigo_identificacion fc"
                    + " inner join formularios.principal fp"
                    + " on fc.folio = fp.folio AND fp.folio = '"+folio+"') t"
                    + " WHERE consecutivo = max_consecutivo");
            }
            if (direccion == 9) {
                sql.append("SELECT fecha_recepcion_codigo as result"
                        + " FROM (select fm.folio,"
                        + " fm.consecutivo,"
                        + " fm.fecha_recepcion_codigo,"
                        + " MAX(fm.consecutivo) OVER (PARTITION BY fm.folio) as max_consecutivo"
                        + " FROM formularios.estatus_mia fm"
                        + " inner join formularios.principal fp"
                        + " on fm.folio = fp.folio AND fp.folio = '"+folio+"') t"
                        + " WHERE consecutivo = max_consecutivo");
            }
            ResultSetHandler rshf = new BeanHandler(ResultString.class);
            ResultString resultf = (ResultString)qr.query(sql.toString(), rshf);
            
            Date FechaCF = this.getFormatoDate(user, resultf.getResult());
            
            sql= new StringBuilder();
            sql.append("SELECT  formularios.contdia('").append(FechaCF).append("','").append(FechaMia).append("') AS result");
            ResultSetHandler rsh=new BeanHandler(ResultInteger.class);
            ResultInteger result = (ResultInteger)(qr.query(sql.toString(), rsh));
            result.getResult();              

            sql= new StringBuilder();
            sql.append("SELECT  diasinternos AS result");
            sql.append(" FROM formularios.principal ");
            sql.append(" WHERE folio='").append(folio).append("'");
            rsh=new BeanHandler(ResultInteger.class);
            ResultInteger result2 = (ResultInteger)(qr.query(sql.toString(), rsh));
            if(result2.getResult()==0 )
               {
            sql= new StringBuilder();
            sql.append("Update formularios.principal SET diasinternos=").append(result.getResult()).append("-1");
            sql.append(" WHERE folio='").append(folio).append("'");

               }
            else
            {             
            sql= new StringBuilder();
            sql.append("Update formularios.principal SET diasinternos=diasinternos+").append(result.getResult()).append("-1");
            sql.append(" WHERE folio='").append(folio).append("'");
            }
           qr.update(sql.toString());

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    //Codigo  Mike Ventanilla 
    //
    //
    
    //Codigo mike para  calculo de fechas de dictaminacion 
    public void getDiasDictaminacion(UserDTO user, String folio,String fecha_envio_Tecnico,String fecha_envio_Juridico,String fecha_envio_ambiental,
          String fecha_recepcion_Tecnico,String fecha_recepcion_Juridico,String fecha_recepcion_ambiental )
 {
         Date FechaMenor;
         Date FechaMayor;
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
    try {
                        
            Date fechaEnvioTecnico = this.getFormatoDate(user, fecha_envio_Tecnico);
            Date fechaEnvioJuridico = this.getFormatoDate(user, fecha_envio_Juridico);
            Date fechaEnvioAmbiental = this.getFormatoDate(user, fecha_envio_ambiental);
            Date fechaRecepcionTecnico = this.getFormatoDate(user, fecha_recepcion_Tecnico);
            Date fechaRecepcionJuridico = this.getFormatoDate(user, fecha_recepcion_Juridico);
            Date fechaRecepcionAmbiental = this.getFormatoDate(user, fecha_recepcion_ambiental);
            
            if(fechaEnvioTecnico.before(fechaEnvioJuridico))
                {
                FechaMenor=fechaEnvioTecnico;
                }
            else
               FechaMenor=fechaEnvioJuridico; 
            if(fechaEnvioAmbiental.before(FechaMenor))
                FechaMenor=fechaEnvioAmbiental;
            
            
            if(fechaRecepcionTecnico.after(fechaRecepcionJuridico))
                {
                FechaMayor=fechaRecepcionTecnico;
                }
            else
               FechaMayor=fechaRecepcionJuridico; 
            if(fechaRecepcionAmbiental.after(FechaMayor))
                FechaMayor=fechaRecepcionAmbiental;
            
              sql= new StringBuilder();
              sql.append("SELECT  formularios.contdia('").append(FechaMenor).append("','").append(FechaMayor).append("') AS result");
              ResultSetHandler rsh=new BeanHandler(ResultInteger.class);
              ResultInteger result = (ResultInteger)(qr.query(sql.toString(), rsh));
              result.getResult();              
              
              sql= new StringBuilder();
              sql.append("SELECT  diasinternos AS result");
              sql.append(" FROM formularios.principal ");
              sql.append(" WHERE folio='").append(folio).append("'");
              rsh=new BeanHandler(ResultInteger.class);
              ResultInteger result2 = (ResultInteger)(qr.query(sql.toString(), rsh));
              if(result2.getResult()==0 )
                 {
              sql= new StringBuilder();
              sql.append("Update formularios.principal SET diasinternos=").append(result.getResult()).append("-1");
              sql.append(" WHERE folio='").append(folio).append("'");
                 
                 }
              else
              {             
              sql= new StringBuilder();
              sql.append("Update formularios.principal SET diasinternos=diasinternos+").append(result.getResult()).append("-1");
              sql.append(" WHERE folio='").append(folio).append("'");
              }
           qr.update(sql.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }     
 
 
 
 }

public Date getFormatoDate(UserDTO user, String fecha) throws SQLException
 {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dddd");
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        
        sql.append("select formularios.confecha('").append(fecha).append("') as fecha");
        ResultSetHandler rsh = new BeanHandler(DateDTO.class);
        DateDTO Fecha = (DateDTO)qr.query(sql.toString(), rsh);
 
 return Fecha.getFecha();
 }




public int getDias(UserDTO user, String fechaInicial, String fechaFinal) throws SQLException
{
   
   DataSource ds = PoolDataSource.getDataSource(user);
   QueryRunner qr = new QueryRunner(ds);
   StringBuilder sql = new StringBuilder();
   int dias=0;
          Date FechaInicial = this.getFormatoDate(user, fechaInicial); 
          Date FechaFinal = this.getFormatoDate(user, fechaFinal); 
       
              sql.append("SELECT  formularios.contdia('").append(FechaInicial).append("','").append(FechaFinal).append("') AS result");
              ResultSetHandler rsh=new BeanHandler(ResultInteger.class);
              ResultInteger result = (ResultInteger)(qr.query(sql.toString(), rsh));
              dias=result.getResult();              
   return dias;
}
        
    
    //Fin codigo Mike 
    
}
