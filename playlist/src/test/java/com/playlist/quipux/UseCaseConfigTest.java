package com.playlist.quipux;

import com.playlist.quipux.domain.model.playlist.PlaylistService;
import com.playlist.quipux.domain.model.requestwebclient.WebClientService;
import com.playlist.quipux.domain.usecase.PlaylistUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;

public class UseCaseConfigTest {

    @InjectMocks
    private UseCaseConfig useCaseConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void initClassTest() {
        UseCaseConfig useCaseConfigClass = new UseCaseConfig();

        Assertions.assertNotNull(useCaseConfigClass);
    }

    @Test
    void objectMapperTest() {
        ObjectMapper objectMapper = useCaseConfig.objectMapper();

        Assertions.assertNotNull(objectMapper);
    }

    @Test
    void playlistUseCaseTest() {
        PlaylistService playlistRepository = Mockito.mock(PlaylistService.class);
        WebClientService webClientService = Mockito.mock(WebClientService.class);

        PlaylistUseCase playlistUseCase = useCaseConfig.playlistUseCase(
                playlistRepository, webClientService);

        Assertions.assertNotNull(playlistUseCase);
    }
}
