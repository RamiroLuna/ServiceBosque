/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dao;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.AuditoriasPreventivasDTO;
import org.probosque.dto.PredioImagenDTO;
import org.probosque.dto.PredioPoligonosDTO;
import org.probosque.dto.PredioRepresentanteDTO;

/**
 *
 * @author Erick_G
 */
public class AuditoriasPreventivasDAO {
    
    public List<AuditoriasPreventivasDTO> getAllPredio (String folio) throws SQLException{
        
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner  qr = new QueryRunner(ds);
        
        String cadenaSelect = "SELECT \n" +
                            "CASE WHEN formularios.principal.folio IS NULL THEN ' ' ELSE formularios.principal.folio END AS folio,\n" +
                            "CASE WHEN formularios.principal.cve_sedemex IS NULL THEN ' ' ELSE cast(formularios.principal.cve_sedemex as text) END AS cve_sedemex,\n" +
                            "CASE WHEN formularios.principal.predio IS NULL THEN ' ' ELSE formularios.principal.predio END AS predio,\n" +
                            "CASE WHEN catalogos.tipotenenciatierra.descripcion IS NULL THEN ' ' ELSE catalogos.tipotenenciatierra.descripcion END AS cve_tenencia,\n" +
                            "CASE WHEN formularios.principal.descripcion_llegada_predio IS NULL THEN ' ' ELSE formularios.principal.descripcion_llegada_predio END AS descripcion_llegada_predio,\n" +
                            "CASE WHEN formularios.principal.latitud IS NULL THEN 0 ELSE formularios.principal.latitud END AS latitud,\n" +
                            "CASE WHEN formularios.principal.longitud IS NULL THEN 0 ELSE formularios.principal.longitud END AS longitud,\n" +
                            "CASE WHEN formularios.principal.superficie_total IS NULL THEN 0 ELSE formularios.principal.superficie_total END AS superficie_total,\n" +
                            "CASE WHEN formularios.principal.superficie_cartografica IS NULL THEN 0 ELSE formularios.principal.superficie_cartografica END AS superficie_cartografica,\n" +
                            "CASE WHEN formularios.principal.superficie_arbolada IS NULL THEN 0 ELSE formularios.principal.superficie_arbolada END AS superficie_arbolada,\n" +
                            "CASE WHEN formularios.principal.superficie_otros IS NULL THEN 0 ELSE formularios.principal.superficie_otros END AS superficie_otros,\n" +
                            "CASE WHEN catalogos.estatus.descripcion IS NULL THEN ' ' ELSE catalogos.estatus.descripcion END AS estatus,\n" +
                            "CASE WHEN formularios.principal.observaciones IS NULL THEN ' ' ELSE formularios.principal.observaciones END AS observaciones,\n" +
                            "CASE WHEN formularios.principal.modulopredio_estado IS NULL THEN ' ' ELSE cast(formularios.principal.modulopredio_estado as text) END AS modulopredio_estado,\n" +
                            "CASE WHEN catalogos.region.descripcion IS NULL THEN ' ' ELSE catalogos.region.descripcion END AS region,\n" +
                            "CASE WHEN catalogos.municipio.descripcion IS NULL THEN ' ' ELSE catalogos.municipio.descripcion END AS modulopredio_municipio,\n" +
                            "CASE WHEN catalogos.localidad.descripcion IS NULL THEN ' ' ELSE catalogos.localidad.descripcion END AS modulopredio_localidad,\n" +
                            "CASE WHEN catalogos.localidad.descripcion IS NULL THEN ' ' ELSE catalogos.localidad.descripcion END AS modulopredio_localidad,\n" +
                            "CASE WHEN catalogos.aceptable_aprovechamiento.descripcion IS NULL THEN ' ' ELSE catalogos.aceptable_aprovechamiento.descripcion END AS permiso_aprovechamiento,\n" +
                            "CASE WHEN formularios.principal.registroforestal_nacional IS NULL THEN ' ' ELSE formularios.principal.registroforestal_nacional END AS registroforestal_nacional\n" +
                            "  FROM formularios.principal\n" +
                            "  LEFT JOIN catalogos.tipotenenciatierra ON formularios.principal.cve_tenencia = catalogos.tipotenenciatierra.id\n" +
                            "  LEFT JOIN catalogos.estatus ON formularios.principal.estatus = catalogos.estatus.id\n" +
                            "  \n" +
                            "  LEFT JOIN catalogos.region On formularios.principal.region = catalogos.region.id\n" +
                            "  LEFT JOIN catalogos.municipio ON formularios.principal.modulopredio_municipio = catalogos.municipio.id\n" +
                            "  LEFT JOIN catalogos.localidad ON formularios.principal.modulopredio_localidad = catalogos.localidad.id\n" +
                            "    AND principal.modulopredio_municipio = catalogos.localidad.id_municipio\n" +
                            "  \n" +
                            "  LEFT JOIN catalogos.aceptable_aprovechamiento ON formularios.principal.permiso_aprovechamiento = catalogos.aceptable_aprovechamiento.id \n" +
                              "WHERE formularios.principal.folio = '" + folio + "'";
        
        ResultSetHandler rsh = new BeanListHandler(AuditoriasPreventivasDTO.class);
        List<AuditoriasPreventivasDTO> result = (List<AuditoriasPreventivasDTO>)qr.query(cadenaSelect, rsh);

        return result;
    }
    
    
    public List<PredioPoligonosDTO> getPredioPoligonos (String folio) throws SQLException{
        
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner  qr = new QueryRunner(ds);
        
        String cadenaSelect = "SELECT CASE WHEN formularios.poligonos.consecutivo IS NULL THEN ' ' ELSE cast(formularios.poligonos.consecutivo as text) END AS consecutivo,\n" +
                                " CASE WHEN formularios.poligonos.folio IS NULL THEN ' ' ELSE formularios.poligonos.folio END AS folio,\n" +
                                "  CASE WHEN formularios.poligonos.accion_agraria IS NULL THEN ' ' ELSE formularios.poligonos.accion_agraria END AS accion_agraria,\n" +
                                "   CASE WHEN formularios.poligonos.fecha_publicacion_dof IS NULL THEN ' ' ELSE to_char(formularios.poligonos.fecha_publicacion_dof,'dd-mm-yyyy') \n" +
                                "   END AS fecha_publicacion_dof,\n" +
                                "    CASE WHEN formularios.poligonos.fecha_resolucion_presidencial IS NULL THEN ' ' ELSE to_char(formularios.poligonos.fecha_resolucion_presidencial,'dd-mm-yyyy' ) END \n" +
                                "    AS fecha_resolucion_presidencial, \n" +
                                "    CASE WHEN formularios.poligonos.fecha_asamblea_procede IS NULL THEN ' ' ELSE to_char(formularios.poligonos.fecha_asamblea_procede, 'dd-mm-yyyy') END AS fecha_asamblea_procede, \n" +
                                "    CASE WHEN formularios.poligonos.documento_ampara_propiedad IS NULL THEN ' ' ELSE formularios.poligonos.documento_ampara_propiedad END AS documento_ampara_propiedad\n" +
                                "    , CASE WHEN formularios.poligonos.numero_documento_ampara_propiedad IS NULL THEN ' ' ELSE formularios.poligonos.numero_documento_ampara_propiedad END AS numero_documento_ampara_propiedad, CASE WHEN formularios.poligonos.latitud IS NULL THEN 0 ELSE formularios.poligonos.latitud END AS latitud, CASE WHEN formularios.poligonos.longitud IS NULL THEN 0 ELSE formularios.poligonos.longitud END AS longitud, CASE WHEN formularios.poligonos.superficie_poligono IS NULL THEN 0 ELSE formularios.poligonos.superficie_poligono END AS superficie_poligono, CASE WHEN formularios.poligonos.superficie_cartografica IS NULL THEN 0 ELSE formularios.poligonos.superficie_cartografica END AS superficie_cartografica, CASE WHEN formularios.poligonos.superficie_arbolada IS NULL THEN 0 ELSE formularios.poligonos.superficie_arbolada END AS superficie_arbolada, CASE WHEN formularios.poligonos.superficie_otros_usos IS NULL THEN 0 ELSE formularios.poligonos.superficie_otros_usos END AS superficie_otros_usos, CASE WHEN catalogos.clima.descripcion IS NULL THEN ' ' ELSE catalogos.clima.descripcion END AS tipo_clima, CASE WHEN catalogos.vegetacionpredominante.descripcion IS NULL THEN ' ' ELSE catalogos.vegetacionpredominante.descripcion END AS tipo_vegetacion, CASE WHEN formularios.poligonos.tipo_fisiografia IS NULL THEN ' ' ELSE formularios.poligonos.tipo_fisiografia END AS tipo_fisiografia, CASE WHEN formularios.poligonos.corrientes_intermitentes IS NULL THEN ' ' ELSE formularios.poligonos.corrientes_intermitentes END AS corrientes_intermitentes, CASE WHEN formularios.poligonos.corrientes_permanentes IS NULL THEN ' ' ELSE formularios.poligonos.corrientes_permanentes END AS corrientes_permanentes, CASE WHEN formularios.poligonos.manantiales_ojos_agua IS NULL THEN ' ' ELSE formularios.poligonos.manantiales_ojos_agua END AS manantiales_ojos_agua, CASE WHEN formularios.poligonos.manantiales_ojos_agua_abastecen IS NULL THEN ' ' ELSE formularios.poligonos.manantiales_ojos_agua_abastecen END AS manantiales_ojos_agua_abastecen, CASE WHEN formularios.poligonos.erosion IS NULL THEN ' ' ELSE formularios.poligonos.erosion END AS erosion, CASE WHEN catalogos.especie.descripcion IS NULL THEN ' ' ELSE catalogos.especie.descripcion END AS especies_arboreas, CASE WHEN formularios.poligonos.distribucion_estrato_arbustivo IS NULL THEN ' ' ELSE formularios.poligonos.distribucion_estrato_arbustivo END AS distribucion_estrato_arbustivo, CASE WHEN formularios.poligonos.distribucion_renuevo IS NULL THEN ' ' ELSE formularios.poligonos.distribucion_renuevo END AS distribucion_renuevo, CASE WHEN formularios.poligonos.cobertura_promedio_arbolado IS NULL THEN ' ' ELSE formularios.poligonos.cobertura_promedio_arbolado END AS cobertura_promedio_arbolado, CASE WHEN formularios.poligonos.fauna IS NULL THEN ' ' ELSE formularios.poligonos.fauna END AS fauna, CASE WHEN formularios.poligonos.observaciones_poligono IS NULL THEN ' ' ELSE formularios.poligonos.observaciones_poligono END AS observaciones_poligono, CASE WHEN formularios.poligonos.figura_polygono IS NULL THEN ' ' ELSE formularios.poligonos.figura_polygono END AS figura_polygono \n" +
                                "    FROM formularios.poligonos LEFT JOIN catalogos.clima ON formularios.poligonos.tipo_clima = catalogos.clima.id \n" +
                                "    LEFT JOIN catalogos.vegetacionpredominante ON formularios.poligonos.tipo_vegetacion = catalogos.vegetacionpredominante.id \n" +
                                "LEFT JOIN catalogos.especie ON formularios.poligonos.especies_arboreas = catalogos.especie.id \n" +
                              "WHERE formularios.poligonos.folio = '" + folio + "'";
        
        ResultSetHandler rsh = new BeanListHandler(PredioPoligonosDTO.class);
        List<PredioPoligonosDTO> result = (List<PredioPoligonosDTO>)qr.query(cadenaSelect, rsh);

        return result;
    }
    
