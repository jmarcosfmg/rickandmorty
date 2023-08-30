package com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto;

import java.time.LocalDate;
import java.util.List;

public record LocationInfoResponse (
    Integer id,
    String name,
    String dimension,
    List<Integer> residents,
    LocalDate creationDate,
    String url
){

}
