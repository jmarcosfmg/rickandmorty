package com.jmarcosfmg.rickandmorty.application.entity.character.enums;

public enum Gender {

    MALE,
    FEMALE,
    GENDERLESS,
    UNKNOWN;

    public static Gender parse(String gender) {
        return Gender.valueOf((gender != null)? gender.strip().toUpperCase() : "NULL");
    }
    
}