    public List<PredioImagenDTO> getPredioImagen (String folio) throws SQLException{
        
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner  qr = new QueryRunner(ds);
        
        String cadenaSelect = "SELECT CASE WHEN formularios.imagen.consecutivo IS NULL THEN ' ' ELSE cast(formularios.imagen.consecutivo as text) END AS consecutivo,\n" +
                            " CASE WHEN formularios.imagen.folio IS NULL THEN ' ' ELSE formularios.imagen.folio END AS folio,\n" +
                            "  CASE WHEN formularios.imagen.url IS NULL THEN ' ' ELSE formularios.imagen.url END AS url,\n" +
                            "   CASE WHEN formularios.imagen.fecha IS NULL THEN ' ' ELSE to_char(formularios.imagen.fecha, 'dd-mm-yyyy') END AS fecha, \n" +
                            "   CASE WHEN formularios.imagen.descripcion IS NULL THEN ' ' ELSE formularios.imagen.descripcion END AS descripcion,\n" +
                            "    CASE WHEN catalogos.campos.descripcion IS NULL THEN ' ' ELSE catalogos.campos.descripcion END AS campoasociado\n" +
                            "     FROM formularios.imagen\n" +
                            " LEFT JOIN catalogos.campos ON formularios.imagen.campoasociado = catalogos.campos.id  \n" +
                            "WHERE formularios.imagen.folio = '" + folio + "'";
        
        ResultSetHandler rsh = new BeanListHandler(PredioImagenDTO.class);
        List<PredioImagenDTO> result = (List<PredioImagenDTO>)qr.query(cadenaSelect, rsh);

        return result;
    }
    
