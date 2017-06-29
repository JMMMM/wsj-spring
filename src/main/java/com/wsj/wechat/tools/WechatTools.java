package com.wsj.wechat.tools;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by Jimmy on 2017/6/29.
 */
public class WechatTools {
    private static final Logger logger = LoggerFactory.getLogger(WechatTools.class);

    public static String callerClient(String url) {
        String body = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            logger.info("create httppost:" + url);
            HttpGet get = new HttpGet(url);
            get.addHeader("Accept-Charset", "utf-8");
            HttpResponse response = sendRequest(httpClient, get);
            body = parseResponse(response);
        } catch (IOException e) {
            logger.error("send post request failed: {}", e.getMessage());
        }

        return body;
    }

    private static HttpResponse sendRequest(CloseableHttpClient httpclient, HttpUriRequest httpost)
            throws ClientProtocolException, IOException {
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        return response;
    }

    private static String parseResponse(HttpResponse response) {
        logger.info("get response from http server..");
        HttpEntity entity = response.getEntity();

        logger.info("response status: " + response.getStatusLine());
        Charset charset = ContentType.getOrDefault(entity).getCharset();
        if (charset != null) {
            logger.info(charset.name());
        }

        String body = null;
        try {
            body = EntityUtils.toString(entity, "utf-8");
            logger.info("body " + body);
        } catch (IOException e) {
            logger.warn("{}: cannot parse the entity", e.getMessage());
        }

        return body;
    }
}
