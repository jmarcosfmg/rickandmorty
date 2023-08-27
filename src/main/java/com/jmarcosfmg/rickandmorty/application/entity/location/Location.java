package com.jmarcosfmg.rickandmorty.application.entity.location;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jmarcosfmg.rickandmorty.application.entity.character.Character;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Location {

    private Integer id;

    private String name;

    private String dimension;

    private List<Character> residents;    

    private Date createdAt;

    
    public Location update(Location location){

        if (location.dimension != null && !location.dimension.isBlank())
            this.dimension = location.dimension;
            
        if (location.name != null && !location.name.isBlank())
            this.name = location.name;
            
        if (location.residents != null) 
            addResident(location.residents.toArray(Character[]::new));        
        
        return this;
    }
    
    public void removeResident(Character... resident){
        if (this.residents == null || this.residents.isEmpty())
            return; 

        for (Character character : resident) {
            this.residents.remove(character);
        }
    }

    public void addResident(Character... resident){
        if (this.residents == null)
            this.residents = new ArrayList<>();
        
        List<Character> newResidents = List.of(resident).stream().distinct().filter(r -> !this.residents.contains(r)).toList();
        newResidents.parallelStream().forEach(r -> r.setLocation(this));        
        this.residents.addAll(newResidents);
    }
}
