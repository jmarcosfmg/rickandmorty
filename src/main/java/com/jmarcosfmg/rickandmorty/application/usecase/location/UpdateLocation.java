package com.jmarcosfmg.rickandmorty.application.usecase.location;

import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.UpdateLocationInput;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.UpdateLocationOutput;

import java.util.List;

public interface UpdateLocation {

    public List<UpdateLocationOutput> execute(List<UpdateLocationInput> location);

}
