package org.probosque.model;

import java.util.ArrayList;
import org.probosque.dto.ColumnDTO;

/**
 *
 * @author admin
 */
public class PDFcontent {

    private ArrayList<ColumnDTO> columns; 
    private ArrayList<PDFrow> rows;
    
    public PDFcontent(ArrayList<ColumnDTO> columns, ArrayList<PDFrow> rows){
        this.columns = columns;
        this.rows = rows;
    }

    public ArrayList<ColumnDTO> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<ColumnDTO> columns) {
        this.columns = columns;
    }

    public ArrayList<PDFrow> getRows() {
        return rows;
    }

    public void setRows(ArrayList<PDFrow> rows) {
        this.rows = rows;
    }
}