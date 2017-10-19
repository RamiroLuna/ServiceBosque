/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.probosque.dto.UserDTO;
import org.probosque.dto.VentanillaDTO;
import org.probosque.dto.VentanillaRegistroDTO;

/**
 *
 * @author TOSHIBA-L55
 */
public class VentanillaRegistroDAO {

    public List<VentanillaRegistroDTO> cargaPeticion(UserDTO user, String _folio) {
        List<VentanillaRegistroDTO> exito=new ArrayList<VentanillaRegistroDTO>();
        try {
            DataSource ds = PoolDataSource.getDataSource(user);
            Connection con=ds.getConnection();
            ResultSet rs=null;
            PreparedStatement st=null;
            String sql="";
            sql=("select * from formularios.principal where formularios.principal.folio='"+_folio+"'");    
            st=con.prepareStatement(sql);
            rs=st.executeQuery();
            while(rs.next()){
                exito.add(new VentanillaRegistroDTO(rs.getString("modulopredio_estado"),rs.getString("region"),rs.getString("modulopredio_municipio"),rs.getString("anio"),
                rs.getString("modulopredio_cup"),rs.getString("folio"),rs.getString("regimen"),rs.getString("propietario"),
                rs.getString("prestador"),rs.getString("dictaminador_tecnico"),rs.getString("dictaminador_ambiental"),rs.getString("tipo_programa"),
                rs.getString("nivel_programa"),rs.getString("superficie"),rs.getString("volumen"),rs.getString("fecha_pago"),
                rs.getString("monto_pago"),rs.getString("fecha_recepcion"),rs.getString("fecha_registro"),rs.getString("requerimiento_mia"),
                rs.getString("solicitud_autorizacion"),rs.getString("proceso")));
            }
            con.close();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(""+ex);
        }
        return exito;
    }
    
}
