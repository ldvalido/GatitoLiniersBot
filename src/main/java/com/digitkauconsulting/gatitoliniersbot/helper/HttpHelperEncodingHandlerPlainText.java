package com.digitkauconsulting.gatitoliniersbot.helper;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;

import javax.inject.Named;

@Service
@Named("HttpHandler")
public class HttpHelperEncodingHandlerPlainText implements HttpHelperEncodingHandler{

    final String TEXTPLAINContentType="text/plain";

    @Override
    public RequestBody preAction(Object data) {
        String bodyRaw = data.toString();

        return RequestBody.create(bodyRaw, MediaType.parse(TEXTPLAINContentType));
    }

    @Override
    public <T> T postAction(String rawValue, Class<T> klass)  {
        return (T) rawValue;
    }
}
