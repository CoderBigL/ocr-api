package com.nmw.ocrapi.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 客户端工具类
 *
 * @author ljq
 */
public class ServletUtils {


    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        try {
            return getRequestAttributes().getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        try {
            return getRequestAttributes().getResponse();
        } catch (Exception e) {
            return null;
        }
    }


    public static ServletRequestAttributes getRequestAttributes() {
        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            RequestContextHolder.setRequestAttributes(attributes, true);

            return (ServletRequestAttributes) attributes;
        } catch (Exception e) {
            return null;
        }
    }

    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }
        }
        return map;
    }


    /**
     * 内容编码（主要是应对存在中文的情况）
     *
     * @param str 内容
     * @return 编码后的内容
     */
    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 内容解码
     *
     * @param str 内容
     * @return 解码后的内容
     */
    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
