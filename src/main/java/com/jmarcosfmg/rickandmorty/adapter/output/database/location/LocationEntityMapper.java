package com.jmarcosfmg.rickandmorty.adapter.output.database.location;

import org.mapstruct.Mapper;

import com.jmarcosfmg.rickandmorty.application.entity.location.Location;

@Mapper(componentModel = "spring")
public interface LocationEntityMapper {

    Location toLocation(LocationEntity entity);

    LocationEntity toEntity(Location location);
    
}
