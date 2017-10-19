package org.probosque.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.CatalogoDTO;
import org.probosque.dto.CatalogoStringDTO;
import org.probosque.dto.ColumnReportDTO;
import org.probosque.ejecutivodto.EjecutivoPrograma1;
import org.probosque.dto.EjecutivoRegionesDTO;
import org.probosque.dto.FiltroDTO;
import org.probosque.dto.ProgramDTO;
import org.probosque.dto.ProgramaDTO;
import org.probosque.dto.ReporteDTO;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.ResultString;
import org.probosque.dto.SelectDTO;
import org.probosque.dto.UserDTO;
import org.probosque.ejecutivodto.ActividadCantidadDTOP72;
import org.probosque.ejecutivodto.ActividadCantidadQuemaDTOP72;
import org.probosque.ejecutivodto.ActivoDTOP11;
import org.probosque.ejecutivodto.AportacionesDTOP11;
import org.probosque.ejecutivodto.AreaRodalSuperficieDTOP1;
import org.probosque.ejecutivodto.AsistenciaTeVolumenSDTOP6;
import org.probosque.ejecutivodto.CantidadBrechaDTOP72;
import org.probosque.ejecutivodto.CantidadQuemaDTOP72;
import org.probosque.ejecutivodto.ConglomeradoSitioDTOP12;
import org.probosque.ejecutivodto.CostoApoyoDTOP11;
import org.probosque.ejecutivodto.DatosGeneralesDTOP10;
import org.probosque.ejecutivodto.DatosPredioDTO;
import org.probosque.ejecutivodto.DocumentacionDTOP8;
import org.probosque.ejecutivodto.DocumentacionGeneralDTOP8;
import org.probosque.ejecutivodto.DuracionDTOP71;
import org.probosque.ejecutivodto.EjecutivoPrograma10;
import org.probosque.ejecutivodto.EjecutivoPrograma11;
import org.probosque.ejecutivodto.EjecutivoPrograma12;
import org.probosque.ejecutivodto.EjecutivoPrograma2;
import org.probosque.ejecutivodto.EjecutivoPrograma3;
import org.probosque.ejecutivodto.EjecutivoPrograma5;
import org.probosque.ejecutivodto.EjecutivoPrograma6;
import org.probosque.ejecutivodto.EjecutivoPrograma71;
import org.probosque.ejecutivodto.EjecutivoPrograma8;
import org.probosque.ejecutivodto.FechaPlagaNotVolDTOP6;
import org.probosque.ejecutivodto.FechaVisitaDiagnosticoDTOP6;
import org.probosque.ejecutivodto.IMADTOP12;
import org.probosque.ejecutivodto.ModalidadPlantacionDTOP5;
import org.probosque.ejecutivodto.MontoAprobadoDTOP10;
import org.probosque.ejecutivodto.MontoTotalDTOP3;
import org.probosque.ejecutivodto.MontoTotalDTOP5;
import org.probosque.ejecutivodto.MontoTotalProrrimDTOP3;
import org.probosque.ejecutivodto.ObservacionesBrechaDTOP72;
import org.probosque.ejecutivodto.ObservacionesDTOP10;
import org.probosque.ejecutivodto.ObservacionesDTOP6;
import org.probosque.ejecutivodto.ObservacionesP3DTO;
import org.probosque.ejecutivodto.ObservacionesProductosDetalleDTOP8;
import org.probosque.ejecutivodto.ObservacionesProductosGeneralDTOP8;
import org.probosque.ejecutivodto.ObservacionesProrrimDTOP3;
import org.probosque.ejecutivodto.ObservacionesQuemaDTOP72;
import org.probosque.ejecutivodto.Oficio_fechasP1DTO;
import org.probosque.ejecutivodto.ParticipantesDetalleDTOP8;
import org.probosque.ejecutivodto.ParticipantesGeneralDTOP8;
import org.probosque.ejecutivodto.PersonasAseguradasDetalleDTOP8;
import org.probosque.ejecutivodto.PersonasAseguradasGeneralDTOP8;
import org.probosque.ejecutivodto.PlantasNuevasDTOP3;
import org.probosque.ejecutivodto.PresenciaIncendioDTOP12;
import org.probosque.ejecutivodto.RegistroPredioDTOP6;
import org.probosque.ejecutivodto.SituacionPredioDTOP1;
import org.probosque.ejecutivodto.SuperficieAprobadaDTOP10;
import org.probosque.ejecutivodto.SuperficieFinalRefDTOP3;
import org.probosque.ejecutivodto.SuperficieFinalRefDTOP5;
import org.probosque.ejecutivodto.SuperficieReforestadaMenosDesistidaDTOP3;
import org.probosque.ejecutivodto.SuperficieReforestadaProrrimDTOP3;
import org.probosque.ejecutivodto.SuperficieTotalBoscosaDTOP6;
import org.probosque.ejecutivodto.TipoAccionDTOP8;
import org.probosque.ejecutivodto.TipoApoyoDTOP11;
import org.probosque.ejecutivodto.TotalAclareoDTOP5;
import org.probosque.ejecutivodto.TotalDTOP71;
import org.probosque.ejecutivodto.TotalMinistracionDTOP10;
import org.probosque.ejecutivodto.TotalMinistracionDTOP3;
import org.probosque.ejecutivodto.TotalMinistracionDTOP5;
import org.probosque.ejecutivodto.TotalMinistracionProrrimDTOP3;
import org.probosque.ejecutivodto.VentaArbolNavidadDTOP5;
import org.probosque.ejecutivodto.VolumenFechaEspecieDTOP12;
import org.probosque.ejecutivodto.VolumenesGeneroP1DTO;
import org.probosque.ejecutivodto.VolumenesTipoProductoDTOP1;
import org.probosque.ejecutivodto.totalCursoAsambleaDTOP2;
import org.probosque.ejecutivodto.totalCursoDTOP2;
import org.probosque.ejecutivodto.totalCursoTallerDTOP2;
import org.probosque.ejecutivodto.totalEventoReportadoDTOP2;
import org.probosque.ejecutivodto.totalParticipantesCursoAsamblea;
import org.probosque.ejecutivodto.totalParticipantesCursoDTOP2;
import org.probosque.ejecutivodto.totalParticipantesCursoTallerDTOP2;
import org.probosque.ejecutivodto.totalParticipantesEventoReportado;
import org.probosque.ejecutivodto.totalParticipantesTaller;
import org.probosque.ejecutivodto.totalTallerDTOP2;

public class EjecutivoDAO {


public List<CatalogoDTO>  getRegiones () throws SQLException
  {
        DataSource ds = PoolDataSource.getDataSource(false);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();    
        EjecutivoRegionesDTO ejec = new EjecutivoRegionesDTO();
        sql.append(" SELECT id, descripcion");
        sql.append(" FROM ").append(" catalogos.region ");
        ResultSetHandler rsh = new BeanHandler(CatalogoDTO.class);
        List<CatalogoDTO> regiones= (List<CatalogoDTO>) qr.query(sql.toString(), rsh);
        
        return regiones;
  }


public List<CatalogoDTO>  getMunicipio (int region ) throws SQLException
  {
        DataSource ds = PoolDataSource.getDataSource(false);
        QueryRunner qr = new QueryRunner(ds);
                    StringBuilder sql = new StringBuilder();    
        EjecutivoRegionesDTO ejec = new EjecutivoRegionesDTO();
        sql.append(" SELECT id, descripcion");
        sql.append(" FROM ").append(" catalogos.municipio ");
        sql.append(" WHERE  cast(id_region as integer)= ").append(region);
        sql.append(" ORDER BY descripcion ASC ");
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        List<CatalogoDTO> regiones= (List<CatalogoDTO>) qr.query(sql.toString(), rsh);
        
        return regiones;
  }

public List<CatalogoDTO>  getLocalidad (int municipio ) throws SQLException
  {
        DataSource ds = PoolDataSource.getDataSource(false);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();    
        EjecutivoRegionesDTO ejec = new EjecutivoRegionesDTO();
        sql.append(" SELECT id, descripcion");
        sql.append(" FROM ").append(" catalogos.localidad ");
        sql.append(" WHERE  cast(id_municipio as integer)=").append(municipio);
        sql.append(" ORDER BY descripcion ASC ");
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        List<CatalogoDTO> regiones= (List<CatalogoDTO>) qr.query(sql.toString(), rsh);
        
        return regiones;
  }

public List<CatalogoDTO>  getPredio (String clave, String text ) throws SQLException
  {
        DataSource ds = PoolDataSource.getDataSource(false);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();    
        EjecutivoRegionesDTO ejec = new EjecutivoRegionesDTO();
        sql.append(" SELECT folio as id, predio as descripcion");
        sql.append(" FROM ").append(" formularios.principal ");
        sql.append(" WHERE  folio like '%").append(clave).append("%'");
        sql.append(" AND upper(predio) like upper('%").append(text).append("%') ");
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        List<CatalogoDTO> regiones= (List<CatalogoDTO>) qr.query(sql.toString(), rsh);
        
        return regiones;
  }

public List<CatalogoDTO>  getRepresentantes (String clave, String text ) throws SQLException
  {
        DataSource ds = PoolDataSource.getDataSource(false);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();    
        EjecutivoRegionesDTO ejec = new EjecutivoRegionesDTO();
        sql.append(" SELECT folio as id, nombre_propietario_representante as descripcion");
        sql.append(" FROM ").append(" formularios.representante ");
        sql.append(" WHERE  folio like '%").append(clave).append("%'");
        sql.append(" AND  upper(nombre_propietario_representante) like upper('%").append(text).append("%') ");
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
        List<CatalogoDTO> regiones= (List<CatalogoDTO>) qr.query(sql.toString(), rsh);
        
        return regiones;
  }

    public List<ProgramaDTO>getProgramasEjecutivo() throws SQLException
      {
      
        DataSource ds = PoolDataSource.getDataSource(false);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();    
        sql.append(" SELECT id, descripcion, accion");
        sql.append(" FROM ").append(SQL.TABLE_PROGRAMAS);
        ResultSetHandler rsh = new BeanHandler(ProgramaDTO.class);
        List<ProgramaDTO> program = (List<ProgramaDTO>) qr.query(sql.toString(), rsh);
        
         return  program;
        
      }
   
   public DatosPredioDTO getDatosPredio( String clavePredio) throws SQLException
    { 
      DataSource ds = PoolDataSource.getDataSourceGeneral();
      QueryRunner qr = new QueryRunner(ds);
      StringBuilder sql= new StringBuilder();
      
      sql.append(" select catalogos.tipotenenciatierra.descripcion as tenencia, ");
      sql.append(" q.folio as id,q.predio as descripcion, ");
      sql.append(" p.nombre_propietario_representante as representante, r.descripcion as region, ");
      sql.append(" m.descripcion as municipio,l.descripcion as localidad ");
      sql.append(" FROM formularios.principal q ");
      sql.append(" INNER JOIN catalogos.region r on r.id =CAST(q.region as integer) ");
      sql.append(" INNER JOIN catalogos.municipio m on m.id=CAST(q.modulopredio_municipio as integer) ");
      sql.append(" INNER JOIN catalogos.localidad l on (l.id=CAST(q.modulopredio_localidad as integer) ");
      sql.append(" and l.id_municipio=CAST(q.modulopredio_municipio as integer)) ");
      sql.append(" INNER JOIN catalogos.tipotenenciatierra on catalogos.tipotenenciatierra.id=q.cve_tenencia ");
      sql.append(" INNER JOIN formularios.representante p on p.folio = q.folio ");
      sql.append(" where q.folio='").append(clavePredio).append("'");
     ResultSetHandler rsh = new BeanHandler(DatosPredioDTO.class);
     
    DatosPredioDTO datos= (DatosPredioDTO)qr.query(sql.toString(), rsh);
    return datos;
           
    }
   
    
    
