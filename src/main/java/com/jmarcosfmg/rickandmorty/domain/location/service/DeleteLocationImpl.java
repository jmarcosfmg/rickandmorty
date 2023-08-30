package com.jmarcosfmg.rickandmorty.domain.location.service;

import com.jmarcosfmg.rickandmorty.domain.location.LocationRepository;
import com.jmarcosfmg.rickandmorty.application.exception.DatabaseIntegrationException;
import com.jmarcosfmg.rickandmorty.application.usecase.location.DeleteLocation;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteLocationImpl extends LogUtils implements DeleteLocation {

    @Autowired
    private LocationRepository repository;

    @Override
    public void execute(List<Integer> id) {
        log.info("Starting process to delete location - {}", id);

        try{
            repository.deleteLocation(id);
        }catch(Exception e){
            log.error("Failed to delete locations - {}", id);
            throw new DatabaseIntegrationException("Location could not be deleted: " + id) {
            };
        }
        log.info("Successfully deleted location - {}", id);

    }
    
}
