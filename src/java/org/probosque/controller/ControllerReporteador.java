package org.probosque.controller;

import com.google.gson.Gson;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.probosque.dao.PoolDataSource;
import org.probosque.dao.ReporteadorDAO;
import org.probosque.dao.SQL;
import org.probosque.dao.UserDAO;
import org.probosque.dto.FiltroDTO;
import org.probosque.dto.GroupDTO;
import org.probosque.dto.TotalGroupDTO;
import org.probosque.dto.OrdenamientoDTO;
import org.probosque.dto.SelectDTO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.ResponseJson;


public class ControllerReporteador {
 public int Nreport;

   
    public OutputJson procesoMaestro(HttpServletRequest request) throws Exception{
        Gson gson = new Gson();
        String _jsonSelect,_jsonAgrupador,_jsonWhere,_jsonOrder;
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ReporteadorDAO dao =new ReporteadorDAO();
        List<SelectDTO> columns;
        int Nreport;
         _jsonSelect = request.getParameter("params"); 
         _jsonAgrupador = request.getParameter("paramsGro"); 
         
         _jsonWhere = request.getParameter("paramsFil"); 
         _jsonOrder = request.getParameter("paramsSort"); 
         String noReporte = request.getParameter("noReporte");
         String _user = request.getParameter("user");
         String activity= request.getParameter("activity");
         
        try{
         GroupDTO group = gson.fromJson(_jsonAgrupador.trim(), GroupDTO.class);
         FiltroDTO where =  gson.fromJson(_jsonWhere.trim(), FiltroDTO.class); 
         OrdenamientoDTO order =  gson.fromJson(_jsonOrder.trim(), OrdenamientoDTO.class);
        columns=this.getSelect( noReporte, _user,activity,_jsonSelect );
        if(group != null && group.getGroup()!=null && (group.getGroup().size()) > 0)
           {
           this.getGroup(String.valueOf(this.getNreport()), _user, activity,_jsonAgrupador, columns);
           }
        if(where != null && where.getSelect().size()>0)
           {
           this.where( _user,activity, String.valueOf(this.getNreport()), _jsonWhere, columns);
           }
               
        if(order != null && order.getOrdenar().size()>0)
           {
           this.getOrder(String.valueOf(this.getNreport()), _user, activity,_jsonOrder);
           }
        
        if(group != null && group.getGroup()!=null && (group.getGroup().size()) > 0)
           {
           dao.totalReporte(_user, group,this.getNreport(),columns);
           UserDAO userDa = new UserDAO();
           UserDTO usex = userDa.getUser(Integer.parseInt(_user));
           dao.updateColumn(usex,"configagrupado", _jsonAgrupador, this.getNreport());
           }
                 
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        output.setCadena(String.valueOf(this.getNreport()));
        output.setData(dao.getReporte(user,  this.getNreport(), _jsonSelect));
        response.setSucessfull(true);
        }
        catch(Exception ex)
                      
        {
        response.setSucessfull(false);
        response.setMessage(ex.getMessage());
        }
        
        
        return output;
    }
    
 public OutputJson getCampos(HttpServletRequest request) throws Exception
    {            
        Gson gson = new Gson();
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        ReporteadorDAO dao =new ReporteadorDAO();
        try{
            String _user = request.getParameter("user");
            String activity= request.getParameter("activity");
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
            output.setData(dao.getCamposParaReporte(user,activity));
             response.setSucessfull(true);
        }catch(Exception ex){
       response.setSucessfull(false);
       response.setMessage(ex.getMessage());
      }
        output.setResponse(response); 
        
        return output;
    }

