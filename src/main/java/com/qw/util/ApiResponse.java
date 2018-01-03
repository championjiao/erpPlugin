package com.qw.util;

import com.qw.erp.controller.Vo.ErpOrderListVo;

/**
 *
 * @ClassName: ApiResponse
 * @Description: 返回值处理
 * @author zhumeiqing
 * @date 2017年3月8日 下午16:22:11
 *
 */
public class ApiResponse {
    private static final String RESULT_OK = "OK";
    private static final String RESULT_FAIL = "FAIL";
    private static final ApiBody OkBody = new ApiBody();
    private String result = "OK";
    private int errorCode;
    private String errorDescription;
    private ApiBody body;

    public ApiResponse() {
    }

    public static ApiResponse OK() {
        ApiResponse ok = new ApiResponse();
        ok.result = "OK";
        ok.body = OkBody;
        return ok;
    }

    public static ApiResponse OK(ApiBody okBody) {
        ApiResponse ok = new ApiResponse();
        ok.result = "OK";
        ok.body = okBody;
        return ok;
    }

    public static ApiResponse FAIL(int errorCode, String errorDescription) {
        ApiResponse fail = new ApiResponse();
        fail.result = "FAIL";
        fail.errorCode = errorCode;
        fail.errorDescription = errorDescription;
        return fail;
    }

    public ApiResponse body(ApiBody body) {
        this.body = body;
        return this;
    }

    public ApiResponse description(String description) {
        this.errorDescription = description;
        return this;
    }

    public ApiResponse code(int code) {
        this.errorCode = code;
        return this;
    }

    public String toString() {
        return "ApiResponse{result=" + this.result + ", errorCode=" + this.errorCode + ", errorDescription=" + this.errorDescription + ", body=" + this.body + '}';
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public ApiBody getBody() {
        return this.body;
    }

    public void setBody(ApiBody body) {
        this.body = body;
    }

    public static class Promotion extends ApiBody {
        private String name;

        public Promotion(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
