package com.digitkauconsulting.gatitoliniersbot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DigitKauConsultingBotApplication implements ApplicationRunner {

    final
    Orchestrator orchestrator;

    public DigitKauConsultingBotApplication(Orchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    public static void main(String[] args) {
        SpringApplication.run(DigitKauConsultingBotApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        orchestrator.start();

    }
}