package com.jmarcosfmg.rickandmorty.application.usecase.character;

import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.CreateCharacterInput;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.CreateCharacterOutput;


public interface CreateCharacter {

    public CreateCharacterOutput execute(CreateCharacterInput input);

}
