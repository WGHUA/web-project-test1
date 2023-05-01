package com.wgh.chian;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

/**
 * @Author wughua
 * @Description AsyncAbstractAction
 * @Date 2023/4/15
 */
public abstract class AsyncAbstractAction<T extends ActionContext> extends AbstractAction<T> {

    @Resource
    private ThreadPoolTaskExecutor scmAssembleChainExecutor;

    public CompletableFuture<Void> getAsyncFuture(T context){
        return CompletableFuture.supplyAsync(()->{
            execute(context);
            return null;
        },scmAssembleChainExecutor);
    }
}
