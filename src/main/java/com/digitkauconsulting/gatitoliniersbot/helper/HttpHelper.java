package com.digitkauconsulting.gatitoliniersbot.helper;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpHelper {

    final String JSONContentType = "application/json";
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    final static Logger LOG = LoggerFactory.getLogger(HttpHelper.class);

    public String get (String url){
        String returnValue = null;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            returnValue = responseBody.string();
            LOG.info(returnValue);
        }
        catch (IOException e){
            LOG.error(e.getMessage());
        }
        return returnValue;
    }

    public <T> T get(String url, Class<T> classOfT) {
        String result = get(url);
        T returnValue = gson.fromJson(result, classOfT);
        return returnValue;
    }

    public <T> T post(String url, Class<T> classOfT, Object data) {
        T returnValue =null;
        RequestBody body = RequestBody.create(gson.toJson(data), MediaType.parse(JSONContentType));

        Request req = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(req).execute()){

            String result = response.body().string();

            LOG.info(result);

            returnValue = gson.fromJson(result, classOfT);
        }
        catch (IOException e){
            LOG.error(e.getMessage());
        }

        return returnValue;
    }
}

