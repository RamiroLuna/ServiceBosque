/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dao;

import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.ResultString;
import org.probosque.dto.TableCartograficoDTO;
import org.probosque.dto.UserDTO;

/**
 *
 * @author Jonathan
 */
public class CartograficoDAO {
    public List<TableCartograficoDTO> ObtenerDatosCartografico(UserDTO user, String folio) throws Exception {
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        int u=user.getId();
        
//        if (u==11){
//        sql.append(" SELECT\n" +
//"Formularios.principal.folio as Folio,\n" +
//"catalogos.municipio.descripcion as Municipio, \n" +
//"catalogos.localidad.descripcion as Localidad, \n" +
//"catalogos.region.descripcion as Región,\n" +
//"catalogos.predios.id as Predio,\n" +
//"catalogos.predios.descripcion as Predio,\n" +
//"catalogos.anio.descripcion as Año,\n" +
//"\n" +
//"representante_actual as Nombre_Representante_Actual,\n" +
//"       formularios.areascorta.area_cortaa as Áreas_de_Corta,\n" +
//"       formularios.rodal_especie.rodal as Rodal,\n" +
//"       sum(formularios.especies.existencias) as Existencias,\n" +
//"       sum(formularios.especies.posibilidad) as Posibilidad,\n" +
//"       sum(formularios.especies.residual) as Residual,\n" +
//"       sum(formularios.especies.ica) as ICA_Pino_y_Oyamel\n" +
//"\n" +
//"FROM formularios.principal \n" +
//"LEFT OUTER JOIN catalogos.municipio on  trim (both ' ' from to_char(catalogos.municipio.id, '9999'))=formularios.principal.modulopredio_municipio\n" +
//"LEFT OUTER JOIN catalogos.localidad on  trim (both ' ' from to_char(catalogos.localidad.id, '9999'))=formularios.principal.modulopredio_localidad \n" +
//"AND TRIM (both ' ' from to_char(catalogos.localidad.id_municipio, '9999'))=formularios.principal.modulopredio_municipio\n" +
//"LEFT OUTER JOIN catalogos.region on  trim (both ' ' from to_char(catalogos.region.id, '9999'))=formularios.principal.region \n" +
//"LEFT OUTER JOIN catalogos.anio on  trim (both ' ' from to_char(catalogos.anio.id, '9999'))=formularios.principal.anio \n" +
//"LEFT OUTER JOIN catalogos.predios on trim( both ' ' from (catalogos.predios.id))=formularios.principal.modulopredio_cup\n" +
//"\n" +
//"LEFT OUTER JOIN formularios.areascorta on trim( both ' ' from (formularios.areascorta.folio))=formularios.principal.folio\n" +
//"	LEFT OUTER JOIN formularios.rodal_especie on trim( both ' ' from (formularios.rodal_especie.folio))=formularios.principal.folio\n" +
//"	LEFT OUTER JOIN formularios.especies on trim( both ' ' from (formularios.especies.folio))=formularios.principal.folio\n" +
//"\n" +
//"\n" +
//"WHERE formularios.principal.folio=  '"+folio+"' \n" +
//"\n" +
//"\n" +
//"GROUP BY catalogos.anio.descripcion, catalogos.predios.id, catalogos.region.descripcion, catalogos.localidad.descripcion , catalogos.municipio.descripcion,formularios.areascorta.area_cortaa ,formularios.principal.folio, formularios.rodal_especie.rodal \n" +
//"--formularios.especies.posibilidad, formularios.especies.residual, formularios.especies.ica\n" +
//"order by 1,2,3,4,5,6,7,8,9,10,11");
//        
//                ResultSetHandler rsh1 = new BeanListHandler(TableCartograficoDTO.class);
//        List<TableCartograficoDTO> requests = (List<TableCartograficoDTO>) qr.query(sql.toString(), rsh1) ;
//        
//        return requests;
//        
//        } 
           sql.append(" SELECT\n" +
            "folio as Folio,\n" +
            "catalogos.municipio.descripcion as Municipio, \n" +
            "catalogos.localidad.descripcion as Localidad, \n" +
            "catalogos.region.descripcion as Región,\n" +
            "catalogos.predios.descripcion as Predio,\n" +
            "catalogos.anio.descripcion as Año\n" +
        "FROM formularios.principal \n" +
            "LEFT OUTER JOIN catalogos.municipio on  trim (both ' ' from to_char(catalogos.municipio.id, '9999'))=formularios.principal.modulopredio_municipio\n" +
            "LEFT OUTER JOIN catalogos.localidad on  trim (both ' ' from to_char(catalogos.localidad.id, '9999'))=formularios.principal.modulopredio_localidad \n" +
            "AND TRIM (both ' ' from to_char(catalogos.localidad.id_municipio, '9999'))=formularios.principal.modulopredio_municipio\n" +
            "LEFT OUTER JOIN catalogos.region on  trim (both ' ' from to_char(catalogos.region.id, '9999'))=formularios.principal.region \n" +
            "LEFT OUTER JOIN catalogos.anio on  trim (both ' ' from to_char(catalogos.anio.id, '9999'))=formularios.principal.anio \n" +
            "LEFT OUTER JOIN catalogos.predios on trim( both ' ' from (catalogos.predios.id))=formularios.principal.modulopredio_cup\n" +
            "WHERE formularios.principal.folio= '"+folio+"' limit 1 ");

             
//        ResultSetHandler rsh = new BeanHandler(ResultString.class);  
//        ResultString result = (ResultString) qr.query(sql.toString(), rsh); 
//        String cadena =  result.getResult();
       
        ResultSetHandler rsh1 = new BeanListHandler(TableCartograficoDTO.class);
        List<TableCartograficoDTO> requests = (List<TableCartograficoDTO>) qr.query(sql.toString(), rsh1) ;
        
        return requests;
    }
    

    

}

