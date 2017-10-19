/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dao;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.probosque.dto.CartografiaTemasDTO;
import org.probosque.dto.CartografiaTemasDosDTO;
import org.probosque.dto.ListaIndicadorAdminDTO;
import org.probosque.dto.ListaMapasAdminDTO;
import org.probosque.dto.ListaTemasAdmin;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.ResultString;
import org.probosque.dto.UserDTO;

/**
 *
 * @author Administrador
 */
public class CartoAdminDAO {
    
    
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
    
    
    public String getFiguraType(UserDTO user, String _tema, String _programa) throws Exception {
        
        int programa = Integer.parseInt(_programa);
        
        String selectUsuario = "SELECT id AS result FROM \"user\" where program=";
        String selectFiguraType = "SELECT type AS result FROM geometry_columns where f_table_schema='capasgeograficas' and f_table_name='";
        String figuraType = " ";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        
        if(programa == 71 || programa == 72){
            programa = 7;
        }
        String usuarioNuevo = selectUsuario + "'" + programa + "' " + "limit 1";
        
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(usuarioNuevo, rsh);
       
        UserDAO userDao = new UserDAO();
        UserDTO user1 = userDao.getUser(result.getResult());
        
        DataSource ds1 = PoolDataSource.getDataSource(user1);
        QueryRunner qr1 = new QueryRunner(ds1);
        
        String consultaFiguraType = selectFiguraType + _tema + "' and f_geometry_column= 'the_geom'";
        
        ResultSetHandler rsh1 = new BeanHandler(ResultString.class);
        ResultString result1 = (ResultString) qr1.query(consultaFiguraType, rsh1);
        
        figuraType = result1.getResult();
        
        return figuraType;
    }
            
            
    public List<CartografiaTemasDTO> recuperacionPopUp(UserDTO user, String _figura) throws Exception {
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        List<CartografiaTemasDTO> resultFolDes = null;
        
        String cadenaMapaId = "SELECT mapa_id AS result FROM general.mapa WHERE mapa_descripcion = 'Mapa Default'";
      
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(cadenaMapaId, rsh);
        
        int mapaId = result.getResult();
        String tipoFigura = "";
        
        if(_figura.length() < 53){

            tipoFigura = "POINT("+_figura+")";
            String cadenaBusPunto = "SELECT tema_descripcion, tema_tipfig, tema_programa FROM general.temas where  tema_tipfig = 'POINT' and mapa_id='"+ mapaId +"'"+ "order by tema_programa";
            
            ResultSetHandler rsh1 = new BeanListHandler(ListaTemasAdmin.class);
            List<ListaTemasAdmin> resultListTema = (List<ListaTemasAdmin>)qr.query(cadenaBusPunto, rsh1);
            
            for(ListaTemasAdmin dto: resultListTema){
                
                if(resultFolDes==null || resultFolDes.size() == 0){
                
                    String selectUsuario = "SELECT id AS result FROM \"user\" where program='"+dto.getTema_programa()+"'";

                    if(dto.getTema_programa().equals("71") || dto.getTema_programa().equals("72")){
                        selectUsuario = "SELECT id AS result FROM \"user\" where program='7'";
                    }

                    rsh = new BeanHandler(ResultInteger.class);
                    result = (ResultInteger) qr.query(selectUsuario, rsh);

                    UserDAO userDao = new UserDAO();
                    UserDTO user1 = userDao.getUser(result.getResult());

                    DataSource ds1 = PoolDataSource.getDataSource(user1);
                    QueryRunner qr1 = new QueryRunner(ds1);

                    String selectFigura = "SELECT folio, descripcion FROM capasgeograficas."+"\""+dto.getTema_descripcion()+"\""+" WHERE st_ASTEXT(ST_Transform(the_geom,4326)) = '"+tipoFigura+"' limit 1";

                    ResultSetHandler rshFolDes = new BeanListHandler(CartografiaTemasDTO.class);
                    resultFolDes = (List<CartografiaTemasDTO>)qr1.query(selectFigura, rshFolDes);
                    
                    for(CartografiaTemasDTO ct: resultFolDes){
                        
                        ct.setPrograma(dto.getTema_programa());
                        
                    }

                }
            }
            
        }
        
        else {
            
            int left = _figura.indexOf(",");
            String figura = _figura.substring(0,left);
            tipoFigura = "MULTIPOLYGON((("+_figura+","+figura+")))";
            
            String cadenaBusPolygon = "SELECT tema_descripcion, tema_tipfig, tema_programa FROM general.temas where  tema_tipfig <> 'POINT' and mapa_id='"+ mapaId +"'"+ "order by tema_programa";
            
            ResultSetHandler rsh1 = new BeanListHandler(ListaTemasAdmin.class);
            List<ListaTemasAdmin> resultListTema = (List<ListaTemasAdmin>)qr.query(cadenaBusPolygon, rsh1);
            
            for(ListaTemasAdmin dto: resultListTema){
                
                if(resultFolDes==null || resultFolDes.size() == 0){
                
                    String selectUsuario = "SELECT id AS result FROM \"user\" where program='"+dto.getTema_programa()+"'";

                    if(dto.getTema_programa().equals("71") || dto.getTema_programa().equals("72")){
                        selectUsuario = "SELECT id AS result FROM \"user\" where program='7'";
                    }

                    rsh = new BeanHandler(ResultInteger.class);
                    result = (ResultInteger) qr.query(selectUsuario, rsh);

                    UserDAO userDao = new UserDAO();
                    UserDTO user1 = userDao.getUser(result.getResult());

                    DataSource ds1 = PoolDataSource.getDataSource(user1);
                    QueryRunner qr1 = new QueryRunner(ds1);

                    String selectFigura = "SELECT folio, descripcion FROM capasgeograficas."+"\""+dto.getTema_descripcion()+"\""+" WHERE the_geom = ST_Transform(ST_GeomFromText('"+tipoFigura+"',4326), 32614)";

                    ResultSetHandler rshFolDes = new BeanListHandler(CartografiaTemasDTO.class);
                    resultFolDes = (List<CartografiaTemasDTO>)qr1.query(selectFigura, rshFolDes);
                
                    for(CartografiaTemasDTO ct: resultFolDes){
                        
                        ct.setPrograma(dto.getTema_programa());
                        
                    }
                }
            }
            
            
        }
        
        return resultFolDes;
        
    }
    
    
    public String insertTemaAdmin(UserDTO user, String _tema, String _programa, String _figuraType, String _colorTema) throws Exception {
        
        String insertTemaAdm = "select  general.insertartema(";
        String selectCaseMax = "SELECT case when max (mapa_id) is null then 0 else max (mapa_id) end AS result from general.mapa";
        String respuesta = "";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
            
        ResultSetHandler rsh1 = new BeanHandler(ResultInteger.class);
        ResultInteger resultId = (ResultInteger) qr.query(selectCaseMax, rsh1);
            
        String insercionTema = insertTemaAdm + resultId.getResult() + ", '" + _tema + "', '" + _programa + "', '" + _figuraType  + "', '" + _colorTema + "') AS result";
        
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(insercionTema, rsh);
        
        if(result.getResult() == 0){
            respuesta = "No se inserto tema";
        }else{
            respuesta = "Tema insertado";
        }
        
        return respuesta;
    }
    
    
    public List<ListaTemasAdmin> getListTemas(UserDTO user, String _idMapa)throws Exception{

        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner  qr = new QueryRunner(ds);
        
        int idMapa = Integer.parseInt(_idMapa);
        
        String cadenaSelect="SELECT tema_descripcion, tema_programa, tema_color FROM general.temas WHERE mapa_id = '"+idMapa+"'";
        
        ResultSetHandler rsh = new BeanListHandler(ListaTemasAdmin.class);
        List<ListaTemasAdmin> result = (List<ListaTemasAdmin>)qr.query(cadenaSelect, rsh);

        return result;

     }
    
    
    public List<ListaIndicadorAdminDTO> getListIndicadores(UserDTO user, String _idMapa)throws Exception{

        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner  qr = new QueryRunner(ds);
        
        int idMapa = Integer.parseInt(_idMapa);
        
        String cadenaSelect="SELECT indicador_id, indicador_nombre, mapa_id, indicador_x, indicador_y FROM general.indicador WHERE mapa_id = '" + idMapa + "'";
        
        ResultSetHandler rsh = new BeanListHandler(ListaIndicadorAdminDTO.class);
        List<ListaIndicadorAdminDTO> result = (List<ListaIndicadorAdminDTO>)qr.query(cadenaSelect, rsh);

        return result;

     }
    
    
    public List<ListaMapasAdminDTO> getListMapas(UserDTO user)throws Exception{

        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner  qr = new QueryRunner(ds);
        
        String cadenaSelect="SELECT mapa_id, mapa_descripcion FROM general.mapa";
        
        ResultSetHandler rsh = new BeanListHandler(ListaMapasAdminDTO.class);
        List<ListaMapasAdminDTO> result = (List<ListaMapasAdminDTO>)qr.query(cadenaSelect, rsh);

        return result;

     }
        
    
    public String eliminarTemas(UserDTO user, String _tema, String _programa) throws Exception {
        
        String deleteTema = "DELETE FROM general.temas WHERE tema_descripcion ='";
        String respuesta = "";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
            
        String cadenaDelete = deleteTema +  _tema + "' AND tema_programa ='" + _programa + "'";
        
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(cadenaDelete, rsh);
        
        return respuesta;
    }
    
    
    public String guardarMapa(UserDTO user, String _nombreMapa) throws Exception {
        
        String guardarMapa = "UPDATE general.mapa SET mapa_descripcion = '";
        String respuesta = "";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
            
        String cadenaGuardar = guardarMapa +  _nombreMapa + "' WHERE mapa_descripcion = 'Mapa Default'";
        
        qr.update(cadenaGuardar);
        
        return respuesta;
    }
    
    
    public String eliminarMapas(UserDTO user, String _idMapa) throws Exception {
        
        String deleteMapa = "SELECT general.mapaelimina(";
        String respuesta = "";
        
        int idMapa = Integer.parseInt(_idMapa);
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
            
        String cadenaDelete = deleteMapa + idMapa + ")";
        
        ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
        ResultInteger result = (ResultInteger) qr.query(cadenaDelete, rsh);
        
        return respuesta;
    }
    
    
    
