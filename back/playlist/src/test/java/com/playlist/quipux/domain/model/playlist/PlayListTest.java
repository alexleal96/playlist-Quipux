package com.playlist.quipux.domain.model.playlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayListTest {

    @Test
    void test() {
        Playlist model = new Playlist();

        Assertions.assertNotNull(model);
    }
}
