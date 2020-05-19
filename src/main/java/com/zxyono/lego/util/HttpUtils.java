package com.zxyono.lego.util;

import com.zxyono.lego.constant.HttpStatus;
import com.zxyono.lego.exception.HttpRequestException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 此类用来在服务器端发起get请求
 */
public class HttpUtils {
    public static String doGet(String urlPath, HashMap<String, Object> params) {
        try {
            StringBuilder sb = new StringBuilder(urlPath);
            if (params != null && !params.isEmpty()) {
                sb.append("?");

                Set<Map.Entry<String, Object>> set = params.entrySet();
                for (Map.Entry<String, Object> entry : set) {
                    String key = entry.getKey();
                    String value = "";
                    if (null != entry.getValue()) {
                        value = entry.getValue().toString();
                        value = URLEncoder.encode(value, "UTF-8");
                    }
                    sb.append(key).append("=").append(value).append("&");
                }

                sb.deleteCharAt(sb.length() - 1);
            }
            URL url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == HttpStatus.SUCCESS) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                StringBuilder sbs = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sbs.append(line);
                }
                return sbs.toString();
            }
            return null;
        } catch (Exception e) {
            throw new HttpRequestException();
        }
    }
}