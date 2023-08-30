package com.jmarcosfmg.rickandmorty.adapter.output.database.character;

import com.jmarcosfmg.rickandmorty.domain.character.Character;
import com.jmarcosfmg.rickandmorty.domain.character.CharacterRepository;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.CharacterMapper;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CharacterDatabaseService extends LogUtils implements CharacterRepository {

    @Autowired
    private CharacterDatabaseRepository repository;

    @Autowired
    private CharacterMapper mapper;
 
    @Override
    public List<Character> getCharacters(List<Integer> id) {
        log.info("Fetching Character filtered by id from database - {}", id);
        
        List<CharacterEntity> results = repository.findAllById(id);
        
        log.info("Successfully fetched Character  filtered by id from database - {}", id);
        return results.parallelStream().map(character -> mapper.toCharacter(character)).toList();
    }

    @Override
    public List<Character> updateCharacters(List<Character> character) {
        log.info("Updating database Character - {}", character);

        List<Character> entities = repository.saveAll(
                character.parallelStream().map(l -> mapper.toEntity(l)).toList()
            ).parallelStream().map(entity -> mapper.toCharacter(entity)).toList();
        
        log.info("Successfully updated database Character - {}", entities);
        return entities;
    }

    @Override
    public Character createCharacter(Character character) {
        log.info("Inserting Character into database - {}", character);

        Character entity = mapper.toCharacter(repository.save(mapper.toEntity(character)));

        log.info("Successfully inserted Character into database - {}", entity);
        return entity;
    }

    @Override
    public void deleteCharacter(List<Integer> id) {
        log.info("Deleting Character from database - {}", id);

        repository.deleteAllById(id);
        log.info("Succ deleted Character from database - {}", id);
    }


}
