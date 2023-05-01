package com.wgh.service.impl;

import com.wgh.bean.dto.ResponsibilityChainContextDTO;

/**
 * @Author wughua
 * @Description TestService
 * @Date 2023/4/15
 */
public interface TestService {

    /**
     * 测试责任链
     *
     * @param responsibilityChainContextDTO
     * @return
     */
    ResponsibilityChainContextDTO testResponsibilityChain(ResponsibilityChainContextDTO responsibilityChainContextDTO);
}
