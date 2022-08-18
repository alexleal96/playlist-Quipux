package com.playlist.quipux;

import com.playlist.quipux.domain.model.requestwebclient.WebClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javax.net.ssl.SSLException;

public class WebClientsConfigTest {

    @InjectMocks
    @Spy
    private WebClientsConfig webClientsConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mev2WebClient() throws SSLException {
        WebClientService webClient = webClientsConfig
                .webClient();

        Assertions.assertNotNull(webClient);
    }
}
