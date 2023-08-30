package com.jmarcosfmg.rickandmorty.domain.location;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LocationRepository {

    Page<Location> getLocationsById(List<Integer> id, Pageable pageable); 

    List<Location> getLocationsById(List<Integer> id); 
    
    Page<Location> getLocations(Pageable pageable); 

    List<Location> updateLocation(List<Location> location);
    
    Location createLocation(Location location);

    void deleteLocation(List<Integer> id);    
}
