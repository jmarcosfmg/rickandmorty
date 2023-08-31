package com.jmarcosfmg.rickandmorty.application.usecase.location.dto;

import java.util.List;

public record UpdateLocationInput(
        Integer id,
        String name,
        String dimension,
        List<Integer> residents
) {

}
