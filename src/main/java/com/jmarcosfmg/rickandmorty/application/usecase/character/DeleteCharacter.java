package com.jmarcosfmg.rickandmorty.application.usecase.character;

import java.util.List;

public interface DeleteCharacter {

    public void execute(List<Integer> id);
    
}
