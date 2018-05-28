package com.yt.tools.thread;

/**
 * @author wuyang
 * @date 2018/5/17 16:34
 */
public  class  Bank{

    static int money = 1000;


    /**
     * 柜台Counter取钱的方法
     * @param money 每次取走的钱
     */
    public void Counter(int money){
        Bank.money -= money;
        System.out.println("A取走了" + money + "还剩下" + (Bank.money));
    }

    /**
     * ATM取钱的方法
     * @param money 每次取走的钱
     */
    public void ATM(int money) {
        //取钱后总数减少
        Bank.money -= money;
        System.out.println("B取走了" + money + "还剩下" + (Bank.money));
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        PersonA a = new PersonA(bank);
        PersonB b = new PersonB(bank);
        a.start();
        b.start();
    }
}
