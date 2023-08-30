package com.jmarcosfmg.rickandmorty.application.service;

import com.jmarcosfmg.rickandmorty.application.entity.location.LocationRepository;
import com.jmarcosfmg.rickandmorty.application.exception.DatabaseIntegrationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteLocationImplTest {

    @Mock
    private LocationRepository repository;

    @Autowired
    @InjectMocks
    private DeleteLocationImpl service;

    private final List<Integer> ids = List.of(1, 2, 3);

    @Test
    void shouldDeleteAllLocations() {
        doNothing().when(repository).deleteLocation(ids);

        service.execute(ids);

        verify(repository, times(1)).deleteLocation(ids);
    }

    @Test
    void shouldParseDatabaseException() {
        doThrow(new DataRetrievalFailureException("")).when(repository).deleteLocation(any());

        DatabaseIntegrationException response = assertThrows(DatabaseIntegrationException.class,
                () -> {
                    service.execute(ids);
                }, "Should have returned exception"
        );

        verify(repository, times(1)).deleteLocation(ids);
        assertTrue(response.getMessage().contains("Location"), "Should explain exception");
    }

}