package com.digitkauconsulting.gatitoliniersbot.service;

import com.digitkauconsulting.gatitoliniersbot.helper.HttpHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
    @Value("${stockRequestUrl}")
    String stockUrl = "https://stooq.com/q/l/?s=%s&f=sd2t2ohlcv&e=cs";

    @Override
    public String getStockRate(String stockName) {
        String url = String.format(stockUrl, stockName);

        HttpHelper httpHelper = new HttpHelper();
        String result = httpHelper.get(url);
        return result;
    }
}
