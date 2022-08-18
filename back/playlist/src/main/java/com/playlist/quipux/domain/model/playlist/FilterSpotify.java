package com.playlist.quipux.domain.model.playlist;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FilterSpotify {

    private String country;
    private String locale;
    private String limit;
    private String offset;
}
