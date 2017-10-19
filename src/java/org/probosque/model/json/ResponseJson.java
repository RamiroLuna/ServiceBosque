package org.probosque.model.json;

public class ResponseJson {

    private String message;
    private boolean sucessfull;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSucessfull() {
        return sucessfull;
    }

    public void setSucessfull(boolean sucessfull) {
        this.sucessfull = sucessfull;
    }
}
