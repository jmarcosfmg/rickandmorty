package com.jmarcosfmg.rickandmorty.application.usecase.character.dto;

import com.jmarcosfmg.rickandmorty.adapter.output.database.character.CharacterEntity;
import com.jmarcosfmg.rickandmorty.domain.character.Character;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    
    Character toCharacter(CharacterEntity entity);

    CharacterEntity toEntity(Character character);
}
