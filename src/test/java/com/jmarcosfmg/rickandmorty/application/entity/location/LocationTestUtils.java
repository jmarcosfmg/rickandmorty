package com.jmarcosfmg.rickandmorty.application.entity.location;

import com.jmarcosfmg.rickandmorty.application.utils.DateUtils;

import java.util.ArrayList;

public class LocationTestUtils {

    public static Location getEarth() {
        return new Location(1, "Earth", "c-137", new ArrayList<>(), DateUtils.toDate("2023-08-26"));
    }

    public static Location getSecondEarth() {
        return new Location(2, "Second Earth", "c-138", new ArrayList<>(), DateUtils.toDate("2023-08-27"));
    }
}