     public List<SelectDTO> getSelect(String noReporte, String _user,String activity,String _json) throws Exception  {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        UserDAO userDao = new UserDAO();
        ReporteadorDAO dao =new ReporteadorDAO();
        int Nreport=Integer.parseInt(noReporte); 
        String  label, datatype, type, name,multiselect="";
        String agrupado="";
        Gson gson = new Gson();
        SelectDTO select = gson.fromJson(_json.trim(), SelectDTO.class);
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        
        
         
        List<SelectDTO> columns = select.getSelect();
        
          if(Nreport!=0)
            {
             dao.limpiarColumna(Nreport, user,"campos","borrar");
             dao.limpiarColumna(Nreport, user,"configcampos"," ");
             dao.limpiarColumna(Nreport, user,"uniones"," ");
             dao.limpiarColumna(Nreport, user,"multireg"," ");
             dao.limpiarColumna(Nreport, user,"filtros"," ");
             dao.limpiarColumna(Nreport, user,"agrupadore"," ");
             dao.limpiarColumna(Nreport, user, "ordenadores"," ");
             dao.limpiarColumna(Nreport, user, "ordenadores"," ");
             dao.limpiarColumna(Nreport, user, "totalselect"," ");
             dao.limpiarColumna(Nreport, user, "totalgroup"," ");
             dao.borrarUnionesMultireg(user, Nreport);
            }
          
      try{ 
                       
          for(SelectDTO column: columns )
                {
                  if(!column.getDatatype().equals("records")) 
                  {  
                    if(dao.getNumeroReporte()!=null &&  Integer.parseInt(dao.getNumeroReporte())!=0)
                       {
                        Nreport=Integer.parseInt(dao.getNumeroReporte());
                       } 
                        label=column.getLabel();
                        datatype=column.getDatatype();
                        if(!column.getType().equals("Principal"))
                             label=this.removeCadena(column.getType(),"_"," ");
                        type=(column.getType().equals("Principal"))?"P":"M";
                        name=column.getName();
                        if(type=="P" && name.equals("anio"))
                        datatype="numeric";
                        //if(column.getName().equals("modulopredio_cup"))
                          //{
                           // dao.insertPredios(user);  
                            //dao.insertRepresentantes(user);
                           //}
                        if(type.equals("M") && !column.getList().isEmpty() && !column.getList().equals("NA"))
                            {
                                multiselect=dao.getMultiselect(user, name, column.getList(),type,column.getType());
                                if(!multiselect.equals("multiselect"))
                                {   
                                    
                                    column.setList(dao.campoListMultireg(user, Nreport, noReporte, column.getList(), name,column.getType()));
                                }
                                else
                                  {
                                   column.setDatatype(multiselect);   
                                  dao.setMultiregistro(user, column.getName(), column.getType(), Nreport);
                                  
                                  }
                            if(dao.getNumeroReporte()!=null &&  Integer.parseInt(dao.getNumeroReporte())!=0)
                              {
                                Nreport=Integer.parseInt(dao.getNumeroReporte());
                              } 
                        
                            }
                        else
                         {
                        if(type.equals("M")){  
                          dao.relacionMultiregistro(user, Nreport, label);
                          if(dao.getNumeroReporte()!=null &&  Integer.parseInt(dao.getNumeroReporte())!=0)
                          {
                          Nreport=Integer.parseInt(dao.getNumeroReporte());
                          } 
                        
                           }
                          if(!column.getList().equals("") && !column.getList().equals(" ") && !column.getList().equals("NA") && !column.getName().equals("modulopredio_cup") )
                              {
                                multiselect=dao.getMultiselect(user, name, column.getList(),type,column.getType());
                              }
                          if(!multiselect.equals("multiselect"))
                          {   
                           
                           Nreport=(dao.Select(user, activity, label, type, String.valueOf(Nreport), name));
                          }
                          else
                            {
                            column.setDatatype(multiselect);       
                            dao.setMultiregistro(user, column.getName(), column.getType(), Nreport);
                            }
                         }
                   }      
                 }
        dao.reemplazaTexto(user,"campos","borrar,", " ", Nreport);
        response.setSucessfull(true);
        response.setMessage("Reporte");
        output.setCadena(String.valueOf(Nreport));
        response.setSucessfull(true); 
      }
       catch(Exception ex)
            {
              response.setSucessfull(false);
              response.setMessage(ex.getMessage());
            }
      
      this.setNreport(Nreport);
      return columns;
       }
     
