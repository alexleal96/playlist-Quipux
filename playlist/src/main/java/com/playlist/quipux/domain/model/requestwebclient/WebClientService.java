package com.playlist.quipux.domain.model.requestwebclient;


import com.playlist.quipux.domain.model.exceptions.WebClientException;
import com.playlist.quipux.domain.model.playlist.GenerosSpotify;
import com.playlist.quipux.domain.model.playlist.FilterSpotify;

public interface WebClientService {

     GenerosSpotify getGenresMusicalSpotify(FilterSpotify filterSpotify, String token) throws WebClientException;

}