    public List<PredioRepresentanteDTO> getPredioRepresentante (String folio) throws SQLException{
        
        DataSource ds = PoolDataSource.getDataSourceGeneral();
        QueryRunner  qr = new QueryRunner(ds);
        
        String cadenaSelect = "SELECT CASE WHEN formularios.representante.consecutivo IS NULL THEN ' ' ELSE \n" +
                                "cast(formularios.representante.consecutivo as text) END AS consecutivo, \n" +
                                "CASE WHEN formularios.representante.folio IS NULL THEN ' ' ELSE formularios.representante.folio END AS folio,\n" +
                                " CASE WHEN formularios.representante.nombre_propietario_representante IS NULL THEN ' ' ELSE formularios.representante.nombre_propietario_representante END AS nombre_propietario_representante,\n" +
                                "  CASE WHEN formularios.representante.nombre_secretario_representante_legal IS NULL THEN ' '\n" +
                                "   ELSE formularios.representante.nombre_secretario_representante_legal END AS nombre_secretario_representante_legal,\n" +
                                "    CASE WHEN formularios.representante.nombre_tesorero IS NULL THEN ' ' ELSE formularios.representante.nombre_tesorero END AS\n" +
                                "     nombre_tesorero, \n" +
                                "     CASE WHEN formularios.representante.curp_propietario_o_representante IS NULL\n" +
                                "      THEN ' ' ELSE formularios.representante.curp_propietario_o_representante END AS curp_propietario_o_representante,\n" +
                                "       CASE WHEN formularios.representante.inicio_periodo IS NULL THEN ' ' ELSE to_char(formularios.representante.inicio_periodo,'dd-mm-yyyy')\n" +
                                "        END AS inicio_periodo,\n" +
                                " CASE WHEN formularios.representante.fin_periodo IS NULL THEN ' ' ELSE to_char(formularios.representante.fin_periodo,'dd-mm-yyyy')\n" +
                                "  END AS fin_periodo, \n" +
                                " CASE WHEN formularios.representante.observaciones_administracion\n" +
                                "  IS NULL THEN ' ' ELSE formularios.representante.observaciones_administracion \n" +
                                "END AS observaciones_administracion FROM formularios.representante " +
                              " WHERE formularios.representante.folio = '" + folio + "'";
        
        ResultSetHandler rsh = new BeanListHandler(PredioRepresentanteDTO.class);
        List<PredioRepresentanteDTO> result = (List<PredioRepresentanteDTO>)qr.query(cadenaSelect, rsh);

        return result;
    }
    
}
