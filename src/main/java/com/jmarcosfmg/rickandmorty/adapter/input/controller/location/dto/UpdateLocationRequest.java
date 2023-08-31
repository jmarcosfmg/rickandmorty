package com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UpdateLocationRequest(
        Integer id,
        @NotBlank String name,
        @NotBlank String dimension,

        List<Integer> residents
) {

}
