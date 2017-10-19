/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.model.json;

import java.util.List;
import org.probosque.dto.CatalogoDTO;
/**
 *
 * @author TOSHIBA-L55
 */
public class SubcuencaJson {
    private List<CatalogoDTO> list;

    public List<CatalogoDTO> getList() {
        return list;
    }

    public void setList(List<CatalogoDTO> list) {
        this.list = list;
    }
}
