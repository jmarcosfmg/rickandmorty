package com.jmarcosfmg.rickandmorty.domain.character.service;

import com.jmarcosfmg.rickandmorty.application.usecase.character.DeleteCharacter;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;
import com.jmarcosfmg.rickandmorty.domain.character.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteCharacterImpl extends LogUtils implements DeleteCharacter {

    @Autowired
    CharacterRepository repository;

    @Override
    public void execute(List<Integer> id) {

        repository.deleteCharacter(id);

    }
}
