package com.jmarcosfmg.rickandmorty.application.entity.character;

import java.util.Date;

import com.jmarcosfmg.rickandmorty.application.entity.character.enums.Gender;
import com.jmarcosfmg.rickandmorty.application.entity.character.enums.Status;
import com.jmarcosfmg.rickandmorty.application.entity.location.Location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Character {

    private Integer id;

    private String name;

    private Status status;

    private String species;

    private Gender gender;

    private Location origin;
    
    private Location location;
    
    private Date createdAt;

    public Character(
        String name,
        Status status,
        String species,
        Gender gender,
        Location origin,
        Location location
    ){
        this.name = name;
        this.status = status;
        this.species = species;
        this.gender = gender;
        this.location = location;
        this.origin = origin;
    }

    public Character update(Character character){
        
        if (character.status != null)
            this.status = character.status;

        setLocation(character.getLocation());
        
        return this;
    }

    public Character setLocation(Location location){
        if (location != null)
            this.location = location;

        return this;
    }

}
