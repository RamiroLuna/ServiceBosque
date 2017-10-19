package org.probosque.dao;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.EstatusReforestacionDTO;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.ResultString;
import org.probosque.dto.UserDTO;

/**
 *
 * @author Administrador
 */
public class SemaforoReforestacionDAO {


    public Boolean updateSemaforoReforestacion(UserDTO user, String No_expediente, String colorSemaforo, String detalleEstatus, String  estatus_numerico_reforestacion) throws SQLException  
   {
     DataSource ds = PoolDataSource.getDataSource(user);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("UPDATE FORMULARIOS.INSPECCION SET estatus_reforestacion='").append(colorSemaforo).append("', ");
     sql.append(" detalle_estatus='").append(detalleEstatus).append("', ");
     sql.append("estatus_numerico_reforestacion='").append(estatus_numerico_reforestacion).append("' ");
     sql.append(" WHERE \"No_expediente\"='").append(No_expediente).append("' ");
     qr.update(sql.toString());
     return true;   
   }       

public String getDetalleEstatus(UserDTO user, String Estatus) throws SQLException
   {
    DataSource ds = PoolDataSource.getDataSource(user);
    QueryRunner qr = new QueryRunner(ds);
    StringBuilder sql = new StringBuilder();
    StringBuilder detalleEstatus = new StringBuilder();
    sql.append(" SELECT * FROM CATALOGOS.ESTATUS order by id ASC ");
    ResultSetHandler rsh = new BeanListHandler(EstatusReforestacionDTO.class);
    List<EstatusReforestacionDTO> lista = (List<EstatusReforestacionDTO>)qr.query(sql.toString(), rsh);
    int i=1;
    for (String descriptivo : Estatus.split(",") )
          {
           detalleEstatus.append(lista.get(Integer.parseInt(descriptivo)-1).getDescripcion()  );          
           if(i<Estatus.split(",").length)
              {
              detalleEstatus.append(", ");
              }
               i++;
          }
   return detalleEstatus.toString();
   }

public Boolean setObservacionesPublicas(UserDTO user, String Observaciones, String No_expediente) throws SQLException
{
     DataSource ds = PoolDataSource.getDataSource(user);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("UPDATE FORMULARIOS.INSPECCION SET observaciones_publicas='").append(Observaciones).append("' ");
     sql.append(" WHERE \"No_expediente\"='").append(No_expediente).append("' ");
     qr.update(sql.toString());
     return true;   
}


public Boolean getExisteSemaforizacion(UserDTO user, String noExpediente) throws SQLException
 {
     DataSource ds = PoolDataSource.getDataSource(user);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("SELECT COUNT(*) AS result ");
     sql.append("FROM  FORMULARIOS.estatus_reforestacion  ");
     sql.append(" WHERE no_expediente='").append(noExpediente).append("' ");
     ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
     ResultInteger result= (ResultInteger)  qr.query(sql.toString(),rsh);
     Boolean Encontrado=true;
     if(result.getResult()==0)
       {
         Encontrado=false;
       }
 return Encontrado;
 }


public Boolean setTable_estatusReforestacion(UserDTO user, String noExpediente, String estatusNumerico,
        String color, String detalle  ) throws SQLException
  {
     DataSource ds = PoolDataSource.getDataSource(user);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("INSERT INTO FORMULARIOS.estatus_reforestacion (NO_EXPEDIENTE, ESTATUS,COLOR_SEMAFORO, DETALLE_ESTATUS )  ");
     sql.append(" VALUES ('").append(noExpediente).append("', '").append(estatusNumerico).append("', '").append(color).append("', ");
     sql.append(" '").append(detalle).append("') ");
     qr.update(sql.toString());
     return true;   
  }
  

public Boolean setUpdate_Table_estatusReforestacion(UserDTO user, String noExpediente, String estatusNumerico,
               String color, String detalle  ) throws SQLException
  {
     DataSource ds = PoolDataSource.getDataSource(user);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("UPDATE FORMULARIOS.estatus_reforestacion SET ESTATUS='").append(estatusNumerico).append("', ");
     sql.append(" COLOR_SEMAFORO='").append(color).append("', ");
     sql.append(" detalle_estatus='").append(detalle).append("' ");
     sql.append(" WHERE no_expediente='").append(noExpediente).append("' ");
     qr.update(sql.toString());
     return true;   
  }

    public String getEstatus(UserDTO user, String noExpediente) throws SQLException {
     DataSource ds = PoolDataSource.getDataSource(user);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("SELECT estatus AS result ");
     sql.append("FROM  FORMULARIOS.estatus_reforestacion  ");
     sql.append(" WHERE no_expediente='").append(noExpediente).append("' ");
     ResultSetHandler rsh = new BeanHandler(ResultString.class);
     ResultString result= (ResultString)  qr.query(sql.toString(),rsh);
     return result.getResult();
    }
public Boolean deleteNoExpediente(UserDTO user, String noExpediente) throws SQLException
  {
     DataSource ds = PoolDataSource.getDataSource(user);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("DELETE  FROM FORMULARIOS.estatus_reforestacion ");
     sql.append(" WHERE no_expediente='").append(noExpediente).append("' ");
     qr.update(sql.toString());
     return true;   
  }

}
