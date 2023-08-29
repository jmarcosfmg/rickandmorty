package com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateLocationRequest(
    Integer id,
    @NotBlank String name,
    @NotBlank String dimension
){

}
