/*
    Esta clase es de uso exclusivo de reporteador utiliza procedimientos almacenados
    
    Tiene metodos para extraccion de informacion  

*/

package org.probosque.dao;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.probosque.dto.CatalogoDTO;
import org.probosque.dto.ColumnReportDTO;
import org.probosque.dto.ReporteDTO;
import org.probosque.dto.ResultInteger;
import org.probosque.dto.SelectDTO;
import org.probosque.dto.UserDTO;
import org.probosque.dao.SQL;
import org.probosque.dto.GroupDTO;
import org.probosque.dto.PrediosDTO;
import org.probosque.dto.ResultBoolean;
import org.probosque.dto.ResultString;
import org.probosque.dto.SubtotalReporte;
import org.probosque.dto.TitulosReporteDTO;
import org.probosque.dto.TotalGroupDTO;

public class ReporteadorDAO {

 public List<String> indices= new ArrayList<>();  
 
 public int SubtotalesColocados;  
 public String numeroReporte;    
    
    
    /*
        Metodo para extaer todos los campos de formularios principales y multiregistros 
        Fecha de Implementacion 21/07/2016
    */
    public List<ColumnReportDTO> getCamposParaReporte(UserDTO user, String activity) throws Exception
  {      
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        String imagenes= "formularios.imagen";
        sql.append(" SELECT type, label,datatype, name,listname");
        if(user.getProgram()!=7)
        {    
         sql.append(" FROM ").append(SQL.getTableInfo(user,activity));
        }
        else
            if(user.getActivity()==3)    
            {
             sql.append(" FROM ").append(SQL.getTableInfo(user,activity));
            }
            else
              {
               sql.append(" FROM ").append(SQL.getTableInfo(user,activity));    
               } 
        if (user.getRole_id() == 3)
            {
            sql.append(" WHERE privacy != true");
            sql.append(" AND name != 'modulopredio_estado'");
         
            }
        sql.append(" WHERE name != 'modulopredio_estado' AND name!='").append(imagenes).append("'");
        sql.append(" AND name!='id_municipio' AND name!='municipio'");
        sql.append(" ORDER BY position ");
        ResultSetHandler rsh = new BeanListHandler(ColumnReportDTO.class);
        List<ColumnReportDTO> columns = (List<ColumnReportDTO>) qr.query(sql.toString(), rsh);
        List<ColumnReportDTO> auxColumns= new ArrayList<>();       
        int i=0;
        String limpia;
        for( ColumnReportDTO column: columns)
        {
           auxColumns.add(column); 
           int aux=0;
           if(!column.getType().equals("form"))
                column.setType("Principal");
             if(column.getType().equals("form"))
                {  
                   column.setType("form");  
                    sql = new StringBuilder();                   
                    aux=i+1;
                    sql.append("SELECT type, label, datatype, name, listname");
                    sql.append(" FROM ").append(column.getName().concat("_info"));
                     sql.append(" WHERE ");
                             if (user.getRole_id() == 3) 
                                                         {
                                                           sql.append(" privacy != true and ");                                                         
                                                         }
                             sql.append("  label!='Consecutivo' and label!= 'Folio'");
                    sql.append(" ORDER BY position");         
                    List<ColumnReportDTO> camposMultiregistro = (List<ColumnReportDTO>) qr.query(sql.toString(), rsh);
                   ///Sublista que falta por recorrer del la lista principal 
                   for(ColumnReportDTO nuevaLinea: camposMultiregistro)
                    {
                    nuevaLinea.setType(this.removeCadena(column.getLabel()," ","_"));
                  
                    auxColumns.add(nuevaLinea);
                    aux++;
                    }
                 }
                //termina prueba obtener columnas de multiregistros dinamico         
                i++;
        }
       i=0;
       columns=new ArrayList();
       for(ColumnReportDTO copiarLista: auxColumns)
            {
             if(copiarLista.getListname()!=null && copiarLista.getListname().equals("catalogos.vacio") && user.getProgram()==1)
                {
                 copiarLista.setListname("catalogos.areacorta");
                 }                      
                
              if(copiarLista.getDatatype().equals("list"))
                  {
                    
                    if(!copiarLista.getListname().equals("catalogos.anio"))
                    {
                      copiarLista.setDatatype("string");
                    }
                    else
                        copiarLista.setDatatype("numeric");
                  }
              
                  
                 if(copiarLista.getListname()!=null && !copiarLista.getListname().isEmpty())
                   {
                     if(copiarLista.getListname().equals("null;catalogos.cup"))
                    {
                      copiarLista.setListname("catalogos.predios");
                    }  
                     if(user.getProgram()==1)
                     {
                        if(copiarLista.getListname().equals("catalogos.vacio"))
                          {
                           copiarLista.setListname("catalogos.areacorta");
                          }
                     }
                    limpia=this.removeCadena(copiarLista.getListname(),"null;"," ");
                    copiarLista.setListname(this.removeCadena(limpia,"null"," "));
                   }
                 else
                 {
                      copiarLista.setListname("NA");
                 }
                 
                 
                 
                 columns.add(i,copiarLista);
             i++;
            }
       
       return columns;
  }    
// Fin metodo para traer campos iincluidos multiregistros Mike Omar 

/*Este metodo tiene por objetivo el Armado de la primera parte del query  SELECT  
    hace uso de un PL llamado campos 
    Recibe 4 parametros 
    
    */    
     
    public int Select (UserDTO user, String activity, String Label, String Type, String nReport, String campo_M_P) throws SQLException
     {
       DataSource ds = PoolDataSource.getDataSource(user);
       QueryRunner qr = new QueryRunner(ds);
       Connection con =ds.getConnection();
       Statement sta = con.createStatement();
       StringBuilder sql = new StringBuilder();
       String union=" ";
       String auxLocalidad=" ";
       int nreport = 0;
       ResultSet   rs = null;
      StringBuilder unionLocalidad =new StringBuilder();
       StringBuilder descripcionCampos = new  StringBuilder(); 
       nreport=Integer.parseInt(nReport);
      if(!campo_M_P.equals("modulopredio_localidad") && !campo_M_P.equals("modulopredio_cup"))  {       
       sql.append("SELECT ").append(SQL.getTableMainCampos(user, activity)).append("('")
              .append(Label).append("', '")
              .append(Type).append("', '")
              .append(user.getId()).append("', '")
              .append(nReport).append("', '")
              .append(campo_M_P).append("') AS Nreport");
       
       sta.executeUpdate(unionLocalidad.toString());  
        rs = sta.executeQuery(sql.toString());
       while(rs.next()){

           nreport= Integer.parseInt(rs.getString("Nreport"));
       }
       
      }
      else {
          
         if(nreport==0)
      {
      sql.append("Select max(identificador)+1 as result from ").append(SQL.getTableMainReportes(user,String.valueOf(user.getActivity())));
      ResultSetHandler max = new BeanHandler(ResultInteger.class);
      ResultInteger result = (ResultInteger) qr.query(sql.toString(), max);
      nreport=result.getResult();
      this.insertColumn(user, "identificador", nreport);
      this.setNumeroReporte(String.valueOf(nreport));
      this.limpiarColumna(nreport, user,"campos","borrar");    
      this.limpiarColumna(nreport, user,"tabla_origen",SQL.getTableMain(user,activity));    
      sql= new StringBuilder();
      if(campo_M_P.equals("modulopredio_localidad"))
      {  
       sql.append("catalogos.localidad.descripcion ");
       this.updateColumn(user, "campos", sql.toString(),nreport);
        unionLocalidad.append(" inner join catalogos.localidad on  cast(catalogos.localidad.id as integer)=cast(").append(SQL.getTableMain(user,activity)).append(".modulopredio_localidad as integer) ");
        unionLocalidad.append(" and cast(").append(SQL.getTableMain(user,activity)).append(".modulopredio_municipio as integer) = cast(catalogos.localidad.id_municipio as integer)");
        this.updateColumn(user, "uniones", unionLocalidad.toString(),nreport);
        sql= new StringBuilder();
      }
      
      if(campo_M_P.equals("modulopredio_cup"))
      {  
      sql.append(" catalogos.predios.predio ");
      this.updateColumn(user, "campos", sql.toString(),nreport);
        unionLocalidad.append(" inner join catalogos.predios on  catalogos.predios.folio =").append(SQL.getTableMain(user,activity)).append(".modulopredio_cup ");
        
        this.updateColumn(user, "uniones", unionLocalidad.toString(),nreport);
        sql= new StringBuilder();
      
      }
      
      }
      else
         {
      if(campo_M_P.equals("modulopredio_localidad"))
      {  
      sql.append(", catalogos.localidad.descripcion ");
      this.concatColumn(user, "campos",sql.toString() , nreport);
      
      unionLocalidad.append(" inner join catalogos.localidad on  cast(catalogos.localidad.id as integer)=cast(").append(SQL.getTableMain(user,activity)).append(".modulopredio_localidad as integer) ");
      unionLocalidad.append(" and cast(").append(SQL.getTableMain(user,activity)).append(".modulopredio_municipio as integer) = cast(catalogos.localidad.id_municipio as integer)");
      this.concatColumn(user, "uniones",unionLocalidad.toString() , nreport);
      sql= new StringBuilder();
      }
      }
         
     // sql.append(SQL.getTableMain(user,activity));
      //this.updateColumn(user, "tabla_origen", sql.toString(), nreport);
      //sql= new StringBuilder();
      
      if(campo_M_P.equals("modulopredio_cup"))
      {  
      sql.append(", catalogos.predios.predio ");
      this.concatColumn(user, "campos", sql.toString(),nreport);
      unionLocalidad.append(" inner join catalogos.predios on  catalogos.predios.folio =").append(SQL.getTableMain(user,activity)).append(".modulopredio_cup ");
        
        this.concatColumn(user, "uniones", unionLocalidad.toString(),nreport);
        sql= new StringBuilder();
      
      }
      
      
      }
       this.setNumeroReporte(String.valueOf(nreport));
       descripcionCampos.append(" AS  ").append(this.removeCadena(campo_M_P, " ", "_"));
       sql=new StringBuilder();
       this.concatColumn(user,"campos" , descripcionCampos.toString(), nreport);
       con.close();
       if(rs!=null)
       rs.close();
      
      return nreport;
     }
    
