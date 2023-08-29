package com.jmarcosfmg.rickandmorty.adapter.input.controller.location.converter;

import org.mapstruct.Mapper;

import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto.CreateLocationRequest;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto.LocationInfoResponse;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto.UpdateLocationRequest;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.CreateLocationInput;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.CreateLocationOutput;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.ReadLocationOutput;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.UpdateLocationInput;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.UpdateLocationOutput;

@Mapper(componentModel = "spring")
public interface ControllerMapper {
    
    public CreateLocationInput toInput(CreateLocationRequest request);

    public UpdateLocationInput toInput(UpdateLocationRequest request);

    public LocationInfoResponse toResponse(CreateLocationOutput output);

    public LocationInfoResponse toResponse(ReadLocationOutput output);

    public LocationInfoResponse toResponse(UpdateLocationOutput output);
}
