package com.qw.erp.controller.Vo;

import com.qw.erp.eneity.ErpBranchProInfoDo;
import com.qw.erp.eneity.ErpOrderDo;
import com.qw.util.ApiBody;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: ErpBranchProListVo
 * @description: /*
 * 　　┏┓　　　┏┓+ +
 * 　┏┛┻━━━┛┻┓ + +
 * 　┃　　　　　　　┃
 * 　┃　　　━　　　┃ ++ + + +
 * 　┃████━████  ┃+
 * 　┃　　　　　　　┃ +
 * 　┃　　　┻　　　┃
 * 　┃　　　　　　　┃ + +
 * 　┗━┓　　　┏━┛
 * 　　　┃　　　┃
 * 　　　┃　　　┃ + + + +
 * 　　　┃　　　┃
 * 　　　┃　　　┃ +  神兽保佑
 * 　　　┃　　　┃    代码无bug
 * 　　　┃　　　┃　　+
 * 　　　┃　 　　┗━━━┓ + +
 * 　　　┃ 　　　　　　　┣┓
 * 　　　┃ 　　　　　　　┏┛
 * 　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　┃┫┫　┃┫┫
 * 　　　　┗┻┛　┗┻┛+ + + +
 * @author: Administrator
 * @create: 2017/4/12
 **/
public class ErpBranchProListVo extends ApiBody {

    private List<ErpBranchProInfoDo> proList = new ArrayList<ErpBranchProInfoDo>();

    public ErpBranchProListVo(List<ErpBranchProInfoDo> list) {
        if (null != list && list.size() > 0){
            proList.addAll(list);
        }
    }

    public List<ErpBranchProInfoDo> getProList() {
        return proList;
    }

    public void setProList(List<ErpBranchProInfoDo> proList) {
        this.proList = proList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
