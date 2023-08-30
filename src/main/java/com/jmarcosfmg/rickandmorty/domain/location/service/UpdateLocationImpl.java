package com.jmarcosfmg.rickandmorty.domain.location.service;

import com.jmarcosfmg.rickandmorty.domain.character.CharacterRepository;
import com.jmarcosfmg.rickandmorty.domain.location.Location;
import com.jmarcosfmg.rickandmorty.domain.location.LocationRepository;
import com.jmarcosfmg.rickandmorty.application.exception.NotFoundException;
import com.jmarcosfmg.rickandmorty.application.usecase.location.UpdateLocation;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.LocationMapper;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.UpdateLocationInput;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.UpdateLocationOutput;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UpdateLocationImpl extends LogUtils implements UpdateLocation {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CharacterRepository characterRepository;

    private final LocationMapper mapper = Mappers.getMapper(LocationMapper.class);

    @Override
    public List<UpdateLocationOutput> execute(List<UpdateLocationInput> location) {
        if (location.isEmpty()){
            log.warn("No locations to update");
            return List.of();
        }

        List<Integer> locationIds = location.parallelStream().map(UpdateLocationInput::id).toList();
        log.info("Starting process to update locations - {}", locationIds);

        Map<Integer, Location> fetchedLocations = new HashMap<>();

        locationRepository.getLocationsById(locationIds)
            .forEach(l -> fetchedLocations.put(l.getId(), l));

        verifyLocationsExistence(fetchedLocations, locationIds);
        log.info("Found {} locations to update", fetchedLocations.size());

        List<Location> updatedLocations = location.parallelStream()
            .map(l -> fetchedLocations.get(l.id()).update(new Location(l.name(), l.dimension(), List.of()))).toList();

        updatedLocations = locationRepository.updateLocation(updatedLocations);

        log.info("Successfully updated locations - {}", locationIds);
        return updatedLocations.parallelStream().map(this.mapper::toUpdateLocationOutput).toList();
    }

    private void verifyLocationsExistence(Map<Integer, Location> fetchedLocations, List<Integer> locationIds) {
        List<String> notFoundKeys = locationIds.stream().filter(id -> !fetchedLocations.containsKey(id)).map(Object::toString).toList();
        if (notFoundKeys.isEmpty())
            return;
        log.error("Missing locations - {}", notFoundKeys);
        throw new NotFoundException("Could not find Location with id=[" + String.join(", ", notFoundKeys) + "]");
    }
}
