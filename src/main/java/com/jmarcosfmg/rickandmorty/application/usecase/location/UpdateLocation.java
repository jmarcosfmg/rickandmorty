package com.jmarcosfmg.rickandmorty.application.usecase.location;

import java.util.List;

import com.jmarcosfmg.rickandmorty.application.entity.location.Location;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.UpdateLocationInput;

public interface UpdateLocation {

    public List<Location> execute(UpdateLocationInput... location);

}
