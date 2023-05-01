package com.wgh.chian.action.sync;

import com.wgh.bean.dto.ResponsibilityChainContextDTO;
import com.wgh.chian.AssembleAction;
import com.wgh.chian.AsyncAbstractAction;
import com.wgh.chian.action.async.AsyncAction2;
import com.wgh.common.exception.ChainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author wughua
 * @Description Action3
 * @Date 2023/4/15
 */
@Service
@Slf4j
public class SyncAction1 extends AssembleAction<ResponsibilityChainContextDTO> {

    @Override
    protected void executeAction(ResponsibilityChainContextDTO context) throws ChainException {
        log.info("责任链{}执行开始...", SyncAction1.log.getName());
        context.setTel("18399999999");
        log.info("责任链{}执行结束...", SyncAction1.log.getName());

    }
}
