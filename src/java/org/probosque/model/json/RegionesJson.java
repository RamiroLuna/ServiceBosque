package org.probosque.model.json;

import java.util.List;
import org.probosque.dto.RegionDTO;

public class RegionesJson {
    
    private List<RegionDTO> features;

    public List<RegionDTO> getFeatures() {
        return features;
    }

    public void setFeatures(List<RegionDTO> features) {
        this.features = features;
    }

}
