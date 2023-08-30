package com.jmarcosfmg.rickandmorty.adapter.output.database.location;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationDatabaseRepository extends JpaRepository<LocationEntity, Integer> {

    Page<LocationEntity> findAllByIdIn(List<Integer> id, Pageable pageable);

    List<LocationEntity> findAllByIdIn(List<Integer> id);

    

}
