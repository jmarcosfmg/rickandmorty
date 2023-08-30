package com.jmarcosfmg.rickandmorty.domain.character.service;

import com.jmarcosfmg.rickandmorty.application.usecase.character.ReadCharacter;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.CharacterMapper;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.ReadCharacterOutput;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;
import com.jmarcosfmg.rickandmorty.domain.character.Character;
import com.jmarcosfmg.rickandmorty.domain.character.CharacterRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadCharacterImpl extends LogUtils implements ReadCharacter {

    @Autowired
    private CharacterRepository repository;

    private static final CharacterMapper mapper = Mappers.getMapper(CharacterMapper.class);

    @Override
    public Page<ReadCharacterOutput> execute(List<Integer> id, Pageable pageable) {

        log.info("Starting to read characters - {}", id);


        Page<Character> response = (id.isEmpty()) ? repository.getCharacters(pageable) : repository.getCharacters(id, pageable);

        log.info("Successfully read {0} characters - {}", response.getSize(), id);

        return response.map(mapper::toReadCharacterOutput);
    }


    protected List<Character> getCharacters(List<Integer> id){
        log.info("Fetching characters on database - {}", id);

        List<Character> response = repository.getCharacters(id);

        log.info("Successfully fetched {} characters - {}", response.size(), id);
        return response;
    }
}
