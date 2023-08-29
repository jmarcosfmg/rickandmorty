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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;

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

    private Page<Location> locations = new PageImpl<>(List.of(earth, secondEarth));

    private Pageable pageable = Pageable.ofSize(2);

    @Test
    public void shouldReturnAllRequiredLocations() {       

        when(repository.getLocationsById(List.of(earth.getId(), secondEarth.getId()), pageable)).thenReturn(locations);

        Page<ReadLocationOutput> response = service.execute(List.of(earth.getId(), secondEarth.getId()), pageable);

        assertEquals("Should have returned both locations", locations.getSize(), response.getSize());
    }

    @Test
    public void shouldReturnEmptyListOfLocation() {

        when(repository.getLocationsById(List.of(earth.getId()), pageable)).thenReturn(Page.empty());

        Page<ReadLocationOutput> response = service.execute(List.of(earth.getId()), pageable);

        assertEquals("Should have returned no location", 0, response.getSize());
    }

    @Test
    public void shouldParseExceptions() {

        when(repository.getLocationsById(List.of(earth.getId(), secondEarth.getId()), pageable)).thenReturn(locations);
        
        RuntimeException response = assertThrows(RuntimeException.class, () -> {service.execute(List.of(earth.getId()), pageable);}, "Should have returned exception");

        assertTrue("Should explain exception", response.getMessage().contains("Location"));
    }
}
