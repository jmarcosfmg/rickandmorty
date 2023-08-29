package com.jmarcosfmg.rickandmorty.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.jmarcosfmg.rickandmorty.application.entity.location.LocationRepository;
import com.jmarcosfmg.rickandmorty.application.usecase.location.DeleteLocation;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;

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
            throw new DataAccessException("Location could not be deleted: "+id){};
        }
        log.info("Succseffuly deleted location - {}", id);

    }
    
}
