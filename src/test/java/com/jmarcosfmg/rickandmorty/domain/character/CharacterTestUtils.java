package com.jmarcosfmg.rickandmorty.domain.character;

import com.jmarcosfmg.rickandmorty.domain.character.enums.Gender;
import com.jmarcosfmg.rickandmorty.domain.character.enums.Status;
import com.jmarcosfmg.rickandmorty.domain.location.Location;
import com.jmarcosfmg.rickandmorty.domain.location.LocationTestUtils;
import com.jmarcosfmg.rickandmorty.application.utils.DateUtils;

public class CharacterTestUtils {

    public static Character getMorty() {

        Location earth = LocationTestUtils.getEarth();

        return new Character(1, "Morty", Status.ALIVE, "human", Gender.MALE, earth, earth, DateUtils.toDate("2023-08-26"));
    }

    public static Character getOtherMorty() {

        Location secondEarth = LocationTestUtils.getSecondEarth();

        return new Character(2, "Other Morty", Status.DEAD, "Alien", Gender.FEMALE, secondEarth, secondEarth, DateUtils.toDate("2023-08-28"));
    }

}
