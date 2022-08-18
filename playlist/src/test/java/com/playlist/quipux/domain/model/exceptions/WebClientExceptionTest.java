package com.playlist.quipux.domain.model.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WebClientExceptionTest {

    @Test
    void createWithException() {
        WebClientException exception = new WebClientException(new Exception());

        Assertions.assertNotNull(exception);
    }

    @Test
    void createWithoutException() {
        WebClientException exception = new WebClientException();

        Assertions.assertNotNull(exception);
    }
}
