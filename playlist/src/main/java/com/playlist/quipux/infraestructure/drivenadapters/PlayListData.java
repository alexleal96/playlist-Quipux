package com.playlist.quipux.infraestructure.drivenadapters;

import com.playlist.quipux.domain.model.Cancion;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor()
@NoArgsConstructor()
@Table( name = "playlist")
@Builder(toBuilder = true)
public class PlayListData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long id;
    public String nombre;
    public String descripcion;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CancionData> canciones;

}