    public void group(UserDTO user, String _json, int Nreport,List<SelectDTO> select) throws SQLException
        {
        String type=" ";
        Gson gson = new Gson();
        StringBuilder sql= new StringBuilder(); 
        StringBuilder agrupador = new StringBuilder();
        GroupDTO group = gson.fromJson(_json.trim(), GroupDTO.class);
        DataSource ds = PoolDataSource.getDataSource(user);
        Connection con =ds.getConnection();
        Statement sta = con.createStatement(); 
        
        
         for(GroupDTO detailGroup: group.getGroup())
               {
               type=(detailGroup.getTipo().equals("Principal"))?"P":"M";
               this.insertGroup(user,Nreport, detailGroup.getLabel() , detailGroup.getName(), type, detailGroup.getOperador(),detailGroup.getTipo(),select);
               }
            int existe=0;
                        
            for(SelectDTO campo: select)
               {
                 sql=new StringBuilder();
                  for(GroupDTO detailGroup: group.getGroup())
                    {
                        if(detailGroup.getName().equals(campo.getName()) && detailGroup.getTipo().equals(campo.getType()))
                            {
                             existe=1;  
                            }
                    }
                  if(existe==0)
                    {
                     if(!agrupador.toString().isEmpty())
                      {
                       agrupador.append(", ");
                      }
                       if(campo.getDatatype().equals("multiselect"))
                           campo.setList("");
                       
                         if(campo.getList()!=  null && !campo.getList().isEmpty() && !campo.getList().equals("NA"))
                        {
                         agrupador.append(campo.getList()).append(".descripcion");
                        }
                        else 
                        {
                          if(campo.getType().equals("Principal"))
                          {
                               agrupador.append(SQL.getTableMain(user,String.valueOf(user.getActivity()))).append(".").append(campo.getName());
                          }
                          else{
                              
                             String multireg="";   
                             sql.append("SELECT name AS multireg from ").append(SQL.getTableInfo(user,String.valueOf(user.getActivity())));
                             sql.append(" WHERE label='").append(this.removeCadena(campo.getType(), "_", " ")).append("' AND datatype='records'");
                             ResultSet rs=sta.executeQuery(sql.toString());

                             while(rs.next())
                                  {
                                   multireg= rs.getString("multireg");
                                  }
                                
                              agrupador.append(multireg).append(".").append(campo.getName());
                              
                              }
                          
                          
                        } 
                             
                    
                    }
                                   
                  existe=0;
               }
            sql= new StringBuilder(); 
                             sql.append("UPDATE ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" SET agrupadore='").append(agrupador).append("'");
                             sql.append(" WHERE identificador=").append(Nreport);
                           sta.executeUpdate(sql.toString());

        sta.close();
        con.close();
        }
    
    public void insertGroup(UserDTO user,int Nreport,String Label, String name, String tipo, String operador, String multiregistro, List<SelectDTO> auxColumns) throws SQLException 
    {
       DataSource ds = PoolDataSource.getDataSource(user);
       Connection con =ds.getConnection();
       Statement sta = con.createStatement();
       SQL sq= new SQL();
       StringBuilder sql = new StringBuilder();
       String listname="";
       String multireg="";
       String multiregColName="";
       String existe="";
       if(tipo.equals("P"))
         {
          sql.append("SELECT listname AS listname from ").append(SQL.getTableInfo(user,String.valueOf(user.getActivity())));
          sql.append(" WHERE name='").append(name).append("'");
          ResultSet rs=sta.executeQuery(sql.toString());
          
          while(rs.next())
               {
                listname= rs.getString("listname");
               }
          if(listname == null ||listname.equals(""))
          {
          sql=new StringBuilder();
          sql.append("SELECT campos as existe FROM ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" WHERE campos like '%");
          sql.append(operador).append("(").append(SQL.getTableMain(user,String.valueOf(user.getActivity()))).append(".").append(name).append(")%'");
          sql.append(" and identificador=").append(Nreport);
           rs=sta.executeQuery(sql.toString());
          
          while(rs.next())
               {
                existe= rs.getString("existe");
               }
          if(existe==null || existe.equals(""))
              if(!operador.equals("avg"))
              {   
              this.reemplazaTexto(user, "campos", SQL.getTableMain(user,String.valueOf(user.getActivity()))+"."+name, operador+"("+SQL.getTableMain(user,String.valueOf(user.getActivity()))+"."+name+")", Nreport);
              }
             else
              this.reemplazaTexto(user, "campos", SQL.getTableMain(user,String.valueOf(user.getActivity()))+"."+name, " Round("+operador+"("+SQL.getTableMain(user,String.valueOf(user.getActivity()))+"."+name+"),2) ", Nreport);    
              //this.reemplazaTexto(user, "campos", SQL.getTableMain(user,String.valueOf(user.getActivity()))+"."+name, operador+"("+SQL.getTableMain(user,String.valueOf(user.getActivity()))+"."+name+")", Nreport);
          }
          else
          {
          listname=this.removeCadena(listname, "null;"," ");
          sql=new StringBuilder();
          sql.append("SELECT campos as existe FROM ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" WHERE campos like '%");
          sql.append(operador).append("(").append(listname).append(".descripcion)%'");
          sql.append(" and identificador=").append(Nreport);
          rs=sta.executeQuery(sql.toString());
          
          while(rs.next())
               {
                existe= rs.getString("existe");
               }
          if(existe==null || existe.equals(""))
          this.reemplazaTexto(user, "campos", listname+".descripcion", operador+"("+listname+".descripcion)", Nreport);
          }  
          
         }
       else
         {
           
             for(SelectDTO col: auxColumns)
               {
                if(col.getName().equals(name) && col.getType().equals("M"))
                 {
                   listname=col.getList();
                 }
               }
             
          sql.append("SELECT name AS multireg from ").append(SQL.getTableInfo(user,String.valueOf(user.getActivity())));
          sql.append(" WHERE label='").append(this.removeCadena(multiregistro, "_", " ")).append("' AND datatype='records'");
          
          ResultSet rs=sta.executeQuery(sql.toString());
          
          while(rs.next())
               {
                multireg= rs.getString("multireg");
               }
          sql= new StringBuilder(); 
          sql.append("SELECT name, listname from ").append(multireg).append("_info");
          sql.append(" WHERE name='").append(name).append("'");
          rs=sta.executeQuery(sql.toString());
        
          while(rs.next())
               {
                multiregColName= rs.getString("name");
                listname= rs.getString("listname");
                
               }
          if(listname==null || listname.equals(""))
          {
          sql=new StringBuilder();
          sql.append("SELECT campos as existe FROM ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" WHERE campos like '%");
          sql.append(operador).append("(").append(multireg).append(".").append(name).append(")%'");
          sql.append(" and identificador=").append(Nreport);
          rs=sta.executeQuery(sql.toString());
          
          while(rs.next())
               {
                existe= rs.getString("existe");
               }   
          if(existe==null || existe.equals(""))    
               if(!operador.equals("avg"))
               {
              this.reemplazaTexto(user, "campos", multireg+"."+name, operador+"("+multireg+"."+name+")", Nreport);
               }
              else 
                   this.reemplazaTexto(user, "campos", multireg+"."+name, " Round("+operador+"("+multireg+"."+name+"),2) ", Nreport);
          }
          else
          {
                           
              
          sql=new StringBuilder();
          sql.append("SELECT campos as existe FROM ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" WHERE campos like '%");
          sql.append(operador).append("(").append(listname).append(".descripcion)%'");
          sql.append(" and identificador=").append(Nreport);
          rs=sta.executeQuery(sql.toString());
          
          while(rs.next())
               {
                existe= rs.getString("existe");
               }   
          if(existe==null || existe.equals(""))      
              
          this.reemplazaTexto(user, "campos", listname+".descripcion", operador+"("+listname+".descripcion)", Nreport);
          }  
            
         } 
       sta.close();
       con.close();
       }
    
    
public void InsertWhere(UserDTO user,int Nreport,String nombreColumna, String Condicion, String filtro, String catalogo, String  type,String datatype,List<SelectDTO>campos ) throws SQLException
 {
       DataSource ds = PoolDataSource.getDataSource(user);
       Connection con =ds.getConnection();
       Statement sta = con.createStatement();
       StringBuilder sql = new StringBuilder();
       StringBuilder origen = new StringBuilder();
       StringBuilder cadenaFiltro = new StringBuilder();
       String auxType=" ";
       String auxCat=" ";
       String multiselect=" ";
       sql.append("SELECT filtros  as filtro FROM  ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" WHERE identificador=").append(Nreport);
       ResultSet rs = sta.executeQuery(sql.toString());
       while(rs.next())
       {
        String consulta=rs.getString("filtro");   
       if( consulta!= null && !consulta.equals(" ") && !consulta.equals(""))
            {
             
                String auxCadenaFiltro=this.removeCadena(consulta,"'%" ,"''%");
                cadenaFiltro.append(this.removeCadena(auxCadenaFiltro,"%'" ,"%''"));
               
                cadenaFiltro.append(" AND ");
            }
       }
    
    if(type.equals("Principal"))
       {auxType="P";}  
      if(!catalogo.equals("NA") &&  !catalogo.isEmpty() &&  !catalogo.equals(" "))
       multiselect=this.getMultiselect(user, nombreColumna, catalogo, auxType, type);
      else 
          multiselect="edit";
     if(multiselect.equals("multiselect"))
      {
      if(type.equals("Principal"))    
      type=SQL.getTableMain(user, String.valueOf(user.getActivity()));
      auxCat=catalogo;    
      catalogo="";
      datatype="multiselect";
      }
     if(type.equals("Principal"))
     {
         
      if(catalogo.equals("NA") || catalogo.equals("") )
         {//No tiene Catalogo 
          origen.append(" ").append(SQL.getTableMain(user, String.valueOf(user.getActivity()))).append(".").append(nombreColumna);
         }
      else 
        {//Tiene Catalogo
         origen.append(" ").append(catalogo).append(".").append("descripcion");    
        }
      
     }
     else
        {
      if(catalogo.equals("NA") || catalogo.equals(""))
         {//No tiene Catalogo 
             String multiregistro=this.removeCadena(type,"_"," ");
             
             sql = new StringBuilder();
             sql.append("SELECT name  as multiregistro FROM ").append(SQL.getTableInfo(user,String.valueOf(user.getActivity()))).append(" WHERE label='").append(multiregistro).append("'");
             rs = sta.executeQuery(sql.toString());
                while(rs.next())
                {
                if(!rs.getString("multiregistro").equals(""))
                     {
                      auxType=(rs.getString("multiregistro"));
                     }
                }
                rs.close();
                
             origen.append(" ").append(auxType).append(".").append(nombreColumna);
         }
      else 
        {//Tiene Catalogo
             for(SelectDTO cat: campos )
             {
              if(cat.getLabel().equals(cat.getLabel()) && auxType.equals(this.removeCadena(cat.getType(), "formularios_", "formularios.")))
              {
                origen.append(" ").append(catalogo).append(".descripcion");    
              }
                  
             }
            
            
            
        }
        } 
       cadenaFiltro.append(" (");
     if (datatype.equals("string"))
         {
         cadenaFiltro.append(this.armadoLike(Condicion, origen.toString(),filtro));
             
         }
     if (datatype.equals("real") || datatype.equals("numeric") )
         {
         cadenaFiltro.append(this.armadoCondicionNumerrico(Condicion, origen.toString(),filtro,datatype));    
         
         }
     if (datatype.equals("date") || datatype.equals("datetime") )
         {
         cadenaFiltro.append(this.armadoCondicionFecha(Condicion, origen.toString(),filtro,datatype));    
         
         }
     if (datatype.equals("multiselect")) 
         {
          if(type.equals("Principal"))
          {   
           cadenaFiltro.append(this.armadoIn(user,Condicion,filtro,auxCat,nombreColumna));
          }
          else
            {                                                                                    //tabla multiregistro   
             cadenaFiltro.append(this.armadoInMultireg(user,Condicion,filtro,auxCat,nombreColumna,auxType)); 
            } 
         }
     
     
        cadenaFiltro.append(") ");
     //Segunda parte de la cadena de condicion 
       sql= new StringBuilder();
       sql.append("UPDATE ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" SET filtros='").append(cadenaFiltro).append("'");
       sql.append(" WHERE identificador=").append(Nreport);
       sta.executeUpdate(sql.toString());        
       con.close();
       rs.close();
       
 }
public void insertOrder(UserDTO user, String campo, String ordenamiento, int Nreport) throws SQLException
   {
       DataSource ds = PoolDataSource.getDataSource(user);
       Connection con =ds.getConnection();
       Statement sta = con.createStatement();
       StringBuilder sql = new StringBuilder();
       StringBuilder ordenador = new StringBuilder();
       
       sql.append("SELECT ordenadores  as ordenador FROM  ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" WHERE identificador=").append(Nreport);
       ResultSet rs = sta.executeQuery(sql.toString());
       while(rs.next())
       {
          if(rs.getString("ordenador")!=null)
           ordenador.append(rs.getString("ordenador"));   
       }
        rs.close();
        if(!ordenador.toString().equals(" ") && !ordenador.toString().equals(""))
            ordenador.append(", ");
      if(ordenamiento.equals("ascendente"))
         {
          ordenamiento="ASC";
         }
      else
          ordenamiento="DESC";
       
       sql=new StringBuilder();
       sql.append("UPDATE ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" SET ordenadores='").append(ordenador).append(" ").append(campo).append(" ").append(ordenamiento).append("'");
       sta.executeUpdate(sql.toString());        
       con.close();
       rs.close();
       
   }

public String armadoLike(String Cadena, String tabla, String Filtro)
  {
    String like=Cadena;
    String likes;
    
     char[] aux=Cadena.toCharArray();
    StringBuilder armadoLike = new StringBuilder();
    armadoLike.append(tabla);
    
    if (Filtro.equals("diferente"))
        {
        likes=" NOT LIKE ''%";
        }
    else {
         likes=" LIKE ''%";
         }
    
    armadoLike.append(" ").append(likes);
    
    for(int i =0;i< aux.length; i++)
    {
        if( aux[i] != ',')
         {
           armadoLike.append(aux[i]);
         } 
        else{
            armadoLike.append("%'' ").append(" or ").append(tabla).append(likes);
            
            }
    }
    armadoLike.append("%''  ");
    like=armadoLike.toString();
    
   return like;
  }

public String armadoCondicionNumerrico(String Cadena, String tabla, String Filtro,String tipoDato)
   {
    char[] aux=Cadena.toCharArray();
    StringBuilder armado = new StringBuilder();
    String condicion=" ";
     if(tipoDato.equals("numeric"))
     {
      armado.append("  CAST(").append(tabla).append(" AS integer )" ).append(condicion);  
     }
     else
   {
    armado.append("  CAST(").append(tabla).append(" AS float8 )" ).append(condicion);  
   }
    if(Filtro.equals("mayor"))
      {
       condicion=">";
      }
        if(Filtro.equals("menor"))
      {
       condicion="<";
      }

       if(Filtro.equals("igual"))
      {
       condicion="=";
      }
   if(Filtro.equals("diferente"))
      {
      condicion="!=";
      }
   if(Filtro.equals("rango"))
      {
      condicion="BETWEEN ";
      }
   armado.append(condicion);
   
    for(int i =0;i< aux.length; i++)
    {
        if( aux[i] != ',')
         {
             
           armado.append(aux[i]);
         } 
        else{
             armado.append(" ").append(" AND "); 
            }
    }
    armado.append(" ");
   return  armado.toString();
   }
public String armadoCondicionFecha(String Cadena, String tabla, String Filtro,String tipoDatp)
   {
    char[] aux=Cadena.toCharArray();
    StringBuilder armado = new StringBuilder();
    String condicion=" ";
      armado.append("  CAST(").append(tabla).append(" AS date )" ).append(condicion);  
    
    if(Filtro.equals("mayor"))
      {
       condicion="> '";
      }
        if(Filtro.equals("menor"))
      {
       condicion="< '";
      }

       if(Filtro.equals("igual"))
      {
       condicion="= '";
      }
   if(Filtro.equals("diferente"))
      {
      condicion="!= '";
      }
   if(Filtro.equals("rango"))
      {
      condicion="BETWEEN '";
      }
   armado.append(condicion);
   
    for(int i =0;i< aux.length; i++)
    {
        if( aux[i] != ',')
         {
             
           armado.append(aux[i]);
         } 
        else{
             armado.append("' ").append(" AND '"); 
            }
    }
    armado.append("' ");
   return  armado.toString();
   }

public String  armadoIn(UserDTO user, String Condicion,String filtro,String auxCat,String nombreColumna) throws SQLException
{
 DataSource ds = PoolDataSource.getDataSource(user);
 QueryRunner qr = new QueryRunner(ds);
 StringBuilder sql = new StringBuilder();
 StringBuilder auxSql = new StringBuilder();
 StringBuilder filtroIN= new StringBuilder();
 filtroIN.append(" ");
 filtroIN.append(SQL.getTableMain(user, String.valueOf(user.getActivity()))).append(".").append(nombreColumna);
 filtroIN.append(" ");
 String f=" ";
 if (!filtro.equals("igual"))
     f="NOT";
 sql.append("SELECT id as result FROM ");
 sql.append(auxCat);
 sql.append(" WHERE ").append(this.armadoLike( Condicion,  auxCat+".descripcion", filtro));
 ResultSetHandler rsh = new BeanListHandler(ResultInteger.class);
 auxSql.append(this.removeCadena(sql.toString(), "''", "'"));
 
 List<ResultInteger> idS=(List<ResultInteger>)qr.query(auxSql.toString(), rsh);
 
 int tamaño=0;
   for( ResultInteger id:idS)
     {   
         filtroIN.append(f);
         filtroIN.append("like ''");
         filtroIN.append(id.getResult());
         filtroIN.append("'' OR ").append(SQL.getTableMain(user,String.valueOf(user.getActivity()))).append(".").append(nombreColumna).append(" ");
         filtroIN.append(f);
         filtroIN.append("like ''%,");
         filtroIN.append(id.getResult()).append(",%''");
         filtroIN.append(" OR ").append(SQL.getTableMain(user,String.valueOf(user.getActivity()))).append(".").append(nombreColumna).append(" ");
         filtroIN.append(f);
         filtroIN.append("like ''%,");
         filtroIN.append(id.getResult());
         filtroIN.append("'' ");      
         
         tamaño++;
         if(tamaño<idS.size())
         filtroIN.append(" OR ").append(SQL.getTableMain(user,String.valueOf(user.getActivity()))).append(".").append(nombreColumna).append(" ");
         
     }
   
   
return filtroIN.toString();    
    
}

public String  armadoInMultireg(UserDTO user, String Condicion,String filtro,String auxCat,String nombreColumna,String tabla) throws SQLException
{
 DataSource ds = PoolDataSource.getDataSource(user);
 QueryRunner qr = new QueryRunner(ds);
 StringBuilder sql = new StringBuilder();
 StringBuilder auxSql = new StringBuilder();
 StringBuilder filtroIN= new StringBuilder();
 filtroIN.append(" ");
 filtroIN.append(tabla).append(".").append(nombreColumna);
 filtroIN.append(" ");
 String f=" ";
 if (!filtro.equals("igual"))
     f="NOT";
 sql.append("SELECT id as result FROM ");
 sql.append(auxCat);
 sql.append(" WHERE ").append(this.armadoLike( Condicion,  auxCat+".descripcion", filtro));
 ResultSetHandler rsh = new BeanListHandler(ResultInteger.class);
 auxSql.append(this.removeCadena(sql.toString(), "''", "'"));
 
 List<ResultInteger> idS=(List<ResultInteger>)qr.query(auxSql.toString(), rsh);
 
 int tamaño=0;
   for( ResultInteger id:idS)
     {   
         filtroIN.append(f);
         filtroIN.append("like ''");
         filtroIN.append(id.getResult());
         filtroIN.append("'' OR ").append(tabla).append(".").append(nombreColumna).append(" ");
         filtroIN.append(f);
         filtroIN.append("like ''%,");
         filtroIN.append(id.getResult()).append(",%''");
         filtroIN.append(" OR ").append(tabla).append(".").append(nombreColumna).append(" ");
         filtroIN.append(f);
         filtroIN.append("like ''%,");
         filtroIN.append(id.getResult());
         filtroIN.append("'' ");      
         
         tamaño++;
         if(tamaño<idS.size())
         filtroIN.append(" OR ").append(tabla).append(".").append(nombreColumna).append(" ");
         
     }
   
   
return filtroIN.toString();    
    
}

public void limpiarColumna(int numeroReporte, UserDTO user,String parametro,String cambio) throws SQLException
{
       DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        if(cambio.equals(" ")){
        sql.append(" UPDATE ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" SET ").append(parametro).append("=' '");
        }
        else
          sql.append(" UPDATE ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" SET ").append(parametro).append("='").append(cambio).append("'");  
        sql.append(" WHERE identificador = ?");
        Object[] params = {
            numeroReporte
        };
        qr.update(sql.toString(), params);
      

}


public ArrayList<ReporteDTO> getReporte(UserDTO user, int Nreport, String _json) throws SQLException
      {
          int idReporte;
          List<SubtotalReporte> sub= new ArrayList<>();
          StringBuilder sql = new StringBuilder();
          StringBuilder total = new StringBuilder();
          String selectCampos=" ",fromTable=" ",joins=" ",multiregistros=" ",agrupador=" ",ordenador=" ", filtros=" ",vistas=" ",dropVistas=" ",status=" ",descripcion=" ",
                  
                titulo=" ",tipo=" ",aux,agrupado=" ", auxTotal=" ",type=" ";
            Gson gson = new Gson();
            
        SelectDTO select = gson.fromJson(_json.trim(), SelectDTO.class);
        for(SelectDTO column: select.getSelect())
          {
              if(column.getType().equals("Principal"))
                  type="P";
              else 
                  type=column.getType();
              if(!column.getList().equals("NA") && !column.getList().equals("") && !column.getList().equals(" ") && !column.getList().isEmpty() && !column.getName().equals("modulopredio_cup"))   
              column.setDatatype(this.getMultiselect(user, column.getName(), column.getList(), type,column.getType() ));
              
              
          }
        
         ArrayList<String> lefts = new ArrayList<>();
        ArrayList<ArrayList<String>> registros = new ArrayList<>(); 
       DataSource ds = PoolDataSource.getDataSource(user);
       Connection con =ds.getConnection();
       Statement sta = con.createStatement();

       QueryRunner qr = new QueryRunner(ds);
       
       ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class);
       
       this.reemplazaTexto(user, "uniones", "inner join", "left join", Nreport);

       sql.append("SELECT  position as id, name as descripcion FROM ");
       sql.append(SQL.getTableInfo(user, String.valueOf(user.getActivity()))); 
       sql.append(" WHERE name!='formularios.imagen' and name!='formularios.imagenincendios' and type='form'");
       List<CatalogoDTO> columnasMultireg = (List<CatalogoDTO>) qr.query(sql.toString(),rsh); 
       sql= new StringBuilder(); 
       
       sql.append(" SELECT identificador AS idReporte,"
                 +" campos AS selectCampos,"
                 +" tabla_origen AS fromTable,"
                 +" uniones AS joins,"
                 +" multireg AS multiregistros, "
                 +" agrupadore AS agrupador,"
                 +" ordenadores AS ordenador,"
                 +" filtros AS filtros,"
                 +" vistas AS vistas, "
                 +" dropvistas AS dropVistas,"
                 +" status AS status,"
                 +" descripcion AS descripcion,"
                 +" titulo AS titulo,"
                 +" totalselect as total,"
                 +" configagrupado as agrupado, ");
       
       for(CatalogoDTO multi: columnasMultireg )
          {
          sql.append(this.removeCadena(multi.getDescripcion(), "formularios.", "")).append(", ");
          this.reemplazaTexto(user,this.removeCadena(multi.getDescripcion(), "formularios.", ""), "inner join","left join",Nreport);
          }
       
       
       
       
             sql.append(" tipo AS tipo"
                 +" FROM ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" where identificador=").append(Nreport);
       ResultSet rs = sta.executeQuery(sql.toString());
       while(rs.next())
            {
             idReporte=rs.getInt("IdReporte");
             selectCampos=rs.getString("selectCampos");
             fromTable=rs.getString("fromTable");
             joins=rs.getString("joins");
             multiregistros=rs.getString("multiregistros");
             agrupador=rs.getString("agrupador");
             ordenador=rs.getString("ordenador");
             filtros=rs.getString("filtros");
             vistas=rs.getString("vistas");
             dropVistas=rs.getString("dropVistas");
             status=rs.getString("status");
             descripcion=rs.getString("descripcion");
             titulo=rs.getString("titulo");
             tipo=rs.getString("tipo");
             auxTotal=rs.getString("total");
             agrupado=rs.getString("agrupado");
            for(CatalogoDTO multi: columnasMultireg )
              {
              lefts.add(rs.getString(this.removeCadena(multi.getDescripcion(), "formularios.", "")));
             }
             
             
            }
       if(selectCampos != null)
       {
       aux=this.removeCadena(selectCampos, "null;"," ");
       aux=this.removeCadena(selectCampos, "borrar,","");
      
       selectCampos=aux;
       }
       if(joins != null && !joins.isEmpty())
       {   
       aux=this.removeCadena(joins, "null;"," ");
      
       
       
       joins=aux;
       
       }
       
       if(multiregistros != null)
       {   
       aux=this.removeCadena(multiregistros, "null;"," ");
       multiregistros=aux;
       }
       
       if(agrupador != null && !agrupador.isEmpty())
       {   
       aux=this.removeCadena(agrupador, "null;"," ");
       agrupador=aux;
       }
        
       if(ordenador != null && !ordenador.isEmpty())
       {   
       aux=this.removeCadena(ordenador, "null;"," ");
       ordenador=aux;
       }
        
       if(filtros != null && !filtros.isEmpty())
       {   
       aux=this.removeCadena(filtros, "null;"," ");
       filtros=aux;
       }
       
        
       sql= new StringBuilder();
       
       if(selectCampos != null)
           sql.append("SELECT  DISTINCT ").append(selectCampos);
       if(fromTable!= null)
            sql.append(" FROM ").append(fromTable);
            total.append(" FROM ").append(fromTable);
       if(joins != null && !joins.isEmpty())
          sql.append(" ").append(joins);
          total.append(" ").append(joins);
      
       for(int i=0; i<lefts.size(); i++ )
              {
              if(lefts.get(i)!= null  && !lefts.get(i).isEmpty() )
                 {
                 sql.append(" ").append(lefts.get(i)).append(" ");
                 total.append(" ").append(lefts.get(i)).append(" ");
                 
                 }
             }
       
       if(filtros != null  && !filtros.isEmpty() && !filtros.equals(" "))
           if(!filtros.equals(" "))
           {sql.append(" WHERE( ").append(filtros).append(")");
         total.append(" WHERE( ").append(filtros).append(")");
           }
       if(agrupador != null && !agrupador.isEmpty() && !agrupador.equals(" ") )
           sql.append(" GROUP BY").append("  ").append(agrupador).append(" ");
       if(ordenador != null && !ordenador.isEmpty() && !ordenador.equals(" ") &&  auxTotal == null  && auxTotal.isEmpty() && auxTotal.equals(" "))
          sql.append(" ORDER BY ").append(ordenador);
       
       if(auxTotal!=null && !auxTotal.toString().isEmpty() && !auxTotal.toString().equals(" ") && !auxTotal.toString().equals(""))
          {
          sql.append(" ");
          sql.append(" UNION ALL SELECT ").append(auxTotal).append("   ");
          sql.append(total).append("  ");
           if(ordenador!=null && !ordenador.equals("")  && !ordenador.equals(" ") )
              {
               sql.append(" ORDER BY ").append(ordenador);
                }
          
          }
       
       
       rs = sta.executeQuery(sql.toString());
       
       ArrayList<ReporteDTO> reporte = new ArrayList(); 
        List<SelectDTO> columns = select.getSelect();   
       int j=1,i=0;
       String multiselect;
       while(rs.next())
             {
              registros.add(new ArrayList<String>());      

              for(SelectDTO column: columns )
                  {
                       //array.get(i).add(nota); 
                      if(column.getDatatype().equals("multiselect"))
                        {
                         multiselect=rs.getString(column.getName());
                         if(multiselect!=null && !multiselect.isEmpty() && !multiselect.equals("-1") && !multiselect.equals(" ") )
                          registros.get(i).add(this.getMultiSelectContent(user,"descripcion",column.getList() ,multiselect,"id")); 
                         else
                            registros.get(i).add(" "); 
                        }  
                      else
                         registros.get(i).add(rs.getString(column.getName())); 
                  }
              i++;
              
             }    
     
     int x=0; i=1;
      for(SelectDTO col: columns)
         {
         ArrayList<String> inf= new ArrayList();  
          for(int m=0; m<registros.size();m++){
             inf.add(registros.get(m).get(x));
              
          }
          if(col.getDatatype().equals("multiselect"))
              col.setDatatype("string");
          reporte.add(new ReporteDTO(col.getName(),col.getLabel(),col.getType(),col.getDatatype(),inf));
         i++;
         x++;
        }     
     
      if(auxTotal!=null && !auxTotal.toString().isEmpty() && !auxTotal.toString().equals(" ") && !auxTotal.toString().equals(""))
          {
           reporte.get(0).getInfo().set(reporte.get(0).getInfo().size()-1," Total ");
           
          }
    
    
      con.close();
      rs.close();
       return reporte;   
      } 
public void insertPredios(UserDTO user) throws SQLException
  {
     
     DataSource ds = PoolDataSource.getDataSourceGeneral();
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     StringBuilder sql2 = new StringBuilder();
     sql.append("SELECT count(*) as result FROM formularios.principal");
     ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
     ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh);
     ds = PoolDataSource.getDataSource(user);
     qr = new QueryRunner(ds); 
     sql = new StringBuilder();
     sql.append("SELECT count(*) as result FROM catalogos.predios");
     rsh = new BeanHandler(ResultInteger.class);
     ResultInteger result2 = (ResultInteger) qr.query(sql.toString(), rsh);
     if(result.getResult() != result2.getResult())
     {
          ds = PoolDataSource.getDataSourceGeneral();
          qr = new QueryRunner(ds);
          sql = new StringBuilder();
            sql.append("SELECT folio , predio,\n" +
                "catalogos.region.descripcion as region,\n" +
                "catalogos.municipio.descripcion as municipio,\n" +
                "catalogos.localidad.descripcion as localidad, catalogos.tipotenenciatierra.descripcion as tenencia  FROM formularios.principal\n" +
                " left join catalogos.region on catalogos.region.id= cast(formularios.principal.region as integer) \n" +
                " left join catalogos.municipio on catalogos.municipio.id= cast(formularios.principal.modulopredio_municipio as integer)\n" +
                " left join catalogos.localidad on catalogos.localidad.id= cast(formularios.principal.modulopredio_localidad as  integer) \n" +
                " and\n" +
                " catalogos.localidad.id_municipio =cast(formularios.principal.modulopredio_municipio as integer) \n" +
                " left join catalogos.tipotenenciatierra on catalogos.tipotenenciatierra.id=cast(formularios.principal.cve_tenencia as integer)");
            rsh = new BeanListHandler(PrediosDTO.class);
            List<PrediosDTO> columns = (List<PrediosDTO>) qr.query(sql.toString(), rsh);
            
            
            ds = PoolDataSource.getDataSource(user);
            qr = new QueryRunner(ds);
             sql = new StringBuilder();
             sql.append("TRUNCATE TABLE catalogos.predios");
             qr.update(sql.toString());
             
            
            for(PrediosDTO column:columns){ 
             sql = new StringBuilder();
             sql.append("INSERT INTO catalogos.predios(\n" +
"            id, descripcion, modulopredio_municipio, modulopredio_region, \n" +
"            modulopredio_localidad, tenencia) values(");
             sql.append("'").append(column.getFolio()).append("', ");
            sql.append("'").append(column.getPredio()).append("', ");
            sql.append("'").append(column.getMunicipio()).append("', ");
            sql.append("'").append(column.getRegion()).append("', ");
            sql.append("'").append(column.getLocalidad()).append("', ");    
            sql.append("'").append(column.getTenencia()).append("') ");
             
            
            qr.update(sql.toString());
            }
     }
     
     
  }

public void insertRepresentantes(UserDTO user) throws SQLException
  {
     
     DataSource ds = PoolDataSource.getDataSourceGeneral();
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     StringBuilder sql2 = new StringBuilder();
     sql.append("SELECT count(*) as result FROM formularios.representante");
     ResultSetHandler rsh = new BeanHandler(ResultInteger.class);
     ResultInteger result = (ResultInteger) qr.query(sql.toString(), rsh);
     ds = PoolDataSource.getDataSource(user);
     qr = new QueryRunner(ds); 
     sql = new StringBuilder();
     sql.append("SELECT count(*) as result FROM catalogos.representante_predio");
     rsh = new BeanHandler(ResultInteger.class);
     ResultInteger result2 = (ResultInteger) qr.query(sql.toString(), rsh);
     if(result.getResult() != result2.getResult())
     {
          ds = PoolDataSource.getDataSourceGeneral();
          qr = new QueryRunner(ds);
         sql2.append("select folio as id, nombre_propietario_representante as descripcion FROM formularios.representante");
            rsh = new BeanListHandler(CatalogoDTO.class);
            List<CatalogoDTO> representantes = (List<CatalogoDTO>) qr.query(sql2.toString(), rsh);
            
            
            ds = PoolDataSource.getDataSource(user);
            qr = new QueryRunner(ds);
            sql = new StringBuilder();
            sql.append("TRUNCATE TABLE catalogos.representante_predio");
            sql2 = new StringBuilder();
            sql2.append("INSERT INTO catalogos.representante_predio values(?,?)");
            for(CatalogoDTO rep: representantes){
            Object[] params = {
                               rep.getId(),
                               rep.getDescripcion()
                               };
            qr.update(sql2.toString(),params);
            }
           
            
           
            
     }       
  }
public String campoListMultireg(UserDTO user,int Nreport, String nombreMultire, String Catalogo, String nameColumn,String labelMultireg) throws SQLException
   {
   
     DataSource ds = PoolDataSource.getDataSource(user);
     Connection con =ds.getConnection();
     Statement sta = con.createStatement();
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     String aux=" ";
     StringBuilder aliasCatalogo = new StringBuilder();
     
     if(Nreport==0)
      {
      sql.append("Select max(identificador)+1 as result from ").append(SQL.getTableMainReportes(user,String.valueOf(user.getActivity())));
      ResultSetHandler max = new BeanHandler(ResultInteger.class);
      ResultInteger result = (ResultInteger) qr.query(sql.toString(), max);
      Nreport=result.getResult();
      this.setNumeroReporte(String.valueOf(Nreport));
      this.insertColumn(user, "identificador", Nreport);
      this.limpiarColumna(Nreport, user,"campos","borrar");
      this.limpiarColumna(Nreport, user,"tabla_origen",SQL.getTableMain(user,String.valueOf(user.getActivity())));    
      sql= new StringBuilder();
      
      }
     
     
     sql.append("SELECT campos AS result");
     sql.append(" FROM ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity())));
     sql.append(" WHERE identificador=").append(Nreport);
     ResultSet rs= sta.executeQuery(sql.toString()); 
     StringBuilder campos = new StringBuilder();
     StringBuilder uniones = new StringBuilder();
     while(rs.next())
            {
               campos.append(rs.getString("result"));
            }

     if (!campos.toString().isEmpty())
         {
         campos.append(", ");
         }
     
        sql = new StringBuilder();
            sql.append("SELECT name AS multireg");
            sql.append(" FROM ").append(SQL.getTableInfo(user, String.valueOf(user.getActivity())));
            sql.append(" WHERE label='").append(this.removeCadena(labelMultireg, "_", " ")).append("' and type='form'");
            rs= sta.executeQuery(sql.toString()); 
            
            StringBuilder Multireg = new StringBuilder();

            while(rs.next())
                   {
                      Multireg.append(rs.getString("multireg"));
                   }
          
      Random r = new Random();
       int limite = 100;
      int numero=(int) (Math.random()*limite + 1);
                       aliasCatalogo.append(this.removeCadena(Catalogo, "\\.", "_"));
                       aliasCatalogo.append("_"+String.valueOf(numero));
     
     
      
          
           campos.append(" ").append(aliasCatalogo).append(".descripcion as ").append(nameColumn);
           this.updateColumn(user, "campos", campos.toString(), Nreport);
       
     sql= new StringBuilder();      
     sql.append("SELECT ").append(this.removeCadena(Multireg.toString(),"formularios.","")).append(" AS result");
     sql.append(" FROM ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity())));
     sql.append(" WHERE identificador=").append(Nreport);
     rs= sta.executeQuery(sql.toString()); 
     uniones = new StringBuilder();
     while(rs.next())
            {
               uniones.append(rs.getString("result"));
            }
     aux=this.removeCadena(Multireg.toString(),"formularios.","");
      if(uniones.toString().isEmpty() || uniones.toString().equals("null") )
         {     
           uniones =new StringBuilder();
           uniones.append("left join ").append(Multireg).append(" on ").append(Multireg).append(".folio=");
           uniones.append(SQL.getTableMain(user, String.valueOf(user.getActivity()))).append(".folio ");
           
           this.updateColumn(user, aux, uniones.toString(), Nreport);
           uniones =new StringBuilder();
         }
      
         sql= new StringBuilder();  
         uniones =new StringBuilder();
         uniones.append(" inner join ").append(Catalogo ).append(" as ").append(aliasCatalogo);

         uniones.append(" on ").append( aliasCatalogo).append(".id= cast(").append(Multireg).append(".").append(nameColumn).append(" as integer)");

         this.concatColumn(user, aux, uniones.toString(), Nreport);
             
             
          
         
     sta.close();
     con.close(); 
    return aliasCatalogo.toString();
   }

public void relacionMultiregistro(UserDTO user,int Nreport, String multiReg) throws SQLException{
    DataSource ds = PoolDataSource.getDataSource(user);
     Connection con =ds.getConnection();
     Statement sta = con.createStatement();
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     
     if(Nreport==0)
      {
      
      sql.append("Select max(identificador)+1 as result from ").append(SQL.getTableMainReportes(user,String.valueOf(user.getActivity())));
      ResultSetHandler max = new BeanHandler(ResultInteger.class);
      ResultInteger result = (ResultInteger) qr.query(sql.toString(), max);
      Nreport=result.getResult();
      this.setNumeroReporte(String.valueOf(Nreport));
      this.insertColumn(user, "identificador", Nreport);
      this.limpiarColumna(Nreport, user,"campos","borrar"); 
      this.limpiarColumna(Nreport, user,"tabla_origen",SQL.getTableMain(user,String.valueOf(user.getActivity())));    
     
      sql= new StringBuilder();
      
      }
     
     
     
     
     String MultiregReporte="";
     sql.append("SELECT name ").append("  AS result");
     sql.append(" FROM ").append(SQL.getTableInfo(user, String.valueOf(user.getActivity())));
     sql.append(" WHERE label='").append(multiReg).append("' and type='form'");
     ResultSet rs= sta.executeQuery(sql.toString()); 
     StringBuilder campos = new StringBuilder();
     StringBuilder uniones = new StringBuilder();
     
     while(rs.next())
            {
               campos.append(rs.getString("result"));
            }
     MultiregReporte=campos.toString();
     
     sql = new StringBuilder();
     sql.append("SELECT ").append(this.removeCadena(campos.toString(),"formularios.","")).append("  AS result");
     sql.append(" FROM ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity())));
     sql.append(" WHERE identificador=" ).append(Nreport);
     rs= sta.executeQuery(sql.toString()); 
     
     while(rs.next())
            {
               uniones.append(rs.getString("result"));
            }
     
    if(uniones.toString().isEmpty() || uniones.toString().equals("null"))
       {
        this.updateColumn(user, this.removeCadena(MultiregReporte,"formularios.",""), " left join "+campos+" on "+campos+".folio="+SQL.getTableMain(user,String.valueOf(user.getActivity()))+".folio", Nreport);
       
       }  


  sta.close();
  con.close();
   }

    public String removeCadena(String Cadena, String patron,String reemplazo)
   {
    return  Cadena.replaceAll(patron,reemplazo);  
   }   
 public String getNumeroReporte() {
        return numeroReporte;
    }
    public void setNumeroReporte(String numeroReporte) {
        this.numeroReporte = numeroReporte;
    }    
public int buscarPatron(String cadena, String patron,int indice)
   {
    return cadena.indexOf(patron,indice);
   }    
public  void reemplazaTexto(UserDTO user, String columna, String cadenaQuitar, String cadenaColocar, int identificador) throws SQLException
{
     DataSource ds = PoolDataSource.getDataSource(user);
     Connection con =ds.getConnection();
     Statement sta = con.createStatement();
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("UPDATE ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" SET ").append(columna).append(" = REPLACE(").append(columna);
     sql.append(",'").append(cadenaQuitar).append("','").append(cadenaColocar).append("')");
     sql.append(" WHERE identificador=").append(identificador);
     qr.update(sql.toString());
     sta.close();
     con.close();
     
}
public  void insertColumn(UserDTO user, String columna, int texto) throws SQLException
  {
     DataSource ds = PoolDataSource.getDataSource(user);
     Connection con =ds.getConnection();
     Statement sta = con.createStatement();
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("INSERT INTO  ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" (").append(columna).append(") VALUES (").append(texto).append(")");
     
     sta.executeUpdate(sql.toString());
     sta.close();
     con.close();
  }

public  void updateColumn(UserDTO user, String columna, String texto, int identificador) throws SQLException
  {
     DataSource ds = PoolDataSource.getDataSource(user);
     Connection con =ds.getConnection();
     Statement sta = con.createStatement();
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("UPDATE ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" SET ").append(columna).append("='").append(texto).append("'");
     sql.append(" WHERE identificador=").append(identificador);
     sta.executeUpdate(sql.toString());
     sta.close();
     con.close();
  }

public  void concatColumn(UserDTO user, String columna, String texto, int identificador) throws SQLException
  {
     DataSource ds = PoolDataSource.getDataSource(user);
     Connection con =ds.getConnection();
     Statement sta = con.createStatement();
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("UPDATE ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" SET ").append(columna).append("=").append(columna).append(" || ");
     sql.append("'").append(texto).append("'");
     sql.append(" WHERE identificador=").append(identificador);
     sta.executeUpdate(sql.toString());
     sta.close();
     con.close();
  }

public  void updateColumnInteger(UserDTO user, String columna, int numero, int identificador) throws SQLException
  {
     DataSource ds = PoolDataSource.getDataSource(user);
     Connection con =ds.getConnection();
     Statement sta = con.createStatement();
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("UPDATE ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" SET ").append(columna).append("=").append(numero);
     sql.append(" WHERE identificador=").append(identificador);
     sta.executeUpdate(sql.toString());
     sta.close();
     con.close();
  }

public  void limpiarConfigReporte(UserDTO user, String columna, int numero, int identificador) throws SQLException
  {
     DataSource ds = PoolDataSource.getDataSource(user);
     Connection con =ds.getConnection();
     Statement sta = con.createStatement();
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("UPDATE ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity()))).append(" SET " +

"  tabla_origen='' ," +
"  uniones='' ," +
"  multireg='' ," +
"  agrupadore='' ," +
"  ordenadores=''," +
"  filtros=''," +
"  vistas=''," +
"  dropvistas=''," +
"  status=''," +
"  descripcion=''," +
"  tiporeporte='' ," +
"  configcampos=''").append(columna).append("=").append(numero);
     sql.append(" WHERE identificador=").append(identificador);
     sta.executeUpdate(sql.toString());
     sta.close();
     con.close();
  }

public  String  selectColumn(UserDTO user, String columna, int identificador) throws SQLException
  {
     String result="";
     DataSource ds = PoolDataSource.getDataSource(user);
     Connection con =ds.getConnection();
     Statement sta = con.createStatement();
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("SELECT ").append(columna).append(" AS result FROM ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity())));
     sql.append(" WHERE identificador=").append(identificador);
     ResultSet rs= sta.executeQuery(sql.toString()); 
     
     while(rs.next())
            {
               result= rs.getString("result");
            }
     sta.close();
     con.close();
  return result;
  }

 public List<TitulosReporteDTO> GetTitulosReporte(UserDTO user) throws Exception{
     DataSource ds = PoolDataSource.getDataSource(user);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("SELECT identificador, titulo");
     sql.append(" FROM ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity())));
     sql.append(" WHERE userid =").append(user.getId());
     sql.append(" ORDER BY identificador");
     ResultSetHandler rsh = new BeanListHandler(TitulosReporteDTO.class);
     List<TitulosReporteDTO> request = (List<TitulosReporteDTO>) qr.query(sql.toString(), rsh);
     return request;
 }

  public List<TitulosReporteDTO> GetTitulosReportes(UserDTO user) throws Exception{
     DataSource ds = PoolDataSource.getDataSource(user);
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("SELECT identificador, titulo");
     sql.append(" FROM ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity())));
     sql.append(" WHERE tiporeporte = 2");
     sql.append(" ORDER BY identificador");
     ResultSetHandler rsh = new BeanListHandler(TitulosReporteDTO.class);
     List<TitulosReporteDTO> request = (List<TitulosReporteDTO>) qr.query(sql.toString(), rsh);
     return request;
 }

 public String GetConfigCampos(UserDTO user, int id) throws Exception{     
     String result="";
     DataSource ds = PoolDataSource.getDataSource(user);
     Connection con =ds.getConnection();
     Statement sta = con.createStatement();
     QueryRunner qr = new QueryRunner(ds);
     StringBuilder sql = new StringBuilder();
     sql.append("SELECT configcampos AS result");
     sql.append(" FROM ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity())));
     sql.append(" WHERE identificador=").append(id);
     ResultSet rs= sta.executeQuery(sql.toString()); 
     
     while(rs.next())
            {
               result= rs.getString("result");
            }
     
     
     
     sta.close();
     con.close();
  return result;
 }

 public void borrarReporte(UserDTO user, int Nreporte, int activity) throws SQLException
 {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM ").append(SQL.getTableMainReportes(user, String.valueOf(user.getActivity())));
        sql.append(" WHERE identificador = ?");
        Object[] params = {
            Nreporte
        };
        qr.update(sql.toString(), params); 
    
 
 }
 
 public void borrarUnionesMultireg(UserDTO user,int Nreporte) throws SQLException
    {
        DataSource ds = PoolDataSource.getDataSource(user);
        QueryRunner qr = new QueryRunner(ds);
        StringBuilder sql = new StringBuilder();
        ResultSetHandler rsh = new BeanListHandler(CatalogoDTO.class); 
       sql.append("SELECT  position as id, name as descripcion FROM ");
       sql.append(SQL.getTableInfo(user, String.valueOf(user.getActivity()))); 
       sql.append(" WHERE name!='formularios.imagen' and name!='formularios.imagenincendios' and type='form'");
       List<CatalogoDTO> columnasMultireg = (List<CatalogoDTO>) qr.query(sql.toString(),rsh);     
       
       for(CatalogoDTO mul:columnasMultireg )
          {
          this.updateColumn(user,this.removeCadena(mul.getDescripcion(), "formularios.", ""),"", Nreporte);                  
          }
       
    }

public void totalReporte(String user, GroupDTO total, int Nreport,List<SelectDTO> columns) throws SQLException, Exception
    {
    
    StringBuilder select = new StringBuilder();    
    StringBuilder sql = new StringBuilder();
    StringBuilder selectGroup = new StringBuilder();
    StringBuilder totalFrom = new StringBuilder();
    String leftsGroup=" ";
    UserDAO userDao = new UserDAO();
    UserDTO userd = userDao.getUser(Integer.parseInt(user));
    DataSource ds = PoolDataSource.getDataSource(userd);
    Connection con =ds.getConnection();
    Statement sta = con.createStatement();
    int i=0;
    int bandera=0;
    
    
  for(SelectDTO columna:columns)
    {  
  for(GroupDTO configuracionTotal: total.getGroup()) 
    {
        
    if(configuracionTotal.getparamsTotal().get(i).getTotal().equals("Si"))
      {
       if(columna.getName().equals(configuracionTotal.getName()) && columna.getType().equals(configuracionTotal.getTipo()))     
       {
           bandera=1;
           if(select != null && !select.toString().equals("") && !select.toString().isEmpty())
          {select.append(", "); }
          
          if(configuracionTotal.getTipo().equals("Principal"))
            {
             select.append(" ").append(configuracionTotal.getOperador()).append(" (").append(SQL.getTableMain(userd, String.valueOf(userd.getActivity())));
             select.append(".").append(configuracionTotal.getName()).append(") as ").append(configuracionTotal.getName()).append(" ");
                
            }
          else{
              String multireg="";   
              sql = new StringBuilder();
              totalFrom = new StringBuilder();
              sql.append("SELECT name AS multireg from ").append(SQL.getTableInfo(userd,String.valueOf(userd.getActivity())));
              sql.append(" WHERE label='").append(this.removeCadena(configuracionTotal.getTipo(), "_", " ")).append("' AND datatype='records'");
              ResultSet rs=sta.executeQuery(sql.toString());
              while(rs.next())
                              {
                               multireg= rs.getString("multireg");
                               }
              totalFrom.append(" left join ");
              totalFrom.append(multireg).append(" on ").append(multireg).append(".folio = ").append(SQL.getTableMain(userd,String.valueOf(userd.getActivity())));
              totalFrom.append(".folio");                
              select.append(" ").append(configuracionTotal.getOperador()).append(" (").append(multireg);
              select.append(".").append(configuracionTotal.getName()).append(") as ").append(configuracionTotal.getName());
              }    
           }
        }
      
    }
    if(bandera==0)
       {          
        if(select != null && !select.toString().equals("") && !select.toString().isEmpty())
          {select.append(", "); }     
        select.append(" null ");   
       }
    bandera=0; 
    }
     
    
     this.updateColumn(userd, "totalselect", select.toString(), Nreport);
     //this.updateColumn(userd, "totalgroup", selectGroup.toString(), Nreport);
   
    
    
    sta.close();
    con.close();
    }
 
 
public String getMultiselect(UserDTO user, String nameColumn, String list, String type ,String table) throws SQLException 
   {
    DataSource ds = PoolDataSource.getDataSource(user);
    QueryRunner qr = new QueryRunner(ds);
    StringBuilder sql = new StringBuilder();
    String multiselect="";
    if(type.equals("P"))
    { 
     sql.append(" SELECT type as result FROM ").append(SQL.getTableInfo(user,String.valueOf(user.getActivity()  )));
     sql.append(" WHERE name='").append(nameColumn).append("' AND listname like '%").append(list).append("%'");
     ResultSetHandler rsh = new BeanHandler(ResultString.class);   
     ResultString multiselectP = (ResultString)qr.query(sql.toString(), rsh);
    multiselect=multiselectP.getResult();
    }
    else
       {
         
         sql.append("SELECT name as result ");
         sql.append(" FROM ").append(SQL.getTableInfo(user, String.valueOf(user.getActivity())));
         sql.append(" WHERE label='").append(this.removeCadena(table, "_", " ")).append("' and type='form'");
         ResultSetHandler rsh = new BeanHandler(ResultString.class);   
         ResultString multiregistro = (ResultString)qr.query(sql.toString(), rsh);
         sql = new StringBuilder();
         sql.append(" SELECT type as result FROM ").append(multiregistro.getResult()).append("_info");
         sql.append(" WHERE name='").append(nameColumn).append("' AND listname='").append(list).append("'");
         rsh = new BeanHandler(ResultString.class);   
         ResultString multiselectM = (ResultString)qr.query(sql.toString(), rsh);
         if(multiselectM!=null)
            multiselect=multiselectM.getResult();
         else
           multiselect="select";
         
       }
    return multiselect;
   }

public String setMultiregistro(UserDTO user ,String  nameColumn,String type,int Nreport) throws SQLException
  {
    DataSource ds = PoolDataSource.getDataSource(user);
    QueryRunner qr = new QueryRunner(ds);
    StringBuilder sql = new StringBuilder();
   if(Nreport==0)
      {
      sql.append("Select max(identificador)+1 as result from ").append(SQL.getTableMainReportes(user,String.valueOf(user.getActivity())));
      ResultSetHandler max = new BeanHandler(ResultInteger.class);
      ResultInteger result = (ResultInteger) qr.query(sql.toString(), max);
      Nreport=result.getResult();
      this.setNumeroReporte(String.valueOf(Nreport));
      this.insertColumn(user, "identificador", Nreport);
      this.limpiarColumna(Nreport, user,"campos","borrar"); 
      this.limpiarColumna(Nreport, user,"tabla_origen",SQL.getTableMain(user,String.valueOf(user.getActivity())));    
      
      sql= new StringBuilder();
     }
   if(type.equals("Principal"))
    {
    this.concatColumn(user, "campos",", "+SQL.getTableMain(user,String.valueOf(user.getActivity()))+"."+nameColumn+" as "+nameColumn , Nreport);
    }
   else
    {
     sql.append("SELECT name as result ");
     sql.append(" FROM ").append(SQL.getTableInfo(user, String.valueOf(user.getActivity())));
     sql.append(" WHERE label='").append(this.removeCadena(type, "_", " ")).append("' and type='form'");
     ResultSetHandler rsh = new BeanHandler(ResultString.class);   
     ResultString multiregistro = (ResultString)qr.query(sql.toString(), rsh);
     this.concatColumn(user, "campos",", "+multiregistro.getResult()+"."+nameColumn+" as "+nameColumn , Nreport);
     
     sql = new StringBuilder();
     this.relacionMultiregistro(user, Nreport, this.removeCadena(type, "_", " "));   
    }
         
  return "multiselect";
  }

public String getMultiSelectContent(UserDTO user, String  columna, String tabla,String filtro, String columnaFiltro) throws SQLException
  {
   DataSource ds = PoolDataSource.getDataSource(user);
   QueryRunner qr = new QueryRunner(ds);   
   StringBuilder multiselect= new StringBuilder();
   StringBuilder sql = new StringBuilder(); 
   int tamaño;
   sql.append(" SELECT ").append(columna).append(" AS result ");
   sql.append(" FROM ").append(tabla);
   sql.append(" WHERE  ").append(tabla).append(".").append(columnaFiltro).append(" IN(");
   sql.append(filtro).append(") ");
   ResultSetHandler rsh=new BeanListHandler(ResultString.class);
   List<ResultString> descripcion=(List<ResultString>)qr.query(sql.toString(), rsh);
   tamaño=0;
   for( ResultString desc:descripcion)
     {
         multiselect.append(desc.getResult());
         tamaño++;
         if(tamaño<descripcion.size())
             multiselect.append(", ");
     }
  return multiselect.toString();
  }

public List<SubtotalReporte> getSubtotal(UserDTO user, String columnaReferencia, String columnaSubtotalizar,String table,String operador,String Inners,ArrayList<String> Lefts,String nameColumnaReferencia,String filtro,int NoReporte) throws SQLException
  {
   DataSource ds = PoolDataSource.getDataSource(user);
   QueryRunner qr = new QueryRunner(ds);   
   StringBuilder multiselect= new StringBuilder();
   StringBuilder sql = new StringBuilder(); 
   StringBuilder buscarOrdenamiento = new StringBuilder();
   buscarOrdenamiento.append("SELECT ordenadores as result  from ");
   buscarOrdenamiento.append(SQL.getTableMainReportes(user,String.valueOf(user.getActivity())));
   buscarOrdenamiento.append("  WHERE ordenadores like '%").append(nameColumnaReferencia).append(" ASC%' and identificador=").append(NoReporte);
   ResultSetHandler rsh= new BeanHandler(ResultString.class);
   ResultString ordenamiento= (ResultString)qr.query(buscarOrdenamiento.toString(), rsh);
   
     sql.append(" SELECT ").append(columnaReferencia).append(" AS columna,");
     sql.append(" ").append(operador).append(" (").append(columnaSubtotalizar).append(") as valor ");
     sql.append(" FROM ").append(SQL.getTableMain(user, String.valueOf(user.getActivity())));
     sql.append(" ").append(Inners);
     sql.append(" ");
     for(String multiregistros: Lefts)
       {
         if (multiregistros!=null && !multiregistros.equals("null") && !multiregistros.isEmpty() ) 
         sql.append(" ").append(multiregistros);
       }
    if(!filtro.equals(" ") && !filtro.equals("") && !filtro.equals("null") )
            {
                sql.append("  WHERE ").append(filtro);
            }     
   sql.append(" GROUP BY ").append(columnaReferencia);
   if(ordenamiento.getResult() !=null && !ordenamiento.getResult().isEmpty())
      {
      sql.append(" ORDER BY  columna ASC");
      }else
      {
       sql.append(" ORDER BY columna DESC");
      }
   
   rsh= new BeanListHandler(SubtotalReporte.class); 
   List<SubtotalReporte> subtotal=(List<SubtotalReporte>)qr.query(sql.toString(), rsh);
  return subtotal;  
  }

public String getMultireg(UserDTO user, String label) throws SQLException
   {
   DataSource ds = PoolDataSource.getDataSource(user);
   QueryRunner qr = new QueryRunner(ds);   
   StringBuilder sql = new StringBuilder(); 
   sql.append("Select name AS result FROM ").append(SQL.getTableInfo(user, String.valueOf(user.getActivity())));
   sql.append(" WHERE type='form' and label='").append(label).append("'");
   ResultSetHandler rsh = new BeanHandler(ResultString.class);
   ResultString multireg=(ResultString)qr.query(sql.toString(), rsh);
   
   return multireg.getResult();
   }


public ArrayList<ReporteDTO> getColocarSubtotalMatriz(ArrayList<ReporteDTO> reporte, List<SubtotalReporte> subtotal, GroupDTO configuracionGroup,List<GroupDTO> g)
  {
     int x=0,k=0,r,infocount; 
     int encontrado=0;
     String columna=" ", valor=" ";
     int j=0,i=0,auxI,indice=0, colocarSubtotal;
     List<String> info= new ArrayList<>();
     List<String> infoValores= new ArrayList<>();
     for(ReporteDTO rep:reporte)
       {
          
           infocount=0;
           if(rep.getName().equals(configuracionGroup.getparamsTotal().get(0).getNameRef()) && rep.getType().equals(configuracionGroup.getparamsTotal().get(0).getType()) )
            {
           k=0;
           encontrado=0;
           while(encontrado==0)
                {
                if(subtotal.get(k).columna.equals(rep.getInfo().get(i)))
                   {
                   encontrado=1;
                   columna=subtotal.get(k).columna;
                   valor=subtotal.get(k).valor;
                   
                   }
                k++;
                }
                            
               while( i < rep.getInfo().size() )
                  {
                   if(!rep.getInfo().get(i).equals(columna))
                         {
                         if(rep.getInfo().get(i).equals(" ") || rep.getInfo().get(i).equals("Subtotal "+columna))
                          {
                           auxI=1+i;
                           if(auxI>=rep.getInfo().size())
                               auxI=i;
                              if(!rep.getInfo().get(auxI).equals(columna))
                                 {
                                    if(x==0){
                                        info.add("Subtotal "+columna);
                                        infocount++;
                                        indice=i;
                                        
                                           }
                                    else {
                                       info.add(" "); 
                                       indice=i;
                                       infocount++;
                                       }
                                 }
                          }
                         else{
                                if(x==0){
                                        info.add("Subtotal "+columna);
                                        indice=i;
                                        infocount++;
                                           }
                                    else {
                                       info.add(" "); 
                                       indice=i;
                                       infocount++;                             
                                       }
                             }
                                
                        indices.add(String.valueOf(infocount-1));
                                
                         r=0;       
                        infoValores= new ArrayList<>();
                        for(ReporteDTO auxRep: reporte)
                         {
                          if(auxRep.getName().equals(configuracionGroup.getName()) && auxRep.getType().equals(configuracionGroup.getTipo()) )
                            {
                             j=0;
                             
                             for( String val:auxRep.getInfo())  
                                 {   
                                 if(j != infocount-1)    
                                         infoValores.add(val);
                                 else {
                                         infoValores.add(valor);
                                         infoValores.add(val);
                                         
                                      }
                                 j++;
                                 }
                             
                             auxRep.setInfo(infoValores);
                            // reporte.get(r).setInfo(infoValores);
                            }            
                         r++;
                         }
                        
                        
                     k=0;
                                encontrado=0;
                                while(encontrado==0 )
                                     {
                                if(!rep.getInfo().get(i).equals(" ") && !rep.getInfo().get(i).equals("Subtotal "+columna)) 
                                   {
                                       if(subtotal.get(k).columna.equals(rep.getInfo().get(i)))
                                        {
                                        encontrado=1;
                                        columna=subtotal.get(k).columna;
                                        valor=subtotal.get(k).valor;
                                        subtotal.remove(k);
                                        }
                                     k++;
                                     }
                                    else
                                     {
                                     i++;                      
                                     if(i>=rep.getInfo().size())
                                         encontrado=1;
                                     }
                                    }           
                            
                                
                       }else
                         {
                         info.add(rep.getInfo().get(i));
                         infocount++;
                         i++;
                         }
                   
                  }
               
               if(x==0)
               info.add("Subtotal "+columna);
               else{
                   info.add("Subtotal "+columna);
                  }
                  
                k=0;
                encontrado=0;
                auxI=i-1;
              
                    r=0;       
                    infoValores= new ArrayList<>();
                    for(ReporteDTO auxRep: reporte)
                         {
                          if(auxRep.getName().equals(configuracionGroup.getName()) && auxRep.getType().equals(configuracionGroup.getTipo()) )
                            {
                             j=0;
                             indices.add(String.valueOf(infocount-1));
                             for( String val:auxRep.getInfo())  
                                 {   
                                 if(j != infocount-1)    
                                         infoValores.add(val);
                                 else {
                                     infoValores.add(val);    
                                     infoValores.add(valor);
                                         
                                       
                                      }
                                 j++;
                                 }
                             
                             auxRep.setInfo(infoValores);
                            // reporte.get(r).setInfo(infoValores);
                            }            
                         r++;
                         }
                  
               
               
                       
            rep.setInfo(info);
           }
        x++;
      
       }     
     
     
      
  return reporte;
  }
        
public int getSubtotalesColocados()
    {
    return SubtotalesColocados;
    }

public void setSubtotalesColocados(int colocado)
    {
    SubtotalesColocados=colocado;
    }

public List<String> getIndices()
   {
      return indices;
   }


}
