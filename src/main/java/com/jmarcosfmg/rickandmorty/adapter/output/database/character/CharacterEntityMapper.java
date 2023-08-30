package com.jmarcosfmg.rickandmorty.adapter.output.database.character;

import com.jmarcosfmg.rickandmorty.domain.character.Character;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterEntityMapper {
    
    Character toCharacter(CharacterEntity entity);

}
