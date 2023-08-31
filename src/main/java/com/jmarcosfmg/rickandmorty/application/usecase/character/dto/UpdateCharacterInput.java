package com.jmarcosfmg.rickandmorty.application.usecase.character.dto;

public record UpdateCharacterInput(

        Integer id,
        String name,
        String status,
        String species,
        String gender,
        Integer origin,
        Integer location

) {

}
