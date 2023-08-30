package com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class LocationInfoResponse {
    Integer id;
    String name;
    String dimension;
    List<String> residents;
    LocalDate creationDate;
    String url;
}
