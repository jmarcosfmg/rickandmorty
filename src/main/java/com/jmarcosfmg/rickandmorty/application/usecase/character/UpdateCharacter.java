package com.jmarcosfmg.rickandmorty.application.usecase.character;

import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.UpdateCharacterInput;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.UpdateCharacterOutput;

import java.util.List;

public interface UpdateCharacter {

    public List<UpdateCharacterOutput> execute(List<UpdateCharacterInput> character);

}
