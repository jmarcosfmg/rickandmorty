package com.jmarcosfmg.rickandmorty.application.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jmarcosfmg.rickandmorty.application.entity.location.Location;
import com.jmarcosfmg.rickandmorty.application.entity.location.LocationRepository;
import com.jmarcosfmg.rickandmorty.application.entity.location.LocationTestUtils;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.ReadLocationOutput;

@ExtendWith(MockitoExtension.class)
public class ReadLocationImplTest {

    @Mock
    private LocationRepository repository;

    @InjectMocks
    private ReadLocationImpl service;

    private Location earth = LocationTestUtils.getEarth();

    private Location secondEarth = LocationTestUtils.getEarth();

    private List<Location> locations = List.of(earth, secondEarth);

    @Test
    public void shouldReturnAllRequiredLocations() {       

        when(repository.getLocation(List.of(earth.getId(), secondEarth.getId()))).thenReturn(locations);

        List<ReadLocationOutput> response = service.execute(List.of(earth.getId(), secondEarth.getId()));

        assertEquals("Should have returned both locations", locations.size(), response.size());
    }

    @Test
    public void shouldReturnEmptyListOfLocation() {

        when(repository.getLocation(List.of(earth.getId()))).thenReturn(List.of());

        List<ReadLocationOutput> response = service.execute(List.of(earth.getId()));

        assertEquals("Should have returned no location", 0, response.size());
    }

    @Test
    public void shouldParseExceptions() {

        when(repository.getLocation(List.of(earth.getId(), secondEarth.getId()))).thenReturn(locations);
        
        RuntimeException response = assertThrows(RuntimeException.class, () -> {service.execute(List.of(earth.getId()));}, "Should have returned exception");

        assertTrue("Should explain exception", response.getMessage().contains("Location"));
    }
}
