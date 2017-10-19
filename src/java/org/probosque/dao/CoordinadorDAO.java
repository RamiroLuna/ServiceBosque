package org.probosque.dao;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import org.probosque.dto.PanelCoordinadorDTO;
import org.probosque.dto.PanelPrincipalCoordinadorDTO;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.SeguimientoInspeccionesDTO;
import org.probosque.dto.UserDTO;

public class CoordinadorDAO extends AbogadoDAO{

    public PanelPrincipalCoordinadorDTO getPanelPrincipalCoordinador(UserDTO user) throws SQLException
      {
      PanelPrincipalCoordinadorDTO pP= new PanelPrincipalCoordinadorDTO();
      pP.setEnTermino(this.getPanel(user, 0));
      pP.setEnProceso(this.getPanel(user,2));
      pP.setRetraso(this.getPanel(user,4));
      pP.setVencido(this.getPanel(user, 6));
      return pP;
      }
    
    public List<PanelCoordinadorDTO> getPanel(UserDTO dto, int tiempo) throws SQLException {
      DataSource ds = PoolDataSource.getDataSource(dto);
      QueryRunner qr = new QueryRunner(ds);
      StringBuilder sql = new StringBuilder(); 
     sql.append(" SELECT \"Folio\", \"No_expediente\", inspeccion.estatus, \"Fecha_recepcion_abogado\", \n "); 
      sql.append(" \"Finalizado\",estatus_reforestacion, catalogos.user.descripcion usuario,\n");
      sql.append(" CASE WHEN  fecha_conclusion IS NULL THEN ' ' ELSE TO_CHAR(FECHA_CONCLUSION, 'DD-MM-YYYY') END AS fecha_conclusion, ");
      sql.append(" CASE WHEN MULTA IS NULL THEN '0' ELSE  TRIM(' ' FROM to_CHAR(MULTA ,'999,999,999,999.99')) END AS MULTA, ");
      sql.append(" CASE WHEN estatus_reforestacion ='AMARILLO' OR estatus_reforestacion ='NARANJA'  THEN estatus_numerico_reforestacion ELSE ' ' end as estatus_oficialia, ");
      sql.append(" CASE WHEN estatus_reforestacion ='ROJO' OR estatus_reforestacion ='VERDE'  THEN estatus_numerico_reforestacion ELSE ' ' end as estatus_reforestacion, ");
      sql.append(" CASE WHEN OBSERVACIONES_PUBLICAS IS NULL THEN ' ' ELSE OBSERVACIONES_PUBLICAS END, ");
      sql.append(" CASE WHEN DECOMISO IS NULL THEN ' ' ELSE DECOMISO END AS   DECOMISO,\n case ");
       
      
      if(tiempo==2){
           sql.append("  when (tiempo is null or tiempo='2') and \"Finalizado\"=false then 'EN PROCESO'  \n" );
         }
      else 
          if(tiempo==4){
      sql.append(" WHEN tiempo='4' and \"Finalizado\"=false  THEN 'RETRASO' \n" );
          }
      else
         if(tiempo==6){  
      sql.append(" WHEN  tiempo='6' and \"Finalizado\"=false  THEN 'VENCIDO' \n" );
         }
      else
             if(tiempo==0)
             {
      sql.append(" WHEN tiempo='0'  THEN 'EN TERMINO' ");
             }
      
      sql.append(" END as contenedor \n" );
      
      sql.append(" FROM formularios.inspeccion\n"
              + "inner join catalogos.user on formularios.inspeccion.usuario= catalogos.user.id" );
          sql.append(" LEFT JOIN FORMULARIOS.ESTATUS_REFORESTACION ef ON  "
                  + "formularios.inspeccion.\"No_expediente\"= ef.no_expediente ");
      sql.append("  WHERE tiempo='").append(String.valueOf(tiempo)).append("' and \"Finalizado\"=false ");
      //Condicion para no mostrar los expedientes con estatus reforestacion
      sql.append(" AND (estatus_reforestacion !='ROJO' AND estatus_reforestacion !='VERDE') ");
      sql.append("AND  ef.no_expediente IS NULL");
      //
      sql.append(" order by \"Fecha_recepcion_abogado\" asc ");      
      ResultSetHandler rsh = new BeanListHandler(PanelCoordinadorDTO.class);
      List<PanelCoordinadorDTO> panel =(List<PanelCoordinadorDTO>)qr.query(sql.toString(), rsh);
      return panel;
       }
    
    public Object terminarExpediente(UserDTO user, String noExpediente) throws SQLException {

       DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder();
       sql.append(" UPDATE  FORMULARIOS.INSPECCION SET \"Finalizado\" =true and tiempo='0'");
       sql.append(" WHERE \"No_expediente\"= '").append(noExpediente).append("' ");
       qr.update(sql.toString());
       return true;
    }

