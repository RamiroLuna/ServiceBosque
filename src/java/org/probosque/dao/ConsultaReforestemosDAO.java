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
import org.probosque.dto.ListArchivos;
import org.probosque.dto.ListBienesAsegurados;
import org.probosque.dto.ListDependenciasParticipantes;
import org.probosque.dto.ListPersonasAseguradas;
import org.probosque.dto.ListPredios;
import org.probosque.dto.ListProgram8;
import org.probosque.dto.ListVehiculosAsegurados;
import org.probosque.dto.UserDTO;

/**
 *
 * @author Administrador
 */
public class ConsultaReforestemosDAO {
    
    
    public List<ListProgram8> selectGeneral(UserDTO user, String folio) throws SQLException {
        
        DataSource ds = PoolDataSource.getDbProgram8(true);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT\n" +
        "        CASE WHEN folio IS NULL THEN ' ' ELSE folio END AS folio,\n" +
        "        CASE WHEN tipoaccion.descripcion IS NULL THEN ' ' ELSE tipoaccion.descripcion END AS tipo_accion,  \n" +
        "        CASE WHEN nombre_predio IS NULL THEN ' ' ELSE nombre_predio END AS nombre_predio, \n" +
        "        CASE WHEN razon_social IS NULL THEN ' ' ELSE razon_social END AS razon_social, \n" +
        "	 CASE WHEN localidad IS NULL THEN ' ' ELSE localidad END AS localidad,\n" +
        "	 CASE WHEN paraje IS NULL THEN ' ' ELSE paraje END AS paraje, \n" +
        "	 CASE WHEN coordenadas_utm_x IS NULL THEN 0 ELSE coordenadas_utm_x END AS coordenadas_utm_x, \n" +
        "	 CASE WHEN coordenadas_utm_y IS NULL THEN 0 ELSE coordenadas_utm_y END AS coordenadas_utm_y, \n" +
        "	 CASE WHEN metros_sobre_nivel_mar IS NULL THEN 0 ELSE metros_sobre_nivel_mar END AS metros_sobre_nivel_mar, \n" +
        "	 CASE WHEN to_char(to_date(fecha_inicio,'dd/mm/yyyy'), 'dd/mm/yyyy') IS NULL THEN '01/01/1999' ELSE to_char(to_date(fecha_inicio,'dd/mm/yyyy'), 'dd/mm/yyyy') END AS fecha_inicio,\n" +
        "	 CASE WHEN to_char(to_date(fecha_termino, 'dd/mm/yyyy'), 'dd/mm/yyyy') IS NULL THEN '01/01/1999' ELSE to_char(to_date(fecha_termino, 'dd/mm/yyyy'), 'dd/mm/yyyy') END AS fecha_termino, \n" +
        "	 CASE WHEN zonacritica.descripcion IS NULL THEN ' ' ELSE zonacritica.descripcion END AS zona_critica,\n" +
        "	 CASE WHEN total_participantes IS NULL THEN 0 ELSE total_participantes END AS total_participantes, \n" +
        "	 CASE WHEN vehiculos_irregularidades IS NULL THEN 0 ELSE vehiculos_irregularidades END AS vehiculos_irregularidades, \n" +
        "	 CASE WHEN vehiculos_sin_irregularidades IS NULL THEN 0 ELSE vehiculos_sin_irregularidades END AS vehiculos_sin_irregularidades, \n" +
        "	 CASE WHEN vehiculos_revisados IS NULL OR vehiculos_revisados = 'S/D' THEN ' ' ELSE vehiculos_revisados END AS vehiculos_revisados,\n" +
        "	 CASE WHEN total_personas IS NULL THEN 0 ELSE total_personas END AS total_personas, \n" +
        "	 CASE WHEN num_orden IS NULL OR num_orden = 'S/D' THEN ' ' ELSE num_orden END AS num_orden, \n" +
        "	 CASE WHEN tipodocumento.descripcion IS NULL THEN ' ' ELSE tipodocumento.descripcion END AS tipo_documento,\n" +
        "        CASE WHEN num_documento IS NULL OR num_documento = 'S/D' THEN ' ' ELSE num_documento END AS num_documento, \n" +
        "        CASE WHEN dependenciaexpidedocumento.descripcion IS NULL THEN ' ' ELSE dependenciaexpidedocumento.descripcion END AS dependencia_expide_documento,\n" +
        "        CASE WHEN agenciaministerio.descripcion IS NULL THEN ' ' ELSE agenciaministerio.descripcion END AS agencia_ministerio_solicita_dictamen,\n" +
        "        CASE WHEN medidaseguridad.descripcion IS NULL THEN ' ' ELSE medidaseguridad.descripcion END AS medidas_seguridad,\n" +
        "        CASE WHEN dictaminador.descripcion IS NULL THEN ' ' ELSE dictaminador.descripcion END AS inspector_forestal,\n" +
        "        CASE WHEN valor_comercial IS NULL THEN 0 ELSE valor_comercial END AS valor_comercial, \n" +
        "        CASE WHEN impacto_ambiental IS NULL THEN 0 ELSE impacto_ambiental END AS impacto_ambiental, \n" +
        "        CASE WHEN reparacion_danio IS NULL THEN 0 ELSE reparacion_danio END AS reparacion_danio, \n" +
        "        CASE WHEN total_dictamen IS NULL THEN 0 ELSE total_dictamen END AS total_dictamen, \n" +
        "        CASE WHEN observaciones IS NULL OR observaciones = 'S/D' THEN ' ' ELSE observaciones END AS observaciones, \n" +
        "        CASE WHEN anio IS NULL THEN 0  ELSE anio END AS anio, \n" +
        "	 CASE WHEN region.descripcion IS NULL THEN ' ' ELSE region.descripcion END AS region,  \n" +
        "        CASE WHEN modulopredio_estado IS NULL THEN ' ' ELSE modulopredio_estado END AS modulopredio_estado, \n" +
        "        CASE WHEN municipio.descripcion IS NULL THEN ' ' ELSE municipio.descripcion END AS modulopredio_municipio, \n" +
        "        CASE WHEN localidad.descripcion IS NULL THEN ' ' ELSE localidad.descripcion END AS modulopredio_localidad,\n" +
        "        CASE WHEN modulopredio_cup IS NULL THEN ' ' ELSE modulopredio_cup END AS modulopredio_cup,\n" +
        "        catalogos.municipio.descripcion as municipio \n" +
        "        FROM formularios.principal \n" +
        "        LEFT JOIN catalogos.region ON CAST (formularios.principal.region AS INTEGER) = catalogos.region.id\n" +
        "        LEFT JOIN catalogos.municipio ON CAST (formularios.principal.modulopredio_municipio AS INTEGER) = catalogos.municipio.id\n" +
        "        LEFT JOIN catalogos.localidad ON (CAST (formularios.principal.modulopredio_localidad AS INTEGER) = catalogos.localidad.id AND CAST (formularios.principal.modulopredio_municipio AS INTEGER) = catalogos.localidad.id_municipio )\n" +
        "        LEFT JOIN catalogos.tipoaccion ON CAST(formularios.principal.tipo_accion AS INTEGER) = catalogos.tipoaccion.id\n" +
        "        LEFT JOIN catalogos.zonacritica ON CAST(formularios.principal.zona_critica AS INTEGER) = catalogos.zonacritica.id\n" +
        "        LEFT JOIN catalogos.tipodocumento ON formularios.principal.tipo_documento = CAST(catalogos.tipodocumento.id AS INTEGER)\n" +
        "        LEFT JOIN catalogos.dependenciaexpidedocumento ON formularios.principal.dependencia_expide_documento = CAST(catalogos.dependenciaexpidedocumento.id AS INTEGER)\n" +
        "        LEFT JOIN catalogos.agenciaministerio ON formularios.principal.agencia_ministerio_solicita_dictamen = CAST(catalogos.agenciaministerio.id AS INTEGER)\n" +
        "        LEFT JOIN catalogos.medidaseguridad ON formularios.principal.medidas_seguridad = CAST(catalogos.medidaseguridad.id AS INTEGER)\n" +
        "        LEFT JOIN catalogos.dictaminador ON formularios.principal.inspector_forestal = CAST(catalogos.dictaminador.id AS INTEGER)\n" +
        "        WHERE folio = '"+folio+"'");
        ResultSetHandler rsh = new BeanListHandler(ListProgram8.class);
        List<ListProgram8> result = (List<ListProgram8>)qr.query(sql.toString(), rsh);
        return result;
    }
    
    
    public List<ListPersonasAseguradas> personasAseguradas(UserDTO user, String folio) throws SQLException {
        
        DataSource ds = PoolDataSource.getDbProgram8(true);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n" +
        "	CASE WHEN infractores.consecutivo IS NULL THEN ' ' ELSE infractores.consecutivo END AS consecutivo, \n" +
        "	CASE WHEN infractores.folio IS NULL THEN ' ' ELSE infractores.folio END AS folio, \n" +
        "	CASE WHEN infractores.nombre IS NULL OR infractores.nombre = 'S/D' THEN ' ' ELSE infractores.nombre END AS nombre, \n" +
        "	CASE WHEN sexoinfractor.descripcion IS NULL THEN ' ' ELSE sexoinfractor.descripcion END AS sexo, \n" +
        "	CASE WHEN CAST(infractores.edad AS TEXT)='0' OR CAST(infractores.edad AS TEXT) IS NULL THEN ' '  ELSE CAST(infractores.edad AS TEXT ) END AS edad, \n" +
        "	CASE WHEN infractores.domicilio IS NULL OR infractores.domicilio = 'S/D' THEN ' ' ELSE infractores.domicilio END AS domicilio, \n" +
        "	CASE WHEN estatuspersona.descripcion IS NULL OR infractores.estatus = '-1' THEN ' ' ELSE estatuspersona.descripcion END AS estatus\n" +
        "	FROM formularios.principal\n" +
        "	LEFT JOIN formularios.infractores ON formularios.principal.folio = formularios.infractores.folio\n" +
        "	LEFT JOIN catalogos.sexoinfractor ON CAST(formularios.infractores.sexo AS INTEGER) = catalogos.sexoinfractor.id\n" +
                "       LEFT JOIN catalogos.estatuspersona ON CAST(formularios.infractores.estatus AS INTEGER) = catalogos.estatuspersona.id\n"+
        "	WHERE formularios.infractores.folio = '"+folio+"'");
        ResultSetHandler rsh = new BeanListHandler(ListPersonasAseguradas.class);
        List<ListPersonasAseguradas> result = (List<ListPersonasAseguradas>)qr.query(sql.toString(), rsh);
        return result;
    }
    
            
            
    public List<ListDependenciasParticipantes> dependenciasParticipantes(UserDTO user, String folio) throws SQLException {
        
        DataSource ds = PoolDataSource.getDbProgram8(true);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n" +
        "	CASE WHEN participantes.consecutivo IS NULL THEN 0 ELSE participantes.consecutivo END AS consecutivo, \n" +
        "	CASE WHEN participantes.folio IS NULL THEN ' ' ELSE participantes.folio END AS folio, \n" +
        "	CASE WHEN dependencia.descripcion IS NULL THEN ' ' ELSE dependencia.descripcion END AS dependencia, \n" +
        "	CASE WHEN CAST(participantes.cantidad AS TEXT)='0' OR CAST(participantes.cantidad AS TEXT) IS NULL THEN ' '  ELSE CAST(participantes.cantidad AS TEXT ) END AS cantidad\n" +
        "	FROM formularios.principal\n" +
        "	LEFT JOIN formularios.participantes ON formularios.principal.folio = formularios.participantes.folio\n" +
        "	LEFT JOIN catalogos.dependencia ON CAST(formularios.participantes.dependencia AS INTEGER) = catalogos.dependencia.id\n" +
        "	WHERE formularios.participantes.folio = '"+folio+"'");
        ResultSetHandler rsh = new BeanListHandler(ListDependenciasParticipantes.class);
        List<ListDependenciasParticipantes> result = (List<ListDependenciasParticipantes>)qr.query(sql.toString(), rsh);
        return result;
    }
            
    
    public List<ListVehiculosAsegurados> vehiculosAsegurados(UserDTO user, String folio) throws SQLException {
        
        DataSource ds = PoolDataSource.getDbProgram8(true);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n" +
        "	CASE WHEN vehiculos.consecutivo IS NULL THEN ' ' ELSE vehiculos.consecutivo END AS consecutivo, \n" +
        "	CASE WHEN vehiculos.folio IS NULL THEN ' ' ELSE vehiculos.folio END AS folio, \n" +
        "	CASE WHEN dependencia.descripcion IS NULL THEN ' ' ELSE dependencia.descripcion END AS dependencia, \n" +
        "	CASE WHEN CAST(vehiculos.cantidad AS TEXT)='0' OR CAST(vehiculos.cantidad AS TEXT) IS NULL THEN ' '  ELSE CAST(vehiculos.cantidad AS TEXT ) END AS cantidad\n" +
        "	FROM formularios.principal\n" +
        "	LEFT JOIN formularios.vehiculos ON formularios.principal.folio = formularios.vehiculos.folio\n" +
        "	LEFT JOIN catalogos.dependencia ON CAST(formularios.vehiculos.dependencia AS INTEGER) = catalogos.dependencia.id\n" +
        "	WHERE formularios.vehiculos.folio = '"+folio+"'");
        ResultSetHandler rsh = new BeanListHandler(ListVehiculosAsegurados.class);
        List<ListVehiculosAsegurados> result = (List<ListVehiculosAsegurados>)qr.query(sql.toString(), rsh);
        return result;
    }
            
            
    public List<ListBienesAsegurados> bienesAseguradosC(UserDTO user, String folio) throws SQLException {
        
        DataSource ds = PoolDataSource.getDbProgram8(true);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n" +
    "	CASE WHEN productos.consecutivo IS NULL THEN ' ' ELSE productos.consecutivo END AS consecutivo, \n" +
    "	CASE WHEN productos.folio IS NULL THEN ' ' ELSE productos.folio END AS folio,\n" +
    "	CASE WHEN tipoproducto.descripcion IS NULL THEN ' ' ELSE tipoproducto.descripcion END AS tipo_producto\n" +
    "	FROM formularios.principal\n" +
    "	LEFT JOIN formularios.productos ON formularios.principal.folio = formularios.productos.folio\n" +
    "	LEFT JOIN catalogos.tipoproducto ON CAST(formularios.productos.tipo_producto AS INTEGER) = catalogos.tipoproducto.id\n" +
    "	WHERE formularios.productos.folio = '"+folio+"'");
        ResultSetHandler rsh = new BeanListHandler(ListBienesAsegurados.class);
        List<ListBienesAsegurados> result = (List<ListBienesAsegurados>)qr.query(sql.toString(), rsh);
        return result;
    }
    
    
    public List<ListBienesAsegurados> bienesAseguradosL(UserDTO user, String folio, String consecutivo) throws SQLException {
        
        DataSource ds = PoolDataSource.getDbProgram8(true);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n" +
        "	CASE WHEN productos.consecutivo IS NULL THEN ' ' ELSE productos.consecutivo END AS consecutivo, \n" +
        "	CASE WHEN productos.folio IS NULL THEN ' ' ELSE productos.folio END AS folio,\n" +
        "	CASE WHEN tipoproducto.descripcion IS NULL THEN ' ' ELSE tipoproducto.descripcion END AS tipo_producto,\n" +
        "	CASE WHEN ilicitoproducto.descripcion IS NULL OR productos.ilicito_producto = '-1' THEN ' ' ELSE ilicitoproducto.descripcion END AS ilicito_producto,\n" +
        "	CASE WHEN generoproducto.descripcion IS NULL THEN ' ' ELSE generoproducto.descripcion END AS genero_producto, \n" +
        "	CASE WHEN unidadmedida.descripcion IS NULL THEN ' ' ELSE unidadmedida.descripcion END AS unidad_medida,\n" +
        "	CASE WHEN lugardeposito.descripcion IS NULL THEN ' ' ELSE lugardeposito.descripcion END AS lugar_deposito,\n" +
        "	CASE WHEN CAST(productos.total_productos_asegurados AS TEXT)='0' OR CAST(productos.total_productos_asegurados AS TEXT) IS NULL THEN ' '  ELSE CAST(productos.total_productos_asegurados AS TEXT ) END AS total_productos_asegurados,\n" +
        "	CASE WHEN productos.total_herramientas_menores IS NULL OR productos.total_herramientas_menores = 'S/D' THEN ' ' ELSE productos.total_herramientas_menores END AS total_herramientas_menores,\n" +
        "	CASE WHEN productos.identificador_bien IS NULL THEN ' ' ELSE productos.identificador_bien END AS identificador_bien\n" +
        "	FROM formularios.productos\n" +
        "	LEFT JOIN catalogos.tipoproducto ON CAST(formularios.productos.tipo_producto AS INTEGER) = catalogos.tipoproducto.id\n" +
        "	LEFT JOIN catalogos.generoproducto ON CAST(formularios.productos.genero_producto AS INTEGER) = catalogos.generoproducto.id\n" +
        "	LEFT JOIN catalogos.unidadmedida ON CAST(formularios.productos.genero_producto AS INTEGER) = catalogos.unidadmedida.id\n" +
        "	LEFT JOIN catalogos.lugardeposito ON CAST(formularios.productos.genero_producto AS INTEGER) = catalogos.lugardeposito.id\n" +
        "       LEFT JOIN catalogos.ilicitoproducto ON CAST(formularios.productos.tipo_producto AS INTEGER) = catalogos.ilicitoproducto.id\n"+
        "	WHERE formularios.productos.folio = '"+folio+"' AND  formularios.productos.consecutivo = '"+consecutivo+"'");
        ResultSetHandler rsh = new BeanListHandler(ListBienesAsegurados.class);
        List<ListBienesAsegurados> result = (List<ListBienesAsegurados>)qr.query(sql.toString(), rsh);
        return result;
    }
    
    
    public List<ListPredios> prediosC(UserDTO user, String folio) throws SQLException {
        
        DataSource ds = PoolDataSource.getDbProgram8(true);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n" +
        "	CASE WHEN predios.consecutivo IS NULL THEN ' ' ELSE predios.consecutivo END AS consecutivo,\n" +
        "	CASE WHEN predios.folio IS NULL THEN ' ' ELSE predios.folio END AS folio,\n" +
        "	CASE WHEN predios.clave_unica_de_predio IS NULL THEN ' ' ELSE predios.clave_unica_de_predio END AS clave_unica_de_predio\n" +
        "	FROM formularios.principal\n" +
        "	LEFT JOIN formularios.predios ON formularios.principal.folio = formularios.predios.folio\n" +
        "	WHERE formularios.predios.folio = '"+folio+"'");
        ResultSetHandler rsh = new BeanListHandler(ListPredios.class);
        List<ListPredios> result = (List<ListPredios>)qr.query(sql.toString(), rsh);
        return result;
    }
    
    
    public List<ListPredios> prediosL(UserDTO user, String folio, String consecutivo) throws SQLException {
        
        DataSource ds = PoolDataSource.getDbProgram8(true);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n" +
        "	CASE WHEN predios.consecutivo IS NULL THEN ' ' ELSE predios.consecutivo END AS consecutivo,\n" +
        "	CASE WHEN predios.folio IS NULL THEN ' ' ELSE predios.folio END AS folio,\n" +
        "	CASE WHEN predios.clave_unica_de_predio IS NULL THEN ' ' ELSE predios.clave_unica_de_predio END AS clave_unica_de_predio,\n" +
        "	CASE WHEN predios.cve_sedemex IS NULL THEN ' ' ELSE predios.cve_sedemex END AS cve_sedemex, \n" +
        "	CASE WHEN predios.campo_trabajo IS NULL THEN ' ' ELSE predios.campo_trabajo END AS campo_trabajo, \n" +
        "	CASE WHEN predios.nombre_del_predio IS NULL THEN ' ' ELSE predios.nombre_del_predio END AS nombre_del_predio, \n" +
        "	CASE WHEN predios.cve_tenencia IS NULL THEN ' ' ELSE predios.cve_tenencia END AS cve_tenencia, \n" +
        "	CASE WHEN predios.descripcion_llegar_predio IS NULL THEN ' ' ELSE predios.descripcion_llegar_predio END AS descripcion_llegar_predio, \n" +
        "	CASE WHEN CAST(predios.latitud_utm AS TEXT)='0' OR CAST(predios.latitud_utm AS TEXT) IS NULL THEN ' '  ELSE CAST(predios.latitud_utm AS TEXT ) END AS latitud_utm,\n" +
        "	CASE WHEN CAST(predios.longitud_utm AS TEXT)='0' OR CAST(predios.longitud_utm AS TEXT) IS NULL THEN ' '  ELSE CAST(predios.longitud_utm AS TEXT ) END AS longitud_utm,\n" +
        "	CASE WHEN CAST(predios.superficie_total_ha AS TEXT)='0' OR CAST(predios.superficie_total_ha AS TEXT) IS NULL THEN ' '  ELSE CAST(predios.superficie_total_ha AS TEXT ) END AS superficie_total_ha,\n" +
        "	CASE WHEN CAST(predios.superficie_cartografica_ha AS TEXT)='0' OR CAST(predios.superficie_cartografica_ha AS TEXT) IS NULL THEN ' '  ELSE CAST(predios.superficie_cartografica_ha AS TEXT ) END AS superficie_cartografica_ha,\n" +
        "	CASE WHEN CAST(predios.superficie_arbolada_ha AS TEXT)='0' OR CAST(predios.superficie_arbolada_ha AS TEXT) IS NULL THEN ' '  ELSE CAST(predios.superficie_arbolada_ha AS TEXT ) END AS superficie_arbolada_ha,\n" +
        "	CASE WHEN CAST(predios.superficie_otros_usos_ha AS TEXT)='0' OR CAST(predios.superficie_otros_usos_ha AS TEXT) IS NULL THEN ' '  ELSE CAST(predios.superficie_otros_usos_ha AS TEXT ) END AS superficie_otros_usos_ha,\n" +
        "	CASE WHEN predios.microcuenca_subcuenca_especifica IS NULL THEN ' ' ELSE predios.microcuenca_subcuenca_especifica  END AS microcuenca_subcuenca_especifica,\n" +
        "	CASE WHEN anp.descripcion IS NULL THEN ' ' ELSE anp.descripcion END AS area_natural_protegida, \n" +
        "	CASE WHEN predios.status_predio IS NULL THEN ' ' ELSE predios.status_predio END AS status_predio, \n" +
        "	CASE WHEN predios.observaciones_predio IS NULL THEN ' ' ELSE predios.observaciones_predio END AS observaciones_predio, \n" +
        "	CASE WHEN predios.propietario_o_representante_legal IS NULL THEN ' ' ELSE predios.propietario_o_representante_legal END AS propietario_o_representante_legal, \n" +
        "	CASE WHEN predios.poligonos_del_predio IS NULL THEN ' ' ELSE predios.poligonos_del_predio END AS poligonos_del_predio, \n" +
        "	CASE WHEN predios.clave_estatal IS NULL THEN ' ' ELSE predios.clave_estatal END AS clave_estatal, \n" +
        "	CASE WHEN predios.cve_regftal IS NULL THEN ' ' ELSE predios.cve_regftal END AS cve_regftal, \n" +
        "	CASE WHEN predios.cve_num IS NULL THEN ' ' ELSE predios.cve_num END AS cve_num, \n" +
        "	CASE WHEN predios.cve_loctext IS NULL THEN ' ' ELSE predios.cve_loctext END AS cve_loctext\n" +
        "	FROM formularios.predios\n" +
        "	LEFT JOIN catalogos.anp ON formularios.predios.area_natural_protegida  = CAST(catalogos.anp.id AS TEXT)\n" +
        "	WHERE formularios.predios.folio = '"+folio+"' AND  formularios.predios.consecutivo = '"+consecutivo+"'");
        ResultSetHandler rsh = new BeanListHandler(ListPredios.class);
        List<ListPredios> result = (List<ListPredios>)qr.query(sql.toString(), rsh);
        return result;
    }
    
    
    public List<ListArchivos> archivos(UserDTO user, String folio) throws SQLException {
        
        DataSource ds = PoolDataSource.getDbProgram8(true);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n" +
        "	CASE WHEN imagen.consecutivo IS NULL THEN ' ' ELSE imagen.consecutivo END AS consecutivo, \n" +
        "	CASE WHEN imagen.folio IS NULL THEN ' ' ELSE imagen.folio END AS folio, \n" +
        "	CASE WHEN imagen.url IS NULL THEN ' ' ELSE imagen.url END AS url, \n" +
        "	CASE WHEN to_char(to_date(imagen.fecha,'dd/mm/yyyy'), 'dd/mm/yyyy') IS NULL THEN '01/01/1999' ELSE to_char(to_date(imagen.fecha,'dd/mm/yyyy'), 'dd/mm/yyyy') END AS fecha,\n" +
        "	CASE WHEN imagen.descripcion IS NULL THEN ' ' ELSE imagen.descripcion END AS descripcion, \n" +
        "	CASE WHEN campos.descripcion IS NULL THEN ' ' ELSE campos.descripcion END AS campoasociado \n" +
        "	FROM formularios.principal\n" +
        "	LEFT JOIN formularios.imagen ON formularios.principal.folio = formularios.imagen.folio\n" +
        "	LEFT JOIN catalogos.campos ON CAST(formularios.imagen.campoasociado AS INTEGER) = catalogos.campos.id\n" +
        "	WHERE formularios.imagen.folio = '"+folio+"'");
        ResultSetHandler rsh = new BeanListHandler(ListArchivos.class);
        List<ListArchivos> result = (List<ListArchivos>)qr.query(sql.toString(), rsh);
        return result;
    }
}
