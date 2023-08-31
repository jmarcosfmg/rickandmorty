package com.jmarcosfmg.rickandmorty.domain.character;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CharacterRepository {

    List<Character> getCharacters(List<Integer> id);

    Page<Character> getCharacters(List<Integer> id, Pageable pageable);

    Page<Character> getCharacters(Pageable pageable);

    List<Character> updateCharacters(List<Character> character);

    Character createCharacter(Character character);

    void deleteCharacter(List<Integer> id);

}
