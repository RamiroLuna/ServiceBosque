/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.probosque.dto.DPAnioDTO;
import org.probosque.dto.DPCiudadanoDTO;
import org.probosque.dto.DatosSeccionDTO;
import org.probosque.dto.Encabezado2DTO;
import org.probosque.dto.EncabezadosDTO;
import org.probosque.dto.PorcentajePP;
import org.probosque.dto.ProduccionDTO;
import org.probosque.dto.SelectPAA;
import org.probosque.dto.TotalesPP;

/**
 *
 * @author Jonathan
 */
public class TableProduccionPlanta{
    ConexionMySQL conexion = new ConexionMySQL("produccionplanta");
    public List<ProduccionDTO> ObtenerDatosProduccion(String where, String like, String vivero) throws Exception {
        ArrayList<ProduccionDTO> dtoproduccion = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if(like.isEmpty() && vivero.isEmpty()){
                sql = "SELECT Vivero, Nombre_comun, Nombre_cientifico, Lote_Semilla, Tipo_produccion, Programada, Recurso, Envase, Altura, Producción, Disponibilidad, Entregada, F_Exis FROM produccion ORDER BY Vivero, Nombre_comun;";
            }else{
                if (like.isEmpty()) {
                    sql = "SELECT Vivero, Nombre_comun, Nombre_cientifico, Lote_Semilla, Tipo_produccion, Programada, Recurso, Envase, Altura, Producción, Disponibilidad, Entregada, F_Exis FROM produccion where replace(Vivero, ' ','') = '"+vivero+"' ORDER BY Vivero, Nombre_comun;";
                }else if(vivero.isEmpty()){
                    sql = "SELECT Vivero, Nombre_comun, Nombre_cientifico, Lote_Semilla, Tipo_produccion, Programada, Recurso, Envase, Altura, Producción, Disponibilidad, Entregada, F_Exis FROM produccion where replace(Nombre_comun,' ','') LIKE '%"+like.replace(" ","")+"%' or replace(Nombre_cientifico,' ','') LIKE '%"+like.replace(" ","")+"%' ORDER BY Vivero, Nombre_comun;";
                }else{
                    sql = "SELECT Vivero, Nombre_comun, Nombre_cientifico, Lote_Semilla, Tipo_produccion, Programada, Recurso, Envase, Altura, Producción, Disponibilidad, Entregada, F_Exis FROM produccion where replace(Vivero, ' ','') = '"+vivero+"' and (replace(Nombre_comun,' ','') LIKE '%"+like.replace(" ","")+"%' or replace(Nombre_cientifico,' ','') LIKE '%"+like.replace(" ","")+"%') ORDER BY Vivero, Nombre_comun;";
                }
            }
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                dtoproduccion.add(new ProduccionDTO(rs.getString("Vivero"),rs.getString("Nombre_comun"), rs.getString("Nombre_cientifico"), rs.getString("Lote_Semilla"), rs.getString("Tipo_produccion"), rs.getInt("Programada"), rs.getString("Recurso"), rs.getString("Envase"), rs.getString("Altura"), rs.getInt("Producción"), rs.getInt("Disponibilidad"), rs.getFloat("Entregada"), rs.getFloat("F_Exis")));          
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return dtoproduccion;
    }
    
    public List<PorcentajePP> ObtenerPorcentaje(String where, String like, String vivero) throws Exception {
        ArrayList<PorcentajePP> dtoporcentaje = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if(like.isEmpty() && vivero.isEmpty()){
                sql = "SELECT Convert(((SUM(producción) * 100) /SUM(Programada)), decimal(10,2)) as Porcentaje FROM produccion;";
            }else{
                if (like.isEmpty()) {
                    sql = "SELECT Convert(((SUM(producción) * 100) /SUM(Programada)), decimal(10,2)) as Porcentaje FROM produccion where replace(Vivero, ' ','') = '"+vivero+"';";
                }else if(vivero.isEmpty()){
                    sql = "SELECT Convert(((SUM(producción) * 100) /SUM(Programada)), decimal(10,2)) as Porcentaje FROM produccion where replace(Nombre_comun,' ','') LIKE '%"+like.replace(" ","")+"%' or replace(Nombre_cientifico,' ','') LIKE '%"+like.replace(" ","")+"%';";
                }else{
                    sql = "SELECT Convert(((SUM(producción) * 100) /SUM(Programada)), decimal(10,2)) as Porcentaje FROM produccion where replace(Vivero, ' ','') = '"+vivero+"' and (replace(Nombre_comun,' ','') LIKE '%"+like.replace(" ","")+"%' or replace(Nombre_cientifico,' ','') LIKE '%"+like.replace(" ","")+"%');";
                }
            }
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                dtoporcentaje.add(new PorcentajePP(rs.getString("porcentaje")));          
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return dtoporcentaje;
    }
    
