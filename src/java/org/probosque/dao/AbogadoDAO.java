package org.probosque.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.ContenedorEstatusAbogado;
import org.probosque.dto.HistorialObservacionesDTO;
import org.probosque.dto.PanelAbogadoDTO;
import org.probosque.dto.UserDTO;

public class AbogadoDAO extends OficialiaDAO {
    
private List<String> estatus = new ArrayList<>();
    
    public Object getPanel(UserDTO user)  throws SQLException  {
       DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder(); 
       PanelAbogadoDTO panel = new PanelAbogadoDTO();
       sql.append(" SELECT \"Folio\", \"No_expediente\", inspeccion.estatus,TO_CHAR(\"Fecha_recepcion_abogado\",'dd-mm-yyyy') as Fecha_recepcion_abogado,\n");
       sql.append(" CASE WHEN  fecha_conclusion IS NULL THEN ' ' ELSE TO_CHAR(FECHA_CONCLUSION, 'DD-MM-YYYY') END AS fecha_conclusion, ");
       sql.append(" CASE WHEN MULTA IS NULL THEN '0' ELSE '$'|| TRIM(' ' FROM to_CHAR(MULTA ,'999,999,999,999.99')) END AS MULTA, ");
       sql.append(" CASE WHEN DECOMISO IS NULL THEN ' ' ELSE DECOMISO END AS   DECOMISO ");
       sql.append(" FROM FORMULARIOS.INSPECCION \n");
       sql.append(" LEFT JOIN FORMULARIOS.ESTATUS_REFORESTACION ef ON  "
                  +" formularios.inspeccion.\"No_expediente\"= ef.no_expediente ");
       
       sql.append(" WHERE usuario=").append(user.getId());
       sql.append(" AND estatus_reforestacion = 'AMARILLO' AND COORDINADOR='ABOGADO'  \n");
       sql.append(" AND  ef.no_expediente IS NULL ");
       sql.append(" ORDER BY \"Fecha_recepcion_abogado\" DESC, \"No_expediente\" ASC ");
       ResultSetHandler rsh = new BeanListHandler(ContenedorEstatusAbogado.class);
       List<ContenedorEstatusAbogado> panelEnProceso= (List<ContenedorEstatusAbogado>) qr.query(sql.toString(),rsh);
       if(panelEnProceso!=null)
          {
           panel.setContenedorEnProceso(panelEnProceso);
          }
       sql = new StringBuilder(); 
       sql.append(" SELECT \"Folio\", \"No_expediente\", inspeccion.estatus,TO_CHAR(\"Fecha_recepcion_abogado\",'dd-mm-yyyy') as Fecha_recepcion_abogado, \n");
       sql.append(" CASE WHEN  fecha_conclusion IS NULL THEN ' ' ELSE TO_CHAR(FECHA_CONCLUSION, 'DD-MM-YYYY') END AS fecha_conclusion, ");
       sql.append(" CASE WHEN MULTA IS NULL THEN '0' ELSE '$'|| TRIM(' ' FROM to_CHAR(MULTA ,'999,999,999,999.99')) END AS MULTA, ");
       sql.append(" CASE WHEN DECOMISO IS NULL THEN ' ' ELSE DECOMISO END AS   DECOMISO ");
       sql.append(" FROM FORMULARIOS.INSPECCION \n");
        sql.append(" LEFT JOIN FORMULARIOS.ESTATUS_REFORESTACION ef ON  "
                 +" formularios.inspeccion.\"No_expediente\"= ef.no_expediente ");
       sql.append(" WHERE usuario=").append(user.getId());
       sql.append(" AND estatus_reforestacion = 'NARANJA' AND COORDINADOR='ABOGADO'  ");
       sql.append(" ORDER BY \"Fecha_recepcion_abogado\" DESC, \"No_expediente\" ASC ");
       rsh = new BeanListHandler(ContenedorEstatusAbogado.class);
       List<ContenedorEstatusAbogado> panelSancion= (List<ContenedorEstatusAbogado>) qr.query(sql.toString(),rsh);
       if(panelSancion!=null)
          {
          panel.setContenedorMedidaSeguridad(panelSancion);
          }
       return panel;        
    }   
     public Boolean addObservacion(UserDTO user, String observacion, String fecha, String No_expediente, Object estatus) throws SQLException, ParseException {
       DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder(); 
       Calendar horas = new GregorianCalendar();
        int hora = horas.get(Calendar.HOUR_OF_DAY);
        int minuto = horas.get(Calendar.MINUTE);
        int segundo = horas.get(Calendar.SECOND);
        
       Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fecha.replace("-","/")+" UTC");
       date.setHours(hora);
       date.setMinutes(minuto);
       date.setSeconds(segundo);
       String fechaYMD=String.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date));
       
        
       sql.append(" INSERT INTO FORMULARIOS.HISTORIAL (\"ID\", \"User\", \"Fecha\", \"Observacion\", \"Expediente\", \"Estatus\") ");
       sql.append(" select  case when MAX(\"ID\")+1 is null THEN 1 ELSE MAX(\"ID\")+1 END,").append(user.getId()).append(",");
       sql.append("'").append(fechaYMD).append("', '").append(observacion);
       sql.append("','").append(No_expediente).append("','").append(estatus).append("' ");
       sql.append(" FROM FORMULARIOS.HISTORIAL  ");
       qr.update(sql.toString());
       return true;
    }
