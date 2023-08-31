package com.jmarcosfmg.rickandmorty.adapter.input.controller.character.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CharacterInfoResponse {
    Integer id;
    String name;
    String status;
    String species;
    String gender;
    LocationResponse origin;
    LocationResponse location;
    String url;
    LocalDate creationDate;
}
