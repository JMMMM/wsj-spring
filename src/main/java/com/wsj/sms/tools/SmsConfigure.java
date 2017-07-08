package com.wsj.sms.tools;

/**
 * 短信平台配置信息
 */
public class SmsConfigure {

    private static String username;

    private static String password;

    private static String productId;

    private static String url;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SmsConfigure.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SmsConfigure.password = password;
    }

    public static String getProductId() {
        return productId;
    }

    public static void setProductId(String productId) {
        SmsConfigure.productId = productId;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        SmsConfigure.url = url;
    }
}