    public List<TotalesPP> ObtenerTotales(String where, String like, String vivero) throws Exception {
        ArrayList<TotalesPP> dtototales = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if(like.isEmpty() && vivero.isEmpty()){
                sql = "SELECT Vivero, Nombre_comun, SUM(Programada) as Programada, SUM(producción) as Producción, SUM(Disponibilidad) as Disponibilidad, SUM(Entregada) as Entregada, SUM(F_Exis) as F_Exis FROM produccion GROUP BY Vivero";
            }else{
                if (like.isEmpty()) {
                    //sql = "SELECT Vivero, Nombre_comun, SUM(Programada) as Programada, SUM(producción) as Producción, SUM(Disponibilidad) as Disponibilidad, SUM(Entregada) as Entregada, SUM(F_Exis) as F_Exis FROM produccion where replace(Vivero, ' ','') = '"+vivero+"' GROUP BY Vivero;";
                    sql = "SELECT Vivero, Nombre_comun, SUM(Programada) as Programada, SUM(producción) as Producción, SUM(Disponibilidad) as Disponibilidad, SUM(Entregada) as Entregada, SUM(F_Exis) as F_Exis FROM produccion GROUP BY Vivero";
                }else if(vivero.isEmpty()){
                    //sql = "SELECT Vivero, Nombre_comun, SUM(Programada) as Programada, SUM(producción) as Producción, SUM(Disponibilidad) as Disponibilidad, SUM(Entregada) as Entregada, SUM(F_Exis) as F_Exis FROM produccion where replace(Nombre_comun,' ','') LIKE '%"+like.replace(" ","")+"%' or replace(Nombre_cientifico,' ','') LIKE '%"+like.replace(" ","")+"%' GROUP BY Vivero;";
                    sql = "SELECT Vivero, Nombre_comun, SUM(Programada) as Programada, SUM(producción) as Producción, SUM(Disponibilidad) as Disponibilidad, SUM(Entregada) as Entregada, SUM(F_Exis) as F_Exis FROM produccion GROUP BY Vivero";
                }else{
                    sql = "SELECT Vivero, Nombre_comun, SUM(Programada) as Programada, SUM(producción) as Producción, SUM(Disponibilidad) as Disponibilidad, SUM(Entregada) as Entregada, SUM(F_Exis) as F_Exis FROM produccion where replace(Vivero, ' ','') = '"+vivero+"' and (replace(Nombre_comun,' ','') LIKE '%"+like.replace(" ","")+"%' or replace(Nombre_cientifico,' ','') LIKE '%"+like.replace(" ","")+"%') GROUP BY Vivero;";
                }
            }
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                dtototales.add(new TotalesPP(rs.getString("Vivero"), rs.getString("Nombre_comun"), rs.getString("Programada"), rs.getString("Producción"), rs.getString("Disponibilidad"), rs.getString("Entregada"), rs.getString("F_Exis")));          
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return dtototales;
    }
    
