package org.probosque.dao;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.BusquedaReforestacionDTO;
import org.probosque.dto.CatalogoDTO;
import org.probosque.dto.PadronDTO;
import org.probosque.dto.RegistroPadronDTO;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.ResultString;
import org.probosque.dto.Tabla1DTO;
import org.probosque.dto.Tabla2DTO;
import org.probosque.dto.Tabla3DTO;
import org.probosque.dto.UserDTO;

public class ReforestacionDAO {

    public List<Tabla1DTO> getDatosTabla1() throws SQLException {
      DataSource ds = PoolDataSource.getRefDataSource();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        //sql.append("Select * from md01.inspecciones");
        
        sql.append("SELECT   ");
     sql.append(" CASE WHEN id_inspeccion IS NULL THEN 0 ELSE  id_inspeccion END AS id_inspeccion, ");
     sql.append(" CASE WHEN acta IS NULL THEN ' ' ELSE acta END AS acta,	 ");
     sql.append(" CASE WHEN orden IS NULL THEN ' ' ELSE orden END as orden,  ");
     sql.append(" CASE WHEN expediente IS NULL THEN ' '  ELSE EXPEDIENTE end AS expediente,  ");
     sql.append(" CASE WHEN to_char(fecha_inspeccion, 'dd/mm/yyyy hh24:mi:ss' ) IS NULL THEN '1/1/9999'  ELSE  to_char(fecha_inspeccion, 'dd/mm/yyyy hh24:mi:ss' ) end AS fecha_inspeccion,  ");
     sql.append(" CASE WHEN id_inspeccionado IS NULL THEN ' '  ELSE id_inspeccionado  end AS id_inspeccionado, ");
     sql.append("  CASE WHEN inspeccionado IS NULL THEN ' '  ELSE inspeccionado  end AS inspeccionado, ");
     sql.append(" CASE WHEN rnf IS NULL THEN ' '  ELSE rnf end AS rnf, ");
     sql.append(" CASE WHEN medida_seguridad IS NULL THEN 'true'  ELSE medida_seguridad  end AS medida_seguridad, ");
     sql.append("  CASE WHEN texto_medida_seguridad IS NULL THEN 't'  ELSE texto_medida_seguridad end AS  texto_medida_seguridad, ");
     sql.append(" CASE WHEN resolucion_admin IS NULL THEN 't'  ELSE resolucion_admin end AS resolucion_admin, ");
     sql.append(" CASE WHEN TEXTO_resolucion_admin IS NULL THEN 't'  ELSE TEXTO_resolucion_admin end AS  texto_resolucion_admin,");
     sql.append(" CASE WHEN sancion IS NULL THEN 't'  ELSE sancion end AS sancion, ");
     sql.append(" CASE WHEN texto_sancion IS NULL THEN ' '  ELSE texto_sancion end AS texto_sancion, ");
     sql.append(" CASE WHEN estado IS NULL THEN ' '  ELSE estado end AS estado, ");
     sql.append(" CASE WHEN tipo IS NULL THEN ' '  ELSE tipo end AS tipo, ");
     sql.append(" CASE WHEN sin_irregularidades IS NULL THEN 't'  ELSE sin_irregularidades end AS sin_irregularidades, ");
     sql.append(" CASE WHEN publico IS NULL THEN 't'  ELSE publico end AS publico, ");
     sql.append(" CASE WHEN obs IS NULL THEN ' '  ELSE obs end AS obs ");
     sql.append("FROM public.inspecciones ");

        
        
        
        ResultSetHandler rsh = new BeanListHandler(Tabla1DTO.class);
        List<Tabla1DTO> dto = (List<Tabla1DTO>)qr.query(sql.toString(), rsh);
        return dto;
    }

