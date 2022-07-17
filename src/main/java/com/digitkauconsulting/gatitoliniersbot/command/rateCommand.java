package com.digitkauconsulting.gatitoliniersbot.command;

import com.digitkauconsulting.gatitoliniersbot.service.StockService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class rateCommand implements IBotCommand {

    public final StockService stockService;

    public rateCommand(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public String getCommandIdentifier() {
        return "rate";
    }

    @Override
    public String getDescription() {
        return "Rate of an stock";
    }

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] arguments) {
        SendMessage msg = new SendMessage();
        msg.setChatId(message.getChatId().toString());
        String ticket = arguments[0];

        String result = stockService.getStockRate(ticket);
        msg.setText(result);
        try {
            absSender.execute(msg);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}
