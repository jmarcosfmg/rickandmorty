package com.jmarcosfmg.rickandmorty.adapter.output.database.character;

import com.jmarcosfmg.rickandmorty.domain.character.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterEntityMapper {

    @Mapping(target = "update", ignore = true)
    @Mapping(target = "location.residents", ignore = true)
    @Mapping(target = "origin.residents", ignore = true)
    Character toCharacter(CharacterEntity entity);

    @Mapping(target = "location.residents", ignore = true)
    @Mapping(target = "origin.residents", ignore = true)
    CharacterEntity toEntity(Character character);

}
