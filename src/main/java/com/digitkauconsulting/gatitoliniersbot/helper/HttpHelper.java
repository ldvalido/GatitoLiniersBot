package com.digitkauconsulting.gatitoliniersbot.helper;

import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
@Service
public class HttpHelper {

    final HashMap<HttpHelperContentType, String> contentTypes = new HashMap<>();
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    final static ValvedLogger LOG = new ValvedLogger(HttpHelper.class ,1);

    ApplicationContext context;
    public HttpHelper(ApplicationContext context){
        this.context = context;
        contentTypes.put(HttpHelperContentType.TextPlain, "HttpHandlerText");
        contentTypes.put(HttpHelperContentType.Json, "HttpHandlerJson");

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

        HttpHelperEncodingHandler handler = (HttpHelperEncodingHandler) context.getBean(contentTypes.get(contentType));

        RequestBody body = handler.preAction(data);

        Request req = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(req).execute()){

            String result = response.body().string();

            LOG.info(result);

            returnValue = handler.postAction(result, classOfT);
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
