package com.jmarcosfmg.rickandmorty.application.usecase.location;

import java.util.List;

import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.ReadLocationOutput;

public interface ReadLocation {

    public List<ReadLocationOutput> execute(Integer... id);

}
