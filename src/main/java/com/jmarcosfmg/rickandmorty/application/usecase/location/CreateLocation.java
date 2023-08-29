package com.jmarcosfmg.rickandmorty.application.usecase.location;

import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.CreateLocationInput;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.CreateLocationOutput;

public interface CreateLocation {

    public CreateLocationOutput execute(CreateLocationInput dto);

}
