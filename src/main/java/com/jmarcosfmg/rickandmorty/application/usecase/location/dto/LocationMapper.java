package com.jmarcosfmg.rickandmorty.application.usecase.location.dto;

import com.jmarcosfmg.rickandmorty.domain.character.Character;
import com.jmarcosfmg.rickandmorty.domain.location.Location;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface LocationMapper {

    UpdateLocationOutput toUpdateLocationOutput(Location location);

    CreateLocationOutput toCreateLocationOutput(Location location);

    default List<Integer> map(List<Character> residents) {
        return residents.stream().map(Character::getId).toList();
    }

}
