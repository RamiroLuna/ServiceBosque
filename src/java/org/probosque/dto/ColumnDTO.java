package org.probosque.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.probosque.dao.SQL;

/**
 *
 * @author admin
 */
public class ColumnDTO{

    private String folio;
    private String type;
    private String label;
    @SerializedName("field")
    private String name;
    private String datatype;
    private String listname;
    @SerializedName("maxLength")
    private int max_length;
    @SerializedName("minLength")
    private int min_length;
    private boolean searchable;
    private boolean editable;
    private boolean privacy;
    private String records;
    private ListDTO list;
    private List<ColumnDTO> fields;
    private Object value;
    private Object filter;
    private boolean onlyselect;



    public String getSQLSelect() {
        String sql = "";
        if (!this.getDatatype().equals("records")) {
            sql += this.getName() + ",";
        }
        return sql;
    }

    public String getSQLSelectEdit() {
        String sql = "";
        if (!this.getDatatype().equals("records")) {
            sql += this.getName() + ",";
        }
        return sql;
    }

    public String getSQLSelectJoin() {
        String sql = "";
        if (!this.getDatatype().equals("records")) {
            if (this.getDatatype().equals("list")) {
                if(this.getListname().contains("catalogos.cup")) {
                    sql += this.getName() + ",";
                }
                else{
                    //sql += " LEFT JOIN " + this.getListnameCustom() + " join" + this.getName() + " ON " + tableName + "." + this.getName() + " = CAST(" + "join" + this.getName() + ".id AS TEXT)";
                    sql += "join" + this.getName() + ".descripcion AS " + this.getName() + ",";
                }
                
                
                //sql += "join" + this.getName() + ".descripcion AS " + this.getName() + ",";
            } else {
                sql += this.getName() + ",";
            }
        }
        return sql;
    }

    public String getSQLSelectWithComillas() {
        String sql = "";
        if (!this.getDatatype().equals("records")) {
            sql += "\"" + this.getName() + "\",";
        }
        return sql;
    }

    public String getSQLJoin(UserDTO user, String activity) {
        String sql = "";
        String tableName = SQL.getTableMain(user, activity);
        if (this.getDatatype().equals("list")) {
            //sql += " LEFT JOIN " + column.getListname()+" ON programa"+program+".principal."+column.getName()+" = "+column.getListname()+".id";
            //sql += " LEFT JOIN " + column.getListname() + " ON " + tableName + "." + column.getName() + " = " + column.getListname() + ".id";

            if (this.getListname().contains("catalogos.localidad")) {
                
                /*
                if (this.getListname().contains(";")) {
                    String[] tables = this.getListname().split(";");
                    tableName = tables[1];
                    
                    sql += " LEFT JOIN " + tables[1] + " join" + this.getName() + " ON " + tableName + "." + this.getName() + " = CAST(" + "join" + this.getName() + ".id AS TEXT)";
                    String extra = " AND " + tableName + "." + this.getName() + " = CAST(" + "join" + this.getName() + ".id AS TEXT)";
                    extra = extra.replaceFirst("localidad =", "municipio =");
                    extra = extra.replaceFirst("localidad.id AS", "localidad.id_municipio AS");
                    sql += extra;
                    
                    //sql += " LEFT JOIN " + tables[1] + " join" + this.getName() + " ON " + tableName + "." + this.getName() + " = CAST(" + "join" + this.getName() + ".id AS TEXT)";                
                }else{
                    //sql += " LEFT JOIN " + this.getListname() + " join" + this.getName() + " ON " + tableName + "." + this.getName() + " = CAST(" + "join" + this.getName() + ".id AS TEXT)";
                    
                    sql += " LEFT JOIN " + this.getListname() + " join" + this.getName() + " ON " + tableName + "." + this.getName() + " = CAST(" + "join" + this.getName() + ".id AS TEXT)";
                    String extra = " AND " + tableName + "." + this.getName() + " = CAST(" + "join" + this.getName() + ".id AS TEXT)";
                    extra = extra.replaceFirst("localidad =", "municipio =");
                    extra = extra.replaceFirst("localidad.id AS", "localidad.id_municipio AS");
                    sql += extra;
                
                }*/
                
                
                sql += " LEFT JOIN " + this.getListnameCustom() + " join" + this.getName() + " ON " + tableName + "." + this.getName() + " = " + "join" + this.getName() + ".id ";
                String extra = " AND " + tableName + "." + this.getName() + " = " + "join" + this.getName() + ".id ";
                extra = extra.replaceFirst("localidad =", "municipio =");
                extra = extra.replaceFirst("localidad.id AS", "localidad.id_municipio AS");
                sql += extra;
            } else {
                
                if(this.getListname().contains("catalogos.cup")) {

                }
                else{
                    sql += " LEFT JOIN " + this.getListnameCustom() + " join" + this.getName() + " ON " + tableName + "." + this.getName() + " = " + "join" + this.getName() + ".id ";
                }


                
                /*
                if (this.getListname().contains(";")) {
                    String[] tables = this.getListname().split(";");
                    tableName = tables[1];
                    sql += " LEFT JOIN " + tables[1] + " join" + this.getName() + " ON " + tableName + "." + this.getName() + " = CAST(" + "join" + this.getName() + ".id AS TEXT)";                
                }else{
                    sql += " LEFT JOIN " + this.getListname() + " join" + this.getName() + " ON " + tableName + "." + this.getName() + " = CAST(" + "join" + this.getName() + ".id AS TEXT)";
                }*/
                //
                
                
            }

            //JOIN library.book b1 ON b1.category_id = category.id
            //JOIN library.book b2 ON b2.id = p.book_id
            //LEFT JOIN catalogos.participantecontrolcombate ON principal.metodocombate = catalogos.participantecontrolcombate.id
            //LEFT JOIN catalogos.participantecontrolcombate ON principal.metodocombate = catalogos.participantecontrolcombate.id
            //"metodo_combate";"list";""
        }

        //sql = sql.substring(0, sql.lastIndexOf(","));
        return sql;
    }

