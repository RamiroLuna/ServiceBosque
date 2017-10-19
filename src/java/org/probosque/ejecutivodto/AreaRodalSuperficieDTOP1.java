package org.probosque.ejecutivodto;

public class AreaRodalSuperficieDTOP1 {

    public AreaRodalSuperficieDTOP1() {
    }

    public AreaRodalSuperficieDTOP1(String area_corta, String rodal, String superficie) {
        this.area_corta = area_corta;
        this.rodal = rodal;
        this.superficie = superficie;
    }
public String area_corta;
public String rodal;
public String superficie;

    public String getArea_corta() {
        return area_corta;
    }

    public void setArea_corta(String area_corta) {
        this.area_corta = area_corta;
    }

    public String getRodal() {
        return rodal;
    }

    public void setRodal(String rodal) {
        this.rodal = rodal;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }
}
