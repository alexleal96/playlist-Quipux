package com.playlist.quipux.infraestructure.entrypoints.response;

import com.playlist.quipux.domain.model.Playlist;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Response {

    private String message;
    private Integer status;
    private Playlist playlist;
}
