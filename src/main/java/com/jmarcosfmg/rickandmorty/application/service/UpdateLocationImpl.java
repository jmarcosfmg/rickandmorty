package com.jmarcosfmg.rickandmorty.application.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmarcosfmg.rickandmorty.application.entity.character.CharacterRepository;
import com.jmarcosfmg.rickandmorty.application.entity.location.Location;
import com.jmarcosfmg.rickandmorty.application.entity.location.LocationRepository;
import com.jmarcosfmg.rickandmorty.application.usecase.location.UpdateLocation;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.LocationMapper;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.UpdateLocationInput;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.UpdateLocationOutput;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;

@Service
public class UpdateLocationImpl extends LogUtils implements UpdateLocation {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private LocationMapper mapper;

    @Override
    public List<UpdateLocationOutput> execute(List<UpdateLocationInput> location) {
        if (location.isEmpty()){
            log.warn("No locations to update");
            return List.of();
        }            

        List<Integer> locationIds = location.parallelStream().map(l -> l.id()).toList();    
        log.info("Starting process to update locations", locationIds);

        Map<Integer, Location> fetchedLocations = new HashMap<>();

        locationRepository.getLocationsById(locationIds)
            .forEach(l -> fetchedLocations.put(l.getId(), l));
        
        log.info("Found {} locations to update", fetchedLocations.size());

        List<Location> updatedLocations = location.parallelStream()
            .map(l -> fetchedLocations.get(l.id()).update(new Location(l.name(), l.dimension(), List.of()))).toList();

        updatedLocations = locationRepository.updateLocation(updatedLocations);

        log.info("Successfully updated locations", locationIds);        
        return updatedLocations.parallelStream().map(l -> this.mapper.toOutput(l)).toList();
    }    
}
