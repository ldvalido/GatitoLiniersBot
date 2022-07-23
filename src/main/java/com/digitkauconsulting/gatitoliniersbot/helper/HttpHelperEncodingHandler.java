package com.digitkauconsulting.gatitoliniersbot.helper;

import okhttp3.RequestBody;

public interface HttpHelperEncodingHandler {

    RequestBody preAction(Object data);
    <T> T postAction(String rawValue, Class<T> klass);
}
