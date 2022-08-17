package com.playlist.quipux.infraestructure.drivenadapters;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PlayListDataRepository
        extends CrudRepository<PlayListData, Long>, QueryByExampleExecutor<PlayListData> {
}
