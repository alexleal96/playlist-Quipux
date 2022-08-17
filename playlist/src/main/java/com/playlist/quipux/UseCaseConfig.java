package com.playlist.quipux;

import com.playlist.quipux.domain.model.gateways.PlaylistService;
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
    public PlaylistUseCase playlistUseCase(PlaylistService playlistRepository) {
        return new PlaylistUseCase(playlistRepository);
    }
}
