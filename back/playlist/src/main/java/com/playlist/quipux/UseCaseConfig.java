package com.playlist.quipux;

import com.playlist.quipux.domain.model.playlist.PlaylistService;
import com.playlist.quipux.domain.model.requestwebclient.WebClientService;
import com.playlist.quipux.domain.usecase.PlaylistUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }

    @Bean
    public PlaylistUseCase playlistUseCase(PlaylistService playlistRepository,
                                           WebClientService webClientService) {
        return new PlaylistUseCase(playlistRepository, webClientService);
    }
}
