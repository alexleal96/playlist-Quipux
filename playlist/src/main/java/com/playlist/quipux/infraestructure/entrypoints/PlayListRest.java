package com.playlist.quipux.infraestructure.entrypoints;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/catalog")
@RequiredArgsConstructor
@Validated
@RestController
public class PlayListRest {
}