public Boolean changeUser(UserDTO user, String noExpediente, String usuario) throws SQLException {
       DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder(); 
       
       sql.append(" UPDATE FORMULARIOS.INSPECCION SET coordinador='").append(usuario).append("' ");
       sql.append( "WHERE \"No_expediente\"='").append(noExpediente).append("' ");
       qr.update(sql.toString());
       return true;    
    }
    public Object getObservaciones(UserDTO user, String noExpediente) throws SQLException
    {
    DataSource ds = PoolDataSource.getDataSource(user);
    QueryRunner qr = new QueryRunner(ds);
    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT id,CATALOGOS.USER.descripcion AS user, TO_CHAR(\"Fecha\",'dd-mm-YYYY HH24:mm') as fecha ,\"Observacion\" as observacion ");
    sql.append(" FROM formularios.historial ");    
    sql.append(" INNER JOIN CATALOGOS.USER ON FORMULARIOS.HISTORIAL.\"User\"=CATALOGOS.USER.ID ");
    sql.append(" WHERE \"Expediente\"= '").append(noExpediente).append("' ");
    sql.append(" ORDER BY   \"Fecha\" DESC ");
    ResultSetHandler rsh=new BeanListHandler(HistorialObservacionesDTO.class);
    List<HistorialObservacionesDTO> historial = (List<HistorialObservacionesDTO>)qr.query(sql.toString(), rsh);
    return historial;
    }
    
    public Object getConcluidas(UserDTO user) throws SQLException
    {
    DataSource ds = PoolDataSource.getDataSource(user);
    QueryRunner qr = new QueryRunner(ds);
    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT \"Folio\", \"No_expediente\", estatus,TO_CHAR(\"Fecha_recepcion_abogado\",'dd-mm-yyyy') as Fecha_recepcion_abogado, \n");
    sql.append(" CASE WHEN  fecha_conclusion IS NULL THEN ' ' ELSE TO_CHAR(FECHA_CONCLUSION, 'DD-MM-YYYY') END AS fecha_conclusion, ");
    sql.append(" CASE WHEN MULTA IS NULL THEN '0' ELSE  TRIM(' ' FROM to_CHAR(MULTA ,'999,999,999,999.99')) END AS MULTA, ");
    sql.append(" CASE WHEN DECOMISO IS NULL THEN ' ' ELSE DECOMISO END AS   DECOMISO ");
    
    sql.append(" FROM FORMULARIOS.INSPECCION \n");
    sql.append(" WHERE usuario=").append(user.getId());
    sql.append(" AND (").append(getLike(this.getListaEstatus(20, 29))).append(") ");
    sql.append(" AND COORDINADOR='COORDINADOR'");
    sql.append(" ORDER BY \"Fecha_recepcion_abogado\" DESC, \"No_expediente\" ASC ");
    ResultSetHandler  rsh = new BeanListHandler(ContenedorEstatusAbogado.class);
    List<ContenedorEstatusAbogado> panelConcluidas= (List<ContenedorEstatusAbogado>) qr.query(sql.toString(),rsh);
    return panelConcluidas;
    }
  public boolean InsertStatus(UserDTO user, Object estatus, String noExpediente, String fechaConclusion, float multa, String decomiso) throws SQLException, ParseException
      {
       DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder();
       sql.append(" UPDATE  FORMULARIOS.INSPECCION SET estatus = '").append(estatus).append("', ");
       sql.append("multa=ROUND(").append(multa).append(",2), decomiso='").append(decomiso).append("' ");
       if(!fechaConclusion.equals(" "))
          {
            Date date = new SimpleDateFormat("dd/mm/yyyy").parse(fechaConclusion.replace("-","/")+" UTC");
            String fechaYMD=String.valueOf(new SimpleDateFormat("yyyy/mm/dd").format(date));
          sql.append(", fecha_conclusion='").append(fechaYMD).append("' ");
          }
       sql.append(" WHERE \"No_expediente\" = '").append(noExpediente).append("' ");
       qr.update(sql.toString());
       return true;
        
      }  
public String  getLike(List<String> estatus)
     {
         
      StringBuilder like= new StringBuilder();
      
      int i=1;
      for(String status:estatus)
      {
      
      like.append("inspeccion.ESTATUS  LIKE ('%,").append(status).append("') ");
      like.append(" OR inspeccion.estatus LIKE('").append(status).append("') ");
      like.append(" OR inspeccion.estatus LIKE('").append(status).append(",%') ");
      like.append(" OR inspeccion.estatus LIKE('%,").append(status).append(",%') ");
         if(i<estatus.size())
           {
           like.append(" OR ");
           }
         i++; 
      }  
      return like.toString();
     }

public String  getNotLike(List<String> estatus)
     {
         
      StringBuilder like= new StringBuilder();
      
      int i=1;
      for(String status:estatus)
      {
      
      like.append(" inspeccion.ESTATUS  NOT LIKE ('%,").append(status).append("') ");
      like.append(" AND inspeccion.estatus NOT LIKE('").append(status).append("') ");
      like.append(" AND inspeccion.estatus NOT LIKE('").append(status).append(",%') ");
      like.append(" AND inspeccion.estatus NOT LIKE('%,").append(status).append(",%') ");
         if(i<estatus.size())
           {
           like.append(" and ");
           }
         i++; 
      }  
      return like.toString();
     }

public List<String> getListaEstatus(int inicio, int fin) 
{
    estatus= new ArrayList<>(); 
    for (int i=inicio; i<=fin; i++)
            {
            estatus.add(String.valueOf(i));
            }
return estatus;

}
  

    
}
