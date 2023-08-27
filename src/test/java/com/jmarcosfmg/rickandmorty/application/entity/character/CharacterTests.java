package com.jmarcosfmg.rickandmorty.application.entity.character;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CharacterTests {

    private Character character = CharacterTestUtils.getMorty();

    @Test
    void shouldUpdateOnlyLocationAndStatus(){

        Character updatedCharacter = CharacterTestUtils.getOtherMorty();

        Character response = character.update(updatedCharacter);

        assertEquals("Gender was changed", character.getGender(), response.getGender());
        assertEquals("Creation date was changed", character.getCreatedAt(), response.getCreatedAt());
        assertEquals("Id was changed", character.getId(), response.getId());
        assertEquals("Name was changed", character.getName(), response.getName());
        assertEquals("Origin was changed", character.getOrigin(), response.getOrigin());
        assertEquals("Species was changed", character.getSpecies(), response.getSpecies());
        
        assertEquals("Status was not changed", updatedCharacter.getStatus(), response.getStatus());
        assertEquals("Location was not changed", updatedCharacter.getLocation(), response.getLocation());
    }

}
