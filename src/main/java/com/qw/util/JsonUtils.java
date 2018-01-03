/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qw.util;

import com.google.common.base.Strings;
import com.google.gson.Gson;

/**
 * json解析
 * @author kaifa
 */
public class JsonUtils {

    public static String convertToJson(Object obj) {
        if (obj == null) {
            return "{}";
        }
        String json = new Gson().toJson(obj);
        return Strings.isNullOrEmpty(json) ? "{}" : json;
    }

    public static <T> T convertToObject(String json, Class<T> clazz) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return new Gson().fromJson(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }
}