    public List<ReporteDTO>  getReportePersona(UserDTO user, SelectDTO camp, FiltroDTO filtro ) throws SQLException
    {
        DataSource ds = PoolDataSource.getDataSource(user);
       Connection con =ds.getConnection();
       Statement sta = con.createStatement();
        StringBuilder sql = new StringBuilder();    
        sql.append(" SELECT ").append(SQL.getTableMain(user, String.valueOf(user.getActivity()))).append(".anio as anio,catalogos.predios.modulopredio_region as modulopredio_region,catalogos.predios.modulopredio_municipio as modulopredio_municipio,");
                sql.append("catalogos.predios.modulopredio_localidad as modulopredio_localidad, catalogos.predios.descripcion as descripcion,");
                sql.append("catalogos.representante_predio.descripcion as representante, catalogos.predios.tenencia  as tenencia");
                sql.append(" FROM ").append(SQL.getTableMain(user, String.valueOf(user.getActivity())));
        sql.append(" left join catalogos.predios on  catalogos.predios.id=").append(SQL.getTableMain(user, String.valueOf(user.getActivity()))).append(".modulopredio_cup");
        sql.append(" left join catalogos.representante_predio on catalogos.representante_predio.id=").append(SQL.getTableMain(user, String.valueOf(user.getActivity()))).append(".modulopredio_cup");
        sql.append(" WHERE ").append(SQL.getTableMain(user, String.valueOf(user.getActivity()))).append(".modulopredio_cup <> '0'");
        sql.append(" group by formularios.principal.anio,catalogos.predios.modulopredio_region,catalogos.predios.modulopredio_municipio,catalogos.predios.modulopredio_localidad, catalogos.predios.descripcion,");
        sql.append(" catalogos.representante_predio.descripcion, catalogos.predios.tenencia");
        
        ResultSet rs = sta.executeQuery(sql.toString());
                
        return getReporte(rs,camp,con);
    }    
    public ArrayList<ReporteDTO> getReporte(ResultSet rs,SelectDTO columns,Connection con) throws SQLException{
       
        
        
        
       ArrayList<ArrayList<String>> registros = new ArrayList<>(); 
       ArrayList<ReporteDTO> reporte = new ArrayList(); 
        
        
       int j=1,i=0;

       while(rs.next())
             {
              registros.add(new ArrayList<String>());      

              for(SelectDTO column: columns.getSelect() )
                  {
                       //array.get(i).add(nota); 
                      registros.get(i).add(rs.getString(column.getName()));
                  }
              i++;
              
             }    
     
     int x=0; i=1;
      for(SelectDTO col: columns.getSelect())
        {
         ArrayList<String> inf= new ArrayList();  
          for(int m=0; m<registros.size();m++){
             inf.add(registros.get(m).get(x));
              
          }  
          reporte.add(new ReporteDTO(col.getName(),col.getLabel(),col.getType(),col.getDatatype(),inf));
         i++;
         x++;
        }     
      con.close();
      rs.close();
       return reporte;   
      }       
    
   ///Consultas Informacion de Programa 1
   
   public EjecutivoPrograma1 getReportePrograma1(UserDTO user, String clavePredio, String anio) throws SQLException
   {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();    
        EjecutivoRegionesDTO ejec = new EjecutivoRegionesDTO();
        sql.append(" SELECT cast(rodal_especie.area_corta as integer),catalogos.areacorta.descripcion AS areacorta ,catalogos.genero.descripcion AS genero, ");
        sql.append(" SUM(volumen_1existencias_reales_rodal) AS volumen_existencias_reales_rodal, SUM(volumen_1posibilidad_total) AS volumen_posibilidad_total,");
        sql.append(" SUM(volumen_1residual_rodal) AS volumen_residual_rodal ");
        sql.append(" FROM  formularios.principal ");
        sql.append(" LEFT JOIN formularios.rodal_especie ON formularios.rodal_especie.folio = formularios.principal.folio ");
        sql.append(" INNER JOIN catalogos.genero ON CAST(catalogos.genero.id AS INTEGER)=CAST(formularios.rodal_especie.genero AS INTEGER) ");
        sql.append(" INNER JOIN catalogos.areacorta ON CAST(catalogos.areacorta.id AS INTEGER)=CAST(formularios.rodal_especie.area_corta AS INTEGER) ");
        sql.append(" WHERE principal.modulopredio_cup='").append(clavePredio).append("' and formularios.rodal_especie.anio_intervencion ='").append(anio).append("'");
        sql.append(" GROUP BY catalogos.genero.descripcion,catalogos.areacorta.descripcion,rodal_especie.area_corta ");
        sql.append(" ORDER BY 1,3");
        ResultSetHandler rsh = new BeanListHandler(VolumenesGeneroP1DTO.class);
        List<VolumenesGeneroP1DTO> volGenP1DTO = (List<VolumenesGeneroP1DTO>) qr.query(sql.toString(), rsh);
        
        sql=new StringBuilder();
        sql.append(" SELECT   principal.fecha_expedicion AS fecha_expedicion , principal.fecha_vencimiento AS fecha_vencimiento");
        sql.append(" FROM  formularios.principal ");
        sql.append(" WHERE principal.modulopredio_cup='").append(clavePredio).append("' and principal.anio ='").append(anio).append("'");
        sql.append(" LIMIT 1 ");
        rsh = new BeanHandler(Oficio_fechasP1DTO.class);
        Oficio_fechasP1DTO Oficio = (Oficio_fechasP1DTO) qr.query(sql.toString(), rsh);
        
        
        
        sql=new StringBuilder();
        sql.append(" SELECT catalogos.genero.descripcion AS genero, SUM(produccion.produccion_escuadria) AS produccion_escuadria ,SUM(produccion.celulosa) AS celulosa, ");
        sql.append(" SUM(produccion.lenia_combustible) AS lenia_combustible,SUM(produccion.carbon) AS carbon,SUM(produccion.volumen_ejercido) AS volumen_ejercido ");
        sql.append(" FROM formularios.principal ");
        sql.append(" LEFT JOIN formularios.produccion on produccion.folio=principal.folio ");
        sql.append(" INNER JOIN catalogos.genero ON CAST(catalogos.genero.id AS INTEGER)=CAST(produccion.genero AS INTEGER) ");
        sql.append(" WHERE principal.modulopredio_cup='").append(clavePredio).append("' and  produccion.anio='").append(anio).append("' ");
        sql.append(" GROUP BY catalogos.genero.descripcion ");
        sql.append(" ORDER BY 1 ");
        rsh = new BeanListHandler(VolumenesTipoProductoDTOP1.class);
        List<VolumenesTipoProductoDTOP1> volProdP1DTO = (List<VolumenesTipoProductoDTOP1>) qr.query(sql.toString(), rsh);
        
        sql=new StringBuilder();
        sql.append(" SELECT catalogos.situacionpredio.descripcion AS  situacion_predio");
        sql.append(" FROM formularios.principal ");
        sql.append(" inner join catalogos.situacionpredio on catalogos.situacionpredio.id = cast(formularios.principal.situacion_especial_predio as integer) ");
        sql.append(" WHERE principal.modulopredio_cup='").append(clavePredio).append("' and principal.anio='").append(anio).append("'");
        sql.append(" LIMIT 1");
        rsh = new BeanHandler(SituacionPredioDTOP1.class);
        SituacionPredioDTOP1 situacionPredio = (SituacionPredioDTOP1) qr.query(sql.toString(), rsh);
        
        
        sql=new StringBuilder();
        sql.append(" SELECT areascorta.fecha_liberacion  AS fecha_expedicion, areascorta.fecha_informe AS fecha_vencimiento ");
        sql.append(" FROM formularios.principal ");
        sql.append(" LEFT JOIN formularios.areascorta on formularios.areascorta.folio=principal.folio ");
        sql.append(" WHERE principal.modulopredio_cup='").append(clavePredio).append("' and  areascorta.anio='").append(anio).append("' ");
        sql.append(" GROUP BY areascorta.fecha_liberacion, areascorta.fecha_informe ");
        sql.append(" LIMIT 1 ");
        rsh = new BeanHandler(Oficio_fechasP1DTO.class);
        Oficio_fechasP1DTO fechaInformeLiberacion = (Oficio_fechasP1DTO) qr.query(sql.toString(), rsh);
        
        
        sql=new StringBuilder();
        sql.append(" SELECT principal.superficie_arbolada as result");
        sql.append(" FROM formularios.principal ");
        sql.append(" WHERE principal.modulopredio_cup='").append(clavePredio).append("' and principal.anio='").append(anio).append("'");
        sql.append(" GROUP BY principal.superficie_arbolada ");
        sql.append(" LIMIT 1 ");
        rsh = new BeanHandler(ResultString.class);
        ResultString superficieArbolada = (ResultString) qr.query(sql.toString(), rsh);
        
         sql=new StringBuilder();
        sql.append(" SELECT cast(formularios.rodal_especie.area_corta as integer) as x,catalogos.areacorta.descripcion as area_corta,CAST(formularios.rodal_especie.rodal AS INTEGER) AS rodal, formularios.rodal_especie.superficie_rodal_hectareas AS superficie ");
        sql.append(" FROM formularios.principal ");
        sql.append(" LEFT JOIN formularios.produccion on formularios.produccion.folio=principal.folio  ");
        sql.append(" LEFT JOIN formularios.rodal_especie on formularios.rodal_especie.folio=principal.folio  ");
        sql.append(" INNER JOIN catalogos.areacorta on catalogos.areacorta.id=cast(formularios.rodal_especie.area_corta as integer) ");
        sql.append(" WHERE principal.modulopredio_cup='").append(clavePredio).append("' AND formularios.rodal_especie.anio_intervencion='").append(anio).append("'");
        sql.append(" GROUP BY formularios.rodal_especie.area_corta,catalogos.areacorta.descripcion, formularios.rodal_especie.rodal, formularios.rodal_especie.superficie_rodal_hectareas");
        sql.append(" ORDER BY  1 ASC");
        rsh = new BeanListHandler(AreaRodalSuperficieDTOP1.class);
             List< AreaRodalSuperficieDTOP1> rodalSuperficie = (List< AreaRodalSuperficieDTOP1>) qr.query(sql.toString(), rsh);    
        sql=new StringBuilder();
        sql.append(" SELECT formularios.principal.oficio_autorizacion AS result");
        sql.append(" FROM formularios.principal ");
        sql.append(" WHERE principal.modulopredio_cup='").append(clavePredio).append("' and principal.anio='").append(anio).append("'");
        sql.append(" GROUP BY formularios.principal.oficio_autorizacion ");
        sql.append(" LIMIT 1 ");
        rsh = new BeanHandler(ResultString.class);
        ResultString oficioAutorizacion = (ResultString) qr.query(sql.toString(), rsh);
        
        EjecutivoPrograma1  eP1= new EjecutivoPrograma1(volGenP1DTO,Oficio,volProdP1DTO,situacionPredio,fechaInformeLiberacion,superficieArbolada,rodalSuperficie,oficioAutorizacion);
        
   
    return eP1;
   }
   
