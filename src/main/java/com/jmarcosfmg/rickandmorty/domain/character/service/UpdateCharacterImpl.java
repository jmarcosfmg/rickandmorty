package com.jmarcosfmg.rickandmorty.domain.character.service;

import com.jmarcosfmg.rickandmorty.application.usecase.character.UpdateCharacter;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.UpdateCharacterInput;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.UpdateCharacterOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateCharacterImpl implements UpdateCharacter {

    @Override
    public List<UpdateCharacterOutput> execute(List<UpdateCharacterInput> character) {
        return null;
    }
}
