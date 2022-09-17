package com.lbm.admin.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthorizeException extends AuthenticationException {
    public AuthorizeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthorizeException(String msg) {
        super(msg);
    }
}
