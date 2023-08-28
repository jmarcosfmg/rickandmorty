package com.jmarcosfmg.rickandmorty.application.entity.location;

import java.util.List;

public interface LocationRepository {

    List<Location> getLocation(List<Integer> id); 

    Location updateLocation(Location location);
    
    Location createLocation(Location location);

    void deleteLocation(List<Integer> id);    
}