    public List<TotalesPP> GranTotal(String where, String like, String vivero) throws Exception {
        ArrayList<TotalesPP> gtototal = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if(like.isEmpty() && vivero.isEmpty()){
                sql = "SELECT SUM(Programada) as Programada, SUM(producción) as Producción, SUM(Disponibilidad) as Disponibilidad, SUM(Entregada) as Entregada, SUM(F_Exis) as F_Exis FROM produccion;";
            }else{
                if (like.isEmpty()) {
                    //sql = "SELECT SUM(Programada) as Programada, SUM(producción) as Producción, SUM(Disponibilidad) as Disponibilidad, SUM(Entregada) as Entregada, SUM(F_Exis) as F_Exis FROM produccion where replace(Vivero, ' ','') = '"+vivero+"';";
                    sql = "SELECT SUM(Programada) as Programada, SUM(producción) as Producción, SUM(Disponibilidad) as Disponibilidad, SUM(Entregada) as Entregada, SUM(F_Exis) as F_Exis FROM produccion;";
                }else if(vivero.isEmpty()){
                    //sql = "SELECT SUM(Programada) as Programada, SUM(producción) as Producción, SUM(Disponibilidad) as Disponibilidad, SUM(Entregada) as Entregada, SUM(F_Exis) as F_Exis FROM produccion where replace(Nombre_comun,' ','') LIKE '%"+like.replace(" ","")+"%' or replace(Nombre_cientifico,' ','') LIKE '%"+like.replace(" ","")+"%';";
                    sql = "SELECT SUM(Programada) as Programada, SUM(producción) as Producción, SUM(Disponibilidad) as Disponibilidad, SUM(Entregada) as Entregada, SUM(F_Exis) as F_Exis FROM produccion;";
                }else{
                    //sql = "SELECT SUM(Programada) as Programada, SUM(producción) as Producción, SUM(Disponibilidad) as Disponibilidad, SUM(Entregada) as Entregada, SUM(F_Exis) as F_Exis FROM produccion where replace(Vivero, ' ','') = '"+vivero+"' and (replace(Nombre_comun,' ','') LIKE '%"+like.replace(" ","")+"%' or replace(Nombre_cientifico,' ','') LIKE '%"+like.replace(" ","")+"%');";
                    sql = "SELECT SUM(Programada) as Programada, SUM(producción) as Producción, SUM(Disponibilidad) as Disponibilidad, SUM(Entregada) as Entregada, SUM(F_Exis) as F_Exis FROM produccion where replace(Vivero, ' ','') = '"+vivero+"';";
                }
            }
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                gtototal.add(new TotalesPP("", "", rs.getString("Programada"), rs.getString("Producción"), rs.getString("Disponibilidad"), rs.getString("Entregada"), rs.getString("F_Exis")));          
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return gtototal;
    }
    
    public List<SelectPAA> ObtenDatosSelectPAA() throws Exception {
        ArrayList<SelectPAA> dtoselectpaa = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            sql = "SELECT Vivero FROM historico_produccion GROUP BY Vivero ORDER BY Vivero;";
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                dtoselectpaa.add(new SelectPAA(rs.getString("Vivero")));          
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return dtoselectpaa;
    }
    
    public List<SelectPAA> SelectViveros() throws Exception {
        ArrayList<SelectPAA> dtoselectviveros = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            sql = "SELECT Vivero FROM produccion GROUP BY Vivero ;";
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                dtoselectviveros.add(new SelectPAA(rs.getString("Vivero")));          
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return dtoselectviveros;
    }
    
    public List<EncabezadosDTO> ObtenEncabezados(String where, String where2) throws Exception {
        ArrayList<EncabezadosDTO> encabezado = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if (where.isEmpty() && where2.isEmpty()) {
                sql="SELECT Vivero, Año, SUM(Programada) AS Programada, SUM(Produccion) AS Produccion FROM historico_produccion Group by Vivero, Año Order by Vivero, Año ASC;";
            }
            else{
                if (where.isEmpty()) {
                    sql="SELECT Vivero, Año, SUM(Programada) AS Programada, SUM(Produccion) AS Produccion FROM historico_produccion WHERE replace(Año, ' ','')="+where2+" Group by Vivero, Año Order by Vivero, Año ASC;";
                }else{
                    if (where2.isEmpty()) {
                        sql="SELECT Vivero, Año, SUM(Programada) AS Programada, SUM(Produccion) AS Produccion FROM historico_produccion WHERE replace(Vivero, ' ','') = '"+where+"' Group by Vivero, Año Order by Vivero, Año ASC;";
                    }else{
                        sql="SELECT Vivero, Año, SUM(Programada) AS Programada, SUM(Produccion) AS Produccion FROM historico_produccion WHERE replace(Vivero, ' ','') = '"+where+"' and replace(Año, ' ','')="+where2+" Group by Vivero, Año Order by Vivero, Año ASC;";
                    }
                }
            }
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                encabezado.add(new EncabezadosDTO(rs.getString("Vivero"), rs.getString("Año"), rs.getInt("Programada"), rs.getInt("Produccion")));          
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return encabezado;
    }
    
