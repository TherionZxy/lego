package com.zxyono.lego.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Jcode2SessionUtil {
    public static String jscode2Session(String appid,String secret, String url,String code,String grantType) throws UnsupportedEncodingException, IOException {
        //定义返回的json对象
        JSONObject result = new JSONObject();
        //创建请求通过code换取session等数据
        System.out.println(url);
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        //建立一个NameValuePair数组，用于存储欲传送的参数
        params.add(new BasicNameValuePair("appid",appid));
        params.add(new BasicNameValuePair("secret",secret));
        params.add(new BasicNameValuePair("js_code",code));
        params.add(new BasicNameValuePair("grant_type",grantType));
        //设置编码
        httpPost.setEntity(new UrlEncodedFormEntity(params));//添加参数
        return EntityUtils.toString(HttpClientBuilder.create().build().execute(httpPost).getEntity());
    }
}