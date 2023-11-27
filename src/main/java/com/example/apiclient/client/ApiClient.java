package com.example.apiclient.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.apiclient.model.Param;

import java.util.HashMap;
import java.util.Map;

import static com.example.apiclient.utils.SignUtils.getSign;

/**
 * @author:22603
 * @Date:2023/7/7 3:25
 */

public class ApiClient {


    private String accessKey;
    private String secretKey;
    private String url;
    private String method;

    public ApiClient(String accessKey, String secretKey,String url,String method) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.url=url;
        this.method=method;
    }

    /**
     * get方法
     *
     * @param param
     * @return
     */

    public String getMethod(String url, String param) {
        String json = JSONUtil.toJsonStr(param);
       return HttpRequest.get(url).addHeaders(getHeadMap(json)).body(json)
               .execute().body();
    }


    private Map<String, String> getHeadMap(String body) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", getSign(body, secretKey));
        hashMap.put("url",url);
        hashMap.put("method",method);
        return hashMap;
    }

    /**
     * @param param
     * @return
     */
    public String getPostMethod(Param param) {
        String json = JSONUtil.toJsonStr(param);
        //请求转发到网关
        return HttpRequest.post(url)
                .addHeaders(getHeadMap(json))
                .body(json)
                .execute().body();
    }

}
