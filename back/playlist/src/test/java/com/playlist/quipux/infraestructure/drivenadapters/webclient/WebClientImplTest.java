package com.playlist.quipux.infraestructure.drivenadapters.webclient;

import com.playlist.quipux.domain.model.exceptions.WebClientException;
import com.playlist.quipux.domain.model.playlist.FilterSpotify;
import com.playlist.quipux.domain.model.playlist.GenerosSpotify;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.net.ssl.SSLException;
import java.util.ArrayList;
import java.util.List;

public class WebClientImplTest {

    private WebClientImpl webClientImpl;

    private WebClientSpotify webClientSpotify;

    @BeforeEach
    public void setup() throws SSLException {
        webClientSpotify = Mockito.mock(WebClientSpotify.class);
        webClientImpl = new WebClientImpl("https://api.spotify.com/v1/browse/", "host");
        webClientImpl.setWebClient(webClientSpotify);
    }

    @Test
    void getGenresMusicalSpotifyTest() throws WebClientException {
        FilterSpotify filterSpotify = FilterSpotify.builder().build();

        Mockito.when(webClientSpotify.get(Mockito.anyString(), Mockito.anyString(),
                Mockito.eq(GenerosSpotify.class), Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Mockito.mock(GenerosSpotify.class));

        GenerosSpotify result = webClientImpl.getGenresMusicalSpotify(filterSpotify, "token");

        Assertions.assertNull(result);
    }
}
