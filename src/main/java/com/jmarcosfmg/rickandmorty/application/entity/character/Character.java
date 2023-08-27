package com.jmarcosfmg.rickandmorty.application.entity.character;

import java.time.LocalDate;

import com.jmarcosfmg.rickandmorty.application.entity.location.Location;

public class Character {

    public Integer id;

    public String name;

    public String status;

    public String species;

    public String gender;

    public String origin;

    public Location location;
    
    public LocalDate createdAt;

}
