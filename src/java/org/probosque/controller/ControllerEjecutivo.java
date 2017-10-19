package org.probosque.controller;

import com.google.gson.Gson;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.EjecutivoDAO;
import org.probosque.dao.ReporteadorDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.FiltroDTO;
import org.probosque.dto.ProgramDTO;
import org.probosque.dto.SelectDTO;
import org.probosque.dto.UserDTO;
import org.probosque.ejecutivodto.EjecutivoPrograma1;
import org.probosque.ejecutivodto.ReporteEjecutivo;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;

public class ControllerEjecutivo {

    
    
    
    public OutputJson getRegiones( HttpServletRequest request)
    {
       Gson gson = new Gson();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        EjecutivoDAO dao =new EjecutivoDAO();
        try{
            output.setData(dao.getRegiones());    
            response.setSucessfull(true);
        }catch(Exception ex){
       response.setSucessfull(false);
       response.setMessage(ex.getMessage());
      }
        output.setResponse(response); 
        return output;
    }

    public OutputJson getMunicipio( HttpServletRequest request)
    {
       Gson gson = new Gson();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        EjecutivoDAO dao =new EjecutivoDAO();
        try{
            String r = request.getParameter("region");
            int region= Integer.parseInt(r);
            output.setData(dao.getMunicipio(region));    
            response.setSucessfull(true);
        }catch(Exception ex){
       response.setSucessfull(false);
       response.setMessage(ex.getMessage());
      }
        output.setResponse(response); 
        return output;
    }
public OutputJson getLocalidad( HttpServletRequest request)
    {
       Gson gson = new Gson();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        EjecutivoDAO dao =new EjecutivoDAO();
        try{
            String l = request.getParameter("localidad");
            int localidad= Integer.parseInt(l);
            output.setData(dao.getLocalidad(localidad));    
            response.setSucessfull(true);
        }catch(Exception ex){
       response.setSucessfull(false);
       response.setMessage(ex.getMessage());
      }
        output.setResponse(response); 
        return output;
    }

public OutputJson getPredio( HttpServletRequest request)
    {
       Gson gson = new Gson();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        EjecutivoDAO dao =new EjecutivoDAO();
        try{
            String c = request.getParameter("clave");
            String text = request.getParameter("text");
            output.setData(dao.getPredio(c,text));    
            response.setSucessfull(true);
        }catch(Exception ex){
       response.setSucessfull(false);
       response.setMessage(ex.getMessage());
      }
        output.setResponse(response); 
        return output;
    }

public OutputJson getRepresentantes( HttpServletRequest request)
    {
       Gson gson = new Gson();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        EjecutivoDAO dao =new EjecutivoDAO();
        try{
            String c = request.getParameter("clave");
            String text = request.getParameter("text");
            output.setData(dao.getRepresentantes(c,text));    
            response.setSucessfull(true);
        }catch(Exception ex){
       response.setSucessfull(false);
       response.setMessage(ex.getMessage());
      }
        output.setResponse(response); 
        return output;
    }

public OutputJson getDetallesPredio( HttpServletRequest request)
{
        Gson gson = new Gson();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        EjecutivoDAO dao =new EjecutivoDAO();
        try{
            String c = request.getParameter("clave");
            output.setData(dao.getDatosPredio(c));    
            response.setSucessfull(true);
        }catch(Exception ex){
       response.setSucessfull(false);
       response.setMessage(ex.getMessage());
      }
        output.setResponse(response); 
        return output;
}



public OutputJson getReporte( HttpServletRequest request)
    {
       Gson gson = new Gson();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        EjecutivoDAO dao =new EjecutivoDAO();
        UserDTO user = new UserDTO();
        
        int j =0;
        
         String  [] programa = new String[20];
        try{
            String clavePredio = request.getParameter("clave");
            String anio = request.getParameter("anio");
            ReporteEjecutivo dto = new ReporteEjecutivo();
           
            String  programas = request.getParameter("programas");
            programas=programas.replace("\"","");
            
            
            char [] strArray = programas.toCharArray();
             StringBuilder  opcion1= new StringBuilder();
            for(int i=0;i<strArray.length;i++)
            {
                if(strArray[i]!=',' && strArray[i]!='[' && strArray[i]!=']')
                     {
                       opcion1.append(strArray[i]); 
                     }      
                if(strArray[i]==',' || strArray[i]==']')
                {
                   
                   j++;
                  
            //dto.setDatos(dao.getDatosPredio( clavePredio));
            switch(opcion1.toString()){
            case  "1":    
            user.setProgram(1);
            user.setActivity(0);
            dto.setP1(dao.getReportePrograma1(user, clavePredio, anio));
            break;
            case "2":
            user.setProgram(2);
            user.setActivity(0);        
            dto.setP2(dao.getReportePrograma2(user, clavePredio, anio));
            break;
            case "3":
            user.setProgram(3);
            user.setActivity(0);        
            dto.setP3(dao.getReportePrograma3(user, clavePredio, anio));
            break;
            case "5":
            user.setProgram(5);
            user.setActivity(0);        
            dto.setP5(dao.getReportePrograma5(user, clavePredio, anio));
            break;
            case "6":
            user.setProgram(6);
            user.setActivity(0);        
            dto.setP6(dao.getReportePrograma6(user, clavePredio, anio));
            break;
            case "7":
            user.setProgram(7);
            user.setActivity(3);        
            dto.setP7(dao.getReportePrograma71(user, clavePredio, anio));
            break;
            case "8":
            user.setProgram(8);
            user.setActivity(0);        
            dto.setP8(dao.getReportePrograma8(user, clavePredio, anio));
            break;
            case "10":
            user.setProgram(10);
            user.setActivity(0);        
            dto.setP10(dao.getReportePrograma10(user, clavePredio, anio));
            break;
            case "11":
            user.setProgram(11);
            user.setActivity(0);        
            dto.setP11(dao.getReportePrograma11(user, clavePredio, anio));
            break;
            
            case "12":
            user.setProgram(12);
            user.setActivity(0);        
            dto.setP12(dao.getReportePrograma12(user, clavePredio, anio));
            break;
            
            }
         opcion1=new StringBuilder();        
         }
         
        } 
            output.setData(dto);
            /*String programas = request.getParameter("programas");
            ProgramDTO program = gson.fromJson(programas.trim(), ProgramDTO.class);
            String campos = request.getParameter("campos");
            SelectDTO camp=gson.fromJson(campos.trim(), SelectDTO.class);
            String activity= request.getParameter("activity");
            String filtro= request.getParameter("filtros");
            FiltroDTO filtros=gson.fromJson(filtro.trim(), FiltroDTO.class);
           // ArrayList<ProgramDTO> listPrograms=dao.getProgramas();
            UserDAO uDao = new UserDAO();
              UserDTO user= new UserDTO(); 
            for(ProgramDTO p : program.getProgram()){
            user.setActivity(p.getActivity());
            user.setProgram(p.getIdProgram());
            output.setData(dao.getReportePersona(user,camp,filtros));
            }*/
             response.setSucessfull(true);
        }catch(Exception ex){
    
    response.setSucessfull(false);
           response.setMessage(ex.getMessage());
      }
        output.setResponse(response); 
        return output;
    }
}
