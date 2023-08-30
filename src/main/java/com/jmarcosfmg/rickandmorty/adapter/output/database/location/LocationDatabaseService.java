package com.jmarcosfmg.rickandmorty.adapter.output.database.location;

import com.jmarcosfmg.rickandmorty.application.entity.location.Location;
import com.jmarcosfmg.rickandmorty.application.entity.location.LocationRepository;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationDatabaseService extends LogUtils implements LocationRepository {

    @Autowired
    private LocationDatabaseRepository repository;

    @Autowired
    private LocationEntityMapper mapper;

    @Override
    public Page<Location> getLocationsById(List<Integer> id, Pageable pageable) {
        log.info("Fetching Location filtered by id from database - {}", id, pageable);
        
        Page<LocationEntity> results = repository.findAllByIdIn(id, pageable);
        
        log.info("Successfully fetched Location  filtered by id from database - {}", id, pageable);
        return results.map(location -> mapper.toLocation(location));
    }

    @Override
    public List<Location> getLocationsById(List<Integer> id) {
        log.info("Fetching Location filtered by id from database - {}", id);
        
        List<LocationEntity> results = repository.findAllById(id);
        
        log.info("Successfully fetched Location  filtered by id from database - {}", id);
        return results.parallelStream().map(location -> mapper.toLocation(location)).toList();
    }
    

    @Override
    public Page<Location> getLocations(Pageable pageable) {
        log.info("Fetching locations from database - {}", pageable);
        
        Page<LocationEntity> results = repository.findAll(pageable);
        
        log.info("Successfully fetched locations from database - {}", pageable);
        return results.map(location -> mapper.toLocation(location));
    }

    @Override
    public List<Location> updateLocation(List<Location> location) {
        log.info("Updating database Location - {}", location);

        List<Location> entities = repository.saveAll(
                location.parallelStream().map(l -> mapper.toEntity(l)).toList()
            ).parallelStream().map(entity -> mapper.toLocation(entity)).toList();
        
        log.info("Successfully updated database Location - {}", entities);
        return entities;
    }

    @Override
    public Location createLocation(Location location) {
        log.info("Inserting Location into database - {}", location);

        Location entity = mapper.toLocation(repository.save(mapper.toEntity(location)));

        log.info("Successfully inserted Location into database - {}", entity);
        return entity;
    }

    @Override
    public void deleteLocation(List<Integer> id) {
        log.info("Deleting Location from database - {}", id);

        repository.deleteAllById(id);
        log.info("Succ deleted Location from database - {}", id);
    }


}
