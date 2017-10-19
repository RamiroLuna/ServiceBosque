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
import org.probosque.dto.SemillaDTO;
import org.probosque.dto.SemillaDestinoDTO;
import org.probosque.dto.TotalDestinoDTO;
import org.probosque.dto.TotalSemillaDTO;

/**
 *
 * @author Jonathan
 */
public class TableProduccionSemilla {
    ConexionMySQL conexion = new ConexionMySQL("semilla");
    public List<SemillaDTO> ObtenerDatosBGP(String like) throws Exception {
        ArrayList<SemillaDTO> dtobgp = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if (like.isEmpty()) {
                sql="select Nombre_comun, Nombre_Cientifico, region, municipio,  procedencia, DATE_FORMAT(fecha_alta,'%d-%m-%Y') as fecha_alta, F_ClaLot, viabilidad, cantidad from semilla;";
            }else{
                sql="select Nombre_comun, Nombre_Cientifico, region, municipio,  procedencia, DATE_FORMAT(fecha_alta,'%d-%m-%Y') as fecha_alta, F_ClaLot, viabilidad, cantidad from semilla WHERE REPLACE(Nombre_comun, ' ', '') LIKE '%"+like+"%' OR REPLACE(Nombre_cientifico, ' ', '') LIKE '%"+like+"%';";
            }
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                dtobgp.add(new SemillaDTO(rs.getString("Nombre_comun"), rs.getString("Nombre_Cientifico"), rs.getString("region"), 
                        rs.getString("municipio"), rs.getString("procedencia"), rs.getString("fecha_alta"), rs.getString("F_ClaLot"), rs.getFloat("viabilidad"), rs.getFloat("cantidad")));          
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return dtobgp;
    }
    
    public List<TotalSemillaDTO> ObtenerTotalBGP(String like) throws Exception {
        ArrayList<TotalSemillaDTO> dtotbgp = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if(like.isEmpty()){
                sql="select SUM(cantidad) as cantidad from semilla;";
            }else{
                sql="select SUM(cantidad) as cantidad from semilla WHERE REPLACE(Nombre_comun, ' ', '') LIKE '%"+like+"%' OR REPLACE(Nombre_cientifico, ' ', '') LIKE '%"+like+"%';";
            }
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                dtotbgp.add(new TotalSemillaDTO(rs.getFloat("cantidad")));          
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return dtotbgp;
    }
    
    public List<SemillaDestinoDTO> ObtenerDatosDGP(String like, String anio) throws Exception {
        ArrayList<SemillaDestinoDTO> dtotdgp = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if (like.isEmpty() && anio.isEmpty()) {
            sql="select año, Nombre_cientifico, Nombre_comun, procedencia, fecha_alta, lote_semilla, viabilidad, cantidad, costo FROM `destino semilla`;";
            }else{
                if (like.isEmpty()) {
                    sql="select año, Nombre_cientifico, Nombre_comun, procedencia, fecha_alta, lote_semilla, viabilidad, cantidad, costo FROM `destino semilla` where REPLACE(año, ' ', '') = "+anio+";";
                }else{
                    if (anio.isEmpty()) {
                        sql="select año, Nombre_cientifico, Nombre_comun, procedencia, fecha_alta, lote_semilla, viabilidad, cantidad, costo FROM `destino semilla` where REPLACE(Nombre_cientifico, ' ', '') LIKE '%"+like+"%' or REPLACE(Nombre_comun, ' ', '') LIKE '%"+like+"%';";
                    }else{
                        sql="select año, Nombre_cientifico, Nombre_comun, procedencia, fecha_alta, lote_semilla, viabilidad, cantidad, costo FROM `destino semilla` where REPLACE(año, ' ', '') = "+anio+" and (REPLACE(Nombre_cientifico, ' ', '') LIKE '%"+like+"%' or REPLACE(Nombre_comun, ' ', '') LIKE '%"+like+"%');";
                    }
                }
            }
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                dtotdgp.add(new SemillaDestinoDTO(rs.getInt("año"), rs.getString("Nombre_cientifico"), rs.getString("Nombre_comun"), rs.getString("procedencia"), rs.getString("fecha_alta"), rs.getString("lote_semilla"), rs.getFloat("viabilidad"), rs.getFloat("cantidad"), rs.getFloat("costo")));
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return dtotdgp;
    }
    
    public List<TotalDestinoDTO> ObtenerTotalDGP(String like, String anio) throws Exception {
        ArrayList<TotalDestinoDTO> dtototaldgp = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if (like.isEmpty() && anio.isEmpty()) {
                sql="select año, Sum(cantidad) as cantidad, Sum(costo) as costo FROM `destino semilla` group by año;";
            }else{
                if (like.isEmpty()) {
                    sql="select año, Sum(cantidad) as cantidad, Sum(costo) as costo FROM `destino semilla` where REPLACE(año, ' ', '') = "+anio+" group by año;";
                }else{
                    if (anio.isEmpty()) {
                        sql="select año, Sum(cantidad) as cantidad, Sum(costo) as costo FROM `destino semilla` where REPLACE(Nombre_cientifico, ' ', '') LIKE '%"+like+"%' or REPLACE(Nombre_comun, ' ', '') LIKE '%"+like+"%' group by año;";
                    }else{
                        sql="select año, Sum(cantidad) as cantidad, Sum(costo) as costo FROM `destino semilla` where REPLACE(año, ' ', '') = "+anio+" and (REPLACE(Nombre_cientifico, ' ', '') LIKE '%"+like+"%' or REPLACE(Nombre_comun, ' ', '') LIKE '%"+like+"%') group by año;";
                    }
                }
            }
            pst = conexion.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                dtototaldgp.add(new TotalDestinoDTO(rs.getInt("año"), rs.getFloat("cantidad"), rs.getFloat("costo")));
            }
        } catch (Exception e) {
        }finally{
            try{
                if (conexion.getConnection() != null)conexion.getConnection().close();
                if (pst != null)pst.close();
                if (rs != null) rs.close();
            }catch(Exception e){}
        }
        return dtototaldgp;
    }
}
