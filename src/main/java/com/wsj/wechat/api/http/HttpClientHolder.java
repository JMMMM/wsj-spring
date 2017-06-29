package com.wsj.wechat.api.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wsj.wx.api.util.WeiXinConfigure;

/**
 * httpClient 拥有者
 */
public class HttpClientHolder {

    private static Logger logger = LoggerFactory.getLogger(HttpClientHolder.class);

    private HttpClient httpClient;

    public HttpClientHolder() {
    	FileInputStream instream =  null;
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            instream = new FileInputStream(new File(WeiXinConfigure.getCertLocalPath()));
            keyStore.load(instream, WeiXinConfigure.getCertPassword().toCharArray());
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, WeiXinConfigure.getCertPassword().toCharArray())
                    .build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    new DefaultHostnameVerifier(PublicSuffixMatcherLoader.getDefault()));
            httpClient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();
        } catch (Exception e) {
            logger.error("创建httpClient出错", e);
        } finally {
            try {
                if (instream != null) {
                	instream.close();
                }
            } catch (IOException e) {
                logger.error("读取文件流出错", e);
            }
        }
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

}
