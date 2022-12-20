package com.fjern.common.exception;

public class MyConstraintViolationException extends RuntimeException{

    public MyConstraintViolationException() {
        super();
    }

    public MyConstraintViolationException(String message) {
        super(message);
    }

    public MyConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyConstraintViolationException(Throwable cause) {
        super(cause);
    }
}
