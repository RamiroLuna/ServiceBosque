package org.probosque.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TotalGroupDTO {

    public TotalGroupDTO() {
    }

    public TotalGroupDTO(String total, String subtotal, String columnaReferencia, String nameRef, String datatypeRef, String listRef, List<TotalGroupDTO> totalGroup) {
        this.total = total;
        this.subtotal = subtotal;
        this.columnaReferencia = columnaReferencia;
        this.nameRef = nameRef;
        this.datatypeRef = datatypeRef;
        this.listRef = listRef;
        this.totalGroup = totalGroup;
    }


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getColumnaReferencia() {
        return columnaReferencia;
    }

    public void setColumnaReferencia(String columnaReferencia) {
        this.columnaReferencia = columnaReferencia;
    }

    public String getNameRef() {
        return nameRef;
    }

    public void setNameRef(String nameRef) {
        this.nameRef = nameRef;
    }

    public String getDatatypeRef() {
        return datatypeRef;
    }

    public void setDatatypeRef(String datatypeRef) {
        this.datatypeRef = datatypeRef;
    }

    public String getListRef() {
        return listRef;
    }

    public void setListRef(String listRef) {
        this.listRef = listRef;
    }
public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TotalGroupDTO> getTotalGroup() {
        return totalGroup;
    }

    public void setTotalGroup(List<TotalGroupDTO> totalGroup) {
        this.totalGroup = totalGroup;
    }
public String total;
public String subtotal;
public String columnaReferencia;
public String nameRef;
public String datatypeRef;
public String type;
  
public String listRef;
@SerializedName("paramsTotal")
public List<TotalGroupDTO> totalGroup;


    
}
