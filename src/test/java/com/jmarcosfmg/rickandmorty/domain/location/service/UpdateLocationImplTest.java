package com.jmarcosfmg.rickandmorty.domain.location.service;

import com.jmarcosfmg.rickandmorty.application.exception.NotFoundException;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.UpdateLocationInput;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.UpdateLocationOutput;
import com.jmarcosfmg.rickandmorty.domain.character.CharacterRepository;
import com.jmarcosfmg.rickandmorty.domain.character.CharacterTestUtils;
import com.jmarcosfmg.rickandmorty.domain.location.Location;
import com.jmarcosfmg.rickandmorty.domain.location.LocationRepository;
import com.jmarcosfmg.rickandmorty.domain.location.LocationTestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateLocationImplTest {

    private final Location earth = LocationTestUtils.getEarth();
    private final UpdateLocationInput earthInput = new UpdateLocationInput(earth.getId(), "new earth name", "new earth dimension", List.of(1, 2));
    private final Location secondEarth = LocationTestUtils.getSecondEarth();
    private final UpdateLocationInput secondEarthInput = new UpdateLocationInput(secondEarth.getId(), "new second earth name", "new second earth dimension", List.of(5, 7));
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private CharacterRepository characterRepository;
    @InjectMocks
    private UpdateLocationImpl service;

    @Test
    public void shouldSkipWhenUpdateListIsEmpty() {

        assertTrue("Returned list should be empty", service.execute(List.of()).isEmpty());

        verify(locationRepository, times(0)).getLocationsById(any());
        verify(locationRepository, times(0)).updateLocation(any());
    }

    @Test
    public void shouldThrowExceptionWhenLocationIsNotFound() {

        when(locationRepository.getLocationsById(any())).thenReturn(List.of(earth));

        NotFoundException response = assertThrows(NotFoundException.class, () -> service.execute(List.of(earthInput, secondEarthInput)));

        verify(locationRepository, times(0)).updateLocation(any());
        assertTrue("Should return missing location id", response.getMessage().contains(String.valueOf(secondEarth.getId())));

    }

    @Test
    public void shouldThrowDatabaseConnectionErrors() {
        when(locationRepository.getLocationsById(any())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> service.execute(List.of(earthInput, secondEarthInput)));

        verify(locationRepository, times(0)).updateLocation(any());
    }

    @Test
    public void shouldReturnUpdatedLocations() {
        List<Location> locations = List.of(earth, secondEarth);
        List<UpdateLocationInput> inputs = List.of(earthInput, secondEarthInput);
        when(characterRepository.getCharacters(any(List.class))).thenReturn(List.of(CharacterTestUtils.getMorty()));
        when(locationRepository.getLocationsById(any())).thenReturn(locations);

        List<UpdateLocationOutput> response = service.execute(inputs);

        verify(locationRepository, times(1)).getLocationsById(any());
        verify(locationRepository, times(1)).updateLocation(any());

        response.forEach(r -> {
            UpdateLocationInput locationInput = inputs.stream().filter(l -> l.id() == r.id()).findFirst().get();
            Location location = locations.stream().filter(l -> l.getId() == r.id()).findFirst().get();
            assertEquals("Id should be equal", locationInput.id(), r.id());
            assertEquals("Id should be equal", location.getCreationDate(), r.creationDate());
            assertEquals("Id should be equal", locationInput.dimension(), r.dimension());
            assertEquals("Id should be equal", locationInput.name(), r.name());
            assertEquals("Id should be equal", locationInput.residents().size(), r.residents().size());
        });
    }

}
