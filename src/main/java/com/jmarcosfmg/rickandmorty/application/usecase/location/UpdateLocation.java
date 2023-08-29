package com.jmarcosfmg.rickandmorty.application.usecase.location;

import java.util.List;

import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.UpdateLocationInput;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.UpdateLocationOutput;

public interface UpdateLocation {

    public List<UpdateLocationOutput> execute(List<UpdateLocationInput> location);

}
