package com.jmarcosfmg.rickandmorty.adapter.output.database.location;

import com.jmarcosfmg.rickandmorty.adapter.output.database.character.CharacterEntityMapper;
import com.jmarcosfmg.rickandmorty.domain.location.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = CharacterEntityMapper.class)
public interface LocationEntityMapper {

    @Mapping(target = "update", ignore = true)
    Location toLocation(LocationEntity entity);

    LocationEntity toEntity(Location location);
    
}
