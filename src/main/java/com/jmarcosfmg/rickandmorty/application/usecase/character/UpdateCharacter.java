package com.jmarcosfmg.rickandmorty.application.usecase.character;

import java.util.List;

import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.UpdateCharacterInput;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.UpdateCharacterOutput;

public interface UpdateCharacter {

    public List<UpdateCharacterOutput> execute(UpdateCharacterInput... character);
    
}
