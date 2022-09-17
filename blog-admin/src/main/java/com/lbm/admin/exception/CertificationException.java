package com.lbm.admin.exception;

import org.springframework.security.core.AuthenticationException;

public class CertificationException extends AuthenticationException {
    public CertificationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CertificationException(String msg) {
        super(msg);
    }
}
