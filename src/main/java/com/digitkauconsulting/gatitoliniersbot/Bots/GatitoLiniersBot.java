package com.digitkauconsulting.gatitoliniersbot.Bots;

import com.digitkauconsulting.gatitoliniersbot.helper.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;

@Service
public class GatitoLiniersBot extends TelegramLongPollingCommandBot {

    @Value("${GatitoLiniersUserName}")
    String botUserName;
    @Value("${GatitoLiniersToken}")
    String botToken;
    private static final String LOGTAG = "GatitoLiniersBot";

    final static Logger LOG = LoggerFactory.getLogger(GatitoLiniersBot.class);

    private  Map<String, IBotCommand> mapCommands;

    public GatitoLiniersBot(Map<String, IBotCommand> mapCommands) {
        mapCommands.forEach( (idx, item) -> {
            register(item);
        });
    }
    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                SendMessage echoMessage = new SendMessage();
                echoMessage.setChatId(message.getChatId().toString());
                echoMessage.setText("Message:\n" + message.getText());

                try {
                    execute(echoMessage);
                } catch (TelegramApiException e) {
                    //BotLogger.error(LOGTAG, e);
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
