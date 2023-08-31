package com.jmarcosfmg.rickandmorty.application.usecase.character.dto;

import java.time.LocalDate;

public record ReadCharacterOutput(
        Integer id,
        String name,
        String status,
        String species,
        String gender,
        CharacterLocationOutput origin,
        CharacterLocationOutput location,
        LocalDate creationDate
) {

}