     public void getGroup(String  nReporte,String _userA,String activityA,String _jsonAgrupador,List<SelectDTO> columns) throws Exception
       {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        UserDAO userDao = new UserDAO();
        ReporteadorDAO dao =new ReporteadorDAO();
        Gson gson = new Gson();
        
        String noReporte = "";
          noReporte=nReporte;
        int Nreport=Integer.parseInt(noReporte); 
        String activity = activityA;
        String _user = _userA;
        StringBuilder agrupador = new StringBuilder();
        String type=" ";
        StringBuilder sql= new StringBuilder();  
        UserDTO user = userDao.getUser(Integer.parseInt(_user)); 
        DataSource ds = PoolDataSource.getDataSource(user);
        Connection con =ds.getConnection();
        Statement sta = con.createStatement();   
        
        try{
            if(Nreport!=0)
            {
             dao.limpiarColumna(Nreport, user,"agrupadore","");
            }
           dao.group(user, _jsonAgrupador, Nreport,columns);
         
        
        response.setMessage("Reporte");
        output.setCadena(String.valueOf(Nreport));
        response.setSucessfull(true);
        }catch(Exception ex)
            {
              response.setSucessfull(false);
              response.setMessage(ex.getMessage());
            }
        
        sta.close();
        con.close();
        //output.setResponse(response);        
        
       }
     
       public void where(String _Auxuser, String auxActivity, String auxNoReporte, String _jsonWhere, List<SelectDTO> campos) throws Exception
     {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        UserDAO userDao = new UserDAO();
        ReporteadorDAO dao =new ReporteadorDAO();
        Gson gson = new Gson();
        try
           {

                String noReporte=auxNoReporte;
                int Nreport=Integer.parseInt(noReporte); 
                String activity = auxActivity;

                String _user=_Auxuser;
                String _json = _jsonWhere;

                UserDTO user = userDao.getUser(Integer.parseInt(_user));
                
                int sinFiltro = dao.buscarPatron(_json, "[]",15);
                if( sinFiltro<0)
                {
                FiltroDTO where =  gson.fromJson(_json.trim(), FiltroDTO.class);
                ArrayList<FiltroDTO> filtro=where.getSelect();
               for (FiltroDTO  f : filtro ){
                     dao.InsertWhere(user, Nreport, f.getName(), f.getCondicion(), f.getTipoFiltro(),f.getList(),f.getType(),f.getDatatype(),campos);
                     }
                }
            }
           catch(Exception ex)
            {
              response.setSucessfull(false);
              response.setMessage(ex.getMessage());
            }
        
        
        
   
     } 
     
public OutputJson getOrder(String auxNreport,String  _auxUser,String auxActivity, String _jsonOrder) throws Exception
  {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        UserDAO userDao = new UserDAO();
        ReporteadorDAO dao =new ReporteadorDAO();
        Gson gson = new Gson();
        String noReporte = auxNreport;
        int Nreport=Integer.parseInt(noReporte); 
        String activity = auxActivity;
        String _user = _auxUser;
        String _json = _jsonOrder;
        
        try
           {
      
        OrdenamientoDTO order =  gson.fromJson(_json.trim(), OrdenamientoDTO.class);
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        
        for (OrdenamientoDTO ord: order.getOrdenar())
              {
               dao.insertOrder(user,ord.getName(),ord.getOrdenamiento(),Nreport);
              }
           
             response.setSucessfull(true);
           }
        catch(Exception ex)
            {
              response.setSucessfull(false);
              response.setMessage(ex.getMessage());
            }
        output.setResponse(response); 
        return output;        
  }
       
public OutputJson saveReport(HttpServletRequest request) throws Exception
  {
        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        UserDAO userDao = new UserDAO();
        ReporteadorDAO dao =new ReporteadorDAO();
        Gson gson = new Gson();
        String noReporte = request.getParameter("noReporte");
        int Nreport=Integer.parseInt(noReporte); 
        String activity = request.getParameter("activity");
        String _user = request.getParameter("user");
        String _json = request.getParameter("nameReport");
        String _json2 = request.getParameter("campos");
        String tipo=request.getParameter("tipo");
        UserDTO user = userDao.getUser(Integer.parseInt(_user));   
        try{
        dao.updateColumn(user, "titulo", _json, Nreport);
        dao.updateColumn(user, "configcampos", _json2, Nreport);
        dao.updateColumnInteger(user, "userid", user.getId(), Nreport);
        if(tipo.equals("ReporteGeneral"))
          {
          dao.updateColumn(user, "tiporeporte", "1", Nreport);
          }
        else
          {
          dao.updateColumn(user, "tiporeporte","2", Nreport);
          }
         response.setSucessfull(true);
        }catch(Exception ex)
                 {
                  response.setSucessfull(false);
                  response.setMessage(ex.getMessage());
                 }
        
        return output;  
  }     
        
       
       
