package com.playlist.quipux.domain.model;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Cancion {

    public String titulo;
    public String artista;
    public String album;
    public String anno;
    public String genero;
}
