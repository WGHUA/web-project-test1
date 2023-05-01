package com.wgh.chian.action.async;

import com.wgh.bean.dto.ResponsibilityChainContextDTO;
import com.wgh.chian.AsyncAbstractAction;
import com.wgh.common.exception.ChainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author wughua
 * @Description Action2
 * @Date 2023/4/15
 */
@Service
@Slf4j
public class AsyncAction2 extends AsyncAbstractAction<ResponsibilityChainContextDTO> {
    @Override
    protected void executeAction(ResponsibilityChainContextDTO context) throws ChainException {
        log.info("责任链{}执行开始...", AsyncAction2.log.getName());
        context.setEmil("xxxxx@email.com");
        log.info("责任链{}执行结束...", AsyncAction2.log.getName());
    }
}
