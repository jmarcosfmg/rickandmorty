package com.jmarcosfmg.rickandmorty.domain.character.enums;

public enum Status {

    ALIVE,
    DEAD,
    UNKNOWN;

    public static Status parse(String status) {
        return Status.valueOf((status != null)? status.strip().toUpperCase() : "NULL");
    }

}
