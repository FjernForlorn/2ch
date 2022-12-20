package com.fjern.common.exception;

public final class MyBadRequestException extends RuntimeException{
    public MyBadRequestException(){super();}

    public MyBadRequestException(String message) {
        super(message);
    }

    public MyBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyBadRequestException(Throwable cause) {
        super(cause);
    }
}
