package com.yt.tools.thread;

import java.util.Date;

/**
 * @author wuyang
 * @date 2018/5/17 15:23
 */
public class SimpleThread extends Thread{

    int pauseTime;
    String name;

    public SimpleThread(int x, String n){
        pauseTime = x;
        name = n;
    }

    @Override
    public void run(){
        while (true){
            try{
                System.out.println(name + ":" +new Date(System.currentTimeMillis()));
                Thread.sleep(pauseTime);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        SimpleThread s1 = new SimpleThread(1000,"Fast Guy");
        s1.start();
        SimpleThread s2 = new SimpleThread(3000,"Slow Guy");
        s2.start();

    }
}
