package com.jmarcosfmg.rickandmorty.application.usecase.character.dto;


import java.util.Date;

public record CreateCharacterOutput(
    Integer id,
    String name,
    String status,
    String species,
    String gender,
    CharacterLocationOutput origin,
    CharacterLocationOutput location,
    Date creationDate
) {
    
}
