package com.jmarcosfmg.rickandmorty.adapter.output.database.character;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterDatabaseRepository extends JpaRepository<CharacterEntity, Integer> {

    Page<CharacterEntity> findAllByIdIn(List<Integer> id, Pageable pageable);
    List<CharacterEntity> findAllByIdIn(List<Integer> id);

}