   public EjecutivoPrograma2 getReportePrograma2(UserDTO user, String clavePredio, String anio) throws SQLException
   {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();    
        EjecutivoRegionesDTO ejec = new EjecutivoRegionesDTO();
        sql.append(" SELECT COUNT (ct.descripcion) as result FROM formularios.principal fp ");
        sql.append(" INNER JOIN catalogos.tipoevento ct ");
        sql.append(" ON ct.id = CAST(fp.tipo_evento as INTEGER) ");
        sql.append(" WHERE fp.modulopredio_cup = '").append(clavePredio).append("' ");
        sql.append(" AND  fp.anio = '").append(anio).append("' ");
        sql.append(" AND ct.descripcion = 'Asamblea' ");
        sql.append(" GROUP BY fp.nombre_evento");
        ResultSetHandler rsh = new BeanHandler(totalCursoDTOP2.class);
        totalCursoDTOP2 totalCursos = (totalCursoDTOP2) qr.query(sql.toString(), rsh);
        
        sql = new StringBuilder();    
        sql.append(" SELECT SUM (total_mujeres+total_hombres) AS result FROM formularios.principal fp ");
        sql.append(" INNER JOIN  catalogos.tipoevento ct ");
        sql.append(" ON ct.id = CAST(fp.tipo_evento AS INTEGER)  ");
        sql.append(" WHERE fp.modulopredio_cup = '").append(clavePredio).append("' ");
        sql.append(" AND  fp.anio = '").append(anio).append("' ");
        sql.append(" AND ct.descripcion = 'Asamblea' ");
         rsh = new BeanHandler(totalParticipantesCursoDTOP2.class);
        totalParticipantesCursoDTOP2 totalParticipantesCurso = (totalParticipantesCursoDTOP2) qr.query(sql.toString(), rsh);
               
        //Total Cursos Taller 
        sql = new StringBuilder();    
        sql.append(" SELECT COUNT(ct.descripcion) AS result FROM formularios.principal fp ");
        sql.append(" INNER JOIN catalogos.tipoevento ct");
        sql.append(" ON ct.id = CAST(fp.tipo_evento AS INTEGER) ");
        sql.append(" WHERE fp.modulopredio_cup = '").append(clavePredio).append("' ");
        sql.append(" AND fp.anio = '").append(anio).append("' ");
        sql.append(" AND ct.descripcion = 'Curso' ");
        sql.append(" GROUP BY fp.nombre_evento");
        rsh = new BeanHandler(totalCursoTallerDTOP2.class);
        totalCursoTallerDTOP2 totalCursosTaller = (totalCursoTallerDTOP2) qr.query(sql.toString(), rsh);
        
        sql = new StringBuilder();    
        sql.append(" SELECT SUM (total_mujeres+total_hombres) AS result FROM formularios.principal fp ");
        sql.append(" INNER JOIN  catalogos.tipoevento ct ");
        sql.append(" ON ct.id = CAST(fp.tipo_evento AS INTEGER)  ");
        sql.append(" WHERE fp.modulopredio_cup = '").append(clavePredio).append("' ");
        sql.append(" AND  fp.anio = '").append(anio).append("' ");
        sql.append(" AND ct.descripcion = 'Curso' ");
        rsh = new BeanHandler(totalParticipantesCursoTallerDTOP2.class);
        totalParticipantesCursoTallerDTOP2 totalParticipantesCursoTaller = (totalParticipantesCursoTallerDTOP2) qr.query(sql.toString(), rsh);
        
         
        //Total Curso Asamblea 
        sql = new StringBuilder();    
        sql.append(" SELECT COUNT(ct.descripcion) AS result FROM formularios.principal fp ");
        sql.append(" INNER JOIN catalogos.tipoevento ct");
        sql.append(" ON ct.id = CAST(fp.tipo_evento AS INTEGER) ");
        sql.append(" WHERE fp.modulopredio_cup = '").append(clavePredio).append("' ");
        sql.append(" AND fp.anio = '").append(anio).append("' ");
        sql.append(" AND ct.descripcion = 'Curso Reportado' ");
        sql.append(" GROUP BY fp.nombre_evento");
        rsh = new BeanHandler(totalCursoAsambleaDTOP2.class);
        totalCursoAsambleaDTOP2 totalCursosAsamblea = (totalCursoAsambleaDTOP2) qr.query(sql.toString(), rsh);
        
        sql = new StringBuilder();    
        sql.append(" SELECT SUM (total_mujeres+total_hombres) AS result FROM formularios.principal fp ");
        sql.append(" INNER JOIN  catalogos.tipoevento ct ");
        sql.append(" ON ct.id = CAST(fp.tipo_evento AS INTEGER)  ");
        sql.append(" WHERE fp.modulopredio_cup = '").append(clavePredio).append("' ");
        sql.append(" AND fp.anio = '").append(anio).append("' ");
        sql.append(" AND ct.descripcion = 'Curso Reportado' ");
         rsh = new BeanHandler(totalParticipantesCursoAsamblea.class);
        totalParticipantesCursoAsamblea totalPCursoAsamblea = (totalParticipantesCursoAsamblea) qr.query(sql.toString(), rsh);
        
        //total Talleres
        sql = new StringBuilder();    
        sql.append(" SELECT COUNT(ct.descripcion) AS result FROM formularios.principal fp ");
        sql.append(" INNER JOIN catalogos.tipoevento ct");
        sql.append(" ON ct.id = CAST(fp.tipo_evento AS INTEGER) ");
        sql.append(" WHERE fp.modulopredio_cup = '").append(clavePredio).append("' ");
        sql.append(" AND fp.anio = '").append(anio).append("' ");
        sql.append(" AND ct.descripcion = 'Evento' ");
        sql.append(" GROUP BY fp.nombre_evento");
        rsh = new BeanHandler(totalTallerDTOP2.class);
        totalTallerDTOP2 totalTaller = (totalTallerDTOP2) qr.query(sql.toString(), rsh);
        
        sql = new StringBuilder();    
        sql.append(" SELECT SUM (total_mujeres+total_hombres) AS result FROM formularios.principal fp ");
        sql.append(" INNER JOIN  catalogos.tipoevento ct ");
        sql.append(" ON ct.id = CAST(fp.tipo_evento AS INTEGER)  ");
        sql.append(" WHERE fp.modulopredio_cup = '").append(clavePredio).append("' ");
        sql.append(" AND  fp.anio = '").append(anio).append("' ");
        sql.append(" AND ct.descripcion = 'Evento' ");
        rsh = new BeanHandler(totalParticipantesTaller.class);
        totalParticipantesTaller totalPTaller = (totalParticipantesTaller) qr.query(sql.toString(), rsh);
       
        //evento reportado
        
        sql = new StringBuilder();    
        sql.append(" SELECT COUNT(ct.descripcion) AS result FROM formularios.principal fp ");
        sql.append(" INNER JOIN catalogos.tipoevento ct");
        sql.append(" ON ct.id = CAST(fp.tipo_evento AS INTEGER) ");
        sql.append(" WHERE fp.modulopredio_cup = '").append(clavePredio).append("' ");
        sql.append(" AND fp.anio = '").append(anio).append("' ");
        sql.append(" AND ct.descripcion = 'Evento Reportado' ");
        sql.append(" GROUP BY fp.nombre_evento");
        rsh = new BeanHandler(totalEventoReportadoDTOP2.class);
        totalEventoReportadoDTOP2 totalEventoReportado = (totalEventoReportadoDTOP2) qr.query(sql.toString(), rsh);
        
        sql = new StringBuilder();    
        sql.append(" SELECT SUM (total_mujeres+total_hombres) AS result FROM formularios.principal fp ");
        sql.append(" INNER JOIN  catalogos.tipoevento ct ");
        sql.append(" ON ct.id = CAST(fp.tipo_evento AS INTEGER)  ");
        sql.append(" WHERE fp.modulopredio_cup = '").append(clavePredio).append("' ");
        sql.append(" AND  fp.anio = '").append(anio).append("' ");
        sql.append(" AND ct.descripcion = 'Evento Reportado' ");
        rsh = new BeanHandler(totalParticipantesEventoReportado.class);
        totalParticipantesEventoReportado totalPEventoReportado = (totalParticipantesEventoReportado) qr.query(sql.toString(), rsh);
       
        
     EjecutivoPrograma2  eP2= new EjecutivoPrograma2(totalCursos,totalParticipantesCurso,totalCursosAsamblea,totalPCursoAsamblea,totalCursosTaller,totalParticipantesCursoTaller,totalTaller,totalPTaller,totalEventoReportado,totalPEventoReportado);
   
      return eP2;
   }
   
   public EjecutivoPrograma3 getReportePrograma3(UserDTO user, String clavePredio, String anio) throws SQLException
     {
      DataSource ds = PoolDataSource.getDataSource(user);
      QueryRunner qr = new QueryRunner(ds);
      StringBuilder sql = new StringBuilder();    
          
     sql.append(" SELECT fp.superficie_final_reforestada AS result FROM formularios.principal fp"); 
     sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' AND fp.anio='").append(anio).append("'");
     sql.append(" LIMIT 1 ");
     ResultSetHandler rsh = new BeanHandler(SuperficieFinalRefDTOP3.class);
     SuperficieFinalRefDTOP3 supFRef = (SuperficieFinalRefDTOP3) qr.query(sql.toString(), rsh);
              
     sql= new StringBuilder();
     sql.append(" SELECT  monto_total AS result FROM formularios.principal fp");
     sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' AND fp.anio='").append(anio).append("'");
     sql.append(" LIMIT 1 ");
     rsh = new BeanHandler(MontoTotalDTOP3.class);
     MontoTotalDTOP3 montoTotal = (MontoTotalDTOP3) qr.query(sql.toString(), rsh);
     
     sql= new StringBuilder();
     sql.append(" SELECT SUM(fp.primera_ministracion+fp.segunda_ministracion) AS  result FROM formularios.principal fp");
     sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' AND fp.anio='").append(anio).append("'");
     rsh = new BeanHandler(TotalMinistracionDTOP3.class);
     TotalMinistracionDTOP3 totalMinistracion = (TotalMinistracionDTOP3) qr.query(sql.toString(), rsh);
     
     sql= new StringBuilder();
     sql.append(" SELECT fp.cantidad_planta_nuevas_ref AS result FROM formularios.principal fp");
     sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' AND fp.anio='").append(anio).append("'");
     sql.append(" LIMIT 1 ");
     rsh = new BeanHandler(PlantasNuevasDTOP3.class);
     PlantasNuevasDTOP3 plantas = (PlantasNuevasDTOP3) qr.query(sql.toString(), rsh);
     
     sql= new StringBuilder(); 
     sql.append(" SELECT fp.observaciones  AS observaciones FROM formularios.principal fp");
     sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' AND fp.anio='").append(anio).append("'");
     sql.append(" LIMIT 1 ");
     rsh = new BeanHandler(ObservacionesP3DTO.class);
     ObservacionesP3DTO observaciones = (ObservacionesP3DTO) qr.query(sql.toString(), rsh);
     
     sql= new StringBuilder();
     sql.append(" SELECT sum(fp.superficie_final_reforestada) AS result FROM formularios.principal fp");
     sql.append(" INNER JOIN catalogos.tipoapoyo ON catalogos.tipoapoyo.id=CAST( id_tipo_apoyo AS INTEGER) ");
     sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' AND fp.anio='").append(anio).append("'");
     sql.append(" AND  (catalogos.tipoapoyo.descripcion='PRORRIM-MTTO' OR catalogos.tipoapoyo.descripcion='RECONVERSIÓN ESTATAL MTTO') ");
     sql.append(" LIMIT 1 ");
     
     rsh = new BeanHandler(SuperficieReforestadaProrrimDTOP3.class);
     SuperficieReforestadaProrrimDTOP3 prorrim = (SuperficieReforestadaProrrimDTOP3) qr.query(sql.toString(), rsh);
     
     
     
     sql= new StringBuilder();
     sql.append(" SELECT  SUM(fp.monto_total) AS result FROM formularios.principal fp");
     sql.append(" INNER JOIN catalogos.tipoapoyo ON catalogos.tipoapoyo.id=CAST( id_tipo_apoyo AS INTEGER) ");
     sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' AND fp.anio='").append(anio).append("'");
     sql.append(" AND  (catalogos.tipoapoyo.descripcion='PRORRIM-MTTO' OR catalogos.tipoapoyo.descripcion='RECONVERSIÓN ESTATAL MTTO') ");
     sql.append(" LIMIT 1 ");
     rsh = new BeanHandler(MontoTotalProrrimDTOP3.class);
     MontoTotalProrrimDTOP3 montoTotalProrrim = (MontoTotalProrrimDTOP3) qr.query(sql.toString(), rsh);
    
     sql= new StringBuilder();
     sql.append(" SELECT SUM(fp.primera_ministracion+fp.segunda_ministracion)as result FROM formularios.principal fp");
     sql.append(" INNER JOIN catalogos.tipoapoyo ON catalogos.tipoapoyo.id=CAST( id_tipo_apoyo AS INTEGER) ");
     sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' AND fp.anio='").append(anio).append("'");
     sql.append(" AND  (catalogos.tipoapoyo.descripcion='PRORRIM-MTTO' OR catalogos.tipoapoyo.descripcion='RECONVERSIÓN ESTATAL MTTO') ");
     sql.append(" LIMIT 1 ");
     
     rsh = new BeanHandler(TotalMinistracionProrrimDTOP3.class);
     TotalMinistracionProrrimDTOP3 totalMinistracionProrrim = (TotalMinistracionProrrimDTOP3) qr.query(sql.toString(), rsh);
     
     sql= new StringBuilder();
     sql.append(" SELECT  (SUM(superficie_final_reforestada) - SUM(superficie_desistida)) AS result FROM formularios.principal fp ");
     sql.append(" INNER JOIN catalogos.tipoapoyo ON catalogos.tipoapoyo.id=CAST( id_tipo_apoyo AS INTEGER) ");
     sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' AND fp.anio='").append(anio).append("'");
     sql.append(" AND  (catalogos.tipoapoyo.descripcion='PRORRIM-MTTO' OR catalogos.tipoapoyo.descripcion='RECONVERSIÓN ESTATAL MTTO') ");
     sql.append(" LIMIT 1 ");
     rsh = new BeanHandler(SuperficieReforestadaMenosDesistidaDTOP3.class);
     SuperficieReforestadaMenosDesistidaDTOP3 SuperficieReforestadaDesistida = (SuperficieReforestadaMenosDesistidaDTOP3) qr.query(sql.toString(), rsh);
     
     sql= new StringBuilder();
     sql.append(" SELECT observaciones AS observaciones_prorrim FROM  formularios.principal fp");
     sql.append(" INNER JOIN catalogos.tipoapoyo ON catalogos.tipoapoyo.id=CAST( id_tipo_apoyo AS INTEGER) ");
     sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' AND fp.anio='").append(anio).append("'");
     sql.append(" AND  (catalogos.tipoapoyo.descripcion='PRORRIM-MTTO' OR catalogos.tipoapoyo.descripcion='RECONVERSIÓN ESTATAL MTTO') ");
     rsh = new BeanHandler(ObservacionesProrrimDTOP3.class);
     ObservacionesProrrimDTOP3 observacionesProrrim = (ObservacionesProrrimDTOP3) qr.query(sql.toString(), rsh);
     

     EjecutivoPrograma3  eP3= new EjecutivoPrograma3(supFRef,montoTotal,totalMinistracion,plantas,observaciones,prorrim,montoTotalProrrim,totalMinistracionProrrim,SuperficieReforestadaDesistida,observacionesProrrim);
     return eP3;
     }
   
