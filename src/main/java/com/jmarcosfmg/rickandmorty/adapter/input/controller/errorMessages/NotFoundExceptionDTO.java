package com.jmarcosfmg.rickandmorty.adapter.input.controller.errorMessages;

import com.jmarcosfmg.rickandmorty.application.config.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;

@ResponseStatus(HttpStatus.NOT_FOUND)
public record NotFoundExceptionDTO(
        LocalDateTime timestamp,
        String message

) {

    public NotFoundExceptionDTO(String message) {
        this(LocalDateTime.now(ZoneId.of(Constants.TIMEZONE)), "NOT_FOUND: " + message);
    }
}
