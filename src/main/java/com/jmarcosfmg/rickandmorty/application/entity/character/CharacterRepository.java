package com.jmarcosfmg.rickandmorty.application.entity.character;

import java.util.List;

public interface CharacterRepository {

    List<Character> getCharacters(List<Integer> id); 
        
    List<Character> updateCharacters(List<Character> character);

    Character createCharacter(Character character);

    void deleteCharacter(List<Integer> id);  
    
}
