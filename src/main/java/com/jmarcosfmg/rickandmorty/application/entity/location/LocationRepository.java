package com.jmarcosfmg.rickandmorty.application.entity.location;

import java.util.List;

public interface LocationRepository {

    List<Location> getLocations();

    Location getLocation(Integer id); 

    Location updateLocation(Location location);
    
    Location createLocation(Location location);

    void deleteLocation(Integer id);    
}
