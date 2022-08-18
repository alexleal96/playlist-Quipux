package com.playlist.quipux.domain.model.playlist;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class GenerosSpotify {

    private Categoria categories;
}
