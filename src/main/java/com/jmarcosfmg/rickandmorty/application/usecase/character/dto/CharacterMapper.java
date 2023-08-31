package com.jmarcosfmg.rickandmorty.application.usecase.character.dto;

import com.jmarcosfmg.rickandmorty.domain.character.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CreateCharacterOutput toCreateCharacterOutput(Character character);

    ReadCharacterOutput toReadCharacterOutput(Character character);

    UpdateCharacterOutput toUpdateCharacterOutput(Character character);

    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "location", ignore = true)
    Character toCharacter(UpdateCharacterInput input);

    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(source = "input.gender", target = "gender")
    @Mapping(source = "input.status", target = "status")
    Character toCharacter(CreateCharacterInput input);


}
