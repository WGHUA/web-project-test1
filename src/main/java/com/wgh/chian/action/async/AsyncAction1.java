package com.wgh.chian.action.async;

import com.mysql.cj.log.Log;
import com.wgh.bean.dto.ResponsibilityChainContextDTO;
import com.wgh.chian.AsyncAbstractAction;
import com.wgh.common.exception.ChainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author wughua
 * @Description Action1
 * @Date 2023/4/15
 */
@Service
@Slf4j
public class AsyncAction1 extends AsyncAbstractAction<ResponsibilityChainContextDTO> {

    @Override
    protected void executeAction(ResponsibilityChainContextDTO context) throws ChainException {
        log.info("责任链{}执行开始...", AsyncAction1.log.getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        context.setAge(23);
        log.info("责任链{}执行结束...", AsyncAction1.log.getName());
    }

}
