package com.jmarcosfmg.rickandmorty.application.usecase.character.dto;


public record CreateCharacterInput(
    String name,
    String status,
    String species,
    String gender,
    Integer origin,
    Integer location
) {
    
}