   public EjecutivoPrograma5 getReportePrograma5(UserDTO user, String clavePredio, String anio) throws SQLException{
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();    
        sql.append(" SELECT  superficie_final_reforestada_ha AS result FROM formularios.principal ");
        sql.append(" WHERE modulopredio_cup = '").append(clavePredio).append("' ");
        sql.append(" AND anio ='").append(anio).append("'");
        ResultSetHandler rsh = new BeanHandler(SuperficieFinalRefDTOP5.class);
        SuperficieFinalRefDTOP5 superficie = (SuperficieFinalRefDTOP5) qr.query(sql.toString(), rsh);
                
        sql = new StringBuilder();    
        sql.append(" select monto_total AS result from formularios.principal ");
        sql.append(" WHERE modulopredio_cup = '").append(clavePredio).append("' ");
        sql.append(" AND anio ='").append(anio).append("'");
        rsh = new BeanHandler(MontoTotalDTOP5.class);
        MontoTotalDTOP5 montoP5 = (MontoTotalDTOP5) qr.query(sql.toString(), rsh);
       
        sql = new StringBuilder();    
        sql.append(" SELECT SUM(primera_ministracion+segunda_ministracion) AS result  FROM formularios.principal ");
        sql.append(" WHERE modulopredio_cup = '").append(clavePredio).append("' ");
        sql.append(" AND anio ='").append(anio).append("'");
        rsh = new BeanHandler(TotalMinistracionDTOP5.class);
        TotalMinistracionDTOP5 totalMinP5 = (TotalMinistracionDTOP5) qr.query(sql.toString(), rsh);
       
        sql = new StringBuilder();    
        sql.append(" SELECT ct.descripcion AS tipo_reforestacion ,SUM(CAST(fp.porcentaje_sobrevivencia AS INTEGER))  AS porcentaje_sobrevivencia  FROM formularios.principal  fp");
        sql.append(" INNER JOIN  catalogos.tiporeforestacion ct   ");
        sql.append(" ON ct.id = CAST (fp.id_tipo_reforestacion AS INTEGER)  ");
        sql.append(" WHERE fp.modulopredio_cup = '").append(clavePredio).append("' ");
        sql.append(" AND  fp.anio = '").append(anio).append("' ");
        sql.append(" AND ct.descripcion = 'Comercial' " );
        sql.append(" GROUP BY ct.descripcion  LIMIT 1");
        rsh = new BeanHandler(ModalidadPlantacionDTOP5.class);
        ModalidadPlantacionDTOP5 plantacion = (ModalidadPlantacionDTOP5) qr.query(sql.toString(), rsh);
        
        sql = new StringBuilder();    
        sql.append(" SELECT (SUM(formularios.primeraclareo.vta_m3)+ SUM(formularios.segundoaclareo.vta_m3)) as result ");
        sql.append(" FROM formularios.principal ");
        sql.append(" LEFT JOIN  formularios.primeraclareo ON formularios.primeraclareo.folio=formularios.principal.folio ");
        sql.append(" LEFT JOIN formularios.segundoaclareo ON formularios.segundoaclareo.folio=formularios.principal.folio ");
        sql.append(" WHERE modulopredio_cup='").append(clavePredio).append("' AND formularios.principal.anio='").append(anio).append("' "); 
        sql.append("AND formularios.primeraclareo.anio='").append(anio).append("'  AND  formularios.segundoaclareo.anio='").append(anio).append("' ");
        rsh = new BeanHandler(TotalAclareoDTOP5.class);
        TotalAclareoDTOP5 totalAclareo = (TotalAclareoDTOP5) qr.query(sql.toString(), rsh);
        
        sql = new StringBuilder();    
        sql.append(" SELECT SUM(formularios.ventaarboles.piezas) AS result ");
        sql.append(" FROM formularios.principal ");
        sql.append(" LEFT JOIN formularios.ventaarboles ON formularios.ventaarboles.folio=formularios.principal.folio ");
        sql.append(" WHERE formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and formularios.principal.anio='").append(anio).append("'");
        rsh = new BeanHandler(VentaArbolNavidadDTOP5.class);
        VentaArbolNavidadDTOP5 venta = (VentaArbolNavidadDTOP5) qr.query(sql.toString(), rsh);
        //eP5(superficie,montoP5,totalMinP5,plantacion,totalAclareo,venta)
       
        
        EjecutivoPrograma5 eP5 = new EjecutivoPrograma5(superficie,montoP5,totalMinP5,plantacion,totalAclareo,venta);
       return eP5;
       
   }

   
   
