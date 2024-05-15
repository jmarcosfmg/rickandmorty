package com.jmarcosfmg.rickandmorty.adapter.input.controller.errorMessages;

import com.jmarcosfmg.rickandmorty.application.config.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public record GenericExceptionDTO(
        LocalDateTime timestamp,
        String message
) {
    public GenericExceptionDTO(String message) {
        this(LocalDateTime.now(ZoneId.of(Constants.TIMEZONE)), "INTERNAL_SERVER_ERROR: " + message);
    }
}
