package com.jmarcosfmg.rickandmorty.domain.character;

import com.jmarcosfmg.rickandmorty.domain.character.enums.Gender;
import com.jmarcosfmg.rickandmorty.domain.character.enums.Status;
import com.jmarcosfmg.rickandmorty.domain.location.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Character {

    private Integer id;

    private String name;

    private Status status;

    private String species;

    private Gender gender;

    private Location origin;

    private Location location;

    private LocalDate creationDate;

    public Character(
            String name,
            Status status,
            String species,
            Gender gender,
            Location origin,
            Location location
    ) {
        this.name = name;
        this.status = status;
        this.species = species;
        this.gender = gender;
        this.location = location;
        this.origin = origin;
    }

    public Character update(Character character) {

        if (character.status != null)
            this.status = character.status;
        if (character.name != null)
            this.name = character.name;
        if (character.species != null)
            this.species = character.species;
        if (character.gender != null)
            this.gender = character.gender;
        if (character.origin != null)
            this.origin = character.origin;
        if (character.location != null)
            this.location = character.location;

        return this;
    }

}
