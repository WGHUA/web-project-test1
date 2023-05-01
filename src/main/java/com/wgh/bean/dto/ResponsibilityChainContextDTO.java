package com.wgh.bean.dto;

import com.wgh.chian.ActionContext;
import lombok.Builder;
import lombok.Data;

/**
 * @Author wughua
 * @Description 责任链测试类
 * @Date 2023/4/15
 */
@Data
@Builder
public class ResponsibilityChainContextDTO extends ActionContext {

    private String id;

    private String name;

    //-------以下作为责任链填充数据--------

    private Integer age;

    private String address;

    private String tel;

    private String emil;

}
