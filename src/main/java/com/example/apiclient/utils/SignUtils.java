package com.example.apiclient.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @author:22603
 * @Date:2023/7/7 19:27
 */
public class SignUtils {

    public static String getSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body + "." + secretKey;
        System.out.println("content:" + content);
        return md5.digestHex(content);
    }

}
