package com.jmarcosfmg.rickandmorty.application.service;

import com.jmarcosfmg.rickandmorty.application.entity.character.Character;
import com.jmarcosfmg.rickandmorty.application.entity.character.CharacterRepository;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.ReadCharacterOutput;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;

import com.jmarcosfmg.rickandmorty.application.usecase.character.ReadCharacter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadCharacterImpl extends LogUtils implements ReadCharacter {

    @Autowired
    private CharacterRepository repository;

    @Override
    public List<ReadCharacterOutput> execute(List<Integer> id){
       log.info("Starting to read characters: ", id);

       List<Character> characters = this.getCharacters(id);

       log.info("Successfully read {0} characters: ", characters.size(), id);
       return characters.stream().parallel().map(this::toReadCharacterOutput).toList();
    }

    
    protected List<Character> getCharacters(List<Integer> id){
        log.info("Fetching characters on database: ", id);

        List<Character> response = repository.getCharacters(id);

        log.info("Successfully fetched {0} characters: ", response.size() , id);
        return response;
    }   

    private ReadCharacterOutput toReadCharacterOutput(Character character){

        return new ReadCharacterOutput(
            character.getId(),
            character.getName(),
            character.getStatus().name(),
            character.getSpecies(),
            character.getGender().name(),
            character.getOrigin().getId(),
            character.getLocation().getId(),
            character.getCreatedAt()
        );
    }
}
