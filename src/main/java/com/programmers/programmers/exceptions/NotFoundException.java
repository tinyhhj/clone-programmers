package com.programmers.programmers.exceptions;


public class NotFoundException extends RuntimeException{
    private String code;
    public NotFoundException(String code, String message) {
        super(message);
        setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
