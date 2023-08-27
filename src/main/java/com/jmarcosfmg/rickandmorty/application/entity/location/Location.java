package com.jmarcosfmg.rickandmorty.application.entity.location;

import java.time.LocalDate;
import java.util.List;

import com.jmarcosfmg.rickandmorty.application.entity.character.Character;

public class Location {

    public Integer id;

    public String name;

    public String dimension;

    public List<Character> residents;
    
    public LocalDate createdAt;
    
}
