package org.probosque.model.json;

import java.util.List;
import org.probosque.dto.CatalogoDTO;

public class MunicipiosJson {
    
    private List<CatalogoDTO> list;

    public List<CatalogoDTO> getList() {
        return list;
    }

    public void setList(List<CatalogoDTO> list) {
        this.list = list;
    }
}