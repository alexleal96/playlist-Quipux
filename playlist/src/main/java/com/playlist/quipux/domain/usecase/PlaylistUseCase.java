package com.playlist.quipux.domain.usecase;

import com.playlist.quipux.domain.model.Playlist;
import com.playlist.quipux.domain.model.gateways.PlaylistService;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class PlaylistUseCase {

    private final PlaylistService repository;

    public List<Playlist> getPlayList() {
        return repository.getPlayList();
    }
}