    public Object getSeguimiento(UserDTO user) throws SQLException {
       DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder();
       sql.append(" SELECT iNSPECCION.\"No_expediente\" as no_expediente, \n" +
"CASE WHEN catalogos.ACTAS.descripcion  IS NULL THEN ' '\n" +
"    ELSE catalogos.ACTAS.descripcion END AS ACTA_inspeccion,\n" +
"CASE WHEN catalogos.ACTAS.num_orden  IS NULL THEN ' '\n" +
"		ELSE catalogos.ACTAS.num_orden END AS orden_inspeccion,\n" +
" CASE WHEN catalogos.actas.infractor IS NULL THEN ' ' ELSE catalogos.actas.infractor END as inspeccionado ,\n" +
"CASE WHEN CATALOGOS.ACTAS.CLAVE_UNICA LIKE('IA%') THEN 'ASERRADERO' \n" +
"     WHEN CATALOGOS.ACTAS.CLAVE_UNICA LIKE('IT%') THEN 'TARIMERA' \n" +
"     WHEN CATALOGOS.ACTAS.CLAVE_UNICA LIKE('IM%') THEN 'MADERERÍA'\n" +
"  ELSE \n" +
"     'PREDIO'\n" +
"    END AS TIPO,\n" +
" catalogos.user.descripcion as abogado,\n" +
"CASE WHEN inspeccion.estatus IS NULL  THEN 'EN PROCESO' \n  "+                             
" WHEN inspeccion.estatus LIKE('%10%') THEN 'EN TERMINO DE 5 DIAS' \n  "+                             
" WHEN inspeccion.estatus LIKE('%11%') THEN 'PRIMER DICTAMEN' \n  "+                                            
" WHEN inspeccion.estatus LIKE('%12%') THEN 'PARA EMPLAZAR' \n  "+                             
" WHEN inspeccion.estatus LIKE('%13%') THEN 'EMPLAZADO 15 DIAS' \n  "+                                            
" WHEN inspeccion.estatus LIKE('%14%') THEN 'ANÁLISIS DE DOCUMENTACION 2DO DICTAMEN' \n  "+                                          
" WHEN inspeccion.estatus LIKE('%15%') THEN 'ALEGATOS' \n  "+          
" WHEN inspeccion.estatus LIKE('%16%') THEN 'PARA EMITIR RESOLUCION' \n  "+                             
" WHEN inspeccion.estatus LIKE('%17%') THEN 'EN PROCESO ->RESOLUCIÓN ADMINISTRATIVA' \n  "+                             
" WHEN inspeccion.estatus LIKE('%18%') THEN 'PARA NOTIFICAR' \n  "+                             
" WHEN inspeccion.estatus LIKE('%19%') THEN 'RESOLUCIÓN NOTIFICADA' \n  "+                                            
               
" WHEN inspeccion.estatus LIKE('%20%') THEN 'RESOLUCIÓN ADMINISTRATIVA' \n  "+              
" WHEN inspeccion.Estatus LIKE('%27%') THEN 'ACUERDO DE CIERRE' \n  "+ 
" WHEN inspeccion.estatus LIKE('%28%') THEN 'ACUERDO DE ARCHIVO' \n  "+              
" WHEN inspeccion.estatus LIKE('%29%') THEN 'ACUERDO DE ARCHIVO Y DECOMISO' \n  "+
" ELSE 'EN PROCESO' "             + 
" END  as resolucion_admin,\n " +
" CASE WHEN INSPECCION.multa is null THEN  '$ 0.0' \n" +
"   ELSE \n" +
"      '$ '|| TRIM(' ' FROM to_CHAR(MULTA ,'999,999,999,999.99')) END  as cantidad,\n" +
"CASE WHEN formularios.inspeccion.fecha_conclusion is null THEN ' ' \n" +
"  ELSE \n" +
"     to_char(formularios.inspeccion.fecha_conclusion,'dd/mm/yyyy')\n" +
"    END AS fecha_conclusion,\n" +
"    case when estatus_reforestacion.color_semaforo is null then     \n" +
"            inspeccion.estatus_reforestacion \n" +
"                        else estatus_reforestacion.color_semaforo end as sancion, " +
" CASE WHEN inspeccion.estatus_reforestacion='VERDE' OR inspeccion.estatus_reforestacion='ROJO' "+
" THEN 'TERMINADO-PUBLICADO' ELSE 'NO TERMINADO' END AS TERMINADO "+    
" from formularios.inspeccion\n" +
" INNER JOIN CATALOGOS.ACTAS ON formularios.inspeccion.\"Folio\"=CATALOGOS.ACTAS.ID \n" +
" left JOIN catalogos.user ON formularios.inspeccion.usuario=catalogos.user.id ");
 sql.append(" left JOIN formularios.estatus_reforestacion ON formularios.inspeccion.\"No_expediente\"=estatus_reforestacion.no_expediente  ");      
              sql.append(" WHEre ").append(this.getNotLike(this.getListaEstatus(37, 38)));
        sql.append(" AND inspeccion.ESTATUS NOT  LIKE ('%,41') ");
        sql.append(" AND inspeccion.estatus NOT LIKE('41') ");
        sql.append(" AND inspeccion.estatus NOT LIKE('41,%') ");
        sql.append(" AND inspeccion.estatus NOT LIKE('%,41,%') ");
        
                      ResultSetHandler rsh = new BeanListHandler(SeguimientoInspeccionesDTO.class);
                    List<SeguimientoInspeccionesDTO> seguimiento=(List<SeguimientoInspeccionesDTO>)qr.query(sql.toString(), rsh);
                    return seguimiento;
    }

