package com.jmarcosfmg.rickandmorty.application.usecase.location.dto;

import java.time.LocalDate;
import java.util.List;

public record ReadLocationOutput(
        Integer id,
        String name,
        String dimension,
        List<Integer> residents,
        LocalDate creationDate
) {

}
