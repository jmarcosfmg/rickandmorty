package com.jmarcosfmg.rickandmorty.application.usecase.location.dto;

import java.util.Date;
import java.util.List;

public record ReadLocationOutput(
    Integer id,
    String name,
    String dimension,
    List<Integer> residents,
    Date creationDate
) {
    
}
