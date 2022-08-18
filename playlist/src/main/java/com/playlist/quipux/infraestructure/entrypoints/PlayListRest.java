package com.playlist.quipux.infraestructure.entrypoints;


import com.playlist.quipux.domain.model.exceptions.WebClientException;
import com.playlist.quipux.domain.model.playlist.GenerosSpotify;
import com.playlist.quipux.domain.model.playlist.FilterSpotify;
import com.playlist.quipux.domain.model.playlist.Playlist;
import com.playlist.quipux.domain.usecase.PlaylistUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/playlist")
@RequiredArgsConstructor
@Validated
@RestController
public class PlayListRest {

    private final PlaylistUseCase playlistUseCase;

    @PostMapping(path = "/lists")
    public ResponseEntity<?> playListAdd(@RequestBody Playlist playlist){
        if (playlist.nombre != null) {
            playlistUseCase.savePlayList(playlist);
            return new ResponseEntity<>(playlist, HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/lists")
    public List<Playlist> getPlaylists() {
        return playlistUseCase.getPlayList();
    }

    @GetMapping(path = "/lists/{listName}")
    public ResponseEntity<?> playListDetail(@PathVariable(name = "listName") String listName) {

            Playlist playlist = playlistUseCase.getPlayListDetail(listName.toLowerCase(Locale.ROOT));

             if (playlist != null){
                 return new ResponseEntity<>(playlist, HttpStatus.OK);
             }else{
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             }

    }

    @DeleteMapping(path = "/lists/{listName}")
    public ResponseEntity<?> deletePlayList(@PathVariable(name = "listName") String listName) {

            Playlist playlist = playlistUseCase.getPlayListDetail(listName);

            if (playlist != null){

            playlistUseCase.deletePlayList(playlist.id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/lists/genres-musical")
    public GenerosSpotify getGenresMusical(FilterSpotify filterSpotify,
                                           @RequestHeader(name = "Authorization") String token)
            throws WebClientException {

        return playlistUseCase.getGenresMusicalSpotify(filterSpotify, token);

    }

}
