package com.jmarcosfmg.rickandmorty.domain.character.enums;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StatusTest {

        @Test
    public void shouldGetStatus() {

        assertEquals(Status.ALIVE, Status.parse("ALIVE"));
        assertEquals(Status.DEAD, Status.parse(" dead"));
        assertEquals(Status.UNKNOWN, Status.parse("UnkNowN "));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenStatusIsInvalid(){
        
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> {Status.parse(null);});
        assertTrue(ex1.getMessage().contains("NULL"));

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> {Status.parse("no status");});
        assertTrue(ex2.getMessage().contains("NO STATUS"));
    }
}
