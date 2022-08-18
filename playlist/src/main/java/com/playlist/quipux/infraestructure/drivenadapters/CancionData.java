package com.playlist.quipux.infraestructure.drivenadapters;

import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor()
@NoArgsConstructor()
@Table( name = "cancion")
@Builder(toBuilder = true)
public class CancionData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String artista;
    private String album;
    private String anno;
    private String genero;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private PlayListData playlist;
}
