package com.jmarcosfmg.rickandmorty.domain.character.service;

import com.jmarcosfmg.rickandmorty.application.exception.NotFoundException;
import com.jmarcosfmg.rickandmorty.application.usecase.character.CreateCharacter;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.CharacterMapper;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.CreateCharacterInput;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.CreateCharacterOutput;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;
import com.jmarcosfmg.rickandmorty.domain.character.Character;
import com.jmarcosfmg.rickandmorty.domain.character.CharacterRepository;
import com.jmarcosfmg.rickandmorty.domain.location.Location;
import com.jmarcosfmg.rickandmorty.domain.location.LocationRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CreateCharacterImpl extends LogUtils implements CreateCharacter {

    private final CharacterMapper mapper = Mappers.getMapper(CharacterMapper.class);
    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    LocationRepository locationRepository;

    public CreateCharacterOutput execute(CreateCharacterInput createCharacterInput) {

        log.info("Starting character creation - {}", createCharacterInput);

        Character characterToCreate = mapper.toCharacter(createCharacterInput);

        setLocations(characterToCreate, createCharacterInput);

        Character savedCharacter = characterRepository.createCharacter(characterToCreate);

        log.info("Successfully created character - {}", savedCharacter.toString());

        return mapper.toCreateCharacterOutput(savedCharacter);
    }

    private void setLocations(Character characterToCreate, CreateCharacterInput inp) throws NotFoundException {
        Map<Integer, Location> locations = getLocations(inp);

        try {
            if (inp.location() != null) {
                locations.computeIfAbsent(inp.location(),
                        a -> {
                            throw new NotFoundException(String.format("Location {%s} was not found", inp.location()));
                        });
                characterToCreate.setLocation(locations.get(inp.location()));
            }

            if (inp.origin() != null) {
                locations.computeIfAbsent(inp.origin(),
                        a -> {
                            throw new NotFoundException(String.format("Location origin {%s} was not found", inp.origin()));
                        });
                characterToCreate.setOrigin(locations.get(inp.origin()));
            }
        } catch (NotFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    private Map<Integer, Location> getLocations(CreateCharacterInput input) {
        List<Integer> locationIds = Stream.of(input.location(), input.origin()).filter(Objects::nonNull).distinct().toList();

        if (locationIds.isEmpty())
            return Collections.emptyMap();

        return locationRepository.getLocationsById(locationIds).stream()
                .collect(Collectors.toMap(Location::getId, Function.identity()));
    }
}
