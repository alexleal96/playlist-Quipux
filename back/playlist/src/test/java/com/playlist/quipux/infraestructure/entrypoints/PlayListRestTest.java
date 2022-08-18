package com.playlist.quipux.infraestructure.entrypoints;

import com.playlist.quipux.domain.model.exceptions.WebClientException;
import com.playlist.quipux.domain.model.playlist.FilterSpotify;
import com.playlist.quipux.domain.model.playlist.GenerosSpotify;
import com.playlist.quipux.domain.model.playlist.Playlist;
import com.playlist.quipux.domain.usecase.PlaylistUseCase;
import com.playlist.quipux.infraestructure.drivenadapters.PlayListData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

public class PlayListRestTest {

    @Spy
    @InjectMocks
    private PlayListRest playListRest;

    @Mock
    private PlaylistUseCase playlistUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveRequestsInventoryTest() {
        Mockito.when(playlistUseCase.savePlayList(Mockito.any()))
                .thenReturn(Playlist.builder().build());

        ResponseEntity<?> result = playListRest
                .playListAdd(Playlist.builder().nombre("Lista 1").build());

        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void getPlaylistsTest() {
        Mockito.when(playlistUseCase.getPlayList())
                .thenReturn(Arrays.asList(Playlist.builder().id(1).build()));

        List<Playlist> result = playListRest.getPlaylists();

        Assertions.assertNotNull(result);
    }

    @Test
    void playListDetailTest() {
        Mockito.when(playlistUseCase.getPlayListDetail(Mockito.anyString()))
                .thenReturn(Playlist.builder().build());

        ResponseEntity<?> result = playListRest
                .playListDetail("Play list 1");

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void deletePlayListTest() {
        Mockito.when(playlistUseCase.getPlayListDetail(Mockito.anyString()))
                .thenReturn(Playlist.builder().build());


        ResponseEntity<?> result = playListRest
                .deletePlayList("Play list 1");

        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void getGenresMusicalTest() throws WebClientException {
        FilterSpotify filterSpotify = FilterSpotify.builder().build();

        Mockito.when(playlistUseCase.getGenresMusicalSpotify(Mockito.any(), Mockito.anyString()))
                .thenReturn(GenerosSpotify.builder().build());


        GenerosSpotify result = playListRest
                .getGenresMusical(filterSpotify, "token");

        Assertions.assertNotNull(result);
    }
}