    public Object getConcluidas(UserDTO user) throws SQLException {
    
    DataSource ds = PoolDataSource.getDataSource(user);
    QueryRunner qr = new QueryRunner(ds);
    StringBuilder sql = new StringBuilder();
    AbogadoDAO dao= new AbogadoDAO();
    sql.append(" SELECT \"Folio\", \"No_expediente\", inspeccion.estatus,TO_CHAR(\"Fecha_recepcion_abogado\",'dd-mm-yyyy') as Fecha_recepcion_abogado, \n");
    sql.append(" CASE WHEN  fecha_conclusion IS NULL THEN ' ' ELSE TO_CHAR(FECHA_CONCLUSION, 'DD-MM-YYYY') END AS fecha_conclusion, ");
    sql.append(" CASE WHEN MULTA IS NULL THEN '0' ELSE  TRIM(' ' FROM to_CHAR(MULTA ,'999,999,999,999.99')) END AS MULTA, ");
    sql.append(" CASE WHEN estatus_reforestacion.estatus is null then  estatus_numerico_reforestacion else estatus_reforestacion.estatus end as estatus_reforestacion, ");
    sql.append(" CASE WHEN OBSERVACIONES_PUBLICAS IS NULL THEN ' ' ELSE OBSERVACIONES_PUBLICAS END, ");
    sql.append(" CASE WHEN DECOMISO IS NULL THEN ' ' ELSE DECOMISO END AS   DECOMISO, ");
    sql.append(" catalogos.user.descripcion as usuario ");
     
    sql.append(" FROM FORMULARIOS.INSPECCION \n");
    sql.append(" left JOIN catalogos.user ON formularios.inspeccion.usuario=catalogos.user.id ");
    sql.append(" left JOIN formularios.estatus_reforestacion ON formularios.inspeccion.\"No_expediente\"=estatus_reforestacion.no_expediente  ");      
    sql.append(" WHERE coordinador='COORDINADOR' ");
    sql.append(" AND  ").append(this.getNotLike(this.getListaEstatus(37, 38)));
        sql.append(" AND inspeccion.ESTATUS NOT  LIKE ('%,41') ");
        sql.append(" AND inspeccion.estatus NOT LIKE('41') ");
        sql.append(" AND inspeccion.estatus NOT LIKE('41,%') ");
        sql.append(" AND inspeccion.estatus NOT LIKE('%,41,%') ");
          
    sql.append(" ORDER BY \"Fecha_recepcion_abogado\" DESC, \"No_expediente\" ASC ");
    ResultSetHandler  rsh = new BeanListHandler(PanelCoordinadorDTO.class);
    List<PanelCoordinadorDTO> panelConcluidas= (List<PanelCoordinadorDTO>) qr.query(sql.toString(),rsh);
    return panelConcluidas;
        
    }

    public Object setImpugnar(UserDTO user, String noExpediente, String estatus) throws SQLException {
      DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder();
       sql.append(" UPDATE  FORMULARIOS.INSPECCION SET estatus=estatus ||','||'").append(estatus).append("' ");
       sql.append(" WHERE btrim(\"No_expediente\") = '").append(noExpediente).append("' ");
       qr.update(sql.toString());
       return true;
    }
    
