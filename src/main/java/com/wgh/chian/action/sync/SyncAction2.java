package com.wgh.chian.action.sync;

import com.wgh.bean.dto.ResponsibilityChainContextDTO;
import com.wgh.chian.AssembleAction;
import com.wgh.chian.AsyncAbstractAction;
import com.wgh.common.exception.ChainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author wughua
 * @Description Action3
 * @Date 2023/4/15
 */
@Slf4j
@Service
public class SyncAction2 extends AssembleAction<ResponsibilityChainContextDTO> {

    @Override
    protected void executeAction(ResponsibilityChainContextDTO context) throws ChainException {
        log.info("责任链{}执行开始...", SyncAction2.log.getName());
        context.setAddress("浙江省杭州市");
        log.info("责任链{}执行结束...", SyncAction2.log.getName());

    }
}
