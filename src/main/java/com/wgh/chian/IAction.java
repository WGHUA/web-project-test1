package com.wgh.chian;

import com.wgh.common.exception.ChainException;

/**
 * @Author wughua
 * @Description IAction
 * @Date 2023/4/15
 */
public interface IAction<T extends IContext> {

    /**
     * action执行
     *
     * @param context
     * @throws ChainException
     */
    void execute(T context) throws ChainException;

    /**
     * 获取action名称
     *
     * @return
     */
    default String getName() {
        return this.getClass().getSimpleName();
    }
}