    public List<Tabla2DTO> getDatosTabla2() throws SQLException {
       DataSource ds = PoolDataSource.getRefDataSource();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
       sql.append("  SELECT  ");
       sql.append(" CASE WHEN id_padron_industria  IS NULL THEN 0  ELSE id_padron_industria END  as id_padron_industria, ");
       sql.append(" CASE WHEN id  IS NULL THEN ' '  ELSE id end as id, ");
       sql.append(" CASE WHEN nombre IS NULL THEN ' '  ELSE nombre END as    nombre, ");
       sql.append(" CASE WHEN razon_social IS NULL THEN ' '  ELSE razon_social END as razon_social, ");
       sql.append(" CASE WHEN rfc  IS NULL THEN ' ' ELSE  rfc END as rfc, ");
       sql.append(" CASE WHEN rnf IS NULL THEN ' '  ELSE rnf END as rnf, ");
       sql.append(" CASE WHEN calle  IS NULL THEN ' ' ELSE  calle END as calle, ");
       sql.append(" CASE WHEN num_ext IS NULL THEN 'S/N' ELSE   num_ext END as num_ext, ");
       sql.append(" CASE WHEN num_int IS NULL THEN 'S/N' ELSE num_int END as  num_int, ");
       sql.append(" CASE WHEN colonia IS NULL THEN ' '  ELSE colonia END as colonia, ");
       sql.append(" CASE WHEN localidad IS NULL THEN ' '  ELSE localidad END as localidad, ");
       sql.append(" CASE WHEN municipio IS NULL THEN ' ' ELSE  municipio END as municipio, ");
       sql.append(" CASE WHEN telefonos IS NULL THEN ' '  ELSE telefonos END as telefonos, ");
       sql.append(" CASE WHEN correo IS NULL THEN ' '  ELSE correo END as correo,");
       sql.append(" CASE WHEN lat IS NULL THEN 0  ELSE lat END as lat, ");
       sql.append(" CASE WHEN lon IS NULL THEN 0  ELSE lon END as lon, ");
       sql.append(" CASE WHEN tipo IS NULL THEN ' ' ELSE  tipo END as tipo, ");
       sql.append(" CASE WHEN actividad IS NULL THEN ' ' ELSE  actividad END as actividad,   ");
       sql.append(" CASE WHEN fecha_alta IS NULL THEN ' '  ELSE to_char(fecha_alta,'dd/mm/yyyy/ hh24:mi:ss ') END as fecha_alta, ");
       sql.append(" CASE WHEN obs IS NULL THEN ' ' ELSE   obs END as obs, ");
       sql.append(" CASE WHEN activo IS NULL THEN 't'  ELSE activo END as activo, ");
       // sql.append(" CASE WHEN status IS NULL THEN 0  ELSE status END as status ");
       /********INICIO CÓDIGO EDGA NUEVOS CAMPOS DE CONSULTA 10/07/17*************/
       sql.append(" CASE WHEN atp IS NULL THEN 'f'  ELSE atp END as atp, ");
       sql.append(" CASE WHEN fecha_atp IS NULL THEN ' '  ELSE to_char(fecha_atp,'dd/mm/yyyy/ hh24:mi:ss ') END as fecha_atp ");
       /********FIN CÓDIGO EDGA NUEVOS CAMPOS DE CONSULTA 10/07/17*************/
       sql.append(" FROM padron_industria ");
      

        ResultSetHandler rsh = new BeanListHandler(Tabla2DTO.class);
        List<Tabla2DTO> dto = (List<Tabla2DTO>)qr.query(sql.toString(), rsh);
        return dto;
    }

