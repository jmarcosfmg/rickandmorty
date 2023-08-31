package com.jmarcosfmg.rickandmorty.adapter.input.controller.character.converter;

import com.jmarcosfmg.rickandmorty.adapter.input.controller.character.dto.CharacterInfoResponse;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.character.dto.CreateCharacterRequest;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.character.dto.LocationResponse;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.character.dto.UpdateCharacterRequest;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.LocationController;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.*;
import org.mapstruct.Mapper;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface CharacterControllerMapper {

    public CreateCharacterInput toInput(CreateCharacterRequest request);

    public UpdateCharacterInput toInput(UpdateCharacterRequest request);

    public CharacterInfoResponse toResponse(CreateCharacterOutput output);

    public CharacterInfoResponse toResponse(ReadCharacterOutput output);

    public CharacterInfoResponse toResponse(UpdateCharacterOutput output);


    default LocationResponse map(CharacterLocationOutput location) {
        if (location == null)
            return null;
        return new LocationResponse(location.name(),
                linkTo(methodOn(LocationController.class).getLocation(List.of(location.id()), null)).toString());
    }

}
