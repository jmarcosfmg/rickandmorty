package com.jmarcosfmg.rickandmorty.adapter.input.controller.character.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCharacterRequest(
        @NotNull
        Integer id,

        @NotBlank
        String name,

        @NotBlank
        String status,

        @NotBlank
        String species,

        @NotBlank
        String gender,
        Integer location
){

}
