package com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateLocationRequest(
    @NotBlank String name,
    @NotBlank String dimension
){

}