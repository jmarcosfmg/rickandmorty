package com.jmarcosfmg.rickandmorty.domain.character.service;

import com.jmarcosfmg.rickandmorty.application.usecase.character.UpdateCharacter;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.CharacterMapper;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.UpdateCharacterInput;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.UpdateCharacterOutput;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;
import com.jmarcosfmg.rickandmorty.domain.character.Character;
import com.jmarcosfmg.rickandmorty.domain.character.CharacterRepository;
import com.jmarcosfmg.rickandmorty.domain.location.Location;
import com.jmarcosfmg.rickandmorty.domain.location.LocationRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UpdateCharacterImpl extends LogUtils implements UpdateCharacter {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    LocationRepository locationRepository;

    private CharacterMapper mapper = Mappers.getMapper(CharacterMapper.class);

    @Override
    public List<UpdateCharacterOutput> execute(List<UpdateCharacterInput> input) {

        List<Integer> characterIds = input.stream().map(UpdateCharacterInput::id).toList();

        log.info("Starting to update Characters with id = {}", characterIds);

        Map<Integer, Location> locations = getLocations(input);

        Map<Integer, Character> characters = getCharacters(characterIds);

        List<Character> updatedCharacters = input.parallelStream().map(c -> {
            Character character = characters.get(c.id());

            Character updatedVersion = this.mapper.toCharacter(c);

            updatedVersion.setOrigin((c.origin() == null) ? null : locations.get(c.origin()));
            updatedVersion.setLocation((c.location() == null) ? null : locations.get(c.location()));

            character.update(updatedVersion);
            return character;
        }).toList();

        log.info("Successfully updated Characters with id = {}", characterIds);
        return characterRepository.updateCharacters(updatedCharacters).stream().map(mapper::toUpdateCharacterOutput).toList();
    }

    private Map<Integer, Character> getCharacters(List<Integer> input) {
        return characterRepository
                .getCharacters(input)
                .stream().collect(Collectors.toMap(Character::getId, Function.identity()));
    }


    Map<Integer, Location> getLocations(List<UpdateCharacterInput> character) {

        List<Integer> locationIds = character.stream().map(c -> Arrays.asList(c.location(), c.origin()))
                .flatMap(Collection::stream).distinct().filter(Objects::nonNull).toList();

        return locationRepository.getLocationsById(locationIds)
                .stream().collect(Collectors.toMap(Location::getId, Function.identity()));
    }
}
