package com.wgh.chian;

import lombok.Data;

/**
 * @Author wughua
 * @Description Actioncontext
 * @Date 2023/4/15
 */
@Data
public class ActionContext<T> implements IContext {

    /**
     * 是否幂等
     */
    private boolean idempotent = false;

    private T target;

    public T getTarget() {
        return target;
    }

}
