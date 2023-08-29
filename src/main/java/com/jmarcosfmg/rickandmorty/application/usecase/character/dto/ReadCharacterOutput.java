package com.jmarcosfmg.rickandmorty.application.usecase.character.dto;

import java.util.Date;

public record ReadCharacterOutput(
    Integer id,
    String name,
    String status,
    String species,
    String gender,
    Integer origin,
    Integer location,
    Date creationDate
) {

}
