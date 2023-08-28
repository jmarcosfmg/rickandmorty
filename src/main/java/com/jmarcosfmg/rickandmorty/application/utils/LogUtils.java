package com.jmarcosfmg.rickandmorty.application.utils;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

public abstract class LogUtils {

    protected Logger log = new LoggerContext().getLogger(this.getClass().getSimpleName());

}
