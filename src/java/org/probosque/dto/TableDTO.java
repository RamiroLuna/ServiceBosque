package org.probosque.dto;

import java.util.ArrayList;
import java.util.List;
import org.probosque.model.ErrorDato;
import org.probosque.model.MensajeError;
import org.probosque.model.PuntoHash;

/**
 *
 * @author admin
 */
public class TableDTO {

    private ArrayList<ColumnDTO> columns;
    private MensajeError mensajeError;

    public String getFolio() {
        String folio = "";
        for (ColumnDTO column : columns) {
            if (column.getName().equals("folio")) {
                return String.valueOf(column.getValue());
            }
        }
        return folio;
    }

    public String getSQLSelect() {
        String sql = "";
        for (ColumnDTO column : columns) {
            sql += column.getSQLSelect();
        }
        sql = sql.substring(0, sql.lastIndexOf(","));
        return sql;
    }

    public String getSQLSelectJoin() {
        String sql = "";
        for (ColumnDTO column : columns) {
            sql += column.getSQLSelectJoin();
        }
        sql = sql.substring(0, sql.lastIndexOf(","));
        return sql;
    }

    public String getSqlSelectColumnsWithComillas() {
        String sql = "";
        for (ColumnDTO column : columns) {
            sql += column.getSQLSelectWithComillas();
        }
        sql = sql.substring(0, sql.lastIndexOf(","));
        return sql;
    }

    public String getSqlEditColumns() {
        String sql = "";
        for (ColumnDTO column : columns) {
            sql += column.getSQLSelectEdit();
        }
        sql = sql.substring(0, sql.lastIndexOf(","));
        return sql;
    }

    public String getSQLSelectJoinWithRecords() {
        String sql = "";
        for (ColumnDTO column : columns) {
            sql += column.getSQLSelectJoinWithRecord();
        }
        sql = sql.substring(0, sql.lastIndexOf(","));
        return sql;
    }
    
     public String getSQLSelectJoinWithRecords2() {
        String sql = "";
        for (ColumnDTO column : columns) {
            sql += column.getSQLSelectJoinWithRecord2();
        }
        sql = sql.substring(0, sql.lastIndexOf(","));
        return sql;
    }

    public String getSQLJoins(UserDTO user, String activity) {
        String sql = "";
        for (ColumnDTO column : columns) {
            sql += column.getSQLJoin(user, activity);
        }
        //sql = sql.substring(0, sql.lastIndexOf(","));
        return sql;
    }

    public ArrayList<String> getColumnsLabels() {
        ArrayList<String> labels = new ArrayList<>();
        for (ColumnDTO column : columns) {
            if (!column.getDatatype().equals("records")) {
                labels.add(column.getLabel());
            }
        }
        return labels;
    }

    public String getSQLParams() {
        try {
            StringBuilder sql = new StringBuilder();
            for (ColumnDTO column : this.columns) {
                sql.append(",?");
            }
            return sql.substring(1);
        } catch (Exception error) {
            return "";
        }
    }

    public String getSQLEditParams() {
        try {
            StringBuilder sql = new StringBuilder();
            for (ColumnDTO column : this.columns) {
                sql.append(",").append(column.getName()).append("=?");
            }
            return sql.substring(1);
        } catch (Exception error) {
            return "";
        }
    }

