package com.jmarcosfmg.rickandmorty.application.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmarcosfmg.rickandmorty.application.entity.location.Location;
import com.jmarcosfmg.rickandmorty.application.entity.location.LocationRepository;
import com.jmarcosfmg.rickandmorty.application.usecase.location.ReadLocation;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.ReadLocationOutput;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;

@Service
public class ReadLocationImpl extends LogUtils implements ReadLocation{

    @Autowired
    LocationRepository repository;

    @Override
    public List<ReadLocationOutput> execute(Integer... id) {
        
        log.info("Starting to read locations: ", id);

        List<Location> response = this.getLocations(id);

        log.info("Successfully read {0} characters: ".formatted(response.size()), id);

        return response.stream().parallel().map(this::toReadLocationOutput).toList();
    }


    protected List<Location> getLocations(Integer... id) {
        log.info("Fetching locations on database: ", id);

        List<Location> response = repository.getLocation(Arrays.asList(id));

        log.info("Successfully fetched {0} locations: ".formatted(response.size()), id);

        return response;
    }
    
    
    private ReadLocationOutput toReadLocationOutput(Location location) {

        return new ReadLocationOutput(
            location.getId(),
            location.getName(),
            location.getDimension(),
            location.getResidents().stream().map(r -> r.getId()).toList(),
            location.getCreatedAt()
        );
    }
}
