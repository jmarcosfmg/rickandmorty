package com.jmarcosfmg.rickandmorty.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.jmarcosfmg.rickandmorty.application.entity.character.Character;
import com.jmarcosfmg.rickandmorty.application.entity.location.Location;
import com.jmarcosfmg.rickandmorty.application.entity.location.LocationRepository;
import com.jmarcosfmg.rickandmorty.application.usecase.location.CreateLocation;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.CreateLocationInput;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.CreateLocationOutput;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;

public class CreateLocationImpl extends LogUtils implements CreateLocation{

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ReadCharacterImpl readCharacterService;

    public CreateLocationOutput execute(CreateLocationInput createLocationInput) {

        log.info("Starting location creation", createLocationInput);
        
        List<Character> residents = (createLocationInput.residents() == null)? 
             new ArrayList<>() : readCharacterService.getCharacters(createLocationInput.residents().toArray(Integer[]::new));
        
            Location createdLocation;
            try{
                createdLocation = locationRepository.createLocation(new Location(createLocationInput.name(), createLocationInput.dimension(), residents));
            }catch(Exception e){
                log.error("Failed to create location", e);
                throw new DataAccessException("Location could not be created"){};
            }
        
        log.info("Successfully created location", createLocationInput);

        return new CreateLocationOutput(
            createdLocation.getId(), 
            createdLocation.getName(), 
            createdLocation.getDimension(), 
            createdLocation.getResidents().stream().map(r -> r.getId()).toList(), 
            createdLocation.getCreatedAt()
            );
    }
    
}
