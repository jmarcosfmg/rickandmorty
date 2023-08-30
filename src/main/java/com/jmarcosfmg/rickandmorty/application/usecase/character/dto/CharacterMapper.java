package com.jmarcosfmg.rickandmorty.application.usecase.character.dto;

import com.jmarcosfmg.rickandmorty.adapter.output.database.character.CharacterEntity;
import com.jmarcosfmg.rickandmorty.domain.character.Character;
import com.jmarcosfmg.rickandmorty.domain.location.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    
    CreateCharacterOutput toCreateCharacterOutput(Character character);
    ReadCharacterOutput toReadCharacterOutput(Character character);
    UpdateCharacterOutput toUpdateCharacterOutput(Character character);
    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(source = "input.gender", target = "gender")
    @Mapping(source = "input.status", target = "status")
    Character toCharacter(CreateCharacterInput input);

}
