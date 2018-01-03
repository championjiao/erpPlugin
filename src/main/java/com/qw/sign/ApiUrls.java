/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qw.sign;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Sanlion.Do
 */
public final class ApiUrls {

    /**
     * SIGN校验白名单：此列表中的URL不做SIGN校验
     */
    public static final List<String> signWhiteListUrls = Arrays.asList();

    /**
     * 需要将返回值密文处理的URL
     */
    public static final List<String> cryptoUrls = Arrays.asList();
}
