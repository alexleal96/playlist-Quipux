package com.playlist.quipux.infraestructure.drivenadapters;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor()
@NoArgsConstructor()
@Table( name = "playlist")
@Builder(toBuilder = true)
public class PlayListData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String nombre;
    public String descripcion;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CancionData> canciones;

    public PlayListData(PlayListData other) {
        this.id = other.id;
        this.nombre = other.nombre;
        this.descripcion = other.descripcion;
        this.canciones = filtrarCanciones(other);
    }

    private List<CancionData> filtrarCanciones(PlayListData playListData){
        return playListData.canciones.stream()
                .map(cancionData -> {
                    CancionData other = new CancionData();
                    other.setId(cancionData.getId());
                    other.setTitulo(cancionData.getTitulo());
                    other.setArtista(cancionData.getArtista());
                    other.setAlbum(cancionData.getAlbum());
                    other.setAnno(cancionData.getAnno());
                    other.setGenero(cancionData.getGenero());
                    other.setPlaylist(filtrarPlaylist(cancionData.getPlaylist()));
                    return other;
                })
                .collect(Collectors.toList());
    }

    private PlayListData filtrarPlaylist(PlayListData playListData){
        PlayListData otherPlaylist = new PlayListData();
        otherPlaylist.setId(playListData.getId());
        otherPlaylist.setNombre(playListData.getNombre());
        otherPlaylist.setDescripcion(playListData.getDescripcion());
        return otherPlaylist;
    }
}
