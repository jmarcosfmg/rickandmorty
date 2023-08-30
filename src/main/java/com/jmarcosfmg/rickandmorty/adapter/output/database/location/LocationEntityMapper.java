package com.jmarcosfmg.rickandmorty.adapter.output.database.location;

import com.jmarcosfmg.rickandmorty.adapter.output.database.character.CharacterEntityMapper;
import com.jmarcosfmg.rickandmorty.application.entity.location.Location;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = CharacterEntityMapper.class)
public interface LocationEntityMapper {

    Location toLocation(LocationEntity entity);

    LocationEntity toEntity(Location location);
    
}