    public String getSQLSelectJoinWithRecord() {
        String sql = "";
        if (!this.getDatatype().equals("records")) {
            if (this.getDatatype().equals("list")) {
                
                if(this.getListname().contains("catalogos.cup")) {
                    sql += this.getName() + ",";
                }
                else{
                    //sql += " LEFT JOIN " + this.getListnameCustom() + " join" + this.getName() + " ON " + tableName + "." + this.getName() + " = CAST(" + "join" + this.getName() + ".id AS TEXT)";
                    sql += "join" + this.getName() + ".descripcion AS " + this.getName() + ",";
                }
                //sql += this.getListname() + ".descripcion AS " + this.getName() + ",";
                //sql += "join" + this.getName() + ".descripcion AS " + this.getName() + ",";
            } else {
                sql += this.getName() + ",";
            }
        } else {
            //sql += "general.countsubrecords('" + this.getName().replace("formularios.", "") + "','') AS "+ "\"" + this.getName() + "\"" + ",";
            sql += "general.countsubrecords('" + this.getName().replace("formularios.", "") + "',folio,'" + this.getLabel() + "') AS " + this.getName().replaceAll("[.]", "") + ",";
            //column.getName()getListname();
            //general.countsubrecords('especies','AM0002001')
        }
        return sql;
    }
    
    public String getSQLSelectJoinWithRecord2() {
        String sql = "";
        if (!this.getDatatype().equals("records")) {
            if (this.getDatatype().equals("list")) {
                
                if(this.getListname().contains("catalogos.cup")) {
                    sql += this.getName() + ",";
                }
                else{
                    //sql += " LEFT JOIN " + this.getListnameCustom() + " join" + this.getName() + " ON " + tableName + "." + this.getName() + " = CAST(" + "join" + this.getName() + ".id AS TEXT)";
                    sql += "join" + this.getName() + ".descripcion" + ",";
                }
                //sql += this.getListname() + ".descripcion AS " + this.getName() + ",";
                //sql += "join" + this.getName() + ".descripcion AS " + this.getName() + ",";
            } else {
                sql += this.getName() + ",";
            }
        } 
        return sql;
    }

    public String getListname() {
        /*
        if (this.listname!= null && this.listname.contains(";")) {
            String[] vars = this.listname.split(";");
            return vars[1];
        } else {
            return listname;
        }*/
        return listname;
    }
    
    public String getListnameCustom() {
        if (this.listname != null && this.listname.contains(";")) {
            String[] vars = this.listname.split(";");
            return vars[1];
        } else {
            return listname;
        }
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public void setListname(String listname) {
        this.listname = listname;
    }

    public int getMax_length() {
        return max_length;
    }

    public void setMax_length(int max_length) {
        this.max_length = max_length;
    }

    public int getMin_length() {
        return min_length;
    }

    public void setMin_length(int min_length) {
        this.min_length = min_length;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public ListDTO getList() {
        return list;
    }

    public void setList(ListDTO list) {
        this.list = list;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Object getFilter() {
        return filter;
    }

    public void setFilter(Object filter) {
        this.filter = filter;
    }

    public List<ColumnDTO> getFields() {
        return fields;
    }

    public void setFields(List<ColumnDTO> fields) {
        this.fields = fields;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public boolean isOnlyselect() {
        return onlyselect;
    }

    public void setOnlyselect(boolean onlyselect) {
        this.onlyselect = onlyselect;
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
}
