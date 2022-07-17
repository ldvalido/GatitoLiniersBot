package com.digitkauconsulting.gatitoliniersbot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

@Service
public class Orchestrator {

    private final Map<String, TelegramLongPollingCommandBot> mapBots;

    public Orchestrator(Map<String, TelegramLongPollingCommandBot> mapBots) {
        this.mapBots = mapBots;
    }

    public void start() throws TelegramApiException {
        try {
            final TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

            mapBots.forEach( (idx, item) -> {
                try {
                    telegramBotsApi.registerBot(item);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}
