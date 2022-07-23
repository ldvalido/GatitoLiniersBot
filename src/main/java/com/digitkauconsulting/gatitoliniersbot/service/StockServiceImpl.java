package com.digitkauconsulting.gatitoliniersbot.service;

import com.digitkauconsulting.gatitoliniersbot.helper.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    static Logger log = LoggerFactory.getLogger(StockServiceImpl.class);
    @Value("${stockRequestUrl}")
    String stockUrl = "https://stooq.com/q/l/?s=%s&f=sd2t2ohlcv&e=cs";

    @Value("${stockRequestIndex}")
    Integer stockRequestIndex;

    final
    HttpHelper httpHelper;

    public StockServiceImpl(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public String getStockRate(String stockName) {
        String url = String.format(stockUrl, stockName);

        String rawResult = httpHelper.get(url);
        String[] results = rawResult.split(",");
        String result = results[stockRequestIndex];
        return result;
    }
}
