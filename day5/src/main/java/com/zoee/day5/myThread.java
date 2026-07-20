package com.zoee.day5;

import java.util.concurrent.*;

public class myThread extends  Thread{

    static ExecutorService executorService =new ThreadPoolExecutor(
            3, //核心线程数
            5, //最大线程数
            1L, //线程空闲时间
            TimeUnit.SECONDS, //时间单位
            new ArrayBlockingQueue<>(3), //任务队列
            Executors.defaultThreadFactory(), //线程工厂
            new ThreadPoolExecutor.AbortPolicy() //拒绝策略
    );



}
