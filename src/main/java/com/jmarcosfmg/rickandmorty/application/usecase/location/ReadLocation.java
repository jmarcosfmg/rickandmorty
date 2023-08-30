package com.jmarcosfmg.rickandmorty.application.usecase.location;

import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.ReadLocationOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReadLocation {

    public Page<ReadLocationOutput> execute(List<Integer> ids, Pageable pageable);

}
