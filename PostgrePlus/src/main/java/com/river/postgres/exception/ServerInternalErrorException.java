package com.river.postgres.exception;


public class ServerInternalErrorException extends RuntimeException {

    private static final long serialVersionUID = -1168987160778410810L;

    @Override
    public String getMessage() {
        return "The server encountered an internal error. Please retry the request.";
    }
}
