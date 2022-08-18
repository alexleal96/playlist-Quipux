package com.playlist.quipux.domain.model.exceptions;

public class WebClientException extends Exception {

    private static final String ERROR_MESSAGE = "Ocurrió un error al comunicarse con el servidor";

    public WebClientException(Exception e) {
        super(ERROR_MESSAGE, e);
    }

    public WebClientException() {
        super(ERROR_MESSAGE);
    }
}