    public List<Encabezado2DTO> ObtenEncabezado2(String where, String where2) throws Exception {
        ArrayList<Encabezado2DTO> encabezado2 = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if(where.isEmpty() && where2.isEmpty()){
                sql="SELECT Vivero, Año, Tipo_produccion, SUM(Programada) AS Programada, SUM(Produccion) AS Produccion FROM historico_produccion Group by Vivero, año, Tipo_produccion order by Vivero, Tipo_produccion DESC";
            }else{
                if (where.isEmpty()) {
                    sql="SELECT Vivero, Año, Tipo_produccion, SUM(Programada) AS Programada, SUM(Produccion) AS Produccion FROM historico_produccion Where replace(Año, ' ','')="+where2+" Group by Vivero, año, Tipo_produccion order by Vivero, Tipo_produccion DESC";
                }else{
                    if (where2.isEmpty()) {
                        sql="SELECT Vivero, Año, Tipo_produccion, SUM(Programada) AS Programada, SUM(Produccion) AS Produccion FROM historico_produccion Where replace(Vivero, ' ','') = '"+where+"' Group by Vivero, año, Tipo_produccion order by Vivero, Tipo_produccion DESC";
                    }else{
                        sql="SELECT Vivero, Año, Tipo_produccion, SUM(Programada) AS Programada, SUM(Produccion) AS Produccion FROM historico_produccion Where replace(Vivero, ' ','') = '"+where+"' and replace(Año, ' ','')="+where2+" Group by Vivero, año, Tipo_produccion order by Vivero, Tipo_produccion DESC";
                    }
                }
            }
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                encabezado2.add(new Encabezado2DTO(rs.getString("Vivero"), rs.getString("Año"), rs.getString("Tipo_produccion"), rs.getInt("Programada"), rs.getInt("Produccion")));          
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return encabezado2;
    }
    
    public List<DatosSeccionDTO> DatosSeccion(String where, String where2) throws Exception {
        ArrayList<DatosSeccionDTO> dtoSeccion = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if (where.isEmpty() && where2.isEmpty())  {
                sql="SELECT Vivero, Año, Tipo_produccion, Nombre_comun, Nombre_cientifico, SUM(Programada) AS Programada, Recurso, Envase, SUM(Produccion) AS Produccion FROM historico_produccion GROUP BY Vivero, Año, Nombre_comun;";
            }else{
                if (where.isEmpty()) {
                    sql="SELECT Vivero, Año, Tipo_produccion, Nombre_comun, Nombre_cientifico, SUM(Programada) AS Programada, Recurso, Envase, SUM(Produccion) AS Produccion FROM historico_produccion Where replace(Año, ' ','')="+where2+" GROUP BY Vivero, Año, Nombre_comun;";
                }else{
                    if (where2.isEmpty()) {
                       sql="SELECT Vivero, Año, Tipo_produccion, Nombre_comun, Nombre_cientifico, SUM(Programada) AS Programada, Recurso, Envase, SUM(Produccion) AS Produccion FROM historico_produccion Where replace(Vivero, ' ','') = '"+where+"' GROUP BY Vivero, Año, Nombre_comun;"; 
                    }else{
                        sql="SELECT Vivero, Año, Tipo_produccion, Nombre_comun, Nombre_cientifico, SUM(Programada) AS Programada, Recurso, Envase, SUM(Produccion) AS Produccion FROM historico_produccion Where replace(Vivero, ' ','') = '"+where+"' and replace(Año, ' ','')="+where2+" GROUP BY Vivero, Año, Nombre_comun;";
                    }
                }
            }
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                dtoSeccion.add(new DatosSeccionDTO(rs.getString("Vivero"), rs.getString("Año"), rs.getString("Tipo_produccion"), rs.getString("Nombre_comun"), rs.getString("Nombre_cientifico"), rs.getInt("Programada"), rs.getString("Recurso"), rs.getString("Envase"), rs.getInt("Produccion")));          
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return dtoSeccion;
    }
   
