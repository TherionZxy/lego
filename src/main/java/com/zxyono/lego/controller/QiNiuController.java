package com.zxyono.lego.controller;

import com.qiniu.util.Auth;
import com.zxyono.lego.util.HttpUtils;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/qiniu")
public class QiNiuController {
    @Value("${lego.qiniu.ak}")
    private String accessKey;
    @Value("${lego.qiniu.sk}")
    private String secretKey;
    @Value("${lego.qiniu.bucket}")
    private String bucket;
    @Value("${lego.qiniu.cdn}")
    private String cdn;

    private String key;

    /**
     * 普通上传
     * @return
     */
    @GetMapping("auth")
    public ResultMap getAuth() {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return ResultMap.success("上传凭证申请成功",upToken);
    }

    /**
     * 覆盖上传
     * @param key
     * @return
     */
    @GetMapping("auth/cover")
    public ResultMap getAuth(@RequestParam("filename") String key) {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, key);
        return ResultMap.success("上传凭证申请成功",upToken);
    }

    /**
     * 刷新缓存
     * @param key
     * @return
     */
    @GetMapping("refresh")
    public ResultMap refresh(@RequestParam("filename") String key) {
        String result = HttpUtils.sendPostWithHeader("http://fusion.qiniuapi.com/v2/tune/refresh", "{\"urls\":[\""+cdn+key+"\"]}", "QBox A1v6koiMUF8Oi6quuPNW6zaYTKXjrBipgEFse-8l:YJq_MhD3qQ21kwb55nwuRlWMqrE=");
        return ResultMap.success(result);
    }
}