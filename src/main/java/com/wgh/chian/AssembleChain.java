package com.wgh.chian;

import com.wgh.chian.utils.AssembleSpringEnv;
import com.wgh.common.enums.ErrorEnum;
import com.wgh.common.exception.ChainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Author wughua
 * @Description 异步数据组装执行链
 * @Date 2023/4/15
 */
@Slf4j
public class AssembleChain<T extends ActionContext> {

    /**
     * 数据准备异步执行链
     */
    public List<Class<? extends AsyncAbstractAction<T>>> asyncPrepareChain = new ArrayList<>();

    /**
     * 数据组装同步链条
     */
    public List<Class<? extends AssembleAction<T>>> syncAssembleChain = new ArrayList<>();

    /**
     * 追加异步数据准备阶段的action
     *
     * @param action
     * @return
     */
    public AssembleChain<T> appendPrepareAction(Class<? extends AsyncAbstractAction<T>> action) {
        asyncPrepareChain.add(action);
        return this;
    }

    /**
     * 追加组装action
     *
     * @param action
     * @return
     */
    public AssembleChain<T> appendAssembleAction(Class<? extends AssembleAction<T>> action) {
        syncAssembleChain.add(action);
        return this;
    }

    /**
     * 异步数据包装执行
     *
     * @param context
     */
    public void execute(T context) {

        // 参数校验
        long chainStart = System.currentTimeMillis();
        if (context == null) {
            throw new ChainException(ErrorEnum.RESPONSIBILITY_CHAIN_ERROR.getCode(),
                    String.format(ErrorEnum.RESPONSIBILITY_CHAIN_ERROR.getMsg(), "责任链context为空"));
        }

        // 构建异步准备阶段的执行future
        List<CompletableFuture<Void>> asyncFutureList = buildPrepareFuture(context);

        long start = System.currentTimeMillis();
        // 异步执行数据准备阶段的Action
        processPrepareAction(asyncFutureList);
        log.info("AssembleChain|prepare|cost:" + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        // 同步执行打包阶段的Action
        processAssembleAction(context);
        log.info("AssembleChain|assemble|cost:" + (System.currentTimeMillis() - start) + "ms");

        log.info("AssembleChain|finish-execute|cost:" + (System.currentTimeMillis() - chainStart) + "ms");
    }

    private void processAssembleAction(T context) {
        IAction<T> assembleAction = null;
        while ((assembleAction = nextAssembleAction(assembleAction)) != null) {
            assembleAction.execute(context);
            if (context.isIdempotent()) {
                // 幂等返回
                break;
            }
        }
    }

    private IAction<T> nextAssembleAction(IAction<T> currentAction) {
        if (syncAssembleChain.size() == 0) {
            return null;
        }

        if (currentAction == null) {
            return AssembleSpringEnv.getBean(syncAssembleChain.get(0));
        }

        int currIndex = syncAssembleChain.indexOf(AopUtils.getTargetClass(currentAction));

        if (currIndex < 0) {
            throw new ChainException(ErrorEnum.RESPONSIBILITY_CHAIN_ERROR.getCode(),
                    String.format(ErrorEnum.RESPONSIBILITY_CHAIN_ERROR.getMsg(), "error class action " + currentAction.getClass().getName()));
        }

        if (currIndex == syncAssembleChain.size() - 1) {
            return null;
        } else {
            return AssembleSpringEnv.getBean(syncAssembleChain.get(currIndex + 1));
        }
    }

    private void processPrepareAction(List<CompletableFuture<Void>> asyncFutureList) {
        CompletableFuture<Void> prepareFuture = CompletableFuture.allOf(
                asyncFutureList.toArray(new CompletableFuture[0]));
        prepareFuture.join();
    }

    private List<CompletableFuture<Void>> buildPrepareFuture(T context) {
        List<CompletableFuture<Void>> asyncFutureList = new ArrayList<>();
        for (Class<? extends AsyncAbstractAction<T>> li : asyncPrepareChain) {
            AsyncAbstractAction<T> bean = AssembleSpringEnv.getBean(li);
            asyncFutureList.add(bean.getAsyncFuture(context));
        }
        return asyncFutureList;
    }

}
