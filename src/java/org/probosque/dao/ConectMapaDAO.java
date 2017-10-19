package org.probosque.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.CartografiaTemasDosDTO;
import org.probosque.dto.FolioPredioDTO;
import org.probosque.dto.ListaConfRasterDTO;
import org.probosque.dto.ListaMapasAdminDTO;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.ResultString;
import org.probosque.dto.TemasDTO;
import org.probosque.dto.UserDTO;


/**
 * Clase que administra en base de datos la información referente a cada uno de los formularios, contiene la descripción de cada una de las columnas que conforman los formularios
 * @author admin
 */

public class ConectMapaDAO {

    public List<TemasDTO> cargarTemas(UserDTO user)throws Exception{

        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner  qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT tablename AS id FROM pg_tables  where schemaname=?");  
        //sql.append("OREDER BY tablename");
        Object[] params = {"capasgeograficas"};
        

        ResultSetHandler rsh = new BeanListHandler(TemasDTO.class);
        List<TemasDTO> resultn =  (List<TemasDTO>)qr.query(sql.toString(), rsh,params);

         return resultn;

     }
    
    
    public ArrayList<String> mostrarTemasPoligonos(UserDTO user, String _tema) throws Exception {

        ArrayList<String> coordenadas = new ArrayList<String>();
        String esquemac = "capasgeograficas.";
        String cadenaSelectGeneral = "SELECT gid";
        String selectFigura = "SELECT ST_ASTEXT(ST_TRANSFORM(ST_SetSRID(the_geom,32614),4326)) as result";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
    
        String cadenaSelect = cadenaSelectGeneral + " FROM " + esquemac + "\"" + _tema + "\"" ;
    
        ResultSetHandler rsh = new BeanListHandler(CartografiaTemasDosDTO.class);
        List<CartografiaTemasDosDTO> result = (List<CartografiaTemasDosDTO>)qr.query(cadenaSelect, rsh);
        
        String cadenaDatos = " ";
        String cadenaSH = " ";
        String resCadSH = " ";
        int x = 0;

       
       for(CartografiaTemasDosDTO dto2: result){
            cadenaDatos = " ";
            resCadSH = " ";

            cadenaSH = selectFigura + " FROM " + esquemac + "\"" + _tema + "\"" + " WHERE gid = '" + dto2.getGid() + "'";
            ResultSetHandler rsh1 = new BeanHandler(ResultString.class);
            ResultString resultCadSH = (ResultString) qr.query(cadenaSH, rsh1);
                    
            resCadSH = resultCadSH.getResult();
   
            cadenaDatos += resCadSH;
            coordenadas.add(cadenaDatos);                                                             
       }

        return coordenadas;
        
    }
    
    
    
    public String conseguirTipoFIgura(UserDTO user , String _tema) throws Exception {
        
        String selectTipoFigura = "SELECT type AS result FROM geometry_columns";
        String whereTipoFigura = " WHERE f_table_schema = 'capasgeograficas' AND f_geometry_column='the_geom'  and f_table_name='";
        
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
    
        String cadenaSelect = selectTipoFigura + whereTipoFigura + _tema + "'";
                      
            ResultSetHandler rsh = new BeanHandler(ResultString.class);
            ResultString result = (ResultString) qr.query(cadenaSelect, rsh);
                    
        String tipoFigura = result.getResult();
        
        return tipoFigura;
        
    }
    
    
    
