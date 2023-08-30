package com.jmarcosfmg.rickandmorty.application.usecase.character;

import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.ReadCharacterOutput;

import java.util.List;

public interface ReadCharacter {

    public List<ReadCharacterOutput> execute(List<Integer> id);
    
}
