package org.probosque.model;

import java.util.ArrayList;

public class MensajeError {

    private ArrayList<ErrorDato> errores;
    
    public MensajeError(){
        errores = new ArrayList<>();
    }
    
    public void addErrorDato(ErrorDato errorDato){
        errores.add(errorDato);
    }
    
    public boolean hasErrors(){
        if(errores.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
    
    public String getMensaje(){
        String mensaje= "";
        for(ErrorDato error: errores){
            switch(error.getDataType()){
                case "string":case "alphanumeric":
                    if(error.getColumnName().equals("Folio")){
                        //mensaje = "El campo folio no debe estar nulo, seleccione primero el Año y la Región\n";
                        mensaje = "Debe seleccionar el año y la región para generar un folio\n";
                    }
                    else{
                        mensaje = "El campo " + error.getColumnName() + " debe ser texto\n";
                    }
                    break;
                case "real":
                    mensaje = "El campo " + error.getColumnName() + " debe ser númerico\n";
                    break;
                case "numeric":
                    if (error.getColumnName().equals("longitud")) {
                        mensaje = "La longitud debe estar compuesta de 6 números\n";
                    } else {
                        if (error.getColumnName().equals("latitud")) {
                            mensaje = "La latitud debe estar compuesta de 7 números\n";
                        } else {
                            mensaje = "El campo " + error.getColumnName() + " debe ser númerico\n";
                        }
                    }
                    break;
                case "date":
                    mensaje = "El campo " + error.getColumnName() + " debe ser una fecha con el siguiente formato: DD/MM/YYYY\n";
                    mensaje += "Ejemplo: 10 Diciembre de 2015 debe ser: 10/12/2015\n";
                    break;
                case "list":
                    break;
            }
        }
        return mensaje;
    }
    
    public ArrayList<ErrorDato> getErrores() {
        return errores;
    }

    public void setErrores(ArrayList<ErrorDato> errores) {
        this.errores = errores;
    }
}