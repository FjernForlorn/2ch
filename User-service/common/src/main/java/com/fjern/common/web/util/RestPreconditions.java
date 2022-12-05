package com.fjern.common.web.util;

import com.fjern.common.exception.MyBadRequestException;
import com.fjern.common.exception.MyConstraintViolationException;
import com.fjern.common.exception.MyResourceNotFoundException;

public class RestPreconditions {



    public static <T> T checkNotNull(final T reference) {
        return checkNotNull(reference, null);
    }

    public static <T> T checkNotNull(final T reference, final String message) {
        if(reference == null) {
            throw new MyResourceNotFoundException(message);
        }
        return reference;
    }

    public static <T> T checkRequestElementNotNull(final T reference) {
       return checkRequestElementNotNull(reference, null);
    }

    public static <T> T checkRequestElementNotNull(final T reference, String message) {
        if (reference == null) {
            throw new MyBadRequestException(message);
        }
        return reference;
    }

    public static void checkRequestState(final boolean expression) {

        checkRequestState(expression, null);
    }

    public static void checkRequestState(final boolean expression, String message) {
        if(!expression) {
            throw new MyBadRequestException(message);
        }

    }

    public static void checkIfBadRequest(final boolean expression) {

        checkIfBadRequest(expression, null);
    }

    public static void checkIfBadRequest(final boolean expression, String message) {
        if(!expression) {
            throw new MyBadRequestException(message);
        }

    }

    public static void checkIfConstraintViolation(final boolean expression) {

        checkIfConstraintViolation(expression, null);
    }

    public static void checkIfConstraintViolation(final boolean expression, String message) {
        if(!expression) {
            throw new MyConstraintViolationException(message);
        }
    }
}
