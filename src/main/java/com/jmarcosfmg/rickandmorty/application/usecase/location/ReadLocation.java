package com.jmarcosfmg.rickandmorty.application.usecase.location;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.ReadLocationOutput;

public interface ReadLocation {

    public Page<ReadLocationOutput> execute(List<Integer> ids, Pageable pageable);

}
