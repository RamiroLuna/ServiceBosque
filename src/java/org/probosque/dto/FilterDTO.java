package org.probosque.dto;

import com.google.gson.Gson;
import com.google.gson.internal.StringMap;
import java.sql.SQLException;
import org.probosque.dao.CatalogoDAO;
import org.probosque.dao.SubTableDAO;

public class FilterDTO {
    private String field;
    private int operator;
    private Object value;
    private String datatype;
    private String listname;
    private FilterDTO subfield;
    
    /*
    filter":{"field":"formularios.planta_valedelegacion","datatype":"records","
    subfield":{"field":"codigoqr","operator":"1","value":"qrcode.png","datatype":"image"}}}
    */
    
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getListname() {
        return listname;
    }

    public void setListname(String listname) {
        this.listname = listname;
    }    
   
    public FilterDTO getSubfield() {
        return subfield;
    }

    public void setSubfield(FilterDTO subfield) {
        this.subfield = subfield;
    }
    
    public String getSQL(UserDTO user){
       
        
        StringBuilder sql = new StringBuilder();
        if (this.subfield == null) {
            switch (datatype) {
                case "date":
                    sql.append(" WHERE ").append("to_date(").append(field).append(", 'DD/MM/YYYY')");
                    break;
                default:
                    sql.append(" WHERE ").append(field);
            }
            //sql.append(" WHERE ").append(field);
            switch (operator) {
                case 1:
                    switch (datatype) {
                        case "string":
                        case "alphanumeric":
                        case "image":
                            sql.append(" LIKE ");
                            break;
                        case "date":
                            sql.append(" BETWEEN ");
                            break;
                        default:
                            sql.append(" = ");
                    }
                    //sql.append(" = ");
                    break;
                case 2:
                    sql.append(" > ");
                    break;
                case 3:
                    sql.append(" < ");
                    break;
            }
            switch (datatype) {
                case "string":case"alphanumeric":
                case "image":
                    sql.append("'%").append(String.valueOf(value)).append("%'");
                    break;
                case "real":
                    sql.append(String.valueOf(value));
                    break;
                case "numeric":
                    sql.append(String.valueOf(value));
                    break;
                case "date":
                    Gson gson = new Gson();
                    
                    StringMap map = (StringMap)value;
                    
                    //FilterDateDTO filterDate = gson.fromJson(String.valueOf(value), FilterDateDTO.class);
                    
                    sql.append("to_date('").append(map.get("start")).append("', 'DD/MM/YYYY') AND " + "to_date('").append(map.get("end")).append("', 'DD/MM/YYYY')");
                    
                    //return "BETWEEN '" + this.start + "' AND '" + this.end + "'";
                    
                    //FilterDateDTO filterDate = FilterDateDTO(String.valueOf(map.get("start")), String.valueOf(map.get("end")));
                    
                    //()
                    
                    
                    //FilterDateDTO filterDate = (FilterDateDTO)value;
                    
                    
                    
                    
                    //:{start:"1",end:"2"}
                    
                    //sql.append("'").append(String.valueOf(value)).append("'");
                    //sql.append(filterDate.getSQLBetween());
                    break;
                case "list":
                    CatalogoDAO catalogoDao = new CatalogoDAO();
                    try {
                        CatalogoDTO catalogo = catalogoDao.getCatalogo(user, listname, String.valueOf(value));
                        /*
                        if (catalogo != null) {
                            sql.append("'").append(String.valueOf(catalogo.getId())).append("'");
                        } else {
                            sql.append("'").append(String.valueOf(value)).append("'");
                        }*/
                        if (catalogo != null) {
                            sql.append("'").append(String.valueOf(catalogo.getDescripcion())).append("'");
                            //sql.append("'").append(String.valueOf(catalogo.getId())).append("'");
                        } else {
                            sql.append("'").append(String.valueOf(value)).append("'");
                        }
                    } catch (SQLException ex) {
                        sql.append("'").append(String.valueOf(value)).append("'");
                        System.out.println("Error 1 "+ex);
                    }
                    break;
            }
            return sql.toString();
        } else {
            //filter":{"field":"formularios.planta_valedelegacion","datatype":"records","
            //subfield":{"field":"codigoqr","operator":"1","value":"qrcode.png","datatype":"image"}}}
            StringBuilder subFilter = new StringBuilder();
        
            
            
            subFilter.append(" WHERE ").append(subfield.getField());
            switch (subfield.getOperator()) {
                case 1:
                    switch (datatype) {
                        case "string":
                        case "alphanumeric":
                        case "image":
                            sql.append(" LIKE ");
                            break;
                        default:
                            sql.append(" = ");
                    }
                    //sql.append(" = ");
                    //subFilter.append(" = ");
                    break;
                case 2:
                    subFilter.append(" > ");
                    break;
                case 3:
                    subFilter.append(" < ");
                    break;
            }
            switch (subfield.getDatatype()) {
                case "string":case"alphanumeric":
                case "image":
                    subFilter.append("'%").append(String.valueOf(subfield.getValue())).append("%'");
                    break;
                case "real":
                    subFilter.append(String.valueOf(subfield.getValue()));
                    break;
                case "numeric":
                    subFilter.append(String.valueOf(subfield.getValue()));
                    break;
                case "date":
                    subFilter.append("'").append(String.valueOf(subfield.getValue())).append("'");
                    break;
                case "list":
                    CatalogoDAO catalogoDao = new CatalogoDAO();
                    try {
                        CatalogoDTO catalogo = catalogoDao.getCatalogo(user, listname, datatype);
                        if (catalogo != null) {
                            subFilter.append("'").append(String.valueOf(catalogo.getId())).append("'");
                        } else {
                            subFilter.append("'").append(String.valueOf(subfield.getValue())).append("'");
                        }
                    } catch (SQLException ex) {
                        System.out.println("Error 1 "+ex);
                        subFilter.append("'").append(String.valueOf(subfield.getValue())).append("'");
                    }
                    break;
            }
            SubTableDAO subDao = new SubTableDAO();
            try {
                return subDao.getFoliosForWhere(user, this.field, subFilter.toString());
            } catch (Exception ex) {
                System.out.println("Error 1 "+ex);
                return "";
                //Logger.getLogger(FilterDTO.class.getName()).log(Level.SEVERE, null, ex);
            }
            //return subFilter.toString();
            //return "";
        }
    }
}
