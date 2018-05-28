package com.yt.tools.thread;

import java.util.Date;

/**
 * @author wuyang
 * @date 2018/5/17 15:45
 */
public class SimpleRunnable implements Runnable {

    int pauseTime;
    String name;

    public SimpleRunnable(int pauseTime, String name) {
        this.pauseTime = pauseTime;
        this.name = name;
    }

    @Override
    public void run(){
        while(true) {
            try {
                System.out.println(name + ":" + new
                        Date(System.currentTimeMillis()));
                Thread.sleep(pauseTime);
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new SimpleRunnable(1000, "Fast Guy"));
        Thread t2 = new Thread(new SimpleRunnable(3000, "Slow Guy"));

        t1.start();
        t2.start();
    }

}
