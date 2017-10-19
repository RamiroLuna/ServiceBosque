
package org.probosque.ejecutivodto;

public class Oficio_fechasP1DTO {

    public Oficio_fechasP1DTO() {
    }

    public Oficio_fechasP1DTO(String fecha_expedicion, String fecha_vencimiento) {
        this.fecha_expedicion = fecha_expedicion;
        this.fecha_vencimiento = fecha_vencimiento;
    }
String fecha_expedicion;
String fecha_vencimiento;

    public String getFecha_expedicion() {
        
        return fecha_expedicion;
    }

    public void setFecha_expedicion(String fecha_expedicion) {
        this.fecha_expedicion = fecha_expedicion;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }
}
