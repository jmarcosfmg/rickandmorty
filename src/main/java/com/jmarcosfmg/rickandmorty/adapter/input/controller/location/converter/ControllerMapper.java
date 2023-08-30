package com.jmarcosfmg.rickandmorty.adapter.input.controller.location.converter;

import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto.CreateLocationRequest;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto.LocationInfoResponse;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto.UpdateLocationRequest;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ControllerMapper {
    
    public CreateLocationInput toInput(CreateLocationRequest request);

    public UpdateLocationInput toInput(UpdateLocationRequest request);

    public LocationInfoResponse toResponse(CreateLocationOutput output);

    public LocationInfoResponse toResponse(ReadLocationOutput output);

    public LocationInfoResponse toResponse(UpdateLocationOutput output);
}