   public EjecutivoPrograma6 getReportePrograma6(UserDTO user, String clavePredio, String anio) throws SQLException{
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();    
        ReporteadorDAO daoReport= new ReporteadorDAO();
        sql.append(" SELECT  COUNT(nombre_predio) as result  FROM  formularios.principal ");
        sql.append(" WHERE formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and formularios.principal.anio='").append(anio).append("'");
        sql.append(" AND ( fecha_visita_diagnostico!='' and fecha_visita_diagnostico!=' ' and fecha_visita_diagnostico!='SD')");
        ResultSetHandler rsh = new BeanHandler(RegistroPredioDTOP6.class);
        RegistroPredioDTOP6 registroPredio = (RegistroPredioDTOP6) qr.query(sql.toString(), rsh);
        
        sql = new StringBuilder();    
        sql.append(" select fp.fecha_visita_diagnostico as fechaVisita ,plaga_enfermedad AS  plaga_enfermedad from formularios.principal fp ");
        sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("'");
        sql.append(" AND ( fecha_visita_diagnostico!='' and fecha_visita_diagnostico!=' ' and fecha_visita_diagnostico!='SD')");
        rsh = new BeanListHandler(FechaVisitaDiagnosticoDTOP6.class);
        List<FechaVisitaDiagnosticoDTOP6> fechaPlaga = (List<FechaVisitaDiagnosticoDTOP6>) qr.query(sql.toString(), rsh);
        
      for(FechaVisitaDiagnosticoDTOP6 Plaga: fechaPlaga)
        {
           if(Plaga.getPlaga_enfermedad()!=null && !Plaga.getPlaga_enfermedad().isEmpty() && !Plaga.getPlaga_enfermedad().equals("-1") )
            Plaga.setPlaga_enfermedad(daoReport.getMultiSelectContent(user, "descripcion","catalogos.plagaenfermedad", Plaga.getPlaga_enfermedad(), " id "));
           else
            Plaga.setPlaga_enfermedad(" ");   
        }
        
        sql = new StringBuilder();    
        sql.append(" select Observaciones_diagnostico as observaciones ");
        sql.append(" from formularios.principal ");
        sql.append(" WHERE formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and formularios.principal.anio='").append(anio).append("'");
        sql.append(" AND ( fecha_visita_diagnostico!='' and fecha_visita_diagnostico!=' ' and fecha_visita_diagnostico!='SD')");
        rsh = new BeanListHandler(ObservacionesDTOP6.class);
        List<ObservacionesDTOP6> observaciones = (List<ObservacionesDTOP6>) qr.query(sql.toString(), rsh);
        
        sql = new StringBuilder();    
        sql.append(" select SUM(superficie_total_boscosa)  as result ");
        sql.append(" from formularios.principal ");
        sql.append(" WHERE formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and formularios.principal.anio='").append(anio).append("'");
        sql.append(" AND ( fecha_visita_diagnostico!='' and fecha_visita_diagnostico!=' ' and fecha_visita_diagnostico!='SD')");
        rsh = new BeanHandler(SuperficieTotalBoscosaDTOP6.class);
        SuperficieTotalBoscosaDTOP6 supTotalBoscosa = (SuperficieTotalBoscosaDTOP6) qr.query(sql.toString(), rsh);
        //// SEGUNDA FILA 
        
        sql = new StringBuilder();
        sql.append(" SELECT  fecha_visita AS  fecha_visita ,plaga_enfermedad AS  plaga_enfermedad,hospedero AS hospedero ,num_notificacion as num_notificacion,superficie_notificada as superficie_notificada ,volumen_notificado as volumen_notificado,vigencia as vigencia");
        sql.append(" FROM formularios.principal");
        sql.append(" WHERE (formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" AND formularios.principal.anio='").append(anio).append("') ");
        sql.append(" AND ( fecha_visita!='' and fecha_visita!=' ' and fecha_visita!='SD')");
        rsh = new BeanListHandler(FechaPlagaNotVolDTOP6.class);
        List<FechaPlagaNotVolDTOP6> fechaplaganotvol = (List<FechaPlagaNotVolDTOP6>) qr.query(sql.toString(), rsh);
        
        for (FechaPlagaNotVolDTOP6 plagaHospedero:fechaplaganotvol)
            {
              if(plagaHospedero.getPlaga_enfermedad()!=null && !plagaHospedero.getPlaga_enfermedad().isEmpty() && !plagaHospedero.getPlaga_enfermedad().equals("-1"))  
               plagaHospedero.setPlaga_enfermedad(daoReport.getMultiSelectContent(user, "descripcion","catalogos.plagaenfermedad", plagaHospedero.getPlaga_enfermedad(), "id"));
              else 
                plagaHospedero.setPlaga_enfermedad(" ");  
              if(plagaHospedero.getHospedero()!=null && !plagaHospedero.getHospedero().isEmpty() && !plagaHospedero.getHospedero().equals("-1"))  
               plagaHospedero.setHospedero(daoReport.getMultiSelectContent(user, "descripcion","catalogos.hospedero", plagaHospedero.getHospedero(), "id"));
             else 
                plagaHospedero.setHospedero(" ");   
            }
        
        sql = new StringBuilder();
        sql.append(" select ROUND(SUM(asistencia_tecnica_ha),2) as asistencia_tecnica_ha ,ROUND(SUM(volumen_saneado),2) as volumen_saneado "); 
        sql.append(" from formularios.principal ");
        sql.append(" WHERE formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" AND formularios.principal.anio='").append(anio).append("'");
        sql.append(" AND (fecha_visita!='' and fecha_visita!=' ' and fecha_visita!='SD')");
        rsh = new BeanHandler(AsistenciaTeVolumenSDTOP6.class);
        AsistenciaTeVolumenSDTOP6 asistenciaVolumen = (AsistenciaTeVolumenSDTOP6) qr.query(sql.toString(), rsh);
               
        EjecutivoPrograma6 eP6 = new EjecutivoPrograma6(registroPredio,fechaPlaga,observaciones,fechaplaganotvol,asistenciaVolumen,supTotalBoscosa);
        return eP6;
}   
   
   
public EjecutivoPrograma71 getReportePrograma71(UserDTO user, String clavePredio, String anio) throws SQLException{

     DataSource ds = PoolDataSource.getDataSource(user);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();    
     sql.append(" SELECT  formularios.principal_incendios.numincendio as numincendio,SUM(formularios.principal_incendios.total) AS total, sum(formularios.principal_incendios.renuevo) as renuevo, ");
     sql.append(" SUM(formularios.principal_incendios.arbolado_adulto)  as arbolado_adulto, SUM(formularios.principal_incendios.arbusto) as arbusto, SUM(formularios.principal_incendios.pasto) AS pasto, ");
     sql.append(" SUM(formularios.principal_incendios.combatientes) AS combatientes,formularios.principal_incendios.combate2 as duracion, formularios.principal_incendios.observacion as observacion "); 
     sql.append(" FROM formularios.principal_incendios ");
     sql.append(" WHERE formularios.principal_incendios.modulopredio_cup='").append(clavePredio).append("' ");
     sql.append(" AND formularios.principal_incendios.anio='").append(anio).append("' ");
     sql.append(" GROUP BY formularios.principal_incendios.numincendio,formularios.principal_incendios.combate2,formularios.principal_incendios.observacion ");
     ResultSetHandler rsh = new BeanListHandler(TotalDTOP71.class);
     List<TotalDTOP71> total = (List<TotalDTOP71>) qr.query(sql.toString(), rsh);
     
    /* sql = new StringBuilder();    
     sql.append(" SELECT formularios.principal_incendios.numincendio as numincendio,formularios.principal_incendios.combate2 as duracion  ");
     sql.append(" , formularios.principal_incendios.observacion as observacion ");
     sql.append(" FROM formularios.principal_incendios ");
     sql.append(" WHERE formularios.principal_incendios.modulopredio_cup='").append(clavePredio).append("' ");
     sql.append(" AND formularios.principal_incendios.anio='").append(anio).append("'");
     rsh = new BeanListHandler(DuracionDTOP71.class);
     List<DuracionDTOP71> duracion = (List<DuracionDTOP71>) qr.query(sql.toString(), rsh);*/
    
     sql = new StringBuilder();    
     sql.append(" SELECT  catalogos.actividadrealizada.descripcion as actividad_realizada, SUM(formularios.principal_actividades.cantidad) as cantidad ");
     sql.append(" FROM formularios.principal_actividades ");
     sql.append(" INNER JOIN catalogos.actividadrealizada ON catalogos.actividadrealizada.id=CAST(formularios.principal_actividades.actividad_realizada  AS INTEGER) ");
     //sql.append(" INNER JOIN catalogos.trabajo ON catalogos.trabajo.id=CAST(formularios.principal_actividades.trabajo  AS INTEGER) ");
     sql.append(" WHERE formularios.principal_actividades.modulopredio_cup='").append(clavePredio).append("' ");
     sql.append(" AND formularios.principal_actividades.anio='").append(anio).append("'");
     sql.append(" AND catalogos.actividadrealizada.descripcion='Quema Controlada' ");
     sql.append(" GROUP BY catalogos.actividadrealizada.descripcion ");
     sql.append(" ORDER BY actividad_realizada ");
     rsh = new BeanListHandler(ActividadCantidadDTOP72.class);
     List<ActividadCantidadDTOP72> actividadCantidad = (List<ActividadCantidadDTOP72>) qr.query(sql.toString(), rsh);
    
     sql = new StringBuilder();    
     sql.append(" SELECT  SUM(formularios.principal_actividades.cantidad) as cantidad_brecha ");
     sql.append(" FROM formularios.principal_actividades ");
     sql.append(" INNER JOIN catalogos.actividadrealizada ON catalogos.actividadrealizada.id=CAST(formularios.principal_actividades.actividad_realizada  AS INTEGER) ");
     sql.append(" WHERE formularios.principal_actividades.modulopredio_cup='").append(clavePredio).append("' ");
     sql.append(" AND formularios.principal_actividades.anio='").append(anio).append("'");
     sql.append(" AND catalogos.actividadrealizada.descripcion='Quema Controlada' ");
     rsh = new BeanHandler(CantidadBrechaDTOP72.class);
     CantidadBrechaDTOP72 brecha = (CantidadBrechaDTOP72) qr.query(sql.toString(), rsh);
     
     sql = new StringBuilder();    
     sql.append(" SELECT  formularios.principal_actividades.observaciones as observaciones ");
     sql.append(" FROM formularios.principal_actividades ");
     sql.append(" INNER JOIN catalogos.actividadrealizada ON catalogos.actividadrealizada.id=CAST(formularios.principal_actividades.actividad_realizada  AS INTEGER) ");
     sql.append(" WHERE formularios.principal_actividades.modulopredio_cup='").append(clavePredio).append("' ");
     sql.append(" AND formularios.principal_actividades.anio='").append(anio).append("'");
     sql.append(" AND catalogos.actividadrealizada.descripcion='Quema Controlada' ");
     rsh = new BeanListHandler(ObservacionesBrechaDTOP72.class);
     List<ObservacionesBrechaDTOP72> observacionesBrecha = (List<ObservacionesBrechaDTOP72>) qr.query(sql.toString(), rsh);
     
     sql = new StringBuilder();    
     sql.append(" SELECT  catalogos.actividadrealizada.descripcion as actividad_realizada, SUM(formularios.principal_actividades.cantidad) as cantidad ");
     sql.append(" FROM formularios.principal_actividades ");
     sql.append(" INNER JOIN catalogos.actividadrealizada ON catalogos.actividadrealizada.id=CAST(formularios.principal_actividades.actividad_realizada  AS INTEGER) ");
     //sql.append(" INNER JOIN catalogos.trabajo ON catalogos.trabajo.id=CAST(formularios.principal_actividades.trabajo  AS INTEGER) ");
     sql.append(" WHERE formularios.principal_actividades.modulopredio_cup='").append(clavePredio).append("' ");
     sql.append(" AND formularios.principal_actividades.anio='").append(anio).append("'");
     sql.append(" AND catalogos.actividadrealizada.descripcion='Brecha Cortafuego' ");
     sql.append(" GROUP BY catalogos.actividadrealizada.descripcion ");
     sql.append(" ORDER BY actividad_realizada ");
     rsh = new BeanListHandler(ActividadCantidadQuemaDTOP72.class);
     List<ActividadCantidadQuemaDTOP72> actividadCantidadQuema = (List<ActividadCantidadQuemaDTOP72>) qr.query(sql.toString(), rsh);
    
     sql = new StringBuilder();    
     sql.append(" SELECT  SUM(formularios.principal_actividades.cantidad) as cantidad_quema ");
     sql.append(" FROM formularios.principal_actividades ");
     sql.append(" INNER JOIN catalogos.actividadrealizada ON catalogos.actividadrealizada.id=CAST(formularios.principal_actividades.actividad_realizada  AS INTEGER) ");
     sql.append(" WHERE formularios.principal_actividades.modulopredio_cup='").append(clavePredio).append("' ");
     sql.append(" AND formularios.principal_actividades.anio='").append(anio).append("'");
     sql.append(" AND catalogos.actividadrealizada.descripcion='Brecha Cortafuego' ");
     rsh = new BeanHandler(CantidadQuemaDTOP72.class);
     CantidadQuemaDTOP72 CantidadQuema = (CantidadQuemaDTOP72) qr.query(sql.toString(), rsh);
     
     sql = new StringBuilder();    
     sql.append(" SELECT  formularios.principal_actividades.observaciones as observaciones ");
     sql.append(" FROM formularios.principal_actividades ");
     sql.append(" INNER JOIN catalogos.actividadrealizada ON catalogos.actividadrealizada.id=CAST(formularios.principal_actividades.actividad_realizada  AS INTEGER) ");
     sql.append(" WHERE formularios.principal_actividades.modulopredio_cup='").append(clavePredio).append("' ");
     sql.append(" AND formularios.principal_actividades.anio='").append(anio).append("'");
     sql.append(" AND catalogos.actividadrealizada.descripcion='Brecha Cortafuego' ");
     rsh = new BeanListHandler(ObservacionesQuemaDTOP72.class);
     List<ObservacionesQuemaDTOP72> observacionesQuema = (List<ObservacionesQuemaDTOP72>) qr.query(sql.toString(), rsh);
     
     
     
     
     
     
    
  ///*duracion
  EjecutivoPrograma71 eP71 = new  EjecutivoPrograma71(total,actividadCantidad,brecha,observacionesBrecha,actividadCantidadQuema,CantidadQuema,observacionesQuema);
  return eP71;
  }   
   
public EjecutivoPrograma8 getReportePrograma8(UserDTO user, String clavePredio, String anio)
        throws SQLException{
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();    
        sql.append(" SELECT  COUNT(cta.descripcion) AS operativos_coordinados from formularios.principal fp ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta ");
        sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("' ");
        sql.append(" AND  cta.descripcion = 'OPERATIVOS COORDINADOS' ");
        ResultSetHandler rsh = new BeanHandler(TipoAccionDTOP8.class);
        TipoAccionDTOP8 accion1 = (TipoAccionDTOP8) qr.query(sql.toString(), rsh);
        
        
        sql = new StringBuilder();    
        sql.append(" SELECT  COUNT(cta.descripcion) as filtro_transporte from formularios.principal fp ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta ");
        sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("' ");
        sql.append(" AND  cta.descripcion = 'FILTRO DE REVISIÓN AL TRANSPORTE' ");
         rsh = new BeanHandler(TipoAccionDTOP8.class);
        TipoAccionDTOP8 accion2 = (TipoAccionDTOP8) qr.query(sql.toString(), rsh);
        
        sql = new StringBuilder();    
        sql.append(" SELECT  COUNT(cta.descripcion) AS inspeccion_industria from formularios.principal fp ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta ");
        sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("' ");
        sql.append(" AND  cta.descripcion = 'INSPECCIÓN A INDUSTRIA' ");
         rsh = new BeanHandler(TipoAccionDTOP8.class);
        TipoAccionDTOP8 accion3 = (TipoAccionDTOP8) qr.query(sql.toString(), rsh);
        
        sql = new StringBuilder();    
        sql.append(" SELECT  COUNT(cta.descripcion) as inspeccion_predios from formularios.principal fp ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta ");
        sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("' ");
        sql.append(" AND  cta.descripcion = 'INSPECCIÓN A PREDIOS' ");
        rsh = new BeanHandler(TipoAccionDTOP8.class);
        TipoAccionDTOP8 accion4 = (TipoAccionDTOP8) qr.query(sql.toString(), rsh);
        
        sql = new StringBuilder();    
        sql.append(" SELECT  COUNT(cta.descripcion) AS dictamen_pericial from formularios.principal fp ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta ");
        sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("' ");
        sql.append(" AND  cta.descripcion = 'DICTÁMENES PERICIALES' ");
        rsh = new BeanHandler(TipoAccionDTOP8.class);
        TipoAccionDTOP8 accion5 = (TipoAccionDTOP8) qr.query(sql.toString(), rsh);
        //(String dictamen_pericial, String inspeccion_predios, String inspeccion_industria, String filtro_transporte, String operativos_coordinados)
        TipoAccionDTOP8 accion = new TipoAccionDTOP8(accion5.getDictamen_pericial(),accion4.getInspeccion_predios(),accion3.getInspeccion_industria(),accion2.getFiltro_transporte(),accion1.getOperativos_coordinados());
        
        DocumentacionGeneralDTOP8 documentacionGeneral = new DocumentacionGeneralDTOP8();
        sql = new StringBuilder();    
        sql.append(" select ct.descripcion as documento, num_documento as numero_documento from formularios.principal fp ");
        sql.append(" inner join catalogos.tipodocumento ct ");
        sql.append(" on ct.id = cast(fp.tipo_documento as integer) ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta ");
        sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("') ");
        sql.append(" AND  cta.descripcion = 'OPERATIVOS COORDINADOS' ");
        rsh = new BeanHandler(DocumentacionDTOP8.class);
        DocumentacionDTOP8 documentacionOperativos = (DocumentacionDTOP8) qr.query(sql.toString(), rsh);
        documentacionGeneral.setDocumentacionOperativos(documentacionOperativos);
        
        sql = new StringBuilder();    
        sql.append(" select ct.descripcion as documento, num_documento as numero_documento from formularios.principal fp ");
        sql.append(" inner join catalogos.tipodocumento ct ");
        sql.append(" on ct.id = cast(fp.tipo_documento as integer) ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta ");
        sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("') ");
        sql.append(" AND  cta.descripcion = 'FILTRO DE REVISIÓN AL TRANSPORTE' ");
        rsh = new BeanHandler(DocumentacionDTOP8.class);
        DocumentacionDTOP8 documentacionTransporte = (DocumentacionDTOP8) qr.query(sql.toString(), rsh);
        documentacionGeneral.setDocumentacionTransporte(documentacionTransporte);
        
        sql = new StringBuilder();    
        sql.append(" select ct.descripcion as documento, num_documento as numero_documento from formularios.principal fp ");
        sql.append(" inner join catalogos.tipodocumento ct ");
        sql.append(" on ct.id = cast(fp.tipo_documento as integer) ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta ");
        sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("') ");
        sql.append(" AND  cta.descripcion = 'INSPECCIÓN A INDUSTRIA' ");
        rsh = new BeanHandler(DocumentacionDTOP8.class);
        DocumentacionDTOP8 documentacionIndustria = (DocumentacionDTOP8) qr.query(sql.toString(), rsh);
        documentacionGeneral.setDocumentacionIndustria(documentacionIndustria);
        
        sql = new StringBuilder();    
        sql.append(" select ct.descripcion as documento, num_documento as numero_documento from formularios.principal fp ");
        sql.append(" inner join catalogos.tipodocumento ct ");
        sql.append(" on ct.id = cast(fp.tipo_documento as integer) ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta ");
        sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("') ");
        sql.append(" AND  cta.descripcion = 'INSPECCIÓN A PREDIOS' ");
        rsh = new BeanHandler(DocumentacionDTOP8.class);
        DocumentacionDTOP8 documentacionPredios = (DocumentacionDTOP8) qr.query(sql.toString(), rsh);
        documentacionGeneral.setDocumentacionPredios(documentacionPredios);
        
        
        sql = new StringBuilder();    
        sql.append(" select ct.descripcion as documento, num_documento as numero_documento from formularios.principal fp ");
        sql.append(" inner join catalogos.tipodocumento ct ");
        sql.append(" on ct.id = cast(fp.tipo_documento as integer) ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta ");
        sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("') ");
        sql.append(" AND  cta.descripcion = 'DICTÁMENES PERICIALES' ");
        rsh = new BeanHandler(DocumentacionDTOP8.class);
        DocumentacionDTOP8 documentacionPericiales = (DocumentacionDTOP8) qr.query(sql.toString(), rsh);
        documentacionGeneral.setDocumentacionPreciciales(documentacionPericiales);
        
        ObservacionesProductosGeneralDTOP8 observacionesGeneral = new ObservacionesProductosGeneralDTOP8();
        sql = new StringBuilder();    
        sql.append(" SELECT  Count(fp.observaciones) AS observaciones, cast (sum(forprod.total_productos_asegurados) as integer) as total_productos_asegurados ");
        sql.append(" from formularios.principal fp  ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta ");
        sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" left join formularios.productos forprod ");
        sql.append(" on forprod.folio=fp.folio ");
        sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("') ");
        sql.append(" AND  cta.descripcion = 'OPERATIVOS COORDINADOS' ");
        rsh = new BeanHandler(ObservacionesProductosDetalleDTOP8.class);
        ObservacionesProductosDetalleDTOP8 observacionesOperativos = (ObservacionesProductosDetalleDTOP8) qr.query(sql.toString(), rsh);
        observacionesGeneral.setObservacionesProductosOperativos(observacionesOperativos);

        sql = new StringBuilder();    
        sql.append(" SELECT  Count(fp.observaciones) AS observaciones, cast (sum(forprod.total_productos_asegurados) as integer) as total_productos_asegurados ");
        sql.append(" from formularios.principal fp  ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta ");
        sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" left join formularios.productos forprod ");
        sql.append(" on forprod.folio=fp.folio ");
        sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("') ");
        sql.append(" AND  cta.descripcion = 'FILTRO DE REVISIÓN AL TRANSPORTE' ");
        rsh = new BeanHandler(ObservacionesProductosDetalleDTOP8.class);
        ObservacionesProductosDetalleDTOP8 observacionesTransporte = (ObservacionesProductosDetalleDTOP8) qr.query(sql.toString(), rsh);
        observacionesGeneral.setObservacionesProductosTransporte(observacionesTransporte);
        
