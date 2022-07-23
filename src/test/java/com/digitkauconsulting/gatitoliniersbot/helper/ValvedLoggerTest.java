package com.digitkauconsulting.gatitoliniersbot.helper;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ValvedLoggerTest {

    @Test public void LogAlways(){

        ValvedLogger log = new ValvedLogger(ValvedLoggerTest.class, 1); //Log Always
        Logger logger = mock(Logger.class);

        doNothing().when(logger).info(anyString());
    }
    @Test public void LogSometimes(){
        ValvedLogger log = new ValvedLogger(ValvedLoggerTest.class, 1); //Log Always
        Logger logger = mock(Logger.class);
    }

}