    public List<PanelCoordinadorDTO> getImpugnaciones(UserDTO user) throws SQLException {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT \"Folio\", \"No_expediente\", inspeccion.estatus, \n ");
        sql.append(" catalogos.user.descripcion usuario, \n");
        sql.append(" CASE WHEN  fecha_conclusion IS NULL THEN ' ' ELSE TO_CHAR(FECHA_CONCLUSION, 'DD-MM-YYYY') END AS fecha_conclusion, ");
        sql.append(" CASE WHEN MULTA IS NULL THEN '0' ELSE  TRIM(' ' FROM to_CHAR(MULTA ,'999,999,999,999.99')) END AS multa, ");
        sql.append(" CASE WHEN ef.estatus  is null then  estatus_numerico_reforestacion else ef.estatus end as estatus_reforestacion, ");
        sql.append(" CASE WHEN OBSERVACIONES_PUBLICAS IS NULL THEN ' ' ELSE OBSERVACIONES_PUBLICAS END, ");
        sql.append(" CASE WHEN DECOMISO IS NULL THEN ' ' ELSE DECOMISO END AS   DECOMISO  ");
        sql.append(" FROM formularios.inspeccion ");
        sql.append(" inner join catalogos.user on formularios.inspeccion.usuario= catalogos.user.id \n");
        sql.append(" LEFT JOIN FORMULARIOS.ESTATUS_REFORESTACION ef ON  "
                 + "formularios.inspeccion.\"No_expediente\"= ef.no_expediente ");
    
        sql.append(" WHEre ").append(this.getLike(this.getListaEstatus(37, 38)));
        sql.append(" or inspeccion.ESTATUS  LIKE ('%,41') ");
        sql.append(" OR inspeccion.estatus LIKE('41') ");
        sql.append(" OR inspeccion.estatus LIKE('41,%') ");
        sql.append(" OR inspeccion.estatus LIKE('%,41,%') ");
        ResultSetHandler rsh= new BeanListHandler(PanelCoordinadorDTO.class);
        List<PanelCoordinadorDTO> impugnacion=(List<PanelCoordinadorDTO>) qr.query(sql.toString(), rsh);
        return impugnacion;
    }

    public void reponer(UserDTO user, String noExpediente) throws SQLException {
      DataSource ds = PoolDataSource.getDataSource(user);
      QueryRunner qr = new QueryRunner(ds);
      StringBuilder sql = new StringBuilder();
       sql.append(" UPDATE  formularios.inspeccion SET ESTATUS=REPLACE(ESTATUS,',37','') ");
       sql.append("WHERE inspeccion.\"No_expediente\"='").append(noExpediente).append("'; ");
       sql.append(" UPDATE  formularios.inspeccion SET ESTATUS=REPLACE(ESTATUS,',38','') ");
       sql.append("WHERE inspeccion.\"No_expediente\"='").append(noExpediente).append("'; ");
       sql.append(" UPDATE  formularios.inspeccion SET ESTATUS=REPLACE(ESTATUS,'37','') ");
       sql.append("WHERE inspeccion.\"No_expediente\"='").append(noExpediente).append("'; ");
       sql.append(" UPDATE  formularios.inspeccion SET ESTATUS=REPLACE(ESTATUS,'38','') ");
       sql.append("WHERE inspeccion.\"No_expediente\"='").append(noExpediente).append("'; ");
       sql.append(" UPDATE  formularios.inspeccion SET ESTATUS=REPLACE(ESTATUS,',41','') ");
       sql.append("WHERE inspeccion.\"No_expediente\"='").append(noExpediente).append("'; ");
       sql.append(" UPDATE  formularios.inspeccion SET ESTATUS=REPLACE(ESTATUS,',,' , ',') ");
       sql.append("WHERE inspeccion.\"No_expediente\"='").append(noExpediente).append("'; ");
       qr.update(sql.toString());
     
    }

    public void changeExpediente(UserDTO user, String noExpedienteNuevo, String noExpedienteViejo) throws SQLException {
       DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder();
       sql.append(" UPDATE  FORMULARIOS.INSPECCION SET \"No_expediente\" ='").append(noExpedienteNuevo).append("'");
       sql.append(" WHERE \"No_expediente\"= '").append(noExpedienteViejo).append("' ");
       qr.update(sql.toString());
       
    }

 public String getPendientesOficialia(UserDTO user) throws SQLException {
      DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       StringBuilder sql = new StringBuilder();
       sql.append("  SELECT count(*) as result\n" +
                  " FROM CATALOGOS.INSPECCIONES \n" +
                  " LEFT JOIN FORMULARIOS.INSPECCION ON CATALOGOS.INSPECCIONES.ID=FORMULARIOS.INSPECCION.\"Folio\" \n" +
                  " LEFT JOIN CATALOGOS.USER ON FORMULARIOS.INSPECCION.USUARIO=CATALOGOS.USER.ID \n" +
                  " WHERE FORMULARIOS.INSPECCION.ESTATUS IS NULL\n" +
                  " ");
    ResultSetHandler  rsh = new BeanHandler(ResultInteger.class);
    ResultInteger result= (ResultInteger) qr.query(sql.toString(),rsh);
       
       return String.valueOf(result.getResult());
    }
        
    
}
