package com.jmarcosfmg.rickandmorty.application.entity.character;

import java.util.List;

public interface CharacterRepository {

    List<Character> getCharacters();

    Character getCharacter(Integer id); 

    Character updateCharacter(Character character);
    
    Character createCharacter(Character character);

    void deleteCharacter(Integer id);  
    
}
