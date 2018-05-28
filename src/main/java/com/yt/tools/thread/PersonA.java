package com.yt.tools.thread;

/**
 * @author wuyang
 * @date 2018/5/17 16:36
 */
public class PersonA extends Thread {

    Bank bank;

    public PersonA(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        synchronized (bank) {
            while (Bank.money >= 10) {
                try {
                    bank.Counter(10);
                    //Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
