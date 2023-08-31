package com.jmarcosfmg.rickandmorty.application.usecase.character.dto;

import java.time.LocalDate;

public record UpdateCharacterOutput(

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
