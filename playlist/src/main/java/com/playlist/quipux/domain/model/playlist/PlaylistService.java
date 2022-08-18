package com.playlist.quipux.domain.model.playlist;
import java.util.List;

public interface PlaylistService {

    Playlist save(Playlist playlist);

    List<Playlist> getPlayList();

    Playlist getPlayListDetail(String listName);

    void deletePlayList(Integer id);

}
