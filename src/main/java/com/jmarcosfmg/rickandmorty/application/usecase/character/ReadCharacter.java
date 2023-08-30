package com.jmarcosfmg.rickandmorty.application.usecase.character;

import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.ReadCharacterOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReadCharacter {

    Page<ReadCharacterOutput> execute(List<Integer> ids, Pageable pageable);
    
}
