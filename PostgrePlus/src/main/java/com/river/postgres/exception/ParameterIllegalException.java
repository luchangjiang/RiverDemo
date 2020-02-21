package com.river.postgres.exception;


import org.apache.commons.lang3.StringUtils;

public class ParameterIllegalException extends RuntimeException {

    private static final long serialVersionUID = 8197086462208138875L;

    @Override
    public String getMessage() {
        return "An invalid value was specified for one of the query parameters in the request URL.";
    }
}
