package com.qw.util;

import com.qw.util.ParamUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

/**
 * Created by Administrator on 2017/2/17.
 */
public class BaseController {
    protected Integer page = Integer.valueOf(1);
    protected Integer pageSize = Integer.valueOf(10);

    public BaseController() {
    }

    public Map<String, Object> getParams() {
        return this.getParams(false);
    }

    public Map<String, Object> getParams(boolean isTrim) {
        return ParamUtil.getParams(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(), isTrim);
    }

    public int getPage() {
        return ParamUtil.getPage(this.getParams()).intValue();
    }

    public int getPageSize() {
        return ParamUtil.getPageSize(this.getParams()).intValue();
    }


}
