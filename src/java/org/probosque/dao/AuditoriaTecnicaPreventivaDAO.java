package org.probosque.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.AuditoriaDTO;

public class AuditoriaTecnicaPreventivaDAO {

    public void setAuditoria(String idPredio, String auditoria, String fechaAuditoria)
            throws SQLException {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO FORMULARIOS.AUDITORIA(CONSECUTIVO, FOLIO, AUDITORIA_TECNICA, FECHA_ATP) ");
        sql.append(" SELECT CASE WHEN MAX(consecutivo) IS NULL OR MAX(consecutivo)=0 THEN 1 ");
        sql.append(" ELSE MAX (consecutivo)+1 END,'").append(idPredio).append("','").
                append(auditoria).append("', '").append(fechaAuditoria).append("' FROM FORMULARIOS.AUDITORIA").
                append(" WHERE folio='").append(idPredio).append("' ").
                append("and 0=(Select case when count(*) is null or count(*)=0 then 0 else count(*) end  as existe ").
                append("from FORMULARIOS.AUDITORIA  ").
                append("where to_char(FECHA_ATP,'dd/mm/yyyy')='").append(fechaAuditoria).
                append("' and FOLIO='").append(idPredio).append("') ");
              
        qr.update(sql.toString());
    }

    public void updateAuditoria(Integer consecutivo,String idPredio, String auditoriaTecnica, String fechaAuditoria)
            throws SQLException {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE  FORMULARIOS.AUDITORIA ").
                append("SET AUDITORIA_TECNICA = '").append(auditoriaTecnica);
        if(fechaAuditoria.isEmpty())
              {
               sql.append("', fecha_atp= null ");
              }
        else
            {
                sql.append("', fecha_atp='").
               append(fechaAuditoria).append("'  ");
            }
                sql.append(" WHERE consecutivo=").append(consecutivo).append(" and folio='").
                append(idPredio).append("' ");
        qr.update(sql.toString());
    }

    public void deleteAuditoria(Integer consecutivo, String idPredio)
            throws SQLException {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM   FORMULARIOS.AUDITORIA ").
                append(" WHERE consecutivo=").append(consecutivo).append(" and folio='").
                append(idPredio).append("' ");
        qr.update(sql.toString());
    }

    public ArrayList<AuditoriaDTO> getAuditorias(String idPredio)
            throws SQLException {
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CONSECUTIVO, folio  \"idPredio\", ").
                append(" CASE WHEN  auditoria_tecnica = '1' THEN true ELSE false END   as \"auditoriaTecnica\",  ").
                append(" CASE WHEN fecha_atp IS NULL THEN '' ELSE to_char(fecha_atp,'dd/mm/yyyy ') END \"fechaATP\" ").
                append(" FROM formularios.auditoria ").
                append(" WHERE FOLIO='").append(idPredio).append("' ").
                append(" ORDER BY CONSECUTIVO DESC ");
        ResultSetHandler rsh = new BeanListHandler(AuditoriaDTO.class);
        ArrayList<AuditoriaDTO> auditorias = (ArrayList<AuditoriaDTO>) qr.query(sql.toString(), rsh);
        return auditorias;
    }
    
}
