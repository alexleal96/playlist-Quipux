package com.playlist.quipux.infraestructure.drivenadapters.webclient;


import com.playlist.quipux.domain.model.exceptions.WebClientException;
import com.playlist.quipux.domain.model.playlist.GenerosSpotify;
import com.playlist.quipux.domain.model.playlist.FilterSpotify;
import com.playlist.quipux.domain.model.requestwebclient.WebClientService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLException;

@Slf4j
public class WebClientImpl implements WebClientService {

    @Setter
    private WebClientSpotify webClient;

    public WebClientImpl(String baseUrl, String origin) throws SSLException {
        this.webClient = new WebClientSpotify(baseUrl, origin);
    }

    @Override
    public GenerosSpotify getGenresMusicalSpotify(FilterSpotify filterSpotify, String token) throws WebClientException {
        var uri = "categories?country={country}&locale={locale}&limit={limit}&offset={offset}";

        return webClient.get(uri, token,  GenerosSpotify.class, filterSpotify.getCountry(),
                filterSpotify.getLocale(), filterSpotify.getLimit(), filterSpotify.getOffset());
    }
}
