package com.playlist.quipux.domain.usecase;

import com.playlist.quipux.domain.model.exceptions.WebClientException;
import com.playlist.quipux.domain.model.playlist.*;
import com.playlist.quipux.domain.model.requestwebclient.WebClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

public class PlaylistUseCaseTest {

    @InjectMocks
    @Spy
    private PlaylistUseCase playlistUseCase;

    @Mock
    private PlaylistService playlistService;

    @Mock
    public WebClientService webClientService;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveTest() {
        List<Cancion> cancionList = new ArrayList<>();
        Cancion cancion = Cancion.builder().build();
        cancionList.add(cancion);
        Playlist playlist = Playlist.builder()
                .nombre("Playlist 1")
                .descripcion("Lista de reproduccion 1")
                .canciones(cancionList).build();
        Mockito.when(playlistService.save(Mockito.any())).thenReturn(Playlist.builder().build());

        Playlist result = playlistUseCase.savePlayList(playlist);

        Assertions.assertNotNull(result);
    }

    @Test
    void getPlayListTest() {

        Mockito.when(playlistService.getPlayList()).thenReturn(new ArrayList<>());

        List<Playlist> result = playlistUseCase.getPlayList();

        Assertions.assertNotNull(result);
    }

    @Test
    void getPlayListDetailTest() {

        Mockito.when(playlistService.getPlayListDetail(Mockito.anyString())).thenReturn(Playlist.builder().build());

        Playlist result = playlistUseCase.getPlayListDetail("Playlist 1");

        Assertions.assertNotNull(result);
    }

    @Test
    void deleteInventoryRequestsTest() {
        playlistUseCase.deletePlayList(1);

        Mockito.verify(playlistService, Mockito.times(1))
                .deletePlayList(Mockito.anyInt());

    }

    @Test
    void getGenresMusicalSpotifyTest() throws WebClientException {

        FilterSpotify filterSpotify = FilterSpotify.builder().build();

        Mockito.when(webClientService.getGenresMusicalSpotify(Mockito.any(), Mockito.anyString()))
                .thenReturn(GenerosSpotify.builder().build());

        GenerosSpotify result = playlistUseCase.getGenresMusicalSpotify(filterSpotify, "");

        Assertions.assertNotNull(result);
    }
}
