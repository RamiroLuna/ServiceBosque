/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.HidrologicaDAO;
import org.probosque.model.json.HidrologicaJson;
/**
 *
 * @author TOSHIBA-L55
 */
public class ControllerHidrologica {
    public HidrologicaJson getHidrologica(HttpServletRequest request) throws SQLException, Exception {
        String regionHidrologica = request.getParameter("region_hidrologica");   
        HidrologicaDAO dao = new HidrologicaDAO();
        HidrologicaJson HidrologicaJson = new HidrologicaJson();
        HidrologicaJson.setList(dao.getHidrologica(Integer.parseInt(regionHidrologica)));
        return HidrologicaJson;
    }
}
