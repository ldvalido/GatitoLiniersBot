package com.digitkauconsulting.gatitoliniersbot.helper;

import com.google.common.annotations.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class ValvedLogger {
    Class klass;
    int timesToLog;

    @VisibleForTesting
    private Logger log;
    private Random r;
    public ValvedLogger(Class klass){
        this(klass,10);
    }
    public ValvedLogger(Class klass, int timesToLog){
        this.klass = klass;
        this.timesToLog = timesToLog;
        log = LoggerFactory.getLogger(klass);
        r = new Random();
    }

    public void error(String message){
        if (hasLog()){
            log.error(message);
        }
    }
    public void info(String message){
        if (hasLog()){
            log.info(message);
        }
    }

    private boolean hasLog(){
        int driver = r.nextInt();
        return ((driver / timesToLog) * timesToLog) == driver;
    }
}
