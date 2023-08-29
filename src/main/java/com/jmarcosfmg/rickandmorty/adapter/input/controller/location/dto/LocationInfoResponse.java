package com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto;

import java.util.Date;
import java.util.List;

public record LocationInfoResponse (
    Integer id,
    String name,
    String dimension,
    List<Integer> residents,
    Date creationDate,
    String url
){

}