    public List<DPCiudadanoDTO> DPCiudadano(String like) throws Exception {
        ArrayList<DPCiudadanoDTO> dtoCiudadano = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if (like.isEmpty()) {
                sql="SELECT Año, Solicitante, Region, Municipio, Vale_planta, Destino, Vivero, Nombre_comun, Nombre_cientifico, Cantidad_planta, Tipo_salida FROM ciudadano ";
            }else{
                sql = "SELECT Año, Solicitante, Region, Municipio, Vale_planta, Destino, Vivero, Nombre_comun, Nombre_cientifico, Cantidad_planta, Tipo_salida FROM ciudadano WHERE replace(Solicitante, ' ','') LIKE '%"+like+"%';";
            }
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                dtoCiudadano.add(new DPCiudadanoDTO(rs.getInt("Año"), rs.getString("Solicitante"), rs.getString("Region"), rs.getString("Municipio"), rs.getString("Vale_planta"), rs.getString("Destino"), rs.getString("Vivero"), rs.getString("Nombre_comun"), rs.getString("Nombre_cientifico"), rs.getInt("Cantidad_planta"), rs.getString("Tipo_salida")));          
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return dtoCiudadano;
    }
    
    public List<DPAnioDTO> DPAnio(String where) throws Exception {
        ArrayList<DPAnioDTO> dtoAnio = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if (where.isEmpty()) {
                sql = "SELECT año,\n" +
                    "sum(PROBOSQUE) as Probosque, sum(superficie_PROBOSQUE) as superficie_PROBOSQUE,\n" +
                    "sum(CEPANAF) as CEPANAF, sum(superficie_CEPANAF) as superficie_CEPANAF,\n" +
                    "sum(CGCE) as CGCE, sum(superficie_CGCE) as superficie_CGCE, \n" +
                    "sum(SECyBS) as SECyBS, sum(superficie_SECyBS) as superficie_SECyBS,\n" +
                    "sum('Otro Estatal') as Otro_Estatal, sum(superficie_Otro_estatal) as superficie_Otro_estatal,\n" +
                    "sum(SEMARNAT) as SEMARNAT, sum(superficie_SEMARNAT) as superficie_SEMARNAT,\n" +
                    "sum(CONAFOR) as CONAFOR, sum(superficie_CONAFOR) as superficie_CONAFOR,\n" +
                    "sum(CONANP) as CONANP, sum(superficie_CONANP) as superficie_CONANP,\n" +
                    "sum(CONAZA) as CONAZA, sum(superficie_CONAZA) as superficie_CONAZA,\n" +
                    "sum(CONAGUA) as CONAGUA, sum(superficie_CONAGUA) as superficie_CONAGUA,\n" +
                    "sum(SEDESOL) as SEDESOL, sum(superficie_SEDESOL) as superficie_SEDESOL,\n" +
                    "sum(BID) as BID, sum(superficie_BID) as superficie_BID,\n" +
                    "sum(Otro_federal) as Otro_federal, sum(superficie_otro_federal) as superficie_otro_federal,\n" +
                    "sum(Cantidad_planta) as Cantidad_planta, sum(Superficie) as Superficie\n" +
                    "FROM destino_planta group by Año order by año desc;";
            }else{
                sql = "SELECT año,\n" +
                    "sum(PROBOSQUE) as Probosque, sum(superficie_PROBOSQUE) as superficie_PROBOSQUE,\n" +
                    "sum(CEPANAF) as CEPANAF, sum(superficie_CEPANAF) as superficie_CEPANAF,\n" +
                    "sum(CGCE) as CGCE, sum(superficie_CGCE) as superficie_CGCE, \n" +
                    "sum(SECyBS) as SECyBS, sum(superficie_SECyBS) as superficie_SECyBS,\n" +
                    "sum('Otro Estatal') as Otro_Estatal, sum(superficie_Otro_estatal) as superficie_Otro_estatal,\n" +
                    "sum(SEMARNAT) as SEMARNAT, sum(superficie_SEMARNAT) as superficie_SEMARNAT,\n" +
                    "sum(CONAFOR) as CONAFOR, sum(superficie_CONAFOR) as superficie_CONAFOR,\n" +
                    "sum(CONANP) as CONANP, sum(superficie_CONANP) as superficie_CONANP,\n" +
                    "sum(CONAZA) as CONAZA, sum(superficie_CONAZA) as superficie_CONAZA,\n" +
                    "sum(CONAGUA) as CONAGUA, sum(superficie_CONAGUA) as superficie_CONAGUA,\n" +
                    "sum(SEDESOL) as SEDESOL, sum(superficie_SEDESOL) as superficie_SEDESOL,\n" +
                    "sum(BID) as BID, sum(superficie_BID) as superficie_BID,\n" +
                    "sum(Otro_federal) as Otro_federal, sum(superficie_otro_federal) as superficie_otro_federal,\n" +
                    "sum(Cantidad_planta) as Cantidad_planta, sum(Superficie) as Superficie\n" +
                    "FROM destino_planta WHERE replace(año, ' ','')="+where+" group by Año order by año desc;";
                
            }
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
//            private int año;
//    private String region;
//    private String municipio;
            while (rs.next()) {
                dtoAnio.add(new DPAnioDTO(rs.getInt("año"), "", "", rs.getInt("Probosque"), rs.getFloat("superficie_PROBOSQUE"), rs.getInt("CEPANAF"), rs.getFloat("superficie_CEPANAF"), rs.getInt("CGCE"), rs.getFloat("superficie_CGCE"), rs.getInt("SECyBS"), rs.getFloat("superficie_SECyBS"), rs.getInt("Otro_Estatal"), rs.getFloat("superficie_Otro_estatal"), rs.getInt("SEMARNAT"), rs.getFloat("superficie_SEMARNAT"), rs.getInt("CONAFOR"), rs.getFloat("superficie_CONAFOR"), rs.getInt("CONANP"), rs.getFloat("superficie_CONANP"), rs.getInt("CONAZA"), rs.getFloat("superficie_CONAZA"), rs.getInt("CONAGUA"), rs.getFloat("superficie_CONAGUA"), rs.getInt("SEDESOL"), rs.getFloat("superficie_SEDESOL"), rs.getInt("BID"), rs.getFloat("superficie_BID"), rs.getInt("Otro_federal"), rs.getFloat("superficie_otro_federal"), rs.getInt("Cantidad_planta"), rs.getFloat("Superficie")));          
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return dtoAnio;
    }
    
    
     public List<DPAnioDTO> DPAnioRegion(String where) throws Exception {
        ArrayList<DPAnioDTO> dtoRegion = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if (where.isEmpty()) {
                sql = "SELECT año, region,\n" +
                    "sum(PROBOSQUE) as Probosque, sum(superficie_PROBOSQUE) as superficie_PROBOSQUE,\n" +
                    "sum(CEPANAF) as CEPANAF, sum(superficie_CEPANAF) as superficie_CEPANAF,\n" +
                    "sum(CGCE) as CGCE, sum(superficie_CGCE) as superficie_CGCE,\n" +
                    "sum(SECyBS) as SECyBS, sum(superficie_SECyBS) as superficie_SECyBS,\n" +
                    "sum('Otro Estatal') as Otro_Estatal, sum(superficie_Otro_estatal) as superficie_Otro_estatal,\n" +
                    "sum(SEMARNAT) as SEMARNAT, sum(superficie_SEMARNAT) as superficie_SEMARNAT,\n" +
                    "sum(CONAFOR) as CONAFOR, sum(superficie_CONAFOR) as superficie_CONAFOR,\n" +
                    "sum(CONANP) as CONANP, sum(superficie_CONANP) as superficie_CONANP,\n" +
                    "sum(CONAZA) as CONAZA, sum(superficie_CONAZA) as superficie_CONAZA,\n" +
                    "sum(CONAGUA) as CONAGUA, sum(superficie_CONAGUA) as superficie_CONAGUA,\n" +
                    "sum(SEDESOL) as SEDESOL, sum(superficie_SEDESOL) as superficie_SEDESOL,\n" +
                    "sum(BID) as BID, sum(superficie_BID) as superficie_BID,\n" +
                    "sum(Otro_federal) as Otro_federal, sum(superficie_otro_federal) as superficie_otro_federal,\n" +
                    "sum(Cantidad_planta) as Cantidad_planta, sum(Superficie) as Superficie\n" +
                    "FROM destino_planta group by año, region order by año, municipio asc;";
            }else{
                sql = "SELECT año, region,\n" +
                    "sum(PROBOSQUE) as Probosque, sum(superficie_PROBOSQUE) as superficie_PROBOSQUE,\n" +
                    "sum(CEPANAF) as CEPANAF, sum(superficie_CEPANAF) as superficie_CEPANAF,\n" +
                    "sum(CGCE) as CGCE, sum(superficie_CGCE) as superficie_CGCE,\n" +
                    "sum(SECyBS) as SECyBS, sum(superficie_SECyBS) as superficie_SECyBS,\n" +
                    "sum('Otro Estatal') as Otro_Estatal, sum(superficie_Otro_estatal) as superficie_Otro_estatal,\n" +
                    "sum(SEMARNAT) as SEMARNAT, sum(superficie_SEMARNAT) as superficie_SEMARNAT,\n" +
                    "sum(CONAFOR) as CONAFOR, sum(superficie_CONAFOR) as superficie_CONAFOR,\n" +
                    "sum(CONANP) as CONANP, sum(superficie_CONANP) as superficie_CONANP,\n" +
                    "sum(CONAZA) as CONAZA, sum(superficie_CONAZA) as superficie_CONAZA,\n" +
                    "sum(CONAGUA) as CONAGUA, sum(superficie_CONAGUA) as superficie_CONAGUA,\n" +
                    "sum(SEDESOL) as SEDESOL, sum(superficie_SEDESOL) as superficie_SEDESOL,\n" +
                    "sum(BID) as BID, sum(superficie_BID) as superficie_BID,\n" +
                    "sum(Otro_federal) as Otro_federal, sum(superficie_otro_federal) as superficie_otro_federal,\n" +
                    "sum(Cantidad_planta) as Cantidad_planta, sum(Superficie) as Superficie\n" +
                    "FROM destino_planta WHERE replace(año, ' ','')="+where+" group by año, region order by año, municipio asc;";
            }
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
//            private int año;
//    private String region;
//    private String municipio;
            while (rs.next()) {
                dtoRegion.add(new DPAnioDTO(rs.getInt("año"), rs.getString("region"), "", rs.getInt("Probosque"), rs.getFloat("superficie_PROBOSQUE"), rs.getInt("CEPANAF"), rs.getFloat("superficie_CEPANAF"), rs.getInt("CGCE"), rs.getFloat("superficie_CGCE"), rs.getInt("SECyBS"), rs.getFloat("superficie_SECyBS"), rs.getInt("Otro_Estatal"), rs.getFloat("superficie_Otro_estatal"), rs.getInt("SEMARNAT"), rs.getFloat("superficie_SEMARNAT"), rs.getInt("CONAFOR"), rs.getFloat("superficie_CONAFOR"), rs.getInt("CONANP"), rs.getFloat("superficie_CONANP"), rs.getInt("CONAZA"), rs.getFloat("superficie_CONAZA"), rs.getInt("CONAGUA"), rs.getFloat("superficie_CONAGUA"), rs.getInt("SEDESOL"), rs.getFloat("superficie_SEDESOL"), rs.getInt("BID"), rs.getFloat("superficie_BID"), rs.getInt("Otro_federal"), rs.getFloat("superficie_otro_federal"), rs.getInt("Cantidad_planta"), rs.getFloat("Superficie")));          
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return dtoRegion;
    }
     
     public List<DPAnioDTO> DPAnioRegionDetalle(String where) throws Exception {
        ArrayList<DPAnioDTO> dtoRegionDetalle = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if (where.isEmpty()) {
            sql = "SELECT año, region, municipio,\n" +
                "sum(PROBOSQUE) as Probosque, sum(superficie_PROBOSQUE) as superficie_PROBOSQUE,\n" +
                "sum(CEPANAF) as CEPANAF, sum(superficie_CEPANAF) as superficie_CEPANAF,\n" +
                "sum(CGCE) as CGCE, sum(superficie_CGCE) as superficie_CGCE,\n" +
                "sum(SECyBS) as SECyBS, sum(superficie_SECyBS) as superficie_SECyBS,\n" +
                "sum('Otro Estatal') as Otro_Estatal, sum(superficie_Otro_estatal) as superficie_Otro_estatal,\n" +
                "sum(SEMARNAT) as SEMARNAT, sum(superficie_SEMARNAT) as superficie_SEMARNAT,\n" +
                "sum(CONAFOR) as CONAFOR, sum(superficie_CONAFOR) as superficie_CONAFOR,\n" +
                "sum(CONANP) as CONANP, sum(superficie_CONANP) as superficie_CONANP,\n" +
                "sum(CONAZA) as CONAZA, sum(superficie_CONAZA) as superficie_CONAZA,\n" +
                "sum(CONAGUA) as CONAGUA, sum(superficie_CONAGUA) as superficie_CONAGUA,\n" +
                "sum(SEDESOL) as SEDESOL, sum(superficie_SEDESOL) as superficie_SEDESOL,\n" +
                "sum(BID) as BID, sum(superficie_BID) as superficie_BID,\n" +
                "sum(Otro_federal) as Otro_federal, sum(superficie_otro_federal) as superficie_otro_federal,\n" +
                "sum(Cantidad_planta) as Cantidad_planta, sum(Superficie) as Superficie\n" +
                "FROM destino_planta group by año, region, municipio;";
            }else{
                sql = "SELECT año, region, municipio,\n" +
                "sum(PROBOSQUE) as Probosque, sum(superficie_PROBOSQUE) as superficie_PROBOSQUE,\n" +
                "sum(CEPANAF) as CEPANAF, sum(superficie_CEPANAF) as superficie_CEPANAF,\n" +
                "sum(CGCE) as CGCE, sum(superficie_CGCE) as superficie_CGCE,\n" +
                "sum(SECyBS) as SECyBS, sum(superficie_SECyBS) as superficie_SECyBS,\n" +
                "sum('Otro Estatal') as Otro_Estatal, sum(superficie_Otro_estatal) as superficie_Otro_estatal,\n" +
                "sum(SEMARNAT) as SEMARNAT, sum(superficie_SEMARNAT) as superficie_SEMARNAT,\n" +
                "sum(CONAFOR) as CONAFOR, sum(superficie_CONAFOR) as superficie_CONAFOR,\n" +
                "sum(CONANP) as CONANP, sum(superficie_CONANP) as superficie_CONANP,\n" +
                "sum(CONAZA) as CONAZA, sum(superficie_CONAZA) as superficie_CONAZA,\n" +
                "sum(CONAGUA) as CONAGUA, sum(superficie_CONAGUA) as superficie_CONAGUA,\n" +
                "sum(SEDESOL) as SEDESOL, sum(superficie_SEDESOL) as superficie_SEDESOL,\n" +
                "sum(BID) as BID, sum(superficie_BID) as superficie_BID,\n" +
                "sum(Otro_federal) as Otro_federal, sum(superficie_otro_federal) as superficie_otro_federal,\n" +
                "sum(Cantidad_planta) as Cantidad_planta, sum(Superficie) as Superficie\n" +
                "FROM destino_planta WHERE replace(año, ' ','')="+where+" group by año, region, municipio;";
            }
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
//            private int año;
//    private String region;
//    private String municipio;
            while (rs.next()) {
                dtoRegionDetalle.add(new DPAnioDTO(rs.getInt("año"), rs.getString("region"), rs.getString("municipio"), rs.getInt("Probosque"), rs.getFloat("superficie_PROBOSQUE"), rs.getInt("CEPANAF"), rs.getFloat("superficie_CEPANAF"), rs.getInt("CGCE"), rs.getFloat("superficie_CGCE"), rs.getInt("SECyBS"), rs.getFloat("superficie_SECyBS"), rs.getInt("Otro_Estatal"), rs.getFloat("superficie_Otro_estatal"), rs.getInt("SEMARNAT"), rs.getFloat("superficie_SEMARNAT"), rs.getInt("CONAFOR"), rs.getFloat("superficie_CONAFOR"), rs.getInt("CONANP"), rs.getFloat("superficie_CONANP"), rs.getInt("CONAZA"), rs.getFloat("superficie_CONAZA"), rs.getInt("CONAGUA"), rs.getFloat("superficie_CONAGUA"), rs.getInt("SEDESOL"), rs.getFloat("superficie_SEDESOL"), rs.getInt("BID"), rs.getFloat("superficie_BID"), rs.getInt("Otro_federal"), rs.getFloat("superficie_otro_federal"), rs.getInt("Cantidad_planta"), rs.getFloat("Superficie")));          
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return dtoRegionDetalle;
    }
}