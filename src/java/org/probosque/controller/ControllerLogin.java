package org.probosque.controller;

import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.LogDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.UserLoginDTO;
import org.probosque.model.json.DataLoginJson;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de acceso al sistema
 * @author admin
 */

public class ControllerLogin {
    public OutputJson getUserInfo(HttpServletRequest request) throws Exception {
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        OutputJson output = new OutputJson();
        DataLoginJson data = new DataLoginJson();
        ResponseJson response = new ResponseJson();
        
        UserDAO dao = new UserDAO();
        UserLoginDTO userDto = dao.getUserLogin(user, password);
        if (userDto != null) {
            data.setUserInfo(userDto);
            response.setSucessfull(true);
            response.setMessage("OK");
            output.setData(data);
            /*E Zamora*/
                LogDAO log=new LogDAO();
                String lusr=userDto.getId()+"-"+userDto.getFirstname()+" "+userDto.getLastname()+"-"+userDto.getProgram();
                log.Prolog(lusr,4," ");
            /*E. zamora*/

        }
        else{
            response.setSucessfull(false);
            response.setMessage("Usuario o contraseña incorrectos");
        }
        output.setResponse(response);
        return output;
    }
}