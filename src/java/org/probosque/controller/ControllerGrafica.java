
package org.probosque.controller;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.probosque.dao.PoolDataSource;
import org.probosque.dao.UserDAO;
import org.probosque.dto.ResultString;
import org.probosque.dto.UserDTO;
/**
 *
 * @author Telyco
 */
public class ControllerGrafica {
 
    public OutputJson getControllerGrafica(HttpServletRequest request) throws SQLException, IOException, Exception {
 
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();    
    
        
        //String _region = request.getParameter("region");
        String _usuario = request.getParameter("usuario");
        String _tema = request.getParameter("tema");
        String _datan = request.getParameter("datan");
        
        int roluser= Integer.parseInt(_usuario);
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(roluser);      
    
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        
        //String CadenaGrafica="SELECT general.count_regc(8, 'capasgeograficas."+ _tema + "::regclass, NULL) AS result"; //tipo result
        String CadenaGrafica="SELECT general.count_regtemas(8,'"+_tema+"', NULL,"+_datan+") AS result"; //tipo result
       


        ResultSetHandler rsh = new BeanHandler(ResultString.class);       
        ResultString result = (ResultString) qr.query(CadenaGrafica, rsh);  //ejecuta el query 
       
        
        String cadenagr= result.getResult();//guarda el resultado de la ejecucion
    
        String CadenaGrafica2= cadenagr.replaceAll("[{]", "[");
        String CadenaGrafica3= CadenaGrafica2.replaceAll("}", "]");

        output.setData(CadenaGrafica3);
        response.setSucessfull(true);
        response.setMessage("Grafica obtenida correctamente");
        output.setResponse(response);
        return output;
        
    }
    

}
