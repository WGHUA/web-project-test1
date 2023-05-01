package com.wgh.chian;

import com.wgh.common.constants.Constant;
import com.wgh.common.enums.ErrorEnum;
import com.wgh.common.exception.ChainException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author wughua
 * @Description AbstractAction
 * @Date 2023/4/15
 */
@Slf4j
public abstract class AbstractAction<T extends ActionContext> implements IAction<T> {

    @Override
    public void execute(T context) throws ChainException {
        try {
            long startTime = System.currentTimeMillis();
            if (!context.isIdempotent()) {
                executeAction(context);
            } else {
                log.info("action-execute|action:{} is idempotent|cost:{} ms", getName(), System.currentTimeMillis() - startTime);
            }
            long cost = System.currentTimeMillis() - startTime;
            if (cost > Constant.ONE_HUNDRED) {
                log.info("finish-executed|slow|action:{}|cost:{}ms", getName(), System.currentTimeMillis() - startTime);
            } else {
                log.info("finish-executed|action:{}|cost:{}ms", getName(), System.currentTimeMillis() - startTime);
            }
        } catch (Throwable e) {
            if (Exception.class.isAssignableFrom(e.getClass())) {
                log.error(String.format("Action-Biz-Exception|action:%s, errorCode:%s, errorMsg:%s", getName(),
                        ((ChainException) e).getErrorCode(), ((ChainException) e).getErrorMsg()));
                throw (ChainException) e;
            } else {
                log.error(String.format("Action-Exception|action:%s, errorCode:%s, errorMsg:%s", getName(),
                        e.getClass().getSimpleName(), e.getMessage()));
                // todo 抛出其他自定义异常
                throw new ChainException(ErrorEnum.RESPONSIBILITY_CHAIN_ERROR.getCode(),
                        String.format(ErrorEnum.RESPONSIBILITY_CHAIN_ERROR.getCode(), e.getMessage()));
            }
        }
    }

    protected abstract void executeAction(T context) throws ChainException;
}
