package com.playlist.quipux.domain.model.playlist;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    private String href;
    private List<Genero> items;
}
