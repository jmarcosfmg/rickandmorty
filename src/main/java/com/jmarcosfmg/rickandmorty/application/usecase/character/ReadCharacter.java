package com.jmarcosfmg.rickandmorty.application.usecase.character;

import java.util.List;

import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.ReadCharacterOutput;

public interface ReadCharacter {

    public List<ReadCharacterOutput> execute(Integer... id);
    
}
