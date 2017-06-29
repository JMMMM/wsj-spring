package com.wsj.wechat.api.http;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * 发送http请求
 */
public class HttpSend {

    private static Logger logger = LoggerFactory.getLogger(HttpSend.class);
    
    public static <T> String sendRequest(String url, T t, HttpClientHolder httpClientHolder) {
        logger.info("微信支付请求的url是：{}", url);
        HttpPost httpPost = new HttpPost(url);

        //解决XStream对出现双下划线的bug
        XStream xStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        xStream.alias("xml", t.getClass()); 
        String postDataXML = xStream.toXML(t);
        logger.info("微信支付请求的参数是：\n{}", postDataXML);
        
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        String result = null;
        try {
            HttpResponse response = httpClientHolder.getHttpClient().execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            logger.info("微信支付接收的数据是：\n{}", result);
        } catch (ConnectionPoolTimeoutException e) {
            logger.error("http get throw ConnectionPoolTimeoutException(wait time out)");
        } catch (ConnectTimeoutException e) {
            logger.error("http get throw ConnectTimeoutException");
        } catch (SocketTimeoutException e) {
            logger.error("http get throw SocketTimeoutException");
        } catch (Exception e) {
            logger.error("http get throw Exception" + e);
        } finally {
            httpPost.abort();
        }
        return result;
    }

    /**
     * 发送post请求
     * @param url
     * @param requestData
     * @return
     */
    public static String post(String url, String requestData) {
        logger.info("请求的url是：{}", url);
        logger.info("请求的参数是：\n{}", requestData);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "text/xml");
        if(StringUtils.isNotBlank(requestData)){
        	StringEntity postEntity = new StringEntity(requestData, "UTF-8");
        	httpPost.setEntity(postEntity);
        }

        String result = null;
        try {
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            logger.info("返回的数据是：\n{}", result);
        } catch (ConnectionPoolTimeoutException e) {
            logger.error("http get throw ConnectionPoolTimeoutException(wait time out)");
        } catch (ConnectTimeoutException e) {
            logger.error("http get throw ConnectTimeoutException");
        } catch (SocketTimeoutException e) {
            logger.error("http get throw SocketTimeoutException");
        } catch (Exception e) {
            logger.error("http get throw Exception" + e);
        } finally {
            httpPost.abort();
            //关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
            	logger.error("httpclient close throw Exception" + e);
            }  
        }
        return result;
    }
    
    /**
     * 发送 get请求
     * @param url 请求url
     * @return
     */
    public static String get(String url) {  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        String result = null;
        try {  
            HttpGet httpget = new HttpGet(url);  
            CloseableHttpResponse response = httpclient.execute(httpget);  
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            logger.info("返回的数据是：\n{}", result);
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return result;
    }  
    
}
