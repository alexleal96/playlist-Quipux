package com.playlist.quipux;


import com.playlist.quipux.domain.model.requestwebclient.WebClientService;
import com.playlist.quipux.infraestructure.drivenadapters.webclient.WebClientImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;

@Slf4j
@Configuration
public class WebClientsConfig {

    @Bean
    public WebClientService webClient() throws SSLException {
        return new WebClientImpl("https://api.spotify.com/v1/browse/", "");
    }

}
