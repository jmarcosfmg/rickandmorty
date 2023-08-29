package com.jmarcosfmg.rickandmorty.application.usecase.location.dto;

import java.util.List;

public record CreateLocationInput(
    String name,
    String dimension,
    List<Integer> residents
){

}