    public String insertIndicadores(UserDTO user, String _nombreIndicador, String _px, String _py) throws Exception {
        
        String insertIndicador = "SELECT general.inserindicado(";
        String selectCaseMax = "SELECT case when max (mapa_id) is null then 0 else max (mapa_id) end AS result from general.mapa";
        String respuesta = "";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
            
        ResultSetHandler rsh1 = new BeanHandler(ResultInteger.class);
        ResultInteger resultId = (ResultInteger) qr.query(selectCaseMax, rsh1);
            
        String insercionIndicador = insertIndicador + resultId.getResult() + ", '" + _px + "', '" + _py + "', '" + _nombreIndicador  + "') AS result";
        
        ResultSetHandler rsh = new BeanHandler(ResultString.class);
        ResultString result = (ResultString) qr.query(insercionIndicador, rsh);
        
        respuesta = result.getResult();
         
        return respuesta;
    }
    
    
    public String eliminarIndicador(UserDTO user, String _indicadorId, String _idMapa) throws Exception {
        
        String deleteIndicador = "DELETE FROM general.indicador WHERE indicador_id ='";
        String respuesta = "";
        
        int idMapa = Integer.parseInt(_idMapa);
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
            
        String cadenaDelete = deleteIndicador + _indicadorId + "' AND mapa_id ='" + idMapa + "'";
        
        qr.update(cadenaDelete);
        
        return respuesta;
    }
    
    
    public ArrayList<String> pintarMapaTemas(UserDTO user, String _idMapa) throws Exception {
                
        int idMapa = Integer.parseInt(_idMapa);
        ArrayList<String> coordenadas = new ArrayList<String>();
        
        String selectListaTemas = "SELECT tema_descripcion, tema_programa, tema_color FROM general.temas WHERE mapa_id ='"+idMapa+"'";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        
        ResultSetHandler rsh = new BeanListHandler(ListaTemasAdmin.class);
        List<ListaTemasAdmin> result = (List<ListaTemasAdmin>)qr.query(selectListaTemas, rsh);
        
        for(ListaTemasAdmin dto: result){
            
            String  Programa1= dto.getTema_programa().toString();
            if(dto.getTema_programa().equals(71) || dto.getTema_programa().equals(72)){
                Programa1= "7";
            }
            
            String selectUsuario = "SELECT id AS result FROM \"user\" where program='"+Programa1+"' limit 1";
            
            ResultSetHandler rshUsr = new BeanHandler(ResultInteger.class);
            ResultInteger resultUsr = (ResultInteger) qr.query(selectUsuario, rshUsr);
            
            int nuevoUsuario = resultUsr.getResult();
            
            UserDAO userDao = new UserDAO();
            UserDTO user1 = userDao.getUser(nuevoUsuario);
        
            DataSource ds1 = PoolDataSource.getDataSource(user1);
            QueryRunner qr1 = new QueryRunner(ds1);
            
            String selectGid = "SELECT gid FROM capasgeograficas." + "\"" + dto.getTema_descripcion() + "\"";
            
            ResultSetHandler rshGid = new BeanListHandler(CartografiaTemasDosDTO.class);
            List<CartografiaTemasDosDTO> resultGid = (List<CartografiaTemasDosDTO>)qr1.query(selectGid, rshGid);
            
            for(CartografiaTemasDosDTO dto1: resultGid){
                
                String selectFiguras = "SELECT ST_ASTEXT(ST_TRANSFORM(ST_SetSRID(the_geom,32614),4326)) as result FROM capasgeograficas." + "\"" + dto.getTema_descripcion() + "\" WHERE gid = '" + dto1.getGid() + "'";
                //String selectFiguras = "SELECT ST_ASTEXT(ST_TRANSFORM(ST_SetSRID(the_geom,32614),4326)), '"+ dto.getTema_color() +"' FROM capasgeograficas." + "\"" + dto.getTema_descripcion() + "\" WHERE gid = '" + dto1.getGid() + "'";
                
                ResultSetHandler rshFig = new BeanHandler(ResultString.class);
                ResultString resultFig = (ResultString) qr1.query(selectFiguras, rshFig);
                
              // ResultSetHandler rshFig = new BeanHandler(TemasConColorDTO.class);
               //coordenadasc = (ArrayList<TemasConColorDTO>) qr1.query(selectFiguras, rshFig);
                
                coordenadas.add(resultFig.getResult());
                //coordenadas.add(dto.getTema_color());
            }
        }
        
        return coordenadas;
        
    }
    
    
    
     public List<ListaIndicadorAdminDTO> pintarMapaIndicador(UserDTO user, String _idMapa) throws Exception {
         
        int idMapa = Integer.parseInt(_idMapa);
        
        String selectMapaIndicador = "SELECT indicador_nombre, indicador_x, indicador_y FROM general.indicador WHERE mapa_id = '" + idMapa + "'";
        
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        
        ResultSetHandler rsh = new BeanListHandler(ListaIndicadorAdminDTO.class);
        List<ListaIndicadorAdminDTO> result = (List<ListaIndicadorAdminDTO>)qr.query(selectMapaIndicador, rsh);
        
        return result;
    }
}