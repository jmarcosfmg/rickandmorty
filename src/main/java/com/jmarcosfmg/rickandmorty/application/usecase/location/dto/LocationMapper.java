package com.jmarcosfmg.rickandmorty.application.usecase.location.dto;

import java.util.List;

import org.mapstruct.Mapper;

import com.jmarcosfmg.rickandmorty.application.entity.character.Character;
import com.jmarcosfmg.rickandmorty.application.entity.location.Location;


@Mapper(componentModel = "spring")
public interface LocationMapper {

    public UpdateLocationOutput toOutput(Location location);

    default List<Integer> map(List<Character> residents){
        return residents.stream().map(r -> r.getId()).toList();
    }
    
}
