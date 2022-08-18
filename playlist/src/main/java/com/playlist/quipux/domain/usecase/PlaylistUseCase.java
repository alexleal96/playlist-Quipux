package com.playlist.quipux.domain.usecase;

import com.playlist.quipux.domain.model.exceptions.WebClientException;
import com.playlist.quipux.domain.model.playlist.*;
import com.playlist.quipux.domain.model.requestwebclient.WebClientService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@RequiredArgsConstructor
public class PlaylistUseCase {

    private final PlaylistService repository;
    private final WebClientService webClientService;

    public Playlist savePlayList(Playlist playlist){
        Playlist request = new Playlist();
        request.setNombre(playlist.nombre.toLowerCase(Locale.ROOT));
        request.setDescripcion(playlist.descripcion);

        List<Cancion> cancion = new ArrayList<>();

        playlist.getCanciones().forEach(result -> {
            Cancion song = Cancion.builder()
                    .playlist(request)
                    .titulo(result.titulo)
                    .artista(result.artista)
                    .album(result.album)
                    .anno(result.anno)
                    .genero(result.genero)
                    .build();
            cancion.add(song);
        });

        request.setCanciones(cancion);

       return repository.save(request);
    }

    public List<Playlist> getPlayList() {
        return repository.getPlayList();
    }

    public Playlist getPlayListDetail(String listName) {
        return  repository.getPlayListDetail(listName);
    }

    public void deletePlayList(Integer id){
         repository.deletePlayList(id);
    }

    public GenerosSpotify getGenresMusicalSpotify(FilterSpotify filterSpotify, String token) throws WebClientException {
       return webClientService.getGenresMusicalSpotify(filterSpotify, token);
    }
}
