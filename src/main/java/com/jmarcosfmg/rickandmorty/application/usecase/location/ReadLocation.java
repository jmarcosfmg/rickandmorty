package com.jmarcosfmg.rickandmorty.application.usecase.location;

import java.util.List;

import com.jmarcosfmg.rickandmorty.application.entity.location.Location;

public interface ReadLocation {

    public List<Location> execute(Integer... id);

}
