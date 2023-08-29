package com.jmarcosfmg.rickandmorty.adapter.output.database.location;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationDatabaseRepository extends JpaRepository<LocationEntity, Integer> {

    Page<LocationEntity> findAllByIdIn(List<Integer> id, Pageable pageable);
    

}
