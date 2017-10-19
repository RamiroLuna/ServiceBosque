package org.probosque.dto;


public class ListaTemasAdmin {
        
    String tema_descripcion;

    public ListaTemasAdmin() {
    }
    String tema_programa;
    String tema_color;
    
    public ListaTemasAdmin(String tema_descripcion, String tema_programa, String tema_color) {
        this.tema_descripcion = tema_descripcion;
        this.tema_programa = tema_programa;
        this.tema_color = tema_color;
    }

    public String getTema_descripcion() {
        return tema_descripcion;
    }

    public void setTema_descripcion(String tema_descripcion) {
        this.tema_descripcion = tema_descripcion;
    }

    public String getTema_programa() {
        return tema_programa;
    }

    public void setTema_programa(String tema_programa) {
        this.tema_programa = tema_programa;
    }

    public String getTema_color() {
        return tema_color;
    }

    public void setTema_color(String tema_color) {
        this.tema_color = tema_color;
    }
    
}
