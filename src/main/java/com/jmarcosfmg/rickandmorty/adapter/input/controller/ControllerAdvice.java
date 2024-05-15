package com.jmarcosfmg.rickandmorty.adapter.input.controller;

import com.jmarcosfmg.rickandmorty.adapter.input.controller.errorMessages.GenericExceptionDTO;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.errorMessages.NotFoundExceptionDTO;
import com.jmarcosfmg.rickandmorty.application.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NotFoundExceptionDTO handleNotFoundException(NotFoundException exception){

        return new NotFoundExceptionDTO(exception.getLocalizedMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericExceptionDTO handleGenericException(Exception exception){

        return new GenericExceptionDTO(exception.getLocalizedMessage());
    }

}
