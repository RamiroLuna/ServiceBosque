package org.probosque.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.CatalogoDTO;
import org.probosque.dto.PanelOficialiaDTO;
import org.probosque.dto.ResultBoolean;
import org.probosque.dto.ResultString;
import org.probosque.dto.UserDTO;

public class OficialiaDAO {

public OficialiaDAO(){}    
    
    public List<PanelOficialiaDTO> getInspecionesIndustria(UserDTO user ) throws SQLException
      {
       DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder(); 
        
             
       sql.append(" SELECT CATALOGOS.INSPECCIONES.ID AS FOLIO, "
               + " CASE WHEN CATALOGOS.INSPECCIONES.DESCRIPCION IS NULL THEN ' ' ELSE CATALOGOS.INSPECCIONES.DESCRIPCION END  AS NO_DOCUMENTO,"
               + " CASE WHEN CATALOGOS.INSPECCIONES.clave_unica IS NULL THEN ' ' ELSE  CATALOGOS.INSPECCIONES.clave_unica END AS CLAVE_UNICA, ");
       sql.append(" CASE WHEN FORMULARIOS.INSPECCION.\"No_expediente\" IS NULL THEN ' ' ELSE FORMULARIOS.INSPECCION.\"No_expediente\" END  AS EXPEDIENTE,");
       sql.append(" FORMULARIOS.INSPECCION.ESTATUS,");
       sql.append(" CASE WHEN CATALOGOS.USER.DESCRIPCION IS NULL THEN ' ' ELSE CATALOGOS.USER.DESCRIPCION END AS ABOGADO");
       sql.append(" FROM CATALOGOS.INSPECCIONES ");
       sql.append(" LEFT JOIN FORMULARIOS.INSPECCION ON CATALOGOS.INSPECCIONES.ID=FORMULARIOS.INSPECCION.\"Folio\" ");
       sql.append(" LEFT JOIN CATALOGOS.USER ON FORMULARIOS.INSPECCION.USUARIO=CATALOGOS.USER.ID ");
       sql.append(" WHERE FORMULARIOS.INSPECCION.ESTATUS IS NULL");
       sql.append(" ORDER BY FOLIO DESC ");
  
       ResultSetHandler rsh = new BeanListHandler(PanelOficialiaDTO.class);
       List<PanelOficialiaDTO> panel= (List<PanelOficialiaDTO>)qr.query(sql.toString(), rsh);
       return panel;
      }

   public List<CatalogoDTO> getUserAbogado( ) throws SQLException
      {
       DataSource ds = PoolDataSource.getDataSourceGeneral();
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder(); 
        
       sql.append(" SELECT ID AS id, 'LIC. '|| UPPER(FIRSTNAME) ||' '||UPPER(LASTNAME)  AS DESCRIPCION ");
       sql.append(" FROM PUBLIC.USER   ") ;
       sql.append(" WHERE PROGRAM=15 AND ACTIVITY=2   ");
       sql.append(" ORDER BY descripcion asc ");
       ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
       List<CatalogoDTO> abogados = (List<CatalogoDTO>) (qr.query(sql.toString(), rsh));
       return abogados;
      }
    
    
   
   
   
   public int validarExpedienteExist(UserDTO user, String expediente) throws SQLException
     {
       DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder();
       
       sql.append(" SELECT count(*) AS RESULT " );
       sql.append(" FROM  FORMULARIOS.INSPECCION ");
       sql.append(" WHERE upper(\"No_expediente\")=upper('").append(expediente).append("') ");
       ResultSetHandler rsh = new BeanHandler(ResultString.class);
       ResultString result = (ResultString)qr.query(sql.toString(), rsh);
      
        return Integer.parseInt(result.getResult());
      
     }
   
   public boolean insertExpediente(UserDTO user, String expediente, String folio ) throws SQLException 
      {
       DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder();
       sql.append(" INSERT INTO FORMULARIOS.INSPECCION(\"No_expediente\", \"Folio\", \"Finalizado\") ");
       sql.append(" VALUES( '").append(expediente).append("', '").append(folio).append("', FALSE) ");
       qr.update(sql.toString());
       return true;
      }
   
   
   
   public boolean InsertAbogado(UserDTO user, int idAbogado, String noExpediente) throws SQLException
      {
       DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder();
       sql.append(" UPDATE  FORMULARIOS.INSPECCION SET USUARIO = ").append(idAbogado);
       sql.append(" WHERE \"No_expediente\" = '").append(noExpediente).append("' ");
       qr.update(sql.toString());
       return true;
        
      }  
   
  public boolean InsertStatus(UserDTO user, Object estatus, String noExpediente) throws SQLException
      {
       DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder();
       sql.append(" UPDATE  FORMULARIOS.INSPECCION SET estatus = '").append(estatus).append("' ");
       sql.append(" WHERE \"No_expediente\" = '").append(noExpediente).append("' ");
       qr.update(sql.toString());
       return true;
        
      }  
   
    
    public static void main(String[] args) {
        try{
        OficialiaDAO dao = new OficialiaDAO();
        UserDTO dto=new UserDTO();
        dto.setProgram(14);
        dto.setRole_id(2);
        dao.getInspecionesIndustria(dto);
        
        }
        catch(Exception ex )
        {
        ex.getMessage();
        }
    }

    public void setEstatusAtendidoP8(UserDTO user, String expediente) throws SQLException {
       DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder();
       sql.append(" SELECT \"Folio\" AS RESULT ");
       sql.append(" FROM FORMULARIOS.INSPECCION ");
       sql.append(" WHERE \"No_expediente\"='").append(expediente).append("' ");
       ResultSetHandler rsh = new BeanHandler(ResultString.class);
       ResultString folio=(ResultString)qr.query(sql.toString(), rsh);
       ds = PoolDataSource.getDbProgram8(true);
       qr = new QueryRunner(ds);
       sql = new StringBuilder();
       sql.append("UPDATE FORMULARIOS.PRINCIPAL SET ESTATUS_JURIDICO=TRUE WHERE FOLIO='").append(folio.getResult()).append("' ");
       qr.update(sql.toString());
    }

    public void setFechaAtencionAbogado(UserDTO user, String expediente) throws SQLException {
       DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder();
       sql.append("UPDATE FORMULARIOS.inspeccion SET \"Fecha_recepcion_abogado\"='").append(this.getFechaActual()).append("' ");
       sql.append(", tiempo=0, COORDINADOR='ABOGADO' WHERE \"No_expediente\"='").append(expediente).append("' ");
       qr.update(sql.toString());
    }
    
     public String getFechaActual() {
        StringBuilder fechaActual= new StringBuilder();
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        fechaActual.append(año).append("-").append(mes+1).append("-").append(dia);
      return fechaActual.toString();
    }    
          public Boolean verificaImpugnacion(UserDTO user, String noExpediente, String estatus) throws SQLException {
      DataSource ds = PoolDataSource.getDataSource(user);
      QueryRunner qr = new QueryRunner(ds);
      StringBuilder sql = new StringBuilder();
       sql.append(" SELECT  CASE WHEN formularios.inspeccion.estatus LIKE '%").append(estatus).append("%' ");
       sql.append(" THEN true ELSE false END AS RESULT ");
       sql.append(" from formularios.inspeccion ");
       sql.append(" WHERE inspeccion.\"No_expediente\"='").append(noExpediente).append("' ");
      ResultSetHandler rsh = new BeanHandler(ResultBoolean.class);
      ResultBoolean encontrado =(ResultBoolean) qr.query(sql.toString(), rsh);
      return encontrado.getResult();
    }        

}
