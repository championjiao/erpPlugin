package com.qw.util;

import org.apache.commons.collections.MapUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/17.
 */
public class ParamUtil {
    public ParamUtil() {
    }

    public static Map<String, Object> getParams(HttpServletRequest request) {
        return getParams(request, false);
    }

    public static Map<String, Object> getParams(HttpServletRequest request, boolean isTrim) {
        Map reqParamMap = handleServletParameter(request, isTrim);
        if(StringUtils.isNullOrEmpty(reqParamMap.get("page"))) {
            reqParamMap.put("page", Integer.valueOf(1));
        }

        if(StringUtils.isNullOrEmpty(reqParamMap.get("rows"))) {
            reqParamMap.put("rows", Integer.valueOf(10));
        }

        return reqParamMap;
    }

    public static Map<String, Object> handleServletParameter(HttpServletRequest request, boolean isTrim) {
        Map requestParameter = request.getParameterMap();
        Object multipartParameter = new HashMap();
        if(null != request.getAttribute("multipartAttribute")) {
            multipartParameter = (Map)request.getAttribute("multipartAttribute");
        }

        HashMap paramMap = new HashMap(32);
        paramMap.putAll(requestParameter);
        paramMap.putAll((Map)multipartParameter);
        HashMap reqParamMap = new HashMap(32);
        Iterator i$ = paramMap.entrySet().iterator();

        while(i$.hasNext()) {
            Map.Entry entry = (Map.Entry)i$.next();
            String key = (String)entry.getKey();
            Object[] value = (Object[])((Object[])entry.getValue());
            if(isTrim) {
                reqParamMap.put(key, value.length > 1?value:(value[0] instanceof String?StringUtils.trim(value[0]):value[0]));
            } else {
                reqParamMap.put(key, value.length > 1?value:value[0]);
            }
        }

        return reqParamMap;
    }

    public static Integer getPage(Map<String, Object> reqParamMap) {
        return !MapUtils.isEmpty(reqParamMap) && !StringUtils.isNullOrEmpty(reqParamMap.get("page"))?Integer.valueOf(String.valueOf(reqParamMap.get("page"))):Integer.valueOf(1);
    }

    public static Integer getPageSize(Map<String, Object> reqParamMap) {
        return !MapUtils.isEmpty(reqParamMap) && !StringUtils.isNullOrEmpty(reqParamMap.get("rows"))?Integer.valueOf(String.valueOf(reqParamMap.get("rows"))):Integer.valueOf(10);
    }
}
