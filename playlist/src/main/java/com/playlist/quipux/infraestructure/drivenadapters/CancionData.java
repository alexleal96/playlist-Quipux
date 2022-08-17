package com.playlist.quipux.infraestructure.drivenadapters;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor()
@NoArgsConstructor()
@Table( name = "cancion")
@Builder(toBuilder = true)
public class CancionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long id;
    public String titulo;
    public String artista;
    public String album;
    public String anno;
    public String genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    private PlayListData playlist;
}
