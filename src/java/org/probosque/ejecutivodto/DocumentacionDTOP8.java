package org.probosque.ejecutivodto;


public class DocumentacionDTOP8 {

    public DocumentacionDTOP8() {
    }

    public DocumentacionDTOP8(String documento, String numero_documento) {
        this.documento = documento;
        this.numero_documento = numero_documento;
    }

public String documento;
public String numero_documento;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }


    
}
