package org.probosque.model.json;

import java.util.List;
import org.probosque.dto.PointDTO;

public class PrediosJson {
    
    private List<PointDTO> features;

    public List<PointDTO> getFeatures() {
        return features;
    }

    public void setFeatures(List<PointDTO> features) {
        this.features = features;
    }
}