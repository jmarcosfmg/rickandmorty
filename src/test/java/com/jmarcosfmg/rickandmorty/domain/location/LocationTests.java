package com.jmarcosfmg.rickandmorty.domain.location;

import com.jmarcosfmg.rickandmorty.domain.character.Character;
import com.jmarcosfmg.rickandmorty.domain.character.CharacterTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LocationTests {

    private Location earth = LocationTestUtils.getEarth();
    private Character morty = CharacterTestUtils.getMorty();
    private Character otherMorty = CharacterTestUtils.getOtherMorty();

    @BeforeEach
    void setUp(){
        earth = LocationTestUtils.getEarth();
        earth.addResident(morty);
    }

    @Test
    void shouldUpdateDimensionAndNameAndCharacters(){

        Location updatedLocation = LocationTestUtils.getSecondEarth();
        updatedLocation.addResident(otherMorty);
        
        Location response = earth.update(updatedLocation);

        assertAll(
            () -> {assertEquals("Id should not have been updated", earth.getId(), response.getId());},
            () -> {assertEquals("Created date should not have been updated", earth.getCreationDate(), response.getCreationDate());},
            () -> {assertEquals("Name should have been updated", updatedLocation.getName(), response.getName());},
            () -> {assertEquals("Dimension should have been updated", updatedLocation.getDimension(), response.getDimension());},
            () -> {assertEquals("Residents list should not have been replaced", earth.getResidents(), response.getResidents());},
            () -> {assertTrue("Characters should have been added to residents list", response.getResidents().contains(otherMorty));}
        );   
    }

    @Test
    void shouldRemoveCharactersFromResidentsListIfFound(){

        this.earth.removeResident(morty);
        assertTrue("Character should not be a resident", !this.earth.getResidents().contains(morty));

    }

    @Test
    void shouldNotThrowExceptionWhenAskedToRemovedCharacterThatIsNotResident(){

        List<Character> residentsToRemove = new ArrayList<Character>();

        residentsToRemove.add(morty);
        residentsToRemove.add(otherMorty);
        
        assertDoesNotThrow(() -> {this.earth.removeResident(residentsToRemove.toArray(Character[]::new));});

        assertTrue("Should have removed residents on list", !this.earth.getResidents().contains(morty));
    }

    @Test
    void shouldAddCharacterToResidentsList(){

        this.earth.addResident(otherMorty);

        assertTrue("Should have added resident", this.earth.getResidents().contains(otherMorty));
    }
    
    @Test
    void shouldNotAddRepeatedCharacterToResidentsList(){

        this.earth.addResident(morty);

        assertEquals("Should not have added repeated resident", this.earth.getResidents().size(), this.earth.getResidents().stream().distinct().toList().size());
    }

    @Test
    void shouldUpdateResidentsLocation(){

        this.earth.addResident(otherMorty);

        this.earth.getResidents().forEach(c -> { 
            assertEquals("Character location should have been updated", this.earth, c.getLocation());
        });
    }
}