    public FolioPredioDTO getfolioPredio(UserDTO user,String _tema,String _figura,String _tipoFigura) throws SQLException{
       
        FolioPredioDTO foliopredio = new FolioPredioDTO();
        String selectFolioPoligono = "SELECT folio as result FROM capasgeograficas.";
        String whereFolioPoligono = " WHERE the_geom = ST_Transform(ST_GeomFromText('";
        
        String selectFolioPunto = "SELECT folio as result FROM capasgeograficas.";
        String whereFolioPunto = " WHERE st_ASTEXT(ST_Transform(the_geom,4326)) = '";
        String selectPredio = "SELECT catalogos.predios.descripcion as result FROM formularios.principal ";
        String innerPredio = "inner join catalogos.predios on  trim (both ' ' from (catalogos.predios.id))=formularios.principal.modulopredio_cup ";
        String wherePredio = "WHERE formularios.principal.folio = ";
        String selecvalpred= "Select count (*) result FROM formularios.principal "; 
        String folioFigura = "";
        String nombrePredio = "";

        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
    
        if(_tipoFigura.equals("MULTIPOLYGON")){
            
            String cadenaSelectPoligono = selectFolioPoligono + "\"" + _tema + "\"" + whereFolioPoligono + _figura + "',4326), 32614)";

            ResultSetHandler rsh = new BeanHandler(ResultString.class);
            ResultString result = (ResultString) qr.query(cadenaSelectPoligono, rsh);
                 
            folioFigura = result.getResult();
            
        }
        
        if(_tipoFigura.equals("POINT")){
            
            String cadenaSelectPunto = selectFolioPunto + "\"" + _tema + "\"" + whereFolioPunto + _figura + "' limit 1";   
            
            ResultSetHandler rsh = new BeanHandler(ResultString.class);
            ResultString result = (ResultString) qr.query(cadenaSelectPunto, rsh);
                    
            folioFigura = result.getResult();
            
        }
        
        if(_tipoFigura.equals("MULTILINESTRING")){
            
            String cadenaSelectPoligono = selectFolioPoligono + "\"" + _tema + "\"" + whereFolioPoligono + _figura + "',4326), 32614)";
   
            ResultSetHandler rsh = new BeanHandler(ResultString.class);
            ResultString result = (ResultString) qr.query(cadenaSelectPoligono, rsh);
                 
            folioFigura = result.getResult();
            
        }
        foliopredio.setFolio(folioFigura);
        
        
        String cadenavalbas ="Select count (*) result from formularios.principal WHERE formularios.principal.folio =" + "'" +  folioFigura + "'"; 
        
            ResultSetHandler rshvba = new BeanHandler(ResultInteger.class);
            ResultInteger resultvba = (ResultInteger) qr.query(cadenavalbas, rshvba);
            
            if (resultvba.getResult()==0){
                nombrePredio= "No existe el folio en la base de datos";
            }
            else{
                String cadenaPredio = selectPredio + innerPredio + wherePredio + "'" +  folioFigura + "'";
                String cadenaSelecvald=   selecvalpred + innerPredio + wherePredio + "'" +  folioFigura + "'";      

                ResultSetHandler rshv = new BeanHandler(ResultInteger.class);
                ResultInteger resultv = (ResultInteger) qr.query(cadenaSelecvald, rshv);

                if(resultv.getResult()==0 ){
                    nombrePredio="No hay relacion Folio  y Clave de Predio";
                }
                else{
                    ResultSetHandler rsh1 = new BeanHandler(ResultString.class);
                    ResultString result1 = (ResultString) qr.query(cadenaPredio, rsh1);
                    nombrePredio = result1.getResult();
                }
            }
        foliopredio.setNombrePredio(nombrePredio);
   
        return foliopredio;
    }
    
    
    
    public String areaFigura(UserDTO user, String _tema, String _figura, String _tipoFigura) throws Exception {
        
        String selectAreaFigura = "SELECT to_char ((ST_Area(the_geom)*POWER(0.3048,2)/1000),'999999999.99') as result FROM capasgeograficas.";
        String whereAreaFigura = " WHERE the_geom = ST_Transform(ST_GeomFromText('";
        String areaFigura = "";
                
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
    
        if(_tipoFigura.equals("MULTIPOLYGON")){
            String cadenaSelectPoligono = selectAreaFigura + "\"" + _tema + "\"" + whereAreaFigura + _figura + "',4326), 32614)";
 
            ResultSetHandler rsh = new BeanHandler(ResultString.class);
            ResultString result = (ResultString) qr.query(cadenaSelectPoligono, rsh);
                    
            areaFigura = result.getResult();
            
        }
        
        else{
            areaFigura = "S/D";
        }
        
        return areaFigura;
    }
    
    
    
