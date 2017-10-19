package org.probosque.controller;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.LayersDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.LayerDTO;
import org.probosque.dto.TotalDTO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.TotalJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de totales para una determinada capa geográfica
 * @author admin
 */
public class ControllerTotal {

    public TotalJson getTotals(HttpServletRequest request) throws SQLException, Exception {

        String _user = request.getParameter("user");
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));

        String _layer = request.getParameter("layer");

        LayersDAO layersDao = new LayersDAO();

        List<LayerDTO> layers = layersDao.getLayers(user);

        int[] count = {0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < 8; i++) {
            count[i] += layersDao.getCount(user, _layer, i + 1);
        }

        TotalDTO total = new TotalDTO();
        total.setZ1(count[0]);
        total.setZ2(count[1]);
        total.setZ3(count[2]);
        total.setZ4(count[3]);
        total.setZ5(count[4]);
        total.setZ6(count[5]);
        total.setZ7(count[6]);
        total.setZ8(count[7]);

        TotalJson json = new TotalJson();
        json.setTotals(total);

        return json;
    }
}
