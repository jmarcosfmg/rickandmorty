package com.jmarcosfmg.rickandmorty.application.exception;

import org.springframework.dao.DataAccessException;


public class DatabaseIntegrationException extends DataAccessException {
    public DatabaseIntegrationException(String msg) {
        super(msg);
    }
}
