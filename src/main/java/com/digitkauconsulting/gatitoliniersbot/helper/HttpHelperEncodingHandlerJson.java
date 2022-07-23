package com.digitkauconsulting.gatitoliniersbot.helper;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

import javax.inject.Named;

@Service()
@Named("HttpHandlerText")
public class HttpHelperEncodingHandlerJson implements HttpHelperEncodingHandler {

    final String TEXTPLAINContentType="text/plain";

    Gson gson = new Gson();

    @Override
    public RequestBody preAction(Object data) {
        String bodyRaw = gson.toJson(data);
        RequestBody body = RequestBody.create(bodyRaw, MediaType.parse(TEXTPLAINContentType));
        return body;
    }

    @Override
    public <T> T postAction(String rawValue, Class<T> klass) {
        return gson.fromJson(rawValue, klass);
    }
}