 public String removeCadena(String Cadena, String patron,String reemplazo)
   {
    return  Cadena.replaceAll(patron,reemplazo);
    
   }
 
 public OutputJson ListaReportes(HttpServletRequest request) throws SQLException, IOException, Exception{
     OutputJson output = new OutputJson();
     ResponseJson response = new ResponseJson();
      UserDAO userDao = new UserDAO();
     ReporteadorDAO dao =new ReporteadorDAO();
     
     String _user = request.getParameter("user");
     UserDTO user = userDao.getUser(Integer.parseInt(_user));   
     
     output.setData(dao.GetTitulosReportes(user));
     output.setResponse(response); 
     return output; 
 }
     
     
 public OutputJson GetRecuperaReporte(HttpServletRequest request) throws SQLException, IOException, Exception{
     OutputJson output = new OutputJson();
     ResponseJson response = new ResponseJson();
     UserDAO userDao = new UserDAO();
     ReporteadorDAO dao =new ReporteadorDAO();
     
     String _user = request.getParameter("user");
     UserDTO user = userDao.getUser(Integer.parseInt(_user)); 
     int id = Integer.parseInt(request.getParameter("noReporte"));
     try{
     String ConfigCampos = dao.GetConfigCampos(user, id);
     output.setData(dao.getReporte(user, id, ConfigCampos));
     response.setSucessfull(true);
     }catch(Exception ex)
                 {
                  response.setSucessfull(false);
                  response.setMessage(ex.getMessage());
                 }
     output.setResponse(response); 
     return output;        
 }
 
 public OutputJson getTitulosReportes(HttpServletRequest request) throws Exception
    {
     OutputJson output = new OutputJson();
     ResponseJson response = new ResponseJson();
     UserDAO userDao = new UserDAO();
     ReporteadorDAO dao =new ReporteadorDAO();
     try{
     String _user = request.getParameter("user");
     UserDTO user = userDao.getUser(Integer.parseInt(_user)); 
     int id = Integer.parseInt(request.getParameter("noReporte"));
     output.setData(dao.GetTitulosReporte(user));
     response.setSucessfull(true);
     }
     catch(Exception ex)
                 {
                  response.setSucessfull(false);
                  response.setMessage(ex.getMessage());
                 }
     output.setResponse(response); 
     return output;        
    
    }
 public OutputJson GetBorrarReporte(HttpServletRequest request) throws SQLException, IOException, Exception{
     OutputJson output = new OutputJson();
     ResponseJson response = new ResponseJson();
     UserDAO userDao = new UserDAO();
     ReporteadorDAO dao =new ReporteadorDAO();
     
     String _user = request.getParameter("user");
     
     UserDTO user = userDao.getUser(Integer.parseInt(_user)); 
     int id = Integer.parseInt(request.getParameter("noReporte"));
     
     try{
     dao.borrarReporte(user, id, user.getActivity());
     response.setSucessfull(true);
     }catch(Exception ex)
                 {
                  response.setSucessfull(false);
                  response.setMessage(ex.getMessage());
                 }
     output.setResponse(response); 
     return output;        
 }
 public int getNreport() {
        return Nreport;
    }

    public void setNreport(int Nreport) {
        this.Nreport = Nreport;
    } 
 
}
