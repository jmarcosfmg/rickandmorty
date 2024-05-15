package com.jmarcosfmg.rickandmorty.adapter.output.database.character;

import com.jmarcosfmg.rickandmorty.application.exception.NotFoundException;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;
import com.jmarcosfmg.rickandmorty.domain.character.Character;
import com.jmarcosfmg.rickandmorty.domain.character.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CharacterDatabaseService extends LogUtils implements CharacterRepository {

    @Autowired
    private CharacterDatabaseRepository repository;

    @Autowired
    private CharacterEntityMapper mapper;

    @Override
    public List<Character> getCharacters(List<Integer> id) {
        log.info("Fetching Character filtered by id from database - {}", id);

        List<CharacterEntity> results = repository.findAllByIdIn(id);

        if (id.size() != results.size()) {
            throw new NotFoundException("Could not find Character(s) with id(s) " + id.removeAll(results.stream().map(CharacterEntity::getId).toList()));
        }
        log.info("Successfully fetched Character filtered by id from database - {}", id);
        return results.parallelStream().map(character -> mapper.toCharacter(character)).toList();
    }

    @Override
    public Page<Character> getCharacters(List<Integer> id, Pageable pageable) {
        log.info("Fetching Character page filtered by id={} - Page={}", id, pageable);

        Page<CharacterEntity> results = repository.findAllByIdIn(id, pageable);

        log.info("Successfully fetched Character page filtered by id={} - Page={}", id, pageable);
        return results.map(mapper::toCharacter);
    }

    @Override
    public Page<Character> getCharacters(Pageable pageable) {
        log.info("Fetching Character page {}", pageable);

        Page<CharacterEntity> results = repository.findAll(pageable);

        log.info("Successfully fetched Character page {}", pageable);
        return results.map(mapper::toCharacter);
    }

    @Override
    public List<Character> updateCharacters(List<Character> character) {
        log.info("Updating database Character - {}", character);

        List<Character> entities = repository.saveAll(
                character.parallelStream().map(mapper::toEntity).toList()
        ).parallelStream().map(mapper::toCharacter).toList();

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
        log.info("Deleting Character(s) from database - id=[{}]", id);

        repository.deleteAllByIdInBatch(id);
        log.info("Successfully deleted Character(s) from database - id=[{}]", id);
    }


}
