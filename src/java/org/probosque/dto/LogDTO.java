/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dto;

/**
 *
 * @author TOSHIBA-L55
 */
public class LogDTO {
   private String idactividad;
   private String fecha;
   private String usuario;
   private String nombre;
   private int programa;
   private String accion;
   private String antes;
   private String despues;

 
  
 public LogDTO(){
        
    }
    
    public LogDTO( String idactividad, String fecha, String usuario, String nombre, int programa, String accion, String antes,String despues){
        this.idactividad = idactividad;
        this.fecha = fecha;
        this.usuario = usuario;
        this.nombre=nombre;
        this.programa=programa;
        this.accion=accion;
        this.antes=antes;
        this.despues=despues;
        
    }
    
    public String getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(String idactividad) {
        this.idactividad= idactividad;
    }

    public String getfecha() {
        return fecha;
    }

    public void setfecha(String fecha) {
        this.fecha = fecha;
    }
    public String getusuario(){
    return usuario;
    }
    public void setusuario(String usuario){
        this.usuario=usuario;
    }
     public String getnombre(){
    return nombre;
    }
    public void setnombre(String nombre){
        this.nombre=nombre;
    }
     public int getprograma(){
    return programa;
    }
    public void setprograma(int programa){
        this.programa=programa;
    }
     public String accion(){
    return accion;
    }
    public void setaccion(String accion){
        this.accion=accion;
    }
  
       public void setantes(String antes) {
        this.antes = antes;
    }

    public void setdespues(String despues) {
        this.despues = despues;
    }

    public String getantes() {
        return antes;
    }

    public String getdespues() {
        return despues;
    }
   
}
