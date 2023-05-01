package com.wgh.service.impl.impl;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.log.Log;
import com.wgh.bean.dto.ResponsibilityChainContextDTO;
import com.wgh.chian.AssembleChain;
import com.wgh.chian.action.async.AsyncAction1;
import com.wgh.chian.action.async.AsyncAction2;
import com.wgh.chian.action.sync.SyncAction1;
import com.wgh.chian.action.sync.SyncAction2;
import com.wgh.service.impl.TestService;
import com.wgh.util.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.channels.Channel;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author wughua
 * @Description TestServiceImpl
 * @Date 2023/4/15
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {


    @Override
    public ResponsibilityChainContextDTO testResponsibilityChain(ResponsibilityChainContextDTO responsibilityChainContextDTO) {

        log.info("责任链执行开始｜dto={}", JSON.toJSONString(responsibilityChainContextDTO));

        AssembleChain<ResponsibilityChainContextDTO> chain = new AssembleChain<>();

        // 添加异步准备的Action
        chain.appendPrepareAction(AsyncAction1.class)
                .appendPrepareAction(AsyncAction2.class);

        // 添加同步数据包装的Action
        chain.appendAssembleAction(SyncAction1.class)
                .appendAssembleAction(SyncAction2.class);

        chain.execute(responsibilityChainContextDTO);

        log.info("责任链执行完成｜dto={}", JSON.toJSONString(responsibilityChainContextDTO));

        return responsibilityChainContextDTO;
    }
}
