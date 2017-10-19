package org.probosque.model.json;

import java.util.List;
import org.probosque.dto.LayerDTO;
import org.probosque.dto.ThemeDTO;

public class DataLayersJson {

    private List<LayerDTO> layers;
    private List<ThemeDTO> themes;

    public List<LayerDTO> getLayers() {
        return layers;
    }

    public void setLayers(List<LayerDTO> layers) {
        this.layers = layers;
    }

    public List<ThemeDTO> getThemes() {
        return themes;
    }

    public void setThemes(List<ThemeDTO> themes) {
        this.themes = themes;
    }
}