    public List<Tabla3DTO> getDatosTabla3() throws SQLException {
       DataSource ds = PoolDataSource.getRefDataSource();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n" +
"       CASE WHEN id_padron_predios IS NULL THEN 0  ELSE id_padron_predios END  as id_padron_predios,\n" +
"       CASE WHEN id IS NULL THEN ''  ELSE id END  as id, \n" +
"       CASE WHEN nombre IS NULL THEN ' '  ELSE nombre END  as nombre,\n" +
"       CASE WHEN razon_social IS NULL THEN ' '  ELSE razon_social END  as razon_social,\n" +
"       CASE WHEN rfc  IS NULL THEN ' '  ELSE rfc END  as rfc,\n" +
"       CASE WHEN rnf  IS NULL THEN ' '  ELSE rnf END  as rnf, \n" +
"       CASE WHEN calle  IS NULL THEN ' '  ELSE calle END  as calle , \n" +
"       CASE WHEN num_ext  IS NULL THEN ' '  ELSE num_ext END  as num_ext,\n" +
"       CASE WHEN num_int  IS NULL THEN ' '  ELSE num_int END  as num_int,\n" +
"       CASE WHEN colonia  IS NULL THEN ' '  ELSE colonia END  as colonia, \n" +
"       CASE WHEN localidad IS NULL THEN ' '  ELSE localidad END  as localidad, \n" +
"       CASE WHEN municipio IS NULL THEN ' '  ELSE municipio END  as municipio,\n" +
"       CASE WHEN telefonos IS NULL THEN ' '  ELSE telefonos END  as telefonos, \n" +
"       CASE WHEN correo IS NULL THEN ' '  ELSE correo END  as correo, \n" +
"       CASE WHEN lat IS NULL THEN 0  ELSE lat END  as lat, \n" +
"       CASE WHEN lon IS NULL THEN 0  ELSE lon END  as lon, \n" +
"       CASE WHEN to_char(fecha_permiso, 'dd/mm/yyyy') is null then  ' ' else to_char(fecha_permiso, 'dd/mm/yyyy') end as fecha_permiso, \n" +
"       CASE WHEN vigencia IS NULL THEN 0  ELSE vigencia END  as vigencia , \n" +
"       CASE WHEN to_char(fecha_alta, 'dd/mm/yyyy') is null then  ' ' else to_char(fecha_alta, 'dd/mm/yyyy') end as fecha_alta , \n" +
"       CASE WHEN obs is null then ' ' ELSE obs END  as obs, \n" +
"       geom as geom, \n" +
"       CASE WHEN activo is null then true ELSE activo END  as activo, \n" +
"       CASE WHEN status is null then 0 ELSE status END  as status \n" +
"  FROM padron_predios");
        ResultSetHandler rsh = new BeanListHandler(Tabla3DTO.class);
        List<Tabla3DTO> dto = (List<Tabla3DTO>)qr.query(sql.toString(), rsh);
        return dto;
    }
    
    public int getMaxRows(String tabla) throws SQLException
     {
        DataSource ds = PoolDataSource.getRefDataSource();
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("Select count(*) as result  from ").append(tabla);
        ResultSetHandler rsh = new BeanListHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh);
        return result.getResult();
     
     }
    
    
    
    
    public void insertPunto(UserDTO user, String lon, String lat, String folio)throws SQLException, Exception {
        
      if(!lon.isEmpty() && !lat.isEmpty())
        
      {DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
          
        String idMax = "SELECT case when max (gid) is null then 0 else max (gid) end +1 as result FROM capasgeograficas.puntos";
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(idMax, rsh);
        
        String idGid= Integer.toString(result.getResult());
        
        String cadenaInsertUTM = "INSERT INTO capasgeograficas.puntos (gid, folio, the_geom) VALUES ('" + idGid + "', '" + folio + "', " + " ST_GeomFromText ('POINT("+lon+" "+lat+")',32614))";
        ds = PoolDataSource.getDataSource(user);
        qr = new QueryRunner(ds);
        
        qr.update(cadenaInsertUTM);
      }
    }
    
