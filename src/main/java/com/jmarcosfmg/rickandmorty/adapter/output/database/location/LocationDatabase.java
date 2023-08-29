package com.jmarcosfmg.rickandmorty.adapter.output.database.location;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jmarcosfmg.rickandmorty.application.entity.location.Location;
import com.jmarcosfmg.rickandmorty.application.entity.location.LocationRepository;

@Repository
public class LocationDatabase implements LocationRepository {

    @Override
    public List<Location> getLocation(List<Integer> id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLocation'");
    }

    @Override
    public List<Location> updateLocation(List<Location> location) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateLocation'");
    }

    @Override
    public Location createLocation(Location location) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createLocation'");
    }

    @Override
    public void deleteLocation(List<Integer> id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteLocation'");
    }
    
}