    public String uploadRaster(UserDTO user, String _nombreRaster, String _cuadraturaRaster, String _tamanoRaster) throws Exception {
        
        String selecMaxId = "SELECT case when max (identificador_raster) is null then 0 else max (identificador_raster) end +1 as result from general.raster";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger resultId = (ResultInteger) qr.query(selecMaxId, rsh);       
        
        String selectAreaFigura = "INSERT INTO general.raster (identificador_raster, nombre_raster, cuadratura_raster, tamano_raster) VALUES('"+resultId.getResult()+"', '"+_nombreRaster+"', '"+_cuadraturaRaster+"', '"+_tamanoRaster+"')";    
        
        qr.update(selectAreaFigura);
        String respuesta =  "Imagen raster arriba";
        
        return respuesta;
    }
    
    
    public String uploadKML(UserDTO user, String _descripcionKML) throws Exception {
        
        String selecMaxId = "SELECT case when max (id_kml) is null then 0 else max (id_kml) end +1 as result from general.kml";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger resultId = (ResultInteger) qr.query(selecMaxId, rsh);       
        
        String selectAreaFigura = "INSERT INTO general.kml (id_kml, descripcion_kml) VALUES('"+resultId.getResult()+"', '"+_descripcionKML+"')";    
        
        qr.update(selectAreaFigura);
        String respuesta =  "KML arriba";
        
        return respuesta;
    }
    
    
    public String uploadServicio(UserDTO user, String _nombreServicio, String _tokenServicio) throws Exception {
        
        String selecMaxId = "SELECT case when max (id_servicios) is null then 0 else max (id_servicios) end +1 as result from general.servicios";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger resultId = (ResultInteger) qr.query(selecMaxId, rsh);       
        
        String selectAreaFigura = "INSERT INTO general.servicios (id_servicios, nombre_servicios, token) VALUES('"+resultId.getResult()+"', '"+_nombreServicio+"', '"+_tokenServicio+"')";    
        
        qr.update(selectAreaFigura);
        String respuesta = "Servicio arriba";
        
        return respuesta;
    }
    
    
    
    public String mostrarDescripcionesPoligonos(UserDTO user , String _tema, String _figura, String _tipoFigura) throws Exception {
        
        String selectDescripcionPoligono = "SELECT descripcion as result FROM capasgeograficas.";
        String whereDescripcionPoligono = " WHERE the_geom = ST_Transform(ST_GeomFromText('";
        String selectDescripcionPunto = "SELECT descripcion as result FROM capasgeograficas.";
        String whereDescripcionPunto = " WHERE st_ASTEXT(ST_Transform(the_geom,4326)) = '";
  
        String descripcionFigura = " ";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
    
        if(_tipoFigura.equals("MULTIPOLYGON")){
            
            String cadenaSelectPoligono = selectDescripcionPoligono + "\"" + _tema + "\"" + whereDescripcionPoligono + _figura + "',4326), 32614)";
            ResultSetHandler rsh = new BeanHandler(ResultString.class);
            ResultString result = (ResultString) qr.query(cadenaSelectPoligono, rsh);
                    
            descripcionFigura = result.getResult();
            
        }
        
        if(_tipoFigura.equals("POINT")){
            
            String cadenaSelectPunto = selectDescripcionPunto + "\"" + _tema + "\"" + whereDescripcionPunto + _figura + "' limit 1";
            ResultSetHandler rsh = new BeanHandler(ResultString.class);
            ResultString result = (ResultString) qr.query(cadenaSelectPunto, rsh);
                    
            descripcionFigura = result.getResult();
            
        }
        
        if(_tipoFigura.equals("MULTILINESTRING")){
            
            String cadenaSelectPoligono = selectDescripcionPoligono + "\"" + _tema + "\"" + whereDescripcionPoligono + _figura + "',4326), 32614)";
            ResultSetHandler rsh = new BeanHandler(ResultString.class);
            ResultString result = (ResultString) qr.query(cadenaSelectPoligono, rsh);
                    
            descripcionFigura = result.getResult();
        }
        
        return descripcionFigura;
        
    }
    
    
    public String mostrarRegiones(UserDTO user) throws Exception {
        
           String cadenaDatos = "GEOMETRYCOLLECTION(";
           String enviarRegion = "";

           DataSource ds = PoolDataSource.getDataSource(user);
           QueryRunner qr = new QueryRunner(ds);
           
           for (int x = 1; x<=8; x++){
                String selectRegiones = "SELECT ST_ASTEXT(ST_Force_2D(ST_TRANSFORM(ST_SETSRID(geom, 32614), 4326))) as result FROM general.regiones WHERE gid = " + x;
                ResultSetHandler rsh = new BeanHandler(ResultString.class);
                ResultString result = (ResultString) qr.query(selectRegiones, rsh);

                enviarRegion = result.getResult();

                cadenaDatos += enviarRegion;

                if(x < 8){
                    cadenaDatos += ",";
                } 
            }
       
       cadenaDatos += ")";
       
       return cadenaDatos;
    }
    
    
    public String mostrarLocalidades(UserDTO user) throws Exception {
        
        String cadenaDatos = "GEOMETRYCOLLECTION(";
        String enviarLocalidad = " ";
                
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
         
        for (int x = 1; x<=125; x++){
           
            String selectRegiones = "SELECT ST_ASTEXT(geom) as result FROM general."+ "\""+"Municipios" +"\""+ "WHERE gid = " + x;
            ResultSetHandler rsh = new BeanHandler(ResultString.class);
            ResultString result = (ResultString) qr.query(selectRegiones, rsh);
       
            enviarLocalidad = result.getResult();
            
            cadenaDatos += enviarLocalidad;
            
            if(x < 125){
                cadenaDatos += ",";
            }
        }
       
        cadenaDatos += ")";
        
        return cadenaDatos;
    }
    
    
    public String mostrarLocalRurales(UserDTO user) throws Exception {
        String cadenaDatos = "GEOMETRYCOLLECTION(";
        String enviarRural = "";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);