public  RegistroPadronDTO getDetalleRegPadron(UserDTO user, String idPadron)
 throws SQLException {
     DataSource ds = PoolDataSource.getDbProgram8(true);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append(" SELECT \n" +
"      CASE WHEN id_padron_industria IS NULL THEN ' ' ELSE id_padron_industria END AS id_padron_industria,\n" +
"      CASE WHEN nombre IS NULL THEN ' ' ELSE nombre END AS nombre ,\n" +
"      CASE WHEN razon_social IS NULL THEN ' ' ELSE razon_social END AS razon_social,\n" +
"      CASE WHEN rfc IS NULL THEN ' ' ELSE rfc END AS rfc,\n" +
"      CASE WHEN rnf IS NULL THEN ' ' ELSE rnf END AS rnf,\n" +
"      CASE WHEN calle IS NULL THEN ' ' ELSE calle END AS calle,\n" +
"      CASE WHEN num_ext IS NULL THEN ' ' ELSE num_ext END AS num_ext,  \n" +
"      CASE WHEN num_int IS NULL THEN ' ' ELSE num_int END AS num_int,\n" +
"      CASE WHEN colonia IS NULL THEN ' ' ELSE colonia END AS colonia,\n" +
"      CASE WHEN localidad IS NULL THEN ' ' ELSE localidad END AS localidad,\n" +
"      CASE WHEN municipio IS NULL THEN ' ' ELSE municipio END AS municipio,\n" +
"      CASE WHEN cve_localidad IS NULL THEN 0 ELSE cve_localidad END AS cve_localidad,\n" +
"      CASE WHEN telefonos IS NULL THEN ' ' ELSE  telefonos END AS telefonos ,  \n" +
"      CASE WHEN correo IS NULL THEN ' ' ELSE correo END AS correo ,\n" +
"      CASE WHEN lat IS NULL THEN ' ' ELSE LAT END AS lat,\n" +
"      CASE WHEN lon IS NULL THEN ' ' ELSE lon END AS lon,\n" +
"      CASE WHEN tipo IS NULL THEN ' ' ELSE tipo END AS tipo,\n" +
"      CASE WHEN actividad IS NULL THEN ' ' ELSE actividad END AS actividad,\n" +
"      CASE WHEN fecha_alta IS NULL THEN ' ' ELSE to_char(fecha_alta,'dd-mm-yyyy') END AS fecha_alta,\n" +
"      CASE WHEN cve_reg IS NULL THEN 0 ELSE cve_reg END AS cve_reg,\n" +
"      CASE WHEN cve_mpio IS NULL THEN 0 ELSE cve_mpio END AS cve_mpio, \n" +
"      CASE WHEN obs IS NULL THEN ' ' ELSE obs END AS obs, \n" +
/********INICIO CÓDIGO EDGA NUEVOS CAMPOS DE CONSULTA 10/07/17*************/
"      CASE WHEN atp IS NULL THEN 'f'  ELSE atp END as atp, \n" +
"      CASE WHEN fecha_atp IS NULL THEN ' '  ELSE to_char(fecha_atp,'dd/mm/yyyy') END as fecha_atp \n" +
/********FIN CÓDIGO EDGA NUEVOS CAMPOS DE CONSULTA 10/07/17*************/
"   FROM formularios.padron_industria WHERE id_padron_industria='").append(idPadron).append("'");
     ResultSetHandler rsh = new BeanHandler(RegistroPadronDTO.class);
     RegistroPadronDTO dto = (RegistroPadronDTO) qr.query(sql.toString(), rsh);
     return dto;
 
 }
    
    
    
    
    
