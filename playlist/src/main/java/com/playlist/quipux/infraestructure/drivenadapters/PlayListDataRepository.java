package com.playlist.quipux.infraestructure.drivenadapters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayListDataRepository
        extends CrudRepository<PlayListData, Integer>, QueryByExampleExecutor<PlayListData>,
        JpaSpecificationExecutor<PlayListData> {


       @Query(value = "SELECT new PlayListData(p) FROM PlayListData p")
       List<PlayListData> findAll();

       @Query(value = "SELECT new PlayListData(p) FROM PlayListData p " +
               "WHERE p.nombre =:listName ")
       PlayListData findByNombre(@Param("listName") String listName);
}
