package com.jmarcosfmg.rickandmorty.domain.location.service;

import com.jmarcosfmg.rickandmorty.domain.character.Character;
import com.jmarcosfmg.rickandmorty.domain.character.CharacterRepository;
import com.jmarcosfmg.rickandmorty.domain.location.Location;
import com.jmarcosfmg.rickandmorty.domain.location.LocationRepository;
import com.jmarcosfmg.rickandmorty.application.exception.NotFoundException;
import com.jmarcosfmg.rickandmorty.application.usecase.location.CreateLocation;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.CreateLocationInput;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.CreateLocationOutput;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.LocationMapper;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateLocationImpl extends LogUtils implements CreateLocation{

    @Autowired
    LocationRepository locationRepository;

    private final LocationMapper mapper = Mappers.getMapper(LocationMapper.class);
    @Autowired
    CharacterRepository characterRepository;

    public CreateLocationOutput execute(CreateLocationInput createLocationInput) {

        log.info("Starting location creation - {}", createLocationInput);
        
        List<Character> residents = (createLocationInput.residents() == null)?
                new ArrayList<>() : characterRepository.getCharacters(createLocationInput.residents());

        verifyCharactersExistence(residents, createLocationInput);

        Location createdLocation = locationRepository.createLocation(new Location(createLocationInput.name(), createLocationInput.dimension(), residents));

        log.info("Successfully created location - {}", createdLocation);

        return mapper.toCreateLocationOutput(createdLocation);
    }

    private void verifyCharactersExistence(List<Character> residents, CreateLocationInput createLocationInput) {
        if (createLocationInput.residents() == null || createLocationInput.residents().isEmpty())
            return;

        Set<Integer> ids = residents.parallelStream().map(Character::getId).collect(Collectors.toSet());
        List<Integer> notFoundKeys = createLocationInput.residents().stream().filter(ids::contains).toList();
        if (notFoundKeys.isEmpty())
            return;

        log.error("Missing Character - {}", notFoundKeys);
        throw new NotFoundException("Could not find Character with id " + notFoundKeys);
    }

}