public List<BusquedaReforestacionDTO> getBusquedaREforestacion(UserDTO user, String nameCol, String criterio) 
  throws SQLException {
     DataSource ds = PoolDataSource.getDbProgram8(true);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append(" SELECT  case when id_padron_industria is null THEN ' ' ELSE id_padron_industria END AS id_padron_industria,"
             + " CASE WHEN NOMBRE  IS NULL THEN ' ' ELSE  UPPER(nombre) END AS nombre,"
             + " CASE WHEN RAZON_SOCIAL IS NULL THEN ' ' ELSE  UPPER(razon_social) END as razon_social,"
             + " CASE WHEN  RFC IS NULL THEN ' ' ELSE UPPER(rfc) END  as rfc,"
             + " CASE WHEN RNF IS NULL THEN ' ' ELSE  UPPER(rnf) END as rnf, "
             + " CASE WHEN TIPO IS NULL THEN ' ' ELSE UPPER(tipo) END as tipo, "
             + " CASE WHEN FECHA_ALTA IS NULL THEN  ' ' ELSE  TO_CHAR(fecha_alta, 'YYYY-MM-DD') END as fecha_alta ");
     sql.append(" FROM formularios.padron_industria ");
     sql.append(" WHERE ").append(this.getConstructWhere(nameCol, criterio)).append(" and  STATUS=TRUE");
     ResultSetHandler  rsh = new BeanListHandler(BusquedaReforestacionDTO.class);
     List<BusquedaReforestacionDTO> dto = (List<BusquedaReforestacionDTO>) qr.query(sql.toString(), rsh);
     return dto;
  }
    
public String getConstructWhere(String Columna, String criterioBusqueda)
 {
     
   StringBuilder condicion =  new StringBuilder();  
    if(Columna.equals("FECHA DE ALTA"))
       {
        condicion.append(" TO_CHAR(fecha_alta, 'DD/MM/YYYY') = '").append(criterioBusqueda).append("' ");
       }
    else if(Columna.equals("ID PADRON INDUSTRIAL"))
             {
             condicion.append("  id_padron_industria= '").append(criterioBusqueda).append("' ");
             
             }
    /********INICIO CÓDIGO EDGA NUEVOS CAMPOS DE CONSULTA 10/07/17*************/
    else if(Columna.equals("FECHA DE AUDITORIA")){
        condicion.append(" TO_CHAR(fecha_atp, 'DD/MM/YYYY') = '").append(criterioBusqueda).append("' ");
    }
    else if(Columna.equals("ATP")){
        condicion.append(" atp = '").append(criterioBusqueda).append("' ");
    }
    /********FIN CÓDIGO EDGA NUEVOS CAMPOS DE CONSULTA 10/07/17*************/
    else 
         condicion.append("UPPER(").append(Columna ).append(") LIKE UPPER('%").append(criterioBusqueda).append("%') ");
     
     return condicion.toString();
 
 }



public boolean DeleteRegistro(UserDTO user, String id_padron) throws SQLException
  {
     DataSource ds = PoolDataSource.getDbProgram8(true);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append(" UPDATE  formularios.padron_industria  ");
     sql.append(" SET STATUS=FALSE ");
     sql.append(" WHERE id_padron_industria='").append(id_padron).append("' ");
     qr.update(sql.toString());
     return true;
  }


