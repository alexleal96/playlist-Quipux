package com.playlist.quipux.infraestructure.entrypoints;


import com.playlist.quipux.domain.model.Playlist;
import com.playlist.quipux.infraestructure.entrypoints.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/playlist")
@RequiredArgsConstructor
@Validated
@RestController
public class PlayListRest {


//    @PostMapping(path = "/lists")
//    public ResponseEntity<Response> playListAdd(@RequestBody Playlist playlist){
//        if (playlist.nombre == null) {
//
//            return new ResponseEntity<>(Response.builder().message("El nombre de la lista no es valido").build(),
//                    HttpStatus.BAD_REQUEST);
//        } else {
//            return new ResponseEntity<>(Response.builder().message("Playlist agregada satisfactoriamente").build(),
//                    HttpStatus.CREATED);
//        }
//    }

    @GetMapping(path = "/lists")
    public List<Playlist> getPlaylists() {
        List<Playlist> list = new ArrayList<>();
        return list;
    }

//    @GetMapping(path = "/lists/{listName}")
//    public ResponseEntity<Response> playListDetail(@PathVariable(name = "listName") String listName) {
//        Playlist playlist = new Playlist();
//        if (playlist.nombre == null) {
//
//            return new ResponseEntity<>(Response.builder().message("La lista no existe").build(),
//                    HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(Response.builder().message("").build(),
//                    HttpStatus.OK, Response.builder().playlist(playlist).build());
//        }
//    }

    @DeleteMapping(path = "/lists/{listName}")
    public ResponseEntity<Response> deletePlayList(@PathVariable(name = "listName") String listName) {
        if (1 == 1) {

            return new ResponseEntity<>(Response.builder().message("La lista no existe").build(),
                    HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(Response.builder().message("Lista eliminada satisfactoriamente").build(),
                    HttpStatus.NO_CONTENT);
        }
    }
}
