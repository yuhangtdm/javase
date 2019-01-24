package com.dity.se.thread.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin 框架
 *
 * @author:yuhang
 * @Date:2019/1/24
 */

public class TestForkJoin {
    //数组
    private static int[] numbs = new int[1000000];
    // 临界值
    private static int max_zize = 50000;
    // 初始化数据
    static {
        for (int i=0 ; i<numbs.length; i++) {
            numbs[i]=i;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long sum = 0;
        for (int i=0 ; i<numbs.length; i++) {
            sum+=numbs[i];
        }
        System.out.println(sum);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        AddTask addTask = new AddTask(0,numbs.length);
        ForkJoinTask<Long> submit = forkJoinPool.submit(addTask);
        System.out.println(submit.get());
    }

    /**
     * Task类
     */
    static class AddTask extends RecursiveTask<Long> {
        private int begin;
        private int end;
        public AddTask(int begin,int end){
            this.begin = begin;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end-begin<max_zize){
                long sum = 0L;
                for (int i = begin;i<end;i++){
                    sum+=numbs[i];
                }
                return sum;
            }else {
                // 任务拆分 类似于递归
                int middle =begin + (end - begin)/2;
                AddTask addTask1 = new AddTask(begin,middle);
                AddTask addTask2 = new AddTask(middle,end);
                addTask1.fork();
                addTask2.fork();
                return addTask1.join()+addTask2.join();
            }
        }
    }

}


