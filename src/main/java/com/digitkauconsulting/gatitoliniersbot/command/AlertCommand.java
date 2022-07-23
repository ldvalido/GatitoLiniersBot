package com.digitkauconsulting.gatitoliniersbot.command;

import com.digitkauconsulting.gatitoliniersbot.model.Event;
import com.digitkauconsulting.gatitoliniersbot.service.StockService;
import com.digitkauconsulting.gatitoliniersbot.service.eventManagement.EventHandler;
import com.digitkauconsulting.gatitoliniersbot.service.eventManagement.EventRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class AlertCommand implements IBotCommand {
    StockService rateService;
    EventHandler evtHandler;
    public AlertCommand(StockService rateService, EventHandler evtHandler){
        this.rateService = rateService;
        this.evtHandler = evtHandler;
    }
    Logger log = LoggerFactory.getLogger(AlertCommand.class);

    @Override
    public String getCommandIdentifier() {
        return "alert";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] arguments) {
        log.info(String.format("A new alert was created. Arguments: %s", String.join(",", arguments)));

        SendMessage msg = new SendMessage();

        String symbol = arguments[0];
        String policy = arguments[2];
        String symbolValue = arguments[2];

        EventRunnable res = ( symbolRunner, symbolValueRunner) -> {
            String stockValue = rateService.getStockRate(symbolRunner);
            float symbolRate = Float.parseFloat(symbolValue);
            float stockValueRate = Float.parseFloat(stockValue);
            log.info(symbolRate + " " + stockValueRate);

            return  symbolRate < stockValueRate;
        };

        Event evt = new Event(message.getMessageId(), message.getChatId(), res, symbol, symbolValue);

        evtHandler.addEvent(evt);

        try {
            msg.setChatId(message.getChatId().toString());
            msg.setText("Alert created successfully");
            absSender.execute(msg);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}
