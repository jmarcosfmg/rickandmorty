package com.jmarcosfmg.rickandmorty.adapter.input.controller.character.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCharacterRequest(
    @NotNull
    @NotBlank
    String name,
    @NotNull
    @NotBlank
    String status,
    @NotNull
    @NotBlank
    String species,
    @NotNull
    @NotBlank
    String gender,
    @NotNull
    Integer origin,
    Integer location
){

}