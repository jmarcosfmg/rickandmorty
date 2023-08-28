package com.jmarcosfmg.rickandmorty.application.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;

import com.jmarcosfmg.rickandmorty.application.entity.character.Character;
import com.jmarcosfmg.rickandmorty.application.entity.character.CharacterTestUtils;
import com.jmarcosfmg.rickandmorty.application.entity.location.Location;
import com.jmarcosfmg.rickandmorty.application.entity.location.LocationRepository;
import com.jmarcosfmg.rickandmorty.application.entity.location.LocationTestUtils;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.CreateLocationInput;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.CreateLocationOutput;

@ExtendWith(MockitoExtension.class)
public class CreateLocationImplTest {

    @Mock
    private LocationRepository repository = mock();

    @Mock 
    private ReadCharacterImpl characterService  = mock();

    @Autowired
    @InjectMocks
    private CreateLocationImpl service;

    private Location earth = LocationTestUtils.getEarth();
    
    private CreateLocationInput input = new CreateLocationInput(earth.getName(),earth.getDimension(), earth.getResidents().stream().map(r -> r.getId()).toList());

    private List<Character> characters ;

    @BeforeEach
    void setUp(){
        characters = new ArrayList<>();
        characters.add(CharacterTestUtils.getMorty());
    }

    @Test
    public void shouldCreateNewLocation(){

        when(characterService.getCharacters()).thenReturn(characters);
        when(repository.createLocation(any())).thenReturn(earth);
        
        CreateLocationOutput response = service.execute(input);

        assertNotNull("Id should not be null", response.id());
        assertNotNull("Creation date should not be null", response.creationDate());
        assertTrue("Created with different residents", earth.getResidents().stream().map(r -> r.getId()).toList().containsAll(response.residents()));
        
        assertEquals("Created with different dimension", earth.getDimension(), response.dimension());
        assertEquals("Created with different name", earth.getName(), response.name());
    }

    @Test
    public void shouldParseIntegrationExceptions() {

        when(characterService.getCharacters()).thenReturn(characters);
        when(repository.createLocation(any())).thenThrow(new DataRetrievalFailureException("Error"));
        
        RuntimeException response = assertThrows(RuntimeException.class, () -> {service.execute(input);}, "Should have returned exception");

        assertTrue("Should explain exception", response.getMessage().contains("Location"));
    }
}
