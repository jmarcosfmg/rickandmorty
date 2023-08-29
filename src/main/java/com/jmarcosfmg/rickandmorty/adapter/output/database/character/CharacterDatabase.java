package com.jmarcosfmg.rickandmorty.adapter.output.database.character;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jmarcosfmg.rickandmorty.application.entity.character.Character;
import com.jmarcosfmg.rickandmorty.application.entity.character.CharacterRepository;

@Repository
public class CharacterDatabase implements CharacterRepository {

    @Override
    public List<Character> getCharacters(List<Integer> id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCharacters'");
    }

    @Override
    public Character updateCharacter(Character character) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCharacter'");
    }

    @Override
    public List<Character> updateCharacters(List<Character> character) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCharacters'");
    }

    @Override
    public Character createCharacter(Character character) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCharacter'");
    }

    @Override
    public void deleteCharacter(List<Integer> id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCharacter'");
    }
    
}
