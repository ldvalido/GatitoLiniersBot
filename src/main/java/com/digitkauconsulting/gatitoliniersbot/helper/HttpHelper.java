package com.digitkauconsulting.gatitoliniersbot.helper;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;

public class HttpHelper {

    final HashMap<HttpHelperContentType, String> contentTypes = new HashMap<>();
    final String JSONContentType = "application/json";
    final String TEXTPLAINContentType="text/plain";
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    final static ValvedLogger LOG = new ValvedLogger(HttpHelper.class ,1);

    public HttpHelper(){
        contentTypes.put(HttpHelperContentType.TextPlain, TEXTPLAINContentType);
        contentTypes.put(HttpHelperContentType.Json, JSONContentType);
    }
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

    public <T> T post(String url, Class<T> classOfT, Object data, HttpHelperContentType contentType) {
        T returnValue =null;

        String bodyRaw = data.toString();

        //TODO: Refactor it
        if (contentType == HttpHelperContentType.Json){
            bodyRaw = gson.toJson(data);
        }
        RequestBody body = RequestBody.create(bodyRaw, MediaType.parse(contentTypes.get(contentType)));

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

    public <T> T post(String url, Class<T> classOfT, Object data) {
       return post(url, classOfT, data, HttpHelperContentType.Json);
    }
}