public boolean UpdateRegistroPadron(UserDTO user, PadronDTO regUpdate ) throws SQLException
 {
     DataSource ds = PoolDataSource.getDbProgram8(true);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     boolean exito= true;
     sql.append(" SELECT * FROM formularios.padron_industria WHERE  id_padron_industria='").append(regUpdate.getIdPadron()).append("'");
     ResultSetHandler rsh = new BeanHandler(PadronDTO.class); 
     PadronDTO regBef = (PadronDTO) qr.query(sql.toString(), rsh);
     String auxCadena =regUpdate.getLatitud().replace("'","''");
     regUpdate.setLatitud(auxCadena.replace("\"", "''\""));
    
     auxCadena =regUpdate.getLongitud().replace("'","''");
     regUpdate.setLongitud(auxCadena.replace("\"", "''\""));
     
     if(this.getChanges(regUpdate, regBef)==true)
         {
           sql = new StringBuilder();
           
           sql.append(" UPDATE formularios.padron_industria ");
           sql.append(" SET id_padron_industria='").append(regUpdate.getIdPadron()).append("', ");
           sql.append("  nombre='").append(regUpdate.getNombre()).append("', ");
           sql.append("  razon_social='").append(regUpdate.getRazonSocial()).append("', ");
           sql.append("  rfc='").append(regUpdate.getRfc()).append("', ");         
           sql.append("  rnf='").append(regUpdate.getRnf()).append("', ");
           sql.append("  calle='").append(regUpdate.getCalle()).append("', ");
           sql.append("  num_ext='").append(regUpdate.getNoExterior()).append("', ");  
           sql.append("  num_int='").append(regUpdate.getNoInterior()).append("', ");
           sql.append("  colonia='").append(regUpdate.getColonia()).append("', ");  
           sql.append("  localidad='").append(regUpdate.getLocalidad()).append("', ");  
           sql.append("  municipio='").append(regUpdate.getMunicipio()).append("', "); 
           sql.append("  telefonos='").append(regUpdate.getTelefono()).append("', ");  
           sql.append(" correo='").append(regUpdate.getCorreo()).append("', ");
           sql.append(" lat='").append(regUpdate.getLatitud()).append("', ");
           sql.append(" lon='").append(regUpdate.getLongitud()).append("', ");
           sql.append(" tipo='").append(regUpdate.getTipo()).append("',");
           sql.append(" actividad='").append(regUpdate.getActividad()).append("',");
           sql.append(" fecha_alta=to_date('").append(regUpdate.getFechaAlta()).append("', 'dd/mm/yyyy hh24:mi:ss'), ");
           sql.append(" cve_reg='").append(regUpdate.getCve_reg()).append("', ");
           sql.append(" cve_mpio='").append(regUpdate.getCve_mpio()).append("', ");
           sql.append(" cve_localidad='").append(regUpdate.getCve_localidad()).append("', ");
           sql.append(" obs='").append(regUpdate.getObservaciones()).append("', ");
              /**
             * ******INICIO CÓDIGO EDGA NUEVOS CAMPOS DE CONSULTA 10/07/17************
             */
            if (regUpdate.getFecha_atp().trim().equals("")) {
                sql.append(" fecha_atp=null, ");
            } else {
                sql.append(" fecha_atp=to_date('").append(regUpdate.getFecha_atp()).append("', 'dd/mm/yyyy'), ");
            }
            sql.append(" atp='").append(regUpdate.getAtp()).append("' ");
            /**
             * ******FIN CÓDIGO EDGA NUEVOS CAMPOS DE CONSULTA 10/07/17************
             */

           sql.append(" WHERE ID_PADRON_INDUSTRIA='").append(regUpdate.getIdPadron()).append("'");         
       qr.update(sql.toString());
         }
     else 
         exito= false;
  return exito; 
 }