        for (int x = 1; x<=421; x++){

             String selectRural = "SELECT ST_ASTEXT(geom) as result FROM general."+ "\""+"LocalidaUrbanas" +"\""+ " WHERE gid = " + x;
             ResultSetHandler rsh = new BeanHandler(ResultString.class);
             ResultString result = (ResultString) qr.query(selectRural, rsh);

             enviarRural = result.getResult();

             cadenaDatos += enviarRural;

             if(x < 421){
                 cadenaDatos += ",";
             }
        }

        cadenaDatos += ")";
        
        return cadenaDatos;
    }
    
    
    public String insertTema(UserDTO user, String _tema) throws Exception {
        
        String _tema_guion = _tema.replaceAll(" ","_");
       
        String selectCount = "SELECT count (*) as result FROM information_schema.columns where table_schema <>'information_schema' and table_schema <>'pg_catalog' and table_schema='capasgeograficas' and table_name like ";
        String createNewTable = "CREATE TABLE capasgeograficas.";
        String createNewTable1 = "(gid integer not null, folio text, descripcion text, origen character varying(16), tipfig character varying(30), the_geom geometry(MultiPolygon,32614), the_geom1 geometry(Point,32614),the_geom2 geometry(LINESTRING,32614), CONSTRAINT pk_";
        String createNewTable2 = " PRIMARY KEY (gid)); create unique index ";
        String createNewTable3 = "_PK on capasgeograficas.";
        String respuesta = "";

        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);

        Connection con= ds.getConnection();
        Statement sta= con.createStatement();


        String existeTema = selectCount + "'" + _tema + "'";

        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger resultn = (ResultInteger) qr.query(existeTema, rsh);

        if (resultn.getResult() <= 0){

            String cadenaCreate = createNewTable + "\"" + _tema + "\"" + createNewTable1 + _tema_guion + createNewTable2 + _tema_guion + createNewTable3 + "\"" + _tema + "\"" + "(gid)";
            ds = PoolDataSource.getDataSource(user);
            qr = new QueryRunner(ds);
            sta.executeUpdate(cadenaCreate);
            respuesta =  "Tema agregado";
                  
        } else{
             
            respuesta = "Ese tema ya existe";
        }
        
        return respuesta;
    }
   
    
    
   /* public String insertFiguraManual(UserDTO user, String _tema, String _cadenaFigura, String _descripcion, String _folio, String _colorRelleno, String _colorContorno, String _tipoCoordenada) throws Exception {
        
        String maximotema="SELECT case when max (gid) is null then 0 else max (gid) end +1 as result FROM capasgeograficas.";
        String cadenaE1;
        String respuesta="";
        int tipoCoordenada = Integer.parseInt(_tipoCoordenada);
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
 
        cadenaE1=maximotema + "\"" + _tema+ "\"";  
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger   resultn = (ResultInteger) qr.query(cadenaE1, rsh);
       
        if (resultn.getResult() > 0){
            
            String idgid= Integer.toString(resultn.getResult());
            if(tipoCoordenada == 1){
                String cadenaInsertGeo;  
                // creamos la cadena de insercion 
                cadenaInsertGeo = "INSERT INTO capasgeograficas." + "\"" + _tema+ "\"" + " (gid, descripcion, folio, the_geom) VALUES (" + idgid + ", " + "'" + _descripcion + "'" + ", " + "'" + _folio + "'" + ", " + "(St_Transform(ST_Multi(ST_GeomFromText ('" + _cadenaFigura + "', 4326)),32614)))";
                ds = PoolDataSource.getDataSource(user);
                qr = new QueryRunner(ds);
        
                qr.update(cadenaInsertGeo);
                respuesta =  "Figura geográfica agregada";
            }
            else if (tipoCoordenada == 2){
                String cadenaInsertUTM;
                cadenaInsertUTM = "INSERT INTO capasgeograficas." + "\"" + _tema+ "\"" + " (gid, descripcion, folio, the_geom) VALUES (" + idgid + ", " + _descripcion + ", " + _folio + ", " + "(ST_Multi(ST_GeomFromText ('" + _cadenaFigura + "'),32614)))";
                ds = PoolDataSource.getDataSource(user);
                qr = new QueryRunner(ds);
        
                qr.update(cadenaInsertUTM);
                respuesta = "Figura UTM agregada";
            }
            else{
                respuesta = "No se ha agregado su figura";
            }
        }
        
        return respuesta;
    }
    
    
    
    public String insertFigura(UserDTO user, String _tema, String _cadenaFigura, String _descripcion, String _folio, String _colorRelleno, String _colorContorno, String _tipoFigura) throws Exception {
        
        String maximotema = "SELECT case when max (gid) is null then 0 else max (gid) end +1 as result FROM capasgeograficas.";
        String inrsion="INSERT INTO ";
        String esquemac="capasgeograficas."; 
        String basefigurabas = " ( gid,descripcion, tipfig, origen, folio, the_geom)VALUES (";
        String figuramulti=",(select ST_Multi(ST_GeomFromText (";
        String finmulti=", 32614))))";
        String coma=","; 
        String comillas="'";
        String cadenaE1;
    
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);

        cadenaE1=maximotema + "\"" + _tema+ "\"";  
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger   resultn = (ResultInteger) qr.query(cadenaE1, rsh);
       
        if (resultn.getResult() > 0){
            String CadenaInsert;
            String idgid= Integer.toString(resultn.getResult());
            CadenaInsert = inrsion + esquemac + "\"" + _tema+ "\"" + basefigurabas + idgid + coma + comillas + _descripcion + comillas + coma + comillas + _tipoFigura + comillas + coma + "'fm'" + coma + comillas + _folio + comillas + figuramulti + comillas + _cadenaFigura + comillas + finmulti;
            ds = PoolDataSource.getDataSource(user);
            qr = new QueryRunner(ds);
        
            qr.update(CadenaInsert);
        }
        
        String respuesta =  "Figura agregada";
        
        return respuesta;
    }
 */
    
    
    public String insertMapa(UserDTO user) throws Exception {
        
        String selectCount = "SELECT count (*) result FROM general.mapa WHERE mapa_descripcion = 'Mapa Default'";
        String selectCaseMax = "SELECT case when max (mapa_id) is null then 0 else max (mapa_id) end +1 as result from general.mapa";
        String insertMapaPivote = "INSERT INTO general.mapa(mapa_id, mapa_descripcion, mapa_tipo, mapa__status)";
        String respuesta = "";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
 
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(selectCount, rsh);
       
        if (result.getResult() == 0){
            
            ResultSetHandler rsh1 = new BeanHandler(ResultInteger.class);
            ResultInteger resultId = (ResultInteger) qr.query(selectCaseMax, rsh1);
            
            String insercionMapa = insertMapaPivote + "VALUES (" + resultId.getResult() + ", " + "'Mapa Default', 'P','A')";
        
            qr.update(insercionMapa);
            respuesta =  "Figura geográfica agregada";
            
        }else{
            
            respuesta = "No se ha agregado su figura";
        }
        
        return respuesta;
    }
    
    
    
    public String insertTemaAdmin(UserDTO user, String _tema, String _colorTema) throws Exception {
        
        String insertTemaAdm = "select  general.insertartema(";
        String selectCaseMax = "SELECT case when max (mapa_id) is null then 0 else max (mapa_id) end AS result from general.mapa";
        String respuesta = "";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        
        String cadenaTypeFigura = "SELECT type AS result FROM geometry_columns where f_table_schema='capasgeograficas' and f_table_name='"+_tema+"' and f_geometry_column= 'the_geom'";
        
        ResultSetHandler rsh2 = new BeanHandler(ResultString.class);
        ResultString resultType = (ResultString) qr.query(cadenaTypeFigura, rsh2);
        
        String _figuraType = resultType.getResult();
            
        ResultSetHandler rsh1 = new BeanHandler(ResultInteger.class);
        ResultInteger resultId = (ResultInteger) qr.query(selectCaseMax, rsh1);
            
        String insercionTema = insertTemaAdm + resultId.getResult() + ", '" + _tema + "', '" + _figuraType  + "', '" + _colorTema + "') AS result";
        
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(insercionTema, rsh);
        
        if(result.getResult() == 0){
            respuesta = "No se inserto tema";
        }else{
            respuesta = "Tema insertado";
        }
        
        return respuesta;
    }
    
    
    
    public String insertIndicadores(UserDTO user, String _nombreIndicador, String _px, String _py) throws Exception {
        
        String selectCaseMax = "SELECT case when max (mapa_id) is null then 0 else max (mapa_id) end AS result from general.mapa";
        String respuesta = "";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
            
        ResultSetHandler rsh1 = new BeanHandler(ResultInteger.class);
        ResultInteger resultId = (ResultInteger) qr.query(selectCaseMax, rsh1);
            
        String insercionIndicador = "SELECT general.inserindicado(" + resultId.getResult() + ", '" + _px + "', '" + _py + "', '" + _nombreIndicador  + "') AS result";
        
        ResultSetHandler rsh = new BeanHandler(ResultString.class);
        ResultString result = (ResultString) qr.query(insercionIndicador, rsh);
        
        respuesta = result.getResult();
         
        return respuesta;
    }
    
    
    public List<ListaMapasAdminDTO> getListMapas(UserDTO user)throws Exception{

        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner  qr = new QueryRunner(ds);
        
        String cadenaSelect="SELECT mapa_id, mapa_descripcion FROM general.mapa";
        
        ResultSetHandler rsh = new BeanListHandler(ListaMapasAdminDTO.class);
        List<ListaMapasAdminDTO> result = (List<ListaMapasAdminDTO>)qr.query(cadenaSelect, rsh);

        return result;

     }
    
    
    public List<ListaConfRasterDTO> getConfRaster(UserDTO user, String _verNombreRaster)throws Exception{

        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner  qr = new QueryRunner(ds);
        
        String cadenaSelect="SELECT nombre_raster, cuadratura_raster, tamano_raster FROM general.raster WHERE nombre_raster = '"+_verNombreRaster+"'";
        
        ResultSetHandler rsh = new BeanListHandler(ListaConfRasterDTO.class);
        List<ListaConfRasterDTO> result = (List<ListaConfRasterDTO>)qr.query(cadenaSelect, rsh);

        return result;

     }
    
    
    public String eliminarTema(UserDTO user, String _tema) throws Exception {
        
        String respuesta = "";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
            
        String cadenaDelete = "DROP TABLE capasgeograficas."+ "\"" + _tema + "\"";
        
        qr.update(cadenaDelete);
        
        return respuesta;
    }

    
    public String contadorTemas(UserDTO user) throws Exception {
        
        String selectCaseMax = "SELECT count (*) AS result FROM pg_tables WHERE schemaname='capasgeograficas'";
        String respuesta = "";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
            
        ResultSetHandler rsh1 = new BeanHandler(ResultString.class);
        ResultString resultId = (ResultString) qr.query(selectCaseMax, rsh1);
            
        respuesta = resultId.getResult();
         
        return respuesta;
    }
    
    public String eliminarTablaTema(UserDTO user, String _tema) throws Exception {
        
        String respuesta = "";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
            
        String cadenaDelete = "DROP TABLE capasgeograficas."+ "\"" + _tema + "\"";
        
        qr.update(cadenaDelete);
        
        return respuesta;
    }
}
