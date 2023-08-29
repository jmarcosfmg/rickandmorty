package com.jmarcosfmg.rickandmorty.application.entity.location;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jmarcosfmg.rickandmorty.application.entity.character.Character;

import lombok.Getter;

@Getter
public class Location {

    private Integer id;

    private String name;

    private String dimension;

    private Map<Integer, Character> residents = new HashMap<>();    

    private Date creationDate;

    public Location(Integer id, String name, String dimension, List<Character> residents, Date creationDate){
        this.id = id;
        this.name = name;
        this.dimension = dimension;
        residents.forEach(r -> this.residents.put(r.getId(), r));
        this.creationDate = creationDate;
    }

    public Location(String name, String dimension, List<Character> residents){
        this.name = name;
        this.dimension = dimension;
        residents.forEach(r -> this.residents.put(r.getId(), r));
    }

    public List<Character> getResidents(){
        return List.copyOf(this.residents.values());
    }

    private void setResidents(List<Character> residents){
        Map<Integer, Character> newResidents = new HashMap<>();
        residents.forEach(r -> newResidents.put(r.getId(), r));
        this.updateResidents(newResidents);
    }

    public Location update(Location location){

        if (location.dimension != null && !location.dimension.isBlank())
            this.dimension = location.dimension;
            
        if (location.name != null && !location.name.isBlank())
            this.name = location.name;
            
        if (location.residents != null) 
            this.setResidents(location.getResidents());        
        
        return this;
    }
    
    public void removeResident(Character... resident){
        for (Character resident2 : resident) {
            if(this.residents.containsKey(resident2.getId())){
                resident2.setLocation(null);
                this.residents.remove(resident2.getId(), resident2);
            }
      }
    }

    public void addResident(Character... resident){
      for (Character resident2 : resident) {
        resident2.setLocation(this);
        this.residents.put(resident2.getId(), resident2);
      }
    }

    private void updateResidents(Map<Integer, Character> newResidents){
        this.residents.forEach((i, c) -> { if(!newResidents.containsKey(i)) c.setLocation(null);});
        newResidents.forEach((i, c) -> c.setLocation(this));
        this.residents = newResidents;
    }
}
