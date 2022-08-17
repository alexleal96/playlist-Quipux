package com.playlist.quipux.infraestructure.drivenadapters;
import com.playlist.quipux.domain.model.Playlist;
import com.playlist.quipux.domain.model.gateways.PlaylistService;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;;

import java.util.List;

@Repository
public class PlayListAdapter  extends AdapterOperationsJpa<Playlist, PlayListData, Long,
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
}