public boolean getChanges(PadronDTO regUpdate, PadronDTO regBef)
  {
     boolean cambio=false;
    if(regUpdate.getActividad().equals(regBef.getActividad()))
        {
        cambio=true;
        }  
  if(regUpdate.getCalle().equals(regBef.getCalle()))
        {
        cambio=true;
        }  
  if(regUpdate.getColonia().equals(regBef.getColonia()))
        {
        cambio=true;
        }  
  if(regUpdate.getCorreo().equals(regBef.getCorreo()))
        {
        cambio=true;
        }  
  if(regUpdate.getFechaAlta().equals(regBef.getFechaAlta()))
        {
        cambio=true;
        }  
  if(regUpdate.getIdPadron().equals(regBef.getIdPadron()))
        {
        cambio=true;
        }  
  if(regUpdate.getLatitud().equals(regBef.getLatitud()))
        {
        cambio=true;
        } 
  if(regUpdate.getLocalidad().equals(regBef.getLocalidad()))
        {
        cambio=true;
        }  
  if(regUpdate.getMunicipio().equals(regBef.getMunicipio()))
        {
        cambio=true;
        }  
  if(regUpdate.getNoExterior().equals(regBef.getNoExterior()))
        {
        cambio=true;
        }  
  if(regUpdate.getNoInterior().equals(regBef.getNoInterior()))
        {
        cambio=true;
        }  
  if(regUpdate.getNombre().equals(regBef.getNombre()))
        {
        cambio=true;
        }  
  if(regUpdate.getObservaciones().equals(regBef.getObservaciones()))
        {
        cambio=true;
        }  
  if(regUpdate.getRazonSocial().equals(regBef.getRazonSocial()))
        {
        cambio=true;
        }  
  if(regUpdate.getRfc().equals(regBef.getRfc()))
        {
        cambio=true;
        }  
  if(regUpdate.getRnf().equals(regBef.getRnf()))
        {
        cambio=true;
        }
  return cambio;  
  }


  public String insertPadron(UserDTO user, String claveLoc, String claveReg, String claveMun, String idPadronIndustrial, String nombre,String razonSocial,String rfc,String rnf,String calle,String noExterior,String noInterior,String colonia,String localidad,String municipio,String telefono,String correo,String latitud,String longitud,String tipo,String actividad,String fechaAlta,String observaciones,String estatus) throws SQLException {
        
        String respuesta = "";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        
        String selectCount = "SELECT count (*) result FROM formularios.padron_industria WHERE id_padron_industria = '"+idPadronIndustrial+"'";
        String selectStatus = "SELECT count (*) result FROM formularios.padron_industria WHERE id_padron_industria = '"+idPadronIndustrial+"' AND status = 'false'";
        
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
       ResultInteger result = (ResultInteger) qr.query(selectCount, rsh);
        
        if(result.getResult() == 0){
        
            String insercion = "INSERT INTO  formularios.padron_industria(\n" +
            "            id_padron_industria, nombre, razon_social, rfc, rnf, calle, \n" +
            "            num_ext, num_int, colonia, localidad, municipio, telefonos, correo, \n" +
            "            lat, lon, tipo, actividad, fecha_alta, obs, cve_reg, cve_mpio, cve_localidad, \n" + 
            "            status) \n" +
            "            VALUES ('"+idPadronIndustrial+"','"+nombre+"','"+razonSocial+"','"+rfc+"','"+rnf+"','"+calle+"', \n" +
            "           '"+noExterior+"','"+noInterior+"','"+colonia+"','"+localidad+"','"+municipio+"', \n" +
            "           '"+telefono+"','"+correo+"','"+latitud+"','"+longitud+"','"+tipo+"','"+actividad+"', \n" +
            "           '"+fechaAlta+"','"+observaciones+"','"+claveReg+"','"+claveMun+"','"+claveLoc+"', 'true')";

            qr.update(insercion);
            
            respuesta = "0";//Se inserto
        }else{
            
            rsh = new BeanHandler(ResultInteger.class);
            result = (ResultInteger) qr.query(selectStatus, rsh);
            
            if(result.getResult() == 1){
                //respuesta = "Ya existe ese folio, pero se encuentra deshabilitado ¿Deseas habilitarlo?";
                respuesta = "1";
            }else{
                //respuesta = "Este folio ya existe";
                respuesta = "2";
            }
        }
        
        return respuesta;
        
    }


    public List<CatalogoDTO> getMunicipio(UserDTO user, String idRegion)throws Exception{

        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner  qr = new QueryRunner(ds);
        
        
        //String cadenaSelect="SELECT id, descripcion FROM catalogos.municipio WHERE id_reg = '"+idRegion+"'";
        String cadenaSelect="SELECT id, descripcion FROM catalogos.municipio where id_region="+Integer.parseInt(idRegion)+" order by descripcion asc";
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        List<CatalogoDTO> result = (List<CatalogoDTO>)qr.query(cadenaSelect, rsh);

        return result;

     }
    
    
    public List<CatalogoDTO> getLocalidad(UserDTO user, String idMunicipio)throws Exception{

        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner  qr = new QueryRunner(ds);
        
        
        String cadenaSelect="SELECT id, descripcion FROM catalogos.localidad WHERE id_municipio = '"+idMunicipio+"' ORDER BY DESCRIPCION ASC";
        
        
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        List<CatalogoDTO> result = (List<CatalogoDTO>)qr.query(cadenaSelect, rsh);

        return result;

     }
    
    
    public String updateStatus(UserDTO user, String idPadron)throws Exception{

        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner  qr = new QueryRunner(ds);
        
        String respuesta = "Folio habilitado!";
        
        
        String cadenaUpdate="UPDATE formularios.padron_industria SET status='true' WHERE  id_padron_industria='"+idPadron+"'";
        
        qr.update(cadenaUpdate);

        return respuesta;

     }/*
    public void insertPunto(UserDTO user, String lon, String lat, String folio)throws SQLException, Exception {
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        
        String idMax = "SELECT case when max (gid) is null then 0 else max (gid) end +1 as result FROM capasgeograficas.puntos";
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(idMax, rsh);
        
        String idGid= Integer.toString(result.getResult());
        
        String cadenaInsertUTM = "INSERT INTO capasgeograficas.puntos (gid, folio, the_geom) VALUES ('" + idGid + "', '" + folio + "', " + " ST_GeomFromText ('POINT("+lon+" "+lat+")',32614))";
        ds = PoolDataSource.getDataSource(user);
        qr = new QueryRunner(ds);
        
        qr.update(cadenaInsertUTM);
    }*/
 public String getFolio(String tipoIndustria, String idRegion, String idMunicipio) throws SQLException 
   {
      StringBuilder folio= new StringBuilder(); 
      String auxTipoIndustria;
      DataSource ds = PoolDataSource.getDbProgram8(true);
      QueryRunner qr = new QueryRunner(ds);
      StringBuilder sql= new StringBuilder();
      if(tipoIndustria.equals("MADERERÍA"))
       {
       auxTipoIndustria="IM";
       }
      else
          if(tipoIndustria.equals("ASERRADERO"))
              {
               auxTipoIndustria="IA";              
              }
          else 
              {
               auxTipoIndustria="IT";              
              }
              
      
      sql.append(" Select MAX( LTRIM(RTRIM(id_padron_industria))) as result  ");
      sql.append(" FROM FORMULARIOS.PADRON_INDUSTRIA");
      sql.append(" WHERE  id_padron_industria LIKE '").append(auxTipoIndustria).append(idRegion).append(idMunicipio).append("16%' ");
     
      ResultSetHandler rsh = new BeanHandler(ResultString.class);
      
      ResultString result = (ResultString)qr.query(sql.toString(), rsh);
      
            if(result==null || result.getResult()==null || result.getResult().isEmpty())
              {
               folio.append(auxTipoIndustria).append(idRegion).append(idMunicipio).append("161");        
              
              }
            else
                {
                Boolean bandera= false;
                    Integer consecutivo = Integer.parseInt(result.getResult().trim().
                        replace(auxTipoIndustria+idRegion+idMunicipio+"16",""));
                folio.append(auxTipoIndustria).append(idRegion).append(idMunicipio).append("16");        
                Integer i=1;
                while(bandera==false)
                      {
                          
                       if(this.getExiste(folio.toString()+(String.valueOf((consecutivo+i))))==0)
                          {
                          folio.append(consecutivo+i);
                          bandera=true;
                          }
                       else{
                          i++;
                       }
                      
                      }
                
                
                
                }
      return folio.toString();
  }

 public Integer getExiste(String folio) throws SQLException
   {
      DataSource ds = PoolDataSource.getDbProgram8(true);
      QueryRunner qr = new QueryRunner(ds);
      StringBuilder sql= new StringBuilder();
      sql.append("Select COUNT(id_padron_industria) as result   FROM FORMULARIOS.PADRON_INDUSTRIA WHERE "
              + " id_padron_industria = '").append(folio).append("' ");
      ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
      ResultInteger result=(ResultInteger) qr.query(sql.toString(), rsh);
      return result.getResult();
      
   }
     
    
}
