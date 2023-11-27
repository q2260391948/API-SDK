package com.example.apiclient;

import com.example.apiclient.client.ApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("api.client")
@ComponentScan
public class ApiClintConfig {
    /**
     * 调⽤签名
     */
    private String accseeKey;
    /**
     * 密钥
     */
    private String secreKey;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 方法
     */
    private String method;

    @Bean
    public ApiClient getClient(){
        return new ApiClient(accseeKey,secreKey,url,method);
    }
}
