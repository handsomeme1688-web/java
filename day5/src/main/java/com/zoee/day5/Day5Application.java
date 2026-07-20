package com.zoee.day5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day5Application {

    public static void main(String[] args) {
        SpringApplication.run(Day5Application.class, args);
        for (int i = 0; i < 100; i++) {
            myThread.executorService.execute(()->{
                try{
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                }catch (Exception e){
                    e.getMessage();
                }
            });
        }
    }

}
