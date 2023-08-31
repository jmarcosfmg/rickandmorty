package com.jmarcosfmg.rickandmorty.adapter.input.controller.location.converter;

import com.jmarcosfmg.rickandmorty.adapter.input.controller.character.CharacterController;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto.CreateLocationRequest;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto.LocationInfoResponse;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto.UpdateLocationRequest;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.*;
import org.mapstruct.Mapper;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface LocationControllerMapper {

    public CreateLocationInput toInput(CreateLocationRequest request);

    public UpdateLocationInput toInput(UpdateLocationRequest request);

    public LocationInfoResponse toResponse(CreateLocationOutput output);

    public LocationInfoResponse toResponse(ReadLocationOutput output);

    public LocationInfoResponse toResponse(UpdateLocationOutput output);

    default List<String> map(List<Integer> residents) {
        return residents.stream().map(
                c -> linkTo(methodOn(CharacterController.class).getCharacter(List.of(c), null))
                        .toString()
        ).toList();
    }
}
