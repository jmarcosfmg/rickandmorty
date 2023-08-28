package com.jmarcosfmg.rickandmorty.application.entity.character.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

public class GenderTest {

    @Test
    public void shouldGetGenders() {

        assertEquals(Gender.FEMALE, Gender.parse("FEMALE"));
        assertEquals(Gender.MALE, Gender.parse("male"));
        assertEquals(Gender.UNKNOWN, Gender.parse("Unknown"));
        assertEquals(Gender.GENDERLESS, Gender.parse("geNDerleSs"));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGenderIsInvalid(){
        
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> {Gender.parse(null);});
        assertTrue(ex1.getMessage().contains("NULL"));

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> {Gender.parse("no gender");});
        assertTrue(ex2.getMessage().contains("NO GENDER"));
    }

}
