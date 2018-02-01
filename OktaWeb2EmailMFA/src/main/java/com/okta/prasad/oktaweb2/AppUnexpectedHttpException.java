package com.okta.prasad.oktaweb2;

public class AppUnexpectedHttpException extends RuntimeException
{       
    private static final long serialVersionUID = -6252766749487342137L;    
    public AppUnexpectedHttpException(String message) {
        super(message);
    }    
}
