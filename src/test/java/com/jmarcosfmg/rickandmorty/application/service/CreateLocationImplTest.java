package com.jmarcosfmg.rickandmorty.application.service;

import com.jmarcosfmg.rickandmorty.application.entity.character.Character;
import com.jmarcosfmg.rickandmorty.application.entity.character.CharacterRepository;
import com.jmarcosfmg.rickandmorty.application.entity.character.CharacterTestUtils;
import com.jmarcosfmg.rickandmorty.application.entity.location.Location;
import com.jmarcosfmg.rickandmorty.application.entity.location.LocationRepository;
import com.jmarcosfmg.rickandmorty.application.entity.location.LocationTestUtils;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.CreateLocationInput;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.CreateLocationOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateLocationImplTest {

    private final Location earth = LocationTestUtils.getEarth();
    private final CreateLocationInput input = new CreateLocationInput(
            earth.getName(), earth.getDimension(), earth.getResidents().stream().map(Character::getId).toList());

    @Autowired
    @InjectMocks
    private CreateLocationImpl service;
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private CharacterRepository characterRepository;
    private List<Character> characters;

    @BeforeEach
    void setUp() {
        characters = new ArrayList<>();
        characters.add(CharacterTestUtils.getMorty());
    }

    @Test
    public void shouldCreateNewLocation() {

        when(characterRepository.getCharacters(input.residents())).thenReturn(characters);
        when(locationRepository.createLocation(any())).thenReturn(earth);

        CreateLocationOutput response = service.execute(input);

        assertNotNull("Id should not be null", response.id());
        assertNotNull("Creation date should not be null", response.creationDate());
        assertTrue("Created with different residents",
                earth.getResidents().stream().map(Character::getId).toList().containsAll(response.residents()));

        assertEquals("Created with different dimension", earth.getDimension(), response.dimension());
        assertEquals("Created with different name", earth.getName(), response.name());
    }
}

