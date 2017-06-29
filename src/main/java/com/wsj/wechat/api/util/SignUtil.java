package com.wsj.wechat.api.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uap.common.utils.ObjectUtils;

/**
 * 签名工具类
 */
public class SignUtil {

    private static Logger logger = LoggerFactory.getLogger(SignUtil.class);

    /**
     * 生成签名
     *
     * @param t   生成签名对象
     * @param key 签名key
     * @param <T> 生成签名对象
     * @return 签名
     */
    public static <T> String genSign(T t, String key) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String fieldName = property.getName();
                if (fieldName.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method reader = property.getReadMethod();
                Object value = reader != null ? reader.invoke(t) : null;
                if (value != null && !value.equals("")) {
                    list.add(fieldName + "=" + value + "&");
                }
            }
        } catch (Exception e) {
            logger.error("无效参数异常", e);
        }
        return sign(key, list);
    }

    /**
     * 生成签名
     *
     * @param map 生成签名集合
     * @param key 签名key
     * @return 签名
     */
    public static String genSign(Map<String, Object> map, String key) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().equals("")) {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        return sign(key, list);
    }

    public static String genMapSign(Map<String, String> map, String key) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().equals("")) {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        return sign(key, list);
    }
    
    private static String sign(String key, ArrayList<String> list) {
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        logger.info("md5加密前的参数值：\n{}", result);
        result += "key=" + key;
        result = MD5Util.MD5Encode(result).toUpperCase();
        return result;
    }

    /**
     * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
     *
     * @param responseString API返回的XML数据字符串
     * @return API签名是否合法
     */
    public static boolean checkSign(String responseString, String key) {

        Map<String, String> map = XmlUtil.getMapFromXML(responseString);
        if (map == null) {
            return false;
        }

        String signFromAPIResponse = map.get("sign").toString();
        if (ObjectUtils.isNull(signFromAPIResponse)) {
            logger.error("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
            return false;
        }
        logger.info("服务器回包里面的签名是:" + signFromAPIResponse);
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        map.put("sign", "");
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        String signForAPIResponse = genMapSign(map, key);
        logger.info("回包数据签名结果:\n{}", signForAPIResponse);
        if (!signForAPIResponse.equals(signFromAPIResponse)) {
            //签名验不过，表示这个API返回的数据有可能已经被篡改了
            logger.error("API返回的数据签名验证不通过，有可能被第三方篡改!!!");
            return false;
        }
        logger.info("API返回的数据签名验证通过!!!");
        return true;
    }

}
