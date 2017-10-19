/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.SubcuencasDao;
import org.probosque.model.json.SubcuencaJson;
/**
 *
 * @author TOSHIBA-L55
 */
public class ControllerSubcuenca {
    public SubcuencaJson getSubcuenca(HttpServletRequest request) throws SQLException, Exception {
        int Cuenca = Integer.parseInt(request.getParameter("cuenca"));   
        SubcuencasDao dao = new SubcuencasDao();
        SubcuencaJson SubcuencasJson = new SubcuencaJson();
        SubcuencasJson.setList(dao.getSubcuenca(Cuenca));
        return SubcuencasJson;
    }
}