    public Object[] getParams() {
        ArrayList<Object> params = new ArrayList<>();
        setMensajeError(new MensajeError());
        for (ColumnDTO column : columns) {
            Object param = null;
            try {
                String valueString = String.valueOf(column.getValue());
                switch (column.getDatatype()) {
                    case "string": case "alphanumeric": case "time":
                        if (valueString.isEmpty()) {
                            param = null;
                            if (column.getName().equals("folio")) {
                                int x = Integer.valueOf("error");
                            }
                        } else {
                            param = valueString;
                        }
                        break;
                    case "real":
                        if (valueString.isEmpty()) {
                            param = null;
                        } else {
                            param = Double.valueOf(valueString);
                        }
                        break;
                    case "numeric":
                        if (valueString.isEmpty()) {
                            param = null;
                        } else {
                            param = Integer.valueOf(valueString);
                            if (column.getName().equals("longitud")) {
                                if (valueString.trim().length() != 6) {
                                    int x = Integer.valueOf("field:longitud");
                                }
                            }
                            if (column.getName().equals("latitud")) {
                                if (valueString.trim().length() != 7) {
                                    int x = Integer.valueOf("field:latitud");
                                }
                            }
                        }
                        break;
                    case "date":
                        if (valueString.isEmpty()) {
                            param = null;
                        } else {
                            /*
                             SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                             Date dateA;
                             java.sql.Date dateSqlA = null;
                             dateA = formatter.parse(valueString);
                             dateSqlA = new java.sql.Date(dateA.getTime());
                             param = dateSqlA;
                             */
                            param = valueString;
                        }
                        break;
                    case "list":
                        if (valueString.isEmpty() || valueString.equals("-1")) {
                            param = null;
                        } else 
                           if(column.getName().equals("modulopredio_cup"))
                            {
                                param = valueString;
                            }
                           else 
                               if(column.getName().equals("numero_arbol"))
                               {
                                   param = Integer.parseInt(valueString.trim().split("-")[1]);
                               }
                               else
                               if(column.getName().equals("cuenca") || column.getName().equals("subcuenca"))
                                {
                                param = valueString;
                                }
                               else 
                                 
                                 if(valueString.contains(","))
                                 {
                                 param = valueString;
                                 }
                               else{                                   
                            param = Integer.valueOf(valueString);
                            }
                        
                        break;
                    default:
                        //param = Integer.valueOf(String.valueOf(column.getValue()));
                        param = (String.valueOf(column.getValue()));
                        break;
                }
            } catch (Exception error) {
                ErrorDato errorDato = new ErrorDato(column.getLabel(), column.getDatatype());
                getMensajeError().addErrorDato(errorDato);
                param = (Integer.valueOf(String.valueOf(column.getValue())));
            } finally {
                //params.add(column.getValue());
                params.add(param);
            }
        }
        return params.toArray();
    }

    public Object[] getEditParams() {
        ArrayList<Object> params = new ArrayList<>();
        Object folio = null;

        setMensajeError(new MensajeError());

        for (ColumnDTO column : columns) {
            Object param = null;

            try {
                String valueString = String.valueOf(column.getValue());
                switch (column.getDatatype()) {
                    case "string": case "alphanumeric": case "time":
                        if (valueString.isEmpty()) {
                            param = null;
                        } else {
                            param = valueString;
                        }
                        break;
                    case "real":
                        if (valueString.isEmpty()) {
                            param = null;
                        } else {
                            param = Double.valueOf(valueString);
                        }
                        break;
                    case "numeric":
                        if (valueString.isEmpty()) {
                            param = null;
                        } else {
                            param = Integer.valueOf(valueString);
                            if (column.getName().equals("longitud")) {
                                if (valueString.trim().length() != 6) {
                                    int x = Integer.valueOf("field:longitud");
                                }
                            }
                            if (column.getName().equals("latitud")) {
                                if (valueString.trim().length() != 7) {
                                    int x = Integer.valueOf("field:latitud");
                                }
                            }
                        }
                        break;
                    case "date":
                        if (valueString.isEmpty()) {
                            param = null;
                        } else {
                            /*
                             SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                             Date dateA;
                             java.sql.Date dateSqlA = null;
                             dateA = formatter.parse(valueString);
                             dateSqlA = new java.sql.Date(dateA.getTime());
                             param = dateSqlA;*/
                            param = valueString;
                        }

                        /*
                         if (valueString.isEmpty()) {
                         param = null;
                         } else {
                         /*
                         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                         Date dateA;
                         java.sql.Date dateSqlA = null;
                         dateA = formatter.parse(valueString);
                         dateSqlA = new java.sql.Date(dateA.getTime());

                         param = dateSqlA;
                         param = valueString;
                         }*/
                        break;
                    case "list":
                     
                        if (valueString.isEmpty()) {
                            param = -1;
                        } else {
                            if(column.getName().equals("modulopredio_cup"))
                            {
                                param = valueString;
                            }else{
                                /*
                                * Try convierte valueString a numero 
                                * en caso de ocurrir eror es porque el 
                                * valueString contiene ejemplo: "1,2,3"
                                */
                                try {
                                    param = Integer.valueOf(valueString);
                                } catch (Exception e) {
                                    param = valueString;
                                }
                              
                            }
                            
                            
                        }
                        break;
                    default:
                        param = (String.valueOf(column.getValue()));
                        //param = Integer.valueOf(String.valueOf(column.getValue()));
                        break;
                }
            } catch (Exception error) {
                //param = (Integer.valueOf(String.valueOf(column.getValue())));

                ErrorDato errorDato = new ErrorDato(column.getLabel(), column.getDatatype());
                getMensajeError().addErrorDato(errorDato);
                param = (Integer.valueOf(String.valueOf(column.getValue())));

                /*
                 if (column.getName().equals("folio")) {
                 folio = param;
                 }*/
            } finally {
                //params.add(column.getValue());
                if (column.getName().equals("folio")) {
                    folio = param;
                }
                params.add(param);

            }
        }
        params.add(folio);
        return params.toArray();
    }

