package com.qw.sign;

import com.qw.util.ApiResponse;
import com.qw.util.JsonUtils;
import com.qw.util.StringUtils;
import com.qw.util.WebUtils;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.TreeMap;

/**
 * 用于请求参数合法性校验 参数中加密串的key名称为sign 参数首尾放secret后进行md5加密生成sign
 *
 * @author zhang_xiaolei
 * @date:2014年9月24日 下午2:53:36
 * @version:V2.0
 */
public class ParamFilter implements Filter {

    @Value("${secret_key}") private String secretKey;
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ParamFilter.class.getName());

    private final static String versionKey = "v";
    private static final List<String> signPrefix = Arrays.asList("/erp/");

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        String contentType = request.getContentType();
        contentType = StringUtils.isNullOrEmpty(contentType) ? "" : contentType;
        if (contentType.toLowerCase().contains("application/json") // application/json请求体跳过
                || isNeedNotCheck(uri) // 不被检测的url或开关
                ) {
            chain.doFilter(req, rep);
            return;
        }

        if (isContainsSignPrefix(uri)) {
            // 新的校验规则
            Enumeration<String> parameterNames = req.getParameterNames();
            TreeMap<String, String> kvs = new TreeMap<String, String>();
            boolean hasSign = false;
            while (parameterNames.hasMoreElements()) {
                String key = parameterNames.nextElement();
                if (key.equalsIgnoreCase(ServerSignCheck.SIGN_KEY)) {
                    hasSign = true;
                }
                if (key.equalsIgnoreCase(versionKey)) {
                    // 如果请求的uri中包含版本号。app可能没有参与计算，直接跳过
                    chain.doFilter(req, rep);
                    return;
                }
                kvs.put(key, URLDecoder.decode(req.getParameter(key),"UTF-8"));
            }
            if (hasSign) {
                // 请求参数中包含Sign：App请求，校验Sign
                if (ServerSignCheck.checkSignApi(kvs, secretKey)) {
                    chain.doFilter(req, rep);
                    return;
                }
            } else {
                // 请求参数中不包含Sign：H5请求，校验Referer
                logger.debug("sign check for html5 =========> " + "请求参数中不包含Sign：H5请求，校验Referer");
                String referer = request.getHeader("Referer");
                referer = StringUtils.isNullOrEmpty(referer) ? "" : referer.toLowerCase();
                String serverDomain = WebUtils.getServerTopDomain(request).toLowerCase();
                logger.debug("sign check for html5 =========> " + "Doamin/Referer:" + serverDomain + "/" + referer);
                if (referer.contains(serverDomain)) {
                    chain.doFilter(req, rep);
                    logger.debug("sign check for html5 =========> " + "通过");
                    return;
                }
            }
            // 校验不通过
            checkSignFailPrint(rep);
        } else {
            System.out.println("============ 没有使用sign校验：" + uri + " ===========");
            chain.doFilter(req, rep);
        }
    }

    private void checkSignFailPrint(ServletResponse rep) throws IOException {
        // 校验不通过
        PrintWriter writer = rep.getWriter();
        rep.setContentType("application/json, charset=utf-8");
        writer.print(JsonUtils.convertToJson(ApiResponse.FAIL(1001009, "Ooops, signature verification failed. Try again.")));
        logger.warn("Ooops, signature verification failed. Try again.");
        writer.flush();
    }

    private void checkTimeFailPrint(ServletResponse rep) throws IOException {
        // 校验不通过
        PrintWriter writer = rep.getWriter();
        rep.setContentType("application/json, charset=utf-8");
        writer.print(JsonUtils.convertToJson(ApiResponse.FAIL(1001010, "Ooops, time verification failed. Try again.")));
        logger.warn("Ooops, time verification failed. Try again.");
        writer.flush();
    }

     boolean check = true;

    private boolean isNeedNotCheck(String uri) {
        if (!check) {
            return true;
        }
        for (String exculdeUri : ApiUrls.signWhiteListUrls) {
            if (uri.toLowerCase().contains(exculdeUri.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isContainsSignPrefix(String uri) {
        for (String prefix : signPrefix) {
            if (uri.toLowerCase().contains(prefix.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
