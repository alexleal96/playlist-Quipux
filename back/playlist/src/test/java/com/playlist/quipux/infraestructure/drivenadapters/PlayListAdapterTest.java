package com.playlist.quipux.infraestructure.drivenadapters;

import com.playlist.quipux.domain.model.playlist.Playlist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.reactivecommons.utils.ObjectMapperImp;

import java.util.Arrays;
import java.util.List;

public class PlayListAdapterTest {

    @Spy
    @InjectMocks
    private PlayListAdapter playListAdapter;

    @Mock
    private PlayListDataRepository repository;

    @Spy
    private ObjectMapperImp objectMapperImp;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        Assertions.assertNotNull(playListAdapter);
    }

    @Test
    void getPlayListTest() {
        Mockito.when(repository.findAll())
                .thenReturn(Arrays.asList(PlayListData.builder().id(1).build()));

        List<Playlist> result = playListAdapter.getPlayList();

        Assertions.assertNotNull(result);

    }

    @Test
    void getPlayListDetailTest() {
        Mockito.when(repository.findByNombre(Mockito.anyString()))
                .thenReturn(PlayListData.builder().id(1).build());

        Playlist result = playListAdapter.getPlayListDetail("Lista 1");

        Assertions.assertNotNull(result);

    }

    @Test
    void deletePlayList() {
        playListAdapter.deletePlayList(Mockito.anyInt());

        Mockito.verify(repository, Mockito.times(1)).deleteById(Mockito.anyInt());
    }
}
