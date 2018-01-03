package com.qw.sign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: sign生成方法
 * @author: zhang_zhaohui
 * @date: 2014-8-7 上午10:05:50
 * @version: V1.0
 */
public class SignUtil {
    
    protected final static Logger logger = LoggerFactory.getLogger(ServerSignCheck.class);

    /**
     * 新的md5签名，首尾放secret。
     * 
     * @param params
     *        传给服务器的参数
     * 
     * @param secret
     *        分配给您的APP_SECRET
     */
    public static String md5Signature(TreeMap<String, String> params, String secret) {
        String result = null;
        StringBuffer orgin = getBeforeSign(params, new StringBuffer(secret));
        if (orgin == null) return result;
        // secret last
        orgin.append(secret);
        logger.info("orgin = " + orgin.toString());
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            result = byte2hex(md.digest(orgin.toString().getBytes("utf-8")));
        } catch (Exception e) {
            throw new RuntimeException("sign error !");
        }
        return result;
    }

    /**
     * 二进制转字符串
     */
    private static String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) hs.append("0").append(stmp);
            else hs.append(stmp);
        }
        return hs.toString();
    }

    /**
     * 添加参数的封装方法
     * 
     * @param params
     * @param orgin
     * @return
     */
    private static StringBuffer getBeforeSign(TreeMap<String, String> params, StringBuffer orgin) {
        if (params == null) return null;
        Map<String, Object> treeMap = new TreeMap<String, Object>();
        treeMap.putAll(params);
        Iterator<String> iter = treeMap.keySet().iterator();
        while (iter.hasNext()) {
            String name = (String) iter.next();
            orgin.append(name).append(params.get(name));
        }
        return orgin;
    }

}