        sql = new StringBuilder();    
        sql.append(" SELECT  Count(fp.observaciones) AS observaciones, cast (sum(forprod.total_productos_asegurados) as integer) as total_productos_asegurados ");
        sql.append(" from formularios.principal fp  ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta ");
        sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" left join formularios.productos forprod ");
        sql.append(" on forprod.folio=fp.folio ");
        sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("') ");
        sql.append(" AND  cta.descripcion = 'INSPECCIÓN A INDUSTRIA' ");
        rsh = new BeanHandler(ObservacionesProductosDetalleDTOP8.class);
        ObservacionesProductosDetalleDTOP8 observacionesIndustria = (ObservacionesProductosDetalleDTOP8) qr.query(sql.toString(), rsh);
        observacionesGeneral.setObservacionesProductosIndustria(observacionesIndustria);
        
        sql = new StringBuilder();    
        sql.append(" SELECT  Count(fp.observaciones) AS observaciones, cast (sum(forprod.total_productos_asegurados) as integer) as total_productos_asegurados ");
        sql.append(" from formularios.principal fp  ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta ");
        sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" left join formularios.productos forprod ");
        sql.append(" on forprod.folio=fp.folio ");
        sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("') ");
        sql.append(" AND  cta.descripcion = 'INSPECCIÓN A PREDIOS' ");
        rsh = new BeanHandler(ObservacionesProductosDetalleDTOP8.class);
        ObservacionesProductosDetalleDTOP8 observacionesPredio = (ObservacionesProductosDetalleDTOP8) qr.query(sql.toString(), rsh);
        observacionesGeneral.setObservacionesProductosPredios(observacionesPredio);
        
        sql = new StringBuilder();    
        sql.append(" SELECT  Count(fp.observaciones) AS observaciones, cast (sum(forprod.total_productos_asegurados) as integer) as total_productos_asegurados ");
        sql.append(" from formularios.principal fp  ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta ");
        sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" left join formularios.productos forprod ");
        sql.append(" on forprod.folio=fp.folio ");
        sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("') ");
        sql.append(" AND  cta.descripcion = 'DICTÁMENES PERICIALES' ");
        rsh = new BeanHandler(ObservacionesProductosDetalleDTOP8.class);
        ObservacionesProductosDetalleDTOP8 observacionesPericiales = (ObservacionesProductosDetalleDTOP8) qr.query(sql.toString(), rsh);
        observacionesGeneral.setObservacionesProductosPericiales(observacionesPericiales);
       
        ParticipantesGeneralDTOP8 participantes = new ParticipantesGeneralDTOP8();
        sql = new StringBuilder();
        sql.append(" select ca.descripcion AS dependencia ,Sum(formularios.participantes.cantidad) as no_participantes ");
        sql.append(" FROM formularios.principal fp   ");
        sql.append("  inner join catalogos.tipodocumento ct  ");
        sql.append(" on ct.id = cast(fp.tipo_documento as integer)  ");
        sql.append(" left join formularios.participantes on formularios.participantes.folio = fp.folio ");
        sql.append(" inner join catalogos.dependencia ca  ");
        sql.append(" on ca.id = cast(fp.tipo_documento as integer) ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta  ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("') ");
        sql.append(" AND  cta.descripcion = 'OPERATIVOS COORDINADOS' ");
        sql.append(" group by ca.descripcion ");
        sql.append("  ORDER BY ca.descripcion ASC ");
        rsh = new BeanListHandler(ParticipantesDetalleDTOP8.class);
        List<ParticipantesDetalleDTOP8> participantesCoordinados = (List<ParticipantesDetalleDTOP8>) qr.query(sql.toString(), rsh);
        participantes.setParticiapntesOperativos(participantesCoordinados);
        
        sql = new StringBuilder();
        sql.append(" select ca.descripcion AS dependencia ,Sum(formularios.participantes.cantidad) as no_participantes ");
        sql.append(" FROM formularios.principal fp   ");
        sql.append("  inner join catalogos.tipodocumento ct  ");
        sql.append(" on ct.id = cast(fp.tipo_documento as integer)  ");
        sql.append(" left join formularios.participantes on formularios.participantes.folio = fp.folio ");
        sql.append(" inner join catalogos.dependencia ca  ");
        sql.append(" on ca.id = cast(fp.tipo_documento as integer) ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta  ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("') ");
        sql.append(" AND  cta.descripcion = 'FILTRO DE REVISIÓN AL TRANSPORTE' ");
        sql.append(" group by ca.descripcion ");
        sql.append("  ORDER BY ca.descripcion ASC ");
        rsh = new BeanListHandler(ParticipantesDetalleDTOP8.class);
        List<ParticipantesDetalleDTOP8> participantesTransporte = (List<ParticipantesDetalleDTOP8>) qr.query(sql.toString(), rsh);
        participantes.setParticiapntesTransporte(participantesTransporte);
        
        sql = new StringBuilder();
        sql.append(" select ca.descripcion AS dependencia ,Sum(formularios.participantes.cantidad) as no_participantes ");
        sql.append(" FROM formularios.principal fp   ");
        sql.append("  inner join catalogos.tipodocumento ct  ");
        sql.append(" on ct.id = cast(fp.tipo_documento as integer)  ");
        sql.append(" left join formularios.participantes on formularios.participantes.folio = fp.folio ");
        sql.append(" inner join catalogos.dependencia ca  ");
        sql.append(" on ca.id = cast(fp.tipo_documento as integer) ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta  ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("') ");
        sql.append(" AND  cta.descripcion = 'INSPECCIÓN A INDUSTRIA' ");
        sql.append(" group by ca.descripcion ");
        sql.append("  ORDER BY ca.descripcion ASC ");
        rsh = new BeanListHandler(ParticipantesDetalleDTOP8.class);
        List<ParticipantesDetalleDTOP8> participantesIndustria= (List<ParticipantesDetalleDTOP8>) qr.query(sql.toString(), rsh);
        participantes.setParticiapntesTransporte(participantesTransporte);
        
        sql = new StringBuilder();
        sql.append(" select ca.descripcion AS dependencia ,Sum(formularios.participantes.cantidad) as no_participantes ");
        sql.append(" FROM formularios.principal fp   ");
        sql.append("  inner join catalogos.tipodocumento ct  ");
        sql.append(" on ct.id = cast(fp.tipo_documento as integer)  ");
        sql.append(" left join formularios.participantes on formularios.participantes.folio = fp.folio ");
        sql.append(" inner join catalogos.dependencia ca  ");
        sql.append(" on ca.id = cast(fp.tipo_documento as integer) ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta  ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("') ");
        sql.append(" AND  cta.descripcion = 'OPERATIVOS COORDINADOS' ");
        sql.append(" group by ca.descripcion ");
        sql.append("  ORDER BY ca.descripcion ASC ");
        rsh = new BeanListHandler(ParticipantesDetalleDTOP8.class);
        List<ParticipantesDetalleDTOP8> participantesPredios = (List<ParticipantesDetalleDTOP8>) qr.query(sql.toString(), rsh);
        participantes.setParticiapntesPredios(participantesPredios);
        
        sql = new StringBuilder();
        sql.append(" select ca.descripcion AS dependencia ,Sum(formularios.participantes.cantidad) as no_participantes ");
        sql.append(" FROM formularios.principal fp   ");
        sql.append("  inner join catalogos.tipodocumento ct  ");
        sql.append(" on ct.id = cast(fp.tipo_documento as integer)  ");
        sql.append(" left join formularios.participantes on formularios.participantes.folio = fp.folio ");
        sql.append(" inner join catalogos.dependencia ca  ");
        sql.append(" on ca.id = cast(fp.tipo_documento as integer) ");
        sql.append(" INNER JOIN catalogos.tipoaccion cta  ON cta.id = cast(fp.tipo_accion AS INTEGER) ");
        sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
        sql.append(" and fp.anio='").append(anio).append("') ");
        sql.append(" AND  cta.descripcion = 'OPERATIVOS COORDINADOS' ");
        sql.append(" group by ca.descripcion ");
        sql.append("  ORDER BY ca.descripcion ASC ");
        rsh = new BeanListHandler(ParticipantesDetalleDTOP8.class);
        List<ParticipantesDetalleDTOP8> participantesPericiales = (List<ParticipantesDetalleDTOP8>) qr.query(sql.toString(), rsh);
        participantes.setParticiapntesPericiales(participantesPericiales);
        
        PersonasAseguradasGeneralDTOP8 personas= new PersonasAseguradasGeneralDTOP8();
        
       sql = new StringBuilder();
       sql.append(" SELECT  Sum(total_personas) AS personas_aseguradas ");
       sql.append(" from formularios.principal fp  ");
       sql.append(" INNER JOIN catalogos.tipoaccion cta ");
       sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) "); 
       sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
       sql.append(" and fp.anio='").append(anio).append("') ");
       sql.append(" AND  cta.descripcion = 'OPERATIVOS COORDINADOS' ");  
       rsh = new BeanHandler(PersonasAseguradasDetalleDTOP8.class);
       PersonasAseguradasDetalleDTOP8 personasOperativos = (PersonasAseguradasDetalleDTOP8) qr.query(sql.toString(), rsh);
       personas.setPersonasOperativos(personasOperativos);
       
       sql = new StringBuilder();
       sql.append(" SELECT  Sum(total_personas) AS personas_aseguradas ");
       sql.append(" from formularios.principal fp  ");
       sql.append(" INNER JOIN catalogos.tipoaccion cta ");
       sql.append(" ON cta.id = cast(fp.tipo_accion AS INTEGER) "); 
       sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
       sql.append(" and fp.anio='").append(anio).append("') ");
       sql.append(" AND  cta.descripcion = 'INSPECCIÓN A PREDIOS' ");
       rsh = new BeanHandler(PersonasAseguradasDetalleDTOP8.class);
       PersonasAseguradasDetalleDTOP8 personasPredios = (PersonasAseguradasDetalleDTOP8) qr.query(sql.toString(), rsh);
       personas.setPersonasPredios(personasPredios);
       
//
  EjecutivoPrograma8 eP8 = new EjecutivoPrograma8(accion,documentacionGeneral,observacionesGeneral,participantes,personas);
  return eP8;
}   
    
public EjecutivoPrograma10 getReportePrograma10(UserDTO user, String clavePredio, String anio) throws SQLException
  {
     DataSource ds = PoolDataSource.getDataSource(user);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();    
    sql.append(" SELECT fp.superficie_aprobada as superficie_aprobada");
    sql.append(" FROM formularios.principal fp ");
    sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' ");
    sql.append(" AND fp.anio='").append(anio).append("'");
    ResultSetHandler rsh= new BeanHandler(SuperficieAprobadaDTOP10.class);
    SuperficieAprobadaDTOP10 superficieAprobada = (SuperficieAprobadaDTOP10) qr.query(sql.toString(), rsh);
    
    sql = new StringBuilder();    
    sql.append(" SELECT fp.monto_aprobado ");
    sql.append(" FROM formularios.principal fp ");
    sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' ");
    sql.append(" AND fp.anio='").append(anio).append("'");
    sql.append( " LIMIT 1 ");
    rsh= new BeanHandler(MontoAprobadoDTOP10.class);
    MontoAprobadoDTOP10 montoAprobado = (MontoAprobadoDTOP10) qr.query(sql.toString(), rsh); 
    
    sql = new StringBuilder();    
    sql.append(" SELECT sum(fp.primera_ministracion+fp.segunda_ministracion) as total_ministracion ");
    sql.append(" FROM formularios.principal fp ");
    sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' ");
    sql.append(" AND fp.anio='").append(anio).append("'");
    rsh= new BeanHandler(TotalMinistracionDTOP10.class);
    TotalMinistracionDTOP10 totalMinistracion = (TotalMinistracionDTOP10) qr.query(sql.toString(), rsh); 
    
    sql = new StringBuilder();    
    sql.append(" Select  formularios.actividades.tipo_actividad as tipo_actividad, ");
    sql.append(" formularios.actividades.meta_comprometida as meta_comprometida, ");
    sql.append(" catalogos.unidadmedida.descripcion as unidad_medida,");        
    sql.append(" formularios.actividades.numero_jornales as numero_jornales ");
    sql.append("  from formularios.principal "); 
    sql.append(" left join formularios.actividades on ");
    sql.append(" formularios.actividades.folio=formularios.principal.folio ");
    sql.append(" inner join catalogos.unidadmedida on catalogos.unidadmedida.id=CAST(formularios.actividades.unidad_medida AS INTEGER)");
    sql.append(" WHERE (formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
    sql.append(" AND formularios.principal.anio='").append(anio).append("')");
    sql.append(" AND (formularios.actividades.tipo_actividad!='' OR ");
    sql.append("formularios.actividades.tipo_actividad!=null  )");
    rsh= new BeanListHandler(DatosGeneralesDTOP10.class);
    List<DatosGeneralesDTOP10> datosGenerales = (List<DatosGeneralesDTOP10>) qr.query(sql.toString(), rsh); 
    
    sql = new StringBuilder();    
    sql.append(" SELECT fp.observaciones_2  AS observaciones");
    sql.append(" FROM formularios.principal fp ");
    sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' ");
    sql.append(" AND fp.anio='").append(anio).append("'");
    rsh= new BeanListHandler(ObservacionesDTOP10.class);
    List<ObservacionesDTOP10> observaciones = (List<ObservacionesDTOP10>) qr.query(sql.toString(), rsh); 
    
    
    
    
  EjecutivoPrograma10 eP10 = new  EjecutivoPrograma10(superficieAprobada,montoAprobado,totalMinistracion,datosGenerales,observaciones);
  return eP10;
  }

public EjecutivoPrograma11 getReportePrograma11(UserDTO user, String clavePredio, String anio) throws SQLException
 {
     DataSource ds = PoolDataSource.getDataSource(user);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();    
     sql.append(" SELECT tipo_apoyo  as tipo_apoyo");
     sql.append(" FROM formularios.principal ");
     sql.append(" WHERE formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
     sql.append(" AND formularios.principal.anio='").append(anio).append("'");
     ResultSetHandler rsh = new BeanListHandler(TipoApoyoDTOP11.class);
     List<TipoApoyoDTOP11> tipoApoyo =(List<TipoApoyoDTOP11>) qr.query(sql.toString(), rsh);
     
     sql = new StringBuilder();    
     sql.append(" SELECT tipo_apoyo AS tipo_apoyo, sum(total) as costo_total ");
     sql.append(" FROM formularios.principal ");
     sql.append(" WHERE formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
     sql.append(" AND formularios.principal.anio='").append(anio).append("'");
     sql.append(" GROUP BY tipo_apoyo ");
     rsh = new BeanListHandler(CostoApoyoDTOP11.class);
     List<CostoApoyoDTOP11> costoApoyo =(List<CostoApoyoDTOP11>) qr.query(sql.toString(), rsh);
     
     sql = new StringBuilder();    
     sql.append(" SELECT  SUM(aportacion_gubernamental) as aportacion_gubernamental ,SUM(aportacion_productor) as aportacion_productor, ");
     sql.append(" SUM(empleos_generados) AS empleos_generados ");
     sql.append(" FROM formularios.principal ");
     sql.append(" WHERE formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
     sql.append(" AND formularios.principal.anio='").append(anio).append("'");
     rsh= new BeanHandler(AportacionesDTOP11.class); 
     AportacionesDTOP11 aportaciones = (AportacionesDTOP11)qr.query(sql.toString(), rsh);
     
     sql = new StringBuilder();    
     sql.append(" SELECT activo ");
     sql.append(" FROM formularios.principal ");
     sql.append(" WHERE formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
     sql.append(" AND formularios.principal.anio='").append(anio).append("'");
     rsh= new BeanListHandler(ActivoDTOP11.class); 
     List<ActivoDTOP11> activo = (List<ActivoDTOP11>)qr.query(sql.toString(), rsh);
     
     
     
     //EjecutivoPrograma11(tipoApoyo,costoApoyo,aportaciones,activo);
 EjecutivoPrograma11 eP11 = new EjecutivoPrograma11(tipoApoyo,costoApoyo,aportaciones,activo);
 return eP11;
 }

public EjecutivoPrograma12 getReportePrograma12(UserDTO user, String clavePredio, String anio) throws SQLException
{
  DataSource ds = PoolDataSource.getDataSource(user);
  QueryRunner qr = new QueryRunner(ds);
  StringBuilder sql = new StringBuilder();    
  PresenciaIncendioDTOP12 aux=new PresenciaIncendioDTOP12();
  
  /*SELECT formularios.sitios.folio, formularios.sitios.conglomerado as conglomerado, formularios.sitios.sitio, catalogos.especie.descripcion,
sum(formularios.sitios.volumen) as volumnen 
from formularios.principal
left join formularios.sitios on formularios.sitios.folio=formularios.principal.folio
inner join catalogos.especie on catalogos.especie.id=cast(formularios.sitios.grupo_especies as integer)
left join formularios.s400  on formularios.s400.folio=formularios.principal.folio
where formularios.principal.modulopredio_cup='150811200130002' and formularios.principal.anio='2010'
--and formularios.sitios.conglomerado=formularios.s400.num_conglomerado and formularios.sitios.sitio=formularios.s400.s400_numero_sitio
--and formularios.sitios.num_arbol=cast(formularios.s400.numero_arbol as integer )
group by formularios.sitios.conglomerado, formularios.sitios.sitio,catalogos.especie.descripcion,formularios.sitios.folio  
order by conglomerado asc, sitio asc
*/
  sql.append(" SELECT formularios.sitios.conglomerado as conglomerado, formularios.sitios.sitio as sitio,formularios.sitios.num_arbol as num_arbol, catalogos.especie.descripcion especie, ");
  sql.append(" sum(formularios.sitios.volumen) as volumen ");
  sql.append(" FROM formularios.principal ");
  sql.append(" LEFT JOIN formularios.sitios ");
  sql.append(" ON formularios.sitios.folio=formularios.principal.folio ");
  sql.append(" inner join catalogos.especie on catalogos.especie.id=cast(formularios.sitios.grupo_especies as integer) ");
  sql.append(" WHERE (formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
  sql.append(" AND formularios.principal.anio='").append(anio).append("') ");
  sql.append(" GROUP BY formularios.sitios.conglomerado, formularios.sitios.sitio,formularios.sitios.num_arbol,catalogos.especie.descripcion");
  sql.append(" ORDER BY 1");
  ResultSetHandler rsh = new BeanListHandler(ConglomeradoSitioDTOP12.class);
  List<ConglomeradoSitioDTOP12> conglomerado= (List<ConglomeradoSitioDTOP12>) qr.query(sql.toString(), rsh);
  
  if(conglomerado.size()>0 && !conglomerado.isEmpty())
    {
        
         for(ConglomeradoSitioDTOP12 con: conglomerado)
         { 
          sql = new StringBuilder();  
          sql.append(" SELECT sum(formularios.s400.ima) ");
          sql.append(" FROM formularios.principal ");
          sql.append(" LEFT JOIN formularios.s400 ON formularios.s400.folio=formularios.principal.folio ");
          sql.append(" WHERE (formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
          sql.append(" AND formularios.principal.anio='").append(anio).append("') ");
          sql.append("and (formularios.s400.s400_numero_sitio='").append(con.getSitio()).append("' and formularios.s400.numero_arbol='").append(con.getNum_arbol()).append("')");  
          rsh=new BeanHandler(ResultString.class);
          ResultString ima = (ResultString)qr.query(sql.toString(), rsh);
          if(ima!=null && ima.getResult()!=null && !ima.getResult().equals("null") && ima.getResult().equals("undefined"))
              con.setIma(ima.getResult());
          else
             con.setIma("0.0"); 
          
         }
    }
  
  /*
  sql = new StringBuilder();    
  sql.append(" SELECT catalogos.arbolespecie.descripcion AS especie, sum(formularios.S400.ima) AS ima ");
  sql.append(" FROM formularios.principal ");
  sql.append(" left join formularios.S400  ON  formularios.S400.folio =formularios.principal.folio ");
  sql.append(" left join formularios.sitios  ON formularios.sitios.folio=formularios.principal.folio");
  sql.append(" inner join catalogos.arbolespecie  ON catalogos.arbolespecie.id=cast( formularios.sitios.especie as integer)");
  sql.append(" WHERE (formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
  sql.append(" AND formularios.principal.anio='").append(anio).append("') ");
  sql.append(" AND formularios.sitios.sitio='1' ");
  sql.append(" GROUP BY catalogos.arbolespecie.descripcion ");
  sql.append(" order by 1 ");
  rsh = new BeanListHandler(IMADTOP12.class);
  List<IMADTOP12> imasitio1= (List<IMADTOP12>) qr.query(sql.toString(), rsh);
  */
  sql = new StringBuilder();    
  sql.append(" SELECT csn.descripcion as presencia_incendio ");
  sql.append(" FROM formularios.principal fp ");
  sql.append(" INNER JOIN catalogos.sino csn ON csn.id = cast(fp.presencia_incendio_1_5_anios as integer) ");
  sql.append(" left join formularios.sitios  ON formularios.sitios.folio=fp.folio");
  sql.append(" WHERE fp.modulopredio_cup='").append(clavePredio).append("' ");
  sql.append(" AND fp.anio='").append(anio).append("'");
  sql.append(" AND formularios.sitios.sitio='1' AND cast(fp.presencia_incendio_1_5_anios as integer)=1 ");
  sql.append(" GROUP BY csn.descripcion ");
  rsh = new BeanHandler(PresenciaIncendioDTOP12.class);
  PresenciaIncendioDTOP12 incendio= (PresenciaIncendioDTOP12) qr.query(sql.toString(), rsh);
  if(incendio==null)
  {
      aux.setPresencia_incendio("No");
      incendio=aux;
  }


/*
  
  //columna dos 
  
  sql = new StringBuilder();    
  sql.append(" SELECT formularios.sitios.sitio AS  sitio, formularios.sitios.conglomerado AS conglomerado ");
  sql.append(" FROM formularios.principal ");
  sql.append(" LEFT JOIN formularios.sitios ");
  sql.append(" ON formularios.sitios.folio=formularios.principal.folio ");
  sql.append(" WHERE (formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
  sql.append(" AND formularios.principal.anio='").append(anio).append("') ");
  sql.append(" AND formularios.sitios.sitio='2' ");
  sql.append(" GROUP BY formularios.sitios.sitio,formularios.sitios.conglomerado");
  sql.append(" ORDER BY 2");
  rsh = new BeanListHandler(ConglomeradoSitioDTOP12.class);
  List<ConglomeradoSitioDTOP12> conglomerado2= (List<ConglomeradoSitioDTOP12>) qr.query(sql.toString(), rsh);
  
  
  sql = new StringBuilder();    
  sql.append(" SELECT ca.descripcion AS especie ,fp.fecha AS fecha,sum(fs.volumen)*25  AS volumen ");
  sql.append(" FROM formularios.principal fp  ");
  sql.append(" left JOIN formularios.sitios fs ON fp.folio = fs.folio  ");
  sql.append(" inner join catalogos.arbolespecie ca ON ca.id = cast(fs.especie as integer) ");
  sql.append(" where (fp.modulopredio_cup='").append(clavePredio).append("' and fp.anio='").append(anio).append("') and fs.sitio='2'");
  sql.append(" GROUP BY ca.descripcion ,fp.fecha");
  sql.append(" ORDER BY 1");
  rsh = new BeanListHandler(VolumenFechaEspecieDTOP12.class);
  List<VolumenFechaEspecieDTOP12> volumenespeciefechaSitio2= (List<VolumenFechaEspecieDTOP12>) qr.query(sql.toString(), rsh);
  
  sql = new StringBuilder();    
  sql.append(" SELECT catalogos.arbolespecie.descripcion as especie, sum(formularios.S400.ima)  as ima ");
  sql.append(" FROM formularios.principal ");
  sql.append(" left join formularios.S400  ON  formularios.S400.folio =formularios.principal.folio ");
  sql.append(" left join formularios.sitios  ON formularios.sitios.folio=formularios.principal.folio");
  sql.append(" inner join catalogos.arbolespecie  ON catalogos.arbolespecie.id=cast( formularios.sitios.especie as integer)");
  sql.append(" WHERE (formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
  sql.append(" AND formularios.principal.anio='").append(anio).append("') ");
  sql.append(" AND formularios.sitios.sitio='2' ");
  sql.append(" GROUP BY catalogos.arbolespecie.descripcion ");
  sql.append(" order by 1 ");
  rsh = new BeanListHandler(IMADTOP12.class);
  List<IMADTOP12> imasitio2= (List<IMADTOP12>) qr.query(sql.toString(), rsh);
  
  sql = new StringBuilder();    
  sql.append(" SELECT csn.descripcion as presencia_incendio ");
  sql.append(" FROM formularios.principal fp ");
  sql.append(" INNER JOIN catalogos.sino csn ON csn.id = cast(fp.presencia_incendio_1_5_anios as integer) ");
  sql.append(" left join formularios.sitios  ON formularios.sitios.folio=fp.folio");
  sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
  sql.append(" AND fp.anio='").append(anio).append("') ");
  sql.append(" and formularios.sitios.sitio='2' AND cast(fp.presencia_incendio_1_5_anios as integer)=1");
  sql.append(" GROUP BY csn.descripcion ");
  rsh = new BeanHandler(PresenciaIncendioDTOP12.class);
  PresenciaIncendioDTOP12 incendioSitio2= (PresenciaIncendioDTOP12) qr.query(sql.toString(), rsh);
  if(incendioSitio2==null)
  {
      aux.setPresencia_incendio("No");
      incendioSitio2=aux;
  }   
  //Columna 3 
  
  sql = new StringBuilder();    
  sql.append(" SELECT formularios.sitios.sitio AS  sitio, formularios.sitios.conglomerado AS conglomerado ");
  sql.append(" FROM formularios.principal ");
  sql.append(" LEFT JOIN formularios.sitios ");
  sql.append(" ON formularios.sitios.folio=formularios.principal.folio ");
  sql.append(" WHERE (formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
  sql.append(" AND formularios.principal.anio='").append(anio).append("') ");
  sql.append(" AND formularios.sitios.sitio='3' ");
  sql.append(" GROUP BY formularios.sitios.sitio,formularios.sitios.conglomerado");
  sql.append(" ORDER BY 2");
  rsh = new BeanListHandler(ConglomeradoSitioDTOP12.class);
  List<ConglomeradoSitioDTOP12> conglomerado3= (List<ConglomeradoSitioDTOP12>) qr.query(sql.toString(), rsh);
  
  
  sql = new StringBuilder();    
  sql.append(" SELECT ca.descripcion AS especie ,fp.fecha AS fecha,sum(fs.volumen)*25  AS volumen ");
  sql.append(" FROM formularios.principal fp  ");
  sql.append(" left JOIN formularios.sitios fs ON fp.folio = fs.folio  ");
  sql.append(" inner join catalogos.arbolespecie ca ON ca.id = cast(fs.especie as integer) ");
  sql.append(" where (fp.modulopredio_cup='").append(clavePredio).append("' and fp.anio='").append(anio).append("') and fs.sitio='3'");
  sql.append(" GROUP BY ca.descripcion ,fp.fecha");
  sql.append(" ORDER BY 1");
  rsh = new BeanListHandler(VolumenFechaEspecieDTOP12.class);
  List<VolumenFechaEspecieDTOP12> volumenespeciefechaSitio3= (List<VolumenFechaEspecieDTOP12>) qr.query(sql.toString(), rsh);
  
  sql = new StringBuilder();    
  sql.append(" SELECT catalogos.arbolespecie.descripcion as especie , sum(formularios.S400.ima) as ima  ");
  sql.append(" FROM formularios.principal ");
  sql.append(" left join formularios.S400  ON  formularios.S400.folio =formularios.principal.folio ");
  sql.append(" left join formularios.sitios  ON formularios.sitios.folio=formularios.principal.folio");
  sql.append(" inner join catalogos.arbolespecie  ON catalogos.arbolespecie.id=cast( formularios.sitios.especie as integer)");
  sql.append(" WHERE (formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
  sql.append(" AND formularios.principal.anio='").append(anio).append("') ");
  sql.append(" AND formularios.sitios.sitio='3' ");
  sql.append(" GROUP BY catalogos.arbolespecie.descripcion ");
  sql.append(" order by 1 ");
  rsh = new BeanListHandler(IMADTOP12.class);
  List<IMADTOP12> imasitio3= (List<IMADTOP12>) qr.query(sql.toString(), rsh);
  
  sql = new StringBuilder();    
  sql.append(" SELECT csn.descripcion as presencia_incendio ");
  sql.append(" FROM formularios.principal fp ");
  sql.append(" INNER JOIN catalogos.sino csn ON csn.id = cast(fp.presencia_incendio_1_5_anios as integer) ");
  sql.append(" left join formularios.sitios  ON formularios.sitios.folio=fp.folio");
  sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
  sql.append(" AND fp.anio='").append(anio).append("') ");
  sql.append(" and formularios.sitios.sitio='3' AND cast(fp.presencia_incendio_1_5_anios as integer)=1");
  sql.append(" GROUP BY csn.descripcion ");
  rsh = new BeanHandler(PresenciaIncendioDTOP12.class);
  PresenciaIncendioDTOP12 incendioSitio3= (PresenciaIncendioDTOP12) qr.query(sql.toString(), rsh);
  if(incendioSitio3==null)
  {
      aux.setPresencia_incendio("No");
      incendioSitio3=aux;
  }
  //Columna 4 
  
  sql = new StringBuilder();    
  sql.append(" SELECT formularios.sitios.sitio AS  sitio, formularios.sitios.conglomerado AS conglomerado ");
  sql.append(" FROM formularios.principal ");
  sql.append(" LEFT JOIN formularios.sitios ");
  sql.append(" ON formularios.sitios.folio=formularios.principal.folio ");
  sql.append(" WHERE (formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
  sql.append(" AND formularios.principal.anio='").append(anio).append("') ");
  sql.append(" AND  formularios.sitios.sitio='4' ");
  sql.append(" GROUP BY formularios.sitios.sitio,formularios.sitios.conglomerado");
  sql.append(" ORDER BY 2");
  rsh = new BeanListHandler(ConglomeradoSitioDTOP12.class);
  List<ConglomeradoSitioDTOP12> conglomerado4= (List<ConglomeradoSitioDTOP12>) qr.query(sql.toString(), rsh);
  
  
  sql = new StringBuilder();    
  sql.append(" SELECT ca.descripcion AS especie ,fp.fecha AS fecha,sum(fs.volumen)*25  AS volumen ");
  sql.append(" FROM formularios.principal fp  ");
  sql.append(" left JOIN formularios.sitios fs ON fp.folio = fs.folio  ");
  sql.append(" inner join catalogos.arbolespecie ca ON ca.id = cast(fs.especie as integer) ");
  sql.append(" where (fp.modulopredio_cup='").append(clavePredio).append("' and fp.anio='").append(anio).append("') and fs.sitio='4'");
  sql.append(" GROUP BY ca.descripcion ,fp.fecha");
  sql.append(" ORDER BY 1");
  rsh = new BeanListHandler(VolumenFechaEspecieDTOP12.class);
  List<VolumenFechaEspecieDTOP12> volumenespeciefechaSitio4= (List<VolumenFechaEspecieDTOP12>) qr.query(sql.toString(), rsh);
  
  sql = new StringBuilder();    
  sql.append(" SELECT catalogos.arbolespecie.descripcion as especie, sum(formularios.S400.ima) as ima  ");
  sql.append(" FROM formularios.principal ");
  sql.append(" left join formularios.S400  ON  formularios.S400.folio =formularios.principal.folio ");
  sql.append(" left join formularios.sitios  ON formularios.sitios.folio=formularios.principal.folio");
  sql.append(" inner join catalogos.arbolespecie  ON catalogos.arbolespecie.id=cast( formularios.sitios.especie as integer)");
  sql.append(" WHERE (formularios.principal.modulopredio_cup='").append(clavePredio).append("' ");
  sql.append(" AND formularios.principal.anio='").append(anio).append("') ");
  sql.append(" AND formularios.sitios.sitio='4' ");
  sql.append(" GROUP BY catalogos.arbolespecie.descripcion ");
  sql.append(" order by 1 ");
  rsh = new BeanListHandler(IMADTOP12.class);
  List<IMADTOP12> imasitio4= (List<IMADTOP12>) qr.query(sql.toString(), rsh);
  
  sql = new StringBuilder();    
  sql.append(" SELECT csn.descripcion as presencia_incendio ");
  sql.append(" FROM formularios.principal fp ");
  sql.append(" INNER JOIN catalogos.sino csn ON csn.id = cast(fp.presencia_incendio_1_5_anios as integer) ");
  sql.append(" left join formularios.sitios  ON formularios.sitios.folio=fp.folio");
  sql.append(" WHERE (fp.modulopredio_cup='").append(clavePredio).append("' ");
  sql.append(" AND fp.anio='").append(anio).append("') ");
  sql.append(" and formularios.sitios.sitio='4' AND cast(fp.presencia_incendio_1_5_anios as integer)=1");
  sql.append(" GROUP BY csn.descripcion ");
  rsh = new BeanHandler(PresenciaIncendioDTOP12.class);
  PresenciaIncendioDTOP12 incendioSitio4= (PresenciaIncendioDTOP12) qr.query(sql.toString(), rsh);
  if(incendioSitio4==null)
  {
    aux.setPresencia_incendio("No");
      incendioSitio4=aux;
  }*/
//EjecutivoPrograma12(conglomerado1,  imasitio1, incendio, 
// promedio, conglomerado2,
// volumenespeciefechaSitio2,  imasitio2, 
// incendioSitio2,  conglomerado3, 
// volumenespeciefechaSitio3, imasitio3,
//  incendioSitio3,  conglomerado4,
// volumenespeciefechaSitio4, imasitio4,  incendioSitio4) 
 //EjecutivoPrograma12(conglomerado1,ima,incendio);
 EjecutivoPrograma12 eP12 = new EjecutivoPrograma12(conglomerado, incendio);
//  incendioSitio3, 
 return eP12;
}


    
}



