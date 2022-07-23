package com.digitkauconsulting.gatitoliniersbot.service;

import com.digitkauconsulting.gatitoliniersbot.helper.HttpHelperEncoding;
import com.digitkauconsulting.gatitoliniersbot.helper.HttpHelper;
import com.digitkauconsulting.gatitoliniersbot.helper.HttpHelperContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TelegramServiceImpl implements TelegramService {

    static Logger log = LoggerFactory.getLogger(TelegramServiceImpl.class);
    @Value("${GatitoLiniersToken}")
    String botToken;

    @Value("${telegramApiUrl}")
    private String telegramApiUrl;

    final
    HttpHelper helper;

    public TelegramServiceImpl(HttpHelper helper) {
        this.helper = helper;
    }

    @Override
    public void postMesage(String chatId, String message) {
        String url = String.format(telegramApiUrl, botToken, chatId, HttpHelperEncoding.encode( message ));
        helper.post(url,Object.class, "", HttpHelperContentType.TextPlain );
    }
}
