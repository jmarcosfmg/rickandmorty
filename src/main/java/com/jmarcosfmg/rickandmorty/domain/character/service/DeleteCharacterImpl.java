package com.jmarcosfmg.rickandmorty.domain.character.service;

import com.jmarcosfmg.rickandmorty.application.usecase.character.DeleteCharacter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteCharacterImpl implements DeleteCharacter {

    @Override
    public void execute(List<Integer> id) {

    }
}
