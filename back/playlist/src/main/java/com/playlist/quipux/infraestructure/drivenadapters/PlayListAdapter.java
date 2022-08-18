package com.playlist.quipux.infraestructure.drivenadapters;
import com.playlist.quipux.domain.model.playlist.Playlist;
import com.playlist.quipux.domain.model.playlist.PlaylistService;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayListAdapter  extends AdapterOperationsJpa<Playlist, PlayListData, Integer,
        PlayListDataRepository>
          implements PlaylistService {


    public PlayListAdapter(PlayListDataRepository repository,
                                                ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Playlist.class));
    }


    @Override
    public List<Playlist> getPlayList() {
        return toList(repository.findAll());
    }

    @Override
    public Playlist getPlayListDetail(String listName) {
        return toEntity(repository.findByNombre(listName));
    }

    @Override
    public void deletePlayList(Integer id) {
       repository.deleteById(id);
    }
}
