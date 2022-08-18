package com.playlist.quipux.domain.model.playlist;


import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {

    public Integer id;
    public String nombre;
    public String descripcion;
    public List<Cancion> canciones;


}

