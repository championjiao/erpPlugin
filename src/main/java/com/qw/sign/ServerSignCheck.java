/**
 *
 */
package com.qw.sign;

import com.google.common.base.Strings;
import com.qw.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * @Description: 参数合法性校验
 * @author: zhang_zhaohui
 * @date: 2014-8-7 上午10:14:50
 * @version: V1.0
 */
public class ServerSignCheck {

    @Value("${secret_key}") private String secretKey;

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ServerSignCheck.class.getName());

    public static final String SIGN_KEY = "sign";
    @SuppressWarnings("unused")
    public static final String TIMESTAMP_KEY = "timestamp";
    @SuppressWarnings("unused")
    private static final long interval = (long) (0.5 * 60 * 1000); // 0.5min

    public static boolean checkSignApi(TreeMap<String, String> paramMap, String secretKey) {
        String sign = paramMap.get(SIGN_KEY);
        if (StringUtils.isNullOrEmpty(sign)) {
            logger.info("[api-check]require api without sign code");
            return false;
        }
        paramMap.remove("sign");
        String serverSign = SignUtil.md5Signature(paramMap, secretKey);
        if (!serverSign.equalsIgnoreCase(sign)) {
            logger.info("[api-check]require api with illegal sign code");
            return false;
        }
        return true;
    }

    //参数签名
    public static TreeMap<String, String> signApi(TreeMap<String, String> paramMap, String secretKey) {
        TreeMap<String, String> kvs = new TreeMap<String, String>();
        Iterator it = paramMap.keySet().iterator();
        while (it.hasNext()) {
            kvs.put(it.next().toString(), paramMap.get(it.next()));
        }

        String serverSign = SignUtil.md5Signature(kvs, secretKey);
        kvs.put("sign", serverSign);
        return kvs;
    }


    public static boolean checkTime(HttpServletRequest request, int interval) {
        try {
            String timeStr = request.getParameter(TIMESTAMP_KEY);
            timeStr = !Strings.isNullOrEmpty(timeStr) && timeStr.length() == 10 ? timeStr + "000" : timeStr;
            long timestamp = Long.parseLong(timeStr);
            if (Math.abs(System.currentTimeMillis() - timestamp) > interval * 1000) {
                logger.info("[api-check]require api during illegal time");
                return false;
            }
        } catch (NumberFormatException numberFormatException) {
            logger.info("[api-check]require api during illegal time format");
            return false;
        }
        return true;
    }

    public static boolean checkSign(TreeMap<String, String> paramMap, String secretKey) {
        if (null == paramMap.get("sign")) {
            logger.info("[api-check]require api without sign code");
            return false;
        }
        String sign = paramMap.get("sign");
        if (sign == null) {
            logger.info("[api-check]require api without sign code");
            return false;
        }
        // 排除sign，重新生成sign，验证是否正确
        paramMap.remove("sign");
        String serverSign = SignUtil.md5Signature(paramMap, secretKey);
        logger.info("server sign=" + serverSign + ",client sign=" + sign);
        return serverSign.equals(sign);
    }
}
