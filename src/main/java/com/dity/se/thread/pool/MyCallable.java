package com.dity.se.thread.pool;

import java.util.concurrent.Callable;

/**
 * @author:yuhang
 * @Date:2019/1/23
 */
public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 3;
    }
}
