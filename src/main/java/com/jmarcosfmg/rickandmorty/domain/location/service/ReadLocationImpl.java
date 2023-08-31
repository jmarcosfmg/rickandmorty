package com.jmarcosfmg.rickandmorty.domain.location.service;

import com.jmarcosfmg.rickandmorty.application.usecase.location.ReadLocation;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.ReadLocationOutput;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;
import com.jmarcosfmg.rickandmorty.domain.character.Character;
import com.jmarcosfmg.rickandmorty.domain.location.Location;
import com.jmarcosfmg.rickandmorty.domain.location.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadLocationImpl extends LogUtils implements ReadLocation {

    @Autowired
    private LocationRepository repository;

    @Override
    public Page<ReadLocationOutput> execute(List<Integer> id, Pageable pageable) {

        log.info("Starting to read locations - {}", id);


        Page<Location> response = (id.isEmpty()) ? repository.getLocations(pageable) : repository.getLocationsById(id, pageable);

        log.info("Successfully read {} characters - {}", response.getSize(), id);

        return response.map(this::toReadLocationOutput);
    }

    private ReadLocationOutput toReadLocationOutput(Location location) {

        return new ReadLocationOutput(
                location.getId(),
                location.getName(),
                location.getDimension(),
                location.getResidents().stream().map(Character::getId).toList(),
                location.getCreationDate()
        );
    }
}
