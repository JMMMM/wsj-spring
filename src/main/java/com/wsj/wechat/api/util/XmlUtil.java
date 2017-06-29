package com.wsj.wechat.api.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlUtil {

    private static Logger logger = LoggerFactory.getLogger(XmlUtil.class);
    
    /**
     * request转字符串
     * @param request
     * @return
     */
    public static String parseRequst(HttpServletRequest request){
        String body = "";
        try {
            ServletInputStream inputStream = request.getInputStream(); 
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while(true){
                String info = br.readLine();
                if(info == null){
                    break;
                }
                if(body == null || "".equals(body)){
                    body = info;
                }else{
                    body += info;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("request转字符串出错",e);
        } catch (IOException e) {
        	logger.error("request转字符串出错",e);
        }            
        return body;
    }
    
    /**
     * 将从API返回的XML数据映射到Java对象
     * @param xml
     * @param clazz
     * @return
     */
    public static <T> T getObjectFromXML(String xml, Class clazz) {
        XStream xStreamForResponseData = new XStream(new DomDriver());
        xStreamForResponseData.alias("xml", clazz);
        xStreamForResponseData.ignoreUnknownElements();//暂时忽略掉一些新增的字段
        return (T) xStreamForResponseData.fromXML(xml);
    }

    /**
     * xml字符串转换成map
     * @param xmlString
     * @return
     */
    public static Map<String, String> getMapFromXML(String xmlString) {
        try {
            //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
            Document document = builder.parse(is);
            //获取到document里面的全部结点
            NodeList allNodes = document.getFirstChild().getChildNodes();
            Node node;
            Map<String, String> map = new HashMap<String, String>();
            int i = 0;
            while (i < allNodes.getLength()) {
                node = allNodes.item(i);
                if (node instanceof Element) {
                    map.put(node.getNodeName(), node.getTextContent());
                }
                i++;
            }
            return map;
        } catch (Exception e) {
            logger.error("解析返回数据出错", e);
            return null;
        }
    }
    
}
