package com.qw.util;

/**
 *
 * @ClassName: ApiBody
 * @Description: 返回状态信息
 * @author zhumeiqing
 * @date 2017年3月8日 下午16:22:11
 *
 */
public class ApiBody {
    private int apiStatus = 0;
    private String apiMessage = "";

    public ApiBody() {
    }

    public ApiBody(int apiStatus, String apiMessage) {
        this.apiStatus = apiStatus;
        this.apiMessage = apiMessage;
    }

    public int getApiStatus() {
        return this.apiStatus;
    }

    public void setApiStatus(int apiStatus) {
        this.apiStatus = apiStatus;
    }

    public String getApiMessage() {
        return this.apiMessage;
    }

    public void setApiMessage(String apiMessage) {
        this.apiMessage = apiMessage;
    }
}
