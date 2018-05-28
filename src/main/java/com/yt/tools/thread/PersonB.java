package com.yt.tools.thread;

/**
 * @author wuyang
 * @date 2018/5/17 16:36
 */
public class PersonB extends Thread {
    Bank bank;

    public PersonB(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        synchronized (bank) {
            while (Bank.money >= 200) {
                bank.ATM(200);
                try {
                    //Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
