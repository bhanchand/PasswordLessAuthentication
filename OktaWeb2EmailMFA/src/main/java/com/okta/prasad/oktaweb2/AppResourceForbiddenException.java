package com.okta.prasad.oktaweb2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AppResourceForbiddenException extends RuntimeException
{       
    private static final long serialVersionUID = -6252766749487342137L;    
    public AppResourceForbiddenException(String message) {
        super(message);
    }    
}
