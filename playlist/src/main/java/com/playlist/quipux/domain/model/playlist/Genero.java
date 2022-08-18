package com.playlist.quipux.domain.model.playlist;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Genero {

    private String id;
    private String name;
}