    public String toJson() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        for (ColumnDTO column : columns) {
            json.append(",");
            json.append("\"").append(column.getName()).append("\"");
            json.append(":");
            json.append("\"").append(column.getValue()).append("\"");
        }
        json.append("}");

        return json.toString().replace("{,", "{");
    }

    /*
     public JSONObject toJsonObject() throws JSONException {
     JSONObject object = new JSONObject();
     for (ColumnDTO column : columns) {
     object.put(column.getName(), column.getValue());
     }
     return object;
     }*/
    public String getFieldValue() {
        StringBuilder result = new StringBuilder();

        result.append("{");

        for (ColumnDTO column : columns) {
            result.append("\"").append(column.getName()).append("\"");
            result.append(":");
            result.append("\"").append(String.valueOf(column.getValue())).append("\"");
            result.append(",");
        }
        result.append("}");

        String resultString = result.toString();
        resultString = resultString.replace(",}", "}");
        return resultString;
    }

    public String getFieldValueReport() {
        StringBuilder result = new StringBuilder();

        result.append("{");

        for (ColumnDTO column : columns) {
            if (!column.getDatatype().equals("records")) {
                result.append("\"").append(column.getName()).append("\"");
                result.append(":");
                result.append("\"").append(String.valueOf(column.getValue())).append("\"");
                result.append(",");
            } else {
                result.append("\"").append(column.getName()).append("\"");
                result.append(":");
                result.append("").append(String.valueOf(column.getValue())).append("");
                result.append(",");
            }
        }
        result.append("}");

        String resultString = result.toString();
        resultString = resultString.replace(",}", "}");
        return resultString;
    }

    public String getCSVValue() {
        StringBuilder result = new StringBuilder();

        //String outputResult = "xxxx, yyyy, zzzz, aaaa, bbbb, ccccc, dddd, eeee, ffff, gggg\n";
        for (ColumnDTO column : columns) {
            result.append(String.valueOf(column.getValue())).append(",");
        }
        result.append("\n");

        String resultString = result.toString();
        resultString = resultString.replace(",\n", "\n");
        return resultString;
    }

    public String getLabelValue() {
        StringBuilder result = new StringBuilder();

        result.append("{");

        for (ColumnDTO column : columns) {
            result.append("\"").append("label").append("\"");
            result.append(":");
            //result.append("\"").append(String.valueOf(column.getName())).append("\"");
            result.append("\"").append(column.getFolio()).append("\"");

            result.append(",");

            result.append("\"").append("value").append("\"");
            result.append(":");
            result.append("\"").append(String.valueOf(column.getValue())).append("\"");
            result.append(",");
        }
        result.append("}");

        String resultString = result.toString();
        resultString = resultString.replace(",}", "}");
        return resultString;
    }

    public List<PuntoHash> getHash() {

        ArrayList<PuntoHash> puntos = new ArrayList<>();

        for (ColumnDTO column : columns) {
            puntos.add(new PuntoHash(column.getLabel(), String.valueOf(column.getValue())));
        }
        return puntos;
    }

    public ArrayList<ColumnDTO> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<ColumnDTO> columns) {
        this.columns = columns;
    }

    public MensajeError getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(MensajeError mensajeError) {
        this.mensajeError = mensajeError;
    }
}
